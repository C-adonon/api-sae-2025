package future.SAE.infrastructure.config;

import future.SAE.domain.model.Utilisateur;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    public String genererToken(Utilisateur utilisateur) {
        return Jwts.builder()
                .subject(utilisateur.getIdentifiant()) // Le "sujet" principal du token
                .claim("id", utilisateur.getId().toString()) // On peut ajouter des infos (Claims)
                .claim("role", utilisateur.getClass().getSimpleName().toUpperCase()) // "PROFESSEUR" ou "ELEVE"
                .issuedAt(new Date(System.currentTimeMillis())) // Date de création
                .expiration(new Date(System.currentTimeMillis() + jwtExpiration)) // Date d'expiration
                .signWith(getSignInKey()) // Signature avec notre clé secrète
                .compact(); // On assemble le tout sous forme de String
    }

    public String extraireIdentifiant(String token) {
        return extraireClaim(token, Claims::getSubject);
    }

    private <T> T extraireClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extraireTousLesClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extraireTousLesClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }


    public boolean estTokenValide(String token, String identifiantFourni) {
        final String identifiantExtrait = extraireIdentifiant(token);
        return (identifiantExtrait.equals(identifiantFourni)) && !estTokenExpire(token);
    }

    private boolean estTokenExpire(String token) {
        return extraireClaim(token, Claims::getExpiration).before(new Date());
    }

    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}