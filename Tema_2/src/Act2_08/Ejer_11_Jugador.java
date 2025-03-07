package Act2_08;


public class Ejer_11_Jugador extends Thread {

    private int idJugador;             // Identificador único del jugador
    private Ejer_11_Arbitro arbitro;   // Referencia al arbitro (JuegoAdivinanza)

    // Constructor que recibe el identificador del jugador y el arbitro
    public Ejer_11_Jugador(int idJugador, Ejer_11_Arbitro arbitro) {
        this.idJugador = idJugador;
        this.arbitro = arbitro;
    }

    @Override
    public void run() {
        // El jugador jugará hasta que el juego termine
        while (!arbitro.esJuegoAcabado()) {
            // Comprobar si es el turno del jugador
            if (arbitro.obtenerTurno() == idJugador) {
                // Si es el turno del jugador, genera un número aleatorio entre 1 y 10
                int numeroJugada = (int) (Math.random() * 10) + 1;
                System.out.println("Jugador " + idJugador + " dice: " + numeroJugada);

                // Hacer la jugada, usando el método synchronized de la clase arbitro
                arbitro.jugar(idJugador, numeroJugada);
            }

            // Esperar un poco antes de la siguiente jugada
            try {
                Thread.sleep(1500); // Simula el tiempo de espera entre turnos (puede ajustarse)
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

}
