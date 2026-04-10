package com.springboot.hospital.dto;

import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class ConsultaDTO {
    private Long id;
    private String fechaConsulta;
    private String informe;
    private CitaDTO citaDTO;

    public Date getFechaConsultaAsDate() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return sdf.parse(this.fechaConsulta);
    }

    public void setFechaConsultaFromDate(Date fechaConsulta) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        this.fechaConsulta = sdf.format(fechaConsulta);
    }
}
