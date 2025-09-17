package com.prueba_tecnica.abitmedia.services.operators;

import com.prueba_tecnica.abitmedia.database.models.Tarea;
import com.prueba_tecnica.abitmedia.database.models.Usuario;
import com.prueba_tecnica.abitmedia.database.repository.ITareaRepository;
import com.prueba_tecnica.abitmedia.rest.builder.TareaBuilder;
import com.prueba_tecnica.abitmedia.rest.models.TareaDto;
import com.prueba_tecnica.abitmedia.services.exception.ApiException;
import com.prueba_tecnica.abitmedia.services.messages.ApiMessages;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TareaService {


    private final ITareaRepository iTareaRepository;
    private final TareaBuilder tareaBuilder;


    public TareaDto save(TareaDto model, Usuario usuario) {
        return tareaBuilder.builderDto(iTareaRepository.save(tareaBuilder.builder(model, usuario)));
    }

    public TareaDto update(UUID tokenId, TareaDto model, Usuario usuario) {

        Tarea tarea = get(tokenId);
        validateUserModifiy(tarea, usuario);
        model.setFechaActualizacion(LocalDate.now());
        return tareaBuilder.builderDto(iTareaRepository.save(tareaBuilder
                .builderUpdate(model, tarea, usuario)));
    }

    private void validateUserModifiy(Tarea tarea, Usuario usuario) {
        if (!usuario.getTokenId().equals(tarea.getUsuario().getTokenId())) {
            throw new ApiException(ApiMessages.ERROR_OPERACION);
        }
    }

    public List<TareaDto> getAllForUser(UUID tokenIdUser) {
        return iTareaRepository.findAllByUsuarioTokenId(tokenIdUser)
                .stream()
                .map(tareaBuilder::builderDto)
                .toList();
    }


    public void delete(UUID tokenId, Usuario usuario) {
        Tarea tarea = get(tokenId);
        validateUserModifiy(tarea, usuario);
        iTareaRepository.delete(tarea);
    }

    private Tarea get(UUID tokenId) {
        return iTareaRepository.findByTokenId(tokenId)
                .orElseThrow(() -> new ApiException(ApiMessages.ERROR_NO_EXISTE_TAREA));
    }

}
