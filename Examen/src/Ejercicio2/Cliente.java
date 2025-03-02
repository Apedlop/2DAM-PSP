package Ejercicio2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) throws IOException {

        String host = "localhost";
        int port = 6000;

        Socket socket = new Socket(host, port);
        DataOutputStream flujoSalida = new DataOutputStream(socket.getOutputStream());
        DataInputStream flujoEntrada = new DataInputStream(socket.getInputStream());
        Scanner scanner = new Scanner(System.in);

    }

}
