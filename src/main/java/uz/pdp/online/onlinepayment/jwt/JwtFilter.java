package uz.pdp.online.onlinepayment.jwt;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AccountExpiredException;

import java.io.IOException;

//@Component
@RequiredArgsConstructor
public class JwtFilter  {


    private final JwtProvider jwtProvider;

//    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
        }

        String token = jwtProvider.extractTokenFromHeader(header);
        if (token != null) {
            filterChain.doFilter(request, response);
        }

        Claims parsed = jwtProvider.parse(token);

        if(!jwtProvider.isValid(parsed)){
            throw new AccountExpiredException("Expired token");
        }

        String phone = jwtProvider.getSubject(parsed);


//        var authentication = new UsernamePasswordAuthenticationToken();
//        SecurityContextHolder.getContext().setAuthentication(authentication);




    }
}
