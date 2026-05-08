package com.billing_saas.satrup.controllers;

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
//this is me trying to send a Personal Record on 08/05/2026
