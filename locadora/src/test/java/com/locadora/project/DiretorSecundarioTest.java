package com.locadora.project;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import com.locadora.project.model.DiretorSecundario;
import com.locadora.project.services.DiretorSecundarioService;

import com.locadora.project.util.HibernateUtil;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DiretorSecundarioTest {
	private DiretorSecundarioService diretorSecundarioService;
	
	@BeforeAll
	public void setupSessionFactory() {
		HibernateUtil.buildSessionFactory("hibernate.cfg.test.xml");
	}
	
	@BeforeAll
	public void initializeServices() {
		diretorSecundarioService = new DiretorSecundarioService(HibernateUtil.getSessionFactory());
	}
	
	@Test
	@Order(1)
	public void testCadastrarDiretorSecundario() {
		DiretorSecundario diretorSecundario = new DiretorSecundario("Vladimir", "Diretor Assistente");
		
		assertDoesNotThrow(() -> diretorSecundarioService.cadastrar(diretorSecundario));
	}
	
	@Test
	@Order(2)
	public void testAtualizarDiretorSecundario() {
		try {
			DiretorSecundario diretorSecundario = diretorSecundarioService.listarId(1L);
			assertNotNull(diretorSecundario);
			
			diretorSecundario.setNome("Anatoly");
			assertEquals("Anatoly", diretorSecundario.getNome());
			
			diretorSecundario.setCargo("Diretor de Elenco");
			assertEquals("Diretor de Elenco", diretorSecundario.getCargo());
			
			assertDoesNotThrow(() -> diretorSecundarioService.atualizar(diretorSecundario));
		} catch (Exception e) {}
	}
	
	@Test
	@Order(4)
	public void testRemoverDiretorSecundario() {
		try {
			DiretorSecundario diretorSecundario = diretorSecundarioService.listarId(1L);
			assertNotNull(diretorSecundario);
			
			assertDoesNotThrow(() -> diretorSecundarioService.remover(diretorSecundario));
		} catch (Exception e) {}
	}
	
	@Test
	@Order(3)
	public void testListarDiretorSecundario() {
		diretorSecundarioService.listar();
	}
}