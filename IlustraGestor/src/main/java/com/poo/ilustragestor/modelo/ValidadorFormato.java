/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poo.ilustragestor.modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Grogu
 */
public class ValidadorFormato {
    private List<String> formatosPermitidos;

    public ValidadorFormato() {
        this.formatosPermitidos = new ArrayList<>(Arrays.asList("PNG", "JPG", "PSD", "CLIP"));
    }

    public ValidadorFormato(List<String> formatosPermitidos) {
        setFormatosPermitidos(formatosPermitidos);
    }

    public boolean esFormatoValido(String formato) {
        if (formato == null) {
            return false;
        }
        for (String permitido : formatosPermitidos) {
            if (permitido.equalsIgnoreCase(formato.trim())) {
                return true;
            }
        }
        return false;
    }

    public List<String> getFormatosPermitidos() {
        return new ArrayList<>(formatosPermitidos); // copia defensiva
    }

    public void setFormatosPermitidos(List<String> formatosPermitidos) {
        if (formatosPermitidos == null || formatosPermitidos.isEmpty()) {
            throw new IllegalArgumentException("La lista de formatos permitidos no puede estar vacía.");
        }
        this.formatosPermitidos = new ArrayList<>(formatosPermitidos);
    }
}
