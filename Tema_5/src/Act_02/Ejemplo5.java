package Act_02;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;

public class Ejemplo5 {

    public static void main(String[] args) {

        try {
            FileOutputStream out = new FileOutputStream("src/Act_02/datos.dat");
            ObjectOutputStream oos = new ObjectOutputStream(out);

            // Crear un texto para almacenar en el archivo
            String datos = "En un lugar de la Mancha de cuyo nombre no quiero acordarme, no ha mucho tiempo que vivía un hidalgo de los de lanza en astillero, adarga antigua, rocín flaco y galgo corredor.";

            // Crear un objeto MessageDigest para SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            byte[] dataByte = datos.getBytes();

            // Calcular el hash (resumen) del texto
            md.update(dataByte); // Texto a resumir
            byte[] resumen = md.digest(); // Se calcula el resumen

            // Escribir el texto y su hash en el archivo
            oos.writeObject(datos);
            oos.writeObject(resumen);

            // Cerrar los streams
            oos.close();
            out.close();

            System.out.println("Archivo 'datos.dat' creado correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
