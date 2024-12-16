package Act3_11;

import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClienteChat extends JFrame {
    private JTextField entradaTexto; // Campo de texto para enviar mensajes
    private JTextArea chatArea; // Área para mostrar mensajes
    private PrintWriter salida; // Para enviar mensajes al servidor
    private BufferedReader entrada; // Para recibir mensajes del servidor
    private Socket socket;

    public ClienteChat() {
        // Configuración de la ventana principal
        setTitle("Cliente Chat");
        setSize(500, 500);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar ventana

        // Área de chat (solo lectura)
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setFont(new Font("Arial", Font.PLAIN, 14));
        add(new JScrollPane(chatArea), BorderLayout.CENTER);

        // Campo de entrada de texto
        entradaTexto = new JTextField();
        entradaTexto.setFont(new Font("Arial", Font.PLAIN, 14));
        add(entradaTexto, BorderLayout.SOUTH);

        // Conectar con el servidor
        conectarAlServidor();

        // Acción para enviar mensajes
        entradaTexto.addActionListener(e -> {
            String mensaje = entradaTexto.getText().trim();
            if (!mensaje.isEmpty()) {
                salida.println(mensaje); // Envía el mensaje al servidor
                entradaTexto.setText(""); // Limpia el campo de entrada
            }
        });
    }

    private void conectarAlServidor() {
        try {
            socket = new Socket("localhost", 6000); // Conectar al servidor en el puerto 6000
            salida = new PrintWriter(socket.getOutputStream(), true);
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Hilo para recibir mensajes del servidor
            new Thread(() -> {
                try {
                    String mensaje;
                    while ((mensaje = entrada.readLine()) != null) {
                        chatArea.append(mensaje + "\n");
                    }
                } catch (IOException e) {
                    mostrarError("Conexión cerrada por el servidor.");
                } finally {
                    cerrarRecursos();
                }
            }).start();

        } catch (IOException e) {
            mostrarError("No se pudo conectar al servidor. Verifica que el servidor esté activo.");
            System.exit(1);
        }
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void cerrarRecursos() {
        try {
            if (salida != null) salida.close();
            if (entrada != null) entrada.close();
            if (socket != null) socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ClienteChat cliente = new ClienteChat();
            cliente.setVisible(true);
        });
    }
}
