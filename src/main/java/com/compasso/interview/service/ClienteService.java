package com.compasso.interview.service;

import java.util.List;
import java.util.Optional;

import com.compasso.interview.dto.ClienteRequestDTO;
import com.compasso.interview.dto.ClienteResponseDTO;

public abstract class ClienteService {

	public abstract ClienteResponseDTO salvar(ClienteRequestDTO clienteRequestDTO);

	public abstract Optional<ClienteResponseDTO> consultarPeloId(Long id);

	public abstract List<ClienteResponseDTO> consultarPeloNome(String nome);

	public abstract ClienteResponseDTO atualizarNome(Long id, String nome);

	public abstract void excluir(Long id);

}
