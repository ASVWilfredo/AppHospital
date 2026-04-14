package com.springboot.hospital.servicio.implementacion;

import com.springboot.hospital.Modelo.Cita;
import com.springboot.hospital.Modelo.Paciente;
import com.springboot.hospital.dto.CitaDTO;
import com.springboot.hospital.dto.PacienteDTO;
import com.springboot.hospital.mappers.CitaMapper;
import com.springboot.hospital.mappers.PacienteMapper;
import com.springboot.hospital.reposiorio.CitaRepositorio;
import com.springboot.hospital.reposiorio.PacienteRepositorio;
import com.springboot.hospital.servicio.CitaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PacienteServicio implements com.springboot.hospital.servicio.PacienteServicio {
    @Autowired
    private PacienteRepositorio  pacienteRepositorio;

    @Autowired
    private CitaRepositorio citaRepositorio;

    @Autowired
    private PacienteMapper pacienteMapper;

    @Autowired
    private CitaMapper citaMapper;

    @Autowired
    private CitaServicio  citaServicio;

    @Override
    public List<PacienteDTO> obtenerPacientes() {
        List<Paciente> pacientes = pacienteRepositorio.findAll();
        return pacientes.stream().map(pacienteMapper::aDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<PacienteDTO> obtenerPacientePorId(Long id) {
        Optional<Paciente> optionalPacienteaciente = pacienteRepositorio.findById(id);
        return optionalPacienteaciente.map(pacienteMapper::aDTO);
    }

    @Override
    public PacienteDTO crearPaciente(PacienteDTO pacienteDTO) {
        Paciente paciente = pacienteMapper.aEntidad(pacienteDTO);
        paciente = pacienteRepositorio.save(paciente);
        return pacienteMapper.aDTO(paciente);
    }

    @Override
    public PacienteDTO actualizarPaciente(Long id, PacienteDTO pacienteDTO) {
        Optional<Paciente> optionalPaciente = pacienteRepositorio.findById(id);
        if(optionalPaciente.isPresent()){
            Paciente paciente = optionalPaciente.get();
            paciente.setNombre(pacienteDTO.getNombre());
            paciente.setFechaNacimiento(pacienteDTO.getFechaNacimiento());
            if (pacienteDTO.getEnfermedad() != null) {
                paciente.setEnfermedad(pacienteDTO.getEnfermedad());
            }
            paciente = pacienteRepositorio.save(paciente);
            return pacienteMapper.aDTO(paciente);
        }
        return null;
    }

    @Override
    public void eliminarPaciente(Long id) {
        Optional<Paciente> optionalPaciente = pacienteRepositorio.findById(id);
        if(optionalPaciente.isPresent()){
            Paciente paciente = optionalPaciente.get();
            for(Cita cita: paciente.getCita()) {
                citaServicio.borrarCita(cita.getId());
            }
            pacienteRepositorio.deleteById(id);
        }
    }

    @Override
    public Collection<CitaDTO> obtenerCitasPorIdPaciente(Long idPaciente) {
        Optional<Paciente>  optionalPaciente = pacienteRepositorio.findById(idPaciente);
        return optionalPaciente.map(paciente -> paciente.getCita().stream().map(citaMapper::aDTO).collect(
                Collectors.toList())).orElse(null);
    }
}
