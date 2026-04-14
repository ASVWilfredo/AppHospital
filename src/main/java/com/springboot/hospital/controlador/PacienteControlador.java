package com.springboot.hospital.controlador;

import com.springboot.hospital.dto.CitaDTO;
import com.springboot.hospital.dto.PacienteDTO;
import com.springboot.hospital.servicio.implementacion.PacienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteControlador {
    @Autowired
    private PacienteServicio pacienteServicio;

    @GetMapping
    public ResponseEntity<List<PacienteDTO>> listarPacientes() {
        List<PacienteDTO> pacientesDTOS = pacienteServicio.obtenerPacientes();
        return new ResponseEntity<>(pacientesDTOS, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<PacienteDTO> listarPacientePorId(@PathVariable Long id) {
        return pacienteServicio.obtenerPacientePorId(id).map(pacienteDTO -> new ResponseEntity<>(pacienteDTO,
                HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<PacienteDTO> guardarPaciente(@RequestBody PacienteDTO pacienteDTO) {
        PacienteDTO pacienteDTOCreado = pacienteServicio.crearPaciente(pacienteDTO);
        return new ResponseEntity<>(pacienteDTOCreado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteDTO> actualizarPaciente(@PathVariable Long id, @RequestBody PacienteDTO pacienteDTO) {
        PacienteDTO actualizarPaciente = pacienteServicio.actualizarPaciente(id, pacienteDTO);
        if(actualizarPaciente != null){
            return new ResponseEntity<>(actualizarPaciente, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> eliminarPaciente(@PathVariable Long id) {
        pacienteServicio.eliminarPaciente(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}/citas")
    public ResponseEntity<Collection<CitaDTO>> listarCitasPorPacienteId(@PathVariable Long id) {
        Collection<CitaDTO> citas = pacienteServicio.obtenerCitasPorIdPaciente(id);
        return new ResponseEntity<>(citas, HttpStatus.OK);
    }
}
