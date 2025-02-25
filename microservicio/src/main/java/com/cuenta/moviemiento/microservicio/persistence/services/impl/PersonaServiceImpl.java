package com.cuenta.moviemiento.microservicio.persistence.services.impl;


import com.cuenta.moviemiento.microservicio.persistence.entities.PersonaEntity;
import com.cuenta.moviemiento.microservicio.persistence.repository.PersonaRepository;
import com.cuenta.moviemiento.microservicio.persistence.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaServiceImpl implements PersonaService {
    @Autowired
    private PersonaRepository personaRepository;
    @Override
    public PersonaEntity obtenerPersona(String identificacion) {
        return personaRepository.findByIdentificacion(identificacion)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));
    }

    @Override
    public PersonaEntity crearPersona(PersonaEntity persona) {
         return personaRepository.save(persona);
    }

    @Override
    public void eliminarPersona(String identificacion) {
        personaRepository.deleteByIdentificacion(identificacion);
    }

}
