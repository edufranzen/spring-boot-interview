package com.compasso.interview.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome", nullable = false)
	private String nome;

	@NotNull
	@Column(name = "sexo", nullable = false)
	private String sexo;

	@NotNull
	@Column(name = "data_nascimento", nullable = false)
	private LocalDate dataNascimento;

	@NotNull
	@Column(name = "idade", nullable = false)
	private Long idade;

	@NotNull
	@Column(name = "id_cidade", nullable = false, insertable = false, updatable = false)
	private Long idCidade;

	@JoinColumn(name = "id_cidade", referencedColumnName = "id")
	@ManyToOne
	private Cidade cidade;

}
