package br.com.cervejariayggbrasil.authentication.config;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.cervejariayggbrasil.authentication.repository.UsuarioRepository;
import br.com.cervejariayggbrasil.authentication.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter{

    @Autowired
    JwtService jwtService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                var token = jwtService.recoverToken(request);
                if(token != null){
                    var login = jwtService.validateToken(token);
                    Optional<UserDetails> user = usuarioRepository.findByLogin(login);
        
                    var authentication = new UsernamePasswordAuthenticationToken(user.get(), null, user.get().getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
                filterChain.doFilter(request, response);
    }

}
