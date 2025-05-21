package com.tbd.backend.Repository;

import com.tbd.backend.Entity.Notificacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class NotificacionRepositoryImp implements NotificacionRepository {

    @Autowired
    private Sql2o sql2o;

    public Notificacion crear(Notificacion notificacion) {
        try (Connection con = sql2o.open()) {
            String sql = "INSERT INTO notificaciones (idUser, idTarea, mensaje, leida) " +
                    "VALUES (:idUser, :idTarea, :mensaje, :leida)";
            con.createQuery(sql)
                    .addParameter("idUser", notificacion.getIdUser())
                    .addParameter("idTarea", notificacion.getIdTarea())
                    .addParameter("mensaje", notificacion.getMensaje())
                    .addParameter("leida", notificacion.getLeida())
                    .executeUpdate();
            return notificacion;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Notificacion> getAllByUser(Integer idUser) {
        try (Connection con = sql2o.open()) {
            String sql = "SELECT * FROM notificaciones WHERE idUser = :idUser";
            return con.createQuery(sql)
                    .addParameter("idUser", idUser)
                    .executeAndFetch(Notificacion.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Notificacion> getAllByUserAnd(Integer idUser) {
        try (Connection con = sql2o.open()) {
            String sql = "SELECT * FROM notificaciones WHERE idUser = :idUser AND leida = false";
            return con.createQuery(sql)
                    .addParameter("idUser", idUser)
                    .executeAndFetch(Notificacion.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void marcarTodasComoLeidas(Integer idUser) {
        try (Connection con = sql2o.open()) {
            String sql = "UPDATE notificaciones SET leida = TRUE WHERE idUser = :idUser";
            con.createQuery(sql)
                    .addParameter("idUser", idUser)
                    .executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void marcarLeida(Integer id){
        try (Connection con = sql2o.open()) {
            String sql = "UPDATE notificaciones SET leida = TRUE WHEre id = :id";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public List<Notificacion> getAllNoLeidas(Integer idUser){
        try (Connection con = sql2o.open()) {
            String sql = "SELECT * FROM notificaciones WHERE idUser = :idUser AND leida = FALSE";
            return con.createQuery(sql)
                    .addParameter("idUser",idUser)
                    .executeAndFetch(Notificacion.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
