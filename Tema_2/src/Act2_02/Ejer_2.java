package Act2_02;

public class Ejer_2 implements Runnable {

    String hilo;

    public Ejer_2(String hilo) {
        this.hilo = hilo;
    }

    public void run() {
        try {
            // Si usamos el método sleep los hilos saldrán desordendos, sin embargo si no lo usamos los hilos sandrán ordenados.
            Thread.sleep( Thread.currentThread().getId() * 100);
            System.out.println("Hola Mundo " + hilo + " " + Thread.currentThread().getId());
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
