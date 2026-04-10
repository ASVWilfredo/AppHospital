package com.springboot.hospital.servicio;

import com.springboot.hospital.Modelo.Cita;
import com.springboot.hospital.dto.CitaDTO;

import java.util.List;
import java.util.Optional;

public interface CitaServicio {
    List<CitaDTO> obtenerCitas();
    Optional<CitaDTO> obtenerCitaPorId(Long id);
    Cita crearCita(CitaDTO citaDTO, Long idPaciente, Long idMedico);
    CitaDTO modificarCita(Long id, CitaDTO citaDTO);
    void borrarCita(Long id);
    List<CitaDTO> obtenerCitasPorIdPaciente(Long pacienteId);
    List<CitaDTO> obtenerCitasPorIdMedico(Long medicoId);
    List<CitaDTO> obtenerCitasPorStatusCita(String statusCita);
}
