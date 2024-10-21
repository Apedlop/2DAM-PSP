package Act2_03;

import java.applet.Applet;

public class HiloContador extends Thread {
    private long contador;
    private boolean parar;
    private Applet applet; // Referencia a la applet

    public HiloContador(long contadorInicial, Applet applet) {
        this.contador = contadorInicial;
        this.parar = false;
        this.applet = applet; // Guardar la referencia
    }

    public void detenerHilo() {
        parar = true; // Señal para detener el hilo
    }

    public void run() {
        while (!parar) {
            try {
                Thread.sleep(300); // Pausa de 300ms entre incrementos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            contador++; // Incrementar el contador
            applet.repaint(); // Solicitar un redibujo desde el hilo
        }
    }

    public long getContador() {
        return contador; // Obtener el valor actual del contador
    }
}
