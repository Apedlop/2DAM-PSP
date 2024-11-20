package Act2_08;

public class Hilo extends Thread {
    private Saldo saldo;  // Objeto Saldo compartido entre los hilos
    private double cantidad;  // Cantidad que el hilo va a añadir
    private String nombreHilo; // Nombre del hilo

    // Constructor
    public Hilo(Saldo saldo, double cantidad, String nombreHilo) {
        this.saldo = saldo;
        this.cantidad = cantidad;
        this.nombreHilo = nombreHilo;
    }

    // Método que ejecuta el hilo
    @Override
    public void run() {
        saldo.añadirCantidad(cantidad, nombreHilo);  // Llama al método para añadir cantidad al saldo
    }
}

