package Ejemplos;

import java.io.IOException;

public class Ejemplo1 {

    public static void main(String[] args) throws IOException {

        ProcessBuilder pb = new ProcessBuilder("ls");
        Process p = pb.start();

    }

}
