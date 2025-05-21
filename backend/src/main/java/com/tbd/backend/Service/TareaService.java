package com.tbd.backend.Service;

import com.tbd.backend.Entity.Tarea;
import com.tbd.backend.Repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    public Tarea crearTarea(Tarea tarea) {
        return tareaRepository.crear(tarea);
    }

    public Tarea actualizarTarea(Tarea tarea) {
        return tareaRepository.actualizar(tarea);
    }

    public void eliminarTarea(Long id) {
        tareaRepository.eliminar(id);
    }

    public Tarea obtenerTareaPorId(Long id) {
        return tareaRepository.getById(id);
    }

    public List<Tarea> obtenerTodas() {
        return tareaRepository.getAll();
    }

    public List<Tarea> obtenerPorUsuario(Long idUser) {
        return tareaRepository.getByUser(idUser);
    }

    public List<Tarea> getAllUser(Integer idUser){
        return tareaRepository.getAllUser(idUser);
    }
}
