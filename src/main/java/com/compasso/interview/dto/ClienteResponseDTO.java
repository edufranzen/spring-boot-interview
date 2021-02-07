package com.compasso.interview.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponseDTO {

	private Long id;

	private String nome;

	private String sexo;

	private LocalDate dataNascimento;

	private Long idade;

	private String cidadeNome;

}
