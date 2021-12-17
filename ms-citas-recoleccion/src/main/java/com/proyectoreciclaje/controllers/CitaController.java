package com.proyectoreciclaje.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.proyectoreciclaje.models.Cita;
import com.proyectoreciclaje.services.CitasService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/citas")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@Slf4j
public class CitaController {

	@Autowired
	CitasService citasService;

	@PostMapping
	public ResponseEntity<Cita> crearCita(@Validated @RequestBody Cita cita) {
		Cita nuevaCita = citasService.guardarCita(cita);
		log.info("Cita: " + nuevaCita);
		return ResponseEntity.ok(nuevaCita);

	}

	@PutMapping("/{id}")
	public ResponseEntity<?> actualizarCita(@PathVariable("id") int id, @Validated @RequestBody Cita cita) {
		if (citasService.citaporID(id) == null) {
			return new ResponseEntity<String>("La cita por el ID no existe", HttpStatus.NOT_FOUND);
		}

		Cita updateCita = citasService.editarCita(id, cita);
		return new ResponseEntity<Cita>(updateCita, HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarCita(@PathVariable("id") int id) {
		Cita citaBorrada = citasService.citaporID(id);
		if (citaBorrada == null) {
			return new ResponseEntity<String>("La cita por el ID no existe", HttpStatus.NOT_FOUND);
		}

		citasService.eliminarCita(id);
		return new ResponseEntity<Cita>(citaBorrada, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<?> listarCitas() {

		List<Cita> allCitas = citasService.listarCitas();
		if (allCitas.size() < 1) {
			return new ResponseEntity<String>("No hay citas", HttpStatus.OK);
		}

		return new ResponseEntity<List<Cita>>(allCitas, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> citaById(@PathVariable("id") int id) {
		if (citasService.citaporID(id) == null) {
			return new ResponseEntity<String>("La cita por el ID no existe", HttpStatus.NOT_FOUND);
		}
		Cita citaById = citasService.citaporID(id);
		return new ResponseEntity<Cita>(citaById, HttpStatus.OK);
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<?> citaByUserId(@PathVariable("id") int id) {
		if (citasService.citasByIdUser(id) == null) {
			return new ResponseEntity<String>("El ID de usuario indicado no tiene citas de recolección asignadas",
					HttpStatus.NOT_FOUND);
		}
		List<Cita> citaByUserId = citasService.citasByIdUser(id);
		if (citaByUserId.size() < 1) {
			return new ResponseEntity<String>("No hay citas asignadas para el usuario", HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<Cita>>(citaByUserId, HttpStatus.OK);
	}

}
