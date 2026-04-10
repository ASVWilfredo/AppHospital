package com.springboot.hospital.servicio;

import com.springboot.hospital.dto.CitaDTO;
import com.springboot.hospital.dto.PacienteDTO;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface PacienteServicio {
    List<PacienteDTO> obtenerPacientes();
    Optional<PacienteDTO> obtenerPacientePorId(Long id);
    PacienteDTO crearPaciente(PacienteDTO pacienteDTO);
    PacienteDTO actualizarPaciente(Long id, PacienteDTO pacienteDTO);
    void eliminarPaciente(Long id);
    Collection<CitaDTO> obtenerCitasPorIdPaciente(Long idPaciente);
}
