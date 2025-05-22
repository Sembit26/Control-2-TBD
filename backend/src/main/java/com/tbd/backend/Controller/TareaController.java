package com.tbd.backend.Controller;

import com.tbd.backend.Entity.Tarea;
import com.tbd.backend.Service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TareaController {
    @Autowired
    private TareaService tareaService;

    @PostMapping("/tarea/crear")
    public Tarea crearTarea(@RequestBody Tarea tarea) {
        return tareaService.crear(tarea);
    }

    @GetMapping("/tarea/All")
    public List<Tarea> getAllTareas() {
        return tareaService.getAll();
    }

    @GetMapping("/tarea/user/{idUser}")
    public List<Tarea> getTareasByUser(@PathVariable int idUser) {
        return tareaService.getAllUser(idUser);
    }

    @GetMapping("/tarea/get/{id}")
    public Tarea getTareaById(@PathVariable Integer id) {
        return tareaService.getById(id);
    }

    @PutMapping("/tarea/update/{id}")
    public String updateTarea(@PathVariable int id, @RequestBody Tarea tarea) {
        return tareaService.update(tarea,id);
    }

    @DeleteMapping("/tarea/delete/{id}")
    public void deleteTarea(@PathVariable int id) {
        tareaService.delete(id);
    }

    @GetMapping("/tarea/search/{status}/{keyword}/{idUser}")
    public List<Tarea> searchStatusOnly(@PathVariable(required = false) Boolean status,@PathVariable String keyword, @PathVariable int idUser) {
        return tareaService.searchFilter(status,keyword,idUser);
    }

    @GetMapping("/tarea/search/{keyword}/{idUser}")
    public List<Tarea> searchKeywordOnly(@PathVariable String keyword, @PathVariable int idUser) {
        return tareaService.searchFilterKeyword(keyword,idUser);
    }

}
