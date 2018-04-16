package com.ifpb.dac.rs.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;
import javax.ejb.Stateless;
import javax.xml.bind.DatatypeConverter;
/**
 * @author lyndemberg
 */

@Stateless
public class Token {
    private static final String CHAVE = "ABnD78hasrpqWNbK93/JkLmNf";
    
    public String create(String usuario, long horas) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;

        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime agoraComHoras = agora.plusHours(horas);
        Date horaGeracao = convertLocalDateTimeToDate(agora);
        Date horaExpiracao = convertLocalDateTimeToDate(agoraComHoras);
        
        byte[] chaveBytes = DatatypeConverter.parseBase64Binary(CHAVE);
        Key signingKey = new SecretKeySpec(chaveBytes, signatureAlgorithm.getJcaName());
        JwtBuilder builder = Jwts.builder().setIssuedAt(horaGeracao)
                                        .setSubject(usuario)
                                        .signWith(signatureAlgorithm, signingKey)
                                        .setExpiration(horaExpiracao);    
        return builder.compact();
    }
    
    public boolean validarToken(String jwt) {
        try{
            Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(CHAVE))
                            .parseClaimsJws(jwt).getBody();
            return true;
        }catch(Exception ex){
            return false;
        }
    }
    
    private Date convertLocalDateTimeToDate(LocalDateTime dt){
        Instant instant = dt.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }
}
