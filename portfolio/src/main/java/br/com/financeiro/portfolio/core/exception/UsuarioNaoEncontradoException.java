package br.com.financeiro.portfolio.core.exception;

public class UsuarioNaoEncontradoException extends Exception {

    private static final long serialVersionUID = 1L;

    public UsuarioNaoEncontradoException(String errorMessage) {
        super(errorMessage);
    }

    public UsuarioNaoEncontradoException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

}