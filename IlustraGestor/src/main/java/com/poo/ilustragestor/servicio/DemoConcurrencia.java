/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poo.ilustragestor.servicio;

import com.poo.ilustragestor.modelo.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Grogu
 */
public class DemoConcurrencia {

    private static final int NUM_CLIENTES = 8;
    private static final int NUM_HILOS = 4;
    private static final long LATENCIA_MS = 300;
    
    public static void ejecutar() {
    List<Cliente> clientes = crearClientesConEncargosEntregados();
    ProcesadorFacturas procesador = new ProcesadorFacturas(LATENCIA_MS);

    System.out.println("Clientes a facturar: " + clientes.size()
            + " | hilos del pool: " + NUM_HILOS);

    System.out.println("\n>> Facturación SECUENCIAL");
    long inicioSecuencial = System.nanoTime();
    procesador.facturarSecuencial(clientes);
    long msSecuencial = (System.nanoTime() - inicioSecuencial) / 1_000_000;
    double totalSecuencial = procesador.getRegistro().getTotalFacturado();

    System.out.println("\n>> Facturación CONCURRENTE (ExecutorService)");
    long inicioParalelo = System.nanoTime();
    List<Factura> facturas;
    try {
        facturas = procesador.facturarEnParalelo(clientes, NUM_HILOS);
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        System.out.println("Facturación interrumpida.");
        return;
    }
    long msParalelo = (System.nanoTime() - inicioParalelo) / 1_000_000;
    double totalParalelo = procesador.getRegistro().getTotalFacturado();

    System.out.println("\n--- Resultados ---");
    System.out.println("Facturas emitidas: " + facturas.size());
    System.out.println("Tiempo secuencial: " + msSecuencial + " ms");
    System.out.println("Tiempo concurrente: " + msParalelo + " ms");
    System.out.println("Total secuencial: " + totalSecuencial
            + " | Total concurrente: " + totalParalelo
            + " | coinciden: " + (totalSecuencial == totalParalelo));
}
    
private static List<Cliente> crearClientesConEncargosEntregados() {
    Ilustrador ilustrador = new Ilustrador(900, "Equipo de ilustración", "General", 1000);
    LocalDate entrega = LocalDate.now().plusDays(10);
    List<Cliente> clientes = new ArrayList<>();

    for (int i = 1; i <= NUM_CLIENTES; i++) {
        Cliente c;
        if (i % 3 == 0) {
            c = new ClienteEmpresa(i, "Empresa " + i, "empresa" + i + "@correo.com", "6050000" + i, "90000" + i + "-1");
        } else {
            c = new Cliente(i, "Cliente " + i, "cliente" + i + "@correo.com", "300000" + i);
        }
        clientes.add(c);

        Encargo retrato = new EncargoRetrato(i * 10, c, entrega, "Retrato", 50000, 1 + i % 3, i % 2 == 0);
        Encargo diseno = new EncargoDisenoPersonaje(i * 10 + 1, c, entrega, "Personaje", 120000, 3, true);
        entregar(retrato, ilustrador);
        entregar(diseno, ilustrador);
    }
    return clientes;
}

    private static void entregar(Encargo encargo, Ilustrador ilustrador) {
        encargo.asignarIlustrador(ilustrador);
        encargo.cambiarEstado(EstadoEncargo.EN_PROCESO);
        encargo.subirVersion("final.png");
        encargo.marcarVersionFinal(1);
        encargo.cambiarEstado(EstadoEncargo.ENTREGADO);
    }
}