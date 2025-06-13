package com.locadora.project.model;

import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import com.locadora.project.dto.LocalDTO;
import com.locadora.project.model.FilmeLocal;

@Entity
@Table(name = "tb_loc_local")
public class Local {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_cod_local_id")
	private Long codLocal;
	
	@Column(name = "local", nullable = false, unique = true, length = 255)
	private String nome;
	
	@OneToMany(mappedBy = "local", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<FilmeLocal> filmeLocal = new ArrayList<>();
	
	public Local() {
	}
	
	public Local(LocalDTO dto) {
		this.nome = dto.getNome();
	}
	
	public Local(String nome) {
		this.nome = nome;
	}
	
	public Long getCodLocal() {
		return codLocal;
	}
	
	public String getNome() {
		return nome;
	}
	
	public List<FilmeLocal> getFilmeLocal() {
		return filmeLocal;
	}
	
	public void setCodLocal(Long codLocal) {
		this.codLocal = codLocal;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void update(LocalDTO dto) {
		if (dto.getNome() != null && !dto.getNome().trim().isEmpty()) nome = dto.getNome();
	}
	
	@Override
	public String toString() {
		return String.format(
			"\nID: %d\n" +
			"Nome: %s\n" +
			"Filme Local:\n" + filmeLocal,
			codLocal, nome
		);
	}
}