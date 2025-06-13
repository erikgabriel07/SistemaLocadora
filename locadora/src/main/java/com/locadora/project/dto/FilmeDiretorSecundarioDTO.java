package com.locadora.project.dto;

import jakarta.validation.constraints.PositiveOrZero;

public class FilmeDiretorSecundarioDTO {
	@PositiveOrZero(message = "O ID do filme é inválido.")
	private Long filmeId;
	
	@PositiveOrZero(message = "O ID do diretor secundário é inválido.")
	private Long diretorSecundarioId;
	
	public Long getFilmeId() { return filmeId; }
	
	public Long getDiretorSecundarioId() { return diretorSecundarioId; }
	
	public void setFilmeId(Long filmeId) {
		this.filmeId = filmeId;
	}
	
	public void setDiretorSecundarioId(Long diretorSecundarioId) {
		this.diretorSecundarioId = diretorSecundarioId;
	}
}