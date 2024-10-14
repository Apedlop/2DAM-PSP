package Act2_01;

public class Hilo extends Thread {

    String hilo;

    public Hilo(String hilo) {
        this.hilo = hilo;
    }

    public void run() {

        try {
            while (true) {
                Thread.sleep(1000);
                System.out.println(hilo);
            }
        }catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

    }

}
