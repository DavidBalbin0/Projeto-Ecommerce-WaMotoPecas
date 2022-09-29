package me.balbino.wamotopecas.security;

import me.balbino.wamotopecas.model.User;
import me.balbino.wamotopecas.repository.UserRepository;
import me.balbino.wamotopecas.service.MeTokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class AuthenticationTokenFilter extends OncePerRequestFilter {

    private MeTokenService tokenService;
    private UserRepository userRepository;

    public AuthenticationTokenFilter(MeTokenService tokenService, UserRepository userRepository) {

        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = getToken(request);
        boolean valido = tokenService.isTokenValido(token);
        System.out.println("token" + valido);
        if (valido){
            autenticarCliente(token);
        }

        filterChain.doFilter(request, response);
    }

    private void autenticarCliente(String token) {
        Long idUsuario = tokenService.getIdUsuario(token);
        System.out.println("id do usuario:" + idUsuario);
        User user =pegaUsuario(idUsuario);
        System.out.println(user.getEmail());
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private User pegaUsuario(Long id){
        Optional<User> usuario = userRepository.findById(id);
        if(usuario.isPresent()){
            return usuario.get();
        }else {
            throw new UsernameNotFoundException("usuario nao encontrado");
        }
    }

    private String getToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if(token == null || !token.startsWith("Bearer ")){
            return null;
        }
        System.out.println(token);
        System.out.println(token.substring(7));
        return token.substring(7);

    }
}
