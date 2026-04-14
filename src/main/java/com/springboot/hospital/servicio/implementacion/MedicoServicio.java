package com.springboot.hospital.servicio.implementacion;

import com.springboot.hospital.Modelo.Cita;
import com.springboot.hospital.Modelo.Medico;
import com.springboot.hospital.dto.CitaDTO;
import com.springboot.hospital.dto.MedicoDTO;
import com.springboot.hospital.mappers.CitaMapper;
import com.springboot.hospital.mappers.MedicoMapper;
import com.springboot.hospital.reposiorio.MedicoRepositorio;
import com.springboot.hospital.servicio.CitaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicoServicio implements com.springboot.hospital.servicio.MedicoServicio {
    @Autowired
    private MedicoRepositorio medicoRepositorio;

    @Autowired
    private CitaServicio citaServicio;

    @Autowired
    private CitaMapper citaMapper;

    @Autowired
    private MedicoMapper medicoMapper;

    @Override
    public List<MedicoDTO> obtenerMedicos() {
        List<Medico> medicos =  medicoRepositorio.findAll();
        return medicos.stream().map(medicoMapper::aDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<MedicoDTO> obtenerMedicoPorId(Long id) {
        Optional<Medico> optionalMedico = medicoRepositorio.findById(id);
        return optionalMedico.map(medicoMapper::aDTO);
    }

    @Override
    public MedicoDTO crearMedico(MedicoDTO medicoDTO) {
        Medico medico = medicoMapper.aEntidad(medicoDTO);
        medico =  medicoRepositorio.save(medico);
        return medicoMapper.aDTO(medico);
    }

    @Override
    public MedicoDTO actualizarMedico(Long id, MedicoDTO medicoDTO) {
        Optional<Medico> optionalMedico = medicoRepositorio.findById(id);
        if(optionalMedico.isPresent()){
            Medico medico = optionalMedico.get();
            medico.setNombre(medicoDTO.getNombre());
            medico.setEmail(medicoDTO.getEmail());
            medico.setEspecialidad(medicoDTO.getEspecialidad());
            medico = medicoRepositorio.save(medico);
            return medicoMapper.aDTO(medico);
        }
        return null;
    }

    @Override
    public void eliminarMedico(Long id) {
        Optional<Medico> optionalMedico = medicoRepositorio.findById(id);
        if(optionalMedico.isPresent()){
            Medico medico = optionalMedico.get();
            for(Cita cita : medico.getCitas()){
                citaServicio.borrarCita(cita.getId());
            }
            medicoRepositorio.deleteById(id);
        }
    }

    @Override
    public Collection<CitaDTO> obtenerCitasPorIdMedico(Long medicoId) {
        Optional<Medico> optionalMedico = medicoRepositorio.findById(medicoId);
        return optionalMedico.map(medico -> medico.getCitas().stream().map(citaMapper::aDTO).collect(
                Collectors.toList())).orElse(null);
    }

    @Override
    public List<MedicoDTO> obtenerMedicosPorEspecialidad(String especialidad) {
        List<Medico> medicos = medicoRepositorio.findByEspecialidad(especialidad);
        return medicos.stream().map(medicoMapper::aDTO).collect(Collectors.toList());
    }
}

