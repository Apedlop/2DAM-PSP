package Act_01;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;

public class MD5 {

    public static void main(String[] args) {

        MessageDigest md;

        try {
            md = MessageDigest.getInstance("MD5");
            String texto = "Esto es un texto plano.";

            byte[] dataBytes = texto.getBytes(); // Texto a Bytes
            md.update(dataBytes); // Se introduce texto en bytes a resumir
            byte[] resumen = md.digest(); // Se calcula el resumen

            System.out.println("Mensaje original: " + texto);
            System.out.println("Número de bytes: " + md.getDigestLength());
            System.out.println("Algoritmo: " + md.getAlgorithm());
            System.out.println("Mensaje resumen: " + new String(resumen));
            System.out.println("Mensaje en Hexadecimal: " + Hexadecimal(resumen));

            Provider proveedor = md.getProvider();
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
