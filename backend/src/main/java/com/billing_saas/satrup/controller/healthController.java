package com.billing_saas.satrup.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class healthController {

    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}
