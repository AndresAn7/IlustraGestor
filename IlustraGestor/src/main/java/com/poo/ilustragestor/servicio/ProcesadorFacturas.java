/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poo.ilustragestor.servicio;

import com.poo.ilustragestor.modelo.Cliente;
import com.poo.ilustragestor.modelo.Encargo;
import com.poo.ilustragestor.modelo.EstadoEncargo;
import com.poo.ilustragestor.modelo.Factura;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Grogu
 */
public class ProcesadorFacturas {
 
    private final long latenciaSimuladaMs;
    private final RegistroFacturacion registro = new RegistroFacturacion();

    public ProcesadorFacturas(long latenciaSimuladaMs) {
        this.latenciaSimuladaMs = latenciaSimuladaMs;
    }

    public RegistroFacturacion getRegistro() {
        return registro;
    }

    public List<Factura> facturarSecuencial(List<Cliente> clientes) {
        registro.reiniciar();
        List<Factura> facturas = new ArrayList<>();
        for (Cliente c : clientes) {
            Factura f = facturarCliente(c);
            if (f != null) {
                facturas.add(f);
            }
        }
        return facturas;
    }

    private Factura facturarCliente(Cliente cliente) {
        List<Encargo> entregados = new ArrayList<>();
        for (Encargo e : cliente.consultarHistorialEncargos()) {
            if (e.getEstado() == EstadoEncargo.ENTREGADO) {
                entregados.add(e);
            }
        }
        if (entregados.isEmpty()) {
            return null;
        }

        String hilo = Thread.currentThread().getName();
        System.out.println("  [" + hilo + "] inicia factura de " + cliente.getNombre());

        Factura factura = new Factura(registro.siguienteIdFactura());
        factura.agregarEncargo(entregados);
        factura.generarFactura();
        simularTrabajoLento();
        registro.registrarFactura(factura.getTotal());

        System.out.println("  [" + hilo + "] termina factura #" + factura.getId()
                + " de " + cliente.getNombre());
        return factura;
    }

    private void simularTrabajoLento() {
        try {
            Thread.sleep(latenciaSimuladaMs);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
}
