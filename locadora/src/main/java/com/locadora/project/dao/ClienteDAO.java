package com.locadora.project.dao;

import java.util.List;
import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaBuilder;

import com.locadora.project.model.Cliente;
import com.locadora.project.model.Locacao;
import com.locadora.project.model.Dependente;
import com.locadora.project.dao.GenericDAO;

import com.locadora.project.model.Locacao;

public class ClienteDAO extends GenericDAO<Cliente, Long> {
	public ClienteDAO() {
		super(Cliente.class);
	}
	
	@Override
	public void remove(Cliente cliente) {
		Transaction tx = null;
		
		try (Session session = super.sessionFactory.openSession()) {
			tx = session.beginTransaction();
			
			Cliente clienteManaged = session.find(Cliente.class, cliente.getCodCliente());
			
			for (Locacao locacao : clienteManaged.getLocacao()) {
				locacao.setCliente(null);
			}
			
			for (Dependente dependente : clienteManaged.getDependente()) {
				dependente.setCliente(null);
			}
			
			clienteManaged.getLocacao().clear();
			clienteManaged.getDependente().clear();
			
			session.remove(clienteManaged);
			
			tx.commit();
		} catch (Exception e) {
			if (tx != null && tx.isActive()) tx.rollback();
			System.err.println("[ClienteDAO:ERR] Erro ao remover a entidade: " + e.getMessage());
			throw e;
		}
	}
	
	public Cliente getClientePorCPF(String cpf) {
		try (Session session = super.sessionFactory.openSession()) {
			return session.createQuery("FROM Cliente c WHERE c.cpf = :cpf", Cliente.class).setParameter("cpf", cpf).uniqueResult();
		}
	}
	
	public List<Locacao> getClienteHistorico(Long id) {
		try (Session session = super.sessionFactory.openSession()) {
			Cliente cliente = session.find(super.getEntityClass(), id);
			return cliente.getLocacao();
		}
	}
	
	public List<Cliente> getClienteFiltro(Cliente filtro) {
		try (Session session = super.sessionFactory.openSession()) {
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Cliente> cq = cb.createQuery(super.getEntityClass());
			Root<Cliente> root = cq.from(super.getEntityClass());
			
			List<Predicate> predicados = new ArrayList<>();
			
			if (filtro.getNome() != null && !filtro.getNome().isBlank()) {
				predicados.add(cb.like(root.get("nome"), "%" + filtro.getNome().toLowerCase() + "%"));
			}
			
			if (filtro.getIdade() != null && filtro.getIdade() > 0) {
				predicados.add(cb.equal(root.get("idade"), filtro.getIdade()));
			}
			
			if (filtro.getTelefone() != null && !filtro.getTelefone().isBlank()) {
				predicados.add(cb.equal(root.get("telefone"), filtro.getTelefone()));
			}
			
			if (filtro.getEmail() != null && !filtro.getEmail().isBlank()) {
				predicados.add(cb.equal(root.get("email"), filtro.getEmail()));
			}
			
			cq.where(predicados.toArray(new Predicate[0]));
			
			Query<Cliente> query = session.createQuery(cq);
			return query.getResultList();
		}
	}
}