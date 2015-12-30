package io.github.parkjoon.SpringSocialDemo.user.controller;

import io.github.parkjoon.SpringSocialDemo.security.util.SecurityUtil;
import io.github.parkjoon.SpringSocialDemo.user.model.AuthenticationServiceProvider;
import io.github.parkjoon.SpringSocialDemo.user.model.RegistrationForm;
import io.github.parkjoon.SpringSocialDemo.user.model.User;
import io.github.parkjoon.SpringSocialDemo.user.service.DuplicateEmailException;
import io.github.parkjoon.SpringSocialDemo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.*;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;

@Controller
@SessionAttributes("user")
public class RegistrationController {

    protected static final String ERROR_CODE_EMAIL_EXIST = "NotExist.user.email";
    protected static final String MODEL_NAME_REGISTRATION_DTO = "user";
    protected static final String VIEW_NAME_REGISTRATION_PAGE = "user/registrationForm";

    private final UserService service;
    private final ProviderSignInUtils providerSignInUtils;

    @Autowired
    public RegistrationController(UserService service, ConnectionFactoryLocator connectionFactoryLocator, UsersConnectionRepository usersConnectionRepository) {
        this.service = service;
        this.providerSignInUtils = new ProviderSignInUtils(connectionFactoryLocator, usersConnectionRepository);
    }

    /**
     * Renders the registration page.
     */
    @RequestMapping(value = "/user/register", method = RequestMethod.GET)
    public String showRegistrationForm(WebRequest request, Model model) {
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(request);

        RegistrationForm registration = createRegistrationDTO(connection);

        model.addAttribute(MODEL_NAME_REGISTRATION_DTO, registration);

        return VIEW_NAME_REGISTRATION_PAGE;
    }

    /**
     * Creates the form object used in the registration form.
     * @param connection
     * @return  If a user is signing in by using a social provider, this method returns a form
     *          object populated by the values given by the provider. Otherwise this method returns
     *          an empty form object (normal form registration).
     */
    private RegistrationForm createRegistrationDTO(Connection<?> connection) {
        RegistrationForm dto = new RegistrationForm();

        if (connection != null) {
            UserProfile socialMediaProfile = connection.fetchUserProfile();
            dto.setEmail(socialMediaProfile.getEmail());
            dto.setFirstName(socialMediaProfile.getFirstName());
            dto.setLastName(socialMediaProfile.getLastName());

            ConnectionKey providerKey = connection.getKey();
            dto.setSignInProvider(AuthenticationServiceProvider.valueOf(providerKey.getProviderId().toUpperCase()));
        }

        return dto;
    }

    /**
     * Processes the form submissions of the registration form.
     */
    @RequestMapping(value ="/user/register", method = RequestMethod.POST)
    public String registerUserAccount(@Valid @ModelAttribute("user") RegistrationForm userAccountData,
                                      BindingResult result,
                                      WebRequest request) throws DuplicateEmailException {
        if (result.hasErrors()) {
            return VIEW_NAME_REGISTRATION_PAGE;
        }

        User registered = createUserAccount(userAccountData, result);

        //If email address was already found from the database, render the form view.
        if (registered == null) {
            return VIEW_NAME_REGISTRATION_PAGE;
        }

        //Logs the user in.
        SecurityUtil.logInUser(registered);
        //If the user is signing in by using a social provider, this method call stores
        //the connection to the UserConnection table. Otherwise, this method does not
        //do anything.
        providerSignInUtils.doPostSignUp(registered.getEmail(), request);

        return "redirect:/";
    }

    /**
     * Creates a new user account by calling the service method. If the email address is found
     * from the database, this method adds a field error to the email field of the form object.
     */
    private User createUserAccount(RegistrationForm userAccountData, BindingResult result) {
        User registered = null;

        try {
            registered = service.registerNewUserAccount(userAccountData);
        }
        catch (DuplicateEmailException ex) {
            addFieldError(
                    MODEL_NAME_REGISTRATION_DTO,
                    RegistrationForm.FIELD_NAME_EMAIL,
                    userAccountData.getEmail(),
                    ERROR_CODE_EMAIL_EXIST,
                    result);
        }

        return registered;
    }

    /**
     * Use with a logger.
     */
    private void addFieldError(String objectName, String fieldName, String fieldValue,  String errorCode, BindingResult result) {
//        LOGGER.debug(
//                "Adding field error object's: {} field: {} with error code: {}",
//                objectName,
//                fieldName,
//                errorCode
//        );
//        FieldError error = new FieldError(
//                objectName,
//                fieldName,
//                fieldValue,
//                false,
//                new String[]{errorCode},
//                new Object[]{},
//                errorCode
//        );
//
//        result.addError(error);
//        LOGGER.debug("Added field error: {} to binding result: {}", error, result);
    }

}
