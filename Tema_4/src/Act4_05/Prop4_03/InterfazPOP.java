package Act4_05.Prop4_03;

import org.apache.commons.net.pop3.POP3Client;
import org.apache.commons.net.pop3.POP3MessageInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;

public class InterfazPOP extends JFrame {
    private JTextField txtServer, txtPort, txtUser;
    private JPasswordField txtPassword;
    private JRadioButton rbImplicit, rbNoImplicit;
    private JButton btnConnect, btnRetrieve;
    private JList<String> messageList;
    private DefaultListModel<String> listModel;
    private JTextArea txtMessage;
    private ButtonGroup group;
    private POP3Client pop3Client;
    private boolean conectado = false;

    public InterfazPOP() {
        setTitle("CLIENTE POP BÁSICO");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelTop = new JPanel(new GridLayout(2, 4));
        txtServer = new JTextField("localhost");
        txtPort = new JTextField("110");
        txtUser = new JTextField("");
        txtPassword = new JPasswordField("");
        rbImplicit = new JRadioButton("Modo implícito");
        rbNoImplicit = new JRadioButton("Modo no implícito", true);
        group = new ButtonGroup();
        group.add(rbImplicit);
        group.add(rbNoImplicit);

        panelTop.add(new JLabel("Servidor POP:"));
        panelTop.add(txtServer);
        panelTop.add(new JLabel("Puerto:"));
        panelTop.add(txtPort);
        panelTop.add(new JLabel("Usuario:"));
        panelTop.add(txtUser);
        panelTop.add(new JLabel("Clave:"));
        panelTop.add(txtPassword);

        JPanel panelOptions = new JPanel();
        panelOptions.add(rbImplicit);
        panelOptions.add(rbNoImplicit);
        panelOptions.add(btnConnect = new JButton("Conectar"));

        btnRetrieve = new JButton("Recuperar mensajes del servidor");
        btnRetrieve.setEnabled(false);
        btnRetrieve.setPreferredSize(new Dimension(300, 25));

        listModel = new DefaultListModel<>();
        messageList = new JList<>(listModel);
        JScrollPane scrollList = new JScrollPane(messageList);

        txtMessage = new JTextArea();
        txtMessage.setEditable(false);
        JScrollPane scrollMessage = new JScrollPane(txtMessage);

        JPanel panelMessages = new JPanel(new BorderLayout());
        panelMessages.add(btnRetrieve, BorderLayout.NORTH);
        panelMessages.add(scrollList, BorderLayout.CENTER);
        panelMessages.add(scrollMessage, BorderLayout.SOUTH);

        add(panelTop, BorderLayout.NORTH);
        add(panelOptions, BorderLayout.CENTER);
        add(panelMessages, BorderLayout.SOUTH);

        btnConnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manejarConexion();
            }
        });

        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                recuperarMensajes();
            }
        });

        messageList.addListSelectionListener(e -> mostrarMensaje());
    }

    private void manejarConexion() {
        if (!conectado) {
            String server = txtServer.getText();
            int port = Integer.parseInt(txtPort.getText());
            String user = txtUser.getText();
            String password = new String(txtPassword.getPassword());

            try {
                conectar(server, port, user, password);
                conectado = true;
                JOptionPane.showMessageDialog(this, "Conexión realizada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                btnConnect.setText("Desconectar");
                btnRetrieve.setEnabled(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al conectar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            try {
                desconectar();
                conectado = false;
                JOptionPane.showMessageDialog(this, "Desconexión realizada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                btnConnect.setText("Conectar");
                btnRetrieve.setEnabled(false);
                listModel.clear();
                txtMessage.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al desconectar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void conectar(String server, int port, String user, String password) throws IOException {
        pop3Client = new POP3Client();
        pop3Client.connect(server, port);
        if (!pop3Client.login(user, password)) {
            throw new IOException("Error de autenticación.");
        }
    }

    private void desconectar() throws IOException {
        if (pop3Client != null && pop3Client.isConnected()) {
            pop3Client.logout();
            pop3Client.disconnect();
        }
    }

    private void recuperarMensajes() {
        try {
            POP3MessageInfo[] mensajes = pop3Client.listMessages();
            if (mensajes == null) {
                JOptionPane.showMessageDialog(this, "No se pudieron recuperar los mensajes.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            listModel.clear();
            for (POP3MessageInfo mensaje : mensajes) {
                listModel.addElement("Mensaje " + mensaje.number + " (" + mensaje.size + " bytes)");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al recuperar mensajes: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarMensaje() {
        int selectedIndex = messageList.getSelectedIndex();
        if (selectedIndex != -1) {
            try {
                BufferedReader reader = (BufferedReader) pop3Client.retrieveMessage(selectedIndex + 1);
                if (reader == null) {
                    txtMessage.setText("No se pudo recuperar el mensaje.");
                    return;
                }
                StringBuilder mensaje = new StringBuilder();
                String linea;
                while ((linea = reader.readLine()) != null) {
                    mensaje.append(linea).append("\n");
                }
                reader.close();
                txtMessage.setText(mensaje.toString());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al leer mensaje: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InterfazPOP().setVisible(true));
    }
}
