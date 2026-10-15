package com.hugonavarro.tema01;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class InvocarOraculo {
    static void main(String[] args) {
        if (args.length < 2) {
            IO.println("El primer parámetro tiene que ser un nombre y el segundo parámetro tiene que ser una pregunta.");
            System.exit(1);
        }

        String nombre = args[0];
        String pregunta = args[1];

        try {
            String java = System.getProperty("java.home")
                    + File.separator + "bin"
                    + File.separator + "java";

            String classpath = System.getProperty("java.class.path");

            List<String> comando = new ArrayList<>();

            comando.add(java);
            comando.add("-cp");
            comando.add(classpath);
            comando.add("com.hugonavarro.tema01.Oraculo");
            comando.add(nombre);
            comando.add(pregunta);

            ProcessBuilder pb = new ProcessBuilder(comando);

            pb.inheritIO();

            Process proceso = pb.start();

            int codigoSalida = proceso.waitFor();

            IO.println("Código de salida de \"Oraculo\": " + codigoSalida);

            if (codigoSalida != 0) {
                System.exit(codigoSalida);
            }
        } catch (Exception e) {
            IO.println("Error al ejecutar \"Oraculo\": " + e.getMessage());
            System.exit(1);
        }
    }
}
