package com.application.javaapplication.Commands

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class hello {
    @RequestMapping("/")
    String home() {
        "Hello World";
    }
}
