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

    public String getnombre() {
        return nombre;
    }

    public void setnombre(String nombre) {
        this.nombre = nombre;
    }

    public int getedad() {
        return edad;
    }

    public void setedad(int edad) {
        this.edad = edad;
    }

    public String getciudad(){
        return ciudad;
    }

    public void setciudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getemail() {
        return email;
    }

    public void setemail(String email) {
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
