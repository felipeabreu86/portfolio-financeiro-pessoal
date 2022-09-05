package br.com.financeiro.portfolio.exterior.controller.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RecuperarSenhaDto {

    @NotBlank(message = "O e-mail é obrigatório.")
    @Size(max = 50, message = "O e-mail deve conter até 50 dígitos.")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
