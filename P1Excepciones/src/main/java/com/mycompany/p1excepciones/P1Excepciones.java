/*
 * Clase principal (main) del proyecto.
 * 
 * Esta clase representa la entrada del sistema de registro de estudiantes en la ESPE.
 * Desde aquí se muestra un menú para que el usuario pueda:
 * - Registrar un nuevo estudiante (opción 1)
 * - Consultar los datos de un estudiante por cédula (opción 2)
 * - Salir del sistema (opción 3)
 * 
 * Llama a las siguientes clases:
 * - `RegistroService`: para guardar y buscar estudiantes.
 * - `Validador`: para validar cédula, nombre, correo y nivel académico.
 * - `Estudiante`: para crear objetos con la información ingresada.
 * 
 * CUMPLIMIENTO DE REQUISITOS:
 * ✔️ Se implementa una aplicación Java en consola.
 * ✔️ Se registran los datos en un archivo CSV (`estudiantes.txt`).
 * ✔️ Usa `IllegalArgumentException` para validar campos vacíos o incorrectos.
 * ✔️ Usa excepciones personalizadas (`Exception` genéricas lanzadas desde `Validador`).
 * ✔️ Usa `try-catch` en el proceso de ingreso para manejar errores de entrada.
 * ✔️ Llama a `buscarPorCedula()` que lanza excepción cuando hay datos duplicados o cédulas inválidas.
 */
// Declaramos que esta clase pertenece al paquete "com.mycompany.p1excepciones"
package com.mycompany.p1excepciones;

// Importamos clases necesarias del proyecto
import com.mycompany.p1excepciones.models.Estudiante;         // Clase que representa a un estudiante
import com.mycompany.p1excepciones.services.RegistroService; // Clase que maneja la lógica de guardar y buscar estudiantes
import com.mycompany.p1excepciones.utils.Validador;           // Clase que contiene validaciones personalizadas

// Importamos clases estándar de Java
import java.io.IOException; // Para manejar errores de lectura/escritura en archivos
import java.util.Scanner;   // Para leer datos ingresados por el usuario desde el teclado

// Declaramos la clase principal del programa
public class P1Excepciones {

    // Método principal, el que se ejecuta cuando el programa comienza
    public static void main(String[] args) {

        // Usamos try-with-resources para que el Scanner se cierre automáticamente al finalizar
        try (Scanner scanner = new Scanner(System.in)) {

            // Imprimimos un banner decorativo del sistema en la consola
            System.out.println("\n");
            System.out.println("  ____        _        _____       _        _   ");
            System.out.println(" |  _ \\      | |      |  __ \\     | |      | |  ");
            System.out.println(" | | | | __ _| |_ __ _| |  | | ___| |_ __ _| |_ ");
            System.out.println(" | | | |/ _ | __/ _ | |  | |/ _ \\ __/ _ | __|");
            System.out.println(" | |_| | (_| | || (_| | |__| |  __/ || (_| | |_ ");
            System.out.println(" |____/ \\__,_|\\__\\__,_|_____/ \\___|\\__\\__,_|\\__|");
            System.out.println("\n  SISTEMA DE REGISTRO DE ESTUDIANTES - DATA");
            System.out.println("  Desarrollado por: Corina Acosta\n");

            // Creamos una instancia del servicio que maneja registros
            RegistroService registro = new RegistroService();

            // Mostramos mensaje de bienvenida al usuario
            System.out.println("Bienvenido al Sistema de Registro de Estudiantes ESPE ");

            // Creamos una variable para controlar cuándo salir del menú
            boolean salir = false;

            // Menú principal: se repite mientras el usuario no elija salir
            while (!salir) {
                // Mostramos el menú de opciones
                System.out.println("\nMENU:");
                System.out.println("1. Ingresar nuevo estudiante");
                System.out.println("2. Consultar estudiante por cedula");
                System.out.println("3. Salir");
                System.out.print("Elige una opcion: ");
                String opcion = scanner.nextLine(); // Leemos lo que el usuario escribe

                // Evaluamos qué opción eligió el usuario
                switch (opcion) {
                    case "1" -> { // Opción para ingresar un nuevo estudiante
                        try {
                            // --------------------------
                            // VALIDACIÓN DE LA CÉDULA
                            // --------------------------
                            String cedula;
                            while (true) {
                                System.out.print("Cedula del estudiante (10 digitos): ");
                                cedula = scanner.nextLine().trim(); // Quitamos espacios

                                // Validamos que la cédula tenga solo números
                                if (!cedula.matches("\\d+")) {
                                    System.err.println("⚠️ La cedula debe contener solo numeros.");
                                    continue; // Repite el ciclo si hay error
                                }

                                // Validamos que tenga exactamente 10 dígitos
                                if (cedula.length() != 10) {
                                    System.err.println("⚠️ La cedula debe tener exactamente 10 digitos.");
                                    continue;
                                }

                                // Verificamos que la cédula no esté ya registrada
                                if (registro.buscarPorCedula(cedula) != null) {
                                    System.err.println("⚠️ Esta cedula ya esta registrada.");
                                    continue;
                                }

                                // Si todo está bien, salimos del bucle
                                break;
                            }

                            // --------------------------
                            // CAPTURAMOS EL NOMBRE
                            // --------------------------
                            System.out.print("Nombre completo: ");
                            String nombre = scanner.nextLine();
                            Validador.validarNombre(nombre); // Validamos que solo tenga letras

                            // --------------------------
                            // CAPTURAMOS EL CORREO
                            // --------------------------
                            System.out.print("Correo institucional (@espe.edu.ec): ");
                            String correo = scanner.nextLine();
                            Validador.validarCorreo(correo); // Validamos que sea del dominio correcto

                            // --------------------------
                            // CAPTURAMOS EL NIVEL
                            // --------------------------
                            System.out.print("Nivel académico (1 - 8 semestre): ");
                            String nivelStr = scanner.nextLine();
                            Validador.validarNivel(nivelStr); // Validamos que esté entre 1 y 8
                            int nivel = Integer.parseInt(nivelStr); // Lo convertimos a número

                            // --------------------------
                            // GENERAMOS EL CÓDIGO ÚNICO
                            // --------------------------
                            String codigo = registro.generarCodigoUnico(); // Ej: ID:L00481234

                            // Creamos un nuevo objeto Estudiante con los datos ingresados
                            Estudiante estudiante = new Estudiante(codigo, cedula, nombre, correo, nivel);

                            // Guardamos el estudiante en el archivo
                            registro.guardarEstudiante(estudiante);

                            System.out.println("✅ Estudiante registrado exitosamente.\n");

                        } catch (Exception e) {
                            // Si hubo un error (por ejemplo, nombre inválido), lo mostramos
                            System.err.println("❌ Error: " + e.getMessage());
                        }
                    }

                    case "2" -> { // Opción para consultar estudiante por cédula
                        Estudiante encontrado = null;

                        // Repetimos hasta que se encuentre la cédula correcta
                        while (encontrado == null) {
                            try {
                                System.out.print("Ingrese la cedula del estudiante (10 digitos): ");
                                String cedulaConsulta = scanner.nextLine();

                                // Validamos que tenga 10 dígitos numéricos
                                if (!cedulaConsulta.matches("\\d{10}")) {
                                    System.err.println("⚠️ La cedula debe tener 10 digitos numericos.");
                                    continue;
                                }

                                // Buscamos al estudiante en el archivo
                                encontrado = registro.buscarPorCedula(cedulaConsulta);

                                if (encontrado == null) {
                                    // Si no se encuentra, avisamos
                                    System.err.println("⚠️ Cedula no encontrada. Intente nuevamente.");
                                } else {
                                    // Si se encuentra, mostramos los datos
                                    System.out.println("\nDatos del estudiante:");
                                    System.out.println(encontrado.toDisplayString());
                                }

                            } catch (IOException e) {
                                // Si hubo un error con el archivo
                                System.err.println("❌ Error de archivo: " + e.getMessage());
                            }
                        }
                    }

                    case "3" -> { // Opción para salir del programa
                        salir = true;
                        System.out.println(" Gracias por usar el sistema. ¡Hasta luego!");
                    }

                    default -> {
                        // Si se ingresa una opción no válida
                        System.err.println("❌ Opcion invalida. Intente nuevamente.");
                    }
                }
            }
        }
    }
}
