package com.compasso.interview.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.compasso.interview.dto.CidadeRequestDTO;
import com.compasso.interview.dto.CidadeResponseDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;

@Api(tags = { "Cidade" })
@RequestMapping("/api/cidade")
public abstract class CidadeController {

	@PostMapping
	public abstract ResponseEntity<CidadeResponseDTO> salvar(
			@ApiParam(value = "cidadeRequestDTO", required = true) CidadeRequestDTO cidadeRequestDTO);

	@GetMapping(value = "/consultarPeloNome/{nome}")
	public abstract ResponseEntity<List<CidadeResponseDTO>> consultarPeloNome(
			@ApiParam(value = "nome", required = true) String nome);

	@GetMapping(value = "/consultarPelaSiglaEstado/{sigla}")
	public abstract ResponseEntity<List<CidadeResponseDTO>> consultarPelaSiglaEstado(
			@ApiParam(value = "sigla", required = true) String sigla);

}
