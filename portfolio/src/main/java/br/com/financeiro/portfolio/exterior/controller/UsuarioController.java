package br.com.financeiro.portfolio.exterior.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.financeiro.portfolio.domain.entity.PasswordResetToken;
import br.com.financeiro.portfolio.domain.entity.Usuario;
import br.com.financeiro.portfolio.domain.service.EmailService;
import br.com.financeiro.portfolio.domain.service.UsuarioService;
import br.com.financeiro.portfolio.exterior.controller.dto.AlterarSenhaDto;
import br.com.financeiro.portfolio.exterior.controller.dto.RecuperarSenhaDto;
import br.com.financeiro.portfolio.exterior.controller.dto.UsuarioDto;
import io.vavr.control.Either;

@Controller
@RequestMapping("/user")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private EmailService emailService;

    @GetMapping(value = "/register-user")
    public String paginaRegistrarNovoUsuario(final Model model) {
        model.addAttribute("usuarioDto", new UsuarioDto());
        return "register";
    }

    @PostMapping(value = "/register-user")
    public String registrarNovoUsuario(final Model model, @ModelAttribute @Valid UsuarioDto usuarioDto, BindingResult bindingResult) {

        if (!bindingResult.hasErrors()) {            
            usuarioService
                .criarNovo(usuarioDto.toUsuario())
                .fold(
                    left -> model.addAttribute("usuarioCadastrado", false).addAttribute("errorMessage", left.getMessage()),
                    right -> model.addAttribute("usuarioCadastrado", true).addAttribute("usuarioDto", new UsuarioDto()));
        }

        return "register";
    }
    
    @GetMapping(value = "/forgot-password")
    public String paginaRecuperarSenha(final Model model) {
        model.addAttribute("recuperarSenhaDto", new RecuperarSenhaDto());
        return "forgot-password";
    }
    
    @PostMapping(value = "/forgot-password")
    public String recuperarSenha(final HttpServletRequest request, final Model model, @ModelAttribute @Valid RecuperarSenhaDto recuperarSenhaDto, BindingResult bindingResult) {

        if (!bindingResult.hasErrors()) {
            Either<Exception, Usuario> userResult = usuarioService.obterUsuarioPelo(recuperarSenhaDto.getEmail());
            
            if (userResult.isRight()) {                
                Either<Exception, PasswordResetToken> tokenResult = usuarioService.criarTokenDeRecuperacaoDeSenha(userResult.get());
                
                if (tokenResult.isRight()) {                    
                    emailService.enviarEmailRecuperacaoSenha(request, tokenResult.get().getToken(), userResult.get())
                        .fold(
                            left -> model.addAttribute("errorMessage", "Erro ao enviar o e-mail de recuperação. Se o erro persistir, favor entrar em contato."), 
                            right -> model.addAttribute("recuperarSenhaDto", new RecuperarSenhaDto()).addAttribute("forgotPasswordMessage", "E-mail de recuperação de senha enviado com sucesso."));
                }
            }
        }

        return "forgot-password";
    }
    
    @GetMapping(value = "/change-password")
    public String recuperarSenha(final Model model, @RequestParam("user") String user,
            @RequestParam("token") String token) {
        
        String view = "";
        Either<Exception, PasswordResetToken> tokenResult = usuarioService.validarTokenDeRecuperacaoDeSenha(user, token);

        if (tokenResult.isRight()) {
            model.addAttribute("alterarSenhaDto", new AlterarSenhaDto(user, token));
            view = "change-password";
        } else {
            model
                .addAttribute("recuperarSenhaDto", new RecuperarSenhaDto())
                .addAttribute("errorMessage", tokenResult.getLeft().getMessage());
            view = "forgot-password";
        }

        return view;
    }
    
    @PostMapping(value = "/change-password")
    public String salvarSenha(final Model model, @ModelAttribute @Valid AlterarSenhaDto alterarSenhaDto,
            BindingResult bindingResult) {
        
        String view = "change-password";

        // Validação dos itens do formulário
        if (!bindingResult.hasErrors()) {
            Either<Exception, PasswordResetToken> tokenResult = usuarioService
                    .validarTokenDeRecuperacaoDeSenha(alterarSenhaDto.getEmail(), alterarSenhaDto.getToken());

            // Validação do Token
            if (tokenResult.isRight()) {
                Either<Exception, Usuario> usuarioResult = usuarioService.obterUsuarioPelo(alterarSenhaDto.getEmail());

                // Validação do usuário
                if (usuarioResult.isRight()) {
                    Usuario usuario = usuarioResult.get();
                    usuario.setSenha(alterarSenhaDto.getSenha());

                    // Validação da atualização da senha do usuário
                    if (usuarioService.atualizar(usuario).isRight()) {
                        view = "redirect:/login";
                    }
                }
            }

            // Adicionar mensagem de erro ao usuário em caso de erro durante a atualização da senha
            if (view.equals("change-password")) {
                model.addAttribute("errorMessage", "Houve um erro ao se tentar atualizar a senha.");
            }
        }

        return view;
    }

}
