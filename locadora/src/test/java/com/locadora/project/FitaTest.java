package com.locadora.project;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import com.locadora.project.model.Fita;
import com.locadora.project.model.Filme;
import com.locadora.project.model.FilmeFita;

import com.locadora.project.services.FitaService;
import com.locadora.project.services.FilmeService;
import com.locadora.project.services.FilmeFitaService;

import com.locadora.project.util.HibernateUtil;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FitaTest {
	private FitaService fitaService;
	private FilmeService filmeService;
	private FilmeFitaService filmeFitaService;
	
	@BeforeAll
	public void setupSessionFactory() {
		HibernateUtil.buildSessionFactory("hibernate.cfg.test.xml");
	}
	
	@BeforeAll
	public void initializeServices() {
		fitaService = new FitaService(HibernateUtil.getSessionFactory());
		filmeService = new FilmeService(HibernateUtil.getSessionFactory());
		filmeFitaService = new FilmeFitaService(HibernateUtil.getSessionFactory());
	}
	
	@Test
	@Order(1)
	public void testCadastrarFita() {
		Filme filme = new Filme("Test", "NaN", "NaN", 1600, "Action", 74, "+10", "English", "Released");
		assertDoesNotThrow(() -> filmeService.cadastrar(filme));
		
		FilmeFita filmeFita = new FilmeFita(null, filme);
		
		Fita fita = new Fita("Test Tape", 47.99);
		
		fita.setFilmeFita(filmeFita);
		filmeFita.setFita(fita);
		
		assertDoesNotThrow(() -> fitaService.cadastrar(fita));
	}
	
	@Test
	@Order(2)
	public void testAtualizarFita() {
		try {
			Fita fita = fitaService.listarId(1L);
			assertNotNull(fita);
			
			fita.setNome("DON'T SEE THIS TAPE!");
			assertEquals("DON'T SEE THIS TAPE!", fita.getNome());
			
			assertDoesNotThrow(() -> fitaService.atualizar(fita));
		} catch (Exception e) {}
	}
	
	@Test
	@Order(4)
	public void testRemoverFita() {
		try {
			Fita fita = fitaService.listarId(1L);
			assertNotNull(fita);
			
			assertDoesNotThrow(() -> fitaService.remover(fita));
		} catch (Exception e) {}
	}
	
	@Test
	@Order(3)
	public void testListarFita() {
		fitaService.listar();
	}
	
	@AfterAll
	public void shutDown() {
		HibernateUtil.shutDown();
	}
} 
