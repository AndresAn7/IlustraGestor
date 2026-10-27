/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poo.ilustragestor.modelo;

import java.time.LocalDate;

/**
 *
 * @author Grogu
 */
public class EncargoRetrato extends Encargo {

    private static final double RECARGO_FONDO = 30000;

    private int numeroPersonajes;
    private boolean incluyeFondo;

    public EncargoRetrato(int id, Cliente cliente, LocalDate fechaEntregaEstimada,
                          String especificaciones, double precioBase,
                          int numeroPersonajes, boolean incluyeFondo) {
        super(id, cliente, fechaEntregaEstimada, especificaciones, precioBase);
        setNumeroPersonajes(numeroPersonajes);
        this.incluyeFondo = incluyeFondo;
    }

    @Override
    public double calcularPrecio() {
        double total = getPrecioBase() * numeroPersonajes;
        return incluyeFondo ? total + RECARGO_FONDO : total;
    }

    @Override
    public String getTipo() {
        return "Retrato";
    }

    public int getNumeroPersonajes() {
        return numeroPersonajes;
    }

    public void setNumeroPersonajes(int numeroPersonajes) {
        if (numeroPersonajes < 1) {
            throw new IllegalArgumentException("Debe haber al menos un personaje.");
        }
        this.numeroPersonajes = numeroPersonajes;
    }

    public boolean isIncluyeFondo() {
        return incluyeFondo;
    }

    public void setIncluyeFondo(boolean incluyeFondo) {
        this.incluyeFondo = incluyeFondo;
    }
}
