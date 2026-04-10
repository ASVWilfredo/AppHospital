package com.springboot.hospital.servicio.implementacion;

import com.springboot.hospital.Modelo.Cita;
import com.springboot.hospital.dto.ConsultaDTO;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultaServicio implements com.springboot.hospital.servicio.ConsultaServicio {
    @Override
    public List<ConsultaDTO> obtenerConsultas() {
        return List.of();
    }

    @Override
    public Optional<ConsultaDTO> obtenerConsultaPorId(Long id) {
        return Optional.empty();
    }

    @Override
    public ConsultaDTO crearConsulta(Long citaId, ConsultaDTO consultaDTO) throws ParseException {
        return null;
    }

    @Override
    public ConsultaDTO modificarConsulta(Long id, ConsultaDTO consultaDTO) {
        return null;
    }

    @Override
    public void eliminarConsulta(Long id) {

    }

    @Override
    public List<ConsultaDTO> obtenerConsultasPorInformeContaining(String searchTerm) {
        return List.of();
    }

    @Override
    public List<ConsultaDTO> obtenerConsultasPorCita(Cita cita) {
        return List.of();
    }

    @Override
    public List<ConsultaDTO> obtenerConsultasPorCita(Long citaId) throws ParseException {
        return List.of();
    }
}
