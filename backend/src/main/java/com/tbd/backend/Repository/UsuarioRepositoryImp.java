package com.tbd.backend.Repository;

import com.tbd.backend.Entity.Usuario;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsuarioRepositoryImp implements UsuarioRepository {

    private final Sql2o sql2o;

    public UsuarioRepositoryImp(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void crearUsuario(Usuario usuario) {
        String sql = "INSERT INTO Usuario (username, correo, contrasena, ubicacion) " +
                "VALUES (:username, :correo, :contrasena, ST_SetSRID(ST_MakePoint(:longitud, :latitud), 4326))";

        try (Connection conn = sql2o.open()) {
            conn.createQuery(sql)
                    .addParameter("username", usuario.getUsername())
                    .addParameter("correo", usuario.getCorreo())
                    .addParameter("contrasena", usuario.getContrasena())
                    // Recuerda: x = latitud, y = longitud (según tu clase)
                    .addParameter("longitud", usuario.getY())
                    .addParameter("latitud", usuario.getX())
                    .executeUpdate();
        } catch (Exception e) {
            System.out.println("Error creando usuario: " + e.getMessage());
        }
    }

    @Override
    public List<Usuario> getAll() {
        String sql = "SELECT id, username, correo, contrasena, " +
                "ST_Y(ubicacion::geometry) AS x, " +    // latitud
                "ST_X(ubicacion::geometry) AS y " +    // longitud
                "FROM Usuario";

        try (Connection conn = sql2o.open()) {
            return conn.createQuery(sql)
                    .executeAndFetch(Usuario.class);
        } catch (Exception e) {
            System.out.println("Error obteniendo usuarios: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Usuario getById(Long id) {
        String sql = "SELECT id, username, correo, contrasena, " +
                "ST_Y(ubicacion::geometry) AS x, " +
                "ST_X(ubicacion::geometry) AS y " +
                "FROM Usuario WHERE id = :id";

        try (Connection conn = sql2o.open()) {
            return conn.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Usuario.class);
        } catch (Exception e) {
            System.out.println("Error obteniendo usuario por ID: " + e.getMessage());
            return null;
        }
    }

    @Override
    public String update(Usuario usuario, Long id) {
        String sql = "UPDATE Usuario SET username = :username, correo = :correo, contrasena = :contrasena, " +
                "ubicacion = ST_SetSRID(ST_MakePoint(:longitud, :latitud), 4326) " +
                "WHERE id = :id";

        try (Connection conn = sql2o.open()) {
            int updated = conn.createQuery(sql)
                    .addParameter("username", usuario.getUsername())
                    .addParameter("correo", usuario.getCorreo())
                    .addParameter("contrasena", usuario.getContrasena())
                    .addParameter("longitud", usuario.getY())
                    .addParameter("latitud", usuario.getX())
                    .addParameter("id", id)
                    .executeUpdate()
                    .getResult();

            if (updated > 0) {
                return "Usuario actualizado correctamente.";
            } else {
                return "No se encontró usuario con ese ID.";
            }
        } catch (Exception e) {
            System.out.println("Error actualizando usuario: " + e.getMessage());
            return "Error al actualizar usuario.";
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM Usuario WHERE id = :id";

        try (Connection conn = sql2o.open()) {
            conn.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Exception e) {
            System.out.println("Error eliminando usuario: " + e.getMessage());
        }
    }

    @Override
    public Usuario searchByNombre(String username) {
        String sql = "SELECT id, username, correo, contrasena, " +
                "ST_Y(ubicacion::geometry) AS x, " +
                "ST_X(ubicacion::geometry) AS y " +
                "FROM Usuario WHERE username = :username";

        try (Connection conn = sql2o.open()) {
            return conn.createQuery(sql)
                    .addParameter("username", username)
                    .executeAndFetchFirst(Usuario.class);
        } catch (Exception e) {
            System.out.println("Error buscando usuario por nombre: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Usuario searchByCorreo(String correo) {
        String sql = "SELECT id, username, correo, contrasena, " +
                "ST_Y(ubicacion::geometry) AS x, " +
                "ST_X(ubicacion::geometry) AS y " +
                "FROM Usuario WHERE correo = :correo";

        try (Connection conn = sql2o.open()) {
            return conn.createQuery(sql)
                    .addParameter("correo", correo)
                    .executeAndFetchFirst(Usuario.class);
        } catch (Exception e) {
            System.out.println("Error buscando usuario por correo: " + e.getMessage());
            return null;
        }
    }

}
