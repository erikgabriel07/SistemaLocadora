package com.locadora.project.dto;

import jakarta.validation.constraints.PositiveOrZero;

public class FilmeLocalDTO {
	@PositiveOrZero(message = "O ID do local é inválido.")
	private Long localId;
	
	@PositiveOrZero(message = "O ID do filme é inválido.")
	private Long filmeId;
	
	public Long getLocalId() { return localId; }
	
	public Long getFilmeId() { return filmeId; }
	
	public void setLocalId(Long localId) {
		this.localId = localId;
	}
	
	public void setFilmeId(Long filmeId) {
		this.filmeId = filmeId;
	}
}