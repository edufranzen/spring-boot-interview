package com.compasso.interview.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compasso.interview.entity.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

	public abstract List<Cidade> findByNomeIgnoreCaseContaining(String nome);

	public abstract List<Cidade> findByEstadoSiglaIgnoreCase(String sigla);

}
