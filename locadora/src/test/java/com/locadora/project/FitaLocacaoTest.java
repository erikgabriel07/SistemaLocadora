package com.locadora.project;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import com.locadora.project.model.Fita;
import com.locadora.project.model.Locacao;
import com.locadora.project.model.Cliente;
import com.locadora.project.model.FitaLocacao;

import com.locadora.project.services.FitaService;
import com.locadora.project.services.LocacaoService;
import com.locadora.project.services.ClienteService;
import com.locadora.project.services.FitaLocacaoService;

import com.locadora.project.util.HibernateUtil;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FitaLocacaoTest {
    private FitaService fitaService;
    private LocacaoService locacaoService;
	private ClienteService clienteService;
    private FitaLocacaoService fitaLocacaoService;
    
	@BeforeAll
	public void setupSessionFactory() {
		HibernateUtil.buildSessionFactory("hibernate.cfg.test.xml");
	}
	
	@BeforeAll
	public void initializeServices() {
		fitaService = new FitaService(HibernateUtil.getSessionFactory());
		clienteService = new ClienteService(HibernateUtil.getSessionFactory());
        locacaoService = new LocacaoService(HibernateUtil.getSessionFactory());
        fitaLocacaoService = new FitaLocacaoService(HibernateUtil.getSessionFactory());
	}
	
	@Test
	@Order(1)
	public void testCadastrarFitaLocacao() {
        Fita fita = new Fita("Fita KN9001", 30.99);
		assertDoesNotThrow(() -> fitaService.cadastrar(fita));
		
		Cliente cliente = new Cliente("Teste", 34, "000.000.000-00", "test@email.com", "+5579900001111", "Rua 1");
		assertDoesNotThrow(() -> clienteService.cadastrar(cliente));
		
		FitaLocacao fitaLocacao = new FitaLocacao(fita);
		assertDoesNotThrow(() -> fitaLocacaoService.cadastrar(fitaLocacao));
		
		Locacao locacao = new Locacao("Locação X", cliente, fitaLocacao);
		assertDoesNotThrow(() -> locacaoService.cadastrar(locacao));
	}
	
	@Test
	@Order(2)
	public void testAtualizarFitaLocacao() {
		try {
			FitaLocacao fitaLocacao = fitaLocacaoService.listarId(1L);
			assertNotNull(fitaLocacao);
			
			Locacao locacao = locacaoService.listarId(1L);
			assertNotNull(locacao);
			
			locacao.setLocacaoUid("Locação XI");
			assertEquals("Locação XI", locacao.getLocacaoUid());
			assertDoesNotThrow(() -> locacaoService.atualizar(locacao));
		} catch (Exception e) {}
	}
	
	@Test
	@Order(4)
	public void testRemoverFitaLocacao() {
		try {
			FitaLocacao fitaLocacao = fitaLocacaoService.listarId(1L);
			assertNotNull(fitaLocacao);
			
			assertDoesNotThrow(() -> fitaLocacaoService.remover(fitaLocacao));
		} catch (Exception e) {}
	}
	
	@Test
	@Order(3)
	public void testListarFitaLocacao() {
		fitaLocacaoService.listar();
	}
	
	@AfterAll
	public void shutDown() {
		HibernateUtil.shutDown();
	}
} 
