package com.tbd.backend.Service;

import com.tbd.backend.Entity.Tarea;
import com.tbd.backend.Repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    //----------------------- CRUD ----------------
    // Crear tarea
    public Tarea crearTarea(Tarea tarea) {
        tarea.setCompletada(false); // por defecto pendiente al crear
        return tareaRepository.save(tarea);
    }

    //Ver toda las tareas pendientes y completadas de un usuario
    public List<Tarea> obtenerTareasPorUsuario(Long usuarioId) {
        return tareaRepository.findByUsuarioId(usuarioId);
    }

    // Obtener todas las tareas asociadas a un sector por su ID
    public List<Tarea> obtenerTareasPorSector(Long sectorId) {
        return tareaRepository.findBySectorId(sectorId);
    }

    // Editar tarea
    public Tarea editarTarea(Long id, Tarea tareaDetalles) {
        Tarea tarea = tareaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada con id: " + id));

        tarea.setNombre(tareaDetalles.getNombre());
        tarea.setDescripcion(tareaDetalles.getDescripcion());
        tarea.setFechaTermino(tareaDetalles.getFechaTermino());
        tarea.setSector(tareaDetalles.getSector());
        // opcional: no cambiar usuario aquí, o sí según reglas de negocio
        return tareaRepository.save(tarea);
    }

    // Eliminar tarea
    public void eliminarTarea(Long id) {
        tareaRepository.deleteById(id);
    }

    //------------------ Marcar tarea como completada -------------------
    // Marcar tarea como completada (se ingresa el id de la tarea)
    public Tarea marcarCompletada(Long id) {
        Tarea tarea = tareaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada con id: " + id));
        tarea.setCompletada(true);
        return tareaRepository.save(tarea);
    }

    //-------------------- Filtros y Búsqueda ----------------
    // Listar tareas completadas
    public List<Tarea> listarTareasCompletadas() {
        return tareaRepository.findByCompletada(true);
    }

    // Listar tareas pendientes (no completadas)
    public List<Tarea> listarTareasPendientes() {
        return tareaRepository.findByCompletada(false);
    }


    // Buscar tareas por palabra clave en nombre o descripción (sin filtro de estado)
    public List<Tarea> buscarPorPalabraClave(String palabraClave) {
        return tareaRepository.findByNombreContainingIgnoreCaseOrDescripcionContainingIgnoreCase(palabraClave, palabraClave);
    }

    // Filtrar por estado y palabra clave combinados
    public List<Tarea> filtrarPorEstadoYPalabraClave(Boolean completada, String palabraClave) {
        boolean tienePalabraClave = palabraClave != null && !palabraClave.isEmpty();

        if (completada == null && !tienePalabraClave) {
            return tareaRepository.findAll();
        }
        if (completada == null) {
            // solo palabra clave
            return buscarPorPalabraClave(palabraClave);
        }
        if (!tienePalabraClave) {
            // solo estado
            return completada ? listarTareasCompletadas() : listarTareasPendientes();
        }
        // combinamos estado y palabra clave
        return tareaRepository.findByCompletadaAndNombreContainingIgnoreCaseOrCompletadaAndDescripcionContainingIgnoreCase(
                completada, palabraClave,
                completada, palabraClave);
    }
}
