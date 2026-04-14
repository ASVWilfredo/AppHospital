package com.springboot.hospital.servicio.implementacion;

import com.springboot.hospital.Modelo.Cita;
import com.springboot.hospital.Modelo.Consulta;
import com.springboot.hospital.Modelo.StatusCita;
import com.springboot.hospital.dto.CitaDTO;
import com.springboot.hospital.dto.ConsultaDTO;
import com.springboot.hospital.mappers.CitaMapper;
import com.springboot.hospital.mappers.ConsultaMapper;
import com.springboot.hospital.reposiorio.CitaRepositorio;
import com.springboot.hospital.reposiorio.ConsultaRepositorio;
import com.springboot.hospital.servicio.CitaServicio;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConsultaServicio implements com.springboot.hospital.servicio.ConsultaServicio {
    @Autowired
    private ConsultaRepositorio consultaRepositorio;

    @Autowired
    private CitaRepositorio citaRepositorio;

    @Autowired
    private CitaMapper citaMapper;

    @Autowired
    private ConsultaMapper consultaMapper;

    @Autowired
    private CitaServicio citaServicio;


    @Override
    public List<ConsultaDTO> obtenerConsultas() {
        List<Consulta> consultas = consultaRepositorio.findAll();
        return consultas.stream().map(consultaMapper::aDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<ConsultaDTO> obtenerConsultaPorId(Long id) {
        Optional<Consulta> consulta = consultaRepositorio.findById(id);
        return consulta.map(consultaMapper::aDTO);
    }

    @Override
    public ConsultaDTO crearConsulta(Long citaId, ConsultaDTO consultaDTO) throws ParseException {
        citaServicio.obtenerCitaPorId(citaId).orElseThrow(() -> new EntityNotFoundException(
                "Cita no encontrada"));
        Cita cita = citaRepositorio.findById(citaId).orElseThrow(() -> new EntityNotFoundException(
                "Cita no encontrada"));
        Consulta consulta = new Consulta();
        consulta.setCita(cita);
        consulta.setFechaConsulta(new Date());
        consulta.setInforme(consultaDTO.getInforme());

        Consulta consultaCreada = consultaRepositorio.save(consulta);
        return consultaMapper.aDTO(consultaCreada);
    }

    @Override
    public ConsultaDTO modificarConsulta(Long id, ConsultaDTO consultaDTO) throws ParseException {
        Optional<Consulta> consultaOptional = consultaRepositorio.findById(id);
        if(consultaOptional.isPresent()) {
            Consulta consulta = consultaOptional.get();
            consulta.setFechaConsulta(consultaDTO.getFechaConsultaAsDate());
            consulta.setInforme(consultaDTO.getInforme());
            Consulta modificarConsulta = consultaRepositorio.save(consulta);
            CitaDTO citaDTO = consultaDTO.getCitaDTO();
            if(citaDTO != null) {
                Cita cita = consulta.getCita();
                if(cita != null) {
                    cita.setFecha(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(citaDTO.getFecha()));
                    cita.setStatusCita(StatusCita.valueOf(citaDTO.getStatusCita()));
                    citaRepositorio.save(cita);
                }
                citaServicio.modificarCita(cita.getId(), citaDTO);
            }
            return consultaMapper.aDTO(modificarConsulta);
        }
        return null;
    }

    @Override
    public void eliminarConsulta(Long id) {
        Optional<Consulta> consultaOptional = consultaRepositorio.findById(id);
        if (consultaOptional.isPresent()) {
            Consulta consulta = consultaOptional.get();
            Cita cita = consulta.getCita();
            if (cita != null) {
                cita.setConsulta(null);
                consulta.setCita(null);
                citaRepositorio.save(cita);
            }
            consultaRepositorio.delete(consulta);
        }
    }

    @Override
    public List<ConsultaDTO> obtenerConsultasPorInformeContaining(String searchTerm) {
        List<Consulta> consultas = consultaRepositorio.findByInformeContainingIgnoreCase(searchTerm);
        return consultas.stream().map(consultaMapper::aDTO).collect(Collectors.toList());
    }

    @Override
    public List<ConsultaDTO> obtenerConsultasPorCita(Cita cita) {
        List<Consulta> consultas = consultaRepositorio.findByCita(cita);
        return consultas.stream().map(consultaMapper::aDTO).collect(Collectors.toList());
    }

    @Override
    public List<ConsultaDTO> obtenerConsultasPorCita(Long citaId) throws ParseException {
        Optional<Cita> citaOptional = citaRepositorio.findById(citaId);
        if(citaOptional.isPresent()) {
            Cita cita = citaOptional.get();
            if(cita.getId() != null) {
                citaRepositorio.save(cita);
            }
            List<Consulta> consultas = consultaRepositorio.findByCita(cita);
            List<ConsultaDTO> consultasDTO = new ArrayList<>();
            for (Consulta consulta : consultas) {
                ConsultaDTO consultaDTO = new ConsultaDTO();
                consultaDTO.setId(consulta.getId());
                consultaDTO.setFechaConsulta(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(consulta
                        .getFechaConsulta()));
                consultaDTO.setInforme(consulta.getInforme());
                consultasDTO.add(consultaDTO);
            }
            return consultasDTO;
        } else {
            throw new EntityNotFoundException("Cita no encontrada con el ID: " + citaId);
        }
    }
}
