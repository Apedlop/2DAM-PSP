/*
* No funciona porque no lo he terminado
* */

package Ejercicio2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) {
        int puerto = 6000;

        try {
            ServerSocket serverSocket = new ServerSocket(puerto);
            System.out.println("Servidor iniciando...");

            try {
                // Llamamos al cliente y esperamos su conexción
                Socket cliente = serverSocket.accept();
                DataInputStream in = new DataInputStream(cliente.getInputStream());
                DataOutputStream out = new DataOutputStream(cliente.getOutputStream());

                System.out.println("Cliente conectado");

                while (true) {
                    Asignatura asignaturas[] =  {
                         new Asignatura(1, "ACD"),
                         new Asignatura(2, "PSP"),
                         new Asignatura(3, "PMDM")
                    };
                    Especialidad especialidad = new Especialidad(1, "Bases de datos");
                    Especialidad especialidad2 = new Especialidad(2, "Programación");
                    Profesor profesor = new Profesor(1, "Profesor1", asignaturas, especialidad);



                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
