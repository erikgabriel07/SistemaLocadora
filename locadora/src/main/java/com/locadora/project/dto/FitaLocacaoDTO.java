package com.locadora.project.dto;

import jakarta.validation.constraints.PositiveOrZero;

public class FitaLocacaoDTO {
	@PositiveOrZero(message = "O ID da fita é inválido.")
	private Long fitaId;
	
	@PositiveOrZero(message = "O ID da locação é inválido.")
	private Long locacaoId;
	
	public Long getFitaId() { return fitaId; }
	
	public Long getLocacaoId() { return locacaoId; }
	
	public void setFitaId(Long fitaId) {
		this.fitaId = fitaId;
	}
	
	public void setLocacaoId(Long locacaoId) {
		this.locacaoId = locacaoId;
	}
}