package com.compasso.interview.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRequestDTO {

	private Long id;

	@Size(min = 3, max = 100, message = "Nome deve ter o tamanho entre 3 e 50 caracteres")
	@Pattern(regexp = "[A-Za-z ]*", message = "Nome deve conter apenas letras")
	@NotBlank(message = "Nome é obrigatório")
	private String nome;

	@Size(min = 1, max = 1, message = "Sexo deve ser M ou F")
	@Pattern(regexp = "M|F", message = "Sexo deve ser M ou F")
	@NotBlank(message = "Sexo é obrigatório")
	private String sexo;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@NotNull(message = "Data de nascimento é obrigatório")
	private LocalDate dataNascimento;

	@NotNull(message = "Cidade é obrigatório")
	private Long idCidade;

}
