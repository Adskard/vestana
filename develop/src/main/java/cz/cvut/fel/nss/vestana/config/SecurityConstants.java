package cz.cvut.fel.nss.vestana.config;

import com.auth0.jwt.algorithms.Algorithm;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class SecurityConstants {
    public static String SECRET = "sseeccrreett";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static Algorithm JWT_ALGORITHM = Algorithm.HMAC256(SECRET);

    //@Value("${jwt.secret}")
    public void setSecret(String secret) {
        SecurityConstants.SECRET = SECRET;
    }

    public static String getSecret() {
        return SECRET;
    }
}
