package com.cristovantamayo.osworks.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class OrdemServico {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/* Uma ver mapeado os dados de input em uma entidade de entrega desacoplada, as anotacoes de validacao tornam-se obsoletas, duplicadas.
	@Valid
	@ConvertGroup(from = Default.class, to = ValidationGroups.ClienteId.class)
	@NotNull
	*/
	@ManyToOne
	private Cliente cliente;
	
	// @NotBlank
	private String descricao;
	
	// @NotNull
	private BigDecimal preco;

	//@JsonProperty(access = Access.READ_ONLY)
	@Enumerated(EnumType.STRING)
	private StatusOrdemServico status;

	//@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime dataAbertura;
	
	//@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime dataFinalizacao;
}