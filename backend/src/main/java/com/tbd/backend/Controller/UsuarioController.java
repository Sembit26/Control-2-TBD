package com.tbd.backend.Controller;
import com.tbd.backend.DTO.LoginRequest;
import com.tbd.backend.Entity.Usuario;
import com.tbd.backend.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/create")
    public void crearUsuario(@RequestBody Usuario usuario) {
        usuarioService.crearUsuario(usuario);
    }

    @GetMapping("/getAll")
    public List<Usuario> getAllUsuarios() {
        return usuarioService.getAll();
    }

    @GetMapping("/getById/{id}")
    public Usuario getUsuarioById(@PathVariable Long id) {
        return usuarioService.getById(id);
    }

    @PutMapping("/update/{id}")
    public void updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        usuarioService.updateUsuario(usuario, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
    }

    // ✅ Registro
    @PostMapping("/register")
    public String registrarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.registrarUsuario(usuario);
    }

    // ✅ Login
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        return usuarioService.iniciarSesion(loginRequest.getCorreo(), loginRequest.getContrasena());
    }

}
