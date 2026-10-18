package com.hugonavarro.tema01;

public class Starship {
    static void main(String[] args) {
        if (args.length < 1) {
            IO.println("Error: se debe indicar un mensaje como argumento.");
            System.exit(1);
        }

        String planeta = System.getenv("PLANETA");

        if (planeta == null || planeta.isEmpty()) {
            planeta = "Tierra";
        }

        String mensaje = args[0];
        IO.println("Starship: " + mensaje + " " + planeta);
        System.exit(0);
    }
}
