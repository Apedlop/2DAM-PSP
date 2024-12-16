package Act3_11;

import java.io.*;
import java.net.*;

public class ServidorChat {
    public static void main(String[] args) {
        System.out.println("Servidor de chat iniciado...");
        try (ServerSocket servidor = new ServerSocket(6000)) { // Puerto 12345
            while (true) {
                Socket socket = servidor.accept();
                System.out.println("Nuevo cliente conectado: " + socket.getInetAddress());
                new HiloServidor(socket).start(); // Inicia un hilo para el cliente
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}