package tarea.obligatoria.pkg1.clase.file.java;

import java.io.*;      // Clases necesarias para manejo de archivos (File, FileWriter, IOException)
import java.util.*;    // Clases para listas, arrays y lectura de teclado (Scanner, Arrays, List)

/**
 * Programa: TAREAOBLIGATORIA2Variante3
 * Asignatura: Acceso a Datos
 * Descripción: Gestión de archivos y directorios mediante menú de opciones.
 *              Incluye creación, eliminación y escritura en ficheros de texto.
 * Autor: David Rodríguez Igual
 * Fecha: 23 Octubre 2025
 */
public class TAREAOBLIGATORIA2Variante3 {

    // ===== RUTAS PRINCIPALES =====
    // Directorio base de trabajo
    private static final File BASE = new File("C:\\AD\\Ejercicio1\\Variante2");

    // Subdirectorio donde se crearán los archivos
    private static final File DIR_NUEVO = new File(BASE, "nuevoDirectorio");

    // Fichero nuevo dentro del directorio anterior
    private static final File FICHERO2 = new File(DIR_NUEVO, "fichero_de_texto2.txt");

    // Fichero del ejercicio anterior (Variante1) que se eliminará
    private static final File FICHERO1_ANT = new File("C:\\AD\\Ejercicio1\\Variante1\\fichero_de_texto.txt");


    // ===== MÉTODO PRINCIPAL =====
    public static void main(String[] args) {
        // Crea la ruta base si no existe
        BASE.mkdirs();

        // Se usa Scanner para leer las opciones del usuario por consola
        try (Scanner sc = new Scanner(System.in)) {
            boolean salir = false;
            // Bucle principal del menú: se repite hasta elegir "Salir"
            while (!salir) {
                mostrarMenu();  // Muestra el menú en pantalla
                String op = sc.nextLine().trim(); // Lee opción sin espacios
                switch (op) {
                    case "1": crearDirectorio(); break;
                    case "2": crearFichero2(); break;
                    case "3": eliminarFicheroAnterior(); break;
                    case "4": eliminarDirectorioRecursivo(); break;
                    case "5": escribirProvinciasAndalucia(); break;
                    case "6": salir = true; System.out.println("Saliendo..."); break;
                    default: System.out.println("Opción no válida. Intenta de nuevo.");
                }
            }
        }
    }


    // ===== MENÚ PRINCIPAL =====
    /** 
     * Muestra las distintas opciones del programa al usuario.
     * Se ejecuta en cada iteración del bucle principal.
     */
    private static void mostrarMenu() {
        System.out.println("\n=== MENÚ ACCESO A DATOS ===");
        System.out.println("Ruta base: " + BASE.getAbsolutePath());
        System.out.println("1) Crear directorio 'nuevoDirectorio'");
        System.out.println("2) Crear fichero 'fichero_de_texto2.txt' en 'nuevoDirectorio'");
        System.out.println("3) Eliminar fichero del ejercicio anterior 'fichero_de_texto.txt'");
        System.out.println("4) Eliminar directorio 'nuevoDirectorio' (recursivo)");
        System.out.println("5) Escribir provincias de Andalucía en 'fichero_de_texto2.txt'");
        System.out.println("6) Salir");
        System.out.print("Elige opción: ");
    }


    // ===== OPCIÓN 1: CREAR DIRECTORIO =====
    /**
     * Crea el directorio nuevoDirectorio dentro de la ruta base.
     * Si ya existe, informa al usuario.
     */
    private static void crearDirectorio() {
        if (DIR_NUEVO.exists()) {
            System.out.println("El directorio ya existe: " + DIR_NUEVO.getAbsolutePath());
        } else if (DIR_NUEVO.mkdirs()) {
            System.out.println("Directorio creado: " + DIR_NUEVO.getAbsolutePath());
        } else {
            System.out.println("ERROR: No se pudo crear el directorio.");
        }
    }


    // ===== OPCIÓN 2: CREAR FICHERO =====
    /**
     * Crea un fichero de texto dentro del directorio nuevoDirectorio.
     * Usa createNewFile(), que devuelve true si se crea correctamente.
     * Puede lanzar IOException si ocurre un error de entrada/salida.
     */
    private static void crearFichero2() {
        if (!DIR_NUEVO.exists()) {
            System.out.println("ERROR: No existe 'nuevoDirectorio'.");
            return;
        }
        try {
            if (FICHERO2.createNewFile()) {
                System.out.println("Fichero creado: " + FICHERO2.getAbsolutePath());
            } else {
                System.out.println("El fichero ya existía: " + FICHERO2.getAbsolutePath());
            }
        } catch (IOException e) {
            System.out.println("ERROR al crear el fichero: " + e.getMessage());
        }
    }


    // ===== OPCIÓN 3: ELIMINAR FICHERO DEL EJERCICIO ANTERIOR =====
    /**
     * Elimina el fichero del ejercicio anterior (Variante1).
     * Usa delete(), que devuelve true si se borra correctamente.
     */
    private static void eliminarFicheroAnterior() {
        if (!FICHERO1_ANT.exists()) {
            System.out.println("No existe el fichero: " + FICHERO1_ANT.getAbsolutePath());
        } else if (FICHERO1_ANT.delete()) {
            System.out.println("Fichero eliminado: " + FICHERO1_ANT.getAbsolutePath());
        } else {
            System.out.println("ERROR: No se pudo eliminar el fichero.");
        }
    }


    // ===== OPCIÓN 4: ELIMINAR DIRECTORIO RECURSIVO =====
    /**
     * Elimina el directorio nuevoDirectorio y todo su contenido.
     * Usa una función auxiliar recursiva llamada borrarContenido().
     */
    private static void eliminarDirectorioRecursivo() {
        if (!DIR_NUEVO.exists()) {
            System.out.println("No existe el directorio: " + DIR_NUEVO.getAbsolutePath());
            return;
        }
        borrarContenido(DIR_NUEVO);
        if (DIR_NUEVO.delete()) {
            System.out.println("Directorio eliminado: " + DIR_NUEVO.getAbsolutePath());
        } else {
            System.out.println("ERROR: No se pudo eliminar el directorio.");
        }
    }


    // ===== OPCIÓN 5: ESCRIBIR PROVINCIAS EN EL FICHERO =====
    /**
     * Escribe las provincias de Andalucía dentro del fichero fichero_de_texto2.txt.
     * Usa FileWriter para escribir línea por línea y flush() para limpiar el buffer.
     * Antes de escribir, comprueba que el fichero exista.
     */
    private static void escribirProvinciasAndalucia() {
        String[] provincias = {
                "Almería", "Cádiz", "Córdoba", "Granada",
                "Huelva", "Jaén", "Málaga", "Sevilla"
        };

        // Verificación de existencia del fichero antes de escribir
        if (!FICHERO2.exists()) {
            System.out.println("Error: No existe el fichero 'fichero_de_texto2.txt'.");
            return;
        }

        // Escritura de provincias usando FileWriter
        try (FileWriter fw = new FileWriter(FICHERO2)) {
            for (int i = 0; i < provincias.length; i++) {
                fw.write(provincias[i]);             // Escribe el nombre de la provincia
                fw.write(System.lineSeparator());    // Inserta salto de línea
            }
            fw.flush(); // Limpia el buffer y asegura que se escriba todo
            System.out.println("Provincias escritas correctamente en el fichero.");
        } catch (IOException e) {
            System.out.println("ERROR al escribir en el fichero: " + e.getMessage());
        }
    }


    // ===== FUNCIÓN AUXILIAR PARA ELIMINACIÓN RECURSIVA =====
    /**
     * Recorre el contenido de un directorio y elimina todos los ficheros y subdirectorios.
     * Si encuentra subcarpetas, las elimina primero (llamada recursiva).
     * @param dir Directorio a limpiar
     */
    private static void borrarContenido(File dir) {
        File[] hijos = dir.listFiles();  // Lista los archivos dentro del directorio
        if (hijos == null) return;       // Si está vacío o inaccesible, sale
        for (File h : hijos) {
            if (h.isDirectory()) borrarContenido(h); // Llamada recursiva para subcarpetas
            if (h.delete()) {
                System.out.println("Eliminado: " + h.getAbsolutePath());
            } else {
                System.out.println("No se pudo eliminar: " + h.getAbsolutePath());
            }
        }
    }
}


