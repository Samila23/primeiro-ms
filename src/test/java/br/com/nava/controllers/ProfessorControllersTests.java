package br.com.nava.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.bind.annotation.PatchMapping;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.nava.dtos.ProfessorDTO;

@ExtendWith(SpringExtension.class)																			//Informa que é uma classe de testes 
@AutoConfigureMockMvc																						//Sempre que usar o MockMVC
@SpringBootTest
public class ProfessorControllersTests {
	@Autowired
	private MockMvc mockMvc;																				//Responsavel por criar requisições Web
	//__________________________________________ Teste da requisição do GetAll __________________________________________
	@Test																									//Responsável pelo teste do metodo
	void getAllTest() throws Exception {
																											//Arnazeba o objeto que fará o teste e colher o resultado
		ResultActions response = mockMvc.perform (
				get("/professores")
				.contentType("application/json")
				);
																											//Pegando o resultado via MvcResult
		MvcResult result = response.andReturn();
																											//Pegando o resultado no formato String
		String responseStr = result.getResponse().getContentAsString();
		System.out.println(responseStr);
		ObjectMapper mapper = new ObjectMapper();
		//Converte o resultado de String em um Array de objetos de ProfessorDTO
		ProfessorDTO[] lista = mapper.readValue(responseStr, ProfessorDTO[].class);
		//Verificando se a lista do professor DTO está vazia
		assertThat(lista).isNotEmpty();
	}
	//__________________________________________ Teste da requisição do GetOne __________________________________________
	@Test
	void getOneTest() throws Exception{
		ResultActions response = mockMvc.perform (
				get("/professores/1")
				.contentType("application/json")
				);
		MvcResult result = response.andReturn();
		String responseStr = result.getResponse().getContentAsString();
		
		System.out.println(responseStr);
		
		ObjectMapper mapper = new ObjectMapper();
		//Converte o resultado de String em um Array de objetos de ProfessorDTO
		ProfessorDTO professor = mapper.readValue(responseStr, ProfessorDTO.class);
		//Verificando se a lista do professor DTO está vazia
		assertThat(professor.getId()).isEqualTo(1);
		assertThat( result.getResponse().getStatus() ).isEqualTo( 200 );
	}
	//__________________________________________ Teste da requisição do Save __________________________________________
	@Test
	void saveTest() throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		
		// criamos um objeto do tipo ProfessorDTO para enviarmos junto com a requisição
		ProfessorDTO professor = new ProfessorDTO();
		professor.setCep("04567895");
		professor.setNome("Professor Teste");
		professor.setNumero(3);
		professor.setRua("Rua de Teste");
		
		// para enviar a requisição
		ResultActions response = mockMvc.perform(
				post("/professores")
				.content( mapper.writeValueAsString(professor) )
				.contentType("application/json")
			);

		// pegando o resultado via MvcResult
		MvcResult result = response.andReturn();
		// pegando o resultado no formato de String
		String responseStr = result.getResponse().getContentAsString();
		
		System.out.println(responseStr);
		
		ProfessorDTO professorSalvo = mapper.readValue(responseStr, ProfessorDTO.class);
		
		// verificar se foi salvo corretamente
		assertThat ( professorSalvo.getId() ).isPositive();
		assertThat( professorSalvo.getCep() ).isEqualTo( professor.getCep() );
		assertThat( professorSalvo.getNome() ).isEqualTo( professor.getNome() );
		assertThat( professorSalvo.getNumero() ).isEqualTo( professor.getNumero() );
		assertThat( professorSalvo.getRua() ).isEqualTo( professor.getRua() );
		
		assertThat( result.getResponse().getStatus() ).isEqualTo( 200 );
		
	}
	//__________________________________________ Teste da requisição do Updade __________________________________________
	@Test
	void UpdateTest() throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		
		// criamos um objeto do tipo ProfessorDTO para enviarmos junto com a requisição
		ProfessorDTO professor = new ProfessorDTO();
		professor.setCep("04567895");
		professor.setNome("Professor Teste");
		professor.setNumero(3);
		professor.setRua("Rua de Teste");
		
		// para enviar a requisição
		ResultActions response = mockMvc.perform(
				patch("/professores/1")
				.content( mapper.writeValueAsString(professor) )
				.contentType("application/json")
			);

		// pegando o resultado via MvcResult
		MvcResult result = response.andReturn();
		// pegando o resultado no formato de String
		String responseStr = result.getResponse().getContentAsString();
		
		System.out.println(responseStr);
		
		ProfessorDTO professorSalvo = mapper.readValue(responseStr, ProfessorDTO.class);
		
		// verificar se foi salvo corretamente
		assertThat ( professorSalvo.getId() ).isPositive();
		assertThat( professorSalvo.getCep() ).isEqualTo( professor.getCep() );
		assertThat( professorSalvo.getNome() ).isEqualTo( professor.getNome() );
		assertThat( professorSalvo.getNumero() ).isEqualTo( professor.getNumero() );
		assertThat( professorSalvo.getRua() ).isEqualTo( professor.getRua() );
		
		assertThat( result.getResponse().getStatus() ).isEqualTo( 200 );
		
	}
	//__________________________________________ Teste da requisição do Delete __________________________________________
	@Test
	void deleteTest() throws Exception {
		// para enviar a requisição
		ResultActions response = mockMvc.perform(
				delete("/professores/7")				
				.contentType("application/json")
			);
		// pegando o resultado via MvcResult
		MvcResult result = response.andReturn();
		assertThat( result.getResponse().getStatus() ).isEqualTo( 200 );
	}
}
