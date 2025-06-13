package com.locadora.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

import com.locadora.project.util.Groups.*;

public class FilmeDTO {
	@NotBlank(groups = {OnCreate.class, OnUpdate.class}, message = "O nome não pode ser deixado em branco.")
	private String nome;
	
	@NotBlank(groups = {OnCreate.class, OnUpdate.class}, message = "O nome do diretor não pode ser deixado em branco.")
	private String diretor;
	
	@NotBlank(groups = {OnCreate.class, OnUpdate.class}, message = "A sinopse não pode ser deixada em branco.")
	private String sinopse;
	
	@PositiveOrZero(groups = {OnCreate.class, OnUpdate.class}, message = "O ano do lançamento é inválido.")
	private Integer anoLancamento;
	
	@NotBlank(groups = {OnCreate.class, OnUpdate.class}, message = "O gênero não pode ser deixado em branco.")
	private String genero;
	
	@PositiveOrZero(groups = {OnCreate.class, OnUpdate.class}, message = "A duração do filme é inválida.")
	private Integer duracao;
	
	@NotBlank(groups = {OnCreate.class, OnUpdate.class}, message = "A classificação não pode ser deixada em branco.")
	private String classificacao;
	
	@NotBlank(groups = {OnCreate.class, OnUpdate.class}, message = "O idioma não pode ser deixado em branco.")
	private String idioma;
	
	@NotBlank(groups = {OnCreate.class, OnUpdate.class}, message = "O status não pode ser deixado em branco.")
	private String status;
	
	@PositiveOrZero(message = "O ID da fita do filme é inválido.")
	private Long filmeFitaId;
	
	@PositiveOrZero(message = "O ID do ator do filme é inválido.")
	private Long atorFilmeId;
	
	@PositiveOrZero(message = "O ID do local do filme é inválido.")
	private Long filmeLocalId;
	
	@PositiveOrZero(message = "O ID do diretor secundário do filme é inválido.")
	private Long filmeDiretorSecundarioId;
	
	public String getNome() { return nome; }
	
	public String getDiretor() { return diretor; }
	
	public String getSinopse() { return sinopse; }
	
	public Integer getAnoLancamento() { return anoLancamento; }
	
	public String getGenero() { return genero; }
	
	public Integer getDuracao() { return duracao; }
	
	public String getClassificacao() { return classificacao; }
	
	public String getIdioma() { return idioma; }
	
	public String getStatus() { return status; }
	
	public Long getFilmeFitaId() { return filmeFitaId; }
	
	public Long getAtorFilmeId() { return atorFilmeId; }
	
	public Long getFilmeLocalId() { return filmeLocalId; }
	
	public Long getFilmeDiretorSecundarioId() { return filmeDiretorSecundarioId; }
	
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
	
	public void setFilmeFitaId(Long filmeFitaId) {
		this.filmeFitaId = filmeFitaId;
	}
	
	public void setAtorFilmeId(Long atorFilmeId) {
		this.atorFilmeId = atorFilmeId;
	}
	
	public void setFilmeLocalId(Long filmeLocalId) {
		this.filmeLocalId = filmeLocalId;
	}
	
	public void setFilmeDiretorSecundarioId(Long filmeDiretorSecundarioId) {
		this.filmeDiretorSecundarioId = filmeDiretorSecundarioId;
	}
}