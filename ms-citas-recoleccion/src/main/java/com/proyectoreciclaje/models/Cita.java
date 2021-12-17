package com.proyectoreciclaje.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "citas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cita {
	@Id
	private String id;
	private String fechaRecoleccion;
	private String horaRecoleccion;
	private String tipoMaterial;
	private double pesoMaterial;
	private Date fechaCreacionCita = new Date();
	private int idUsuario;
}
