package io.github.parkjoon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication
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