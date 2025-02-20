package Ejercicio1;

public class Persona extends Thread {

    private int id;
    private Cuenta cuenta;

    public Persona(int id, Cuenta cuenta) {
        this.id = id;
        this.cuenta = cuenta;
    }

    @Override
    public void run() {
        try {
            int random = (int) (Math.random() * 500 + 1);
            Thread.sleep(1000);

            // Ingreso en la cuenta
            cuenta.deposito(random, id);

            // Reintegro en la cuenta
            cuenta.retiro(random, id);


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
