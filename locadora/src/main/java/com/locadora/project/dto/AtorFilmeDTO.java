package com.locadora.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public class AtorFilmeDTO {
	@NotBlank(message = "O papel do ator não pode ser deixado em branco.")
	private String papel;
	
	@PositiveOrZero(message = "O ID do ator é inválido.")
	private Long atorId;
	
	@PositiveOrZero(message = "O ID do filme é inválido.")
	private Long filmeId;
	
	public String getPapel() { return papel; }
	
	public Long getAtorId() { return atorId; }
	
	public Long getFilmeId() { return filmeId; }
	
	public void setPapel(String papel) {
		this.papel = papel;
	}
	
	public void setAtorId(Long atorId) {
		this.atorId = atorId;
	}
	
	public void setFilmeId(Long filmeId) {
		this.filmeId = filmeId;
	}
}