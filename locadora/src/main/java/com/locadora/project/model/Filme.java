package com.locadora.project.model;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.Year;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import org.hibernate.annotations.CreationTimestamp;

import com.locadora.project.dto.FilmeDTO;

import com.locadora.project.model.AtorFilme;
import com.locadora.project.model.FilmeFita;
import com.locadora.project.model.FilmeLocal;
import com.locadora.project.model.FilmeDiretorSecundario;

@Entity
@Table(name = "tb_loc_filme")
public class Filme {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_cod_filme_id")
	private Long codFilme;
	
	@Column(name = "nome", nullable = false, unique = true, length = 255)
	private String nome;
	
	@Column(name = "diretor", nullable = false, unique = false, length = 150)
	private String diretor;
	
	@Column(name = "sinopse", nullable = false, unique = false, length = 255)
	private String sinopse;
	
	@Column(name = "ano_lancamento", nullable = false, unique = false)
	private Integer anoLancamento;
	
	@Column(name = "genero", nullable = false, unique = false)
	private String genero;
	
	@Column(name = "duracao", nullable = false, unique = false)
	private Integer duracao;
	
	@Column(name = "classificacao", nullable = false, unique = false, length = 150)
	private String classificacao;
	
	@Column(name = "idioma", nullable = false, unique = false, length = 100)
	private String idioma;
	
	@CreationTimestamp
	@Column(name = "data_adicionado")
	private LocalDate dataAdicionado;
	
	@Column(name = "status", nullable = false, unique = false, length = 100)
	private String status;
	
	@OneToMany(mappedBy = "filme", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<FilmeFita> filmeFita;
	
	@OneToMany(mappedBy = "filme", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<AtorFilme> atorFilme = new ArrayList<>();
	
	@OneToMany(mappedBy = "filme", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<FilmeLocal> filmeLocal = new ArrayList<>();
	
	@OneToMany(mappedBy = "filme", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<FilmeDiretorSecundario> filmeDiretorSecundario = new ArrayList<>();
	
	public Filme() {
	}
	
	public Filme(FilmeDTO dto) {
		this.nome = dto.getNome();
		this.diretor = dto.getDiretor();
		this.sinopse = dto.getSinopse();
		this.anoLancamento = dto.getAnoLancamento();
		this.genero = dto.getGenero();
		this.duracao = dto.getDuracao();
		this.classificacao = dto.getClassificacao();
		this.idioma = dto.getIdioma();
		this.status = dto.getStatus();
	}
	
	public Filme(String nome, String diretor, String sinopse, Integer anoLancamento, String genero, Integer duracao, String classificacao,
	String idioma, String status) {
		this.nome = nome;
		this.diretor = diretor;
		this.sinopse = sinopse;
		this.anoLancamento = anoLancamento;
		this.genero = genero;
		this.duracao = duracao;
		this.classificacao = classificacao;
		this.idioma = idioma;
		this.status = status;
	}
	
	public Long getCodFilme() {
		return this.codFilme;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String getDiretor() {
		return this.diretor;
	}
	
	public String getSinopse() {
		return this.sinopse;
	}
	
	public Integer getAnoLancamento() {
		return this.anoLancamento;
	}
	
	public String getGenero() {
		return this.genero;
	}
	
	public Integer getDuracao() {
		return this.duracao;
	}
	
	public String getClassificacao() {
		return this.classificacao;
	}
	
	public String getIdioma() {
		return this.idioma;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public List<FilmeFita> getFilmeFita() {
		return filmeFita;
	}
	
	public List<AtorFilme> getAtorFilme() {
		return atorFilme;
	}
	
	public List<FilmeLocal> getFilmeLocal() {
		return filmeLocal;
	}
	
	public List<FilmeDiretorSecundario> getFilmeDiretorSecundario() {
		return filmeDiretorSecundario;
	}
	
	public void setCodFilme(Long codFilme) {
		this.codFilme = codFilme;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setDiretor(String diretor) {
		this.diretor = diretor;
	}
		
	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}
	
	public void setAnoLancamento(Integer anoLancamento) {
		this.anoLancamento = anoLancamento;
	}
	
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	public void setDuracao(Integer duracao) {
		this.duracao = duracao;
	}
	
	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}
	
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public void update(FilmeDTO dto) {
		if (dto.getNome() != null && !dto.getNome().trim().isEmpty()) nome = dto.getNome();
		
		if (dto.getDiretor() != null && !dto.getDiretor().trim().isEmpty()) diretor = dto.getDiretor();
		
		if (dto.getSinopse() != null && !dto.getSinopse().trim().isEmpty()) sinopse = dto.getSinopse();
		
		if (dto.getAnoLancamento() != null && dto.getAnoLancamento() <= Year.now().getValue()) anoLancamento = dto.getAnoLancamento();
		
		if (dto.getGenero() != null && !dto.getGenero().trim().isEmpty()) genero = dto.getGenero();
		
		if (dto.getDuracao() != null && dto.getDuracao() > 0) duracao = dto.getDuracao();
		
		if (dto.getClassificacao() != null && !dto.getClassificacao().trim().isEmpty()) classificacao = dto.getClassificacao();
		
		if (dto.getIdioma() != null && !dto.getIdioma().trim().isEmpty()) idioma = dto.getIdioma();
		
		if (dto.getStatus() != null && dto.getStatus().trim().isEmpty()) status = dto.getStatus();
	}
	
	@Override
	public String toString() {
		return String.format(
			"\nID     : %d\n" +
			"NOME   : %s\n" +
			"SINOPSE: %s\n" +
			"GÊNERO : %s\n" +
			"ANO	: %d\n" +
			"DURAÇÃO: %d minutos\n" +
			"DIRETOR: %s\n" +
			"CLASSIFICAÇÃO: %s\n" +
			"IDIOMA : %s\n" +
			"ADIÇÃO : %s\n",
			codFilme, nome, sinopse,
			genero, anoLancamento,
			duracao, diretor,
			classificacao, idioma,
			dataAdicionado
		);
	}
}