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
public abstract class Encargo {
    
    private int id;
    private LocalDate fechaSolicitud;
    private LocalDate fechaEntregaEstimada;
    private EstadoEncargo estado;
    private String especificaciones;
    private double precioBase;
    private Cliente cliente;
    private Ilustrador ilustrador;
    private final List<Ilustracion> ilustraciones = new ArrayList<>();

    protected Encargo(int id, Cliente cliente, LocalDate fechaEntregaEstimada,
                      String especificaciones, double precioBase) {
        if (cliente == null) {
            throw new IllegalArgumentException("Todo encargo debe tener un cliente.");
        }
        this.id = id;
        this.cliente = cliente;
        this.fechaSolicitud = LocalDate.now();
        setFechaEntregaEstimada(fechaEntregaEstimada);
        this.especificaciones = especificaciones;
        setPrecioBase(precioBase);
        this.estado = EstadoEncargo.PENDIENTE;
        cliente.agregarEncargo(this); 
    }

    public abstract double calcularPrecio();

    public abstract String getTipo();

    public double calcularPrecio(double porcentajeDescuento) {
        if (porcentajeDescuento < 0 || porcentajeDescuento > 1) {
            throw new IllegalArgumentException("El descuento debe estar entre 0 y 1.");
        }
        return calcularPrecio() * (1 - porcentajeDescuento);
    }

    public void asignarIlustrador(Ilustrador ilustrador) {
        if (!ilustrador.puedeAceptarEncargo()) {
            throw new IllegalStateException(ilustrador.getNombre() + " no puede aceptar más encargos.");
        }
        this.ilustrador = ilustrador;
        ilustrador.recibirEncargo(this);
    }

    public void cambiarEstado(EstadoEncargo nuevoEstado) {
        if (estado == EstadoEncargo.ENTREGADO || estado == EstadoEncargo.CANCELADO) {
            throw new IllegalStateException("Un encargo " + estado + " ya no puede cambiar de estado.");
        }
        if (nuevoEstado == EstadoEncargo.EN_PROCESO && ilustrador == null) {
            throw new IllegalStateException("Debe asignarse un ilustrador antes de iniciar el encargo.");
        }
        if (nuevoEstado == EstadoEncargo.ENTREGADO && obtenerVersionFinal() == null) {
            throw new IllegalStateException("No se puede entregar sin una ilustración marcada como final.");
        }
        this.estado = nuevoEstado;
    }

    public void cancelarEncargo() {
        cambiarEstado(EstadoEncargo.CANCELADO);
    }

    public Ilustracion subirVersion(String rutaArchivo, String formato) {
        if (estado != EstadoEncargo.EN_PROCESO) {
            throw new IllegalStateException("Solo se pueden subir versiones a un encargo EN_PROCESO.");
        }
        Ilustracion nueva = new Ilustracion(ilustraciones.size() + 1, formato, rutaArchivo);
        ilustraciones.add(nueva);
        return nueva;
    }

    public Ilustracion subirVersion(String rutaArchivo) {
        int punto = rutaArchivo.lastIndexOf('.');
        String formato = (punto >= 0) ? rutaArchivo.substring(punto + 1) : "";
        return subirVersion(rutaArchivo, formato);
    }

    public void marcarVersionFinal(int version) {
        Ilustracion elegida = null;
        for (Ilustracion i : ilustraciones) {
            if (i.getVersion() == version) {
                elegida = i;
            }
        }
        if (elegida == null) {
            throw new IllegalArgumentException("No existe la versión " + version);
        }
        for (Ilustracion i : ilustraciones) {
            i.quitarMarcaFinal();
        }
        elegida.marcarComoFinal();
    }

    public Ilustracion obtenerVersionFinal() {
        for (Ilustracion i : ilustraciones) {
            if (i.isEsFinal()) {
                return i;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFechaSolicitud() {
        return fechaSolicitud;
    }

    public LocalDate getFechaEntregaEstimada() {
        return fechaEntregaEstimada;
    }

    public void setFechaEntregaEstimada(LocalDate fechaEntregaEstimada) {
        if (fechaEntregaEstimada == null || !fechaEntregaEstimada.isAfter(fechaSolicitud)) {
            throw new IllegalArgumentException("La fecha de entrega debe ser posterior a la de solicitud.");
        }
        this.fechaEntregaEstimada = fechaEntregaEstimada;
    }

    public EstadoEncargo getEstado() {
        return estado;
    }

    public String getEspecificaciones() {
        return especificaciones;
    }

    public void setEspecificaciones(String especificaciones) {
        this.especificaciones = especificaciones;
    }

    protected double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(double precioBase) {
        if (precioBase <= 0) {
            throw new IllegalArgumentException("El precio base debe ser positivo.");
        }
        this.precioBase = precioBase;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Ilustrador getIlustrador() {
        return ilustrador;
    }

    public List<Ilustracion> getIlustraciones() {
        return Collections.unmodifiableList(ilustraciones);
    }

    @Override
    public String toString() {
        return "Encargo #" + id + " [" + getTipo() + "] " + estado + " - cliente: " + cliente.getNombre();
    }
}
