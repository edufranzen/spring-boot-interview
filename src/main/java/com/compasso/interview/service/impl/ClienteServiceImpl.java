package com.compasso.interview.service.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compasso.interview.dto.ClienteRequestDTO;
import com.compasso.interview.dto.ClienteResponseDTO;
import com.compasso.interview.entity.Cidade;
import com.compasso.interview.entity.Cliente;
import com.compasso.interview.helper.DataHelper;
import com.compasso.interview.repository.CidadeRepository;
import com.compasso.interview.repository.ClienteRepository;
import com.compasso.interview.service.ClienteService;

@Service
public class ClienteServiceImpl extends ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private ModelMapper modelMapper;

	public ClienteResponseDTO salvar(ClienteRequestDTO clienteRequestDTO) {

		Optional<Cidade> optionalCidade = this.cidadeRepository.findById(clienteRequestDTO.getIdCidade());

		if (optionalCidade.isEmpty()) {
			throw new RuntimeException("Cidade inválida");
		}

		Cliente cliente = modelMapper.map(clienteRequestDTO, Cliente.class);
		cliente.setCidade(optionalCidade.get());
		cliente.setIdade(DataHelper.calcularIdade(clienteRequestDTO.getDataNascimento()));

		return modelMapper.map(clienteRepository.save(cliente), ClienteResponseDTO.class);
	}

	public Optional<ClienteResponseDTO> consultarPeloId(Long id) {
		Optional<Cliente> optionalCliente = clienteRepository.findById(id);

		if (optionalCliente.isEmpty()) {
			return Optional.empty();
		}

		return Optional.of(modelMapper.map(optionalCliente.get(), ClienteResponseDTO.class));
	}

	public List<ClienteResponseDTO> consultarPeloNome(String nome) {
		List<Cliente> listaCliente = clienteRepository.findByNomeIgnoreCaseContaining(nome);

		return modelMapper.map(listaCliente, new TypeToken<List<ClienteResponseDTO>>() {
		}.getType());
	}

	public ClienteResponseDTO atualizarNome(Long id, String nome) {

		Optional<Cliente> optionalCliente = clienteRepository.findById(id);

		if (optionalCliente.isPresent()) {

			Cliente cliente = optionalCliente.get();
			cliente.setNome(nome);

			return modelMapper.map(clienteRepository.save(cliente), ClienteResponseDTO.class);
		}

		return null;
	}

	public void excluir(Long id) {

		clienteRepository.deleteById(id);
	}

}
