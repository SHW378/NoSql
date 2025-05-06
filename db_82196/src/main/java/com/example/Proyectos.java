package com.example;

public class Proyectos {
    private String nombre;
    private String fecha_inicio;
    private String estado;

    public Proyectos(String nombre, String fecha_inicio, String estado) {
        this.nombre = nombre;
        this.fecha_inicio = fecha_inicio;
        this.estado = estado;
    }
    
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getFecha_inicio() {
        return fecha_inicio;
    }
    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}



