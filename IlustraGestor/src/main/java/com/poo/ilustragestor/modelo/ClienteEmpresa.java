/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poo.ilustragestor.modelo;

/**
 *
 * @author Grogu
 */
public class ClienteEmpresa extends Cliente {

    private static final double DESCUENTO_EMPRESARIAL = 0.10;

    private String nit;

    public ClienteEmpresa() {
        super();
        this.nit = "SIN-NIT";
    }

    public ClienteEmpresa(int id, String nombre, String correo, String telefono, String nit) {
        super(id, nombre, correo, telefono);
        this.nit = nit;
    }

    @Override
    public String obtenerRol() {
        return super.obtenerRol() + " empresarial (NIT " + nit + ")";
    }

    @Override
    public double calcularDescuento() {
        return DESCUENTO_EMPRESARIAL;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }
}
