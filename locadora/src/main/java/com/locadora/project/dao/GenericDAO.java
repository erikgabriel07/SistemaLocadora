package com.locadora.project.dao;

import java.util.List;
import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.locadora.project.util.HibernateUtil;

public abstract class GenericDAO<T, ID extends Serializable> {
	private final Class<T> entityClass;
	protected SessionFactory sessionFactory;
	
	protected Class<T> getEntityClass() {
		return entityClass;
	}
	
	public GenericDAO(Class<T> entityClass) {
		this.entityClass = entityClass;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public void save(T entity) {
		Transaction tx = null;
		
		try (Session session = sessionFactory.openSession()) {
			tx = session.beginTransaction();
			session.persist(entity);
			tx.commit();
		} catch (Exception e) {
			if (tx != null && tx.isActive()) tx.rollback();
			System.err.println("Erro ao salvar a entidade: " + e.getMessage());
			throw e;
		}
	}
	
	public void update(T entity) {
		Transaction tx = null;
		
		try (Session session = sessionFactory.openSession()) {
			tx = session.beginTransaction();
			session.merge(entity);
			tx.commit();
		} catch (Exception e) {
			if (tx != null && tx.isActive()) tx.rollback();
			System.err.println("Erro ao atualizar a entidade: " + e.getMessage());
			throw e;
		}
	}
	
	public void remove(T entity) {
		Transaction tx = null;
		
		try (Session session = sessionFactory.openSession()) {
			tx = session.beginTransaction();
			session.remove(session.contains(entity) ? entity : session.merge(entity));
			tx.commit();
		} catch (Exception e) {
			if (tx != null && tx.isActive()) tx.rollback();
			System.err.println("Erro ao remover a entidade: " + e.getMessage());
			throw e;
		}
	}
	
	public T findBy(ID id) {
		try (Session session = sessionFactory.openSession()) {
			return session.find(entityClass, id);
		}
	}
	
	public List<T> findAll() {
		try (Session session = sessionFactory.openSession()) {
			String query = "FROM " + entityClass.getSimpleName();
			Query<T> queryProcessing = session.createQuery(query, entityClass);
			return queryProcessing.getResultList();
		}
	}
}