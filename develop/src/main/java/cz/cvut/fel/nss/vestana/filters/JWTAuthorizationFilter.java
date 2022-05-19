package cz.cvut.fel.nss.vestana.filters;

import com.auth0.jwt.JWT;
import com.sun.istack.NotNull;
import cz.cvut.fel.nss.vestana.config.SecurityConstants;
import cz.cvut.fel.nss.vestana.exception.InvalidStateException;
import cz.cvut.fel.nss.vestana.model.AppUserDetails;
import cz.cvut.fel.nss.vestana.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component @Slf4j @RequiredArgsConstructor
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    final private UserDetailsServiceImpl userDetailsServiceImpl;

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest req, @NotNull HttpServletResponse res, @NotNull FilterChain filterChain) throws ServletException, IOException {
        log.info("Running Authorization filter");

        if (req.getRequestURI().startsWith("/auth")) {
            filterChain.doFilter(req, res);
            return;
        }

        try {
            String jwtToken = parseTokenFromHeader(req).orElseThrow(InvalidStateException::new);

            String username = JWT.decode(jwtToken).getSubject();

            UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));

            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            log.error(e.getMessage());
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Malformed or expirated token.");
            return;
        }

        filterChain.doFilter(req, res);
    }

    /**
     * @param req HTTP request object with headers.
     * @return parsed JWT token or empty if non-existent.
     */
    private Optional<String> parseTokenFromHeader(HttpServletRequest req) {
        String authorizationHeader = req.getHeader("Authorization");

        if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            return Optional.of(
                    authorizationHeader.substring(SecurityConstants.TOKEN_PREFIX.length())
            );
        }

        return Optional.empty();
    }
}