package Act1_8;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        // Directorio donde está el archivo .class de EjemploLectura
        File directorio = new File("C:\\Users\\pedre\\OneDrive\\Documentos\\2DAM-PSP\\Tema_1"); // Asegúrate de que esta ruta es correcta en tu sistema

        // Crear el ProcessBuilder para ejecutar el programa
        ProcessBuilder pb = new ProcessBuilder("java", "Act1_8.EjemploLectura");

        // Fichero de entrada (asegúrate de que existe y tiene datos)
        File archivoEntrada = new File("C:\\Users\\pedre\\OneDrive\\Documentos\\2DAM-PSP\\Tema_1\\src\\Act1_8\\Entrada.txt"); // Asegúrate de que esta ruta es correcta

        // Fichero de salida
        File archivoSalida = new File("C:\\Users\\pedre\\OneDrive\\Documentos\\2DAM-PSP\\Tema_1\\src\\Act1_8\\Salida.txt"); // Asegúrate de que esta ruta es correcta

        // Redirigir la entrada y salida del proceso
        pb.redirectInput(ProcessBuilder.Redirect.from(archivoEntrada));

        // Establecemos el directorio donde ejecutar el proceso
        pb.directory(directorio);

        // Ejecutar el proceso
        Process p = pb.start();

        // Leer la salida del proceso en la consola
        try (InputStream is = p.getInputStream()) {
            int c;
            while ((c = is.read()) != -1) {
                System.out.print((char) c);  // Imprimir la salida en la consola
            }
        }

        // Copiar la entrada al archivo de salida
        try (BufferedReader in = new BufferedReader(new FileReader(archivoEntrada));
             BufferedWriter out = new BufferedWriter(new FileWriter(archivoSalida, true))) {  // true para no sobrescribir
            String texto;
            while ((texto = in.readLine()) != null) {
                out.write(texto + "\n");  // Escribir con salto de línea
            }
        }

        // Comprobación del valor de salida del proceso (0 = éxito)
        int exitVal;
        try {
            exitVal = p.waitFor();  // Esperar a que el proceso termine
            System.out.println("Valor de Salida: " + exitVal);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Leer y mostrar los errores del proceso, si los hay
        try (BufferedReader brer = new BufferedReader(new InputStreamReader(p.getErrorStream()))) {
            String errorLinea;
            while ((errorLinea = brer.readLine()) != null) {
                System.out.println("ERROR > " + errorLinea);  // Imprimir los errores del proceso
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
