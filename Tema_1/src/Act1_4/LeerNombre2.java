package Act1_4;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class LeerNombre2 {

    public static void main(String[] args) throws IOException {

        // Situamos dónde está el programa
        File directorio = new File("/home/usuario/Documentos/2DAM-PSP/Tema_1/out/production/Tema_1"); // Ubuntu

        // Le pasamos el programa, el nombre de la clase y un argumento
        ProcessBuilder pb = new ProcessBuilder("java", "Act1_4.LeerNombre", "Lucas"); // Ubuntu

        // EStablecemos el directorio donde se encuentra el archivo
        pb.directory(directorio);

        // Ejecutamos el proceso
        Process p = pb.start();

        // Obtener la salida devuelta por el proceso
        try {
            InputStream is = p.getInputStream();
            int c;
            while ((c = is.read()) != -1) {
                System.out.print((char) c);
            }
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // COMPROBACIÓN DE ERROR - 0 bien - 1 mal
        int exiVal;
        try {
            exiVal = p.waitFor();
            System.out.println("Valor de Salida: " + exiVal);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
