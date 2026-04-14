package com.springboot.hospital.mappers;

import com.springboot.hospital.Modelo.Paciente;
import com.springboot.hospital.dto.PacienteDTO;
import org.springframework.stereotype.Component;

@Component
public class PacienteMapper {
    public PacienteDTO aDTO (Paciente paciente) {
        PacienteDTO pacienteDTO = new PacienteDTO();
        pacienteDTO.setId(paciente.getId());
        pacienteDTO.setNombre(paciente.getNombre());
        pacienteDTO.setFechaNacimiento(paciente.getFechaNacimiento());
        pacienteDTO.setEnfermedad(paciente.isEnfermedad());
        return pacienteDTO;
    }

    public Paciente aEntidad (PacienteDTO pacienteDTO) {
        Paciente paciente = new Paciente();
        paciente.setId(pacienteDTO.getId());
        paciente.setNombre(pacienteDTO.getNombre());
        paciente.setFechaNacimiento(pacienteDTO.getFechaNacimiento());
        paciente.setEnfermedad(Boolean.TRUE.equals(pacienteDTO.getEnfermedad()));
        return paciente;

    }
}
