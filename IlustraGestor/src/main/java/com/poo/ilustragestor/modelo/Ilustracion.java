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
public class Ilustracion {
    private int version;
    private String formato;
    private boolean esFinal;
    private LocalDate fechaCarga;
    private String rutaArchivo;

    Ilustracion() {
        this.version = 1;
        this.formato = "PNG";
        this.esFinal = false;
        this.fechaCarga = LocalDate.now();
        this.rutaArchivo = "";
    }

    Ilustracion(int version, String formato, String rutaArchivo) {
        this.version = version;
        this.rutaArchivo = rutaArchivo;
        this.fechaCarga = LocalDate.now();
        this.esFinal = false;
        validarFormato(formato);
        this.formato = formato.toUpperCase();
    }

    private void validarFormato(String formato) {
        ValidadorFormato validador = new ValidadorFormato();
        if (!validador.esFormatoValido(formato)) {
            throw new IllegalArgumentException("Formato no permitido: " + formato
                    + ". Permitidos: " + validador.getFormatosPermitidos());
        }
    }

    void marcarComoFinal() {
        this.esFinal = true;
    }

    void quitarMarcaFinal() {
        this.esFinal = false;
    }

    public int getVersion() {
        return version;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        validarFormato(formato);
        this.formato = formato.toUpperCase();
    }

    public boolean isEsFinal() {
        return esFinal;
    }

    public LocalDate getFechaCarga() {
        return fechaCarga;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    @Override
    public String toString() {
        return "v" + version + " [" + formato + "] " + rutaArchivo + (esFinal ? " (FINAL)" : "");
    }
}
