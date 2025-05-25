package com.tbd.backend.Repository;

import com.tbd.backend.Entity.Tarea;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long> {

    // Obtener todas las tareas de un usuario específico
    List<Tarea> findByUsuarioId(Long usuarioId);

    // Obtener todas las tareas de un sector específico
    List<Tarea> findBySectorId(Long sectorId);

    // Obtener todas las tareas completadas o no completadas
    List<Tarea> findByCompletada(Boolean completada);

    // Buscar por palabra clave en nombre o descripción (ignora mayúsculas/minúsculas)
    List<Tarea> findByNombreContainingIgnoreCaseOrDescripcionContainingIgnoreCase(String nombre, String descripcion);

    // Buscar por estado y palabra clave combinados
    List<Tarea> findByCompletadaAndNombreContainingIgnoreCaseOrCompletadaAndDescripcionContainingIgnoreCase(
            Boolean completada1, String nombre, Boolean completada2, String descripcion);

    // Tareas que vencen entre ahora y cierta fecha futura, y que aún no están completadas
    List<Tarea> findByFechaTerminoBetweenAndCompletada(Date desde, Date hasta, Boolean completada);

    Page<Tarea> findByCompletadaAndNombreContainingIgnoreCaseOrCompletadaAndDescripcionContainingIgnoreCase(
            Boolean c1, String nombre,
            Boolean c2, String descripcion,
            Pageable pageable
    );

    Page<Tarea> findByUsuarioId(Long usuarioId, Pageable pageable);

    Page<Tarea> findBySectorId(Long sectorId, Pageable pageable);

    Page<Tarea> findByCompletada(Boolean completada, Pageable pageable);

    Page<Tarea> findByNombreContainingIgnoreCaseOrDescripcionContainingIgnoreCase(String nombre, String descripcion, Pageable pageable);


}
