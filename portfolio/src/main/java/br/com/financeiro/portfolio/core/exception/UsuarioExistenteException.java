package br.com.financeiro.portfolio.core.exception;

public class UsuarioExistenteException extends Exception {

    private static final long serialVersionUID = 1L;

    public UsuarioExistenteException(String errorMessage) {
        super(errorMessage);
    }

    public UsuarioExistenteException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

}