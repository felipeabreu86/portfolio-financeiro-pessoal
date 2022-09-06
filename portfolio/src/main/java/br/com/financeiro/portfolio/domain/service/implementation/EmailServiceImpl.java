package br.com.financeiro.portfolio.domain.service.implementation;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import br.com.financeiro.portfolio.domain.entity.Usuario;
import br.com.financeiro.portfolio.domain.service.EmailService;
import io.vavr.control.Either;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public Either<Exception, Boolean> enviarEmailRecuperacaoSenha(final HttpServletRequest request, final String token,
            final Usuario usuario) {
        
        try {
            final String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
            mailSender.send(montarEmailDeRecuperacaoSenha(appUrl, token, usuario));
            return Either.right(true);
        } catch (Exception e) {
            return Either.left(e);
        }
    }

    /**
     * 
     * @param contextPath
     * @param token
     * @param user
     * @return
     * @throws MessagingException
     */
    private MimeMessage montarEmailDeRecuperacaoSenha(final String contextPath, final String token, final Usuario user)
            throws MessagingException {
        
        final String url = contextPath + "/user/change-password?email=" + user.getNomeUsuario() + "&token=" + token;

        String dataHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
                .format(Calendar.getInstance().getTime())
                .toString();

        final String bodyMessage = new StringBuilder()
                .append("Olá, <b>" + String.format("%s %s", user.getNome(), user.getSobrenome()) + "</b>.<br/><br/>")
                .append("Recebemos uma solicitação para restaurar sua senha de acesso em nosso site.<br/>")
                .append("Ela ocorreu em: <b>" + dataHora + "</b>.<br/><br/>")
                .append("Se você reconhece essa ação, clique no link abaixo para prosseguir:<br/><br/>")
                .append("<a href='" + url + "'>REDEFINIR SENHA</a><br/><br/>")
                .append("Atenciosamente,<br/>")
                .append("<b>Equipe Portfólio Financeiro</b>")
                .toString();

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        mimeMessage.setSubject("[Portfólio Financeiro] Recuperação de Senha");
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        helper.setTo(user.getNomeUsuario());
        helper.setText(bodyMessage, true);

        return mimeMessage;
    }

}
