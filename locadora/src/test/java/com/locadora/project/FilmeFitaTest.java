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
public class FilmeFitaTest {
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
	public void testCadastrarFilmeFita() {
		Fita fita = new Fita("Hakuna Matata Fita", 30.99);
		assertDoesNotThrow(() -> fitaService.cadastrar(fita));
		
		Filme filme = new Filme("Hakuna Matata", "Smyslov", "Hakuna Matata is life", 1999, "Humor", 80, "Livre", "Portuguese/Brazil", "released");
		assertDoesNotThrow(() -> filmeService.cadastrar(filme));
		
		FilmeFita filmeFita = new FilmeFita(fita, filme);
		assertDoesNotThrow(() -> filmeFitaService.cadastrar(filmeFita));
	}
	
	@Test
	@Order(2)
	public void testAtualizarFilmeFita() {
		try {
			Fita fita = fitaService.listarId(1L);
			assertNotNull(fita);
			
			fita.setValor(17.99);
			assertEquals(17.99, fita.getValor());
			assertDoesNotThrow(() -> fitaService.atualizar(fita));
			
			Filme filme = filmeService.listarId(1L);
			assertNotNull(filme);
			
			filme.setDuracao(100);
			assertEquals(100, filme.getDuracao());
			assertDoesNotThrow(() -> filmeService.atualizar(filme));
			
			FilmeFita filmeFita = filmeFitaService.listarId(1L);
			assertNotNull(filmeFita);
			
			filmeFita.setFita(fita);
			filmeFita.setFilme(filme);
			
			assertDoesNotThrow(() -> filmeFitaService.atualizar(filmeFita));
		} catch (Exception e) {}
	}
	
	@Test
	@Order(4)
	public void testRemoverFilmeFita() {
		try {
			FilmeFita filmeFita = filmeFitaService.listarId(1L);
			assertNotNull(filmeFita);
			
			assertDoesNotThrow(() -> filmeFitaService.remover(filmeFita));
		} catch (Exception e) {}
	}
	
	@Test
	@Order(3)
	public void testListarFilmeFita() {
		filmeFitaService.listar();
	}
} 
