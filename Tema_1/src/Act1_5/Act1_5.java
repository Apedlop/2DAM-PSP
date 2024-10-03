package Act1_5;

import java.io.*;

public class Act1_5 {

    public static void main(String[] args) throws IOException {

        // Cambiamos el formato del directorio para que sea compatible
        File directorio = new File("/home/usuario/Documentos/2DAM-PSP/Tema_1/out/production/Tema_1");  // Usamos "/" en lugar de "\\"

        // Configuramos el ProcessBuilder para ejecutar Ejemplo2
        ProcessBuilder pb = new ProcessBuilder("java", "Ejemplos.ProgNoExis");

        // Establecemos el directorio de trabajo
        pb.directory(directorio);
        System.out.printf("Directorio de trabajo: %s%n", pb.directory());

        // Iniciamos el proceso
        Process p = pb.start();

        try {
            // Leemos la salida del proceso
            InputStream is = p.getInputStream();
            int c;
            while ((c = is.read()) != -1) {
                System.out.print((char) c);
            }
            is.close(); // Movemos el cierre del InputStream fuera del ciclo
        } catch (Exception e) {
            e.printStackTrace();
        }

        // MUESTRA ERROR
        try {
            InputStream er = p.getErrorStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(er));
            String liner = null;
            while ((liner = br.readLine()) != null) {
                System.out.println(liner);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        /*El error que nos muesta nos  indica que la clase Ejemplos.ProgNoExis no existe en el sistema o no es accesible
         la ruta donde Java está buscando, lo que impide que el programa se ejecute correctamente.*/

    }

}
