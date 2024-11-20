package Act2_09;

public class Productor extends Thread {

    private Cola cola;
    private int n;

    public Productor(Cola cola, int n) {
        this.cola = cola;
        this.n = n;
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            cola.put(i); // Pone el número
            System.out.println(i + " => Productor: " + n + ", produce: " + i);
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            /* Si quitamos el sleep() el productor producirá más rápido de lo que el consumidor puede consumir */
        }
    }

}
