package com.locadora.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import com.locadora.project.util.Groups.*;

public class DependenteDTO {
	@NotBlank(groups = OnCreate.class, message = "O nome não pode ser deixado vazio.")
	private String nome;
	
	@Positive(groups = OnCreate.class, message = "A idade é inválida")
	private Integer idade;
	
	@NotBlank(groups = OnCreate.class, message = "O gênero não pode ser deixado em branco.")
	private String genero;
	
	@PositiveOrZero(groups = OnCreate.class, message = "O ID do cliente é inválido.")
	private Long clienteId;
	
	public String getNome() { return nome; }
	
	public Integer getIdade() { return idade; }
	
	public String getGenero() { return genero; }
	
	public Long getClienteId() { return clienteId; }
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}
}