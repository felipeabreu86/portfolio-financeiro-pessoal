package br.com.financeiro.portfolio.exterior.controller.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.financeiro.portfolio.core.annotation.FieldMatch;
import br.com.financeiro.portfolio.core.annotation.ValidPassword;

@FieldMatch(first = "senha", second = "confirmacaoSenha", message = "As senhas não coincidem.")
public class AlterarSenhaDto {

    @NotBlank(message = "O e-mail é obrigatório.")
    @Size(max = 50, message = "O e-mail deve conter até 50 dígitos.")
    private String email;

    @NotBlank(message = "Token inválido.")
    private String token;

    @ValidPassword
    private String senha;

    private String confirmacaoSenha;

    public AlterarSenhaDto() {
        super();
    }

    public AlterarSenhaDto(String email, String token) {
        this();
        this.email = email;
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getConfirmacaoSenha() {
        return confirmacaoSenha;
    }

    public void setConfirmacaoSenha(String confirmacaoSenha) {
        this.confirmacaoSenha = confirmacaoSenha;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
