/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.dac.rs.filters;

import com.ifpb.dac.rs.security.Secure;
import com.ifpb.dac.rs.security.Token;
import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.math.BigDecimal;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.NameBinding;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author lyndemberg
 */


@Provider
@Secure
public class AuthorizationFilter implements ContainerRequestFilter{
    
    private final Token token;
    
    public AuthorizationFilter() throws NamingException{
        String jndi = "java:global/core/Token";
        this.token = (Token) new InitialContext().lookup(jndi);
    }
    
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        System.out.println("Passou por aqui");
        
        String autorizacao = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if(autorizacao == null ||autorizacao.isEmpty()){
            JsonObject msg = Json.createObjectBuilder().add("msg", "Autorização não encontrada").build();
            Response response = Response.status(Response.Status.UNAUTHORIZED).
                                type(MediaType.APPLICATION_JSON)
                                .entity(msg)
                                .build();
            requestContext.abortWith(response);
        }else{
            String tokenString = autorizacao.substring("Bearer".length()).trim();
            boolean valido = token.validarToken(tokenString);
            if(!valido){
                JsonObject msg = Json.createObjectBuilder().add("msg", "Token não válido").build();
                Response response = Response.status(Response.Status.UNAUTHORIZED).
                                type(MediaType.APPLICATION_JSON).
                                entity(msg)
                                .build();
                requestContext.abortWith(response);
            }
        }    
    }
    
}
