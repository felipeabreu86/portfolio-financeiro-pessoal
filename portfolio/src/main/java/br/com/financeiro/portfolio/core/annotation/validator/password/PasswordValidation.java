package br.com.financeiro.portfolio.core.annotation.validator.password;

public interface PasswordValidation {

    Boolean isValid(String password);

    String getErrorMessage();

}
