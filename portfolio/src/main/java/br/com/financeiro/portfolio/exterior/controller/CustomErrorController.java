package br.com.financeiro.portfolio.exterior.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;

import br.com.financeiro.portfolio.core.type.ErroHttpType;
import br.com.financeiro.portfolio.core.util.MessageUtil;

@Controller
public class CustomErrorController implements ErrorController {

    @Autowired
    private ErrorAttributes errorAttributes;

    @Autowired
    private MessageUtil messageUtil;

    @RequestMapping("/error")
    public String handleError(final HttpServletRequest request) {
        
        Map<String, Object> attrs = errorAttributes
                .getErrorAttributes(new ServletWebRequest(request), ErrorAttributeOptions.defaults());

        // get error attributes
        String status = attrs.getOrDefault("status", "").toString();

        // send status and error description to error.html through request attributes
        request.setAttribute("status", status);
        request.setAttribute("errordescription", ErroHttpType.obterMensagemPor(status, messageUtil));

        // display generic page error
        return "error";
    }

    @GetMapping("/favicon.ico")
    @ResponseBody
    public void returnNoFavicon() {
    }

}