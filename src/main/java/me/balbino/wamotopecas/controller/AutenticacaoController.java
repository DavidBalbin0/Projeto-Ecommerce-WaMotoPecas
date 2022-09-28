package me.balbino.wamotopecas.controller;

import me.balbino.wamotopecas.dto.LoginForm;
import me.balbino.wamotopecas.dto.TokenDto;
import me.balbino.wamotopecas.model.Produto;
import me.balbino.wamotopecas.service.MeTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.token.TokenService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private MeTokenService tokenService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/auth")@ResponseBody
    public TokenDto autenticar(@RequestBody LoginForm form){
        UsernamePasswordAuthenticationToken dadosLogin = form.converter();
        try{
            Authentication authentication = authManager.authenticate(dadosLogin);
            String token = tokenService.gerarToken(authentication);
            return new TokenDto(token, "Bearer");
        } catch (AuthenticationException e){
            return null;
        }

    }
}
