package tarea.obligatoria.pkg1.clase.file.java;

import java.io.*;  // Importa todas las clases necesarias para trabajar con archivos

/**
 * Programa: TAREAOBLIGATORIA2_Ejercicio2
 * Asignatura: Acceso a Datos
 * Descripción: 
 * - Crea un fichero de texto llamado Empleados.txt en la ruta C:\AD\Ejercicio1\Variante2
 * - Escribe 10 empleados (ID y nombre) dentro del fichero
 * - Luego lee su contenido de dos formas:
 *      1. Usando FileWriter y FileReader (métodos básicos)
 *      2. Usando BufferedWriter y BufferedReader (métodos optimizados)
 * 
 * Objetivo didáctico:
 * Comprender las diferencias entre los flujos de entrada/salida directos (File)
 * y los flujos con buffer (Buffered), aplicando control de errores con IOException.
 * 
 * Autor: David Rodríguez Igual
 * Fecha: 23 Octubre 2025
 */
public class TAREAOBLIGATORIA2_Ejercicio2 {

    // ===== RUTA DEL FICHERO =====
    // La clase File representa una ruta o un archivo físico en disco.
    // No crea el archivo por sí misma, solo "apunta" a él.
    private static final File FICHERO_EMPLEADOS =
            new File("C:\\AD\\Ejercicio1\\Variante2\\Empleados.txt");

    // ===== DATOS A ESCRIBIR =====
    // Array con 10 empleados, cada uno formado por un ID y un nombre.
    // Se usarán para escribir las líneas del fichero.
    private static final String[] EMPLEADOS = {
            "1;Ana Gómez", "2;Luis Pérez", "3;Marta Ruiz", "4;Iván Soto", "5;Noa Vidal",
            "6;Sara León", "7;Hugo Mora", "8;Leo Navas", "9;Nora Gil", "10;Pau Roca"
    };

    // ===== MÉTODO PRINCIPAL =====
    public static void main(String[] args) {
        // El método main() actúa como punto de entrada del programa.

        // 1. Escribimos y leemos el fichero con las clases básicas FileWriter/FileReader.
        escribirConFileWriter();
        leerConFileReader();

        // 2. Luego repetimos con las clases optimizadas BufferedWriter/BufferedReader.
        escribirConBufferedWriter();
        leerConBufferedReader();
    }

    // ===== 1️⃣ Escritura con FileWriter =====
    /**
     * FileWriter → clase de escritura de texto carácter a carácter directamente en disco.
     * No usa buffer intermedio, por lo que cada operación de write() accede al sistema de archivos.
     * 
     * Este método:
     * - Crea (o sobrescribe) el fichero Empleados.txt
     * - Escribe una línea por empleado
     * - Añade salto de línea con System.lineSeparator()
     */
    private static void escribirConFileWriter() {
        System.out.println("\n--- Escritura con FileWriter ---");

        // mkdirs() crea los directorios intermedios si no existen.
        // No crea el archivo, solo las carpetas necesarias.
        FICHERO_EMPLEADOS.getParentFile().mkdirs();

        // try-with-resources: cierra automáticamente el flujo al finalizar el bloque.
        try (FileWriter fw = new FileWriter(FICHERO_EMPLEADOS)) {
            // Bucle for-each que recorre cada elemento del array EMPLEADOS
            for (String emp : EMPLEADOS) {
                fw.write(emp);                   // Escribe el texto del empleado (ID y nombre)
                fw.write(System.lineSeparator()); // Inserta salto de línea compatible con Windows
            }

            fw.flush(); // Envía cualquier dato pendiente del buffer interno al archivo
            System.out.println("Fichero creado correctamente en: " + FICHERO_EMPLEADOS.getAbsolutePath());
        } catch (IOException e) {
            // IOException: error general de entrada/salida (permiso, ruta, espacio, etc.)
            System.out.println("ERROR al escribir con FileWriter: " + e.getMessage());
        }
    }

    // ===== 2️⃣ Lectura con FileReader =====
    /**
     * FileReader → clase de lectura de texto carácter a carácter directamente desde disco.
     * No usa buffer, por lo que cada read() lee un único carácter del archivo.
     * 
     * Este método:
     * - Abre el fichero Empleados.txt
     * - Lee carácter a carácter hasta llegar al final (-1)
     * - Muestra su contenido por consola
     */
    private static void leerConFileReader() {
        System.out.println("\n--- Lectura con FileReader ---");

        try (FileReader fr = new FileReader(FICHERO_EMPLEADOS)) {
            int c; // Variable para almacenar cada carácter leído (valor ASCII)
            while ((c = fr.read()) != -1) { // -1 indica fin de archivo (EOF)
                System.out.print((char) c); // Convierte el valor numérico en carácter
            }
        } catch (IOException e) {
            System.out.println("ERROR al leer con FileReader: " + e.getMessage());
        }
    }

    // ===== 3️⃣ Escritura con BufferedWriter =====
    /**
     * BufferedWriter → clase de escritura con buffer intermedio en memoria.
     * 
     * Diferencias con FileWriter:
     * - Almacena temporalmente los datos antes de volcarlos al disco, reduciendo accesos físicos.
     * - Usa el método newLine() en lugar de System.lineSeparator().
     * 
     * Este método:
     * - Reescribe el fichero Empleados.txt (sobrescribe el contenido anterior)
     * - Escribe los mismos empleados de forma más eficiente
     */
    private static void escribirConBufferedWriter() {
        System.out.println("\n--- Escritura con BufferedWriter ---");

        // El BufferedWriter se asocia a un FileWriter interno
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FICHERO_EMPLEADOS))) {
            for (String emp : EMPLEADOS) {
                bw.write(emp);  // Escribe la línea con ID y nombre
                bw.newLine();   // Añade salto de línea de forma segura y portable
            }
            // flush() no es necesario aquí porque el try-with-resources ya cierra el flujo correctamente
            System.out.println("Fichero reescrito correctamente con BufferedWriter.");
        } catch (IOException e) {
            System.out.println("ERROR al escribir con BufferedWriter: " + e.getMessage());
        }
    }

    // ===== 4️⃣ Lectura con BufferedReader =====
    /**
     * BufferedReader → clase de lectura de texto con buffer intermedio en memoria.
     * 
     * Diferencias con FileReader:
     * - Permite leer líneas completas usando readLine()
     * - Mayor rendimiento porque reduce las operaciones de disco.
     * 
     * Este método:
     * - Lee el contenido del fichero Empleados.txt línea por línea
     * - Muestra cada línea en consola
     */
    private static void leerConBufferedReader() {
        System.out.println("\n--- Lectura con BufferedReader ---");

        try (BufferedReader br = new BufferedReader(new FileReader(FICHERO_EMPLEADOS))) {
            String linea; // Variable para guardar cada línea leída
            while ((linea = br.readLine()) != null) { // readLine() devuelve null al final del archivo
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("ERROR al leer con BufferedReader: " + e.getMessage());
        }
    }
}

