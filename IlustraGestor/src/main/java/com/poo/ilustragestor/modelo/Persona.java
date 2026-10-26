/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poo.ilustragestor.modelo;

/**
 *
 * @author Grogu
 */
public abstract class Persona {
   
    private int id;
    private String nombre;

    public Persona() {
        this(0, "Sin nombre");
    }
    
    public Persona(int id, String nombre) {
        this.id = id;
         setNombre(nombre);

    }
    
     public abstract String obtenerRol();
     
        public String descripcion() {
        return obtenerRol() + ": " + nombre + " (id " + id + ")";
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
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        this.nombre = nombre.trim();
    }

    @Override
    public String toString() {
        return descripcion();
    }
}