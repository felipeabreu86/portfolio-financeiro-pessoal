package br.com.financeiro.portfolio.exterior.controller.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.financeiro.portfolio.core.annotation.FieldMatch;
import br.com.financeiro.portfolio.core.annotation.ValidPassword;
import br.com.financeiro.portfolio.domain.entity.Usuario;

@FieldMatch(first = "senha", second = "confirmacaoSenha", message = "As senhas não coincidem.")
public class UsuarioDto {

    @NotBlank(message = "O e-mail é obrigatório.")
    @Size(max = 50, message="O e-mail deve conter até 50 dígitos.")
    private String nomeUsuario;

    @NotBlank(message = "O nome é obrigatório.")
    @Size(max = 80, message="O nome deve conter até 80 dígitos.")
    private String nome;

    @NotBlank(message = "O sobrenome é obrigatório.")
    @Size(max = 80, message="O sobrenome deve conter até 80 dígitos.")
    private String sobrenome;

    @ValidPassword
    private String senha;

    private String confirmacaoSenha;

    // Getters e Setters

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
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

    // Métodos

    public Usuario toUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNome(this.nome);
        usuario.setNomeUsuario(this.nomeUsuario);
        usuario.setSenha(this.senha);
        usuario.setSobrenome(this.sobrenome);
        usuario.setStatus(true);
        return usuario;
    }

}
