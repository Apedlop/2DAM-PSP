package Act2_06;

import javax.swing.*;

public class Ejer_6_HiloContador extends Thread {

    private JProgressBar progressBar;
    private int contador;
    private int ms;

    public Ejer_6_HiloContador(int ms) {
        this.ms = ms;
    }

    public void run() {
        while (contador <= 100) {
            progressBar.setValue(contador);
            contador++;
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
