package Act_04;

import javax.net.ssl.*;
import java.io.*;
import java.security.KeyStore;

public class ServidorSSL {
    public static void main(String[] args) {
        int puerto = 6000; // Puerto seguro

        try {
            // Cargar el almacén de claves (KeyStore)
            KeyStore ks = KeyStore.getInstance("JKS");
            ks.load(new FileInputStream("src/Act_04/servidor_keystore.jks"), "password".toCharArray());

            // Inicializar el KeyManagerFactory con el KeyStore
            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
            kmf.init(ks, "password".toCharArray());

            // Crear el contexto SSL
            SSLContext contextoSSL = SSLContext.getInstance("TLS");
            contextoSSL.init(kmf.getKeyManagers(), null, null);

            // Crear el ServerSocket seguro
            SSLServerSocketFactory serverSocketFactory = contextoSSL.getServerSocketFactory();
            SSLServerSocket servidorSSL = (SSLServerSocket) serverSocketFactory.createServerSocket(puerto);

            System.out.println("Servidor SSL esperando conexiones...");

            try (SSLSocket clienteConectado = (SSLSocket) servidorSSL.accept()) {
                System.out.println("Cliente conectado.");

                DataInputStream flujoEntrada = new DataInputStream(clienteConectado.getInputStream());
                DataOutputStream flujoSalida = new DataOutputStream(clienteConectado.getOutputStream());

                // Recibir mensaje del cliente
                String mensajeCliente = flujoEntrada.readUTF();
                System.out.println("Mensaje recibido del cliente: \n\t" + mensajeCliente);

                // Enviar respuesta al cliente
                String saludo = "¡Hola, Cliente! (desde el servidor seguro)";
                flujoSalida.writeUTF(saludo);
                System.out.println("Enviando al cliente: \n\t" + saludo);

                // Recibir el mensaje en minúsculas del cliente
                String respuestaCliente = flujoEntrada.readUTF();
                System.out.println("Respuesta en minúsculas del cliente: \n\t" + respuestaCliente);

                // Cerrar flujos y socket
                flujoEntrada.close();
                flujoSalida.close();
            }
            servidorSSL.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
