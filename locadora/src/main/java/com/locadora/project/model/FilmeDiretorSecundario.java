package com.locadora.project.model;

import java.util.List;
import java.util.ArrayList;

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

import com.locadora.project.model.Filme;
import com.locadora.project.model.DiretorSecundario;

@Entity
@Table(name = "tb_loc_filme_diretor_secundario")
public class FilmeDiretorSecundario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_cod_filme_diretor_secundario_id")
	private Long codFilmeDiretorSecundario;
	
	@ManyToOne
	@JoinColumn(name = "filme_id")
	private Filme filme;
	
	@ManyToOne
	@JoinColumn(name = "diretor_secundario_id")
	private DiretorSecundario diretorSecundario;
	
	public FilmeDiretorSecundario() {
	}
	
	public FilmeDiretorSecundario(Filme filme, DiretorSecundario diretorSecundario) {
		this.filme = filme;
		this.diretorSecundario = diretorSecundario;
	}
	
	public Long getCodFilmeDiretorSecundario() {
		return codFilmeDiretorSecundario;
	}
	
	public Filme getFilme() {
		return filme;
	}
	
	public DiretorSecundario getDiretorSecundario() {
		return diretorSecundario;
	}
	
	public void setCodFilmeDiretorSecundario(Long codFilmeDiretorSecundario) {
		this.codFilmeDiretorSecundario = codFilmeDiretorSecundario;
	}
	
	public void setFilme(Filme filme) {
		this.filme = filme;
	}
	
	public void setDiretorSecundario(DiretorSecundario diretorSecundario) {
		this.diretorSecundario = diretorSecundario;
	}
	
	@Override
	public String toString() {
		return String.format(
			"\nCOD_FILME_DIR_SEC: %d\n" +
			"Diretor:" + diretorSecundario +
			"Filme:" + filme,
			codFilmeDiretorSecundario
		);
	}
}