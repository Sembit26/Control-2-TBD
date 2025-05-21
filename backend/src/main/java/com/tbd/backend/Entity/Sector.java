package com.tbd.backend.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sector {
    private Long id;
    private String nombre;
    private String descripcion;
    private Point ubicacion; // Centro del sector
}
