package br.com.financeiro.portfolio.exterior.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {

    @GetMapping(value = "/admin")
    public String paginaDashboardAdmin() {
        return "admin/admin-dashboard";
    }

}
