package com.locadora.project.model;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import com.locadora.project.dto.AtorDTO;
import com.locadora.project.model.AtorFilme;

@Entity
@Table(name = "tb_loc_ator")
public class Ator {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_ator_id")
	private Long codAtor;
	
	@Column(name = "nome", nullable = false, unique = false, length = 255)
	private String nome;
	
	@Column(name = "data_nascimento", nullable = false, unique = false)
	private LocalDate dataNascimento;
	
	@Column(name = "nacionalidade", nullable = false, unique = false, length = 100)
	private String nacionalidade;
	
	@Column(name = "genero", nullable = false, unique = false, length = 50)
	private String genero;
	
	@Column(name = "biografia", nullable = false, unique = false, length = 255)
	private String biografia;
	
	@Column(name = "tipo_carreira", nullable = false, unique = false, length = 255)
	private String tipoCarreira;
	
	@Column(name = "ativo", nullable = false, unique = false)
	private Boolean ativo;
	
	@OneToMany(mappedBy = "ator", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<AtorFilme> atorFilme = new ArrayList<>();
	
	public Ator() {
	}
	
	public Ator(AtorDTO dto) {
		this.nome = dto.getNome();
		this.dataNascimento = dto.getDataNascimento();
		this.nacionalidade = dto.getNacionalidade();
		this.genero = dto.getGenero();
		this.biografia = dto.getBiografia();
		this.tipoCarreira = dto.getTipoCarreira();
		this.ativo = dto.getAtivo();
	}
	
	public Ator(String nome, LocalDate dataNascimento, String nacionalidade, String genero, String biografia, String tipoCarreira) {
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.nacionalidade = nacionalidade;
		this.genero = genero;
		this.biografia = biografia;
		this.tipoCarreira = tipoCarreira;
		this.ativo = true;
	}
	
	public Long getCodAtor() {
		return this.codAtor;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public LocalDate getNascimento() {
		return this.dataNascimento;
	}
	
	public String getNacionalidade() {
		return this.nacionalidade;
	}
	
	public String getGenero() {
		return this.genero;
	}
	
	public String getBiografia() {
		return this.biografia;
	}
	
	public String getTipoCarreira() {
		return this.tipoCarreira;
	}
	
	public Boolean getAtivo() {
		return this.ativo;
	}
	
	public List<AtorFilme> getAtorFilme() {
		return atorFilme;
	}
	
	public void setCodAtor(Long codAtor) {
		this.codAtor = codAtor;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}
	
	public void setTipoCarreira(String tipoCarreira) {
		this.tipoCarreira = tipoCarreira;
	}
	
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	public void update(AtorDTO dto) {
		if (dto.getNome() != null && !dto.getNome().trim().isEmpty()) nome = dto.getNome();
		
		if (dto.getDataNascimento() != null) dataNascimento = dto.getDataNascimento();
		
		if (dto.getNacionalidade() != null && !dto.getNacionalidade().trim().isEmpty()) nacionalidade = dto.getNacionalidade();
		
		if (dto.getGenero() != null && !dto.getGenero().trim().isEmpty()) genero = dto.getGenero();
		
		if (dto.getBiografia() != null && !dto.getBiografia().trim().isEmpty()) biografia = dto.getBiografia();
		
		if (dto.getTipoCarreira() != null && !dto.getTipoCarreira().trim().isEmpty()) tipoCarreira = dto.getTipoCarreira();
		
		ativo = dto.getAtivo();
	} 
	
	@Override
	public String toString() {
		return String.format(
			"\nNome: %s\n" +
			"Gênero: %s\n" +
			"Nascimento: %s\n" +
			"Nacionalidade: %s\n" +
			"Tipo de carreira: %s\n" +
			"Biografria: %s\n" +
			"Status: %s\n",
			nome, genero, dataNascimento,
			nacionalidade, tipoCarreira,
			biografia, ativo ? "Ativo" : "Aposentado"
		);
	}
}