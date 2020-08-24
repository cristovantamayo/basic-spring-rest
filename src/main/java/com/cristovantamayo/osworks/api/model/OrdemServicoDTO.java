package com.cristovantamayo.osworks.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.cristovantamayo.osworks.domain.model.StatusOrdemServico;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrdemServicoDTO {
	private Long id;
	private ClienteResumoModel cliente;
	private String descricao;
	private BigDecimal preco;
	private StatusOrdemServico status;
	private OffsetDateTime dataAbertura;
	private OffsetDateTime dataFinalizacao;
}
