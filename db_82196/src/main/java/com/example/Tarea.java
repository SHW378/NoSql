package com.example;

public class Tarea {
    
    private String titulo;
    private String fecha_limite;
    private String fecha_inicio;

    public Tarea(String titulo, String fecha_limite, String fecha_inicio) {
        this.titulo = titulo;
        this.fecha_limite = fecha_limite;
        this.fecha_inicio = fecha_inicio;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }  
    
    public String getFechaLimite() {
        return fecha_limite;
    }
    public void setFechaLimite(String fecha_limite) {
        this.fecha_limite = fecha_limite;
    }
    
    public String getFechaInicio() {
        return fecha_inicio;
    }
    
    public void setFechaInicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }
    
}

