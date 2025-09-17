package com.prueba_tecnica.abitmedia.utils;

import com.prueba_tecnica.abitmedia.config.jwt.JwtService;
import com.prueba_tecnica.abitmedia.database.models.Usuario;
import com.prueba_tecnica.abitmedia.database.repository.IUsuarioRepository;
import com.prueba_tecnica.abitmedia.services.exception.ApiException;
import com.prueba_tecnica.abitmedia.services.messages.ApiMessages;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class HttpServletRequestService {

    private final JwtService jwtService;
    private final IUsuarioRepository iUsuarioRepository;

    public Usuario getTokenAccess(HttpServletRequest request) {

        String authHeader = request.getHeader("Authorization");
        String token = null;
        if (Objects.nonNull(authHeader) && authHeader.startsWith("Bearer ")) {
            String tokenJwt = authHeader.substring(7);
            token = jwtService.getSubject(tokenJwt);
        }

        return iUsuarioRepository.findByEmail(token)
                .orElseThrow(() -> new ApiException(ApiMessages.ERROR_NO_EXISTE_USUARIO));

    }

}
