package com.locadora.project;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import com.locadora.project.model.Fita;
import com.locadora.project.model.Filme;
import com.locadora.project.model.Cliente;
import com.locadora.project.model.Locacao;
import com.locadora.project.model.FilmeFita;
import com.locadora.project.model.FitaLocacao;

import com.locadora.project.services.FitaService;
import com.locadora.project.services.FilmeService;
import com.locadora.project.services.ClienteService;
import com.locadora.project.services.LocacaoService;
import com.locadora.project.services.FilmeFitaService;
import com.locadora.project.services.FitaLocacaoService;

import com.locadora.project.util.HibernateUtil;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LocacaoTest {
	private FitaService fitaService;
	private FilmeService filmeService;
	private ClienteService clienteService;
	private LocacaoService locacaoService;
	private FilmeFitaService filmeFitaService;
	private FitaLocacaoService fitaLocacaoService;
	
	@BeforeAll
	public void setupSessionFactory() {
		HibernateUtil.buildSessionFactory("hibernate.cfg.test.xml");
	}
	
	@BeforeAll
	public void initializeServices() {
		fitaService = new FitaService(HibernateUtil.getSessionFactory());
		filmeService = new FilmeService(HibernateUtil.getSessionFactory());
		clienteService = new ClienteService(HibernateUtil.getSessionFactory());
        locacaoService = new LocacaoService(HibernateUtil.getSessionFactory());
		filmeFitaService = new FilmeFitaService(HibernateUtil.getSessionFactory());
        fitaLocacaoService = new FitaLocacaoService(HibernateUtil.getSessionFactory());
	}
	
	@Test
	@Order(1)
	public void testCadastrarLocacao() {
		Cliente cliente = new Cliente("Czar", 34, "000.000.000-00", "marcelo.test@email.com", "+5579912344321", "Rua 1");
		assertDoesNotThrow(() -> clienteService.cadastrar(cliente));
		
		Fita fita = new Fita("Hakuna Matata Fita", 30.99);
		assertDoesNotThrow(() -> fitaService.cadastrar(fita));
		
		Filme filme = new Filme("NaN", "NaN", "NaN", 1000, "NaN", 9999, "NaN", "NaN", "NaN");
		assertDoesNotThrow(() -> filmeService.cadastrar(filme));
		
		FilmeFita filmeFita = new FilmeFita(fita, filme);
		assertDoesNotThrow(() -> filmeFitaService.cadastrar(filmeFita));
		
		FitaLocacao fitaLocacao = new FitaLocacao(fita);
		assertDoesNotThrow(() -> fitaLocacaoService.cadastrar(fitaLocacao));
		
		Locacao locacao = new Locacao("Czar_loc_1245", cliente, fitaLocacao);
		assertDoesNotThrow(() -> locacaoService.cadastrar(locacao));
	}
	
	@Test
	@Order(2)
	public void testAtualizarLocacao() {
		try {
			Locacao locacao = locacaoService.listarId(1L);
			assertNotNull(locacao);
			
			locacao.setLocacaoUid("CZAR_LOC_1245");
			assertEquals("CZAR_LOC_1245", locacao.getLocacaoUid());
			
			assertDoesNotThrow(() -> locacaoService.atualizar(locacao));
		} catch (Exception e) {}
	}
	
	@Test
	@Order(4)
	public void testRemoverLocacao() {
		try {
			Locacao locacao = locacaoService.listarId(1L);
			assertNotNull(locacao);
			
			assertDoesNotThrow(() -> locacaoService.remover(locacao));
		} catch (Exception e) {}
	}
	
	@Test
	@Order(3)
	public void testListarLocacao() {
		locacaoService.listar();
	}
	
	@AfterAll
	public void shutDown() {
		HibernateUtil.shutDown();
	}
} 
