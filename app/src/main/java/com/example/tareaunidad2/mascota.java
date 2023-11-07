package com.example.tareaunidad2;

public class mascota {
    int id;
    String nombre;
    String raza;
    String tamano;
    String peso;
    String enfermedad;

    public mascota() {
    }

    public mascota(String nombre, String raza, String tamano, String peso, String enfermedad) {
        this.nombre = nombre;
        this.raza = raza;
        this.tamano = tamano;
        this.peso = peso;
        this.enfermedad = enfermedad;
    }

    public mascota(int id, String nombre, String raza, String tamano, String peso, String enfermedad) {
        this.id = id;
        this.nombre = nombre;
        this.raza = raza;
        this.tamano = tamano;
        this.peso = peso;
        this.enfermedad = enfermedad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }
}
