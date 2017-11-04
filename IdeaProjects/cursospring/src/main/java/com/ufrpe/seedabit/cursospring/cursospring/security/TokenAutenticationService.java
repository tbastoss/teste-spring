package com.ufrpe.seedabit.cursospring.cursospring.security;


import com.ufrpe.seedabit.cursospring.cursospring.data.UsuarioDAO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;


@Component
public class TokenAutenticationService {

    @Autowired
    private UsuarioDAO repository;

    private static UsuarioDAO staticRepository;



    static final long EXPIRATIONTIME = 432_000_000;     // 5 dias
    static final String SECRET = "senha";            // Senha do JWT
    static final String TOKEN_PREFIX = "Bearer";        // Token Prefixo
    static final String HEADER_STRING = "Authorization";//Chave do cabeçalho do Token

    @PostConstruct
    public void init()
    {

        TokenAutenticationService.staticRepository =this.repository; // So this method is responsible for handing out the bean after                                                                     the class is constructed and autowired by the spring                                                                                  container . :)

    }




    public static void addAuthentication(HttpServletResponse response, String username, Collection<? extends GrantedAuthority> autorizations) {

        // Cria o JWT
         String  jwt = Jwts.builder()
                    .setSubject("users/TzMUocMF4p")
                    .setExpiration(new Date(Calendar.getInstance().getTimeInMillis() + EXPIRATIONTIME))
                    .claim("cpf", username)
                    .claim("authorities", autorizations)
                    .signWith(
                            SignatureAlgorithm.HS256,
                            SECRET
                    )
                    .compact();


        // Adciona a String do JWT ao corpo da resposta
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.addHeader(HEADER_STRING,jwt);
    }

   public static Authentication getAuthentication(HttpServletRequest request) throws SignatureException {
        //Pega o token no cabeçalho da requisição
        String token = request.getHeader(HEADER_STRING);
       Claims claims = null;
        if (token != null) {

           claims = Jwts.parser()
                    // Verifica a autenticidade do token, caso não seja válido lança uma exceção
                    .setSigningKey(SECRET)
                    // 去掉 Bearer
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody();

                //Pega o CPF do usuário na requisição
            String user = (String) claims.get("cpf");



            //Retorna um Objeto Autenticação com o cpf do usuário junto com suas autorizações
            return user != null ?
                    new UsernamePasswordAuthenticationToken(user, null, staticRepository.findOne(user).getCargo().getAutorizacoes()) :
                    null;
        }
        return null;
    }
}
