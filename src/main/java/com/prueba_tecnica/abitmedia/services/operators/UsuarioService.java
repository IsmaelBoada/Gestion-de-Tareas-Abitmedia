package com.prueba_tecnica.abitmedia.services.operators;

import com.prueba_tecnica.abitmedia.config.jwt.JwtService;
import com.prueba_tecnica.abitmedia.database.models.Usuario;
import com.prueba_tecnica.abitmedia.database.repository.IUsuarioRepository;
import com.prueba_tecnica.abitmedia.rest.builder.UsuarioBuilder;
import com.prueba_tecnica.abitmedia.rest.models.AuthenticationDto;
import com.prueba_tecnica.abitmedia.rest.models.AuthenticationResponse;
import com.prueba_tecnica.abitmedia.rest.models.UsuarioDto;
import com.prueba_tecnica.abitmedia.services.exception.ApiException;
import com.prueba_tecnica.abitmedia.services.messages.ApiMessages;
import com.prueba_tecnica.abitmedia.config.UtilPassword;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final IUsuarioRepository iUsuarioRepository;
    private final UsuarioBuilder usuarioBuilder;
    private final UtilPassword utilPassword;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UsuarioDto save(UsuarioDto model) {
        validateEmail(model.getEmail());
        model.setPassword(utilPassword.passwordEncoder().encode(model.getPassword()));
        return usuarioBuilder.builderDto(iUsuarioRepository.save(usuarioBuilder.builder(model)));
    }

    private void validateEmail(String email) {
        iUsuarioRepository.findByEmail(email).ifPresent(x -> {
            throw new ApiException(ApiMessages.ERROR_CORREO_REGISTRADO);
        });
    }


    public AuthenticationResponse login(AuthenticationDto model) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                model.getEmail(), model.getPassword()));

        Usuario usuario = iUsuarioRepository.findByEmail(model.getEmail())
                .orElseThrow(() -> new ApiException(ApiMessages.ERROR_NO_EXISTE_USUARIO));

        return AuthenticationResponse.builder()
                .token(jwtService.generateToken(usuario))
                .build();
    }

}
