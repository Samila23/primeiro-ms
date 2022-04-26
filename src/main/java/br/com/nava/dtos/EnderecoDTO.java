package br.com.nava.dtos;

import org.modelmapper.ModelMapper;

import br.com.nava.entities.EnderecoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 																						//Inclui todos os Gte e Set
@AllArgsConstructor 																		//Inclui os construtores com todos os paramentros
@NoArgsConstructor 																			//Incluir o contrutores padrão
public class EnderecoDTO {
	private Integer id;
	private String rua;
	private int numero;
	private String cep;
	private String cidade;
	private String estado;
	
	//__________________________________________ Conversão da EnderecoTDO para EnderecoEntity ____________________________________________
	public EnderecoEntity toEntity() {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(this, EnderecoEntity.class);
	}
}
