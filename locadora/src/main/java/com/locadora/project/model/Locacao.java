package com.locadora.project.model;

import java.time.LocalDateTime;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import com.locadora.project.dto.LocacaoDTO;

import com.locadora.project.model.Cliente;
import com.locadora.project.model.FitaLocacao;

import org.hibernate.annotations.CurrentTimestamp;

@Entity
@Table(name = "tb_loc_locacao")
public class Locacao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_cod_locacao_id")
	private Long codLocacao;
	
	@CurrentTimestamp
	@Column(name = "data_loc", nullable = false, unique = false)
	private LocalDateTime data;
	
	@Column(name = "locacao_uid", nullable = false, unique = true)
	private String locacao_uid;
	
	@Column(name = "valor_total", nullable = false, unique = false)
	private Double valor_total;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false)
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "fita_locacao_id", nullable = false)
	private FitaLocacao fitaLocacao;
	
	public Locacao() {
	}
	
	public Locacao(LocacaoDTO dto) {
		this.valor_total = dto.getValorTotal();
	}
	
	public Locacao(String locacao_uid, Cliente cliente, FitaLocacao fitaLocacao) {
		this.locacao_uid = locacao_uid;
		this.valor_total = fitaLocacao.getFita().getValor();
		this.cliente = cliente;
		this.fitaLocacao = fitaLocacao;
	}
	
	public Long getCodLocacao() {
		return codLocacao;
	}
	
	public String getLocacaoUid() {
		return locacao_uid;
	}
	
	public LocalDateTime getData() {
		return data;
	}
	
	public Double getValorTotal() {
		return valor_total;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public FitaLocacao getFitaLocacao() {
		return fitaLocacao;
	}
	
	public void setCodLocacao(Long codLocacao) {
		this.codLocacao = codLocacao;
	}
	
	public void setLocacaoUid(String locacao_uid) {
		this.locacao_uid = locacao_uid;
	}
	
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	
	public void setValorTotal(Double valor_total) {
		this.valor_total = valor_total;
	}
	
	public void setFitaLocacao(FitaLocacao fitaLocacao) {
		this.fitaLocacao = fitaLocacao;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public void update(LocacaoDTO dto) {
		if (dto.getData() != null) data = dto.getData();
		
		if (dto.getValorTotal() != null && dto.getValorTotal() > 0) valor_total = dto.getValorTotal();
	}
	
	@Override
	public String toString() {
		return String.format(
			"\nUID: %s\n" +
			"Data: %s\n" +
			"Valor: %.2f\n" +
			"Cliente: %s\n" +
			"Fita: %s\n",
			locacao_uid,
			data, valor_total,
			cliente.getNome(),
			fitaLocacao.getFita().getNome()
		);
	}
}