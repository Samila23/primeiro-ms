package br.com.nava.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.nava.dtos.EnderecoDTO;

@ExtendWith(SpringExtension.class)																			//Informa que é uma classe de testes 
@AutoConfigureMockMvc																						//Sempre que usar o MockMVC
@SpringBootTest
public class EnderecoControllersTest {
	@Autowired
	private MockMvc mockMvc;	
	//__________________________________________ Teste da requisição do GetAll __________________________________________
	@Test																									//Responsável pelo teste do metodo
	void getAllTest() throws Exception {
																											//Arnazeba o objeto que fará o teste e colher o resultado
		ResultActions response = mockMvc.perform (
				get("/enderecos")
				.contentType("application/json")
				);
																											//Pegando o resultado via MvcResult
		MvcResult result = response.andReturn();
																											//Pegando o resultado no formato String
		String responseStr = result.getResponse().getContentAsString();
		System.out.println(responseStr);
		ObjectMapper mapper = new ObjectMapper();
		//Converte o resultado de String em um Array de objetos de EnderecoDTO
		EnderecoDTO[] lista = mapper.readValue(responseStr, EnderecoDTO[].class);
		//Verificando se a lista do endereco DTO está vazia
		assertThat(lista).isNotEmpty();
	}
	//__________________________________________ Teste da requisição do GetOne __________________________________________
	@Test
	void getOneTest() throws Exception{
		ResultActions response = mockMvc.perform (
				get("/enderecos/1")
				.contentType("application/json")
				);
		MvcResult result = response.andReturn();
		String responseStr = result.getResponse().getContentAsString();
		
		System.out.println(responseStr);
		
		ObjectMapper mapper = new ObjectMapper();
		//Converte o resultado de String em um Array de objetos de EnderecoDTO
		EnderecoDTO endereco = mapper.readValue(responseStr, EnderecoDTO.class);
		//Verificando se a lista do EnderecoDTO  está vazia
		assertThat(endereco.getId()).isEqualTo(1);
		assertThat( result.getResponse().getStatus() ).isEqualTo( 200 );
	}
	//__________________________________________ Teste da requisição do Save __________________________________________
	@Test
	void saveTest() throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		
		// criamos um objeto do tipo EnderecoDTO para enviarmos junto com a requisição
		EnderecoDTO endereco = new EnderecoDTO();
		endereco.setRua("Rua teste");
		endereco.setNumero(111);
		endereco.setCep("11111-111");
		endereco.setCidade("Cidade Teste");
		endereco.setEstado("PA");
		// para enviar a requisição
		ResultActions response = mockMvc.perform(
				post("/enderecos")
				.content( mapper.writeValueAsString(endereco) )
				.contentType("application/json")
			);
		// pegando o resultado via MvcResult
		MvcResult result = response.andReturn();
		// pegando o resultado no formato de String
		String responseStr = result.getResponse().getContentAsString();
		
		System.out.println(responseStr);
		
		EnderecoDTO enderecoSalvo = mapper.readValue(responseStr, EnderecoDTO.class);
		
		// verificar se foi salvo corretamente
		assertThat ( enderecoSalvo.getId() ).isPositive();
		assertThat( enderecoSalvo.getRua() ).isEqualTo( endereco.getRua() );
		assertThat( enderecoSalvo.getNumero() ).isEqualTo( endereco.getNumero() );
		assertThat( enderecoSalvo.getCep() ).isEqualTo( endereco.getCep() );
		assertThat( enderecoSalvo.getCidade() ).isEqualTo( endereco.getCidade() );
		assertThat( enderecoSalvo.getEstado() ).isEqualTo( endereco.getEstado() );
		
		assertThat( result.getResponse().getStatus() ).isEqualTo( 200 );
	}
	//__________________________________________ Teste da requisição do Updade __________________________________________
	@Test
	void UpdateTest() throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		
		// criamos um objeto do tipo EnderecoDTO para enviarmos junto com a requisição
		EnderecoDTO endereco = new EnderecoDTO();
		endereco.setRua("Rua teste");
		endereco.setNumero(111);
		endereco.setCep("11111-111");
		endereco.setCidade("Cidade Teste");
		endereco.setEstado("PA");
		
		// para enviar a requisição
		ResultActions response = mockMvc.perform(
				patch("/enderecos/1")
				.content( mapper.writeValueAsString(endereco) )
				.contentType("application/json")
			);

		// pegando o resultado via MvcResult
		MvcResult result = response.andReturn();
		// pegando o resultado no formato de String
		String responseStr = result.getResponse().getContentAsString();
		
		System.out.println(responseStr);
		
		EnderecoDTO enderecoSalvo = mapper.readValue(responseStr, EnderecoDTO.class);
		
		// verificar se foi salvo corretamente
		assertThat ( enderecoSalvo.getId() ).isPositive();
		assertThat( enderecoSalvo.getRua() ).isEqualTo( endereco.getRua() );
		assertThat( enderecoSalvo.getNumero() ).isEqualTo( endereco.getNumero() );
		assertThat( enderecoSalvo.getCep() ).isEqualTo( endereco.getCep() );
		assertThat( enderecoSalvo.getCidade() ).isEqualTo( endereco.getCidade() );
		assertThat( enderecoSalvo.getEstado() ).isEqualTo( endereco.getEstado() );
		
		assertThat( result.getResponse().getStatus() ).isEqualTo( 200 );
		
	}
	//__________________________________________ Teste da requisição do Delete __________________________________________
	@Test
	void deleteTest() throws Exception {
		// para enviar a requisição
		ResultActions response = mockMvc.perform(
				delete("/enderecos/7")				
				.contentType("application/json")
			);
		// pegando o resultado via MvcResult
		MvcResult result = response.andReturn();
		assertThat( result.getResponse().getStatus() ).isEqualTo( 200 );
	}
}
