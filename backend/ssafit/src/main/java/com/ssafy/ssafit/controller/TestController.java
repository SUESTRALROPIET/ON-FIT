package com.ssafy.ssafit.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"test"})
@RestController
@RequestMapping("/test/api")
public class TestController {
    @GetMapping("/hello")
    public String Hello(){
        return "hello";
    }
}
