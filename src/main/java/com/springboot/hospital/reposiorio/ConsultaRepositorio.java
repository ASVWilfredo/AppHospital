package com.springboot.hospital.reposiorio;

import com.springboot.hospital.Modelo.Cita;
import com.springboot.hospital.Modelo.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultaRepositorio extends JpaRepository<Consulta, Long> {
    List<Consulta> findByCita(Cita cita);
    List<Consulta> findByInformeContainingIgnoreCase(String searchTerm); // Encuentra informe sin importa si esta en mayuscula (CONTAINING IGNORE CASE
}
