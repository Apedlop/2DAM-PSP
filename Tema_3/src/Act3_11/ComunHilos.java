package Act3_11;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class ComunHilos {
    // Lista sincronizada para almacenar los flujos de salida de los clientes
    private static Set<PrintWriter> clientes = new HashSet<>();

    // Método para agregar un cliente a la lista
    public static synchronized void agregarCliente(PrintWriter cliente) {
        clientes.add(cliente);
    }

    // Método para eliminar un cliente de la lista
    public static synchronized void eliminarCliente(PrintWriter cliente) {
        clientes.remove(cliente);
    }

    // Método para enviar un mensaje a todos los clientes
    public static synchronized void enviarATodos(String mensaje) {
        for (PrintWriter cliente : clientes) {
            cliente.println(mensaje);
        }
    }
}
