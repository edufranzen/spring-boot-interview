package com.compasso.interview.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CidadeResponseDTO {

	private Long id;

	private String nome;
	
	private String siglaEstado;

}
