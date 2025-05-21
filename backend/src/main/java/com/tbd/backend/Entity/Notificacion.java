package com.tbd.backend.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notificacion {
    private Long id;
    private Integer idUser;
    private Long idTarea;
    private String mensaje;
    private Boolean leida; // Estado de lectura
}