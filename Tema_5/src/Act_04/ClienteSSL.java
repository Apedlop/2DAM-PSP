/*
 Comandos antes de inciar el código, para crear certificados en un almacén distinto:
    - "C:\Program Files\Java\jdk-23\bin\keytool" -genkey -alias cliente -keyalg RSA -keystore AlmacenCli -storepass clavecli
    - "C:\Program Files\Java\jdk-23\bin\keytool" -exportcert -alias cliente -keystore AlmacenCli -storepass clavecli -file CertificadoCli.cer
    - "C:\Program Files\Java\jdk-23\bin\keytool" -importcert -trustcacerts -alias cercliente -file CertificadoCli.cer -keystore SrvCertConfianza -storepass cercli

 Para iniciar el código:
    - java -Djavax.net.ssl.keyStore=CliCertConfianza -Djavax.net.ssl.keyStorePassword=890123 ClienteSSL.java
*/

// EJERCICIO USADO ACT3_04
// El cliente debe mandarle un número al servidor, y que éste devuelva su cuadrado
package Act_04;

import javax.net.ssl.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Scanner;

public class ClienteSSL {

    public static void main(String[] args) throws IOException, CertificateException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        String host = "localhost";
        int puerto = 6000;

        System.out.println("Programa cliente iniciado...");

        // Cargar el almacén de certificados de confianza
        FileInputStream ficCerConf = new FileInputStream("CliCertConfianza");
        String claveCerConf = "890123";
        KeyStore almacenConf = KeyStore.getInstance(KeyStore.getDefaultType());
        almacenConf.load(ficCerConf, claveCerConf.toCharArray());

        // Crear el gestor de confianza
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(almacenConf);

        // Configurar el contexto SSL
        SSLContext contextoSSL = SSLContext.getInstance("TLS");
        contextoSSL.init(null, tmf.getTrustManagers(), null);

        // Crear el socket SSL de cliente
        SSLSocketFactory sfact = contextoSSL.getSocketFactory();
        SSLSocket cliente = (SSLSocket) sfact.createSocket(host, puerto);

        // Flujos de entrada y salida
        DataOutputStream flujoSalida = new DataOutputStream(cliente.getOutputStream());
        DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());
        Scanner scanner = new Scanner(System.in);

        // Pedir número al usuario
        System.out.print("Introduce un número: ");
        double numero = scanner.nextDouble();

        // Enviar número al servidor
        flujoSalida.writeDouble(numero);
        System.out.println("Número enviado al servidor: " + numero);

        // Recibir resultado del servidor
        double cuadrado = flujoEntrada.readDouble();
        System.out.println("El cuadrado del número recibido del servidor: " + cuadrado);

        // Cerrar streams y sockets
        flujoEntrada.close();
        flujoSalida.close();
        scanner.close();
        cliente.close();
    }

}