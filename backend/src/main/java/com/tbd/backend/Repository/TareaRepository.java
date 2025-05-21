package com.tbd.backend.Repository;

import com.tbd.backend.Entity.Tarea;

import java.util.List;

public interface TareaRepository {
    Tarea crear(Tarea tarea);
    Tarea actualizar(Tarea tarea);
    void eliminar(Long id);
    Tarea getById(Long id);
    List<Tarea> getAll();
    List<Tarea> getByUser(Long idUser);
    List<Tarea> getAllUser(Integer id_user);
}
