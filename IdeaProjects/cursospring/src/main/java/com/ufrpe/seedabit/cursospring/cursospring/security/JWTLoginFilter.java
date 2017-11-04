package com.ufrpe.seedabit.cursospring.cursospring.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufrpe.seedabit.cursospring.cursospring.model.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {



    public JWTLoginFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }


    //Método que descreve como a autentição padrão será feita
    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException, IOException, ServletException {

        //transforma o corpo da requisição de login no DTO do usuário
        UsuarioDTO creds = new ObjectMapper().readValue(req.getInputStream(), UsuarioDTO.class);

        //Retorna uma autenticação após validar os dados passsar
        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                        creds.getCpf(),
                        creds.getSenha()
                )
        );
    }

    //Esse método descreve o que será feito em caso de sucesso na autenticação
    @Override
    protected void successfulAuthentication(
            HttpServletRequest req,
            HttpServletResponse res, FilterChain chain,
            Authentication auth) throws IOException, ServletException {
        TokenAutenticationService.addAuthentication(res, auth.getName(),auth.getAuthorities());
    }

    //Caso ocorra erro na autenticação
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
