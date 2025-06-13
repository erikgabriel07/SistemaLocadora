package com.locadora.project.util;

public interface IOperations<T, ID> {
	public void cadastrar(T entity) throws Exception;
	public void remover(T entity) throws Exception;
	public void atualizar(T entity) throws Exception;
	public T listarId(ID id) throws Exception;
	public void listar();
}