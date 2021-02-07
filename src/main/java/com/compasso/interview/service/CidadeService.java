package com.compasso.interview.service;

import java.util.List;

import com.compasso.interview.dto.CidadeRequestDTO;
import com.compasso.interview.dto.CidadeResponseDTO;

public abstract class CidadeService {

	public abstract CidadeResponseDTO salvar(CidadeRequestDTO cidadeRequestDTO);

	public abstract List<CidadeResponseDTO> consultarPeloNome(String nome);

	public abstract List<CidadeResponseDTO> consultarPelaSiglaEstado(String siglaEstado);

}
