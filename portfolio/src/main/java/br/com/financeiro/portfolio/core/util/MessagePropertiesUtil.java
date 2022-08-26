package br.com.financeiro.portfolio.core.util;

import java.util.Locale;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class MessagePropertiesUtil {

    @Autowired
    private MessageSource messageSource;

    private Locale locale = new Locale("pt", "BR");

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = Objects.requireNonNull(locale);
    }

    public String obterMensagemPor(String codigoRecurso) {
        return messageSource.getMessage(codigoRecurso, null, locale);
    }

    public String obterMensagemPor(String codigoRecurso, Object... args) {
        return messageSource.getMessage(codigoRecurso, args, locale);
    }

}