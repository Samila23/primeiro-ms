package br.com.nava.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.nava.dtos.EnderecoDTO;
import br.com.nava.entities.EnderecoEntity;
import br.com.nava.repositories.EnderecoRepository;

@ExtendWith(SpringExtension.class)																			//Informa que é uma classe de testes 
@AutoConfigureMockMvc																						//Sempre que usar o MockMVC
@SpringBootTest
public class EnderecoServiceTests {
	@Autowired
	private EnderecoService enderecoService;
	@MockBean																								// serve para sinalizar que iremos MOCKAR(SIMULAR) a camada repository
	private EnderecoRepository enderecoRepository;
	//__________________________________________ TESTE Mostrar TODOS_________________________________________________
	@Test
	void getAllTest() {
		// vamos criar uma lista de entidade de professor com o objeto
		// de retornar a mesma quando o professorRepository.findAll() 
		// for acionado
		
		List<EnderecoEntity> listaMockada = new ArrayList<EnderecoEntity>();
		
		EnderecoEntity enderecoEntidade = createValidEndereco();
		
		listaMockada.add(enderecoEntidade);
		
		// quando o repository for acionado, retorno a lista Mockada
		when( enderecoRepository.findAll() ).thenReturn( listaMockada );
		
		List<EnderecoDTO> retorno = enderecoService.getAll();
		
		// validar a resposta
		isEnderecoValid(retorno.get(0), listaMockada.get(0));
	}
	//__________________________________________ TESTE Mostrar 01 / / CRIAR NOVO / GETONE  _________________________________________________
	@Test
	void getOneWhenFindObjectTest() {
		EnderecoEntity enderecoEntidade = createValidEndereco();
		
		Optional<EnderecoEntity> optional = Optional.of(enderecoEntidade);
		
		when ( enderecoRepository.findById(1) ).thenReturn( optional );
		
		// execução
		EnderecoDTO obj = enderecoService.getOne(1);

		// validar a resposta
		isEnderecoValid(obj, enderecoEntidade);
	}
	
	@Test
	void getOneWhenNotFoundObjectTest() {
		// Optional.empty() -> simulando o caso de NÃO achar o registro no banco de dados
		Optional<EnderecoEntity> optional = Optional.empty();
		
		when ( enderecoRepository.findById(1) ).thenReturn( optional );
		// execução
		EnderecoDTO obj = enderecoService.getOne(1);
		// objeto com valores "padrão"
		EnderecoEntity enderecoEntidade = new EnderecoEntity();
		// validar a resposta
		isEnderecoValid(obj, enderecoEntidade);
	}
	//__________________________________________ TESTE Mostrar 01 / SALVAR  NOVO _______________________________________
	@Test
	void saveTest() {
		// 1-) Cenário
		//objeto com dados válidos de um professor
		EnderecoEntity enderecoEntidade = createValidEndereco();
		
		// quando o enderecoRepository.save for acionado, retornaremos um objeto de endeteco com dados válidos
		when( enderecoRepository.save(enderecoEntidade) ).thenReturn(enderecoEntidade);
		
		EnderecoDTO enderecoSalvo = enderecoService.save(enderecoEntidade);
		
		// validar a resposta
		isEnderecoValid(enderecoSalvo, enderecoEntidade);
	}
	
	//__________________________________________ TESTE UPDATE / 02 CONDIÇÕES IF e ELSE _______________________________________
	@Test
	void updateWhenFoundObj() {
		//Cenário
		
		EnderecoEntity enderecoEntidade = createValidEndereco();
		Optional<EnderecoEntity> optional = Optional.of(enderecoEntidade);
		
		//mocks
		when (enderecoRepository.findById( enderecoEntidade.getId() ) ).thenReturn(optional);
		when ( enderecoRepository.save(enderecoEntidade) ).thenReturn(enderecoEntidade);
		
		// execução
		EnderecoDTO enderecoAlterado = enderecoService.
					update(enderecoEntidade.getId(), enderecoEntidade);
		
		// validar a resposta
		isEnderecoValid(enderecoAlterado, enderecoEntidade);
	}
	@Test
	void updateWhenNotFoundObj() {
		
		
		// Cenário
		// Optional.empty() por conta que não achou o objeto a ser alterado
		Optional<EnderecoEntity> optional = Optional.empty();
		
		EnderecoEntity enderecoEntidade = createValidEndereco();
		
		// mocks
		when ( enderecoRepository.findById(1) ).thenReturn(optional);
		
		// execução
		// estamos passando como argumento o professorEntidade pois 
		// em suposição ele não estará no banco de dadaos neste cenário
		EnderecoDTO enderecoAlterado = enderecoService.
							update(1, enderecoEntidade );
		
		// validar a resposta
		isEnderecoValid(enderecoAlterado, new EnderecoEntity() );
	}
	
	//__________________________________________ Delete  _______________________________________
	@Test
	void deleteTest() {
		// execução
		// assertDoesNotThrow espera uma lambda (método sem nome) e verifica se a lambda executa sem erro
		assertDoesNotThrow( () -> enderecoService.delete(1) );
		
		// verifico se o enderecoRepository.deleteById foi executado somente uma vez 
		verify( enderecoRepository, times(1) ).deleteById(1);
	}

	//__________________________________________ Validações _______________________________________
	private void isEnderecoValid( EnderecoDTO obj, EnderecoEntity enderecoEntidade ) {

		assertThat( obj.getRua() ).isEqualTo( enderecoEntidade.getRua() );
		assertThat( obj.getNumero() ).isEqualTo( enderecoEntidade.getNumero() );
		assertThat( obj.getCep() ).isEqualTo( enderecoEntidade.getCep() );
		assertThat( obj.getCidade() ).isEqualTo( enderecoEntidade.getCidade() );
		assertThat( obj.getEstado() ).isEqualTo( enderecoEntidade.getEstado() );
		assertThat( obj.getId() ).isEqualTo( enderecoEntidade.getId() );
	}	
	//__________________________________________ Setando os valores  _______________________________________
	private EnderecoEntity createValidEndereco() {
		EnderecoEntity enderecoEntidade = new EnderecoEntity();
		enderecoEntidade.setRua("Rua teste");
		enderecoEntidade.setNumero(111);
		enderecoEntidade.setCep("11111-111");
		enderecoEntidade.setCidade("Cidade Teste");
		enderecoEntidade.setEstado("PA");
		enderecoEntidade.setId(1);
		
		return enderecoEntidade;
	}
}
