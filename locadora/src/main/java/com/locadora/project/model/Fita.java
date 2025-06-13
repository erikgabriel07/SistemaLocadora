package com.locadora.project.model;

import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import com.locadora.project.dto.FitaDTO;

import com.locadora.project.model.FilmeFita;
import com.locadora.project.model.FitaLocacao;

@Entity
@Table(name = "tb_loc_fita")
public class Fita {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_cod_fita_id")
	private Long codFita;
	
	@Column(name = "nome", nullable = false, unique = false, length = 255)
	private String nome;
	
	@Column(name = "valor", nullable = false, unique = false)
	private Double valor;
	
	@OneToMany(mappedBy = "fita", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<FitaLocacao> fitaLocacao = new ArrayList<>();
	
	@OneToOne(mappedBy = "fita", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private FilmeFita filmeFita;
	
	public Fita() {
	}
	
	public Fita(FitaDTO dto) {
		this.nome = dto.getNome();
		this.valor = dto.getValor();
	}
	
	public Fita(String nome, Double valor) {
		this.nome = nome;
		this.valor = valor;
	}
	
	public Long getCodFita() {
		return codFita;
	}
	
	public String getNome() {
		return nome;
	}
	
	public Double getValor() {
		return valor;
	}
	
	public FilmeFita getFilmeFita() {
		return filmeFita;
	}
	
	public List<FitaLocacao> getFitaLocacao() {
		return fitaLocacao;
	}
	
	public void setCodFita(Long codFita) {
		this.codFita = codFita;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public void setFilmeFita(FilmeFita filmeFita) {
		this.filmeFita = filmeFita;
	}
	
	public void update(FitaDTO dto) {
		if (dto.getNome() != null && !dto.getNome().trim().isEmpty()) nome = dto.getNome();
		
		if (dto.getValor() != null && dto.getValor() > 0) valor = dto.getValor();
	}
	
	@Override
	public String toString() {
		return String.format(
			"\nID: %d\n" +
			"Nome: %s\n" +
			"Valor: %.2f\n" +
			"Filme: " + filmeFita.getFilme(),
			codFita, nome, valor
		);
	}
}