package com.springboot.hospital.servicio.implementacion;

import com.springboot.hospital.dto.CitaDTO;
import com.springboot.hospital.dto.PacienteDTO;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class PacienteServicio implements com.springboot.hospital.servicio.PacienteServicio {
    @Override
    public List<PacienteDTO> obtenerPacientes() {
        return List.of();
    }

    @Override
    public Optional<PacienteDTO> obtenerPacientePorId(Long id) {
        return Optional.empty();
    }

    @Override
    public PacienteDTO crearPaciente(PacienteDTO pacienteDTO) {
        return null;
    }

    @Override
    public PacienteDTO actualizarPaciente(Long id, PacienteDTO pacienteDTO) {
        return null;
    }

    @Override
    public void eliminarPaciente(Long id) {

    }

    @Override
    public Collection<CitaDTO> obtenerCitasPorIdPaciente(Long idPaciente) {
        return List.of();
    }
}
