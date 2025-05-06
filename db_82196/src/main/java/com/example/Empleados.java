package com.example;

public class Empleados {
    private String nombre;
    private String rol;
    private int edad;

    public Empleados(String nombre, String rol, int edad) {
        this.nombre = nombre;
        this.rol = rol;
        this.edad = edad;
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
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }
}
