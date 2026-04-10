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

    public Cita aEntidad(CitaDTO citaDTO) throws ParseException {
        Cita cita = new Cita();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date fecha = sdf.parse(citaDTO.getFecha());
        cita.setFecha(fecha);
        cita.setCancelada(citaDTO.isCancelado());
        cita.setStatusCita(StatusCita.valueOf(citaDTO.getStatusCita()));
        Optional<Paciente> paciente = pacienteRepositorio.findById(citaDTO.getPacienteId());
        Paciente pacienteBD = paciente.get();
        cita.setPaciente(pacienteBD);
        Optional<Medico> medico = medicoRepositorio.findById(citaDTO.getMedicoId());
        Medico medicoBD = medico.get();
        cita.setMedico(medicoBD);
        return cita;
    }


}
