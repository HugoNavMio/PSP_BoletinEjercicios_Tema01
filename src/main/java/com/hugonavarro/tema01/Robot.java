package com.hugonavarro.tema01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Robot {
    static void main() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String linea;

            while ((linea = br.readLine()) != null) {
                String cadenaTransformadda = linea.toUpperCase();
                int caracteres = linea.length();
                IO.println(cadenaTransformadda + " (" + caracteres + " caracteres)");
            }
        } catch (IOException ioe) {
            IO.println("Error al leer la entrada: " + ioe.getMessage());
        }
    }
}
