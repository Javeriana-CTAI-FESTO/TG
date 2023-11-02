package co.edu.javeriana.ctai.tgsecurity.security.jwt;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Métodos para generar y validar los token JWT
 */
@Component
public class JwtTokenUtil {

    private static final Logger log = LoggerFactory.getLogger(JwtTokenUtil.class);

    @Value("${app.jwt.secret}")
    private String jwtSecret;

    @Value("${app.jwt.expiration-ms}")
    private int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication) {

        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }

    public boolean isValidRefreshToken(String refreshToken) {
        try {
            // Parsea el token de actualización
            Claims claims = Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(refreshToken)
                    .getBody();

            // Verifica que el token no haya expirado
            Date expirationDate = claims.getExpiration();
            Date now = new Date();
            if (expirationDate.before(now)) {
                return false; // El token ha expirado
            }

            // Si pasa todas las comprobaciones, el token de actualización es válido
            return true;
        } catch (Exception e) {
            return false; // Error al validar el token
        }
    }

    public String getUsernameFromRefreshToken(String refreshToken) {
        try {
            // Parsea el token de actualización
            Claims claims = Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(refreshToken)
                    .getBody();

            // Extrae el nombre de usuario (username) de las reclamaciones
            String username = claims.getSubject();

            // Devuelve el nombre de usuario
            return username;
        } catch (Exception e) {
            return null; // Error al obtener el nombre de usuario
        }
    }

    public String generateAccessToken(UserDetails userDetails) {
        // Crea un nuevo token de acceso
        String accessToken = Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();

        // Devuelve el token de acceso
        return accessToken;
    }

    public Date getExpirationDateFromToken(String refreshToken) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(jwtSecret) // Reemplaza SECRET_KEY con tu clave secreta
                    .parseClaimsJws(refreshToken)
                    .getBody();

            return claims.getExpiration();
        } catch (Exception e) {
            // Si ocurre algún error al obtener la fecha de expiración, puedes manejarlo aquí.
            return null;
        }
    }
}

