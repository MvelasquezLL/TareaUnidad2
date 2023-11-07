package com.example.tareaunidad2;

public class Usuario {
    int id;
    String correo;
    String password;

    public Usuario() {
    }

    public Usuario(String correo, String password) {
        this.correo = correo;
        this.password = password;
    }

    public Usuario(int id, String correo, String password) {
        this.id = id;
        this.correo = correo;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
