package io.github.parkjoon.SpringSocialDemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.security.AuthenticationNameUserIdSource;

import javax.sql.DataSource;

@Configuration
@EnableSocial
public class SocialContext implements SocialConfigurer{

    @Autowired
    private DataSource dataSource;

    /**
     * Configures the connection factories for Facebook.
     */
    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer cfConfig, Environment env) {
        cfConfig.addConnectionFactory(new FacebookConnectionFactory(
            env.getProperty("facebook.app.id"),
            env.getProperty("facebook.app.secret")
        ));

        cfConfig.addConnectionFactory(new GoogleConnectionFactory(
                env.getProperty("google.consumerKey"),
                env.getProperty("google.consumerSecret")
        ));
    }

    /**
     * The UserIdSource determines the account ID of the user.
     * The demo application uses the username as the account ID.
     * (Maybe change this?)
     */
    @Override
    public UserIdSource getUserIdSource() {
        return new AuthenticationNameUserIdSource();
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        return new JdbcUsersConnectionRepository(
            dataSource,
            connectionFactoryLocator,
            /**
             * The TextEncryptor encrypts the authorization details of the connection.
             * Here, the authorization details are stored as PLAINTEXT.
             */
            Encryptors.noOpText()
        );
    }

    /**
     * This bean manages the connection flow between
     * the account provider and the example application.
     */
    @Bean
    public ConnectController connectController(ConnectionFactoryLocator connectionFactoryLocator,
                                               ConnectionRepository connectionRepository) {
        return new ConnectController(connectionFactoryLocator, connectionRepository);
    }
}
