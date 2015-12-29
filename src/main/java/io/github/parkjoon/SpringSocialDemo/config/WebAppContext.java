package io.github.parkjoon.SpringSocialDemo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = {
        "io.github.parkjoon.SpringSocialDemo.**.controller"})
@EnableWebMvc
public class WebAppContext {

}