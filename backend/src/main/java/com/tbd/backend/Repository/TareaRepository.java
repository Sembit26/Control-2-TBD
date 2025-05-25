package com.tbd.backend.Repository;

import com.tbd.backend.Entity.Tarea;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

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

    // ------------------------------ PREGUNTAS -------------------------
    // CAMBIAR DIALECTO A POSTGRESQL???
    @Query(value = """
    SELECT s.nombre AS sector, COUNT(*) AS total
    FROM tarea t
    JOIN sector s ON t.sector_id = s.id
    WHERE t.usuario_id = :usuarioId
    GROUP BY s.nombre
    ORDER BY total DESC
    """, nativeQuery = true)
    List<Map<String, Object>> contarTareasPorSectorUsuario(@Param("usuarioId") Long usuarioId);

    @Query(value = """
    SELECT t.*
    FROM tarea t
    JOIN usuario u ON u.id = :usuarioId
    WHERE t.completada = false
      AND t.usuario_id != :usuarioId
    ORDER BY ST_Distance(t.ubicacion, u.ubicacion)
    LIMIT 1
    """, nativeQuery = true)
    Tarea tareaPendienteMasCercana(@Param("usuarioId") Long usuarioId);

    @Query(value = """
    SELECT s.nombre AS sector, COUNT(*) AS total
    FROM tarea t
    JOIN sector s ON s.id = t.sector_id
    JOIN usuario u ON u.id = :usuarioId
    WHERE t.completada = true
      AND ST_DWithin(t.ubicacion, u.ubicacion, 2000)
    GROUP BY s.nombre
    ORDER BY total DESC
    LIMIT 1
    """, nativeQuery = true)
    Map<String, Object> sectorConMasTareasEnRadio(@Param("usuarioId") Long usuarioId);

    @Query(value = """
    SELECT AVG(ST_Distance(t.ubicacion, u.ubicacion)) AS promedio
    FROM tarea t
    JOIN usuario u ON u.id = :usuarioId
    WHERE t.completada = true
      AND t.usuario_id = :usuarioId
    """, nativeQuery = true)
    Double promedioDistanciaTareasCompletadas(@Param("usuarioId") Long usuarioId);


    @Query(value = """
    SELECT s.nombre AS sector, COUNT(*) AS total
    FROM tarea t
    JOIN sector s ON s.id = t.sector_id
    WHERE t.completada = false
    GROUP BY s.nombre
    ORDER BY total DESC
    """, nativeQuery = true)
    List<Map<String, Object>> sectoresConMasTareasPendientes();

    @Query(value = """
    SELECT t.*
    FROM tarea t
    JOIN usuario u ON u.id = :usuarioId
    WHERE t.completada = false
    ORDER BY ST_Distance(t.ubicacion, u.ubicacion)
    LIMIT 1
    """, nativeQuery = true)
    Tarea tareaPendienteMasCercanaAGeoUsuario(@Param("usuarioId") Long usuarioId);

    @Query(value = """
    SELECT u.username AS usuario, s.nombre AS sector, COUNT(*) AS total
    FROM tarea t
    JOIN usuario u ON u.id = t.usuario_id
    JOIN sector s ON s.id = t.sector_id
    GROUP BY u.username, s.nombre
    ORDER BY u.username, total DESC
    """, nativeQuery = true)
    List<Map<String, Object>> contarTareasPorUsuarioYSector();

    @Query(value = """
    SELECT s.nombre AS sector, COUNT(*) AS total
    FROM tarea t
    JOIN sector s ON s.id = t.sector_id
    JOIN usuario u ON u.id = :usuarioId
    WHERE t.completada = true
      AND ST_DWithin(t.ubicacion, u.ubicacion, 5000)
    GROUP BY s.nombre
    ORDER BY total DESC
    LIMIT 1
    """, nativeQuery = true)
    Map<String, Object> sectorConMasCompletadasEnRadio5km(@Param("usuarioId") Long usuarioId);

    @Query(value = """
    SELECT AVG(ST_Distance(t.ubicacion, u.ubicacion)) AS promedio
    FROM tarea t
    JOIN usuario u ON u.id = :usuarioId
    WHERE t.completada = true
    """, nativeQuery = true)
    Double promedioDistanciaTodasCompletadasAUsuario(@Param("usuarioId") Long usuarioId);



}
