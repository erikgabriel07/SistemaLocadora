package com.locadora.project;

import java.lang.System;
import java.util.Scanner;

import com.locadora.project.model.*;
import com.locadora.project.util.ConsoleUtil;
import com.locadora.project.util.HibernateUtil;
import com.locadora.project.util.ControllerUtil;

public class App 
{
	private static final Scanner scanner = new Scanner(System.in);
	
	public static void main( String[] args )
	{
		HibernateUtil.buildSessionFactory("hibernate.cfg.xml");
		
		Integer status = -1, option;
		
		ConsoleUtil.waitInput();
		while (status != 4) {
			ConsoleUtil.ClearConsole();
			
			status = ConsoleUtil.PrincipalMenu();
			
			ConsoleUtil.ClearConsole();
			switch (status) {
				case 1:
					option = ConsoleUtil.ClienteMenu();
					
					if (option != 0) Cliente(option);
					break;
				case 2:
					option = ConsoleUtil.ItemMenu();
					
					if (option != 0) Item(option);
					break;
				case 3:
					ConsoleUtil.LocacaoMenu();
					break;
			}
		}
		
		HibernateUtil.shutDown();
	}
	
	public static void Cliente(Integer opcao) {
		Integer idade;
		Long clienteId;
		Cliente cliente;
		String nome, cpf, email, telefone, endereco;
		
		ConsoleUtil.ClearConsole();
		switch (opcao) {
			case 1:
				System.out.print("Nome: ");
				nome = scanner.nextLine();
				
				System.out.print("CPF: ");
				cpf = scanner.nextLine();
				
				System.out.print("EMAIL: ");
				email = scanner.nextLine();
				
				System.out.print("Telefone: ");
				telefone = scanner.nextLine();
				
				System.out.print("Endereço: ");
				endereco = scanner.nextLine();
				
				System.out.print("Idade: ");
				idade = scanner.nextInt();
				
				ControllerUtil.clienteController.cadastrarCliente(nome, idade, cpf, email, telefone, endereco);
				
				scanner.nextLine();
				break;
			case 2:
				System.out.print("ID do cliente: ");
				clienteId = scanner.nextLong();
				
				cliente = ControllerUtil.clienteController.buscarCliente(clienteId);
				
				if (cliente == null) break;
				
				System.out.print(cliente);
				
				scanner.nextLine();
				
				System.out.print("Nome: ");
				nome = scanner.nextLine();
				
				System.out.print("CPF: ");
				cpf = scanner.nextLine();
				
				System.out.print("EMAIL: ");
				email = scanner.nextLine();
				
				System.out.print("Telefone: ");
				telefone = scanner.nextLine();
				
				System.out.print("Endereço: ");
				endereco = scanner.nextLine();
				
				System.out.print("Idade (digite 0 para deixar em branco): ");
				idade = scanner.nextInt();
				
				ControllerUtil.clienteController.atualizarCliente(clienteId, nome, idade, cpf, email, telefone, endereco);
				
				scanner.nextLine();
				
				break;
			case 3:
				System.out.print("ID do cliente: ");
				clienteId = scanner.nextLong();
				
				cliente = ControllerUtil.clienteController.buscarCliente(clienteId);
				
				if (cliente == null) break;
				
				ControllerUtil.clienteController.removerCliente(clienteId);
				
				break;
			case 4:
				System.out.print("CPF do cliente: ");
				cpf = scanner.nextLine();
				
				ControllerUtil.clienteController.listarClienteCPF(cpf);
				
				break;
			case 5:
				System.out.println("- Filtros -");
				
				System.out.print("Nome: ");
				nome = scanner.nextLine();
				
				System.out.print("Email: ");
				email = scanner.nextLine();
				
				System.out.print("Telefone: ");
				telefone = scanner.nextLine();
				
				System.out.print("Idade (0 para não aplicar filtro): ");
				idade = scanner.nextInt();
				
				ControllerUtil.clienteController.listarFiltro(nome, idade, telefone, email);
				
				scanner.nextLine();
				
				break;
			case 6:
				System.out.print("ID do cliente: ");
				clienteId = scanner.nextLong();
				
				ControllerUtil.clienteController.listarHistorico(clienteId);
				
				scanner.nextLine();
				
				break;
		}
		
		ConsoleUtil.waitInput();
	}
	
	public static void Item(Integer opcao) {
		Integer anoLancamento, duracao;
		String nome, diretor, sinopse,
		genero, classificacao, idioma,
		status;
		
		Long itemId;
		
		Filme filme;
		
		ConsoleUtil.ClearConsole();
		switch (opcao) {
			case 1:
				System.out.print("Nome: ");
				nome = scanner.nextLine();
				
				System.out.print("Idioma: ");
				idioma = scanner.nextLine();
				
				System.out.print("Gênero: ");
				genero = scanner.nextLine();
				
				System.out.print("Sinopse: ");
				sinopse = scanner.nextLine();
				
				System.out.print("Diretor: ");
				diretor = scanner.nextLine();
				
				System.out.print("Classificação: ");
				classificacao = scanner.nextLine();
				
				System.out.print("Status: ");
				status = scanner.nextLine();
				
				System.out.print("Ano: ");
				anoLancamento = scanner.nextInt();
				
				System.out.print("Duração (em minutos): ");
				duracao = scanner.nextInt();
				
				ControllerUtil.filmeController.cadastrarFilme(
					nome, diretor, sinopse, anoLancamento, genero, duracao, classificacao, idioma, status, null, null, null, null
				);
				
				scanner.nextLine();
				break;
			case 2:
				System.out.print("ID do item: ");
				itemId = scanner.nextLong();
				
				filme = ControllerUtil.filmeController.buscarFilme(itemId);
				
				System.out.println(filme);
				
				scanner.nextLine();
				
				System.out.print("Nome: ");
				nome = scanner.nextLine();
				
				System.out.print("Idioma: ");
				idioma = scanner.nextLine();
				
				System.out.print("Gênero: ");
				genero = scanner.nextLine();
				
				System.out.print("Sinopse: ");
				sinopse = scanner.nextLine();
				
				System.out.print("Diretor: ");
				diretor = scanner.nextLine();
				
				System.out.print("Classificação: ");
				classificacao = scanner.nextLine();
				
				System.out.print("Ano: ");
				anoLancamento = scanner.nextInt();
				
				System.out.print("Duração (em minutos): ");
				duracao = scanner.nextInt();
				
				ControllerUtil.filmeController.atualizarFilme(
					itemId, nome, diretor, sinopse, anoLancamento, genero, duracao, classificacao, idioma, null, null, null, null, null
				);
				
				scanner.nextLine();
				break;
			case 3:
				System.out.print("ID do item: ");
				itemId = scanner.nextLong();
				
				filme = ControllerUtil.filmeController.buscarFilme(itemId);
				
				System.out.println(filme);
				
				System.out.print("Status: ");
				status = scanner.nextLine();
				
				ControllerUtil.filmeController.atualizarFilme(
					itemId, null, null, null, null, null, null, null, null, status, null, null, null, null
				);
				
				scanner.nextLine();
				break;
			case 4:
				System.out.print("ID do item: ");
				itemId = scanner.nextLong();
				
				filme = ControllerUtil.filmeController.buscarFilme(itemId);
				
				System.out.println(filme);
				
				scanner.nextLine();
				break;
			case 5:
				ControllerUtil.filmeController.listar();
				break;
		}
		
		ConsoleUtil.waitInput();
	}
}
