package com.springboot.hospital.mappers;

import com.springboot.hospital.Modelo.Cita;
import com.springboot.hospital.Modelo.Medico;
import com.springboot.hospital.Modelo.Paciente;
import com.springboot.hospital.Modelo.StatusCita;
import com.springboot.hospital.dto.CitaDTO;
import com.springboot.hospital.reposiorio.MedicoRepositorio;
import com.springboot.hospital.reposiorio.PacienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Component
public class CitaMapper {
    @Autowired
    private MedicoRepositorio medicoRepositorio;

    @Autowired
    private PacienteRepositorio pacienteRepositorio;

    public CitaDTO aDTO(Cita cita) {
        CitaDTO citaDTO = new CitaDTO();
        citaDTO.setId(citaDTO.getId());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String fechaFormateada =  sdf.format(cita.getFecha());
        citaDTO.setFecha(fechaFormateada);
        citaDTO.setCancelado(cita.isCancelada());
        citaDTO.setStatusCita(cita.getStatusCita().toString());
        citaDTO.setPacienteId(cita.getPaciente().getId());
        citaDTO.setMedicoId(cita.getMedico().getId());
        return citaDTO;
    }

    public Cita aEntidad(CitaDTO citaDTO, Paciente paciente, Medico medico) throws ParseException {
        Cita cita = new Cita();
        cita.setId(citaDTO.getId());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date  fechaFormateada = sdf.parse(citaDTO.getFecha());
        cita.setFecha(fechaFormateada);
        cita.setCancelada(citaDTO.isCancelado());
        cita.setStatusCita(StatusCita.valueOf(String.valueOf(citaDTO.getStatusCita())));
        cita.setPaciente(paciente);
        cita.setMedico(medico);
        return cita;
    }
}
