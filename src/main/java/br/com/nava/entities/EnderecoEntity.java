package br.com.nava.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.nava.dtos.EnderecoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data 																		//Inclui todos os Gte e Set

@AllArgsConstructor 														//Inclui os construtores com todos os paramentros

@NoArgsConstructor 															//Incluir o contrutores padrão

@Entity 																	//@Entity é o mapeamento entre a classe e tabela

@Table(name="ENDERECOS") 													//@Table passa o nome da tabela do banco que receberá os dados , caso nao ele procura o nome da tabela com o nome da classe
public class EnderecoEntity {
	@Id																		// especifica qual é a chave primaria independente da descrição da chave primaria 
	@GeneratedValue (strategy = GenerationType.IDENTITY) 					// Avisa que o Banco irá gerar o ID e como será gerado o cod ID
	private Integer id;
	private String rua;
	private int numero;
	private String cep;
	private String cidade;
	private String estado;
	
	
	
	//__________________________________________Relacionamento de classes USUARIO E ENDEREÇO___________________________________	
	@JsonIgnore
	@ToString.Exclude //Está excluindo do ToString padrão o UsuarioEntity
	//mappedBy : poe o nome do atributo da classe Java que mapeia o endereço(USUARIO) na EnderecoEntity
	@OneToOne(mappedBy = "endereco")// Indica o tipo de mapeamento, indica que terá um relacionamento 01 para 01, com a classe UsuarioEntity
	private UsuarioEntity usuario;
	
	//__________________________________________ Conversão da EnderecoEntity para EnderecoDTO____________________________________________
	public EnderecoDTO toTDO() {
		ModelMapper mapper = new ModelMapper();
		EnderecoDTO dto = mapper.map(this, EnderecoDTO.class);
		return dto;
	}
}
