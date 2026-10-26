/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poo.ilustragestor.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Grogu
 */
public class Cliente extends Persona {
    
    private String correo;
    private String telefono;
    private final List<Encargo> encargos = new ArrayList<>();
    
    public Cliente() {
        super();
        this.correo = "sin-correo@ejemplo.com";
        this.telefono = "";
    }

    public Cliente(int id, String nombre, String correo, String telefono) {
        super(id, nombre);
        setCorreo(correo);
        this.telefono = telefono;
    }

    @Override
    public String obtenerRol() {
        return "Cliente";
    }

    public double calcularDescuento() {
        return 0.0;
    }

    public void actualizarDatosContacto(String correo) {
        setCorreo(correo);
    }

    public void actualizarDatosContacto(String correo, String telefono) {
        setCorreo(correo);
        this.telefono = telefono;
    }

    void agregarEncargo(Encargo encargo) {
        if (!encargos.contains(encargo)) {
            encargos.add(encargo);
        }
    }

    public List<Encargo> consultarHistorialEncargos() {
        return Collections.unmodifiableList(encargos);
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        if (correo == null || !correo.contains("@")) {
            throw new IllegalArgumentException("Correo electrónico no válido: " + correo);
        }
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
   
}
