package com.example;

public class Empleados {
    private String nombre;
    private String rol;
    private String tareas_asignadas;

    public Empleados(String nombre, String rol, String tareas_asignadas) {
        this.nombre = nombre;
        this.rol = rol;
        this.tareas_asignadas = tareas_asignadas;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getRol() {
        return rol;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }
    public String getTareas_asignadas() {
        return tareas_asignadas;
    }
    public void setTareas_asignadas(String tareas_asignadas) {
        this.tareas_asignadas = tareas_asignadas;
    }
}
