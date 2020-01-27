package com.algaworks.comercial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.comercial.model.Oportunidade;
import com.algaworks.comercial.repository.OportunidadeRepository;

@Service
public class OportunidadeService {
	
	@Autowired
	private OportunidadeRepository oportunidadeRepository;
	
	public List<Oportunidade> listar() {
		return oportunidadeRepository.findAll();
	}
	
	public Oportunidade buscarPorId(Long id) {
		Optional<Oportunidade> oportunidade = oportunidadeRepository.findById(id);
		return oportunidade.orElse(null);
	}
	
	public Oportunidade adicionar(Oportunidade oportunidade) {
		return oportunidadeRepository.save(oportunidade);
	}
	
	public void deletar(Long id) {
		oportunidadeRepository.deleteById(id);
	}
	
	public Oportunidade buscarPorDescricaoENome(Oportunidade oportunidade) {
		Optional<Oportunidade> op = oportunidadeRepository.findByDescricaoAndNomeProspecto(oportunidade.getDescricao(), oportunidade.getNomeProspecto());
		return op.orElse(null);
	}
	
	
	public Oportunidade alterar(Oportunidade oportunidade) {
		return oportunidadeRepository.save(oportunidade);
	}
}
