package br.com.financeiro.portfolio.core.annotation.validator.password;

import br.com.financeiro.portfolio.core.util.StringUtil;

public class SenhaNulaOuVaziaValidation implements PasswordValidation {

    @Override
    public Boolean isValid(String password) {
        return !StringUtil.isNullOrEmpty(password);
    }

    @Override
    public String getErrorMessage() {
        return "A senha é obrigatória.";
    }

}
