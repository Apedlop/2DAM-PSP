package Act2_09;

public class Consumidor extends Thread {

    private Cola cola;
    private int n;

    public Consumidor(Cola cola, int n) {
        this.cola = cola;
        this.n = n;
    }

    @Override
    public void run() {
        int valor = 0;
        for (int i = 0; i < 5; i++) {
            valor = cola.get(); // Recoge el número
            System.out.println(i + " => Consumidor: " + n + ", consume: " + valor);
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            /* Si añadimos un sleep() hace que al productor le de tiempo a consumir los números antes de que el productor pueda seguir produciendo más. */
        }
    }
}
