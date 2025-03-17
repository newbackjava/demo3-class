package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class S3Controller {

    @GetMapping("s3")
    public String s3(){
        return "s3";
    }
}
