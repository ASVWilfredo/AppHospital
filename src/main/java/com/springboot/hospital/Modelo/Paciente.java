package com.springboot.hospital.Modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    private String especialidad;
    private boolean enfermedad;

    @OneToMany(mappedBy = "paciente", fetch = FetchType.LAZY)
    private Collection<Cita> cita;
}
