package com.proyectoreciclaje.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectoreciclaje.models.Cita;
import com.proyectoreciclaje.repositories.CitasRepository;

@Service
public class CitasService {
    
    @Autowired
    CitasRepository citaRepository;

    public Cita guardarCita(Cita cita) {
        return citaRepository.insert(cita);
    }

    public Cita editarCita(int idCita, Cita cita) {
        return citaRepository.save(cita);
    }

    public void eliminarCita(int idCita) {
        citaRepository.deleteById(idCita);
    }

    public List<Cita> listarCitas() {
        return citaRepository.findAll();
    }


    public Cita citaporID(int id) {
        return citaRepository.findById(id).orElse(null);
    }

    public List<Cita> citasByIdUser(int userId){
        List<Cita> allCitas = listarCitas();
        List<Cita> citasByUser = new ArrayList<Cita>();

        for (Cita cita : allCitas) {
            if(cita.getIdUsuario() == userId) {
                citasByUser.add(cita);
            }
        }
        return citasByUser;


    }
}
