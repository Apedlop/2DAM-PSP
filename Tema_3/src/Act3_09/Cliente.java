package Act3_09;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;
public class Cliente extends JFrame {
    private JTextField textoInput;
    private JTextArea textoOutput;
    private Socket socket;
    private DataOutputStream salida;
    private DataInputStream entrada;

    int puerto = 44444;
    String host = "localhost";

    public Cliente() {
        // Configuración de la ventana
        setTitle("Cliente");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel label = new JLabel("Escribe texto:");
        label.setBounds(20, 20, 100, 20);
        add(label);

        textoInput = new JTextField();
        textoInput.setBounds(120, 20, 200, 20);
        add(textoInput);

        JButton enviarBtn = new JButton("Enviar");
        enviarBtn.setBounds(50, 60, 80, 25);
        add(enviarBtn);

        JButton limpiarBtn = new JButton("Limpiar");
        limpiarBtn.setBounds(150, 60, 80, 25);
        add(limpiarBtn);

        JButton salirBtn = new JButton("Salir");
        salirBtn.setBounds(250, 60, 80, 25);
        add(salirBtn);

        textoOutput = new JTextArea();
        textoOutput.setBounds(20, 100, 340, 120);
        textoOutput.setEditable(false);
        add(new JScrollPane(textoOutput));

        try {
            socket = new Socket(host, puerto);
            salida = new DataOutputStream(socket.getOutputStream());
            entrada = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al conectar con el servidor: " + e.getMessage());
            System.exit(1);
        }

        enviarBtn.addActionListener(e -> {
            String texto = textoInput.getText();
            if (!texto.isEmpty()) {
                try {
                    salida.writeUTF(texto); // Envía el texto al servidor
                    String respuesta = entrada.readUTF(); // Lee la respuesta
                    textoOutput.append("Servidor: " + respuesta + "\n");
                } catch (IOException ex) {
                    textoOutput.append("Error al enviar el mensaje.\n");
                }
            }
        });

        limpiarBtn.addActionListener(e -> {
            textoInput.setText("");
            textoOutput.setText("");
        });

        salirBtn.addActionListener(e -> {
            try {
                salida.writeUTF("*"); // Envía * para cerrar la conexión
                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.exit(0);
        });
    }

    public static void main(String[] args) {
        Cliente app = new Cliente(); // Crea una instancia de la ventana
        app.setVisible(true); // Hace visible la ventana
    }
}
