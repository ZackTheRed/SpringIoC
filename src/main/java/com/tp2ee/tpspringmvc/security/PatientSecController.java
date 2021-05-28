package com.tp2ee.tpspringmvc.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PatientSecController {
    @GetMapping("/unauthorizedAccess")
    public String error() {
        return "unauthorizedAccess";
    }
}
