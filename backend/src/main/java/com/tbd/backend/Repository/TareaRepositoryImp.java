package com.tbd.backend.Repository;

import com.tbd.backend.Entity.Tarea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class TareaRepositoryImp implements TareaRepository {

    private final Sql2o sql2o;

    public TareaRepositoryImp(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Tarea crear(Tarea tarea) {
        String sql = "INSERT INTO tarea (nombre, descripcion, fecha_termino, id_user, id_sector, status) " +
                "VALUES (:nombre, :descripcion, :fechaTermino, :idUser, :idSector, :status)";
        try (Connection con = sql2o.open()) {
            Long id = (Long) con.createQuery(sql, true)
                    .addParameter("nombre", tarea.getNombre())
                    .addParameter("descripcion", tarea.getDescripcion())
                    .addParameter("fechaTermino", new java.sql.Date(tarea.getFechaTermino().getTime()))
                    .addParameter("idUser", tarea.getIdUser())
                    .addParameter("idSector", tarea.getIdSector())
                    .addParameter("status", tarea.getStatus())
                    .executeUpdate()
                    .getKey(Long.class);
            tarea.setId(id);
            return tarea;
        }
    }

    @Override
    public List<Tarea> getAll() {
        String sql = "SELECT * FROM tarea";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(Tarea.class);
        }
    }

    @Override
    public Tarea getById(Integer id) {
        String sql = "SELECT * FROM tarea WHERE id = :id";
        try (Connection con = sql2o.open()) {
            List<Tarea> result = con.createQuery(sql)
                    .addParameter("id", id.longValue())
                    .executeAndFetch(Tarea.class);
            return result.isEmpty() ? null : result.get(0);
        }
    }

    @Override
    public String update(Tarea tarea, Integer id) {
        String sql = "UPDATE tarea SET nombre = :nombre, descripcion = :descripcion, fecha_termino = :fechaTermino, " +
                "id_user = :idUser, id_sector = :idSector, status = :status WHERE id = :id";
        try (Connection con = sql2o.open()) {
            int updated = con.createQuery(sql)
                    .addParameter("nombre", tarea.getNombre())
                    .addParameter("descripcion", tarea.getDescripcion())
                    .addParameter("fechaTermino", new java.sql.Date(tarea.getFechaTermino().getTime()))
                    .addParameter("idUser", tarea.getIdUser())
                    .addParameter("idSector", tarea.getIdSector())
                    .addParameter("status", tarea.getStatus())
                    .addParameter("id", id.longValue())
                    .executeUpdate()
                    .getResult();
            return updated > 0 ? "Tarea actualizada correctamente." : "No se encontró la tarea o no se actualizó.";
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM tarea WHERE id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id.longValue())
                    .executeUpdate();
        }
    }

    @Override
    public List<Tarea> searchByStatus(Boolean status, Integer idUser) {
        String sql = "SELECT * FROM tarea WHERE status = :status AND id_user = :idUser";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("status", status)
                    .addParameter("idUser", idUser.longValue())
                    .executeAndFetch(Tarea.class);
        }
    }

    @Override
    public List<Tarea> searchByKeywordAndStatus(Boolean status, String keyword, Integer idUser) {
        String sql = "SELECT * FROM tarea WHERE status = :status AND id_user = :idUser AND " +
                "(LOWER(nombre) LIKE :keyword OR LOWER(descripcion) LIKE :keyword)";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("status", status)
                    .addParameter("idUser", idUser.longValue())
                    .addParameter("keyword", "%" + keyword.toLowerCase() + "%")
                    .executeAndFetch(Tarea.class);
        }
    }

    @Override
    public List<Tarea> getAllUser(Integer idUser) {
        String sql = "SELECT * FROM tarea WHERE id_user = :idUser";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("idUser", idUser.longValue())
                    .executeAndFetch(Tarea.class);
        }
    }

    @Override
    public List<Tarea> searchByKeyword(String keyword, Integer idUser) {
        String sql = "SELECT * FROM tarea WHERE id_user = :idUser AND " +
                "(LOWER(nombre) LIKE :keyword OR LOWER(descripcion) LIKE :keyword)";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("idUser", idUser.longValue())
                    .addParameter("keyword", "%" + keyword.toLowerCase() + "%")
                    .executeAndFetch(Tarea.class);
        }
    }
}