package Act4_05;

import org.apache.commons.net.pop3.POP3Client;
import org.apache.commons.net.pop3.POP3MessageInfo;

import java.io.BufferedReader;
import java.io.IOException;

public class LocalPOP {
    public static void main(String[] args) {
        String server = "localhost", username = "usu1", password = "usuario";
        int puerto = 110;

        POP3Client pop3 = new POP3Client();
        try {
            // Nos conectamos al servidor
            pop3.connect(server, puerto);
            System.out.println("Conexión realizada al servidor POP3 " + server);
            System.out.println("Respuesta del servidor: " + pop3.getReplyString());


            // Iniciamos sesión
            if (!pop3.login(username, password)) {
                System.err.println("Error al hacer login");
                System.out.println("Respuesta del servidor: " + pop3.getReplyString());
            } else {
                // Obtenemos todos los mensajes en un array
                System.out.println("Respuesta del servidor: " + pop3.getReplyString());
                POP3MessageInfo[] men = pop3.listMessages();

                if (men == null) {
                    System.out.println("Imposible recuperar mensajes.");
                    System.out.println("Respuesta del servidor: " + pop3.getReplyString());
                } else {
                    System.out.println("N° de mensajes: " + men.length);
                    System.out.println("Respuesta del servidor: " + pop3.getReplyString());

                }
                System.out.println("\nMensaje:");
                Recuperarmensajes(men, pop3);

                System.out.println("\nCabecera:");
                Recuperarcabeceras(men, pop3);


                // Finalizar sesión
                pop3.logout();
                System.out.println("Respuesta del servidor: " + pop3.getReplyString());

            }

            // Nos desconectamos
            pop3.disconnect();
            System.out.println("Respuesta del servidor: " + pop3.getReplyString());


        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        System.exit(0);
    }

    private static void Recuperarmensajes(POP3MessageInfo[] men, POP3Client pop3) throws IOException {
        for (int i = 0; i < men.length; i++) {
            System.out.println("Mensaje: " + (i + 1));
            POP3MessageInfo msginfo = men[i]; // Lista de mensajes
            System.out.println("Identificador: " + msginfo.identifier +
                    ", Number: " + msginfo.number +
                    ", Tamaño: " + msginfo.size);

            System.out.println("Prueba de listUniqueIdentifier: ");
            POP3MessageInfo pmi = pop3.listUniqueIdentifier(i + 1); // Un mensaje
            System.out.println("\tIdentificador: " + pmi.identifier +
                    ", Number: " + pmi.number +
                    ", Tamaño: " + pmi.size);
            System.out.println("Respuesta del servidor: " + pop3.getReplyString());

        }
    }

    private static void Recuperarcabeceras(POP3MessageInfo[] men, POP3Client pop3) throws IOException {
        for (int i = 0; i < men.length; i++) {
            System.out.println("Mensaje: " + (i + 1));
            POP3MessageInfo msginfo = men[i];

            System.out.println("Cabecera del mensaje:");
            BufferedReader reader = (BufferedReader) pop3.retrieveMessageTop(msginfo.number, 0);
            System.out.println("Respuesta del servidor: " + pop3.getReplyString());

            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea.toString());
            }
            reader.close();
        }
    }

}