package com.locadora.project.controller;

import com.locadora.project.model.Filme;
import com.locadora.project.model.Local;
import com.locadora.project.model.FilmeLocal;
import com.locadora.project.dto.FilmeLocalDTO;

import com.locadora.project.util.HibernateUtil;
import com.locadora.project.util.ValidatorUtil;
import com.locadora.project.util.ObjectFactory;

import com.locadora.project.services.FilmeService;
import com.locadora.project.services.LocalService;
import com.locadora.project.services.FilmeLocalService;

public class FilmeLocalController extends ValidatorUtil<FilmeLocalDTO> {
	private FilmeLocalDTO prototype = new FilmeLocalDTO();
	private ObjectFactory<FilmeLocalDTO> objFactory;
	
	private final FilmeService filmeService = new FilmeService(HibernateUtil.getSessionFactory());
	private final LocalService localService = new LocalService(HibernateUtil.getSessionFactory());
	private final FilmeLocalService filmeLocalService = new FilmeLocalService(HibernateUtil.getSessionFactory());
	
	public FilmeLocalController() {
		super();
		objFactory = new ObjectFactory<>(prototype);
	}
	
	public void cadastrarFilmeLocal(Long localId, Long filmeId) {
		FilmeLocalDTO dto = objFactory.createInstance();
		
		dto.setLocalId(localId);
		dto.setFilmeId(filmeId);
		
		try {
			if (super.validate(dto, null) == 0) {
				Filme filme = filmeService.listarId(dto.getFilmeId());
				if (filme == null) throw new Exception("Filme não encontrado.");
				
				Local local = localService.listarId(dto.getLocalId());
				if (local == null) throw new Exception("Local não encontrado.");
				
				FilmeLocal filmeLocal = new FilmeLocal();
				
				filmeLocal.setFilme(filme);
				filme.getFilmeLocal().add(filmeLocal);
				
				filmeLocal.setLocal(local);
				local.getFilmeLocal().add(filmeLocal);
				
				filmeLocalService.cadastrar(filmeLocal);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void atualizarFilmeLocal(Long filmeLocalId, Long localId, Long filmeId) {
		try {
			FilmeLocal filmeLocal = filmeLocalService.listarId(filmeLocalId);
			if (filmeLocal == null) throw new Exception("Registro não encontrado.");
			
			FilmeLocalDTO dto = objFactory.createInstance();
		
			dto.setLocalId(localId);
			dto.setFilmeId(filmeId);
			
			if (super.validate(dto, null) != null) return;
			
			Filme filme = filmeService.listarId(dto.getFilmeId());
			if (filme != null) {
				filmeLocal.setFilme(filme);
				filme.getFilmeLocal().add(filmeLocal);
			}
			
			Local local = localService.listarId(dto.getLocalId());
			if (local != null) {
				filmeLocal.setLocal(local);
				local.getFilmeLocal().add(filmeLocal);
			}
			
			if (filme != null || local != null) filmeLocalService.atualizar(filmeLocal);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void removerFilmeLocal(Long filmeLocalId) {
		try {
			FilmeLocal filmeLocal = filmeLocalService.listarId(filmeLocalId);
			
			if (filmeLocal == null) throw new Exception("Registro não encontrado.");
			
			filmeLocalService.remover(filmeLocal);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public FilmeLocal buscarFilmeLocal(Long filmeLocalId) {
		try {
			return filmeLocalService.listarId(filmeLocalId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public void listarFilmeLocal() {
		filmeLocalService.listar();
	}
}