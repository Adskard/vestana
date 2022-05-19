package cz.cvut.fel.nss.vestana.controller.util;

import com.auth0.jwt.JWT;
import com.sun.istack.NotNull;
import cz.cvut.fel.nss.vestana.config.SecurityConstants;
import cz.cvut.fel.nss.vestana.model.Employee;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Date;

public class SecurityUtils {

    public static String jwtGenerateAccessToken(@NotNull Employee user) {
        return JWT.create()
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .withSubject(user.getUsername())
                .sign(SecurityConstants.JWT_ALGORITHM);
    }

    public static Employee getCurrentUser() {
        final SecurityContext context = SecurityContextHolder.getContext();

        assert context != null;

        final Employee userDetails = (Employee) context.getAuthentication().getPrincipal();

        return userDetails;
    }
}