package com.locadora.project.model;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import org.hibernate.annotations.CreationTimestamp;

import com.locadora.project.model.Cliente;
import com.locadora.project.dto.DependenteDTO;

@Entity
@Table(name = "tb_loc_dependente")
public class Dependente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_cod_dependente_id")
	private Long codDependente;
	
	@Column(name = "nome", nullable = false, unique = false, length = 255)
	private String nome;
	
	@Column(name = "idade", nullable = false, unique = false)
	private Integer idade;
	
	@Column(name = "genero", nullable = false, unique = false, length = 255)
	private String genero;
	
	@CreationTimestamp
	@Column(name = "data_cadastro")
	private LocalDate data_cadastro;
	
	@ManyToOne
	@JoinColumn(name = "dependente", nullable = false)
	private Cliente cliente;
	
	public Dependente() {
	}
	
	public Dependente(String nome, Integer idade, String genero, Cliente cliente) {
		this.nome = nome;
		this.idade = idade;
		this.genero = genero;
		this.cliente = cliente;
	}
	
	public Dependente(DependenteDTO dto) {
		this.nome = dto.getNome();
	}

	public Long getCodDependente() {
		return codDependente;
	}
	
	public String getNome() {
		return nome;
	}
	
	public Integer getIdade() {
		return idade;
	}
	
	public String getGenero() {
		return genero;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public void update(DependenteDTO dto) {
		if (dto.getNome() != null && !dto.getNome().trim().isEmpty()) nome = dto.getNome();
	}
	
	@Override
	public String toString() {
		return String.format(
			"\nID: %d\n" +
			"Nome: %s\n" +
			"Idade: %d\n" +
			"Genero: %s\n" +
			"Cadastro: %s\n" +
			"Responsável:" + cliente,
			codDependente, nome, idade,
			genero, data_cadastro
		);
	}
}