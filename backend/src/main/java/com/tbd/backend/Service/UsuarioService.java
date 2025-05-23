package com.tbd.backend.Service;

import com.tbd.backend.DTO.UsuarioDTO;
import com.tbd.backend.Entity.Usuario;
import com.tbd.backend.Repository.UsuarioRepository;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private GeometryFactory geometryFactory;

    public List<UsuarioDTO> getAll() {
        return usuarioRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<UsuarioDTO> getById(Long id) {
        return usuarioRepository.findById(id)
                .map(this::convertToDTO);
    }

    // Método privado para convertir entidad a DTO
    private UsuarioDTO convertToDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setUsername(usuario.getUsername());
        dto.setCorreo(usuario.getCorreo());
        dto.setContrasena(usuario.getContrasena()); // En prod, quizás no mandar la contraseña
        dto.setX(usuario.getUbicacion().getX());
        dto.setY(usuario.getUbicacion().getY());
        return dto;
    }

    // Actualizar usuario
    public Usuario update(Long id, Usuario usuarioDetails) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
        usuario.setUsername(usuarioDetails.getUsername());
        usuario.setCorreo(usuarioDetails.getCorreo());
        usuario.setContrasena(usuarioDetails.getContrasena());
        usuario.setUbicacion(usuarioDetails.getUbicacion());
        return usuarioRepository.save(usuario);
    }

    // Eliminar usuario
    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

    // Registro - solo si el correo no existe
    public Usuario register(UsuarioDTO dto) {
        if (usuarioRepository.existsByCorreo(dto.getCorreo())) {
            throw new RuntimeException("Correo ya registrado");
        }

        Usuario usuario = new Usuario();
        usuario.setUsername(dto.getUsername());
        usuario.setCorreo(dto.getCorreo());
        usuario.setContrasena(dto.getContrasena());

        Point ubicacion = geometryFactory.createPoint(new Coordinate(dto.getX(), dto.getY()));
        ubicacion.setSRID(4326); // <- muy importante para PostGIS
        usuario.setUbicacion(ubicacion);

        return usuarioRepository.save(usuario);
    }

    // Login - validar correo y contraseña
    public Usuario login(String correo, String contrasena) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByCorreo(correo);
        if (usuarioOpt.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado");
        }
        Usuario usuario = usuarioOpt.get();
        if (!usuario.getContrasena().equals(contrasena)) {
            throw new RuntimeException("Contraseña incorrecta");
        }
        return usuario;
    }
}
