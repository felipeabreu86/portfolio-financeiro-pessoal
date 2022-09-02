package br.com.financeiro.portfolio.core.annotation.validator;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.financeiro.portfolio.core.annotation.ValidPassword;
import br.com.financeiro.portfolio.core.annotation.validator.password.PasswordValidation;

@Component
public class ValidPasswordValidator implements ConstraintValidator<ValidPassword, String> {
    
    @Autowired
    private List<PasswordValidation> validacoesSenha;
    
    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        Boolean isValid = true;
        
        for (PasswordValidation passwordValidation : validacoesSenha) {
            if (!passwordValidation.isValid(password)) {
                isValid = false;                
                context
                    .buildConstraintViolationWithTemplate(passwordValidation.getErrorMessage())
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            }
        }
        
        return isValid;
    }

}
