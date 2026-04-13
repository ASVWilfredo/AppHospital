package com.springboot.hospital.controlador;

import com.springboot.hospital.servicio.CitaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/citas")
public class CitaControlador {
    @Autowired
    private CitaServicio citaServicio;


}
