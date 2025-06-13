package com.locadora.project;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import com.locadora.project.model.Cliente;
import com.locadora.project.services.ClienteService;

import com.locadora.project.util.HibernateUtil;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ClienteTest {
	private ClienteService clienteService;
	
	@BeforeAll
	public void setupSessionFactory() {
		HibernateUtil.buildSessionFactory("hibernate.cfg.test.xml");
	}
	
	@BeforeAll
	public void initializeServices() {
		clienteService = new ClienteService(HibernateUtil.getSessionFactory());
	}
	
	@Test
	@Order(1)
	public void testCadastrarCliente() {
		Cliente cliente = new Cliente("Marcelo", 34, "000.000.000-00", "marcelo.test@email.com", "+5579912344321", "Rua 1");
		
		assertDoesNotThrow(() -> clienteService.cadastrar(cliente));
	}
	
	@Test
	@Order(2)
	public void testAtualizarCliente() {
		try {
			Cliente cliente = clienteService.listarId(1L);
			assertNotNull(cliente);
			
			cliente.setNome("Paulo");
			assertEquals("Paulo", cliente.getNome());
			
			cliente.setIdade(20);
			assertEquals(20, cliente.getIdade());
			
			cliente.setCPF("123.456.789-00");
			assertEquals("123.456.789-00", cliente.getCPF());
			
			cliente.setEmail("paulo.teste@email.com");
			assertEquals("paulo.teste@email.com", cliente.getEmail());
			
			cliente.setTelefone("+5579987655678");
			assertEquals("+5579987655678", cliente.getTelefone());
			
			cliente.setEndereco("Rua 2");
			assertEquals("Rua 2", cliente.getEndereco());
			
			assertDoesNotThrow(() -> clienteService.atualizar(cliente));
		} catch (Exception e) {}
	}
	
	@Test
	@Order(7)
	public void testRemoverCliente() {
		try {
			Cliente cliente = clienteService.listarId(1L);
			assertNotNull(cliente);
			assertDoesNotThrow(() -> clienteService.remover(cliente));
		} catch (Exception e) {}
	}
	
	@Test
	@Order(3)
	public void testListarCliente() {
		clienteService.listar();
	}
	
	@Test
	@Order(4)
	public void testBuscarClientePorCPF() {
		try {
			Cliente cliente = clienteService.getClientePorCPF("123.456.789-00");
			assertNotNull(cliente);
		} catch (Exception e) {}
	}
	
	@Test
	@Order(5)
	public void testBuscarClienteHistorico() {
		assertNotNull(clienteService.getClienteHistorico(1L));
	}
	
	@Test
	@Order(6)
	public void testFiltroClienteListagem() {
		assertNotNull(clienteService.getClienteFiltro(new Cliente("Marcelo", 34, "", "marcelo.test@email.com", "+5579912344321", "")));
	}
	
	@AfterAll
	public void shutDown() {
		HibernateUtil.shutDown();
	}
}