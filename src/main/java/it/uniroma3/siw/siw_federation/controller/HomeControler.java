package it.uniroma3.siw.siw_federation.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class HomeControler {


    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
