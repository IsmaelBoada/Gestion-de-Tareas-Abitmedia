package com.prueba_tecnica.abitmedia.rest.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.prueba_tecnica.abitmedia.services.enums.EstadoTarea;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TareaDto implements Serializable {

    private Long id;

    private UUID tokenId;

    @NotNull(message = "Es requerido el título")
    private String titulo;

    @NotNull(message = "Es requerido el título")
    private String descripcion;

    private EstadoTarea estadoTarea;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaCreacion;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaActualizacion;

}
