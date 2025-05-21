package com.tbd.backend.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tarea {
    private Long id;
    private String nombre;
    private String descripcion;
    private Date fechaTermino;
    private Long idUser;
    private Long idSector;
    private Boolean status;
}


