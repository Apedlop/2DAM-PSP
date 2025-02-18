package Act_01;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.util.Scanner;

public class SHA_256 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("Introduce una cadena de texto: ");
            String texto1 = sc.nextLine();

            System.out.println("Introduce otra cadena de texto: ");
            String texto2 = sc.nextLine();

            System.out.println("Introduce la cadena de texto que actuará como clave: ");
            String clave = sc.next();

            // Concatenamos las cadenas con la clave
            String cadena1 = texto1 + clave;
            String cadena2 = texto2 + clave;

            // Inicializamos el algoritmo SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // TEXTO 1
            byte[] dataBytes1 = cadena1.getBytes(); // Texto a Bytes
            md.update(dataBytes1); // Se introduce texto en bytes a resumir
            byte[] resumen1 = md.digest(); // Se calcula el resumen

            System.out.println("===========TEXTO 1==========");
            System.out.println("Mensaje original: " + texto1);
            System.out.println("Mensaje resumen: " + new String(resumen1));
            System.out.println("Mensaje en Hexadecimal: " + Hexadecimal(resumen1));

            // TEXTO 2
            md.reset(); // Reiniciamos el MessageDigest
            byte[] dataBytes2 = cadena2.getBytes(); // Texto a Bytes
            md.update(dataBytes2); // Se introduce texto en bytes a resumir
            byte[] resumen2 = md.digest(); // Se calcula el resumen

            System.out.println("===========TEXTO 2==========");
            System.out.println("Mensaje original: " + texto2);
            System.out.println("Mensaje resumen: " + new String(resumen2));
            System.out.println("Mensaje en Hexadecimal: " + Hexadecimal(resumen2));

            // Información del proveedor
            Provider proveedor = md.getProvider();
            System.out.println("Proveedor: " + proveedor.getName());

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } finally {
            sc.close(); // Cerramos el Scanner
        }
    }

    // Convierte un array de bytes a hexadecimal
    static String Hexadecimal(byte[] resumen) {
        StringBuilder hex = new StringBuilder();
        for (int i = 0; i < resumen.length; i++) {
            String h = Integer.toHexString(resumen[i] & 0xFF);
            if (h.length() == 1) {
                hex.append("0");
            }
            hex.append(h);
        }
        return hex.toString().toUpperCase();
    }
}
