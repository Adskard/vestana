package cz.cvut.fel.nss.vestana.filters;

import com.auth0.jwt.JWT;
import java.io.IOException;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cz.cvut.fel.nss.vestana.config.SecurityConstants;
import cz.cvut.fel.nss.vestana.model.AppUserDetails;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static cz.cvut.fel.nss.vestana.config.SecurityConstants.*;
import static com.auth0.jwt.algorithms.Algorithm.HMAC512;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super();
        this.setAuthenticationManager(authenticationManager);

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth
    ) throws IOException {
        System.out.println("successfulAuthentication method initiated");
        AppUserDetails principal = (AppUserDetails) auth.getPrincipal();
        GrantedAuthority authority = null;
        try {
            authority = principal.getAuthorities().stream().findFirst().orElseThrow(Exception::new);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String token = JWT.create()
                .withSubject(principal.getUsername())
                .withClaim("role", authority.getAuthority())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SecurityConstants.getSecret().getBytes()));
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        res.getWriter().write("{\"" + HEADER_STRING + "\":\"" + TOKEN_PREFIX + token + "\"}");
    }
}
