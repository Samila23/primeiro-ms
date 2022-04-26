package br.com.nava.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import br.com.nava.dtos.ProfessorDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data 																			//Inclui todos os Gte e Set
@AllArgsConstructor 															//Inclui os construtores com todos os paramentros
@NoArgsConstructor 																//Incluir o contrutores padrão
@Entity 																		//@Entity é o mapeamento entre a classe e tabela
@Table(name = "PROFESSORES")													//@Table passa o nome da tabela do banco que receberá os dados
public class ProfessorEntity {

																				// id , nome, cpf , rua, cep, numero
	
	@Id 																		// Informa qual é a chave primaria
	@GeneratedValue (strategy = GenerationType.IDENTITY) 						// Avisa que o Banco irá gerar o ID e como será gerado o cod ID
	private int id;
	private String nome;
	private String cpf;
	private String rua;
	private String cep;
	private int numero;
	private int dados;
	private int dadosss;
	private int dadosssasas;
	private double mdsjndd;
	private int dadjwncvv;
	//__________________________________________ Conversão da ProfessorEntity para ProfessorDTO____________________________________________
	public ProfessorDTO toDTO() {												// Ele irá converter apenas os ATRIBUTOS em COMUM das CLASSES
		ModelMapper mapper = new ModelMapper();
		ProfessorDTO dto = mapper.map(this, ProfessorDTO.class);
		return dto;
	}
}
