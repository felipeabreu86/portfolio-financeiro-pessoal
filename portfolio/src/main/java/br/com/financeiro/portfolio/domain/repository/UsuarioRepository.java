package br.com.financeiro.portfolio.domain.repository;

import br.com.financeiro.portfolio.domain.entity.Usuario;

public interface UsuarioRepository {

    Usuario obterUsuarioPelo(String nomeUsuario);

}
