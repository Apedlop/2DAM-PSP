package Act2_04;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ejer_8 extends JFrame implements ActionListener {
    Ejer_8_MyHilo hilo1, hilo2;
    private JButton comenzar, reanudar1, reanudar2, suspender1, suspender2, finalizar;
    private JLabel contadorLabel1, contadorLabel2, hiloLabel1, hiloLabel2, estadoLabel1, estadoLabel2;

    public Ejer_8() {
        setTitle("EJECUTAR, SUSPENDER Y REANUDAR HILOS");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); // Utilizar layout nulo para posicionar manualmente

        // Botón de "Comenzar Proceso" en la parte superior
        comenzar = new JButton("Comenzar Proceso");
        comenzar.setBounds(120, 20, 150, 30);
        comenzar.addActionListener(this);
        add(comenzar);

        // Botones de "Reanudar" y "Suspender" para cada hilo
        reanudar1 = new JButton("Reanudar");
        reanudar1.setBounds(50, 70, 100, 30);
        reanudar1.addActionListener(this);
        add(reanudar1);

        reanudar2 = new JButton("Reanudar");
        reanudar2.setBounds(250, 70, 100, 30);
        reanudar2.addActionListener(this);
        add(reanudar2);

        suspender1 = new JButton("Suspender");
        suspender1.setBounds(50, 110, 100, 30);
        suspender1.addActionListener(this);
        add(suspender1);

        suspender2 = new JButton("Suspender");
        suspender2.setBounds(250, 110, 100, 30);
        suspender2.addActionListener(this);
        add(suspender2);

        // Etiquetas de contadores y estados para cada hilo
        contadorLabel1 = new JLabel("0", JLabel.CENTER);
        contadorLabel1.setFont(new Font("Arial", Font.BOLD, 16));
        contadorLabel1.setBounds(50, 180, 100, 30);
        add(contadorLabel1);

        contadorLabel2 = new JLabel("0", JLabel.CENTER);
        contadorLabel2.setFont(new Font("Arial", Font.BOLD, 16));
        contadorLabel2.setBounds(250, 180, 100, 30);
        add(contadorLabel2);

        estadoLabel1 = new JLabel("Hilo1", JLabel.CENTER);
        estadoLabel1.setBounds(50, 210, 100, 30);
        add(estadoLabel1);

        estadoLabel2 = new JLabel("Hilo2", JLabel.CENTER);
        estadoLabel2.setBounds(240, 210, 110, 30);
        add(estadoLabel2);

        // Botón de "Finalizar Proceso" en la parte inferior
        finalizar = new JButton("Finalizar Proceso");
        finalizar.setBounds(120, 250, 150, 30);
        finalizar.addActionListener(this);
        add(finalizar);
    }

    public void actualizarContador(Ejer_8_MyHilo hilo) {
        if (hilo == hilo1) {
            contadorLabel1.setText(String.valueOf(hilo.getContador()));
        } else if (hilo == hilo2) {
            contadorLabel2.setText(String.valueOf(hilo.getContador()));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == comenzar) {
            hilo1 = new Ejer_8_MyHilo(this);
            hilo2 = new Ejer_8_MyHilo(this);
            comenzar.setEnabled(false);
            hilo1.start();
            hilo2.start();
            estadoLabel1.setText("Hilo 1 Corriendo");
            estadoLabel2.setText("Hilo 2 Corriendo");
        } else if (e.getSource() == reanudar1) {
            hilo1.reanuda();
            estadoLabel1.setText("Hilo 1 Corriendo");
        } else if (e.getSource() == reanudar2) {
            hilo2.reanuda();
            estadoLabel2.setText("Hilo 2 Corriendo");
        } else if (e.getSource() == suspender1) {
            hilo1.suspende();
            estadoLabel1.setText("Hilo 1 Suspendido");
        } else if (e.getSource() == suspender2) {
            hilo2.suspende();
            estadoLabel2.setText("Hilo 2 Suspendido");
        } else if (e.getSource() == finalizar) {
            hilo1.finalizar();
            hilo2.finalizar();
            finalizar.setEnabled(false);
            estadoLabel1.setText("Hilo 1 Finalizado");
            estadoLabel2.setText("Hilo 2 Finalizado");
        }
        repaint();
    }

    public static void main(String[] args) {
        Ejer_8 app = new Ejer_8();
        app.setVisible(true);
    }
}
