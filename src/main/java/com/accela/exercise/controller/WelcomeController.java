package com.accela.exercise.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

	@Value("${welcome.message}")
    private String welcomeMesssage;

    @GetMapping("/")
    public String helloWorld() {
        return welcomeMesssage;
    }
}
