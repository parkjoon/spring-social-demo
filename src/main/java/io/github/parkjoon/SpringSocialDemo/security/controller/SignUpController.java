package io.github.parkjoon.SpringSocialDemo.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SignUpController {

    /**
     * Redirects request forward to the registration page. This hack is required because
     * there is no way to set the sign in url to the SocialAuthenticationFilter class.
     * Another option is to move registration page to to url '/signup'.
     */
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String redirectRequestToRegistrationPage() {
        return "redirect:/user/register";
    }

}
