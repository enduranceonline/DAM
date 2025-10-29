package tarea.obligatoria.pkg1.clase.file.java;

import java.io.File;
import java.io.IOException;

class TAREAOBLIGATORIA1Variante1 {

    public static void main(String[] args) {

        // Ruta relativa y portable dentro del proyecto (puede adaptarse a C:\AD\Ejercicios en Windows si se desea).
        File ruta = new File("AD/Ejercicios");
        File fichero = new File(ruta, "fichero_de_texto.txt");

        try {
            // Si la carpeta no existe, la creamos
            if (!ruta.exists()) {
                ruta.mkdirs();
                System.out.println("Directorio creado correctamente.");
            }

            // Si el fichero ya existe, lo borramos antes de crearlo de nuevo.
            // Esto permite testear el programa varias veces sin que aparezca el mensaje "ya existe".
            if (fichero.exists()) {
                if (fichero.delete()) {
                    System.out.println("Fichero previo eliminado para reiniciar la prueba.");
                } else {
                    System.out.println("No se ha podido eliminar el fichero previo.");
                }
            }

            // Ahora creamos el fichero desde cero
            if (fichero.createNewFile()) {
                System.out.println("Fichero creado correctamente en " + fichero.getAbsolutePath());
            } else {
                System.out.println("ERROR: No se ha podido crear el fichero.");
            }

        } catch (IOException e) {
            System.out.println("Error de entrada/salida: " + e.getMessage());
        }

        System.out.println("FIN DEL PROGRAMA");
    }
}


