package com.locadora.project.dto;

import jakarta.validation.constraints.PositiveOrZero;

public class FilmeFitaDTO {
	@PositiveOrZero(message = "O ID da fita é inválido.")
	private Long fitaId;
	
	@PositiveOrZero(message = "O ID do filme é inválido.")
	private Long filmeId;
	
	public Long getFitaId() { return fitaId; }
	
	public Long getFilmeId() { return filmeId; }
	
	public void setFitaId(Long fitaId) {
		this.fitaId = fitaId;
	}
	
	public void setFilmeId(Long filmeId) {
		this.filmeId = filmeId;
	}
}