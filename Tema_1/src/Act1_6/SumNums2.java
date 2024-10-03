package Act1_6;

import java.io.*;

public class SumNums2 {

    public static void main(String[] args) throws IOException {

        // Situamos dónde está el programa
//        File directorio = new File("C:\\Users\\pedre\\OneDrive\\Documentos\\2DAM-PSP\\Tema_1\\out\\production\\Tema_1"); // Windows
        File directorio = new File("/home/usuario/Documentos/2DAM-PSP/Tema_1/out/production/Tema_1");

        // Le pasamos el programa, el nombre de la clase
//        ProcessBuilder pb = new ProcessBuilder("C:\\Users\\pedre\\.jdks\\openjdk-23\\bin\\java.exe", "Act1_6.SumNums"); // Windows
        ProcessBuilder pb = new ProcessBuilder("java", "Act1_6.SumNums");

        // EStablecemos el directorio donde se encuentra el archivo
        pb.directory(directorio);

        // Ejecutamos el proceso
        Process p = pb.start();

        // Enviar números al proceso, simulando la entrada por teclado
        OutputStream os = p.getOutputStream();
        os.write("7\n".getBytes());
        os.write("8\n".getBytes());
        os.flush();

        // Obtener y leer la salida devuelta por el proceso
        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String linea;
        while ((linea = br.readLine()) != null) {
            System.out.print(linea + "\n");  // Mostrar la salida del proceso
        }
        os.close();

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
