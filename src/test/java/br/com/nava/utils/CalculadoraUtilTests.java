package br.com.nava.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)																						//Informa que é uma classe de testes 
public class CalculadoraUtilTests {
	private CalculadoraUtil calculadoraUtil = new CalculadoraUtil();
	@Test
	void somarTest() {
		int atual = calculadoraUtil.soma(5, 9);
		assertEquals(14, atual);
	}
}
