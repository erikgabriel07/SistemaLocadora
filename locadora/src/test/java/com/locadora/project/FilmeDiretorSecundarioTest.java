package com.locadora.project;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import com.locadora.project.model.Filme;
import com.locadora.project.model.DiretorSecundario;
import com.locadora.project.model.FilmeDiretorSecundario;

import com.locadora.project.services.FilmeService;
import com.locadora.project.services.DiretorSecundarioService;
import com.locadora.project.services.FilmeDiretorSecundarioService;

import com.locadora.project.util.HibernateUtil;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FilmeDiretorSecundarioTest {
	private FilmeService filmeService;
	private DiretorSecundarioService diretorSecundarioService;
	private FilmeDiretorSecundarioService filmeDiretorSecundarioService;
	
	@BeforeAll
	public void setupSessionFactory() {
		HibernateUtil.buildSessionFactory("hibernate.cfg.test.xml");
	}
	
	@BeforeAll
	public void initializeServices() {
		filmeService = new FilmeService(HibernateUtil.getSessionFactory());
		diretorSecundarioService = new DiretorSecundarioService(HibernateUtil.getSessionFactory());
		filmeDiretorSecundarioService = new FilmeDiretorSecundarioService(HibernateUtil.getSessionFactory());
	}
	
	@Test
	@Order(1)
	public void testCadastrarFilmeDiretorSecundario() {
		Filme filme = new Filme("Hakuna Matata", "Smyslov", "Hakuna Matata is life", 1999, "Humor", 80, "Livre", "Portuguese/Brazil", "released");
		assertDoesNotThrow(() -> filmeService.cadastrar(filme));
		
		DiretorSecundario diretorSecundario = new DiretorSecundario("Vassily", "Error");
		assertDoesNotThrow(() -> diretorSecundarioService.cadastrar(diretorSecundario));
		
		FilmeDiretorSecundario filmeDiretorSecundario = new FilmeDiretorSecundario(filme, diretorSecundario);
		assertDoesNotThrow(() -> filmeDiretorSecundarioService.cadastrar(filmeDiretorSecundario));
	}
	
	@Test
	@Order(2)
	public void testAtualizarFilmeDiretorSecundario() {
		try {
			Filme filme = filmeService.listarId(1L);
			assertNotNull(filme);
			
			filme.setAnoLancamento(2004);
			assertEquals(2004, filme.getAnoLancamento());
			assertDoesNotThrow(() -> filmeService.atualizar(filme));
			
			DiretorSecundario diretorSecundario = diretorSecundarioService.listarId(1L);
			assertNotNull(diretorSecundario);
			
			diretorSecundario.setCargo("Diretor de Arte");
			assertEquals("Diretor de Arte", diretorSecundario.getCargo());
			assertDoesNotThrow(() -> diretorSecundarioService.atualizar(diretorSecundario));
			
			FilmeDiretorSecundario filmeDiretorSecundario = filmeDiretorSecundarioService.listarId(1L);
			assertNotNull(filmeDiretorSecundario);
			filmeDiretorSecundario.setFilme(filme);
			filmeDiretorSecundario.setDiretorSecundario(diretorSecundario);
			
			assertEquals(filme, filmeDiretorSecundario.getFilme());
			assertEquals(diretorSecundario, filmeDiretorSecundario.getDiretorSecundario());
			
			assertDoesNotThrow(() -> filmeDiretorSecundarioService.atualizar(filmeDiretorSecundario));
		} catch (Exception e) {}
	}
	
	@Test
	@Order(4)
	public void testRemoverFilmeDiretorSecundario() {
		try {
			FilmeDiretorSecundario filmeDiretorSecundario = filmeDiretorSecundarioService.listarId(1L);
			assertNotNull(filmeDiretorSecundario);
			
			assertDoesNotThrow(() -> filmeDiretorSecundarioService.remover(filmeDiretorSecundario));
		} catch (Exception e) {}
	}
	
	@Test
	@Order(3)
	public void testListarFilmeDiretorSecundario() {
		filmeDiretorSecundarioService.listar();
	}
	
	@AfterAll
	public void shutDown() {
		HibernateUtil.shutDown();
	}
}