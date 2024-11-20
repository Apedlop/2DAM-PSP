package Act2_10.PingPong;

public class Productor extends Thread {

    private Cola cola;
    private String[] mensajes = {"PING", "PONG"}; // Mensajes alternados

    public Productor(Cola cola) {
        this.cola = cola;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) { // Produce 50 alternancias
            String mensaje = mensajes[i % 2]; // Alterna entre "PING" y "PONG"
            cola.put(mensaje);
            try {
                sleep(100); // Simula tiempo de producción
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
