/*
 * Clase modelo que representa a un estudiante.
 * 
 * Contiene los atributos necesarios para identificar al estudiante:
 * - Código único
 * - Cédula
 * - Nombre
 * - Correo institucional
 * - Nivel académico
 * - Fecha de registro
 * 
 * Métodos:
 * - `toCsvString()`: convierte al estudiante en una línea CSV para guardarlo.
 * - `toDisplayString()`: devuelve una presentación amigable para mostrar en consola.
 * 
 * Esta clase es utilizada por:
 * - `P1Excepciones` (main) para crear el estudiante a partir de datos ingresados.
 * - `RegistroService` para guardar y recuperar estudiantes desde el archivo.
 * 
 * Aunque aquí no se manejan directamente excepciones, es fundamental en el flujo de validación
 * y persistencia de datos.
 */
// Declaramos que esta clase pertenece al paquete donde están los modelos
package com.mycompany.p1excepciones.models;

// Importamos la clase LocalDate para manejar fechas
import java.time.LocalDate;

// Esta clase representa un estudiante de la ESPE
public class Estudiante {

    // Atributo que representa el código único del estudiante (ej: ID:L00481234)
    private String codigo;

    // Cédula del estudiante, debe tener 10 dígitos numéricos
    private String cedula;

    // Nombre completo del estudiante
    private String nombre;

    // Correo institucional del estudiante (debe ser @espe.edu.ec)
    private String correo;

    // Nivel académico o semestre que cursa el estudiante (1 a 8)
    private int nivel;

    // Fecha en que fue registrado el estudiante en el sistema
    private LocalDate fechaRegistro;

    // Constructor para crear un nuevo estudiante con todos los datos necesarios
    public Estudiante(String codigo, String cedula, String nombre, String correo, int nivel) {
        this.codigo = codigo; // Se genera automáticamente en RegistroService
        this.cedula = cedula;
        this.nombre = nombre;
        this.correo = correo;
        this.nivel = nivel;
        this.fechaRegistro = LocalDate.now(); // Se guarda la fecha actual del sistema
    }

    // =======================
    // Getters (obtener datos)
    // =======================

    // Devuelve la cédula del estudiante
    public String getCedula() {
        return cedula;
    }

    // Devuelve el nombre del estudiante
    public String getNombre() {
        return nombre;
    }

    // Devuelve el correo del estudiante
    public String getCorreo() {
        return correo;
    }

    // Devuelve el nivel académico del estudiante
    public int getNivel() {
        return nivel;
    }

    // Devuelve el código único del estudiante
    public String getCodigo() {
        return codigo;
    }

    // =======================
    // Conversión a CSV
    // =======================

    // Convierte los datos del estudiante a una línea de texto con formato CSV
    // Esto es útil para guardar en un archivo .txt o .csv
    public String toCsvString() {
        return String.format("%s,%s,%s,%s,%d,%s",
                codigo, cedula, nombre, correo, nivel, fechaRegistro);
    }

    // =======================
    // Representación para mostrar en consola
    // =======================

    // Devuelve una versión de los datos bonita para mostrar en pantalla
    public String toDisplayString() {
        return "Codigo: " + codigo + "\n" +
               "Cedula: " + cedula + "\n" +
               "Nombre: " + nombre + "\n" +
               "Correo: " + correo + "\n" +
               "Nivel: " + nivel + "\n" +
               "Fecha de Registro: " + fechaRegistro + "\n";
    }
}
