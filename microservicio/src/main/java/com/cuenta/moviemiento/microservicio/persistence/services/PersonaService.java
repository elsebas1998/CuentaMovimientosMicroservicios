package com.cuenta.moviemiento.microservicio.persistence.services;


import com.cuenta.moviemiento.microservicio.persistence.entities.PersonaEntity;

public interface PersonaService {
    PersonaEntity obtenerPersona(String identificacion);

    PersonaEntity crearPersona(PersonaEntity persona);

    void eliminarPersona(String identificacion);
}
