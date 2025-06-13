package com.locadora.project;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import com.locadora.project.model.Local;
import com.locadora.project.services.LocalService;

import com.locadora.project.util.HibernateUtil;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LocalTest {
	private LocalService localService;
	@BeforeAll
	public void setupSessionFactory() {
		HibernateUtil.buildSessionFactory("hibernate.cfg.test.xml");
	}
	
	@BeforeAll
	public void initializeServices() {
		localService = new LocalService(HibernateUtil.getSessionFactory());
	}
	
	@Test
	@Order(1)
	public void testCadastrarLocal() {
		Local local = new Local("Local de Teste");
		
		assertDoesNotThrow(() -> localService.cadastrar(local));
	}
	
	@Test
	@Order(2)
	public void testAtualizarLocal() {
		try {
			Local local = localService.listarId(1L);
			assertNotNull(local);
			
			local.setNome("Local 2");
			
			assertDoesNotThrow(() -> localService.atualizar(local));
		} catch (Exception e) {}
	}
	
	@Test
	@Order(4)
	public void testRemoverLocal() {
		try {
			Local local = localService.listarId(1L);
			assertNotNull(local);
			
			assertDoesNotThrow(() -> localService.remover(local));
		} catch (Exception e) {}
	}
	
	@Test
	@Order(3)
	public void testListarLocal() {
		localService.listar();
	}
}