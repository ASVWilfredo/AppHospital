package com.springboot.hospital.servicio.implementacion;

import com.springboot.hospital.dto.CitaDTO;
import com.springboot.hospital.dto.MedicoDTO;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class MedicoServicio implements com.springboot.hospital.servicio.MedicoServicio {
    @Override
    public List<MedicoDTO> obtenerMedicos() {
        return List.of();
    }

    @Override
    public Optional<MedicoDTO> obtenerMedicoPorId(Long medicoId) {
        return Optional.empty();
    }

    @Override
    public MedicoDTO crearMedico(MedicoDTO medicoDTO) {
        return null;
    }

    @Override
    public MedicoDTO actualizarMedico(MedicoDTO medicoDTO) {
        return null;
    }

    @Override
    public void eliminarMedico(Long medicoId) {

    }

    @Override
    public Collection<CitaDTO> obtenerCitasPorIdMedico(Long medicoId) {
        return List.of();
    }

    @Override
    public List<MedicoDTO> obtenerMedicosPorEspecialidad() {
        return List.of();
    }
}
