package br.com.financeiro.portfolio.infrastructure.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.financeiro.portfolio.domain.entity.Ativo;

@Repository
public interface AtivoDao extends JpaRepository<Ativo, Long> {

}