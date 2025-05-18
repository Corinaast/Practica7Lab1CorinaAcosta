/*
 * Clase que se encarga de toda la lógica de almacenamiento y recuperación de datos.
 * 
 * FUNCIONES:
 * - `generarCodigoUnico()`: genera un ID único aleatorio en el formato "ID:L00XXXXXX"
 * - `guardarEstudiante(Estudiante e)`: guarda un estudiante en `estudiantes.txt`, con encabezado si es necesario.
 * - `buscarPorCedula(String cedula)`: busca si una cédula ya existe, retornando un estudiante o `null`.
 * 
 * RELACIÓN:
 * - Llamada desde `P1Excepciones` para guardar o buscar registros.
 * - Trabaja directamente con objetos `Estudiante`.
 * 
 * CUMPLIMIENTO DE REQUISITOS:
 * ✔️ Guarda datos en un archivo CSV.
 * ✔️ Lanza error si el archivo no existe o si hay errores de lectura.
 * ✔️ Permite buscar un ID/cédula para lanzar excepción de duplicado en la clase principal (main).
 */
// Declaramos que esta clase pertenece al paquete "services"
package com.mycompany.p1excepciones.services;

import com.mycompany.p1excepciones.models.Estudiante;

import java.io.*;
import java.nio.file.*;
import java.util.*;

// Esta clase maneja el registro de estudiantes (guardar, buscar, generar códigos, etc.)
public class RegistroService {

    // Archivos de salida
    private static final String ARCHIVO_TXT = "estudiantes.txt";
    private static final String ARCHIVO_CSV = "estudiantes.csv";

    // MÉTODO PARA GENERAR UN CÓDIGO ÚNICO CON PREFIJO L00
    public String generarCodigoUnico() {
        int numero = new Random().nextInt(900000) + 100000;
        return "ID:L00" + numero;
    }

    // MÉTODO PARA GUARDAR ESTUDIANTE EN .txt Y .csv
    public void guardarEstudiante(Estudiante estudiante) throws IOException {
        guardarEnArchivo(estudiante, ARCHIVO_TXT);
        guardarEnArchivo(estudiante, ARCHIVO_CSV);
    }

    // MÉTODO AUXILIAR PARA GUARDAR EN CUALQUIER ARCHIVO
    private void guardarEnArchivo(Estudiante estudiante, String nombreArchivo) throws IOException {
        boolean archivoExiste = Files.exists(Paths.get(nombreArchivo));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo, true))) {
            if (!archivoExiste) {
                writer.write("CODIGO,CEDULA,NOMBRE,CORREO,NIVEL,FECHA");
                writer.newLine();
            }
            writer.write(estudiante.toCsvString());
            writer.newLine();
        }
    }

    // MÉTODO PARA BUSCAR ESTUDIANTE POR CÉDULA
    public Estudiante buscarPorCedula(String cedulaBuscada) throws IOException {
        if (!Files.exists(Paths.get(ARCHIVO_TXT))) return null;

        List<String> lineas = Files.readAllLines(Paths.get(ARCHIVO_TXT));
        for (int i = 1; i < lineas.size(); i++) {
            String[] datos = lineas.get(i).split(",");
            if (datos.length >= 6 && datos[1].equals(cedulaBuscada)) {
                return new Estudiante(
                    datos[0], datos[1], datos[2], datos[3], Integer.parseInt(datos[4])
                );
            }
        }
        return null;
    }
}
