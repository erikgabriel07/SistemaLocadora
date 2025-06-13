package com.locadora.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public class LocalDTO {
	@NotBlank(message = "O nome não deve ser deixado em branco.")
	private String nome;
	
	@PositiveOrZero(message = "O ID do local do filme é inválido.")
	private Long filmeLocalId;
	
	public String getNome() { return nome; }
	
	public Long getFilmeLocalId() { return filmeLocalId; }
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setFilmeLocalId(Long filmeLocalId) {
		this.filmeLocalId = filmeLocalId;
	}
}