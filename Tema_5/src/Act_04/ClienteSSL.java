package Act_04;

import javax.net.ssl.*;
import java.io.*;

public class ClienteSSL {
    public static void main(String[] args) {
        String host = "localhost"; // Dirección del servidor
        int puerto = 6000; // Puerto seguro

        System.out.println("Iniciando Cliente SSL...");

        try {
            // Configurar el contexto SSL
            SSLContext contextoSSL = SSLContext.getInstance("TLS");
            contextoSSL.init(null, null, null);

            SSLSocketFactory socketFactory = contextoSSL.getSocketFactory();
            try (SSLSocket clienteSSL = (SSLSocket) socketFactory.createSocket(host, puerto)) {
                DataOutputStream flujoSalida = new DataOutputStream(clienteSSL.getOutputStream());
                DataInputStream flujoEntrada = new DataInputStream(clienteSSL.getInputStream());

                // Enviar mensaje al servidor
                String mensaje = "¡Hola, servidor seguro!";
                flujoSalida.writeUTF(mensaje);
                System.out.println("Enviando al servidor: \n\t" + mensaje);

                // Recibir respuesta del servidor
                String mensajeServidor = flujoEntrada.readUTF();
                System.out.println("Mensaje recibido del servidor: \n\t" + mensajeServidor);

                // Convertir a minúsculas y reenviar
                String mensajeEnMinusculas = mensajeServidor.toLowerCase();
                flujoSalida.writeUTF(mensajeEnMinusculas);
                System.out.println("Enviando mensaje en minúsculas al servidor: \n\t" + mensajeEnMinusculas);

                // Cerrar flujos
                flujoSalida.close();
                flujoEntrada.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
