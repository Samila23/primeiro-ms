package br.com.nava.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;

import br.com.nava.entities.ProfessorEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 																											//Inclui todos os Gte e Set
@AllArgsConstructor 																							//Inclui os construtores com todos os paramentros
@NoArgsConstructor 																								//Incluir o contrutor padrão
public class ProfessorDTO {

	private int id;
	@NotEmpty(message = "Preenchimento Obrigatório")															//Esse campo não pode ser preenchido em branco
	@NotNull(message = "Preenchimento Obrigatório")																//Esse campo não pode ser preenchido em nulo
	@Length(min = 3 , max = 80 , message = "O númeo de caractere deve ser entre 3 e 80")						//Especifica a quantidade de caracteres
	@Pattern( regexp = "^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]*$", message = "É valido apenas caracteres")	//Expressão regular para não aceitar numeros
	private String nome;
	//private String cpf;
	private String rua;
	private String cep;
	private int numero;
	
	
	//__________________________________________ Conversão da ProfessorDTO para ProfessorEntity ____________________________________________
	public ProfessorEntity toEntity() {											// Ele irá converter apenas os ATRIBUTOS em COMUM das CLASSES
		ModelMapper mapper = new ModelMapper();
		return mapper.map(this, ProfessorEntity.class);
	}
}
