package com.cristovantamayo.osworks.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cristovantamayo.osworks.api.model.ClienteDTO;
import com.cristovantamayo.osworks.api.model.ClienteInput;
import com.cristovantamayo.osworks.domain.model.Cliente;
import com.cristovantamayo.osworks.domain.repository.ClienteRepository;
import com.cristovantamayo.osworks.domain.service.CrudClientService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private CrudClientService service;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public List<ClienteDTO> listar() {
		return toView(repository.findAll());
	}
	
	@GetMapping("/{clienteId}")
	public ResponseEntity<ClienteDTO> buscar(@PathVariable Long clienteId) {
		Optional<Cliente> cliente = repository.findById(clienteId);
		
		return cliente.isPresent()
				? ResponseEntity.ok(toView(cliente.get()))
				: ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@Valid @RequestBody ClienteInput clienteInput) {
		return service.salvar(toEntity(clienteInput));
	}
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<ClienteDTO> atualizar(@PathVariable Long clienteId, @Valid @RequestBody Cliente cliente){
		
		if(!repository.existsById(clienteId))
			return ResponseEntity.notFound().build();
		
		cliente.setId(clienteId);
		return ResponseEntity.ok(toView(service.salvar(cliente)));
	}
	
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> remover(@PathVariable Long clienteId){
		
		if(!repository.existsById(clienteId))
			return ResponseEntity.notFound().build();
		
		service.excluir(clienteId);
		return ResponseEntity.noContent().build();
	}
	
	private ClienteDTO toView(Cliente cliente){
		return modelMapper.map(cliente, ClienteDTO.class);
	}
	
	private List<ClienteDTO> toView(List<Cliente> clientes){
		return clientes.stream()
				.map(cliente -> toView(cliente))
				.collect(Collectors.toList());
	}
	
	private Cliente toEntity(ClienteInput clienteInput) {
		return modelMapper.map(clienteInput, Cliente.class);
	}

}
