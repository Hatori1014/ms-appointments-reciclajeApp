package com.proyectoreciclaje.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.proyectoreciclaje.models.Cita;

@Repository
public interface CitasRepository extends MongoRepository<Cita, Integer> {

}