package com.locadora.project.util;

import java.lang.System;
import java.util.Scanner;
import java.lang.Process;
import java.lang.ProcessBuilder;

public class ConsoleUtil {
	public static Integer PrincipalMenu() {
		System.out.println("\n- MENU PRINCIPAL -");
		System.out.println("[1] CLIENTE MENU");
		System.out.println("[2] ITEM MENU");
		System.out.println("[3] LOCAÇÃO MENU");
		System.out.println("[4] Sair");
		
		return Opcao(1, 4);
	}
	
	public static Integer ClienteMenu() {
		System.out.println("\n- CLIENTE MENU -");
		System.out.println("[1] Cadastrar cliente");
		System.out.println("[2] Atualizar cliente");
		System.out.println("[3] Remover cliente");
		System.out.println("[4] Consultar cliente por CPF");
		System.out.println("[5] Listar todos os clientes");
		System.out.println("[6] Ver histórico de locações");
		
		return Opcao(0, 6);
	}
	public static Integer ItemMenu() {
		System.out.println("- ITEM MENU -");
		System.out.println("[1] Cadastrar novo item");
		System.out.println("[2] Atualizar dados do item");
		System.out.println("[3] Alterar status do item");
		System.out.println("[4] Buscar dados do item");
		System.out.println("[5] Listar itens");
		
		return Opcao(0, 5);
	}
	
	public static Integer LocacaoMenu() {
		System.out.println("- LOCAÇÃO MENU -");
		System.out.println("[1] Registrar nova locação");
		System.out.println("[2] Registrar devolução de item");
		System.out.println("[3] Calcular valor total da locação");
		System.out.println("[4] Listar locações ativas");
		System.out.println("[5] Listar loações com devolução em atraso");
		System.out.println("[6] Cancelar locação");
		
		return Opcao(0, 6);
	}
	
	public static Integer Opcao(Integer minimo, Integer maximo) {
		Integer opcao = -1;
		
		Scanner scanner = new Scanner(System.in);
		while (opcao < minimo || opcao > maximo) {
			System.out.print("Escolha uma opção: ");
			opcao = scanner.nextInt();
			
			if (opcao < minimo || opcao > maximo || opcao == null) {
				System.out.println("Opção inválida.");
			}
		}
		
		return opcao;
	}
	
	public static void waitInput() {
		System.out.print("Press ENTER to continue...");
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
	}
	
	public static void ClearConsole() {
		try {
			String operatingSystem = System.getProperty("os.name");
			
			if (operatingSystem.contains("windows")) {
				ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
				Process startProcess = pb.inheritIO().start();
				startProcess.waitFor();
			}
			else {
				ProcessBuilder pb = new ProcessBuilder("clear");
				Process startProcess = pb.inheritIO().start();
				startProcess.waitFor();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
