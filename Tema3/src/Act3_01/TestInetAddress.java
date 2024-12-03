package Act3_01;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestInetAddress {

    public static void main(String[] args) {

        InetAddress dir = null;

        System.out.println("=======================================================");
        System.out.println("SALIDA PARA LOCALHOST: ");

        try {
            // LOCALHOST
            dir = InetAddress.getByName("localhost");
            pruebaMetodos(dir);

            // URL www.google.es
            System.out.println("=======================================================");
            System.out.println("SALIDA PARA UNA URL");
            dir = InetAddress.getByName("www.google.com");
            pruebaMetodos(dir);

            // Array de tipo address con todas las direcciones IP asignadas a google.es
            System.out.println("\tDIRECCIONES IP PARA: " + dir.getHostName());
            InetAddress[] direcciones = InetAddress.getAllByName((dir.getHostName()));
            for (int i = 0; i < direcciones.length; i++) {
                System.out.println("\t\t" + direcciones[i].toString());
            }

            System.out.println("=======================================================");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private static void pruebaMetodos(InetAddress dir) {
        System.out.println("\tMetodo getByName(): " + dir);
        InetAddress dir2;

        try {
            dir2 = InetAddress.getLocalHost();
            System.out.println("\tMétodo getLocalHost(): " + dir2);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        // USAMOS MÉTODOS DE LA CLASE
        System.out.println("\tMétodo getHostName(): " + dir.getHostName());
        System.out.println("\tMétodo getHostAddress(): " + dir.getHostAddress());
        System.out.println("\tMétodo toString(): " + dir.toString());
        System.out.println("\tMétodo getCanoniucalHostName(): " + dir.getCanonicalHostName());

    }

}
