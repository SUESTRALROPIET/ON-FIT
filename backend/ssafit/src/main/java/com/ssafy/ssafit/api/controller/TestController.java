package com.ssafy.ssafit.api.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"test"})
@CrossOrigin(origins = {"*"})
@RestController
public class TestController {
    @GetMapping("/api/hello")
    public String Hello(){
        return "hello";
    }

    @GetMapping("/")
    public String get(){
        return "hello world";
    }
}
