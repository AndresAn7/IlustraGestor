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
public class EncargoDisenoPersonaje extends Encargo {

    private static final double PRECIO_POR_EXPRESION = 8000;
    private static final double PRECIO_HOJA_REFERENCIA = 40000;

    private int numeroExpresiones;
    private boolean incluyeHojaReferencia;

    public EncargoDisenoPersonaje(int id, Cliente cliente, LocalDate fechaEntregaEstimada,
                                  String especificaciones, double precioBase,
                                  int numeroExpresiones, boolean incluyeHojaReferencia) {
        super(id, cliente, fechaEntregaEstimada, especificaciones, precioBase);
        setNumeroExpresiones(numeroExpresiones);
        this.incluyeHojaReferencia = incluyeHojaReferencia;
    }

    @Override
    public double calcularPrecio() {
        double total = getPrecioBase() + numeroExpresiones * PRECIO_POR_EXPRESION;
        return incluyeHojaReferencia ? total + PRECIO_HOJA_REFERENCIA : total;
    }

    @Override
    public String getTipo() {
        return "Diseño de personaje";
    }

    public int getNumeroExpresiones() {
        return numeroExpresiones;
    }

    public void setNumeroExpresiones(int numeroExpresiones) {
        if (numeroExpresiones < 0) {
            throw new IllegalArgumentException("El número de expresiones no puede ser negativo.");
        }
        this.numeroExpresiones = numeroExpresiones;
    }

    public boolean isIncluyeHojaReferencia() {
        return incluyeHojaReferencia;
    }

    public void setIncluyeHojaReferencia(boolean incluyeHojaReferencia) {
        this.incluyeHojaReferencia = incluyeHojaReferencia;
    }
}