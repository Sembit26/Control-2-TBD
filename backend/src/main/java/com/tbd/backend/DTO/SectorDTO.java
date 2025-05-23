package com.tbd.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SectorDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private Double coordenadaX;
    private Double coordenadaY;
}

