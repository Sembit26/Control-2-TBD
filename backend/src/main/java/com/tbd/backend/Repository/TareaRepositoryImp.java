package com.tbd.backend.Repository;

import com.tbd.backend.Entity.Tarea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class TareaRepositoryImp implements TareaRepository {

    @Autowired
    private Sql2o sql2o;

    @Override
    public Tarea crear(Tarea tarea) {
        String sql = "INSERT INTO tarea (nombre, descripcion, fechaTermino, idUser, idSector, status) " +
                "VALUES (:nombre, :descripcion, :fechaTermino, :idUser, :idSector, :status) " +
                "RETURNING id";
        try (Connection con = sql2o.open()) {
            Long id = con.createQuery(sql)
                    .addParameter("nombre", tarea.getNombre())
                    .addParameter("descripcion", tarea.getDescripcion())
                    .addParameter("fechaTermino", tarea.getFechaTermino())
                    .addParameter("idUser", tarea.getIdUser())
                    .addParameter("idSector", tarea.getIdSector())
                    .addParameter("status", tarea.getStatus())
                    .executeScalar(Long.class);
            tarea.setId(id);
            return tarea;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Tarea actualizar(Tarea tarea) {
        String sql = "UPDATE tarea SET nombre = :nombre, descripcion = :descripcion, fechaTermino = :fechaTermino, " +
                "idUser = :idUser, idSector = :idSector, status = :status WHERE id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("nombre", tarea.getNombre())
                    .addParameter("descripcion", tarea.getDescripcion())
                    .addParameter("fechaTermino", tarea.getFechaTermino())
                    .addParameter("idUser", tarea.getIdUser())
                    .addParameter("idSector", tarea.getIdSector())
                    .addParameter("status", tarea.getStatus())
                    .addParameter("id", tarea.getId())
                    .executeUpdate();
            return tarea;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void eliminar(Long id) {
        String sql = "DELETE FROM tarea WHERE id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Tarea getById(Long id) {
        String sql = "SELECT * FROM tarea WHERE id = :id";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Tarea.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Tarea> getAll() {
        String sql = "SELECT * FROM tarea";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(Tarea.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Tarea> getByUser(Long idUser) {
        String sql = "SELECT * FROM tarea WHERE idUser = :idUser";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("idUser", idUser)
                    .executeAndFetch(Tarea.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    //Obtener todas las tareas de un usuario
    public List<Tarea> getAllUser(Integer idUser){
        try(Connection con = sql2o.open()){
            String sql = "SELECT * FROM tarea WHERE idUser=:idUser";
            return con.createQuery(sql)
                    .addParameter("idUser", idUser)
                    .executeAndFetch(Tarea.class);
        } catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
