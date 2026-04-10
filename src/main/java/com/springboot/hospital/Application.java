package com.springboot.hospital;

import com.springboot.hospital.Modelo.*;
import com.springboot.hospital.reposiorio.CitaRepositorio;
import com.springboot.hospital.reposiorio.ConsultaRepositorio;
import com.springboot.hospital.reposiorio.MedicoRepositorio;
import com.springboot.hospital.reposiorio.PacienteRepositorio;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	//@Bean
	CommandLineRunner iniciar(PacienteRepositorio pacienteRepositorio, MedicoRepositorio medicoRepositorio,
							  CitaRepositorio citaRepositorio, ConsultaRepositorio  consultaRepositorio) {
		return args -> {
			Stream.of("Wilfredo", "Jose Ramon", "Julio").forEach(nombre -> {
				Paciente paciente = new Paciente();
				paciente.setNombre(nombre);
				paciente.setFechaNacimiento(new Date());
				paciente.setEnfermedad(false);
				pacienteRepositorio.save(paciente);
			});
			Stream.of("Alan", "Maria", "Jaime").forEach(nombre -> {
				Medico medico = new Medico();
				medico.setNombre(nombre);
				medico.setEmail(nombre + ((int) Math.random() * 9) + "@gmail.com");
				medico.setEspecialidad(Math.random() > 0.5 ? "Medicina Familiar" : "Medicina Interna");
				medicoRepositorio.save(medico);
			});
			Paciente paciente1 = pacienteRepositorio.findById(1L).orElse(null);
			Medico  medico = medicoRepositorio.findByNombre("Alan");
			Cita cita1 = new Cita();
			cita1.setFecha(new Date());
			cita1.setStatusCita(StatusCita.PENDIENTE);
			cita1.setMedico(medico);
			cita1.setPaciente(paciente1);
			citaRepositorio.save(cita1);

			Cita citaBD1 = citaRepositorio.findById(1L).orElse(null);
			Consulta consulta = new Consulta();
			consulta.setFechaConsulta(new Date());
			consulta.setCita(citaBD1);
			consulta.setInforme("Informe de la consulta");
			consultaRepositorio.save(consulta);
		};
	}
}
