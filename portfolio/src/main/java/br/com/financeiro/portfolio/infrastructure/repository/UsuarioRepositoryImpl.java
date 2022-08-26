package br.com.financeiro.portfolio.infrastructure.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.financeiro.portfolio.domain.entity.Usuario;
import br.com.financeiro.portfolio.domain.repository.UsuarioRepository;
import br.com.financeiro.portfolio.infrastructure.dao.UsuarioDao;

@Component
public class UsuarioRepositoryImpl implements UsuarioRepository {

    @Autowired
    private UsuarioDao usuarioDao;

    @Override
    public Usuario obterUsuarioPelo(String nomeUsuario) {
        return usuarioDao.findByNomeUsuario(nomeUsuario);
    }

}
