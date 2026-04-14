package com.springboot.hospital.controlador;

import com.springboot.hospital.dto.CitaDTO;
import com.springboot.hospital.dto.MedicoDTO;
import com.springboot.hospital.servicio.MedicoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/medicos")
public class MedicoControlador {
    @Autowired
    private MedicoServicio medicoServicio;

    @GetMapping
    public ResponseEntity<List<MedicoDTO>> listarMedicos(){
        List<MedicoDTO> medicos = medicoServicio.obtenerMedicos();
        return new ResponseEntity<>(medicos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoDTO> listarMedicoPorId(@PathVariable Long id){
        return medicoServicio.obtenerMedicoPorId(id).map(medico -> new ResponseEntity<>(medico, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<MedicoDTO> guardarMedico(@RequestBody MedicoDTO medicoDTO){
        MedicoDTO medicoCreado =  medicoServicio.crearMedico(medicoDTO);
        return new ResponseEntity<>(medicoCreado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicoDTO> actualizarMedico(@PathVariable Long id, @RequestBody MedicoDTO medicoDTO) {
        MedicoDTO modificarMedico = medicoServicio.actualizarMedico(id, medicoDTO);
        if(modificarMedico != null){
            return new ResponseEntity<>(modificarMedico, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMedico(@PathVariable Long id){
        medicoServicio.eliminarMedico(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}/citas")
    public ResponseEntity<Collection<CitaDTO>> listarCitasPorMedico(@PathVariable Long id){
        Collection<CitaDTO> citas = medicoServicio.obtenerCitasPorIdMedico(id);
        if(citas != null){
            return new ResponseEntity<>(citas, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/especialidad/{especialidad}")
    public ResponseEntity<List<MedicoDTO>> listarMedicosPorEspecialidad(@PathVariable String especialidad){
        List<MedicoDTO> medicos = medicoServicio.obtenerMedicosPorEspecialidad(especialidad);
        return new ResponseEntity<>(medicos, HttpStatus.OK);
    }
}
