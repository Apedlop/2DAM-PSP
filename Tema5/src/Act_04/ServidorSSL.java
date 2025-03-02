package Act_04;

import javax.net.ssl.*;
import java.io.IOException;
import java.net.Socket;
import java.security.KeyStore;

public class ServidorSSL {
    public static void main(String[] args) {
        int puerto = 6000; // Puerto del servidor SSL

        try {
            // Cargar el keystore con la clave privada del servidor
            KeyStore keyStore = KeyStore.getInstance("JKS");
            keyStore.load(new java.io.FileInputStream("C:/Users/pedre/OneDrive/Documentos/2DAM-PSP/Tema5/src/Act_04/servidorKeystore.jks"), "123456".toCharArray());

            // Inicializar el KeyManagerFactory con el keystore
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
            keyManagerFactory.init(keyStore, "123456".toCharArray());

            // Crear contexto SSL con los KeyManagers
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(keyManagerFactory.getKeyManagers(), null, null);

            // Crear el socket de servidor seguro
            SSLServerSocketFactory socketFactory = sslContext.getServerSocketFactory();
            SSLServerSocket servidor = (SSLServerSocket) socketFactory.createServerSocket(puerto);

            System.out.println("Servidor SSL iniciado en el puerto " + puerto);

            // Espera la conexión de dos clientes
            for (int i = 1; i <= 2; i++) {
                System.out.println("Esperando conexión del cliente " + i + "...");
                SSLSocket cliente = (SSLSocket) servidor.accept();

                System.out.println("Cliente " + i + " conectado:");
                System.out.println("\tPuerto local del servidor: " + cliente.getLocalPort());
                System.out.println("\tPuerto remoto del cliente: " + cliente.getPort());
                System.out.println("\tDirección IP del cliente: " + cliente.getInetAddress().getHostAddress());
                System.out.println("\tNombre del host del cliente: " + cliente.getInetAddress().getHostName());

                cliente.close();
                System.out.println("Conexión con el cliente " + i + " cerrada.\n");
            }

            servidor.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}