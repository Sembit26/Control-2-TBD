package com.tbd.backend.Controller;

import com.tbd.backend.Entity.Notificacion;
import com.tbd.backend.Entity.Tarea;
import com.tbd.backend.Repository.TareaRepository;
import com.tbd.backend.Service.NotificacionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    @PostMapping("/crear")
    public Notificacion crearNotificacion(@RequestBody Notificacion notificacion) {
        return notificacionService.crear(notificacion);
    }

    @GetMapping("/user/{idUser}")
    public List<Notificacion> getNotificacionesByUser(@PathVariable Integer idUser) {
        return notificacionService.getAllByUser(idUser);
    }

    @GetMapping("/userAND/{idUser}")
    public List<Notificacion> getNotificacionesByUserAND(@PathVariable Integer idUser) {
        return notificacionService.getAllByUserAnd(idUser);
    }

    @PutMapping("/marcarTodasLeidas/{idUser}")
    public void marcarTodasLasNotificacionesComoLeidas(@PathVariable Integer idUser) {
        notificacionService.marcarTodasComoLeidas(idUser);
    }

    @PostMapping("/checkTareasAVencer/{idUser}")
    public void checkTareasVencidas(@PathVariable Integer idUser) {
        notificacionService.checkTareasVencidas(idUser);
    }

    @PutMapping("/marcarLeida/{id}")
    public void marcarLeida(@PathVariable Integer id) {
        notificacionService.marcarLeida(id);
    }

    @GetMapping("/user/noleidas/{id}")
    public Integer getNoleidasByUser(@PathVariable Integer id) {

        return notificacionService.getAllNoLeidas(id);

    }
}
