package Act2_01;

public class Ejer_1 extends Thread {

    String hilo;

    public Ejer_1(String hilo) {
        this.hilo = hilo;
    }

    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println(hilo);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

}
