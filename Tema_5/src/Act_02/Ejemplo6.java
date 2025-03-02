/*
* Si cambiamos una letra del fichero .dat no sale este error:
* java.io.StreamCorruptedException: invalid stream header: EFBFBDEF
	at java.base/java.io.ObjectInputStream.readStreamHeader(ObjectInputStream.java:977)
	at java.base/java.io.ObjectInputStream.<init>(ObjectInputStream.java:404)
	at Act_02.Ejemplo6.main(Ejemplo6.java:13)
* Esto signfinica que .dat ha sido alterado y su contenido ya no coincide con el formato binario
* esperado por ObjectInputStream. Este flujo espera leer un encabezado específico generado por
* ObjectOutputStream, y al cambiar una letra (o cualquier byte), el encabezado se corrompe,
* provocando el error invalid stream header.
*
* A raíz de esto, he cambiado una palabra, una vez he cogido el texto del fichero bianrio. De esta manera las frases no
* coinciden, por lo que la salida será "Datos no válidos".
*/

package Act_02;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.security.MessageDigest;

public class Ejemplo6 {

    public static void main(String[] args) {

        try {
            // Leemos el archivo de datos.dat
            FileInputStream in = new FileInputStream("src/Act_02/datos.dat");
            ObjectInputStream ois = new ObjectInputStream(in);

            // Primera lectura del archivo
            Object o = ois.readObject();

            // Se obtiene la cadena de texto original del archivo
            String datos = (String) o;
            System.out.println("Datos originales: " + datos);

            // Modificación de la frase para que no coincida con el hash original
            // Por ejemplo, cambiamos la "M" de Mancha a "m"
            datos = datos.replace("Mancha", "mancha");
            System.out.println("Datos modificados: " + datos);

            // Segunda lectura del archivo para obtener el resumen del hash original
            o = ois.readObject();
            byte[] resumenOriginal = (byte[]) o;

            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Se calcula el resumen del texto modificado
            md.update(datos.getBytes()); // Texto modificado a resumir
            byte[] resumenActual = md.digest(); // Se calcula el resumen

            // Comprobamos si los dos resúmenes son iguales
            if (MessageDigest.isEqual(resumenOriginal, resumenActual)) {
                System.out.println("Datos válidos");
            } else {
                System.out.println("Datos no válidos");
            }

            // Cerramos los streams
            ois.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
