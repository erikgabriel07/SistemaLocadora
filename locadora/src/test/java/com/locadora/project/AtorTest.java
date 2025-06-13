package com.locadora.project;

import java.time.LocalDate;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import com.locadora.project.model.Ator;
import com.locadora.project.services.AtorService;

import com.locadora.project.util.HibernateUtil;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AtorTest {
	private AtorService atorService;
	
	@BeforeAll
	public void setupSessionFactory() {
		HibernateUtil.buildSessionFactory("hibernate.cfg.test.xml");
	}
	
	@BeforeAll
	public void initializeServices() {
		atorService = new AtorService(HibernateUtil.getSessionFactory());
	}
	
	@Test
	@Order(1)
	public void testCadastrarAtor() {
		Ator ator = new Ator("Jonas", LocalDate.parse("1978-03-15"), "Brazilian", "Masculino", "NaN", "Sem informação");
		
		assertDoesNotThrow(() -> atorService.cadastrar(ator));
	}
	
	@Test
	@Order(2)
	public void testAtualizarAtor() {
		try {
			Ator ator = atorService.listarId(1L);
			assertNotNull(ator);
			
			ator.setNome("Hellen");
			assertEquals("Hellen", ator.getNome());
			
			ator.setNascimento(LocalDate.parse("1977-03-25"));
			assertEquals(LocalDate.parse("1977-03-25"), ator.getNascimento());
			
			ator.setNacionalidade("Romanian");
			assertEquals("Romanian", ator.getNacionalidade());
			
			ator.setGenero("Feminino");
			assertEquals("Feminino", ator.getGenero());
			
			ator.setBiografia("She doesn't want to talk about.");
			assertEquals("She doesn't want to talk about.", ator.getBiografia());
			
			ator.setTipoCarreira("Action/Drama");
			assertEquals("Action/Drama", ator.getTipoCarreira());
			
			assertDoesNotThrow(() -> atorService.atualizar(ator));
		} catch (Exception e) {}
	}
	
	@Test
	@Order(4)
	public void testRemoverAtor() {
		try {
			Ator ator = atorService.listarId(1L);
			assertNotNull(ator);
			assertDoesNotThrow(() -> atorService.remover(ator));
		} catch (Exception e) {}
	}
	
	@Test
	@Order(3)
	public void testListarAtor() {
		atorService.listar();
	}
	
	@AfterAll
	public void shutDown() {
		HibernateUtil.shutDown();
	}
} 

