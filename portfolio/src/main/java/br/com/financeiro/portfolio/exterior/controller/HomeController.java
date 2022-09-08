package br.com.financeiro.portfolio.exterior.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(value = { "/", "/home" })
    public String paginaHome() {
        return "index";
    }

    @GetMapping(value = "/login")
    public String login(final Principal principal) {
        boolean isUserLoggedIn = principal != null;

        return isUserLoggedIn 
                ? "redirect:/home" 
                : "login";
    }

}
