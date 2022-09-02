package br.com.financeiro.portfolio.core.annotation.validator.password;

public class SenhaTamanhoPermitido implements PasswordValidation {

    @Override
    public Boolean isValid(String password) {
        int tamanho = password.length();
        return (tamanho < 8 || tamanho > 16)
            ? false
            : true;
    }

    @Override
    public String getErrorMessage() {
        return "A senha deve conter entre 8 e 16 dígitos.";
    }

}
