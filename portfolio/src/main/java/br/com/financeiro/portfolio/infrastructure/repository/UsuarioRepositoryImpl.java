package br.com.financeiro.portfolio.infrastructure.repository;

import org.springframework.stereotype.Component;

import br.com.financeiro.portfolio.domain.entity.Usuario;
import br.com.financeiro.portfolio.domain.repository.UsuarioRepository;

@Component
public class UsuarioRepositoryImpl implements UsuarioRepository {

    @Override
    public Usuario obterUsuarioPelo(String nomeUsuario) {
        return null;
    }

}
