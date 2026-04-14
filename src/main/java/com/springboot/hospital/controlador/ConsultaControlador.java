package com.springboot.hospital.controlador;

import com.springboot.hospital.dto.ConsultaDTO;
import com.springboot.hospital.mappers.CitaMapper;
import com.springboot.hospital.servicio.CitaServicio;
import com.springboot.hospital.servicio.ConsultaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/consultas")
public class ConsultaControlador {
    @Autowired
    private ConsultaServicio consultaServicio;

    @Autowired
    private CitaMapper citaMapper;

    @Autowired
    private CitaServicio citaServicio;

    @GetMapping
    public ResponseEntity<List<ConsultaDTO>> listarConsultas(){
        List<ConsultaDTO> consultas = consultaServicio.obtenerConsultas();
        return new ResponseEntity<>(consultas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaDTO> obtenerConsultaPorId(@PathVariable Long id) {
        Optional<ConsultaDTO> consulta = consultaServicio.obtenerConsultaPorId(id);
        return consulta.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(
                HttpStatus.NOT_FOUND));
    }

    @PostMapping()
    public ResponseEntity<ConsultaDTO> guardarConsulta(@RequestParam Long citaId, @RequestBody
    ConsultaDTO consultaDTO) throws ParseException {
        ConsultaDTO consultaCreada = consultaServicio.crearConsulta(citaId, consultaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(consultaCreada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultaDTO> actualizarConsulta(@PathVariable Long id, @RequestBody
    ConsultaDTO consultaDTO) throws ParseException {
        ConsultaDTO consultaActualizada = consultaServicio.modificarConsulta(id, consultaDTO);
        return consultaActualizada != null ? new ResponseEntity<>(consultaActualizada, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarConsulta(@PathVariable Long id) {
        consultaServicio.eliminarConsulta(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/busqueda")
    public ResponseEntity<List<ConsultaDTO>> listarConsultasPorInforme(@RequestParam String terminoABuscar) {
        List<ConsultaDTO> consultaDTOS = consultaServicio.obtenerConsultasPorInformeContaining(terminoABuscar);
        return new ResponseEntity<>(consultaDTOS, HttpStatus.OK);
    }

    @GetMapping("/cita/{citaId}")
    public ResponseEntity<List<ConsultaDTO>> listarConsultasPorCita(@PathVariable Long citaId) throws ParseException {
        List<ConsultaDTO> consultaDTOS = consultaServicio.obtenerConsultasPorCita(citaId);
        return new ResponseEntity<>(consultaDTOS, HttpStatus.OK);
    }
}
