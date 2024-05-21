package uz.pdp.online.onlinepayment.jwt;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.pdp.online.onlinepayment.service.UserService;

import javax.security.auth.login.AccountException;
import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {


    private final JwtProvider jwtProvider;
    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("ENTEEERRRRRRRRRRRRRRRRRRRRR");

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = jwtProvider.extractTokenFromHeader(header);
        if (token.isEmpty() || token.isBlank()) {
            sendError(response, "jwt token is not valid");
        }
        Claims parsed = null;
        try {
            parsed = jwtProvider.parse(token);
        } catch (RuntimeException e) {
            sendError(response, "jwt token is not valid");
            return;
        }

        if (!jwtProvider.isValid(parsed)) {
            sendError(response, "jwt token is expired");
            return;
        }

        String phone = jwtProvider.getSubject(parsed);

        try {
            userService.checkUser(parsed);
        } catch (AccountException e) {
            sendError(response, e.getMessage());
        }



        var authentication = new UsernamePasswordAuthenticationToken(
                phone,
                null,
                List.of(new SimpleGrantedAuthority(parsed.get("role",String.class)))
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);

    }

    private void sendError(HttpServletResponse resp, String msg) throws IOException {
        resp.setStatus(HttpServletResponse.SC_BAD_GATEWAY);
        resp.setContentType("application/json");
        resp.getWriter().write(msg);
    }
}
