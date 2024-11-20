package Act2_07;

public class HiloB extends Thread {

    private Contador contador;

    public HiloB(String n, Contador contador) {
        setName(n);
        this.contador = contador;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5000; i++) {
            contador.incrementar();
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(getName() + " contador vale " + contador.valor());
    }
}
