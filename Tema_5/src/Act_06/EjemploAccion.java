package Act_06;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.PrivilegedAction;

public class EjemploAccion implements PrivilegedAction<Object> {

    public Object run() {
        File f = new File("Act_06/fichero.txt");

        if (f.exists()) {
            System.out.println("El fichero existe...");

            // Mostrar su contenido
            try (FileReader fic = new FileReader(f)) {
                int i;
                System.out.println("Su contenido es: ");
                while ((i = fic.read()) != -1) {
                    System.out.print((char) i);
                }
                System.out.println();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Si no existe, se crea y se insertan datos
            System.out.println("El fichero no existe, lo creo...");

            try (FileWriter fic = new FileWriter(f)) {
                String cadena = "Esto es una línea de texto";
                fic.write(cadena);
                System.out.println("Fichero creado con datos...");
            } catch (IOException e) {
                System.out.println("Error => " + e.getMessage());
            }
        }

        return null;
    }
}
