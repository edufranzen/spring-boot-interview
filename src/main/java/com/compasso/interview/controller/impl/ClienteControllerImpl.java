package com.compasso.interview.controller.impl;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.compasso.interview.controller.ClienteController;
import com.compasso.interview.dto.ClienteRequestDTO;
import com.compasso.interview.dto.ClienteResponseDTO;
import com.compasso.interview.service.ClienteService;
import com.sun.istack.NotNull;

@RestController
public class ClienteControllerImpl extends ClienteController {

	@Autowired
	private ClienteService clienteService;

	@Override
	public ResponseEntity<ClienteResponseDTO> salvar(@Valid @RequestBody ClienteRequestDTO clienteRequestDTO) {

		return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.salvar(clienteRequestDTO));
	}

	@Override
	public ResponseEntity<ClienteResponseDTO> consultarPeloId(@PathVariable @NotNull @NotEmpty Long id) {

		Optional<ClienteResponseDTO> optionalClienteResponseDTO = clienteService.consultarPeloId(id);

		return ResponseEntity.ok(optionalClienteResponseDTO.get());
	}

	@Override
	public ResponseEntity<List<ClienteResponseDTO>> consultarPeloNome(@PathVariable @NotNull @NotEmpty String nome) {

		List<ClienteResponseDTO> listaClienteResponseDTO = clienteService.consultarPeloNome(nome);

		if (listaClienteResponseDTO.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(listaClienteResponseDTO);
	}

	@Override
	public ResponseEntity<ClienteResponseDTO> atualizarNome(@PathVariable @NotNull @NotEmpty Long id,
			@RequestParam(value = "nome") @NotNull @NotEmpty String nome) {

		return ResponseEntity.ok(clienteService.atualizarNome(id, nome));
	}

	@Override
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable @NotNull @NotEmpty Long id) {

		clienteService.excluir(id);
	}

}
