package com.locadora.project.model;

import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import com.locadora.project.model.Local;
import com.locadora.project.model.Filme;

@Entity
@Table(name = "tb_loc_filme_local")
public class FilmeLocal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_cod_filme_local_id")
	private Long codFilmeLocal;
	
	@ManyToOne
	@JoinColumn(name = "filme_id", nullable = false)
	private Filme filme;
	
	@ManyToOne
	@JoinColumn(name = "local_id", nullable = false)
	private Local local;
	
	public FilmeLocal() {
	}
	
	public FilmeLocal(Filme filme, Local local) {
		this.filme = filme;
		this.local = local;
	}
	
	public Long getCodFilmeLocal() {
		return codFilmeLocal;
	}
	
	public Filme getFilme() {
		return filme;
	}
	
	public Local getLocal() {
		return local;
	}
	
	public void setCodFilmeLocal(Long codFilmeLocal) {
		this.codFilmeLocal = codFilmeLocal;
	}
	
	public void setFilme(Filme filme) {
		this.filme = filme;
	}
	
	public void setLocal(Local local) {
		this.local = local;
	}
	
	@Override
	public String toString() {
		return String.format(
			"\nID: %d\n" +
			"Filme: %s\n" +
			"Local: %s\n",
			codFilmeLocal, filme.getNome(), local.getNome()
		);
	}
}