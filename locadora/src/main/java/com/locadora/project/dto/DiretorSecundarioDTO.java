package com.locadora.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

import com.locadora.project.util.Groups.*;

public class DiretorSecundarioDTO {
	@NotBlank(message = "O nome não deve ser deixado em branco.")
	private String nome;
	
	@NotBlank(message = "O cargo não deve ser deixado em branco.")
	private String cargo;
	
	@PositiveOrZero(groups = OnUpdate.class, message = "O ID do diretor secundário do filme é inválido.")
	private Long filmeDiretorSecundarioId;
	
	public String getNome() { return nome; }
	
	public String getCargo() { return cargo; }
	
	public Long getFilmeDiretorSecundarioId() { return filmeDiretorSecundarioId; }
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	public void setFilmeDiretorSecundarioId(Long filmeDiretorSecundarioId) {
		this.filmeDiretorSecundarioId = filmeDiretorSecundarioId;
	}
}