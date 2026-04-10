package com.springboot.hospital.servicio.implementacion;

import com.springboot.hospital.Modelo.Cita;
import com.springboot.hospital.dto.CitaDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CitaServicio implements com.springboot.hospital.servicio.CitaServicio {
    @Override
    public List<CitaDTO> obtenerCitas() {
        return List.of();
    }

    @Override
    public Optional<CitaDTO> obtenerCitaPorId(Long id) {
        return Optional.empty();
    }

    @Override
    public Cita crearCita(CitaDTO citaDTO, Long idPaciente, Long idMedico) {
        return null;
    }

    @Override
    public CitaDTO modificarCita(Long id, CitaDTO citaDTO) {
        return null;
    }

    @Override
    public void borrarCita(Long id) {

    }

    @Override
    public List<CitaDTO> obtenerCitasPorIdPaciente(Long pacienteId) {
        return List.of();
    }

    @Override
    public List<CitaDTO> obtenerCitasPorIdMedico(Long medicoId) {
        return List.of();
    }

    @Override
    public List<CitaDTO> obtenerCitasPorStatusCita(String statusCita) {
        return List.of();
    }
}
