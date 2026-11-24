/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poo.ilustragestor.servicio;

/**
 *
 * @author Grogu
 */
public class RegistroFacturacion {
    
    private int facturasEmitidas;
    private double totalFacturado;
    private int ultimoIdFactura;

    public synchronized int siguienteIdFactura() {
        ultimoIdFactura++;
        return ultimoIdFactura;
    }

    public synchronized void registrarFactura(double total) {
        facturasEmitidas++;
        totalFacturado += total;
    }

    public synchronized int getFacturasEmitidas() {
        return facturasEmitidas;
    }

    public synchronized double getTotalFacturado() {
        return totalFacturado;
    }

    public synchronized void reiniciar() {
        facturasEmitidas = 0;
        totalFacturado = 0;
        ultimoIdFactura = 0;
    }
}