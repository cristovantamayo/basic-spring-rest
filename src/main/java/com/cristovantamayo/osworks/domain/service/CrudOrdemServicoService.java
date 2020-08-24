package com.cristovantamayo.osworks.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cristovantamayo.osworks.domain.exception.BusinessException;
import com.cristovantamayo.osworks.domain.model.Cliente;
import com.cristovantamayo.osworks.domain.model.OrdemServico;
import com.cristovantamayo.osworks.domain.model.StatusOrdemServico;
import com.cristovantamayo.osworks.domain.repository.ClienteRepository;
import com.cristovantamayo.osworks.domain.repository.OrdemServicoRepository;

@Service
public class CrudOrdemServicoService {

	@Autowired
	private OrdemServicoRepository repository;
	
	@Autowired
	private ClienteRepository clientRepository;
	
	public OrdemServico criar(OrdemServico ordemServico) {
		Cliente cliente = clientRepository.findById(ordemServico.getCliente().getId())
				.orElseThrow(() -> new BusinessException("Cliente não existe"));
		
		ordemServico.setCliente(cliente);
		ordemServico.setStatus(StatusOrdemServico.ABERTA);
		ordemServico.setDataAbertura(OffsetDateTime.now());
		
		return repository.save(ordemServico);
	}
	
}
