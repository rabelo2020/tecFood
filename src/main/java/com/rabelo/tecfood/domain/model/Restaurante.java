package com.rabelo.tecfood.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rabelo.tecfood.core.validation.Grups;
import com.rabelo.tecfood.core.validation.Multiplo;
import com.rabelo.tecfood.core.validation.ValorZeroIncluiDescricao;

import lombok.Data;
import lombok.EqualsAndHashCode;

@ValorZeroIncluiDescricao(valorField="taxaFrete", descricaoField="nome", descricaoObrigatoria="Frete Grátis")
@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Restaurante {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	@NotBlank
	@Column(nullable = false)
	private String nome;

	@NotNull
	//@PositiveOrZero//(groups = Grups.CozinhaId.class)
	//@TaxaFrete
	@Multiplo(numero=3)
	@Column(name = "taxa_frete", nullable = false)
	private BigDecimal taxaFrete;

	// @JsonIgnore
	//@JsonIgnoreProperties("hibernateLazyInitializer")
	@NotNull//(groups = Grups.CadastroRestaurante.class)
	@ConvertGroup(from = Default.class, to = Grups.CozinhaId.class)
	@Valid
	@ManyToOne // (fetch = FetchType.LAZY)
	@JoinColumn(name = "cozinha_id", nullable = false)
	private Cozinha cozinha;

	@JsonIgnore
	@Column(nullable = false, columnDefinition = "datetime")
	@CreationTimestamp
	private LocalDateTime dataCadastro;

	@JsonIgnore
	@Column(nullable = false, columnDefinition = "datetime")
	@UpdateTimestamp
	private LocalDateTime dataAtualizacao;

	@JsonIgnore
	@Embedded
	private Endereco endereco;

	@JsonIgnore
	@OneToMany(mappedBy = "restaurante")
	private List<Produto> produtos = new ArrayList<>();

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "restaurante_forma_pagamento", joinColumns = @JoinColumn(name = "restaurante_id"), inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
	private List<FormaPagamento> formaPagamento = new ArrayList<>();

}
