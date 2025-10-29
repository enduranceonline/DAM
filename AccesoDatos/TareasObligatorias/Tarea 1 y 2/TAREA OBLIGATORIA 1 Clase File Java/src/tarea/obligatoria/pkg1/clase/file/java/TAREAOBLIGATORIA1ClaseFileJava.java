package tarea.obligatoria.pkg1.clase.file.java;

import java.io.File;                  // Importa la clase File para trabajar con ficheros y directorios.
import java.io.IOException;           // Importa la excepción de E/S que puede lanzar createNewFile().

public class TAREAOBLIGATORIA1ClaseFileJava {

    public static void main(String[] args) {

        // Rutas relativas dentro del proyecto (quedarán bajo "src/tarea/obligatoria/pkg1/clase/file/java/...").
        File miDirectorio = new File("src/tarea/obligatoria/pkg1/clase/file/java/miDirectorio"); 
        // Crea un objeto File que APUNTA a la ruta indicada. Todavía NO crea físicamente el directorio.

        File miFichero = new File("src/tarea/obligatoria/pkg1/clase/file/java/miDirectorio/miFichero.txt"); 
        // Otro objeto File que apunta al fichero dentro del directorio anterior. Tampoco se crea aún el fichero; solo se referencia la ruta.

        try {                                   // Inicio de un bloque que puede lanzar IOException.

            miDirectorio.mkdir();               // Intenta crear el directorio (un único nivel). 
                                                 // Devuelve true si lo crea y false si ya existía o si falla (permisos, ruta, etc.).

            if (miFichero.createNewFile())      // Intenta crear físicamente el fichero. Devuelve true si lo crea; false si YA EXISTE.
                System.out.println("Fichero creado correctamente");
            else
                System.out.println("ERROR: No se ha podido crear el fichero (puede que ya exista).");

            // El if/else no es necesario para crear el fichero en sí, solo lo ponemos para informar por consola.

        } catch (IOException e) {               // Captura la IOException que puede lanzar createNewFile().
            System.out.println("Error al crear el fichero: " + e.getMessage()); 
            // Alternativa más detallada para depuración:
            // e.printStackTrace();
        }

        System.out.println("FIN DEL PROGRAMA"); // Mensaje optativo de cierre, solo informativo.
    }
}
