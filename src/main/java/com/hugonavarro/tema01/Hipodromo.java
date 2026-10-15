package com.hugonavarro.tema01;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Hipodromo {
    static void main() {
        String[] nombres = {"Cody", "Nala", "Trufa"};
        int[] pasos = {5, 2, 9};

        List<Process> procesos = new ArrayList<>();

        String java = System.getProperty("java.home")
                + File.separator + "bin"
                + File.separator + "java";

        String classpath = System.getProperty("java.class.path");

        try {
            for (int i = 0; i < nombres.length; i++) {
                ProcessBuilder pb = new ProcessBuilder(
                        java,
                        "-cp",
                        classpath,
                        "com.hugonavarro.tema01.Mascota",
                        nombres[i],
                        String.valueOf(pasos[i])
                );

                pb.inheritIO();

                Process proceso = pb.start();
                procesos.add(proceso);

                IO.println("Lanzada la mascota " + nombres[i] + " con PID " + proceso.pid());
            }

            ExecutorService ejecutor = Executors.newFixedThreadPool(procesos.size());
            CompletionService<Resultado> finalizaciones = new ExecutorCompletionService<>(ejecutor);

            for (int i = 0; i < procesos.size(); i++) {
                final int indice = i;
                final Process proceso = procesos.get(i);
                final String nombre = nombres[i];

                Callable<Resultado> tarea = () -> {
                    int codigo = proceso.waitFor();

                    return new Resultado(
                            nombre,
                            proceso.pid(),
                            codigo,
                            indice
                    );
                };

                finalizaciones.submit(tarea);
            }

            for (int i = 1; i <= procesos.size(); i++) {
                Future<Resultado> futuro = finalizaciones.take();
                Resultado resultado = futuro.get();

                IO.println();
                IO.println("Llegada número " + i + ": " + resultado.nombre);
                IO.println("PID: " + resultado.pid);
                IO.println("Código de salida: " + resultado.codigoSalida);
            }

            ejecutor.shutdown();

            IO.println();
            IO.println("La carrera ha terminado.");
        } catch (IOException ioe) {
            IO.println("Error al iniciar un proceso: " + ioe.getMessage());
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
            IO.println("El hipódromo ha sido interrumpido.");
        } catch (java.util.concurrent.ExecutionException ee) {
            IO.println("Error al obtener el resultado: " + ee.getMessage());
        }
    }

    static class Resultado {
        String nombre;
        long pid;
        int codigoSalida;
        int indice;

        Resultado(
                String nombre,
                long pid,
                int codigoSalida,
                int indice
        ) {
            this.nombre = nombre;
            this.pid = pid;
            this.codigoSalida = codigoSalida;
            this.indice = indice;
        }
    }
}
