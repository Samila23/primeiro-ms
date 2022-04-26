package br.com.nava.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 																			//Inclui todos os Gte e Set
@AllArgsConstructor 															//Inclui os construtores com todos os paramentros
@NoArgsConstructor 																//Incluir o contrutores padrão
//__________________________________________ Mostrará apenas os erros, que desejo mostrar nas requisições ____________________________________________
public class StandardError {													
	private Long timestamp;
	private Integer status;
	private String error;
	private String message;
	private String path;
}
