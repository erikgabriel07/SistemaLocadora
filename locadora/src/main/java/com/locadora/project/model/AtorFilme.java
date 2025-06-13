package com.locadora.project.model;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import com.locadora.project.model.Ator;
import com.locadora.project.model.Filme;

@Entity
@Table(name = "tb_loc_ator_filme")
public class AtorFilme {
	@Id
	@Column(name = "pk_ator_filme_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codAtorFilme;
	
	@Column(name = "papel", nullable = false, unique = false, length = 255)
	private String papel;
	
	@ManyToOne
	@JoinColumn(name = "ator_id", nullable = false)
	private Ator ator;
	
	@ManyToOne
	@JoinColumn(name = "filme_id", nullable = false)
	private Filme filme;
	
	public AtorFilme() {
	}
	
	public AtorFilme(String papel, Ator ator, Filme filme) {
		this.papel = papel;
		this.ator = ator;
		this.filme = filme;
	}
	
	public Long getCodAtorFilme() {
		return codAtorFilme;
	}
	
	public String getPapel() {
		return papel;
	}
	
	public Ator getAtor() {
		return ator;
	}
	
	public Filme getFilme() {
		return filme;
	}
	
	public void setCodAtorFilme(Long codAtorFilme) {
		this.codAtorFilme = codAtorFilme;
	}
	
	public void setPapel(String papel) {
		this.papel = papel;
	}
	
	public void setAtor(Ator ator) {
		this.ator = ator;
	}
	
	public void setFilme(Filme filme) {
		this.filme = filme;
	}
	
	@Override
	public String toString() {
		return String.format(
			"\nAtor: %s\n" +
			"Filme: %s\n" +
			"Papel: %s\n",
			ator.getNome(), filme.getNome(), papel
		);
	}
}