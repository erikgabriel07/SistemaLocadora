package com.locadora.project.dto;

import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.FutureOrPresent;

import java.time.LocalDateTime;

public class LocacaoDTO {
	@FutureOrPresent(message = "A data fornecida é inválida.")
	private LocalDateTime data;
	
	@PositiveOrZero(message = "O valor total é inválido.")
	private Double valor_total;
	
	@PositiveOrZero(message = "O ID do cliente é inválido.")
	private Long clienteId;
	
	@PositiveOrZero(message = "O ID da locação da fita é inválido.")
	private Long fitaLocacaoId;
	
	public LocalDateTime getData() { return data; }
	
	public Double getValorTotal() { return valor_total; }
	
	public Long getClienteId() { return clienteId; }
	
	public Long getFitaLocacaoId() { return fitaLocacaoId; }
	
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	
	public void setValorTotal(Double valor_total) {
		this.valor_total = valor_total;
	}
	
	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}
	
	public void setFitaLocacaoId(Long fitaLocacaoId) {
		this.fitaLocacaoId = fitaLocacaoId;
	}
}