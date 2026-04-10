package com.springboot.hospital.mappers;

import com.springboot.hospital.Modelo.Medico;
import com.springboot.hospital.dto.MedicoDTO;
import org.springframework.stereotype.Component;

@Component
public class MedicoMapper {
    public MedicoDTO aDTO(Medico medico) {
        MedicoDTO medicoDTO = new MedicoDTO();
        medicoDTO.setId(medico.getId());
        medicoDTO.setNombre(medico.getNombre());
        medicoDTO.setEmail(medico.getEmail());
        medicoDTO.setEspecialidad(medico.getEspecialidad());
        return medicoDTO;
    }

    public Medico aEntidad(MedicoDTO medicoDTO) {
        Medico medico = new Medico();
        medico.setId(medicoDTO.getId());
        medico.setNombre(medicoDTO.getNombre());
        medico.setEmail(medicoDTO.getEmail());
        medico.setEspecialidad(medicoDTO.getEspecialidad());
        return medico;
    }


}
