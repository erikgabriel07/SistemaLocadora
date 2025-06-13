package com.locadora.project.controller;

import java.time.LocalDate;

import com.locadora.project.model.Ator;
import com.locadora.project.dto.AtorDTO;

import com.locadora.project.util.HibernateUtil;
import com.locadora.project.util.ValidatorUtil;
import com.locadora.project.util.ObjectFactory;

import com.locadora.project.services.AtorService;

public class AtorController extends ValidatorUtil<AtorDTO> {
	private AtorDTO prototype = new AtorDTO();
	private ObjectFactory<AtorDTO> objFactory;
	
	private final AtorService atorService = new AtorService(HibernateUtil.getSessionFactory());
	
	public AtorController() {
		super();
		prototype.setAtivo(true);
		objFactory = new ObjectFactory<>(prototype);
	}
	
	public void cadastrarAtor(String nome, LocalDate dataNascimento, String nacionalidade, String genero, String biografia, String tipoCarreira) {
		AtorDTO dto = objFactory.createInstance();
		
		dto.setNome(nome);
		dto.setDataNascimento(dataNascimento);
		dto.setNacionalidade(nacionalidade);
		dto.setGenero(genero);
		dto.setBiografia(biografia);
		dto.setTipoCarreira(tipoCarreira);
		
		try {
			if (super.validate(dto, null) == 0) {
				Ator ator = new Ator(dto);
				atorService.cadastrar(ator);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void atualizarAtor(Long atorId, String nome, LocalDate dataNascimento, String nacionalidade, String genero, String biografia, String tipoCarreira) {
		try {
			Ator ator = atorService.listarId(atorId);
			if (ator == null) {
				System.out.println("Ator não encontrado.");
				return;
			}
			
			AtorDTO dto = objFactory.createInstance();
			
			dto.setNome(nome);
			dto.setDataNascimento(dataNascimento);
			dto.setNacionalidade(nacionalidade);
			dto.setGenero(genero);
			dto.setBiografia(biografia);
			dto.setTipoCarreira(tipoCarreira);
			
			if (super.validate_field(dto, "dataNascimento") != 0) return;
			
			ator.update(dto);
		 
			atorService.atualizar(ator);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void removerAtor(Long atorId) {
		try {
			Ator ator = atorService.listarId(atorId);
			
			if (ator == null) throw new Exception("Ator não enocntrado.");
			
			atorService.remover(ator);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Ator buscarAtor(Long atorId) {
		try {
			return atorService.listarId(atorId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public void listarAtor() {
		atorService.listar();
	}
}