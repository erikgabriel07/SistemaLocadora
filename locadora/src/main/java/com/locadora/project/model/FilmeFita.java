package com.locadora.project.model;

import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import com.locadora.project.model.Fita;
import com.locadora.project.model.Filme;

@Entity
@Table(name = "tb_loc_filme_fita")
public class FilmeFita {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_cod_filme_fita_id")
	private Long codFilmeFita;
	
	@OneToOne
	@JoinColumn(name = "fita_id", nullable = false)
	private Fita fita;
	
	@ManyToOne
	@JoinColumn(name = "filme_id", nullable = false)
	private Filme filme;
	
	public FilmeFita() {
	}
	
	public FilmeFita(Fita fita, Filme filme) {
		this.fita = fita;
		this.filme = filme;
	}
	
	public Long getCodFilmeFita() {
		return codFilmeFita;
	}
	
	public Fita getFita() {
		return fita;
	}
	
	public Filme getFilme() {
		return filme;
	}
	
	public void setCodFilmeFita(Long codFilmeFita) {
		this.codFilmeFita = codFilmeFita;
	}
	
	public void setFita(Fita fita) {
		this.fita = fita;
	}
	
	public void setFilme(Filme filme) {
		this.filme = filme;
	}
	
	@Override
	public String toString() {
		return String.format(
			"\nCOD_FILME_FITA: %d\n" +
			"Fita: %s" +
			"\nFilmes:" + filme,
			codFilmeFita, fita.getNome()
		);
	}
}