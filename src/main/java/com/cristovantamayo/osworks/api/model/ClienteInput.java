package com.cristovantamayo.osworks.api.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteInput {
	
	@NotNull
	private Long id;
	
	@NotBlank
	@NotEmpty
	@Size(max = 60)
	private String nome;
	
	@NotBlank
	@NotEmpty
	@Email
	@Size(max = 255)
	private String email;
	
	@NotBlank
	@NotEmpty
	@Size(max = 20)
	private String telefone;
	
}
