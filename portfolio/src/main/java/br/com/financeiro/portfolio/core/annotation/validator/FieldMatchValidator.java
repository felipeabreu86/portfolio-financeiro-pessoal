package br.com.financeiro.portfolio.core.annotation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;

import br.com.financeiro.portfolio.core.annotation.FieldMatch;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {

    private String firstFieldName;
    private String secondFieldName;
    private String message;

    @Override
    public void initialize(final FieldMatch constraintAnnotation) {
        this.firstFieldName = constraintAnnotation.first();
        this.secondFieldName = constraintAnnotation.second();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        boolean valid = true;
        
        try {
            final Object firstObj = BeanUtils.getProperty(value, this.firstFieldName);
            final Object secondObj = BeanUtils.getProperty(value, this.secondFieldName);
            valid = firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj);
        } catch (final Exception ignore) {
            valid = false;
        }

        if (!valid) {
            context
                .buildConstraintViolationWithTemplate(this.message)
                .addPropertyNode(this.firstFieldName)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        }

        return valid;
    }
}