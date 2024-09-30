package Act1_4;

import java.io.IOException;

import java.util.Arrays;

public class LeerNombre {

    public static void main(String[] args) throws IOException {

        // Si no le pasamos
        if (args.length <= 0) {
            System.err.println("Se necesita un programa a ejecutar");
            System.exit(-1);
        }

        System.out.println("Nombre: " + args[0]);
        System.exit(0);

    }

}