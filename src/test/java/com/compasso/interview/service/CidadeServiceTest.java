package com.compasso.interview.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

import com.compasso.interview.dto.CidadeRequestDTO;
import com.compasso.interview.dto.CidadeResponseDTO;
import com.compasso.interview.entity.Cidade;
import com.compasso.interview.entity.Estado;
import com.compasso.interview.repository.CidadeRepository;
import com.compasso.interview.repository.EstadoRepository;
import com.compasso.interview.service.impl.CidadeServiceImpl;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CidadeServiceTest {

	@InjectMocks
	@Spy
	private CidadeServiceImpl cidadeService;

	@Spy
	private ModelMapper modelMapper;

	@Mock
	private CidadeRepository cidadeRepository;

	@Mock
	private EstadoRepository estadoRepository;

	private Estado estado;
	private Cidade cidade;
	private CidadeRequestDTO cidadeRequestDTO;

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

		cidadeRequestDTO = new CidadeRequestDTO();
		cidadeRequestDTO.setIdEstado(cidade.getIdEstado());
		cidadeRequestDTO.setNome(cidade.getNome());
	}

	@Test
	public void salvarSucesso() throws Exception {

		when(estadoRepository.findById(anyLong())).thenReturn(Optional.of(estado));
		when(cidadeRepository.save(any())).thenReturn(cidade);

		CidadeResponseDTO cidadeResponseDTO = cidadeService.salvar(cidadeRequestDTO);

		assertNotNull(cidadeResponseDTO);
		assertEquals(cidadeResponseDTO.getId(), cidade.getId());
		assertEquals(cidadeResponseDTO.getNome(), cidade.getNome());
		assertEquals(cidadeResponseDTO.getSiglaEstado(), estado.getSigla());

		verify(estadoRepository).findById(anyLong());
		verify(cidadeRepository).save(any(Cidade.class));
	}

	@Test
	public void salvarExcecaoEstadoNaoExiste() throws Exception {

		when(estadoRepository.findById(anyLong())).thenReturn(Optional.empty());

		RuntimeException runtimeException = assertThrows(RuntimeException.class,
				() -> cidadeService.salvar(cidadeRequestDTO));

		assertEquals(runtimeException.getMessage(), "Estado inválido");

		verify(estadoRepository).findById(anyLong());
		verify(cidadeRepository, times(0)).save(any(Cidade.class));
	}

	@Test
	public void consultarPeloNomeRetornoVazio() throws Exception {

		when(cidadeRepository.findByNomeIgnoreCaseContaining(any())).thenReturn(new ArrayList<>());

		List<CidadeResponseDTO> lista = cidadeService.consultarPeloNome("das");

		assertEquals(lista.size(), 0);

		verify(cidadeRepository).findByNomeIgnoreCaseContaining(any());
	}

	@Test
	public void consultarPeloNomeRetornoComDoisItens() throws Exception {

		Cidade cidade2 = new Cidade();
		cidade2.setId(2L);
		cidade2.setNome("Chapecó");
		cidade2.setEstado(estado);
		cidade2.setIdEstado(1L);

		List<Cidade> listaCidades = new ArrayList<>();
		listaCidades.add(cidade);
		listaCidades.add(cidade2);

		when(cidadeRepository.findByNomeIgnoreCaseContaining(any())).thenReturn(listaCidades);

		List<CidadeResponseDTO> lista = cidadeService.consultarPeloNome("a");

		assertEquals(lista.size(), 2);

		assertEquals(lista.get(0).getId(), cidade.getId());
		assertEquals(lista.get(0).getNome(), cidade.getNome());
		assertEquals(lista.get(0).getSiglaEstado(), cidade.getEstado().getSigla());

		assertEquals(lista.get(1).getId(), cidade2.getId());
		assertEquals(lista.get(1).getNome(), cidade2.getNome());
		assertEquals(lista.get(1).getSiglaEstado(), cidade2.getEstado().getSigla());

		verify(cidadeRepository).findByNomeIgnoreCaseContaining(any());
	}

	@Test
	public void consultarPelaSiglaEstadoRetornoVazio() throws Exception {

		when(cidadeRepository.findByEstadoSiglaIgnoreCase(any())).thenReturn(new ArrayList<>());

		List<CidadeResponseDTO> lista = cidadeService.consultarPelaSiglaEstado("SC");

		assertEquals(lista.size(), 0);

		verify(cidadeRepository).findByEstadoSiglaIgnoreCase(any());
	}

	@Test
	public void consultarPelaSiglaEstadoRetornoComDoisItens() throws Exception {

		Cidade cidade2 = new Cidade();
		cidade2.setId(2L);
		cidade2.setNome("Chapecó");
		cidade2.setEstado(estado);
		cidade2.setIdEstado(1L);

		List<Cidade> listaCidades = new ArrayList<>();
		listaCidades.add(cidade);
		listaCidades.add(cidade2);

		when(cidadeRepository.findByEstadoSiglaIgnoreCase(any())).thenReturn(listaCidades);

		List<CidadeResponseDTO> lista = cidadeService.consultarPelaSiglaEstado("SC");

		assertEquals(lista.size(), 2);

		assertEquals(lista.get(0).getId(), cidade.getId());
		assertEquals(lista.get(0).getNome(), cidade.getNome());
		assertEquals(lista.get(0).getSiglaEstado(), cidade.getEstado().getSigla());

		assertEquals(lista.get(1).getId(), cidade2.getId());
		assertEquals(lista.get(1).getNome(), cidade2.getNome());
		assertEquals(lista.get(1).getSiglaEstado(), cidade2.getEstado().getSigla());

		verify(cidadeRepository).findByEstadoSiglaIgnoreCase(any());
	}

}
