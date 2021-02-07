package com.compasso.interview.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.compasso.interview.dto.ClienteRequestDTO;
import com.compasso.interview.dto.ClienteResponseDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;

@Api(tags = { "Cliente" })
@RequestMapping("/api/cliente")
public abstract class ClienteController {

	@PostMapping
	public abstract ResponseEntity<ClienteResponseDTO> salvar(
			@ApiParam(value = "clienteRequestDTO", required = true) ClienteRequestDTO clienteRequestDTO);

	@GetMapping(value = "/{id}")
	public abstract ResponseEntity<ClienteResponseDTO> consultarPeloId(
			@ApiParam(value = "id", required = true) Long id);

	@GetMapping(value = "/consultarPeloNome/{nome}")
	public abstract ResponseEntity<List<ClienteResponseDTO>> consultarPeloNome(
			@ApiParam(value = "nome", required = true) String nome);

	@PutMapping(value = "/{id}")
	public abstract ResponseEntity<ClienteResponseDTO> atualizarNome(@ApiParam(value = "id", required = true) Long id,
			@ApiParam(value = "nome", required = true) String nome);

	@DeleteMapping(value = "/{id}")
	public abstract void excluir(@ApiParam(value = "id", required = true) Long id);

}
