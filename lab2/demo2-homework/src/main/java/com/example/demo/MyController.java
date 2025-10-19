package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {
    @Value("${app.value}")
    private String message;

    @GetMapping("/hello")
    @ResponseBody
    public String hello(){
        return message;
    }
}