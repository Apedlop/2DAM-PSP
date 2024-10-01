package Act1_6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class SumNums {

    public static void main(String[] args) {

        InputStreamReader in = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(in);
        int num1, num2;

        try {
            System.out.println("Introduce un número: ");
            num1 = Integer.parseInt(br.readLine());
            System.out.println("Introduce otro número: ");
            num2 = Integer.parseInt(br.readLine());
            System.out.println(num1 + " + " + num2 + " = " + (num1 + num2));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
