package io.github.parkjoon.SpringSocialDemo.security.util;

import io.github.parkjoon.SpringSocialDemo.security.model.DemoUserDetails;
import io.github.parkjoon.SpringSocialDemo.user.model.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

    /**
     * REFACTOR ME!
     */
    public static void logInUser(User user) {
        DemoUserDetails userDetails = DemoUserDetails.getBuilder()
                .firstName(user.getFirstName())
                .id(user.getId())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .role(user.getRole())
                .socialSignInProvider(user.getSignInProvider())
                .username(user.getEmail())
                .build();

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
