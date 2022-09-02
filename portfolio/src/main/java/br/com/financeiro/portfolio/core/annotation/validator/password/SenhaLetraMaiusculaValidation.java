package br.com.financeiro.portfolio.core.annotation.validator.password;

import br.com.financeiro.portfolio.core.util.StringUtil;

public class SenhaLetraMaiusculaValidation implements PasswordValidation {

    @Override
    public Boolean isValid(String password) {
        return StringUtil.containsUpperCase(password);
    }

    @Override
    public String getErrorMessage() {
        return "A senha deve conter ao menos uma letra maiúscula.";
    }

}
