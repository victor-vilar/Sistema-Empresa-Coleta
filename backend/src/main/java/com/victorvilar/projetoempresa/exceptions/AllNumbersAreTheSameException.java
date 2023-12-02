package com.victorvilar.projetoempresa.exceptions;

/**
 * Exception created to be thrown if all numbers of a cpf or cnpj are the same
 * @author Victor Vilar
 */
public class AllNumbersAreTheSameException extends RuntimeException {

		public AllNumbersAreTheSameException(String errorMessage) {
			super(errorMessage);
		}
}
