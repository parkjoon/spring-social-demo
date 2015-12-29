package io.github.parkjoon.SpringSocialDemo.user.service;

import io.github.parkjoon.SpringSocialDemo.user.model.RegistrationForm;
import io.github.parkjoon.SpringSocialDemo.user.model.User;

public interface UserService {

    /**
     * Creates a new user account to the service.
     * @param userAccountData   The information of the created user account.
     * @return  The information of the created user account.
     */
    public User registerNewUserAccount(RegistrationForm userAccountData) throws DuplicateEmailException;

}
