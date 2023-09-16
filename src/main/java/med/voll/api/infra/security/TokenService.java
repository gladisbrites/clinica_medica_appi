package med.voll.api.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Payload;

import med.voll.api.domain.usuarios.Usuario;

@Service
public class TokenService {

@Value("${api.security.secret}")
private String apiSecret;
private Payload verifier;
    public String generarToken(Usuario usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                .withIssuer("Vol med")
                .withSubject(usuario.getLogin())
                .withClaim("id", usuario.getId())
                .withExpiresAt(generarFechaExpiracion())
                .sign(algorithm);
        } catch (JWTCreationException exception){
           throw new RuntimeException();
        }

        
    }
    public String getSubjet(String token){
        DecodedJWT verifier=null;
            try {
                Algorithm algorithm = Algorithm.HMAC256(apiSecret);
                verifier = JWT.require(algorithm)
                    // specify an specific claim validations
                    .withIssuer("Vol med")
                    // reusable verifier instance
                    .build()
                    .verify(token);
           verifier.getSubject();
            } catch (JWTVerificationException exception){
                // Invalid signature/claims
            }
            if(verifier.getSubject()==null){
              throw new RuntimeException("Verifier invalido ");
            }
             return verifier.getSubject();
     }



  private Instant generarFechaExpiracion()  {
    return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
  }
}
