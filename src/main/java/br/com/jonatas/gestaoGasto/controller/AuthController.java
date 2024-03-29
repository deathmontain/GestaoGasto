package br.com.jonatas.gestaoGasto.controller;

import br.com.jonatas.gestaoGasto.config.security.ServicoDeToken;
import br.com.jonatas.gestaoGasto.controller.dto.TokenDTO;
import br.com.jonatas.gestaoGasto.controller.form.AuthForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private ServicoDeToken servicoDeToken;

    @PostMapping("sistema/login")
    public ResponseEntity<TokenDTO> sistemaLogin(@RequestBody AuthForm form) {
        UsernamePasswordAuthenticationToken dadosLogin = form.ciarAutenticacaoSistema();
        try {
            Authentication authentication = authManager.authenticate(dadosLogin);
            String token = servicoDeToken.gerarToken(authentication, "SISTEMA");
            return ResponseEntity.ok(new TokenDTO(token, "Bearer"));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<TokenDTO> login(@RequestBody AuthForm form) {
        UsernamePasswordAuthenticationToken dadosLogin = form.criarAutenticacaoUsuario();
        try {
            Authentication authentication = authManager.authenticate(dadosLogin);
            String token = servicoDeToken.gerarToken(authentication, "USUARIO");
            return ResponseEntity.ok(new TokenDTO(token, "Bearer"));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}