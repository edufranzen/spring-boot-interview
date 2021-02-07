package com.compasso.interview.service.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compasso.interview.dto.CidadeRequestDTO;
import com.compasso.interview.dto.CidadeResponseDTO;
import com.compasso.interview.entity.Cidade;
import com.compasso.interview.entity.Estado;
import com.compasso.interview.repository.CidadeRepository;
import com.compasso.interview.repository.EstadoRepository;
import com.compasso.interview.service.CidadeService;

@Service
public class CidadeServiceImpl extends CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private ModelMapper modelMapper;

	public CidadeResponseDTO salvar(CidadeRequestDTO cidadeRequestDTO) {

		Optional<Estado> optionalEstado = estadoRepository.findById(cidadeRequestDTO.getIdEstado());

		if (optionalEstado.isEmpty()) {
			throw new RuntimeException("Estado inválido");
		}

		Cidade cidade = new Cidade();
		cidade.setNome(cidadeRequestDTO.getNome());
		cidade.setEstado(optionalEstado.get());

		return modelMapper.map(cidadeRepository.save(cidade), CidadeResponseDTO.class);
	}

	public List<CidadeResponseDTO> consultarPeloNome(String nome) {
		List<Cidade> listaCidades = cidadeRepository.findByNomeIgnoreCaseContaining(nome);

		return modelMapper.map(listaCidades, new TypeToken<List<CidadeResponseDTO>>() {
		}.getType());
	}

	public List<CidadeResponseDTO> consultarPelaSiglaEstado(String siglaEstado) {

		List<Cidade> listaCidades = cidadeRepository.findByEstadoSiglaIgnoreCase(siglaEstado);

		return modelMapper.map(listaCidades, new TypeToken<List<CidadeResponseDTO>>() {
		}.getType());
	}

}
