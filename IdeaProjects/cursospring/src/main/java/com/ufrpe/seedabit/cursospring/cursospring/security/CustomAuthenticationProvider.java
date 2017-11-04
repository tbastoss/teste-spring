package com.ufrpe.seedabit.cursospring.cursospring.security;

import com.ufrpe.seedabit.cursospring.cursospring.data.UsuarioDAO;
import com.ufrpe.seedabit.cursospring.cursospring.model.beans.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {


    @Autowired
    private UsuarioDAO repository;

    //Como a auntenticação é feita
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //Obtem o login  e a senha do usuário para auntenticá-lo
        String cpf = authentication.getName();
        String password = authentication.getCredentials().toString();

        Usuario result = repository.findOne(cpf);

        if(result != null){
            if(result.getSenha().equals(password)){
                Authentication auth = new UsernamePasswordAuthenticationToken(result.getCpf(), password,result.getCargo().getAutorizacoes());
                return auth;
            }else
                throw new BadCredentialsException("Senha incorreta");
        }
        else {
            throw new BadCredentialsException("Login incorreto");
        }
    }

    // Define o tipo de entrada do serviço de autenticação (nesse caso a classe UsernamePasswordAuthenticationToken)
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
