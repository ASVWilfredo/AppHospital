package com.springboot.hospital.reposiorio;

import com.springboot.hospital.Modelo.Cita;
import com.springboot.hospital.Modelo.StatusCita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitaRepositorio extends JpaRepository<Cita, Long> {
    List<Cita> findByPacienteId(Long pacienteId);
    List<Cita> findByMedicoId(Long medicoId);
    List<Cita> findByStatusCita(StatusCita statusCita);
}
