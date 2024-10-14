package Act2_02;

import java.applet.Applet;

public class Hilo implements Runnable {

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
