package br.com.financeiro.portfolio.exterior.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.financeiro.portfolio.domain.entity.Usuario;
import br.com.financeiro.portfolio.domain.service.UsuarioService;
import br.com.financeiro.portfolio.exterior.controller.dto.UsuarioDto;
import io.netty.util.internal.StringUtil;
import io.vavr.control.Either;

@Controller
@RequestMapping("/user")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

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
        model.addAttribute("usuarioDto", new UsuarioDto());
        return "forgot-password";
    }
    
    @PostMapping(value = "/forgot-password")
    public String recuperarSenha(final Model model, @ModelAttribute UsuarioDto usuarioDto) {

        if (usuarioDto != null && !StringUtil.isNullOrEmpty(usuarioDto.getNomeUsuario())) {
            // TODO: Implementar chamada ao serviço que realiza a recuperação da senha
        }

        model.addAttribute("usuarioDto", new UsuarioDto());

        return "forgot-password";
    }

}
