package Act2_05;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ejer_9 extends JFrame implements ActionListener {

    HiloContador hilo1, hilo2;
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
        estado1.setBounds(50, 210, 100, 30);



    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
