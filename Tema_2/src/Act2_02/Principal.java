package Act2_02;

public class Principal {

    public static void main(String[] args) {

        Hilo hilo = new Hilo("TIC");
        Hilo hilo2 = new Hilo("TAC");

        new Thread(hilo).start();
        new Thread(hilo2).start();

    }

}
