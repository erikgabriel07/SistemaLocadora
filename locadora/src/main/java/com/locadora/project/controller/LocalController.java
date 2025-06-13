package com.locadora.project.controller;

import com.locadora.project.model.Local;
import com.locadora.project.dto.LocalDTO;

import com.locadora.project.util.HibernateUtil;
import com.locadora.project.util.ValidatorUtil;
import com.locadora.project.util.ObjectFactory;

import com.locadora.project.services.LocalService;

public class LocalController extends ValidatorUtil<LocalDTO> {
	private LocalDTO prototype = new LocalDTO();
	private ObjectFactory<LocalDTO> objFactory;
	
	private final LocalService localService = new LocalService(HibernateUtil.getSessionFactory());
	
	public LocalController() {
		super();
		objFactory = new ObjectFactory<>(prototype);
	}
	
	public void cadastrarLocal(String nome) {
		LocalDTO dto = objFactory.createInstance();
		
		dto.setNome(nome);
		
		try {
			if (super.validate_field(dto, "nome") != 0) return;
			Local local = new Local(dto);
			localService.cadastrar(local);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void atualizarLocal(Long localId, String nome) {
		try {
			Local local = localService.listarId(localId);
			if (local == null) throw new Exception("Local não encontrado.");
			
			LocalDTO dto = objFactory.createInstance();
		
			dto.setNome(nome);
			
			local.update(dto);
			
			localService.atualizar(local);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void removerLocal(Long localId) {
		try {
			Local local = localService.listarId(localId);
			
			if (local == null) throw new Exception("Local não encontrado.");
			
			localService.remover(local);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Local buscarLocal(Long localId) {
		try {
			return localService.listarId(localId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public void listarLocal() {
		localService.listar();
	}
}