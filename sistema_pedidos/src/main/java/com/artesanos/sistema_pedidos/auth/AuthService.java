package com.artesanos.sistema_pedidos.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.artesanos.sistema_pedidos.dtos.LoginRequestDto;
import com.artesanos.sistema_pedidos.entities.Usuario;
import com.artesanos.sistema_pedidos.jwt.JwtService;
import com.artesanos.sistema_pedidos.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequestDto request) {
        authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(request.getNombreUsuario(), request.getContrasena()));
        Usuario user = userRepository.findByNombre(request.getNombreUsuario()).orElseThrow();
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .rol(user.getRol().getNombreRol())
                .build();

    }
}
