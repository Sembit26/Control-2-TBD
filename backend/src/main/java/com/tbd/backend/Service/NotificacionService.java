package com.tbd.backend.Service;

import com.tbd.backend.Entity.Notificacion;
import com.tbd.backend.Entity.Tarea;
import com.tbd.backend.Repository.NotificacionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;

    @Autowired
    private TareaService tareaService;


    public void checkTareasVencidas(Integer id_user) {
        Date hoy = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(hoy);
        calendar.add(Calendar.DATE, 1);
        Date diaSiguiente = calendar.getTime();
        List<Notificacion> notificaciones = notificacionRepository.getAllByUser(id_user);
        List<Tarea> tareas = tareaService.getAllUser(id_user);

        for (Tarea tarea : tareas) {
            boolean existe = false;
            for (Notificacion notificacion : notificaciones) {
                if (Objects.equals(notificacion.getIdTarea(), tarea.getId())){
                    existe = true;
                    break;
                }
            }
            Date fecha = tarea.getFechaTermino();
            if (!existe && fecha.before(diaSiguiente)) {
                Notificacion notificacion = new Notificacion(null, id_user, tarea.getId(), "Tarea proxima a vencer", false );
                Notificacion notificacionCreada = crear(notificacion);
            }
        }
    }

    public Notificacion crear(Notificacion notificacion) {
        return notificacionRepository.crear(notificacion);
    }

    public List<Notificacion> getAllByUser(Integer idUser) {
        return notificacionRepository.getAllByUser(idUser);
    }

    public List<Notificacion> getAllByUserAnd(Integer idUser) {
        return notificacionRepository.getAllByUserAnd(idUser);
    }

    public void marcarTodasComoLeidas(Integer idUser) {
        notificacionRepository.marcarTodasComoLeidas(idUser);
    }

    public void marcarLeida(Integer id) {notificacionRepository.marcarLeida(id);}

    public Integer getAllNoLeidas(Integer idUser){
        List<Notificacion> notificaciones = notificacionRepository.getAllNoLeidas(idUser);
        System.out.println(notificaciones);
        return notificaciones.size();
    }
}
