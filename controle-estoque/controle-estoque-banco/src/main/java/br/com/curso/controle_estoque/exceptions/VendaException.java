package br.com.curso.controle_estoque.exceptions;

public class VendaException extends RuntimeException {	

	private static final long serialVersionUID = -4292176176855073124L;

	public VendaException(String message) {
        super(message);
    }

}
