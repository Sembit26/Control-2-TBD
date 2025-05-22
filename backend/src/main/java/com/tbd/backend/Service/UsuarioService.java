package com.tbd.backend.Service;

import com.tbd.backend.Entity.Usuario;
import com.tbd.backend.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void crearUsuario(Usuario usuario) {
        usuarioRepository.crearUsuario(usuario);
    }

    public List<Usuario> getAll() {
        return usuarioRepository.getAll();
    }

    public Usuario getById(Long id) {
        return usuarioRepository.getById(id);
    }

    public void updateUsuario(Usuario usuario, Long id) {
        usuarioRepository.update(usuario, id);
    }

    public void deleteUsuario(Long id) {
        usuarioRepository.delete(id);
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        return usuarioRepository.searchByCorreo(email);
    }

    public Usuario buscarUsuarioPorNombre(String nombre) {
        return usuarioRepository.searchByNombre(nombre);
    }

    public String registrarUsuario(Usuario usuario) {
        // Verificar si ya existe un usuario con ese correo
        if (usuarioRepository.searchByCorreo(usuario.getCorreo()) != null) {
            return "Ya existe un usuario registrado con ese correo.";
        }

        /* Verificar si ya existe un usuario con ese username
        if (usuarioRepository.searchByNombre(usuario.getUsername()) != null) {
            return "El nombre de usuario ya está en uso.";
        }
         */

        // Si no existe, lo crea
        usuarioRepository.crearUsuario(usuario);
        return "Usuario registrado correctamente.";
    }

    public String iniciarSesion(String correo, String contrasena) {
        Usuario usuario = usuarioRepository.searchByCorreo(correo);

        if (usuario == null) {
            return "No se encontró un usuario con ese correo.";
        }

        if (!usuario.getContrasena().equals(contrasena)) {
            return "Contraseña incorrecta.";
        }

        return "Inicio de sesión exitoso.";
    }

}
