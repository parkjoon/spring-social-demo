package io.github.parkjoon.SpringSocialDemo;

import io.github.parkjoon.SpringSocialDemo.config.PersistenceContext;
import io.github.parkjoon.SpringSocialDemo.config.SecurityContext;
import io.github.parkjoon.SpringSocialDemo.config.SocialContext;
import io.github.parkjoon.SpringSocialDemo.config.WebAppContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Arrays;

@SpringBootApplication

@ComponentScan(basePackages = {
        "io.github.parkjoon.SpringSocialDemo.user.service"})
@Import({ WebAppContext.class, PersistenceContext.class, /*SecurityContext.class,*/ SocialContext.class })
@PropertySource("classpath:application.properties")
public class Application {

    // Refactor to external variable.
    private static final String MESSAGE_SOURCE_BASE_NAME = "i18n/messages";

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
         // listBeansCreated(ctx);
    }

    public static void listBeansCreated(ApplicationContext ctx) {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Listing beans provided by Spring Boot:");

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename(MESSAGE_SOURCE_BASE_NAME);
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setUseCodeAsDefaultMessage(true);

        return messageSource;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}