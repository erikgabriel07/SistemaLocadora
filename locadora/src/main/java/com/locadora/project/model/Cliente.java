package com.locadora.project.model;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ElementCollection;

import jakarta.validation.constraints.Email;

import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.CreationTimestamp;

import com.locadora.project.dto.ClienteDTO;

import com.locadora.project.model.Locacao;
import com.locadora.project.model.Dependente;

@Entity
@Table(name = "tb_loc_cliente")
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_cod_cliente_id")
	private Long codCliente;
	
	@Column(name = "nome", nullable = false, unique = false, length = 150)
	private String nome;
	
	@Column(name = "idade", nullable = false, unique = false)
	private Integer idade;
	
	@Column(name = "cpf", nullable = false, unique = true)
	private String cpf;
	
	@Email
	@Column(name = "email", nullable = false, unique = true, length = 255)
	private String email;
	
	@Column(name = "telefone", nullable = false, unique = true, length = 20)
	private String telefone;
	
	@Column(name = "endereco", nullable = false, unique = false, length = 200)
	private String endereco;
	
	@CreationTimestamp
	@Column(name = "data_cadastro")
	private LocalDateTime dataCadastro;
	
	@UpdateTimestamp
	@Column(name = "atualizado")
	private LocalDateTime dataAtualizacao;
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Dependente> dependente = new ArrayList<>();
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Locacao> locacao = new ArrayList<>();
	
	public Cliente() {
	}
	
	public Cliente(ClienteDTO dto) {
		this.nome = dto.getNome();
		this.idade = dto.getIdade();
		this.cpf = dto.getCPF();
		this.email = dto.getEmail();
		this.telefone = dto.getTelefone();
		this.endereco = dto.getEndereco();
	}
	
	public Cliente(String nome, Integer idade, String cpf, String email, String telefone, String endereco) {
		this.nome = nome;
		this.idade = idade;
		this.cpf = cpf;
		this.email = email;
		this.telefone = telefone;
		this.endereco = endereco;
	}
	
	public Long getCodCliente() {
		return codCliente;
	}
	
	public String getNome() {
		return nome;
	}
	
	public Integer getIdade() {
		return idade;
	}
	
	public String getCPF() {
		return cpf;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}
	
	public LocalDateTime getDataAtualizacao() {
		return dataAtualizacao;
	}
	
	public List<Locacao> getLocacao() {
		return locacao;
	}
	
	public List<Dependente> getDependente() {
		return dependente;
	}
	
	public void setCodCliente(Long codCliente) {
		this.codCliente = codCliente;
	}
	
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
	
	public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}
	
	public void update(ClienteDTO dto) {
		if (dto.getNome() != null && !dto.getNome().trim().isEmpty()) nome = dto.getNome();
		
		if (dto.getIdade() != null && dto.getIdade() > 0) idade = dto.getIdade();
		
		if (dto.getCPF() != null && !dto.getCPF().trim().isEmpty()) cpf = dto.getCPF();
		
		if (dto.getEmail() != null && !dto.getEmail().trim().isEmpty()) email = dto.getEmail();
		
		if (dto.getTelefone() != null && !dto.getTelefone().trim().isEmpty()) telefone = dto.getTelefone();
		
		if (dto.getEndereco() != null && !dto.getEndereco().trim().isEmpty()) endereco = dto.getEndereco();
	}
	
	@Override
	public String toString() {
		return String.format(
			"\nID  : %d\n" +
			"Nome  : %s\n" +
			"Idade : %d\n" +
			"CPF   : %s\n" +
			"E-mail: %s\n" +
			"Telefone: %s\n" +
			"Endereço: %s\n" +
			"Cadastro: %s\n" +
			"Atualizado: %s\n\n",
			codCliente, nome, idade,
			cpf, email, telefone, endereco,
			dataCadastro, dataAtualizacao
		);
	}
}