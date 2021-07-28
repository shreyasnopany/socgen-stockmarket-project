package userservice.security.jwt;

import javax.crypto.SecretKey;

import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

@Component
public class JwtUtil {

    @Autowired
    private SecretKey secretKey;

    public String getUsernameFromToken(String token) {
        token = token.replaceFirst("Bearer", "");
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            var body = claimsJws.getBody();
            System.out.println(body.getSubject());
            return (body.getSubject());
        } catch (JwtException e) {
            throw new IllegalStateException(String.format("Token %s cannot be trusted from javaUtil", token));
        }
    }
}