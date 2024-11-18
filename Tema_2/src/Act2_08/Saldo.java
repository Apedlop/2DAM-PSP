package Act2_08;

import java.util.Random;

public class Saldo {
    private double saldo; // Atributo saldo

    // Constructor que asigna un valor inicial al saldo
    public Saldo(double saldoInicial) {
        this.saldo = saldoInicial;
    }

    // Método para obtener el saldo, con un sleep aleatorio
    public synchronized double obtenerSaldo() {
        try {
            Thread.sleep(new Random().nextInt(1000)); // Sleep aleatorio entre 0 y 1000 ms
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return saldo;
    }

    // Método para dar valor al saldo, con un sleep aleatorio
    public synchronized void restablecerSaldo(double nuevoSaldo) {
        try {
            Thread.sleep(new Random().nextInt(1000)); // Sleep aleatorio entre 0 y 1000 ms
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        this.saldo = nuevoSaldo;
    }

    // Método para añadir una cantidad al saldo, con sincronización y mostrando los cambios
    public synchronized void añadirCantidad(double cantidad, String quienAñade) {
        double saldoAntes = this.saldo;
        this.saldo += cantidad;
        double saldoDespues = this.saldo;
        System.out.println(quienAñade + " añade " + cantidad + " al saldo.");
        System.out.println("Saldo antes: " + saldoAntes + ", saldo después: " + saldoDespues);
    }
}

