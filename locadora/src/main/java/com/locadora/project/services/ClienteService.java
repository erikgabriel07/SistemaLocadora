package com.locadora.project.services;

import java.util.List;

import com.locadora.project.model.Locacao;
import com.locadora.project.model.Cliente;
import com.locadora.project.dao.ClienteDAO;

import com.locadora.project.util.IOperations;
import com.locadora.project.util.HibernateUtil;

import org.hibernate.SessionFactory;

public class ClienteService extends ClienteDAO implements IOperations<Cliente, Long> {
	public ClienteService() {
		super();
	}
	
	public ClienteService(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	@Override
	public void cadastrar(Cliente cliente) throws Exception {
		try {
			super.save(cliente);
			System.out.println("Cliente cadastrado com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public void atualizar(Cliente cliente) throws Exception {
		try {
			super.update(cliente);
			System.out.println("Cliente atualizado com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public void remover(Cliente cliente) throws Exception {
		try {
			super.remove(cliente);
			System.out.println("Cliente removido com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public Cliente listarId(Long id) throws Exception {
		try {
			Cliente cliente = super.findBy(id);
			if (cliente != null) return cliente;
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public void listar() {
		for (Cliente cliente : super.findAll()) {
			System.out.println(cliente);
		}
	}
	
	public void listarComFiltro(Cliente cliente) throws Exception {
		try {
			List<Cliente> listagem = super.getClienteFiltro(cliente);
			
			if (listagem.size() > 0) {
				for (Cliente i : listagem) {
					System.out.println(i);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public void listarClientePorCPF(String cpf) throws Exception {
		try {
			Cliente cliente = super.getClientePorCPF(cpf);
			
			if (cliente == null) throw new Exception("Cliente não encontrado!");
			
			System.out.println(cliente);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public void listarClienteHistorico(Long id) throws Exception {
		try {
			List<Locacao> historico = super.getClienteHistorico(id);
			
			for (Locacao i : historico) {
				System.out.println(i);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}