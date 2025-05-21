package com.tbd.backend.Service;

import com.tbd.backend.Entity.Tarea;
import com.tbd.backend.Repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    public Tarea crear(Tarea tarea) {
        return tareaRepository.crear(tarea);
    }

    public List<Tarea> getAll() {
        return tareaRepository.getAll();
    }

    public Tarea getById(Integer id) {
        return tareaRepository.getById(id);
    }

    public String update(Tarea tarea, Integer id) {
        return tareaRepository.update(tarea, id);
    }

    public void delete(Integer id) {
        tareaRepository.delete(id);
    }

    /**
     * Busca tareas filtrando por estado y opcionalmente por palabra clave.
     * Si keyword es "empty" (como cadena), solo filtra por estado.
     */
    public List<Tarea> searchFilter(Boolean status, String keyword, Integer idUser) {
        if (keyword != null && !Objects.equals(keyword, "empty")) {
            return tareaRepository.searchByKeywordAndStatus(status, keyword, idUser);
        } else {
            return tareaRepository.searchByStatus(status, idUser);
        }
    }

    public List<Tarea> searchFilterKeyword(String keyword, Integer idUser) {
        return tareaRepository.searchByKeyword(keyword, idUser);
    }

    public List<Tarea> getAllUser(Integer idUser) {
        return tareaRepository.getAllUser(idUser);
    }
}
