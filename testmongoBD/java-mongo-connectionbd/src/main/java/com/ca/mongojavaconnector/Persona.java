package com.ca.mongojavaconnector;

public class Persona {
    private String nombre;
    private int edad;
    private String ciudad;
    private String email;

    public Persona(String nombre, int edad, String ciudad, String email) {
        this.nombre = nombre;
        this.edad = edad;
        this.ciudad = ciudad;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre: " + nombre + "\'" +
                ", edad: " + edad + "\'" +
                ", ciudad: " + ciudad + "\'" +
                ", email: " + email + "\'" +
                "}";
    }
}
