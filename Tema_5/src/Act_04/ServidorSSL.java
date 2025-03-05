/*
 Antes de comanzar o inicar el código, debemos hacer los siguiente comandos en la terminal
 para crear un certificado para cada socket.

 Comando para crear el sertificado del servidor (usando keytoo):
    - "C:\Program Files\Java\jdk-23\bin\keytool" -genkey -alias servidor -keyalg RSA -keystore AlmacenSrv -storepass 1234567

 Comando para exportar el certificado del servidor, que está dentor del AlmacenSrv:
    - "C:\Program Files\Java\jdk-23\bin\keytool" -exportcert -alias servidor -keystore AlmacenSrv -storepass 1234567 -file CertificadoServ.cer

 Comando para generar el almacen que contendrá el mismo certificado que el servidor:
    - "C:\Program Files\Java\jdk-23\bin\keytool" -importcert -trustcacerts -alias servidor -file CertificadoServ.cer -keystore CliCertConfianza -storepass 890123

 Para iniciar el codigo:
    - java -Djavax.net.ssl.keyStore=AlmacenSrv -Djavax.net.ssl.keyStorePassword=1234567 ServidorSSL.java
*/

// EJERCICIO USADO ACT3_04
// El cliente debe mandarle un número al servidor, y que éste devuelva su cuadrado
package Act_04;

import javax.net.ssl.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.security.*;
import java.security.cert.CertificateException;

public class ServidorSSL {

    public static void main(String[] args) throws IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException, UnrecoverableKeyException, KeyManagementException {
        int puerto = 6000; // Puerto donde el servidor escuchará

        // Cargar el almacén de claves del servidor
        FileInputStream ficAlmacen = new FileInputStream("AlmacenSrv");
        String claveAlmacen = "1234567";
        KeyStore almacen = KeyStore.getInstance(KeyStore.getDefaultType());
        almacen.load(ficAlmacen, claveAlmacen.toCharArray());

        // Crear el gestor de claves
        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        kmf.init(almacen, claveAlmacen.toCharArray());

        // Configurar el contexto SSL
        SSLContext contextoSSL = SSLContext.getInstance("TLS");
        contextoSSL.init(kmf.getKeyManagers(), null, null);

        // Crear el socket de servidor SSL
        SSLServerSocketFactory sfact = contextoSSL.getServerSocketFactory();
        SSLServerSocket servidorSSL = (SSLServerSocket) sfact.createServerSocket(puerto);

        System.out.println("Esperando al cliente...");
        SSLSocket clienteConectado = (SSLSocket) servidorSSL.accept();

        // Flujos de entrada y salida
        DataInputStream flujoEntrada = new DataInputStream(clienteConectado.getInputStream());
        DataOutputStream flujoSalida = new DataOutputStream(clienteConectado.getOutputStream());

        // Recibir número del cliente
        double numeroCliente = flujoEntrada.readDouble();
        System.out.println("Número recibido del cliente: " + numeroCliente);

        // Calcular cuadrado del número
        double cuadrado = Math.pow(numeroCliente, 2);

        // Enviar resultado al cliente
        flujoSalida.writeDouble(cuadrado);
        System.out.println("Enviando al cliente el cuadrado del número: " + cuadrado);

        // Cerrar streams y sockets
        flujoEntrada.close();
        flujoSalida.close();
        clienteConectado.close();
        servidorSSL.close();
    }

}