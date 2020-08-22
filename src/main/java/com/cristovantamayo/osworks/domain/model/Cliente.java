package com.cristovantamayo.osworks.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter
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
