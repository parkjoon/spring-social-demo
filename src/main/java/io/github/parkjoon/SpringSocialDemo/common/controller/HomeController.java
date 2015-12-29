package io.github.parkjoon.SpringSocialDemo.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Properties;

@Controller
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public String showHomePage() {
        return "index";
    }

    // Test mapping
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String greet() {
        return "Hello!";
    }

    // Test mapping
    @RequestMapping(value = "/properties", method = RequestMethod.GET)
    @ResponseBody
    public Properties listProperties() {
        return System.getProperties();
    }

}
