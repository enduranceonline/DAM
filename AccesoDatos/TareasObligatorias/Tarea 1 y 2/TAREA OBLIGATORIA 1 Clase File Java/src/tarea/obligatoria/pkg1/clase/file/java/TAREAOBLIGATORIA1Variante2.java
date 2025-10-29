package tarea.obligatoria.pkg1.clase.file.java;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

class TAREAOBLIGATORIA1Variante2 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n=== MENÚ DE OPCIONES ===");
            System.out.println("1. Crear directorio nuevoDirectorio");
            System.out.println("2. Crear fichero fichero_de_texto2.txt");
            System.out.println("3. Eliminar fichero fichero_de_texto.txt");
            System.out.println("4. Eliminar directorio nuevoDirectorio");
            System.out.println("5. Salir");
            System.out.print("Elige una opción: ");

            // Este bloque controla que el usuario introduzca un número entero válido (por ejemplo 1, 2, 3...)
            // y evita que el programa falle si se escriben letras u otros símbolos.
            while (!sc.hasNextInt()) {                              
            System.out.print("Introduce un número válido: ");    // Mensaje que pide una nueva entrada válida.
            sc.next();                                           // Descarta el valor no numérico introducido para limpiar el buffer.
            }

            // Si el usuario introduce un número válido, se guarda en la variable 'opcion'.
            opcion = sc.nextInt();                                   

            // Después de leer un número con nextInt(), queda un salto de línea pendiente en el buffer.
            // nextLine() se utiliza para "consumirlo" y evitar problemas en futuras lecturas de texto.
            sc.nextLine();                                           

            // Try es una estructura de control de lenguaje
            try {
                // Crea un objeto File que representa el directorio "nuevoDirectorio"
                // dentro de la ruta relativa "AD/Ejercicios".
                //️ Este objeto todavía no crea nada físicamente; simplemente APUNTA a esa ruta.
                File dir = new File("AD/Ejercicios/nuevoDirectorio");
                // Crea otro objeto File apuntando al fichero "fichero_de_texto.txt"
                // que estaría directamente en la carpeta "AD/Ejercicios" (fuera de nuevoDirectorio).
                // Este fichero corresponde al del ejercicio anterior (Variante 1).
                File f1 = new File("AD/Ejercicios/fichero_de_texto.txt");
                // Aquí aparece el concepto de "parent" (padre):
                // Este constructor de File recibe DOS argumentos:
                //   - El primero (dir) actúa como "directorio padre".
                //   - El segundo ("fichero_de_texto2.txt") es el nombre del archivo dentro de ese directorio.
                //
                // Por tanto, este objeto File equivale a la ruta:
                //   "AD/Ejercicios/nuevoDirectorio/fichero_de_texto2.txt"
                //
                // Esta forma es más clara y profesional que concatenar cadenas manualmente,
                // porque usa la relación jerárquica real entre carpetas y archivos.
                File f2 = new File(dir, "fichero_de_texto2.txt");

                // Estructura switch–case que evalúa la opción introducida por el usuario en el menú.
                // Según el número elegido, se ejecuta un bloque de código distinto.
                switch (opcion) {
                    // ======================================
                // OPCIÓN 1 → Crear directorio
                // ======================================
                case 1:
                    // Comprueba si el directorio NO existe antes de crearlo.
                    if (!dir.exists()) {
                        dir.mkdirs(); // mkdirs() crea todos los directorios intermedios si no existen.
                        System.out.println("Directorio creado correctamente.");
                    } else {
                        // Si ya existía, informa al usuario y no lo recrea.
                        System.out.println("El directorio ya existe.");
                    }
                    break; // Evita que el programa continúe ejecutando el siguiente case.

                // ======================================
                // OPCIÓN 2 → Crear fichero dentro del directorio
                // ======================================
                case 2:
                    // Asegura que el directorio existe antes de crear el fichero.
                    if (!dir.exists()) dir.mkdirs();

                    // 💡 Comprobación previa: si el fichero ya existe, lo eliminamos.
                    // Esto permite testear el programa varias veces sin conflictos ni mensajes de error.
                    if (f2.exists()) {
                        if (f2.delete()) {
                            System.out.println("Fichero previo eliminado para reiniciar la prueba.");
                        } else {
                            System.out.println("No se ha podido eliminar el fichero previo.");
                        }
                    }

                    // Intenta crear el nuevo fichero vacío dentro del directorio.
                    // createNewFile() devuelve true si se crea correctamente o false si ya existía.
                    if (f2.createNewFile()) {
                        System.out.println("Fichero creado correctamente en nuevoDirectorio.");
                    } else {
                        System.out.println("ERROR: No se ha podido crear el fichero.");
                    }
                    break;

                // ======================================
                // OPCIÓN 3 → Eliminar fichero (de la Variante 1)
                // ======================================
                case 3:
                    // Comprueba si el fichero f1 existe y lo elimina con delete().
                    // El operador && asegura que solo se muestra el mensaje de éxito si delete() devuelve true.
                    if (f1.exists() && f1.delete()) {
                        System.out.println("Fichero eliminado correctamente.");
                    } else {
                        System.out.println("No se ha podido eliminar el fichero (no existe o está en uso).");
                    }
                    break;

                // ======================================
                // OPCIÓN 4 → Eliminar directorio
                // ======================================
                case 4:
                    // delete() solo puede eliminar directorios vacíos.
                    // Si el directorio contiene archivos, fallará y mostrará el mensaje de error.
                    if (dir.exists() && dir.delete()) {
                        System.out.println("Directorio eliminado correctamente.");
                    } else {
                        System.out.println("No se puede eliminar el directorio (puede que no exista o no esté vacío).");
                    }
                    break;

                // ======================================
                // OPCIÓN 5 → Salir del programa
                // ======================================
                case 5:
                    System.out.println("Saliendo del programa...");
                    break;

                // ======================================
                // Cualquier otra opción → No válida
                // ======================================
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
                }

                // ==========================================================
                // El bloque try–catch captura posibles errores de E/S (IOException)
                // por ejemplo, si hay problemas al crear o eliminar archivos.
                // ==========================================================
                } catch (IOException e) {
                    System.out.println("Error de E/S: " + e.getMessage());
                }

                // ==========================================================
                // El menú se repetirá mientras la opción elegida sea distinta de 5.
                // Cuando el usuario elige 5, se sale del bucle y finaliza el programa.
                // ==========================================================
                } while (opcion != 5);

                // Cierra el objeto Scanner para liberar recursos del sistema.
                // Siempre es buena práctica cerrar los flujos de entrada/salida.
                sc.close();

                // Mensaje final que indica el fin del programa.
                System.out.println("FIN DEL PROGRAMA");
    }
}
