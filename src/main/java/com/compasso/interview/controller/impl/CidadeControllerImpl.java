package com.compasso.interview.controller.impl;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.compasso.interview.controller.CidadeController;
import com.compasso.interview.dto.CidadeRequestDTO;
import com.compasso.interview.dto.CidadeResponseDTO;
import com.compasso.interview.service.CidadeService;
import com.sun.istack.NotNull;

@RestController
public class CidadeControllerImpl extends CidadeController {

	@Autowired
	private CidadeService cidadeService;

	@Override
	public ResponseEntity<CidadeResponseDTO> salvar(@Valid @RequestBody CidadeRequestDTO cidadeRequestDTO) {

		return ResponseEntity.status(HttpStatus.CREATED).body(cidadeService.salvar(cidadeRequestDTO));
	}

	@Override
	public ResponseEntity<List<CidadeResponseDTO>> consultarPeloNome(@PathVariable @NotNull @NotEmpty String nome) {

		List<CidadeResponseDTO> listaCidadeDTO = cidadeService.consultarPeloNome(nome);

		return validarListaCidadeVazia(listaCidadeDTO);
	}

	@Override
	public ResponseEntity<List<CidadeResponseDTO>> consultarPelaSiglaEstado(
			@PathVariable @NotNull @NotEmpty String sigla) {

		List<CidadeResponseDTO> listaCidadeDTO = cidadeService.consultarPelaSiglaEstado(sigla);

		return validarListaCidadeVazia(listaCidadeDTO);
	}

	private ResponseEntity<List<CidadeResponseDTO>> validarListaCidadeVazia(List<CidadeResponseDTO> listaCidadeDTO) {

		if (listaCidadeDTO.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(listaCidadeDTO);
	}

}
