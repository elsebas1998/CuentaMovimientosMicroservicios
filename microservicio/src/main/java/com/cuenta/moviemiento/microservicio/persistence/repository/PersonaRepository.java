package com.cuenta.moviemiento.microservicio.persistence.repository;

import com.cuenta.moviemiento.microservicio.persistence.entities.PersonaEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<PersonaEntity, Long> {
    Optional<PersonaEntity> findByIdentificacion(final String identificacion);

    @Transactional
    void deleteByIdentificacion(final String identificacion);
}
