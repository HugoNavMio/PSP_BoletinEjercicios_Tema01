package com.hugonavarro.tema01;

public class Mascota {
    static void main(String[] args) {
        if (args.length != 2) {
            IO.println("El primer parámetro tiene que ser un nombre y el segundo parámetro tiene que ser el número de pasos.");
            System.exit(1);
        }

        String nombre = args[0];
        int pasos = 0;

        try {
            pasos = Integer.parseInt(args[1]);

            if (pasos < 0) {
                IO.println("El número de pasos no puede ser negativo.");
                System.exit(1);
            }
        } catch (NumberFormatException nfe) {
            IO.println("Error: el número de pasos debe ser un entero.");
            System.exit(1);
        }

        long pid = ProcessHandle.current().pid();

        for (int i = 1; i <= pasos; i++) {
            IO.println("PID: " + pid + " | Mascota: " + nombre + " | Paso: " + i);
        }
    }
}
