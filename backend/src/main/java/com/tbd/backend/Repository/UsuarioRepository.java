package com.tbd.backend.Repository;

import com.tbd.backend.Entity.Usuario;

import java.util.List;

public interface UsuarioRepository {

    void crearUsuario(Usuario usuario);
    List<Usuario> getAll();
    Usuario getById(Long id);
    String update(Usuario usuario, Long id);
    void delete(Long id);
    public Usuario searchByNombre(String username);
    public Usuario searchByCorreo(String correo);

}
