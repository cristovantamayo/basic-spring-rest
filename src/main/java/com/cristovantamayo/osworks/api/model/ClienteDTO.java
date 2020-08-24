package com.cristovantamayo.osworks.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO {
	private Long id;
	private String nome;
	private String email;
	private String telefone;
}
