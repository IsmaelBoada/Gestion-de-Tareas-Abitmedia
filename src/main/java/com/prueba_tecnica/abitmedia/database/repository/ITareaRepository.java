package com.prueba_tecnica.abitmedia.database.repository;

import com.prueba_tecnica.abitmedia.database.models.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ITareaRepository extends JpaRepository<Tarea, Long> {

    Optional<Tarea> findByTokenId(UUID tokenId);


    List<Tarea> findAllByUsuarioTokenId(UUID tokenUserId);

}
