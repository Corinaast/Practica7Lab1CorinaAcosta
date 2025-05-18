/*
 * Clase que contiene métodos estáticos para validar los datos ingresados por el usuario.
 * 
 * Métodos:
 * - `validarCedula(String)`: asegura que la cédula tenga 10 dígitos numéricos.
 * - `validarNombre(String)`: asegura que el nombre solo tenga letras y espacios.
 * - `validarCorreo(String)`: valida que el correo termine con @espe.edu.ec.
 * - `validarNivel(String)`: convierte a número y asegura que esté entre 1 y 8.
 * 
 * RELACIÓN:
 * - Llamado por `P1Excepciones` antes de crear el estudiante.
 * 
 * CUMPLIMIENTO DE REQUISITOS:
 * ✔️ Lanza `IllegalArgumentException` en validaciones.
 * ✔️ Lanza `Exception` personalizada cuando el correo o el nivel no son válidos.
 * ✔️ Se usa dentro de bloques `try-catch` en el `main` para mostrar mensajes personalizados.
 */
// Declaramos que esta clase forma parte del paquete de utilidades
package com.mycompany.p1excepciones.utils;

// Esta clase se encarga de validar los datos que el usuario ingresa
public class Validador {

    // ===============================================
    // Método para validar la cédula
    // ===============================================
    public static void validarCedula(String cedula) {
        // Verifica que la cédula contenga exactamente 10 dígitos numéricos
        if (!cedula.matches("\\d{10}")) {
            // Si no cumple, lanza una excepción con un mensaje personalizado
            throw new IllegalArgumentException("La cedula debe tener exactamente 10 digitos numericos.");
        }
    }

    // ===============================================
    // Método para validar el nombre
    // ===============================================
    public static void validarNombre(String nombre) {
        // Verifica que el nombre solo contenga letras (incluye tildes y la ñ)
        if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            throw new IllegalArgumentException("El nombre debe contener solo letras.");
        }
    }

    // ===============================================
    // Método para validar el correo institucional
    // ===============================================
    public static void validarCorreo(String correo) throws Exception {
        // Verifica que el correo termine con "@espe.edu.ec"
        if (!correo.endsWith("@espe.edu.ec")) {
            // Lanza una excepción si el formato no es el correcto
            throw new Exception("El correo debe terminar con @espe.edu.ec");
        }
    }

    // ===============================================
    // Método para validar el nivel académico
    // ===============================================
    public static void validarNivel(String nivelStr) throws Exception {
        try {
            // Intenta convertir el nivel a un número entero
            int nivel = Integer.parseInt(nivelStr);

            // Verifica que el nivel esté en el rango de 1 a 8
            if (nivel < 1 || nivel > 8) {
                throw new Exception("Nivel debe estar entre 1 y 8");
            }

        } catch (NumberFormatException e) {
            // Si ocurre un error al convertir a número, lanza esta excepción
            throw new Exception("Nivel invalido, debe ser un numero");
        }
    }
}
