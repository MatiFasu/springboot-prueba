
package com.todocodeacademy.prueba;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Plato {
    
    private Long id;
    private String nombre;
    private float precio;
    private String desc;

    public Plato() {
    }

    public Plato(Long id, String nombre, float precio, String desc) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.desc = desc;
    }
    
    
    
}
