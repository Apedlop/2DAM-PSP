package Act2_08;

public class Ejer_11_Arbitro {

    private int totalJugadores;      // Número total de jugadores
    private int turno;               // Turno actual (jugador que le toca)
    private int numeroAdivinar;      // Número a adivinar entre 1 y 10
    private boolean juegoAcabado;    // Indica si el juego ha acabado

    // Constructor que recibe el número total de jugadores
    public Ejer_11_Arbitro(int totalJugadores) {
        this.totalJugadores = totalJugadores;
        this.turno = 1;              // El primer jugador inicia el juego
        this.numeroAdivinar = (int) (Math.random() * 10) + 1; // Número aleatorio entre 1 y 10
        this.juegoAcabado = false;   // El juego no ha terminado al principio

        System.out.println("NÚEMRO A ADIVINAR: " + numeroAdivinar); // Para saber que número debe adibinar el jugador
    }

    // Método que devuelve el turno actual
    public int obtenerTurno() {
        return this.turno;
    }

    // Método que indica si el juego ha acabado
    public boolean esJuegoAcabado() {
        return this.juegoAcabado;
    }

    // Método que verifica la jugada del jugador
    // Recibe el identificador del jugador (1...n) y el número jugado
    public synchronized void jugar(int jugador, int numeroJugada) {
        if (this.juegoAcabado) {
            System.out.println("El juego ha terminado, no puedes jugar.");
            return;
        }

        // Si el jugador acierta el número, termina el juego
        if (numeroJugada == numeroAdivinar) {
            System.out.println("Jugador " + jugador + " gana, adivinó el número!!");
            this.juegoAcabado = true;
        } else {
            // Si no ha acertado, muestra el siguiente turno
            System.out.println("Le toca a: " + siguienteTurno());
        }
    }

    // Método para determinar el siguiente turno
    private int siguienteTurno() {
        this.turno = (this.turno % totalJugadores) + 1; // El turno pasa al siguiente jugador, de vuelta al primero si es necesario
        return this.turno;
    }

}
