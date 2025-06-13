package com.locadora.project;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import com.locadora.project.model.Dependente;
import com.locadora.project.model.Cliente;

import com.locadora.project.services.DependenteService;
import com.locadora.project.services.ClienteService;

import com.locadora.project.util.HibernateUtil;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DependenteTest {
	private DependenteService dependenteService;
	private ClienteService clienteService;
	
	@BeforeAll
	public void setupSessionFactory() {
		HibernateUtil.buildSessionFactory("hibernate.cfg.test.xml");
	}
	
	@BeforeAll
	public void initializeServices() {
		dependenteService = new DependenteService(HibernateUtil.getSessionFactory());
		clienteService = new ClienteService(HibernateUtil.getSessionFactory());
	}
	
	@Test
	@Order(1)
	public void testCadastrarDependente() {
		Cliente cliente = new Cliente("Teste", 30, "123.321.456-33", "test@mail.com", "84976341263", "Teste");
		assertDoesNotThrow(() -> clienteService.cadastrar(cliente));
		
		Dependente dependente = new Dependente("Bruno", 19, "Masculino", cliente);
		assertDoesNotThrow(() -> dependenteService.cadastrar(dependente));
	}
	
	@Test
	@Order(2)
	public void testAtualizarDependente() {
		try {
			Dependente dependente = dependenteService.listarId(1L);
			assertNotNull(dependente);
			
			dependente.setNome("Beatriz");
			assertEquals("Beatriz", dependente.getNome());
			
			dependente.setIdade(20);
			assertEquals(20, dependente.getIdade());
			
			dependente.setGenero("Feminino");
			assertEquals("Feminino", dependente.getGenero());
			
			assertDoesNotThrow(() -> dependenteService.atualizar(dependente));
		} catch (Exception e) {}
	}
	
	@Test
	@Order(4)
	public void testRemoverCliente() {
		try {
			Dependente dependente = dependenteService.listarId(1L);
			assertNotNull(dependente);
			
			assertDoesNotThrow(() -> dependenteService.remover(dependente));
		} catch (Exception e) {}
	}
	
	@Test
	@Order(3)
	public void testListarDependente() {
		dependenteService.listar();
	}
	
	@AfterAll
	public void shutDown() {
		HibernateUtil.shutDown();
	}
}