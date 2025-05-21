package com.tbd.backend.Repository;


import com.tbd.backend.Entity.Notificacion;

import java.util.List;

public interface NotificacionRepository {
    Notificacion crear(Notificacion notificacion);
    List<Notificacion> getAllByUser(Integer idUser);
    void marcarTodasComoLeidas(Integer idUser);
    void marcarLeida(Integer idLeida);
    List<Notificacion> getAllByUserAnd(Integer idUser);
    List<Notificacion> getAllNoLeidas(Integer idUser);
}
