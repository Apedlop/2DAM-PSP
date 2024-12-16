package Act3_11;

import java.io.*;
import java.net.Socket;

public class HiloServidor extends Thread {
    private Socket socket;
    private PrintWriter salida;

    public HiloServidor(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try (
                InputStream input = socket.getInputStream();
                BufferedReader entrada = new BufferedReader(new InputStreamReader(input))
        ) {
            // Configura la salida para el cliente
            salida = new PrintWriter(socket.getOutputStream(), true);
            ComunHilos.agregarCliente(salida); // Agrega cliente a la lista global

            // Mensaje de bienvenida
            salida.println("¡Bienvenido al chat! Escribe tus mensajes.");

            String mensaje;
            while ((mensaje = entrada.readLine()) != null) {
                System.out.println("Mensaje recibido: " + mensaje);
                ComunHilos.enviarATodos(mensaje); // Envía mensaje a todos los clientes
            }
        } catch (IOException e) {
            System.out.println("Cliente desconectado: " + e.getMessage());
        } finally {
            // Elimina el cliente cuando se desconecta
            ComunHilos.eliminarCliente(salida);
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
