package com.springboot.hospital.servicio;

import com.springboot.hospital.Modelo.Cita;
import com.springboot.hospital.dto.CitaDTO;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public interface CitaServicio {
    List<CitaDTO> obtenerCitas();
    Optional<CitaDTO> obtenerCitaPorId(Long id);
    Cita crearCita(CitaDTO citaDTO, Long idPaciente, Long idMedico) throws ParseException;
    CitaDTO modificarCita(Long id, CitaDTO citaDTO) throws ParseException;
    void borrarCita(Long id);
    List<CitaDTO> obtenerCitasPorIdPaciente(Long pacienteId);
    List<CitaDTO> obtenerCitasPorIdMedico(Long medicoId);
    List<CitaDTO> obtenerCitasPorStatusCita(String statusCita);
}
