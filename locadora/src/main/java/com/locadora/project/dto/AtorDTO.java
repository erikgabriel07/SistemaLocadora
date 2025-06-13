package com.locadora.project.dto;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;

import com.locadora.project.util.Groups.*;

public class AtorDTO {
	@NotBlank(message = "O nome não deve ser deixado em branco.")
	private String nome;
	
	@Past(message = "A data de nascimento é inválida.")
	private LocalDate dataNascimento;
	
	@NotBlank(message = "A nacionalidade não deve ser deixada em branco.")
	private String nacionalidade;
	
	@NotBlank(message = "O gênero ome não deve ser deixado em branco.")
	private String genero;
	
	@NotBlank(message = "A biografia não deve ser deixado em branco.")
	private String biografia;
	
	@NotBlank(message = "O tipo de carreira não deve ser deixado em branco.")
	private String tipoCarreira;
	
	private Boolean ativo;
	
	@PositiveOrZero(groups = OnUpdate.class, message = "O ID do filme do ator é inválido.")
	private Long atorFilmeId;
	
	public String getNome() { return nome; }
	
	public LocalDate getDataNascimento() { return dataNascimento; }
	
	public String getNacionalidade() { return nacionalidade; }
	
	public String getGenero() { return genero; }
	
	public String getBiografia() { return biografia; }
	
	public String getTipoCarreira() { return tipoCarreira; }
	
	public Boolean getAtivo() { return ativo; }
	
	public Long getCodAtorFilme() { return atorFilmeId; }
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setDataNascimento(LocalDate dataNascimento) {
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
	
	public void setCodAtorFlme(Long atorFilmeId) {
		this.atorFilmeId = atorFilmeId;
	}
}