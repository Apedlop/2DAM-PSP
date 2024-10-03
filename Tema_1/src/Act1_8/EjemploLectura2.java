package Act1_8;

import java.io.*;

public class EjemploLectura2 {

    public static void main(String[] args) throws IOException {

        // Directorio donde está el archivo .class de EjemploLectura
        File directorio = new File("/home/usuario/Documentos/2DAM-PSP/Tema_1/out/production/Tema_1");

        // Directorio donde está el archivo .class de EjemploLectura
//        ProcessBuilder pb = new ProcessBuilder("C:\\Users\\pedre\\.jdks\\openjdk-23\\bin\\java.exe", "Act1_7.EjemploLectura"); // Windows
        ProcessBuilder pb = new ProcessBuilder("java", "Act1_8.EjemploLectura");

        // Fichero de entrada
//        File archivoEntrada = new File("C:\\Users\\pedre\\OneDrive\\Documentos\\2DAM-PSP\\Tema_1\\Ficheros\\Act1_8\\Entrada.txt"); // Windows
        File archivoEntrada = new File("/home/usuario/Documentos/2DAM-PSP/Tema_1/Ficheros/Act1_8/Entrada.txt");
        pb.redirectInput(ProcessBuilder.Redirect.from(archivoEntrada));

        // Fichero de salida
//        File archivoSalida = new File("C:\\Users\\pedre\\OneDrive\\Documentos\\2DAM-PSP\\Tema_1\\Ficheros\\Act1_8\\Salida.txt"); // Windows
        File archivoSalida = new File("/home/usuario/Documentos/2DAM-PSP/Tema_1/Ficheros/Act1_8/Salida.txt");
        pb.redirectOutput(ProcessBuilder.Redirect.to(archivoSalida));

        // Establecemos el directorio donde ejecutar el proceso
        pb.directory(directorio);

        // Se ejecuta el proceso
        Process p = pb.start();

        // COMPROBACIÓN DE ERROR - 0 bien - 1 mal
        int exiVal;
        try {
            exiVal = p.waitFor();  // Esperar a que el proceso termine antes de continuar
            System.out.println("Valor de Salida: " + exiVal);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Mostrar la salida del proceso en la consola mientras se guarda en el fichero
        try (BufferedReader br = new BufferedReader(new FileReader(archivoSalida))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);  // Mostrar la salida del proceso en consola
            }
        }

        // ERRORES
        try {
            InputStream er = p.getErrorStream();
            BufferedReader brer = new BufferedReader(new InputStreamReader(er));
            String liner;
            while ((liner = brer.readLine()) != null) {
                System.out.println("ERROR >" + liner);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
