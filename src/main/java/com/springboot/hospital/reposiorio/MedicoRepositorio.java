package com.springboot.hospital.reposiorio;

import com.springboot.hospital.Modelo.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicoRepositorio extends JpaRepository<Medico, Long> {
    Medico findByNombre(String nombre);
    List<Medico> findByEspecialidad(String especialidad);
}
