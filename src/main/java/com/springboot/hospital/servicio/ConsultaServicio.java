package com.springboot.hospital.servicio;

import com.springboot.hospital.Modelo.Cita;
import com.springboot.hospital.dto.ConsultaDTO;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public interface ConsultaServicio {
    List<ConsultaDTO> obtenerConsultas();
    Optional<ConsultaDTO> obtenerConsultaPorId(Long id);
    ConsultaDTO crearConsulta(Long citaId, ConsultaDTO consultaDTO) throws ParseException;
    ConsultaDTO modificarConsulta(Long id, ConsultaDTO consultaDTO) throws ParseException;
    void eliminarConsulta(Long id);
    List<ConsultaDTO> obtenerConsultasPorInformeContaining(String searchTerm);
    List<ConsultaDTO> obtenerConsultasPorCita(Cita cita);
    List<ConsultaDTO> obtenerConsultasPorCita(Long citaId) throws ParseException;
}
