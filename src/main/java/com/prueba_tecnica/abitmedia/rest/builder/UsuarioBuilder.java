package com.prueba_tecnica.abitmedia.rest.builder;

import com.prueba_tecnica.abitmedia.database.models.Usuario;
import com.prueba_tecnica.abitmedia.rest.models.UsuarioDto;
import org.springframework.stereotype.Component;

@Component
public class UsuarioBuilder {

    public Usuario builder(UsuarioDto model) {
        return Usuario.builder()
                .id(model.getId())
                .tokenId(model.getTokenId())
                .nombre(model.getNombre())
                .email(model.getEmail())
                .password(model.getPassword())
                .build();
    }


    public UsuarioDto builderDto(Usuario model) {
        return UsuarioDto.builder()
                .id(model.getId())
                .tokenId(model.getTokenId())
                .nombre(model.getNombre())
                .email(model.getEmail())
                .build();
    }


}
