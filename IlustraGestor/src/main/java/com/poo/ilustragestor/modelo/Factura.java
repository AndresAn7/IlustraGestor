/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poo.ilustragestor.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Grogu
 */
public class Factura {

    private int id;
    private LocalDate fechaEmision;
    private LocalDate fechaPago;
    private EstadoFactura estado;
    private double total;
    private final List<Encargo> encargos = new ArrayList<>();

    public Factura() {
        this(0);
    }

    public Factura(int id) {
        this(id, LocalDate.now());
    }

    public Factura(int id, LocalDate fechaEmision) {
        this.id = id;
        this.fechaEmision = fechaEmision;
        this.estado = EstadoFactura.PENDIENTE;
        this.total = 0;
    }

    public void agregarEncargo(Encargo encargo) {
        if (estado != EstadoFactura.PENDIENTE) {
            throw new IllegalStateException("Solo se modifican facturas PENDIENTES.");
        }
        if (encargo.getEstado() != EstadoEncargo.ENTREGADO) {
            throw new IllegalArgumentException("Solo se facturan encargos ENTREGADOS: " + encargo);
        }
        if (!encargos.isEmpty() && encargos.get(0).getCliente() != encargo.getCliente()) {
            throw new IllegalArgumentException("Todos los encargos de una factura deben ser del mismo cliente.");
        }
        if (!encargos.contains(encargo)) {
            encargos.add(encargo);
        }
        calcularTotal();
    }

    public void agregarEncargo(List<Encargo> lista) {
        for (Encargo e : lista) {
            agregarEncargo(e);
        }
    }

    public double calcularTotal() {
        double suma = 0;
        for (Encargo e : encargos) {
            suma += e.calcularPrecio(e.getCliente().calcularDescuento());
        }
        this.total = suma;
        return total;
    }

    public String generarFactura() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== FACTURA #").append(id).append(" (").append(estado).append(") ===\n");
        sb.append("Emitida: ").append(fechaEmision).append("\n");
        for (Encargo e : encargos) {
            double descuento = e.getCliente().calcularDescuento();
            sb.append(String.format(" - %s | precio $%,.0f | desc. %.0f%% | neto $%,.0f%n",
                    e, e.calcularPrecio(), descuento * 100, e.calcularPrecio(descuento)));
        }
        sb.append(String.format("TOTAL: $%,.0f", total));
        return sb.toString();
    }

    public void marcarComoPagada() {
        if (encargos.isEmpty()) {
            throw new IllegalStateException("No se puede pagar una factura sin encargos.");
        }
        this.estado = EstadoFactura.PAGADA;
        this.fechaPago = LocalDate.now();
    }

    public void anularFactura() {
        this.estado = EstadoFactura.ANULADA;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public EstadoFactura getEstado() {
        return estado;
    }

    public double getTotal() {
        return total;
    }

    public List<Encargo> getEncargos() {
        return Collections.unmodifiableList(encargos);
    }
}
