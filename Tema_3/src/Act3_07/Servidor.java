package Act3_07;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        int puerto = 6000;
        ServerSocket servidor = new ServerSocket(puerto);

        System.out.println("Esperando al Cliente...");
        Socket cliente = servidor.accept();

        // Preparar el flujo de salida para los objetos
        ObjectOutputStream salida = new ObjectOutputStream(cliente.getOutputStream());

        // Preparar el objeto que se enviará
        Numeros num = new Numeros(7, 2, 3);
        salida.writeObject(num);
        System.out.println("Envío: " + num.getNumero());

        // Obtener stream para leer objetos
        ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
        Numeros dato = (Numeros) entrada.readObject();
        System.out.println("Recibido: " + dato.getNumero());

        // Cerrar streams y sockerts
        salida.close();
        entrada.close();
        cliente.close();
        servidor.close();
    }

}
