package Act_01;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.util.Scanner;

public class SHA_256 {

    MessageDigest md;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);


        try {
            System.out.println("Introduce una cadena de texto: ");
            String texto1 = sc.nextLine();

            System.out.println("Introduce otra cadena de texto: ");
            String texto2 = sc.nextLine();

            System.out.println("Introduce la cadena de texto que actuará como clave: ");
            String clave = sc.next();

            String cadena1 = texto1 + clave;
            String cadena2 = texto2 + clave;

            // TEXTO 1
            byte[] dataBytes = texto1.getBytes(); // Texto a Bytes
            md.update(dataBytes); // Se introduce texto en bytes a resumir
            byte[] resumen = md.digest(); // Se calcula el resumen

            System.out.println("===========TEXTO 1==========");
            System.out.println("Mensaje original: " + texto1);
            System.out.println("Mensaje resumen: " + new String(resumen));
            System.out.println("Mensaje en Hexadecimal: " + Hexadecimal(resumen));

            // TEXTO 2
            

            System.out.println("===========TEXTO 1==========");
            System.out.println("Mensaje original: " + texto2);
            System.out.println("Mensaje resumen: " + new String(resumen2));
            System.out.println("Mensaje en Hexadecimal: " + Hexadecimal(resumen2));

            Provider proveedor = md.getProvider();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }

    // Convierte la cadena de texto a byte
    public void bytes(String texto) {
        try {
            md = MessageDigest.getInstance("SHA-256"); // Algoritmo que se va a usar
            byte[] dataBytes2 = texto.getBytes(); // Texto a Bytes
            md.update(dataBytes2); // Se introduce texto en bytes a resumir
            byte[] resumen = md.digest(); // Se calcula el resumen
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    // Convierte un array de bytes a hexadecimal
    static String Hexadecimal(byte[] resumen) {
        String hex = "";
        for (int i = 0; i < resumen.length; i++) {
            String h = Integer.toHexString(resumen[i] & 0xFF);
            if (h.length() == 1) {
                hex += "0";
            }
            hex += h;
        }
        return hex.toUpperCase();
    }

}
