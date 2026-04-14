package com.springboot.hospital.mappers;

import com.springboot.hospital.Modelo.Cita;
import com.springboot.hospital.Modelo.Consulta;
import com.springboot.hospital.dto.CitaDTO;
import com.springboot.hospital.dto.ConsultaDTO;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class ConsultaMapper {
    private static final SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

    public ConsultaDTO aDTO(Consulta consulta) {
        ConsultaDTO consultaDTO = new ConsultaDTO();
        consultaDTO.setId(consulta.getId());
        consultaDTO.setFechaConsultaFromDate(consulta.getFechaConsulta());
        consultaDTO.setInforme(consulta.getInforme());
        if(consulta.getCita() != null) {
            Cita cita = consulta.getCita();
            CitaDTO citaDTO = new CitaDTO();
            citaDTO.setId(cita.getId());
            citaDTO.setFecha(formatoFecha.format(cita.getFecha()));
            citaDTO.setCancelado(cita.isCancelada());
            citaDTO.setStatusCita(cita.getStatusCita().toString());
            citaDTO.setPacienteId(cita.getPaciente().getId());
            citaDTO.setMedicoId(cita.getMedico().getId());
            consultaDTO.setCitaDTO(citaDTO);
        }
        return consultaDTO;
    }

    public Consulta aEntidad(ConsultaDTO consultaDTO) throws ParseException {
        Consulta consulta = new Consulta();
        consulta.setId(consultaDTO.getId());
        consulta.setFechaConsulta((formatoFecha.parse(consultaDTO.getFechaConsulta())));
        consulta.setInforme(consultaDTO.getInforme());
        if(consultaDTO.getCitaDTO() != null) {
            CitaDTO citaDTO = consultaDTO.getCitaDTO();
            Cita cita = new Cita();
            cita.setId(citaDTO.getId());
            consulta.setCita(cita);
        }
        return consulta;
    }
}
