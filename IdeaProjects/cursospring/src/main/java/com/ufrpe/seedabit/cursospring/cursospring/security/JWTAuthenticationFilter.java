package com.ufrpe.seedabit.cursospring.cursospring.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JWTAuthenticationFilter extends GenericFilterBean {



    // intercepta as requisições e altera o serviço de autenticação padrão para uma autenticação customizada com o Token
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {

        //Verifica se a requisitão tem mesmo um token válido
        Authentication authentication = TokenAutenticationService.getAuthentication((HttpServletRequest)request);
        //Seta a autorização da requisição ao contexto da thread de execução, isso servirá para os endpoints saberem se a thread
        //de execução possui as autorizações para acessar o método
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //Aplica o filtro
        filterChain.doFilter(request,response);
    }
}
