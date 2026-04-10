package com.springboot.hospital.reposiorio;

import com.springboot.hospital.Modelo.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepositorio extends JpaRepository<Paciente, Long> {
    Paciente findByNombre(String nombre);
}
