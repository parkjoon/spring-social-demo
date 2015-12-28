package io.github.parkjoon.SpringSocialDemo.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

@SpringBootApplication
@ComponentScan(basePackages = {
        "io.github.parkjoon.SpringSocialDemo.common.controller",
        "io.github.parkjoon.SpringSocialDemo.security.controller",
        "io.github.parkjoon.SpringSocialDemo.user.controller"})
public class Application {

    public static void main(String[] args) {
        boolean listBeans = false;

        ApplicationContext ctx = SpringApplication.run(Application.class, args);

        if(listBeans) {
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            System.out.println("Listing beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }
        }
    }

}