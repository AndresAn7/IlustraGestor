/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.poo.ilustragestor;

import com.poo.ilustragestor.modelo.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

/**
 *
 * @author Grogu
 */
public class IlustraGestor {

public static void main(String[] args) {

        System.out.println("--- 1. Instanciación ---");
        Ilustrador ilustradorVacio = new Ilustrador();
        Ilustrador mika = new Ilustrador(1, "Mika Torres", "Anime y chibi", 2);
        Cliente andrea = new Cliente(10, "Andrea Pérez", "andrea@correo.com", "3001234567");
        ClienteEmpresa nami = new ClienteEmpresa(11, "Studio Nami", "contacto@nami.co", "6055550000", "900123456-7");
        System.out.println(ilustradorVacio);
        System.out.println(mika);

        System.out.println("\n--- 2. Polimorfismo ---");
        List<Persona> personas = new ArrayList<>();
        personas.add(mika);
        personas.add(andrea);
        personas.add(nami);
        for (Persona p : personas) {
            System.out.println(p.descripcion());
        }

        System.out.println("\n--- 3. Sobrecarga ---");
        andrea.actualizarDatosContacto("andrea.nuevo@correo.com");
        andrea.actualizarDatosContacto("andrea.nuevo@correo.com", "3109998888");
        System.out.println(andrea.getCorreo() + " / " + andrea.getTelefono());

        System.out.println("\n--- 4. Encargos ---");
        LocalDate entrega = LocalDate.now().plusDays(10);
        Encargo retrato = new EncargoRetrato(100, andrea, entrega, "Retrato estilo anime", 50000, 2, true);
        Encargo diseno = new EncargoDisenoPersonaje(101, nami, entrega, "Mascota del estudio", 120000, 4, true);
        System.out.println(retrato + " -> $" + retrato.calcularPrecio());
        System.out.println(diseno + " -> $" + diseno.calcularPrecio());
        System.out.println("Retrato con 20% de descuento -> $" + retrato.calcularPrecio(0.20));

        System.out.println("\n--- 5. Flujo y composición ---");
        retrato.asignarIlustrador(mika);
        retrato.cambiarEstado(EstadoEncargo.EN_PROCESO);
        retrato.subirVersion("boceto.png", "PNG");
        retrato.subirVersion("final.psd");
        try {
            retrato.subirVersion("virus.exe");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        retrato.marcarVersionFinal(2);
        retrato.cambiarEstado(EstadoEncargo.ENTREGADO);
        for (Ilustracion i : retrato.getIlustraciones()) {
            System.out.println(i);
        }
        System.out.println(retrato);

        System.out.println("\n--- 6. Factura ---");
        Factura factura = new Factura(500);
        factura.agregarEncargo(retrato);
        System.out.println(factura.generarFactura());

        System.out.println("\n--- 7. Restricciones ---");
        try {
            factura.agregarEncargo(diseno);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        try {
            andrea.setCorreo("sin-arroba");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
