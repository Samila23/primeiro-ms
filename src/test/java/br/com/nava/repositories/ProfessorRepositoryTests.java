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

import br.com.nava.entities.ProfessorEntity;

//@DataJpaTest
//permite manipular o banco de dados com rollback (desfazer uma operação)

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class ProfessorRepositoryTests {

	@Autowired
	private ProfessorRepository professorRepository;
	
	@Autowired
	private TestEntityManager testEntityManager;
	
	//__________________________________________ TESTE Mostrar 01 / / CRIAR NOVO / GETONE  _________________________________________________
	@Test
	void findByIdWhenFoundTest() {
		
		ProfessorEntity professorEntidade = createValidProfessor();
		
		// vai persistir a entidade no banco de dados para testar o findById
		// ao final do testes, esta entidade será deletada
		testEntityManager.persist(professorEntidade);
		
		// buscar a entidade no banco de dados para testar o findById
		
		// execução do findById
		Optional<ProfessorEntity> professor = professorRepository.findById( professorEntidade.getId() );
		
		// validando a respota - se o objeto encontrado não é nulo
		assertThat( professor ).isNotNull();
	}
	@Test
	void findByIdWhenNotFoundTest() {
		// buscar uma entidade na qual não existe no banco de dados
		Optional<ProfessorEntity> professor = professorRepository.findById(1);
		
		// temos que verificar se o opcional não possui valores, ou seja, isPresent() possui valor falso
		assertThat( professor.isPresent() ).isFalse();
	}
	
	//__________________________________________ TESTE Mostrar TODOS_________________________________________________
	@Test
	void findAllTest() {
		ProfessorEntity professorEntidade = createValidProfessor();
		
		// salvando temporariamente o professor no banco de dados
		testEntityManager.persist(professorEntidade);
		
		//execução		
		List<ProfessorEntity> professores = this.professorRepository.findAll();
		
		// verificar
		assertThat( professores.size() ).isEqualTo(1);
	}
	
	//__________________________________________ TESTE UPDATE _________________________________________________
	@Test
	void saveTest() {
		
		ProfessorEntity professorEntidade = createValidProfessor();
		
		// salvando temporariamente o professor no banco de dados
		testEntityManager.persist(professorEntidade);
		
		//execução
		ProfessorEntity professorSalvo = professorRepository.save(professorEntidade);
		
		//validação
		assertThat( professorSalvo.getId() ).isNotNull();
		assertThat( professorSalvo.getCep() ).isEqualTo( professorEntidade.getCep() );
		assertThat( professorSalvo.getNome() ).isEqualTo( professorEntidade.getNome() );
		assertThat( professorSalvo.getNumero() ).isEqualTo( professorEntidade.getNumero() );
	}
	//__________________________________________ TESTE DELETE _________________________________________________
	@Test
	void deleteById() {
		ProfessorEntity professorEntidade = createValidProfessor();
		
		// salvando temporariamente o professor no banco de dados
		ProfessorEntity professorTemporario = testEntityManager.persist(professorEntidade);
		
		//execução
		professorRepository.deleteById( professorTemporario.getId() );
		
		// verificação
		//busquei o professor deletado e comparei a resposta 
		
		Optional<ProfessorEntity> deletado = professorRepository.findById( professorTemporario.getId() );
		
		assertThat( deletado.isPresent() ).isFalse();
		
		
	}
	
	//__________________________________________ Setando os valores  _______________________________________
	private ProfessorEntity createValidProfessor() {
		
		// instanciando o novo objeto do tipo ProfessorEntity
		ProfessorEntity professorEntidade = new ProfessorEntity();
		
		// colocando valores nos atributos de ProfessorEntity
		professorEntidade.setCep("04567895");
		professorEntidade.setNome("Professor Teste");
		professorEntidade.setNumero(3);
		professorEntidade.setRua("Rua de Teste");
		//professorEntidade.setId(1);
		
		// retornando este novo objeto criado
		return professorEntidade;
	}
}