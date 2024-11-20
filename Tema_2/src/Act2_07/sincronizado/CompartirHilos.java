package Act2_07.sincronizado;

public class CompartirHilos {

    public static void main(String[] args) {
        Contador cont = new Contador(0); // Inicializamos el contador en 0
        int iteraciones = 5000; // Total de iteraciones a realizar entre todos los hilos

        HiloA hiloA = new HiloA("HiloA", cont, iteraciones);
        HiloB hiloB = new HiloB("HiloB", cont, iteraciones);
        HiloC hiloC = new HiloC("HiloC", cont, iteraciones);
        HiloD hiloD = new HiloD("HiloD", cont, iteraciones);
        HiloE hiloE = new HiloE("HiloE", cont, iteraciones);

        hiloA.start();
        hiloB.start();
        hiloC.start();
        hiloD.start();
        hiloE.start();

        try {
            /* Esperamos a que todos los hilos terminen su ejecución mediante el método join(), que asegura que el hilo principal (main)
            * no continúe hasta que cada uno de estos hilos haya finalizado. */
            hiloA.join(); // Espera a que hiloA termine
            hiloB.join(); // Espera a que hiloB termine
            hiloC.join(); // Espera a que hiloC termine
            hiloD.join(); // Espera a que hiloD termine
            hiloE.join(); // Espera a que hiloE termine
        } catch (InterruptedException e) {
            // Si el hilo actual es interrumpido mientras espera, se lanza una excepción.
            e.printStackTrace();
        }

        System.out.println("Resultado con sincronización: " + cont.valor());
    }
}
