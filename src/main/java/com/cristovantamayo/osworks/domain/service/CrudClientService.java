package com.cristovantamayo.osworks.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cristovantamayo.osworks.domain.exception.BusinessException;
import com.cristovantamayo.osworks.domain.model.Cliente;
import com.cristovantamayo.osworks.domain.repository.ClienteRepository;

@Service
public class CrudClientService {
	
	@Autowired
	private ClienteRepository repository;
	
	public Cliente salvar(Cliente cliente) {
		
		Cliente clienteExistente = repository.findByEmail(cliente.getEmail());
		
		if(clienteExistente != null && clienteExistente.getEmail().equals(cliente.getEmail()))
			throw new BusinessException("Já existe um cliente cadastrado com este e-mail.");
		
		return repository.save(cliente);
	}
	
	public void excluir(Long clienteId) {
		repository.deleteById(clienteId);
	}

}
