package com.locadora.project.model;

import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import com.locadora.project.model.Fita;
import com.locadora.project.model.Locacao;

@Entity
@Table(name = "tb_loc_fita_locacao")
public class FitaLocacao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_cod_fita_locacao_id")
	private Long codFitaLocacao;
	
	@OneToMany(mappedBy = "fitaLocacao", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<Locacao> locacao = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "fita_id", nullable = false)
	private Fita fita;
	
	public FitaLocacao() {
	}
	
	public FitaLocacao(Fita fita) {
		this.fita = fita;
	}
	
	public Long getCodFitaLocacao() {
		return codFitaLocacao;
	}
	
	public List<Locacao> getLocacao() {
		return locacao;
	}
	
	public Fita getFita() {
		return fita;
	}
	
	public void setCodFitaLocacao(Long codFitaLocacao) {
		this.codFitaLocacao = codFitaLocacao;
	}
	
	public void setFita(Fita fita) {
		this.fita = fita;
	}
	
	@Override
	public String toString() {
		return String.format(
			"\nID: %d\n" +
			"Fita: %s\n" +
			"Qtd. Locacoes:" + locacao,
			codFitaLocacao,
			fita.getNome()
		);
	}
}