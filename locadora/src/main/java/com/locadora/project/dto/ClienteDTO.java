package com.locadora.project.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import com.locadora.project.util.Groups.*;

public class ClienteDTO {
	@NotBlank(groups = {OnCreate.class}, message = "O nome não deve ser deixado em branco.")
	private String nome;
	
	@Positive(groups = {OnCreate.class}, message = "A idade não pode ser menor ou igual a zero.")
	private Integer idade;
	
	@Pattern(groups = {OnCreate.class}, regexp = "([0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[-]?[0-9]{2})", message = "O CPF é inválido.")
	private String cpf;
	
	@Email(groups = {OnCreate.class}, message = "O email é inválido.")
	private String email;
	
	@Pattern(groups = {OnCreate.class}, regexp = "^(?:\\+?55\\s?)?(?:\\(?\\d{2}\\)?\\s?)?(?:9\\d{4}|\\d{4})-?\\d{4}$", message = "Telefone inválido.")
	private String telefone;
	
	@NotBlank(groups = {OnCreate.class}, message = "O endereço não pode ser deixado em branco.")
	private String endereco;
	
	@PositiveOrZero(message = "O ID da locação é inválido.")
	private Long locacaoId;
	
	@PositiveOrZero(message = "O ID do dependente é inválido.")
	private Long dependenteId;
	
	public String getNome() { return nome; }
	
	public Integer getIdade() { return idade; }
	
	public String getCPF() { return cpf; }
	
	public String getEmail() { return email; }
	
	public String getTelefone() { return telefone; }
	
	public String getEndereco() { return endereco; }
	
	public Long getLocacaoId() { return locacaoId; }
	
	public Long getDependenteId() { return dependenteId; }
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	
	public void setCPF(String cpf) {
		this.cpf = cpf;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public void setLocacaoId(Long locacaoId) {
		this.locacaoId = locacaoId;
	}
	
	public void setDependenteId(Long dependenteId) {
		this.dependenteId = dependenteId;
	}
}