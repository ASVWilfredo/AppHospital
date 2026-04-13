package com.springboot.hospital.servicio;

import com.springboot.hospital.dto.CitaDTO;
import com.springboot.hospital.dto.MedicoDTO;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface MedicoServicio {
    List<MedicoDTO> obtenerMedicos();
    Optional<MedicoDTO> obtenerMedicoPorId(Long id);
    MedicoDTO crearMedico(MedicoDTO medicoDTO);
    MedicoDTO actualizarMedico(Long id, MedicoDTO medicoDTO);
    void eliminarMedico(Long medicoId);
    Collection<CitaDTO> obtenerCitasPorIdMedico(Long medicoId);
    List<MedicoDTO> obtenerMedicosPorEspecialidad(String especialidad);
}
