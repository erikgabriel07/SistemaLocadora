package com.locadora.project;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import com.locadora.project.model.Local;
import com.locadora.project.model.Filme;
import com.locadora.project.model.FilmeLocal;

import com.locadora.project.services.LocalService;
import com.locadora.project.services.FilmeService;
import com.locadora.project.services.FilmeLocalService;

import com.locadora.project.util.HibernateUtil;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FilmeLocalTest {
	private LocalService localService;
	private FilmeService filmeService;
	private FilmeLocalService filmeLocalService;
	
	@BeforeAll
	public void setupSessionFactory() {
		HibernateUtil.buildSessionFactory("hibernate.cfg.test.xml");
	}
	
	@BeforeAll
	public void initializeServices() {
		localService = new LocalService(HibernateUtil.getSessionFactory());
		filmeService = new FilmeService(HibernateUtil.getSessionFactory());
		filmeLocalService = new FilmeLocalService(HibernateUtil.getSessionFactory());
	}
	
	@Test
	@Order(1)
	public void testCadastrarFilmeLocal() {
		Local local = new Local("Local 1");
		assertDoesNotThrow(() -> localService.cadastrar(local));
		
		Filme filme = new Filme("Hakuna Matata", "Smyslov", "Hakuna Matata is life", 1999, "Humor", 80, "Livre", "Portuguese/Brazil", "released");
		assertDoesNotThrow(() -> filmeService.cadastrar(filme));
		
		FilmeLocal filmeLocal = new FilmeLocal(filme, local);
		assertDoesNotThrow(() -> filmeLocalService.cadastrar(filmeLocal));
	}
	
	@Test
	@Order(2)
	public void testAtualizarFilmeLocal() {
		try {
			Local local = localService.listarId(1L);
			assertNotNull(local);
			
			local.setNome("Local 2");
			assertEquals("Local 2", local.getNome());
			assertDoesNotThrow(() -> localService.atualizar(local));
			
			Filme filme = filmeService.listarId(1L);
			assertNotNull(filme);
			
			filme.setAnoLancamento(2004);
			assertEquals(2004, filme.getAnoLancamento());
			assertDoesNotThrow(() -> filmeService.atualizar(filme));
			
			FilmeLocal filmeLocal = filmeLocalService.listarId(1L);
			assertNotNull(filmeLocal);
			
			filmeLocal.setLocal(local);
			assertEquals(local, filmeLocal.getLocal());
			
			filmeLocal.setFilme(filme);
			assertEquals(filme, filmeLocal.getFilme());
			
			assertDoesNotThrow(() -> filmeLocalService.atualizar(filmeLocal));
		} catch (Exception e) {}
	}
	
	@Test
	@Order(4)
	public void testRemoverFilmeLocal() {
		try {
			FilmeLocal filmeLocal = filmeLocalService.listarId(1L);
			assertNotNull(filmeLocal);
			
			assertDoesNotThrow(() -> filmeLocalService.remover(filmeLocal));
		} catch (Exception e) {}
	}
	
	@Test
	@Order(3)
	public void testListarFilmeLocal() {
		filmeLocalService.listar();
	}
	
	@AfterAll
	public void shutDown() {
		HibernateUtil.shutDown();
	}
} 
