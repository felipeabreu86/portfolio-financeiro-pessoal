package br.com.financeiro.portfolio.infrastructure.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.financeiro.portfolio.domain.entity.Authorities;

@Repository
public interface AuthoritiesDao extends JpaRepository<Authorities, Long> {

}