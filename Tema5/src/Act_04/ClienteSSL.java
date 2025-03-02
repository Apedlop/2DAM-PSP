package Act_04;

import javax.net.ssl.*;
import java.io.IOException;
import java.security.KeyStore;

public class ClienteSSL {
    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 6000;

        try {
            // Cargar el truststore con el certificado del servidor
            KeyStore trustStore = KeyStore.getInstance("JKS");
            trustStore.load(new java.io.FileInputStream("C:/Users/pedre/OneDrive/Documentos/2DAM-PSP/Tema5/src/Act_04/clienteTruststore.jks"), "123456".toCharArray());

            // Inicializar el TrustManagerFactory con el truststore
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("SunX509");
            trustManagerFactory.init(trustStore);

            // Crear contexto SSL con los TrustManagers
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustManagerFactory.getTrustManagers(), null);

            // Crear socket de cliente seguro
            SSLSocketFactory socketFactory = sslContext.getSocketFactory();
            SSLSocket cliente = (SSLSocket) socketFactory.createSocket(host, puerto);

            System.out.println("Conectado al servidor SSL:");
            System.out.println("\tPuerto local del cliente: " + cliente.getLocalPort());
            System.out.println("\tPuerto remoto del servidor: " + cliente.getPort());
            System.out.println("\tDirección IP del servidor: " + cliente.getInetAddress().getHostAddress());
            System.out.println("\tNombre del host del servidor: " + cliente.getInetAddress().getHostName());

            cliente.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}