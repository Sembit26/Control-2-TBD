package com.tbd.backend.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    private Long id;
    private String username;
    private String correo;
    private String contrasena;

    // campo real de geometría (no mapeado por sql2o directamente)
    private Point ubicacion;
}

