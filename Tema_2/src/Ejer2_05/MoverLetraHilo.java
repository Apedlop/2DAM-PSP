package Ejer2_05;

public class MoverLetraHilo extends Thread {

    private int x = 1; // Posición inicial en X
    private int y = 250; // Posición fija en Y
    private int velocidad = 5; // Velocidad de movimiento de la letra
    private boolean seMueveDerecha = true; // Indica si la letra se mueve a la derecha
    private boolean hiloActivo = false; // Indica si el hilo está corriendo
    private MoverLetra ventana; // Ventana donde se dibuja la letra

    public MoverLetraHilo(MoverLetra ventana) {
        this.ventana = ventana;
    }

    @Override
    public void run() {
        while (hiloActivo) {
            moverLetra(); // Mueve la letra
            ventana.repaint(); // Redibuja la ventana
            try {
                Thread.sleep(100); // Intervalo de actualización (20ms)
            } catch (InterruptedException e) {
                e.printStackTrace(); // Maneja la excepción si el hilo es interrumpido
            }
        }
    }

    // Mueve la letra y cambia de dirección si rebota
    private void moverLetra() {
        if (seMueveDerecha) { // Si se mueve a la derecha
            x += velocidad; // Aumenta la posición X
            if (x > ventana.getSize().width - 50) { // Si llega al borde derecho
                seMueveDerecha = false; // Cambia la dirección a izquierda
            }
        } else { // Si se mueve a la izquierda
            x -= velocidad; // Disminuye la posición X
            if (x < 1) { // Si llega al borde izquierdo
                seMueveDerecha = true; // Cambia la dirección a derecha
            }
        }
    }

    // Inicia el hilo de movimiento
    public void iniciar() {
        hiloActivo = true;
        this.start(); // Inicia el hilo
    }

    // Detiene el hilo de movimiento
    public void detener() {
        hiloActivo = false;
    }

    // Si el hilo está activo
    public boolean isHiloActivo() {
        return hiloActivo;
    }

    // Obtener el valor de X
    public int getX() {
        return x;
    }

    // Obtener el vallor de Y
    public int getY() {
        return y;
    }
}
