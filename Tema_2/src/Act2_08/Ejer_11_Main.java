package Act2_08;

public class Ejer_11_Main {

    public static void main(String[] args) {

        // Crear el juego con 3 jugadores
        Ejer_11_Arbitro juego = new Ejer_11_Arbitro(3);

        // Crear los hilos para los jugadores
        Ejer_11_Jugador jugador1 = new Ejer_11_Jugador(1, juego);
        Ejer_11_Jugador jugador2 = new Ejer_11_Jugador(2, juego);
        Ejer_11_Jugador jugador3 = new Ejer_11_Jugador(3, juego);

        // Iniciar los hilos de los jugadores
        jugador1.start();
        jugador2.start();
        jugador3.start();

        try {
            // Esperar a que todos los hilos terminen
            jugador1.join();
            jugador2.join();
            jugador3.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Después de que los jugadores terminen, mostrar el estado final
        if (juego.esJuegoAcabado()) {
            System.out.println("¡El juego ha terminado!");
        }
    }

}
