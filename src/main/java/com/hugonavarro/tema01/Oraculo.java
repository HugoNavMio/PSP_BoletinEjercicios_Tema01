package com.hugonavarro.tema01;

public class Oraculo {
    static void main(String[] args) {
        if (args.length < 2) {
            IO.println("Error: se debe indicar un nombre y una pregunta.");
            System.exit(1);
        }

        String nombre = args[0];
        String pregunta = args[1];

        String[] respuestas = {
                "Sí, sin ninguna duda.",
                "No cuentes con ello.",
                "Es posible que ocurra.",
                "El futuro es incierto.",
                "Las señales apuntan a que sí.",
                "Es mejor que esperes.",
                "Todo saldrá según lo previsto.",
                "No puedo asegurarlo."
        };

        int indice = pregunta.length() % respuestas.length;

        IO.println("----- ORÁCULO -----");
        IO.println("Consultante: " + nombre);
        IO.println("Pregunta: " + pregunta);
        IO.println("Respuesta: " + respuestas[indice]);

        System.exit(0);
    }
}
