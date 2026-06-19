package future.SAE.infrastructure.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        // Extraire l'en-tête "Authorization"
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userIdentifiant;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7);

        try {
            userIdentifiant = jwtTokenProvider.extraireIdentifiant(jwt);

            // Si l'identifiant est trouvé et que l'utilisateur n'est pas encore authentifié dans le contexte Spring
            if (userIdentifiant != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                // Si le token est valide (non expiré, etc.)
                if (jwtTokenProvider.estTokenValide(jwt, userIdentifiant)) {

                    // On crée l'objet d'authentification pour Spring Security
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userIdentifiant,
                            null,
                            Collections.emptyList()
                    );

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    // On enregistre l'utilisateur dans le contexte de sécurité de Spring
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        } catch (Exception e) {
            // Si le token est corrompu ou expiré, on ne lève pas d'exception ici,
            // le contexte restera vide et le filtre suivant bloquera la requête si la route est protégée.
        }

        filterChain.doFilter(request, response);
    }
}