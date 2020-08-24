package com.cristovantamayo.osworks.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cristovantamayo.osworks.api.model.OrdemServicoDTO;
import com.cristovantamayo.osworks.api.model.OrdemServicoInput;
import com.cristovantamayo.osworks.domain.model.OrdemServico;
import com.cristovantamayo.osworks.domain.repository.OrdemServicoRepository;
import com.cristovantamayo.osworks.domain.service.CrudOrdemServicoService;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {
	
	@Autowired
	private CrudOrdemServicoService service;
	
	@Autowired
	private OrdemServicoRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OrdemServicoDTO criar(@Valid @RequestBody OrdemServicoInput ordemServicoInput) {
		return toView(service.criar(toEntity(ordemServicoInput)));
	}
	
	@GetMapping
	public List<OrdemServicoDTO> listar(){
		return toView(repository.findAll());
	}
	
	@GetMapping("/{ordemServicoId}")
	public ResponseEntity<OrdemServicoDTO> buscar(@PathVariable Long ordemServicoId){
		Optional<OrdemServico> ordemServico = repository.findById(ordemServicoId);
		
		if(ordemServico.isPresent())
			return ResponseEntity.ok(toView(ordemServico.get()));
		
		return ResponseEntity.notFound().build();
	}
	
	private OrdemServicoDTO toView(OrdemServico ordemServico){
		return modelMapper.map(ordemServico, OrdemServicoDTO.class);
	}
	
	private List<OrdemServicoDTO> toView(List<OrdemServico> ordensServico){
		return ordensServico.stream()
				.map(ordemServico -> toView(ordemServico))
				.collect(Collectors.toList());
	}
	
	private OrdemServico toEntity(OrdemServicoInput ordemServicoInput) {
		return modelMapper.map(ordemServicoInput, OrdemServico.class);
	}

}
