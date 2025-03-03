package Act_05;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Accion {

    public Object run() {
        File f = new File("fichero.txt");

        if (f.exists()) {
            System.out.println("El fichero existe...");
            FileReader fic;

            try {
                fic = new FileReader(f);
                int i;
                System.out.println("Su contenido es:");
                while ((i = fic.read()) != -1) {
                    fic.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("El fichero no existe, lo creo...");

            try {
                FileWriter fic = new FileWriter(f);
                String cadena = "Esto es una linea de texto";
                fic.append(cadena);
                fic.close();
                System.out.println("Fichero creado con datos...");
            } catch (IOException e) {
                System.out.println("Error ==> " + e.getMessage());
            }
        }
        return null;
    }

}
