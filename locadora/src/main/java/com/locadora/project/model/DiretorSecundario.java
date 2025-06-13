package com.locadora.project.model;

import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import com.locadora.project.dto.DiretorSecundarioDTO;
import com.locadora.project.model.FilmeDiretorSecundario;

@Entity
@Table(name = "tb_loc_diretor_secundario")
public class DiretorSecundario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_cod_diretor_secundario_id")
	private Long codDiretorSecundario;
	
	@Column(name = "nome", nullable = false, unique = false, length = 150)
	private String nome;
	
	@Column(name = "cargo", nullable = false, unique = false, length = 150)
	private String cargo;
	
	@OneToMany(mappedBy = "diretorSecundario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<FilmeDiretorSecundario> filmeDiretorSecundario = new ArrayList<>();
	
	public DiretorSecundario() {
	}
	
	public DiretorSecundario(DiretorSecundarioDTO dto) {
		this.nome = dto.getNome();
		this.cargo = dto.getCargo();
	}
	
	public DiretorSecundario(String nome, String cargo) {
		this.nome = nome;
		this.cargo = cargo;
	}
	
	public Long getCodDiretorSecundario() {
		return codDiretorSecundario;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getCargo() {
		return cargo;
	}
	
	public List<FilmeDiretorSecundario> getFilmeDiretorSecundario() {
		return filmeDiretorSecundario;
	}
	
	public void setCodDiretorSecundario(Long codDiretorSecundario) {
		this.codDiretorSecundario = codDiretorSecundario;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	public void update(DiretorSecundarioDTO dto) {
		if (dto.getNome() != null && !dto.getNome().trim().isEmpty()) nome = dto.getNome();
		
		if (dto.getCargo() != null && !dto.getCargo().trim().isEmpty()) cargo = dto.getCargo();
	}
	
	@Override
	public String toString() {
		return String.format(
			"\nID: %d\n" +
			"Nome: %s\n" +
			"Cargo: %s\n",
			codDiretorSecundario,
			nome, cargo
		);
	}
}