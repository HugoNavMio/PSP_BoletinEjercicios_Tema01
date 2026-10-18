package com.hugonavarro.tema01;

import java.io.IOException;
import java.util.Map;

public class ControlMision {
    static void main() {
        String planeta = "Saturno";
        String mensaje = "Rumbo a";

        try {
            ProcessBuilder pb = new ProcessBuilder(
                    "java",
                    "-cp",
                    System.getProperty("java.class.path"),
                    "com.hugonavarro.tema01.Starship",
                    mensaje
            );

            Map<String, String> entorno = pb.environment();

            entorno.put("PLANETA", planeta);

            IO.println("ControlMision: lanzando Starship...");
            IO.println("ControlMision: PLANETA = " + entorno.get("PLANETA"));

            pb.inheritIO();
            Process proceso = pb.start();
            int codigoSalida = proceso.waitFor();

            IO.println("ControlMision: Starship ha terminado.");
            IO.println("Código de salida: " + codigoSalida);
        } catch (IOException ioe) {
            IO.println("Error al iniciar Starship: " + ioe.getMessage());
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
            IO.println("La espera de Starship fue interrumpida.");
        }
    }
}
