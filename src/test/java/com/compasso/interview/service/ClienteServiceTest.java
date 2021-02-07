package com.compasso.interview.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.compasso.interview.dto.ClienteRequestDTO;
import com.compasso.interview.dto.ClienteResponseDTO;
import com.compasso.interview.entity.Cidade;
import com.compasso.interview.entity.Cliente;
import com.compasso.interview.entity.Estado;
import com.compasso.interview.repository.CidadeRepository;
import com.compasso.interview.repository.ClienteRepository;
import com.compasso.interview.service.impl.ClienteServiceImpl;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ClienteServiceTest {

	@InjectMocks
	@Spy
	private ClienteServiceImpl clienteService;

	@Spy
	private ModelMapper modelMapper;

	@Mock
	private CidadeRepository cidadeRepository;

	@Mock
	private ClienteRepository clienteRepository;

	private Estado estado;
	private Cidade cidade;
	private Cliente cliente;
	private ClienteRequestDTO clienteRequestDTO;

	@BeforeEach
	public void setUp() throws Exception {

		estado = new Estado();
		estado.setId(1L);
		estado.setSigla("SC");

		cidade = new Cidade();
		cidade.setId(1L);
		cidade.setNome("São Miguel do Oeste");
		cidade.setEstado(estado);
		cidade.setIdEstado(1L);

		cliente = new Cliente();
		cliente.setId(1L);
		cliente.setNome("Eduardo Franzen");
		cliente.setSexo("M");
		cliente.setDataNascimento(LocalDate.of(1996, Month.JUNE, 17));
		cliente.setIdade(24L);
		cliente.setIdCidade(cidade.getId());
		cliente.setCidade(cidade);

		clienteRequestDTO = new ClienteRequestDTO();
		clienteRequestDTO.setNome(cliente.getNome());
		clienteRequestDTO.setSexo(cliente.getSexo());
		clienteRequestDTO.setDataNascimento(cliente.getDataNascimento());
		clienteRequestDTO.setIdCidade(cliente.getIdCidade());
	}

	@Test
	public void salvarSucesso() throws Exception {

		when(cidadeRepository.findById(anyLong())).thenReturn(Optional.of(cidade));
		when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

		ClienteResponseDTO clienteResponseDTO = clienteService.salvar(clienteRequestDTO);

		assertNotNull(clienteResponseDTO);
		assertEquals(clienteResponseDTO.getId(), cliente.getId());
		assertEquals(clienteResponseDTO.getNome(), cliente.getNome());
		assertEquals(clienteResponseDTO.getSexo(), cliente.getSexo());
		assertEquals(clienteResponseDTO.getDataNascimento(), cliente.getDataNascimento());
		assertEquals(clienteResponseDTO.getIdade(), cliente.getIdade());
		assertEquals(clienteResponseDTO.getCidadeNome(), cliente.getCidade().getNome());

		verify(cidadeRepository).findById(anyLong());
		verify(clienteRepository).save(any(Cliente.class));
	}

	@Test
	public void salvarExcecaoEstadoNaoExiste() throws Exception {

		when(cidadeRepository.findById(anyLong())).thenReturn(Optional.empty());

		RuntimeException runtimeException = assertThrows(RuntimeException.class,
				() -> clienteService.salvar(clienteRequestDTO));

		assertEquals(runtimeException.getMessage(), "Cidade inválida");

		verify(cidadeRepository).findById(anyLong());
		verify(clienteRepository, times(0)).save(any(Cliente.class));
	}

	@Test
	public void consultarPeloIdRetornoVazio() throws Exception {

		when(clienteRepository.findById(anyLong())).thenReturn(Optional.empty());

		Optional<ClienteResponseDTO> clienteResponseDTO = clienteService.consultarPeloId(1L);

		assertTrue(clienteResponseDTO.isEmpty());

		verify(clienteRepository).findById(anyLong());
	}

	@Test
	public void consultarPeloIdRetornoSucesso() throws Exception {

		when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(cliente));

		Optional<ClienteResponseDTO> optionalClienteResponseDTO = clienteService.consultarPeloId(1L);

		ClienteResponseDTO clienteResponseDTO = optionalClienteResponseDTO.get();

		assertNotNull(clienteResponseDTO);
		assertEquals(clienteResponseDTO.getId(), cliente.getId());
		assertEquals(clienteResponseDTO.getNome(), cliente.getNome());
		assertEquals(clienteResponseDTO.getSexo(), cliente.getSexo());
		assertEquals(clienteResponseDTO.getDataNascimento(), cliente.getDataNascimento());
		assertEquals(clienteResponseDTO.getIdade(), cliente.getIdade());
		assertEquals(clienteResponseDTO.getCidadeNome(), cliente.getCidade().getNome());

		verify(clienteRepository).findById(anyLong());
	}

	@Test
	public void consultarPeloNomeRetornoVazio() throws Exception {

		when(clienteRepository.findByNomeIgnoreCaseContaining(any())).thenReturn(new ArrayList<>());

		List<ClienteResponseDTO> lista = clienteService.consultarPeloNome("Ed");

		assertEquals(lista.size(), 0);

		verify(clienteRepository).findByNomeIgnoreCaseContaining(any());
	}

	@Test
	public void consultarPeloNomeRetornoComDoisItens() throws Exception {

		Cliente cliente2 = new Cliente();
		cliente2.setId(2L);
		cliente2.setNome("Edmundo X");
		cliente2.setSexo("M");
		cliente2.setDataNascimento(LocalDate.of(1992, Month.JUNE, 30));
		cliente2.setIdade(28L);
		cliente2.setIdCidade(cidade.getId());
		cliente2.setCidade(cidade);

		List<Cliente> listaClientes = new ArrayList<>();
		listaClientes.add(cliente);
		listaClientes.add(cliente2);

		when(clienteRepository.findByNomeIgnoreCaseContaining(any())).thenReturn(listaClientes);

		List<ClienteResponseDTO> lista = clienteService.consultarPeloNome("Ed");

		assertEquals(lista.size(), 2);

		assertEquals(lista.get(0).getId(), cliente.getId());
		assertEquals(lista.get(0).getNome(), cliente.getNome());
		assertEquals(lista.get(0).getSexo(), cliente.getSexo());
		assertEquals(lista.get(0).getDataNascimento(), cliente.getDataNascimento());
		assertEquals(lista.get(0).getIdade(), cliente.getIdade());
		assertEquals(lista.get(0).getCidadeNome(), cliente.getCidade().getNome());

		assertEquals(lista.get(1).getId(), cliente2.getId());
		assertEquals(lista.get(1).getNome(), cliente2.getNome());
		assertEquals(lista.get(1).getSexo(), cliente2.getSexo());
		assertEquals(lista.get(1).getDataNascimento(), cliente2.getDataNascimento());
		assertEquals(lista.get(1).getIdade(), cliente2.getIdade());
		assertEquals(lista.get(1).getCidadeNome(), cliente2.getCidade().getNome());

		verify(clienteRepository).findByNomeIgnoreCaseContaining(any());
	}

	@Test
	public void atualizarNomeClienteExiste() throws Exception {

		Cliente clienteAlterado = cliente;
		clienteAlterado.setNome("Fulano da Silva");

		when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(cliente));
		when(clienteRepository.save(any(Cliente.class))).thenReturn(clienteAlterado);

		ClienteResponseDTO clienteResponseDTO = clienteService.atualizarNome(cliente.getId(),
				clienteAlterado.getNome());

		assertNotNull(clienteResponseDTO);

		assertEquals(clienteResponseDTO.getId(), clienteAlterado.getId());
		assertEquals(clienteResponseDTO.getNome(), clienteAlterado.getNome());
		assertEquals(clienteResponseDTO.getSexo(), clienteAlterado.getSexo());
		assertEquals(clienteResponseDTO.getDataNascimento(), clienteAlterado.getDataNascimento());
		assertEquals(clienteResponseDTO.getIdade(), clienteAlterado.getIdade());
		assertEquals(clienteResponseDTO.getCidadeNome(), clienteAlterado.getCidade().getNome());

		verify(clienteRepository).findById(anyLong());
		verify(clienteRepository).save(any(Cliente.class));
	}

	@Test
	public void atualizarNomeClienteNaoExiste() throws Exception {

		when(clienteRepository.findById(anyLong())).thenReturn(Optional.empty());

		ClienteResponseDTO clienteResponseDTO = clienteService.atualizarNome(cliente.getId(), "Fulano da Silva");

		assertNull(clienteResponseDTO);

		verify(clienteRepository).findById(anyLong());
		verify(clienteRepository, times(0)).save(any(Cliente.class));
	}

	@Test
	public void excluir() throws Exception {

		clienteService.excluir(1L);

		verify(clienteRepository).deleteById(anyLong());
	}

}
