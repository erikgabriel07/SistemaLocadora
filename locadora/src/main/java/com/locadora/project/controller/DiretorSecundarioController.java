package com.locadora.project.controller;

import com.locadora.project.model.DiretorSecundario;
import com.locadora.project.dto.DiretorSecundarioDTO;

import com.locadora.project.util.HibernateUtil;
import com.locadora.project.util.ValidatorUtil;
import com.locadora.project.util.ObjectFactory;

import com.locadora.project.services.DiretorSecundarioService;

public class DiretorSecundarioController extends ValidatorUtil<DiretorSecundarioDTO> {
	private DiretorSecundarioDTO prototype = new DiretorSecundarioDTO();
	private ObjectFactory<DiretorSecundarioDTO> objFactory;
	
	private final DiretorSecundarioService diretorSecundarioService = new DiretorSecundarioService(HibernateUtil.getSessionFactory());
	
	public DiretorSecundarioController() {
		super();
		objFactory = new ObjectFactory<>(prototype);
	}
	
	public void cadastrarDiretor(String nome, String cargo) {
		DiretorSecundarioDTO dto = objFactory.createInstance();
		
		dto.setNome(nome);
		dto.setCargo(cargo);
		
		try {
			if (super.validate(dto, null) == 0) {
				DiretorSecundario diretorSecundario = new DiretorSecundario(dto);
				diretorSecundarioService.cadastrar(diretorSecundario);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void atualizarDiretor(Long diretorId, String nome, String cargo) {
		try {
			DiretorSecundario diretor = diretorSecundarioService.listarId(diretorId);
			if (diretor == null) throw new Exception("Diretor não encontrado.");
			
			DiretorSecundarioDTO dto = objFactory.createInstance();
			
			dto.setNome(nome);
			dto.setCargo(cargo);
			
			diretor.update(dto);
			
			diretorSecundarioService.atualizar(diretor);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void removerDiretorSecundario(Long diretorSecundarioId) {
		try {
			DiretorSecundario diretorSec = diretorSecundarioService.listarId(diretorSecundarioId);
			
			if (diretorSec == null) throw new Exception("Diretor não encontrado.");
			
			diretorSecundarioService.remover(diretorSec);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public DiretorSecundario buscarDiretor(Long diretorId) {
		try {
			return diretorSecundarioService.listarId(diretorId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public void listarDiretor() {
		diretorSecundarioService.listar();
	}
}