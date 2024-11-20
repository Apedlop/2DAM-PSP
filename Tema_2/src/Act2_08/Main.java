package Act2_08;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Crear objeto Saldo con un valor inicial
        Saldo saldo = new Saldo(1000.0);

        // Mostrar saldo inicial
        System.out.println("Saldo inicial: " + saldo.obtenerSaldo());

        // Crear hilos que compartirán el objeto Saldo
        Hilo hilo1 = new Hilo(saldo, 200.0, "Hilo 1");
        Hilo hilo2 = new Hilo(saldo, 150.0, "Hilo 2");
        Hilo hilo3 = new Hilo(saldo, 300.0, "Hilo 3");

        // Lanzar los hilos
        hilo1.start();
        hilo2.start();
        hilo3.start();

        // Esperar a que todos los hilos terminen
        hilo1.join();
        hilo2.join();
        hilo3.join();

        // Mostrar saldo final
        System.out.println("Saldo final: " + saldo.obtenerSaldo());

        // Restablecer saldo a 1000
        saldo.restablecerSaldo(1000.0);
        System.out.println("Saldo final después de restablecer: " + saldo.obtenerSaldo());
    }
}
