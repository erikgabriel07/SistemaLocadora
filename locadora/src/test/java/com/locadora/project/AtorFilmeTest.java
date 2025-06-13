package com.locadora.project;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import com.locadora.project.model.Ator;
import com.locadora.project.model.Filme;
import com.locadora.project.model.AtorFilme;

import com.locadora.project.services.AtorService;
import com.locadora.project.services.FilmeService;
import com.locadora.project.services.AtorFilmeService;

import com.locadora.project.util.HibernateUtil;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AtorFilmeTest {
	private AtorService atorService;
	private FilmeService filmeService;
	private AtorFilmeService atorFilmeService;
	
	@BeforeAll
	public void setupSessionFactory() {
		HibernateUtil.buildSessionFactory("hibernate.cfg.test.xml");
	}
	
	@BeforeAll
	public void initializeServices() {
		atorService = new AtorService(HibernateUtil.getSessionFactory());
		filmeService = new FilmeService(HibernateUtil.getSessionFactory());
		atorFilmeService = new AtorFilmeService(HibernateUtil.getSessionFactory());
	}
	
	@Test
	@Order(1)
	public void testCadastrarAtorFilme() {
		Ator ator = new Ator("Jonas", LocalDate.parse("1978-03-15"), "Brazilian", "Masculino", "NaN", "Sem informação");
		assertDoesNotThrow(() -> atorService.cadastrar(ator));
		
		Filme filme = new Filme("End of Beginning", "NaN", "NaN", 1600, "Action", 74, "Not recomended for you", "British", "Released");
		assertDoesNotThrow(() -> filmeService.cadastrar(filme));
		
		AtorFilme atorFilme = new AtorFilme("Protagonista", ator, filme);
		assertDoesNotThrow(() -> atorFilmeService.cadastrar(atorFilme));
	}
	
	@Test
	@Order(2)
	public void testAtualizarAtorFilme() {
		try {
			Ator ator = atorService.listarId(1L);
			assertNotNull(ator);
			
			ator.setNome("Juliano");
			assertEquals("Juliano", ator.getNome());
			atorService.atualizar(ator);
			
			Filme filme = filmeService.listarId(1L);
			assertNotNull(filme);
			
			filme.setNome("Begin After End");
			assertEquals("Begin After End", filme.getNome());
			filmeService.atualizar(filme);
			
			AtorFilme atorFilme = atorFilmeService.listarId(1L);
			assertNotNull(atorFilme);
			
			atorFilme.setPapel("Figurante");
			assertEquals(atorFilme.getPapel(), "Figurante");
			
			atorFilme.setAtor(ator);
			assertEquals(atorFilme.getAtor(), ator);
			
			atorFilme.setFilme(filme);
			assertEquals(atorFilme.getFilme(), filme);
			
			assertDoesNotThrow(() -> atorFilmeService.atualizar(atorFilme));
		} catch (Exception e) {}
	}
	
	@Test
	@Order(4)
	public void testRemoverAtorFilme() {
		try {
			AtorFilme atorFilme = atorFilmeService.listarId(1L);
			assertNotNull(atorFilme);
			assertDoesNotThrow(() -> atorFilmeService.remover(atorFilme));
		} catch (Exception e) {}
	}
	
	@Test
	@Order(3)
	public void testListarAtorFilme() {
		atorFilmeService.listar();
	}
	
	@AfterAll
	public void shutDown() {
		HibernateUtil.shutDown();
	}
} 
