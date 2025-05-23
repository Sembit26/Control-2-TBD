package com.tbd.backend.Controller;

import com.tbd.backend.Entity.Tarea;
import com.tbd.backend.Service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tareas")
@CrossOrigin("*")
public class TareaController {

    @Autowired
    private TareaService tareaService;

    // Crear tarea
    @PostMapping("/create")
    public ResponseEntity<Tarea> crearTarea(@RequestBody Tarea tarea) {
        return ResponseEntity.ok(tareaService.crearTarea(tarea));
    }

    // Obtener tareas por usuario
    @GetMapping("/getTareasByUsuario/{usuarioId}")
    public ResponseEntity<List<Tarea>> obtenerTareasPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(tareaService.obtenerTareasPorUsuario(usuarioId));
    }

    // Obtener tareas por sector
    @GetMapping("/getTareasBySector/{sectorId}")
    public ResponseEntity<List<Tarea>> obtenerTareasPorSector(@PathVariable Long sectorId) {
        return ResponseEntity.ok(tareaService.obtenerTareasPorSector(sectorId));
    }

    // Editar tarea (Se le pasan todos los campos de la tarea)
    @PutMapping("/update/{tareaId}")
    public ResponseEntity<Tarea> editarTarea(@PathVariable Long tareaId, @RequestBody Tarea tareaDetalles) {
        return ResponseEntity.ok(tareaService.editarTarea(tareaId, tareaDetalles));
    }

    // Eliminar tarea
    @DeleteMapping("/{idTarea}")
    public ResponseEntity<Void> eliminarTarea(@PathVariable Long idTarea) {
        tareaService.eliminarTarea(idTarea);
        return ResponseEntity.noContent().build();
    }

    // Marcar como completada
    @PatchMapping("/marcarTareaComoCompletada/{tareaId}")
    public ResponseEntity<Tarea> marcarCompletada(@PathVariable Long tareaId) {
        return ResponseEntity.ok(tareaService.marcarCompletada(tareaId));
    }

    // Listar tareas completadas
    @GetMapping("/getTareasCompletadas")
    public ResponseEntity<List<Tarea>> listarTareasCompletadas() {
        return ResponseEntity.ok(tareaService.listarTareasCompletadas());
    }

    // Listar tareas pendientes
    @GetMapping("/getTareasPendientes")
    public ResponseEntity<List<Tarea>> listarTareasPendientes() {
        return ResponseEntity.ok(tareaService.listarTareasPendientes());
    }

    // Buscar tareas por palabra clave (en nombre o descripción)
    @GetMapping("/buscarPorPalabraClave")
    public ResponseEntity<List<Tarea>> buscarPorPalabraClave(@RequestParam String palabra) {
        return ResponseEntity.ok(tareaService.buscarPorPalabraClave(palabra));
    }

    // Filtro combinado: estado y palabra clave
    @GetMapping("/filtrar")
    public ResponseEntity<List<Tarea>> filtrarPorEstadoYPalabraClave(
            @RequestParam(required = false) Boolean completada,
            @RequestParam(required = false) String palabraClave
    ) {
        return ResponseEntity.ok(tareaService.filtrarPorEstadoYPalabraClave(completada, palabraClave));
    }
}
