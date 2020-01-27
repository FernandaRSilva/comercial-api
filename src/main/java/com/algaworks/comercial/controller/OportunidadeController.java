package com.algaworks.comercial.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.algaworks.comercial.model.Oportunidade;
import com.algaworks.comercial.repository.OportunidadeRepository;
import com.algaworks.comercial.service.OportunidadeService;

@CrossOrigin
@RestController
@RequestMapping("/oportunidades")
public class OportunidadeController {
	
	@Autowired
	private OportunidadeService oportunidadeService;
	
	@GetMapping
	public ResponseEntity<List<Oportunidade>> listar() {
		return ResponseEntity.ok(this.oportunidadeService.listar());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Oportunidade> buscarOportunidade(@PathVariable Long id) {
		if (oportunidadeService.buscarPorId(id) == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(this.oportunidadeService.buscarPorId(id));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Oportunidade> adicionar(@Valid @RequestBody Oportunidade oportunidade) {
		if (oportunidadeService.buscarPorDescricaoENome(oportunidade) != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Já existe uma oportunidade para este prospecto com a mesma descrição");
		}
		return ResponseEntity.ok(this.oportunidadeService.adicionar(oportunidade));	
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletar(@PathVariable Long id) {
		this.oportunidadeService.deletar(id);
		return ResponseEntity.ok("Deletado com sucesso!");
	}
	
	@PutMapping
	public ResponseEntity<Oportunidade> alterar(@Valid @RequestBody Oportunidade oportunidade) {
		return ResponseEntity.ok(this.oportunidadeService.alterar(oportunidade));
	}
	

	
}
