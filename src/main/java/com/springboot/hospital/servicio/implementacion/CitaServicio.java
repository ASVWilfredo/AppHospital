package com.springboot.hospital.servicio.implementacion;

import com.springboot.hospital.Modelo.*;
import com.springboot.hospital.dto.CitaDTO;
import com.springboot.hospital.dto.MedicoDTO;
import com.springboot.hospital.dto.PacienteDTO;
import com.springboot.hospital.mappers.CitaMapper;
import com.springboot.hospital.mappers.MedicoMapper;
import com.springboot.hospital.mappers.PacienteMapper;
import com.springboot.hospital.reposiorio.CitaRepositorio;
import com.springboot.hospital.reposiorio.ConsultaRepositorio;
import com.springboot.hospital.reposiorio.MedicoRepositorio;
import com.springboot.hospital.reposiorio.PacienteRepositorio;
import com.springboot.hospital.servicio.MedicoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CitaServicio implements com.springboot.hospital.servicio.CitaServicio {
    @Autowired
    private CitaRepositorio  citaRepositorio;

    @Autowired
    private PacienteRepositorio  pacienteRepositorio;

    @Autowired
    private MedicoRepositorio  medicoRepositorio;

    @Autowired
    private ConsultaRepositorio consultaRepositorio;

    @Autowired
    private CitaMapper  citaMapper;

    @Autowired
    private PacienteMapper pacienteMapper;

    @Autowired
    private MedicoMapper medicoMapper;

    @Autowired
    private MedicoServicio  medicoServicio;

    @Autowired
    private PacienteServicio pacienteServicio;

    @Override
    public List<CitaDTO> obtenerCitas() {
        List<Cita>  citas = citaRepositorio.findAll();
        return citas.stream().map(citaMapper::aDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<CitaDTO> obtenerCitaPorId(Long id) {
        Optional<Cita> citaOptional = citaRepositorio.findById(id);
        return citaOptional.map(citaMapper::aDTO);
    }

    @Override
    public Cita crearCita(CitaDTO citaDTO, Long idPaciente, Long idMedico) throws ParseException {
        PacienteDTO pacienteDTO = pacienteServicio.obtenerPacientePorId(idPaciente).orElse(null);
        MedicoDTO medicoDTO = medicoServicio.obtenerMedicoPorId(idMedico).orElse(null);
        if(pacienteDTO == null || medicoDTO == null){
            return null;
        }
        citaDTO.setPacienteId(idPaciente);
        citaDTO.setMedicoId(idMedico);
        Cita cita = citaMapper.aEntidad(citaDTO);

        return citaRepositorio.save(cita);
    }

    @Override
    public CitaDTO modificarCita(Long id, CitaDTO citaDTO) throws ParseException {
        Optional<Cita> citaOptional = citaRepositorio.findById(id);
        if(citaOptional.isPresent()){
            Cita cita = citaOptional.get();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date fecha = sdf.parse(citaDTO.getFecha());
            cita.setFecha(fecha);
            cita.setCancelada(citaDTO.isCancelado());
            cita.setStatusCita(StatusCita.valueOf(citaDTO.getStatusCita()));

            Paciente paciente = pacienteRepositorio.findById(citaDTO.getPacienteId()).orElse(null);
            if (paciente == null) {
                return null;
            }
            cita.setPaciente(paciente);

            Medico medico = medicoRepositorio.findById(citaDTO.getMedicoId()).orElse(null);
            if (medico == null) {
                return null;
            }
            cita.setMedico(medico);

            return citaMapper.aDTO(citaRepositorio.save(cita));
        }
        return null;
    }

    @Override
    public void borrarCita(Long id) {
        Optional<Cita> citaOptional = citaRepositorio.findById(id);
        if(citaOptional.isPresent()){
            Cita cita = citaOptional.get();
            if(cita.getConsulta() != null){
                Consulta consulta = cita.getConsulta();
                consulta.setCita(null);
                consultaRepositorio.delete(consulta);
            }
            citaRepositorio.delete(cita);
        }
    }

    @Override
    public List<CitaDTO> obtenerCitasPorIdPaciente(Long pacienteId) {
        List<Cita> citas = citaRepositorio.findByPacienteId(pacienteId);
        return citas.stream().map(citaMapper::aDTO).collect(Collectors.toList());
    }

    @Override
    public List<CitaDTO> obtenerCitasPorIdMedico(Long medicoId) {
        List<Cita> citas =  citaRepositorio.findByMedicoId(medicoId);
        return citas.stream().map(citaMapper::aDTO).collect(Collectors.toList());
    }

    @Override
    public List<CitaDTO> obtenerCitasPorStatusCita(String statusCita) {
        List<Cita> citas =  citaRepositorio.findByStatusCita(StatusCita.valueOf(statusCita));
        return citas.stream().map(citaMapper::aDTO).collect(Collectors.toList());
    }
}
