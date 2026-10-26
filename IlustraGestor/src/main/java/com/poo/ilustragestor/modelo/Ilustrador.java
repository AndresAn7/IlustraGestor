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
public class Ilustrador extends Persona {
    
    private String especialidad;
    private boolean disponible;
    private int maxEncargosSimultaneos;
    private final List<Encargo> encargosAsignados = new ArrayList<>();

    /** Constructor por defecto. */
    public Ilustrador() {
        super();
        this.especialidad = "General";
        this.disponible = true;
        this.maxEncargosSimultaneos = 3;
    }

    public Ilustrador(int id, String nombre, String especialidad) {
        this(id, nombre, especialidad, 3);
    }

    public Ilustrador(int id, String nombre, String especialidad, int maxEncargosSimultaneos) {
        super(id, nombre);
        this.especialidad = especialidad;
        this.disponible = true;
        setMaxEncargosSimultaneos(maxEncargosSimultaneos);
    }

    @Override
    public String obtenerRol() {
        return "Ilustrador (" + especialidad + ")";
    }

    public int consultarCargaTrabajo() {
        int carga = 0;
        for (Encargo e : encargosAsignados) {
            if (e.getEstado() == EstadoEncargo.EN_PROCESO) {
                carga++;
            }
        }
        return carga;
    }

    public void registrarDisponibilidad(boolean disponible) {
        this.disponible = disponible;
    }

    public boolean puedeAceptarEncargo() {
        return disponible && encargosAsignados.size() < maxEncargosSimultaneos;
    }

    void recibirEncargo(Encargo encargo) {
        if (!encargosAsignados.contains(encargo)) {
            encargosAsignados.add(encargo);
        }
    }

    public List<Encargo> getEncargosAsignados() {
        return Collections.unmodifiableList(encargosAsignados);
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public int getMaxEncargosSimultaneos() {
        return maxEncargosSimultaneos;
    }

    public void setMaxEncargosSimultaneos(int maxEncargosSimultaneos) {
        if (maxEncargosSimultaneos < 1) {
            throw new IllegalArgumentException("La capacidad debe ser al menos 1.");
        }
        this.maxEncargosSimultaneos = maxEncargosSimultaneos;
    }
    
}
