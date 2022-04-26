package br.com.nava.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.nava.entities.EnderecoEntity;

//@DataJpaTest
//permite manipular o banco de dados com rollback (desfazer uma operação)

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class EnderecoRepositoryTests {
	@Autowired
	EnderecoRepository enderecoRepository;
	
	@Autowired
	private TestEntityManager testEntityManager;
	
	//__________________________________________ TESTE Mostrar 01 / / CRIAR NOVO / GETONE  _________________________________________________
	@Test
	void findByIdWhenFoundTest() {
		EnderecoEntity enderecoEntidade = createValidEndereco();
		
		// vai persistir a entidade no banco de dados para testar o findById
		// ao final do testes, esta entidade será deletada
		testEntityManager.persist(enderecoEntidade);
		// buscar a entidade no banco de dados para testar o findById
		
		// execução do findById
		Optional<EnderecoEntity> endereco = enderecoRepository.findById(enderecoEntidade.getId());
		
		// validando a respota - se o objeto encontrado não é nulo
		assertThat(endereco).isNotNull();
	}
	@Test
	void findByIdWhenNotFoundTest() {
		// buscar uma entidade na qual não existe no banco de dados
		Optional<EnderecoEntity> endereco = enderecoRepository.findById(1);
		
		// temos que verificar se o opcional não possui valores, ou seja, isPresent() possui valor falso
		assertThat( endereco.isPresent() ).isFalse();
	}
	//__________________________________________ TESTE Mostrar TODOS_________________________________________________
	@Test
	void findAllTest() {
		EnderecoEntity enderecoEntidade = createValidEndereco();
		// salvando temporariamente o professor no banco de dados
		testEntityManager.persist(enderecoEntidade);
		//execução	
		List<EnderecoEntity> enderecos = this.enderecoRepository.findAll();
		// verificar
		assertThat( enderecos.size() ).isEqualTo(1);
	}
	//__________________________________________ TESTE UPDATE _________________________________________________
	
	@Test
	void saveTest() {
		EnderecoEntity enderecoEntidade = createValidEndereco();
		// salvando temporariamente o professor no banco de dados
		testEntityManager.persist(enderecoEntidade);
		//execução
		EnderecoEntity enderecoSalvo = enderecoRepository.save(enderecoEntidade);
		//Validação
		assertThat( enderecoSalvo.getId() ).isNotNull();
		assertThat( enderecoSalvo.getRua() ).isEqualTo( enderecoEntidade.getRua() );
		assertThat( enderecoSalvo.getNumero() ).isEqualTo( enderecoEntidade.getNumero() );
		assertThat( enderecoSalvo.getCep() ).isEqualTo( enderecoEntidade.getCep() );
		assertThat( enderecoSalvo.getCidade() ).isEqualTo( enderecoEntidade.getCidade() );
		assertThat( enderecoSalvo.getEstado() ).isEqualTo( enderecoEntidade.getEstado() );
	}
	//__________________________________________ TESTE DELETE _________________________________________________
	@Test
	void deleteById() {
		EnderecoEntity enderecoEntidade = createValidEndereco();
		// salvando temporariamente o professor no banco de dados
		EnderecoEntity enderecoTemporario = testEntityManager.persist(enderecoEntidade);
		//execução
		enderecoRepository.deleteById(enderecoTemporario.getId());
		// verificação
		//busquei o professor deletado e comparei a resposta 
		Optional<EnderecoEntity> deletado = enderecoRepository.findById(enderecoTemporario.getId());
		assertThat( deletado.isPresent() ).isFalse();
	}
	//__________________________________________ Setando os valores  _______________________________________
	private EnderecoEntity createValidEndereco() {
		// instanciando o novo objeto do tipo EnderecoEntity
		EnderecoEntity enderecoEntidade = new EnderecoEntity();
		
		// colocando valores nos atributos de ProfessorEntity
		enderecoEntidade.setRua("Rua teste");
		enderecoEntidade.setNumero(111);
		enderecoEntidade.setCep("11111-111");
		enderecoEntidade.setCidade("Cidade Teste");
		enderecoEntidade.setEstado("PA");
		
		// retornando este novo objeto criado
		return enderecoEntidade;
	}
}
