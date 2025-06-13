package com.locadora.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

import com.locadora.project.util.Groups.*;

public class FitaDTO {
	@NotBlank(groups = {OnCreate.class, OnUpdate.class}, message = "O nome não pode ser vazio.")
	private String nome;
	
	@PositiveOrZero(groups = {OnCreate.class, OnUpdate.class}, message = "O valor é inválido.")
	private Double valor;
	
	@PositiveOrZero(message = "O ID da fita do filme é inválido.")
	private Long filmeFitaId;
	
	@PositiveOrZero(message = "O ID da locação da fita é inválido.")
	private Long fitaLocacaoId;
	
	public String getNome() { return nome; }
	
	public Double getValor() { return valor; }
	
	public Long getFilmeFitaId() { return filmeFitaId; }
	
	public Long getFitaLocacaoId() { return fitaLocacaoId; }
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public void setFilmeFitaId(Long filmeFitaId) {
		this.filmeFitaId = filmeFitaId;
	}
	
	public void setFitaLocacaoId(Long fitaLocacaoId) {
		this.fitaLocacaoId = fitaLocacaoId;
	}
}