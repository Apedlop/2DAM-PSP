package Act2_07.runnable;

public class CompartirHilos {

    public static void main(String[] args) {
        Contador cont = new Contador(0); // Inicializamos el contador en 0
        int iteraciones = 5000; // Total de iteraciones a realizar entre todos los hilos

        HiloA hiloA = new HiloA("HiloA", cont, iteraciones);
        HiloB hiloB = new HiloB("HiloB", cont, iteraciones);
        HiloC hiloC = new HiloC("HiloC", cont, iteraciones);
        HiloD hiloD = new HiloD("HiloD", cont, iteraciones);
        HiloE hiloE = new HiloE("HiloE", cont, iteraciones);

        hiloA.run();
        hiloB.run();
        hiloC.run();
        hiloD.run();
        hiloE.run();

        System.out.println("Resultado con sincronización: " + cont.valor());
    }
}
