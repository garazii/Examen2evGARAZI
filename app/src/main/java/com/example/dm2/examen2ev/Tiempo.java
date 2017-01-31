package com.example.dm2.examen2ev;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Tiempo {
    private String nombre;
    private String hora;
    private String temp;
    private String texto;

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getNombre() {
        return nombre;
    }

    public String getHora() {
        return hora;
    }

    public String getTemp() {
        return temp;
    }

    public String getTexto() {
        return texto;
    }
}