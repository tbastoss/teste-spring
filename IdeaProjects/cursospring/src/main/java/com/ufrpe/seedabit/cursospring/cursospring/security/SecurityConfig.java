package com.ufrpe.seedabit.cursospring.cursospring.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//
@Configuration
//Habilita o uso das anotações PreAuthorized para acesso aos endpoints
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationProvider authenticationProvider;


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                //Autoriza as requisições com regras a seguir
                .authorizeRequests()
                // Autoriza qualquer requisição POST para o /login
                .antMatchers(HttpMethod.POST,"/login").permitAll()
//                //Todas as requisições além do login exigem quem estejam autorizadas
                .anyRequest().authenticated()
                .and()
                // Adciona um filtro para todas as requisições quando não tiver token serem
                .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
               //Validação Redirecinamento para o filtro de aultenticação
                .addFilterBefore(new JWTAuthenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class);
    }

    //Reescreve o método de autenticação padrão para o customizado
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Autenticação personalizada
        auth.authenticationProvider(authenticationProvider);

    }
}
