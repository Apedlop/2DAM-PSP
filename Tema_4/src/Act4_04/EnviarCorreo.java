package Act4_04;

import org.apache.commons.net.smtp.AuthenticatingSMTPClient;
import org.apache.commons.net.smtp.SMTPReply;
import org.apache.commons.net.smtp.SimpleSMTPHeader;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import java.io.IOException;
import java.io.Writer;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.spec.InvalidKeySpecException;

public class EnviarCorreo {

    public static void main(String[] args) throws NoSuchAlgorithmException, UnrecoverableKeyException, KeyStoreException, InvalidKeyException, InvalidKeySpecException {

        // Se crea cliente SMTP seguro
        AuthenticatingSMTPClient cliente = new AuthenticatingSMTPClient();

        // Datos del usuario y del servidor
        String server = "smtp.gmail.com";
        String userName = "correo@gmail.com";
        String password = "clavedelusuario";
        int puerto = 587;
        String remitente = "correo@gmail.com";

        try {
            int respuesta;

            // Creación de la clave para establecer un canal seguro
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            kmf.init(null, null);
            KeyManager km = kmf.getKeyManagers()[0];

            //  Nos conectamos al servidor SMTP
            cliente.connect(server, puerto);
            System.out.println("1 - " + cliente.getReplyString());
            cliente.setKeyManager(km); // Se establece la clave para la comunicación segura

            respuesta = cliente.getReplyCode();
            if (!SMTPReply.isPositiveCompletion(respuesta)) {
                cliente.disconnect();
                System.err.println("CONEXIÓN CERRADA");
                System.exit(1);
            }

            // Se envía el comando EHLO
            cliente.ehlo(server);
            System.out.println("2 - " + cliente.getReplyString());

            // NECESITA NEGOCIACIÓN TLS - MODO NO IMPLÍCITO
            // Se ejecuta el comando STARTTLS y se comprueba si el true
            if (cliente.execTLS()) {
                System.out.println("3 - " + cliente.getReplyString());

                // Se realiza la autenticaciṕn con el servidor
                if (cliente.auth(AuthenticatingSMTPClient.AUTH_METHOD.PLAIN, userName, password)) {
                    System.out.println("4 - " + cliente.getReplyString());
                    String destino1 = "mariajesusramos@brianda.net";
                    String asunto = "Prueba de SMTPCliente con GMAIL";
                    String mensaje = "Hola \nEnviando saludos \n Usando GMAIL \nChao";

                    // Se crea la cabecera
                    SimpleSMTPHeader cabecera = new SimpleSMTPHeader(remitente, destino1, asunto);

                    // El nombre de usuario y email de origen coinciden
                    cliente.setSender(remitente);
                    cliente.addRecipient(destino1);
                    System.out.println("5 - " + cliente.getReplyString());

                    // Se envía DATA
                    Writer writer = cliente.sendMessageData();
                    if (writer == null) {
                        System.out.println("FALLO AL ENVIAR DATA");
                        System.exit(1);
                    }

                    writer.write(cabecera.toString()); // cabecera
                    writer.write(mensaje); // mensaje
                    writer.close();
                    System.out.println("6 - " + cliente.getReplyString());

                    boolean exito = cliente.completePendingCommand();
                    System.out.println("7 - " + cliente.getReplyString());
                    if (!exito) {
                        System.out.println("FALLO AL FINALIZAR TRANSACCIÓN.");
                        System.exit(1);
                    } else {
                        System.out.println("MENSAJE ENVIADO CON ÉXITO...");
                    }
                } else {
                    System.out.println("USUARIO NO IDENTIFICADO.");
                }
            } else {
                System.out.println("FALLO AL EJECUTAR STARTTLS.");
            }
        } catch (IOException ex) {
            System.err.println("Could not connect to server");
            ex.printStackTrace();
            System.exit(1);
        }

        try {
            cliente.disconnect();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        System.out.println("Fin de envío.");
        System.exit(0);
    }

}
