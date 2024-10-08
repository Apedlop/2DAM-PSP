package Act1_7;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        // Directorio donde está el archivo .class de EjemploLectura
        File directorio = new File("C:\\Users\\pedre\\OneDrive\\Documentos\\2DAM-PSP\\Tema_1\\out\\production\\Tema_1"); // Windows
        //  File directorio = new File("/out/production/Tema_1");

        // Archivo de entrada (de donde tomará los datos de entrada)
        File archivoEntrada = new File("C:\\Users\\pedre\\OneDrive\\Documentos\\2DAM-PSP\\Tema_1\\src\\Act1_7\\Entrada.txt"); // Windows
       // File archivoEntrada = new File("src/Act1_7/Entrada.txt");

        // Archivo de salida (donde se guardará la salida estándar)
        File archivoSalida = new File("C:\\Users\\pedre\\OneDrive\\Documentos\\2DAM-PSP\\Tema_1\\src\\Act1_7\\Salida.txt"); // Windows
        // File archivoSalida = new File("src/Act1_7/Salida.txt");

        ProcessBuilder pb = new ProcessBuilder("java", "Act1_7.EjemploLectura"); // Windows
        // ProcessBuilder pb = new ProcessBuilder("java", "Act1_7.EjemploLectura");

        // Establecemos el directorio donde ejecutar el proceso
        pb.directory(directorio);

        // Redirigimos la entrada estándar al archivo "entrada.txt"
        pb.redirectInput(archivoEntrada);

        // Redirigimos la salida estándar al archivo "salida.txt"
        pb.redirectOutput(archivoSalida);

        // Redirigimos la salida de error al archivo "error.txt"
        pb.redirectError(archivoSalida);

        // Se ejecuta el proceso
        Process p = pb.start();

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
