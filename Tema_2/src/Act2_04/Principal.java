package Act2_04;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        MyHilo hilo = new MyHilo();
        Scanner scanner = new Scanner(System.in);
        boolean hiloIniciado = false;

        while (true) {
            System.out.print("Introduce una cadena (* para finalizar, D para suspender, R para reanudar): ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("*")) {
                hilo.finalizar();
                break;
            } else if (input.equalsIgnoreCase("D")) {
                hilo.suspende();
                System.out.println("Hilo suspendido.");
            } else if (input.equalsIgnoreCase("R")) {
                hilo.reanuda();
                System.out.println("Hilo reanudado.");
            }

            if (!hiloIniciado) {
                hilo.start();
                hiloIniciado = true;
            }
        }

        try {
            hilo.join(); // Espera a que el hilo termine
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Valor final del contador: " + hilo.getContador());
        scanner.close();
    }
}

