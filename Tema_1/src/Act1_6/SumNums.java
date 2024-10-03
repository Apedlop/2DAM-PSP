package Act1_6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class SumNums {

    public static void main(String[] args) {
        InputStreamReader in = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(in);
        int num1 = 0, num2 = 0;

        // Validar el primer número
        num1 = leerNumero(br, "Introduce un número: ");
        System.out.println(num1);

        // Validar el segundo número
        num2 = leerNumero(br, "Introduce otro número: ");
        System.out.println(num2);

        // Mostrar la suma
        System.out.println("Solución: " + num1 + " + " + num2 + " = " + (num1 + num2));
    }

    // Método para leer un número y asegurarse de que es un entero
    public static int leerNumero(BufferedReader br, String mensaje) {
        int numero = 0;
        boolean entradaValida = false;

        while (!entradaValida) {
            try {
                System.out.print(mensaje); // Mostrar el mensaje
                numero = Integer.parseInt(br.readLine()); // Intentar convertir a entero
                entradaValida = true; // Si tiene éxito, marca la entrada como válida
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Debes introducir un número entero."); // Mensaje de error
            } catch (IOException e) {
                e.printStackTrace(); // Manejo de excepciones de entrada/salida
            }
        }
        return numero; // Devuelve el número válido
    }

}
