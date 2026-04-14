package com.springboot.hospital.controlador;

import com.springboot.hospital.Modelo.Cita;
import com.springboot.hospital.Modelo.StatusCita;
import com.springboot.hospital.dto.CitaDTO;
import com.springboot.hospital.mappers.CitaMapper;
import com.springboot.hospital.mappers.MedicoMapper;
import com.springboot.hospital.mappers.PacienteMapper;
import com.springboot.hospital.servicio.CitaServicio;
import com.springboot.hospital.servicio.MedicoServicio;
import com.springboot.hospital.servicio.PacienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/citas")
public class CitaControlador {
    @Autowired
    private CitaServicio citaServicio;

    @Autowired
    private MedicoServicio medicoServicio;

    @Autowired
    private PacienteServicio pacienteServicio;

    @Autowired
    private CitaMapper citaMapper;

    @Autowired
    private MedicoMapper medicoMapper;

    @Autowired
    private PacienteMapper pacienteMapper;

    @GetMapping
    public ResponseEntity<List<CitaDTO>> listarCitas() {
        List<CitaDTO> citas = citaServicio.obtenerCitas();
        return new ResponseEntity<>(citas,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CitaDTO> listarCitaPorId(@PathVariable Long id) {
        Optional<CitaDTO> citaDTOOptional = citaServicio.obtenerCitaPorId(id);
        return citaDTOOptional.map(cita -> new ResponseEntity<>(cita, HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{idPaciente}/{idMedico}")
    public ResponseEntity<CitaDTO> guardarCita(@RequestBody CitaDTO citaDTO, @PathVariable Long idPaciente,
                                               @PathVariable Long idMedico) throws ParseException {
        Cita nuevaCita = citaServicio.crearCita(citaDTO, idPaciente, idMedico);
        if(nuevaCita == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        CitaDTO nuevaCitaDTO = citaMapper.aDTO(nuevaCita);
        return new ResponseEntity<>(nuevaCitaDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CitaDTO> actualizarCita(@PathVariable Long id, @RequestBody CitaDTO citaDTO)
            throws ParseException {
        CitaDTO citaActualizada = citaServicio.modificarCita(id, citaDTO);
        if(citaActualizada != null){
            return ResponseEntity.ok(citaActualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCita(@PathVariable Long id) throws ParseException {
        citaServicio.borrarCita(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/paciente/{pacienteId}")
    public List<CitaDTO> listarCitasPorIdPaciente(@PathVariable Long pacienteId) {
        return citaServicio.obtenerCitasPorIdPaciente(pacienteId);
    }

    @GetMapping("/medico/{medicoId}")
    public List<CitaDTO> listarCitasPorId(@PathVariable Long medicoId) {
        return citaServicio.obtenerCitasPorIdMedico(medicoId);
    }

    @GetMapping("/status/{statusCita}")
    public List<CitaDTO> listarCitasPorStatus(@PathVariable StatusCita statusCita) {
        return citaServicio.obtenerCitasPorStatusCita(String.valueOf(statusCita));
    }
}
