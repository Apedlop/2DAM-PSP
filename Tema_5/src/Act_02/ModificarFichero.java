package Act_02;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;

public class ModificarFichero {

    public static void main(String[] args) {

        try {
            // Abrimos el archivo original para leerlo
            FileInputStream in = new FileInputStream("src/Act_02/datos.dat");
            ObjectInputStream ois = new ObjectInputStream(in);
            Object o = ois.readObject();

            // Leemos el String original
            String datos = (String) o;
            System.out.println("Datos originales: " + datos);

            // Modificamos el String (cambiamos una letra)
            String datosModificados = datos.replace("Mancha", "mancha"); // Cambiamos "Mancha" por "mancha"

            // Leemos el hash original
            o = ois.readObject();
            byte[] resumenOriginal = (byte[]) o;

            // Calculamos el nuevo hash para el texto modificado
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(datosModificados.getBytes()); // Calculamos el nuevo resumen
            byte[] resumenModificado = md.digest();

            // Cerramos los flujos de entrada
            ois.close();
            in.close();

            // Guardamos el contenido modificado en el archivo
            FileOutputStream out = new FileOutputStream("src/Act_02/datos.dat");
            ObjectOutputStream oos = new ObjectOutputStream(out);

            // Escribimos el nuevo String y su nuevo hash en el archivo
            oos.writeObject(datosModificados);
            oos.writeObject(resumenModificado);

            // Cerramos el flujo de salida
            oos.close();
            out.close();

            System.out.println("Fichero modificado y guardado correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
