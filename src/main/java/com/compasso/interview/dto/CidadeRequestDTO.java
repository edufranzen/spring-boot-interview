package com.compasso.interview.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CidadeRequestDTO {

	@Size(min = 3, max = 50, message = "Nome deve ter o tamanho entre 3 e 50 caracteres")
	@Pattern(regexp = "[a-zA-Z\\u00C0-\\u00FF ]*", message = "Nome deve conter apenas letras")
	@NotBlank(message = "Nome é obrigatório")
	private String nome;

	@NotNull(message = "Estado é obrigatório")
	private Long idEstado;

}
