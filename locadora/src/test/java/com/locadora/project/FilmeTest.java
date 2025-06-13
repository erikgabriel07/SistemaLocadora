package com.locadora.project;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import com.locadora.project.model.Filme;
import com.locadora.project.services.FilmeService;

import com.locadora.project.util.HibernateUtil;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FilmeTest {
	private FilmeService filmeService;
	
	@BeforeAll
	public void setupSessionFactory() {
		HibernateUtil.buildSessionFactory("hibernate.cfg.test.xml");
	}
	
	@BeforeAll
	public void initializeServices() {
		filmeService = new FilmeService(HibernateUtil.getSessionFactory());
	}
	
	@Test
	@Order(1)
	public void testCadastrarFilme() {
		Filme filme = new Filme("NaN", "NaN", "NaN", 1000, "NaN", 9999, "NaN", "NaN", "NaN");
		
		assertDoesNotThrow(() -> filmeService.cadastrar(filme));
	}
	
	@Test
	@Order(2)
	public void testAtualizarFilme() {
		try {
			Filme filme = filmeService.listarId(1L);
			assertNotNull(filme);
			
			filme.setNome("Star Wars: The Rise of Skywalker");
			assertEquals("Star Wars: The Rise of Skywalker", filme.getNome());
			
			filme.setDiretor("J. J. Abrams");
			assertEquals("J. J. Abrams", filme.getDiretor());
			
			filme.setSinopse("Sem informação");
			assertEquals("Sem informação", filme.getSinopse());
			
			filme.setAnoLancamento(2019);
			assertEquals(2019, filme.getAnoLancamento());
			
			filme.setGenero("épico, aventura, fantasia, ficção científica, space opera");
			assertEquals("épico, aventura, fantasia, ficção científica, space opera", filme.getGenero());
			
			filme.setDuracao(141);
			assertEquals(141, filme.getDuracao());
			
			filme.setClassificacao("Para maiores de 12 anos.");
			assertEquals("Para maiores de 12 anos.", filme.getClassificacao());
			
			filme.setIdioma("Portuguese/Brazil");
			assertEquals("Portuguese/Brazil", filme.getIdioma());
			
			filme.setStatus("Released");
			assertEquals("Released", filme.getStatus());
			
			assertDoesNotThrow(() -> filmeService.atualizar(filme));
		} catch (Exception e) {}
	}
	
	@Test
	@Order(4)
	public void testRemoverFilme() {
		try {
			Filme filme = filmeService.listarId(1L);
			assertNotNull(filme);
			
			assertDoesNotThrow(() -> filmeService.atualizar(filme));
		} catch (Exception e) {}
	}
	
	@Test
	@Order(3)
	public void testListarFilme() {
		filmeService.listar();
	}
	
	@AfterAll
	public void shutDown() {
		HibernateUtil.shutDown();
	}
} 
