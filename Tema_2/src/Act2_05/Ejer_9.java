package Act2_05;

import Act2_01.Hilo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ejer_9 extends JFrame implements ActionListener {

    Ejer_9_HiloContador hilo1, hilo2;
    private JButton comenzar, interrumpir1, interrumpir2, finalizar;
    private JLabel contador1, contador2, estado1, estado2;

    public Ejer_9() {
        setTitle("EJECUTAR E INTERRUMPIR HILOS");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); // Utilizat layout nulo para posicionar manualmente los elementos

        // Botón de "Comenzar Proceso" en la parte superior
        comenzar = new JButton("Comenzar Proceso");
        comenzar.setBounds(120, 20, 150, 30);
        comenzar.addActionListener(this);
        add(comenzar);

        // Botones de "Interrumpir"
        interrumpir1 = new JButton("Interrumpir");
        interrumpir1.setBounds(50, 110, 100, 30);
        interrumpir1.addActionListener(this);
        add(interrumpir1);

        interrumpir2 = new JButton("Interrumpir");
        interrumpir2.setBounds(250, 110, 100, 30);
        interrumpir2.addActionListener(this);
        add(interrumpir2);

        // Etiquetas de contadores
        contador1 = new JLabel("0", JLabel.CENTER);
        contador1.setFont(new Font("Arial", Font.BOLD, 20));
        contador1.setBounds(50, 150, 100, 30);
        add(contador1);

        contador2 = new JLabel("0", JLabel.CENTER);
        contador2.setFont(new Font("Arial", Font.BOLD, 20));
        contador2.setBounds(250, 150, 100, 30);
        add(contador2);

        // Etiquetas de estados para cada hilo
        estado1 = new JLabel("Hilo 1", JLabel.CENTER);
        estado1.setBounds(50, 180, 110, 30);
        add(estado1);

        estado2 = new JLabel("Hilo 2", JLabel.CENTER);
        estado2.setBounds(240, 180, 110, 30);
        add(estado2);

        // Botón "Finalizar Proceso" en la parte inferior
        finalizar = new JButton("Finalizar Proceso");
        finalizar.setBounds(120, 250, 150, 30);
        finalizar.addActionListener(this);
        add(finalizar);
    }

    public void actualizarContador(Ejer_9_HiloContador hilo) {
        if (hilo == hilo1) {
            contador1.setText(String.valueOf(hilo.getContador()));
        } else if (hilo == hilo2) {
            contador2.setText(String.valueOf(hilo.getContador()));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == comenzar) {
            hilo1 = new Ejer_9_HiloContador(300, this);
            hilo2 = new Ejer_9_HiloContador(1000, this);
            comenzar.setEnabled(false); // Una vez pulsado se desabilia el botón
            hilo1.start();
            hilo2.start();
            estado1.setText("Hilo 1 Corriendo");
            estado2.setText("Hilo 2 Corriendo");
        } else if (e.getSource() == interrumpir1) {
            hilo1.detenerHilo();
            estado1.setText("Hilo 1 Interrumpido");
        } else if (e.getSource() == interrumpir2) {
            hilo2.detenerHilo();
            estado2.setText("Hilo 2 Interrumpido");
        } else if (e.getSource() == finalizar) {
            hilo1.finalizar();
            hilo2.finalizar();
            finalizar.setEnabled(false);
            estado1.setText("Hilo 1 Finalizado");
            estado2.setText("Hilo 2 Finalizado");
        }
        repaint();
    }

    public static void main(String[] args) {
        Ejer_9 app = new Ejer_9();
        app.setVisible(true);
    }
}
