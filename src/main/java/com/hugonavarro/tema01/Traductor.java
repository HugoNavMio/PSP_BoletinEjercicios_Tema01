package com.hugonavarro.tema01;

import java.io.*;

public class Traductor {
    static void main() {
        try {
            ProcessBuilder pb = new ProcessBuilder(
                    "java",
                    "-cp",
                    System.getProperty("java.class.path"),
                    "com.hugonavarro.tema01.Robot"
            );

            pb.redirectErrorStream(true);
            Process proceso = pb.start();

            PrintWriter escritor = new PrintWriter(new BufferedWriter(new OutputStreamWriter(proceso.getOutputStream())));
            BufferedReader lector = new BufferedReader(new InputStreamReader(proceso.getInputStream()));

            escritor.println("Hola a todos");
            escritor.println("Soy un robot amoroso");
            escritor.println("Os quiero mucho");
            escritor.close();

            String respuesta;

            while ((respuesta = lector.readLine()) != null) {
                IO.println("Robot responde: " + respuesta);
            }

            int codigoSalida = proceso.waitFor();

            IO.println("El robot ha terminado.");
            IO.println("Código de salida: " + codigoSalida);
        } catch (IOException ioe) {
            IO.println("Error de entrada/salida: " + ioe.getMessage());
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
            IO.println("La espera del robot ha sido interrumpida.");
        }
    }
}
