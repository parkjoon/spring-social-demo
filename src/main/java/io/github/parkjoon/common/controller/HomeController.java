package io.github.parkjoon.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {




    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showHomePage() {
        return "index";
    }


    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String greet() {
        return "Hello!";
    }


}
