package Act3_09;

import java.io.*;
import java.net.*;

class HiloCliente extends Thread {
    private Socket socket;

    public HiloCliente(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
                DataInputStream entrada = new DataInputStream(socket.getInputStream());
                PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
        ) {
            String mensaje;

            while (true) {
                mensaje = entrada.readUTF(); // Recibe la cadena
                System.out.println("Mensaje recibido: " + mensaje);

                if (mensaje.equals("*")) {
                    System.out.println("Cliente desconectado.");
                    break;
                }

                // Envía el texto en mayúsculas
                String respuesta = mensaje.toUpperCase();
                salida.println(respuesta);
                System.out.println("Respuesta enviada: " + respuesta);
            }
        } catch (IOException e) {
            System.out.println("Error en la comunicación: " + e.getMessage());
        }
    }
}
