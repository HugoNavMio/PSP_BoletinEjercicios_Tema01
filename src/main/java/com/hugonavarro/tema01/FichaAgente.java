package com.hugonavarro.tema01;

import java.time.Instant;

public class FichaAgente {
    static void main(String[] args) {
        String pid;
        try {
            pid = String.valueOf(ProcessHandle.current().pid());
        } catch (Exception e) {
            pid = "No disponible";
        }

        String pidPadre;
        try {
            pidPadre = ProcessHandle.current()
                    .parent()
                    .map(p -> String.valueOf(p.pid()))
                    .orElse("No disponible");
        } catch (Exception e) {
            pidPadre = "No disponible";
        }

        String usuario;
        try {
            usuario = System.getProperty("user.name", "No disponible");

            if (usuario == null || usuario.isBlank()) {
                usuario = "No disponible";
            }
        } catch (Exception e) {
            usuario = "No disponible";
        }

        String instanteInicio;
        try {
            instanteInicio = ProcessHandle.current()
                    .info()
                    .startInstant()
                    .map(Instant::toString)
                    .orElse("No disponible");
        } catch (Exception e) {
            instanteInicio = "No disponible";
        }

        String alias;
        if (args.length > 0 && !args[0].isBlank()) {
            alias = args[0];
        } else {
            alias = "No disponible";
        }

        IO.println("--- FICHA DEL AGENTE ---");
        IO.println("Alias: " + alias);
        IO.println("PID: " + pid);
        IO.println("PID del proceso padre: " + pidPadre);
        IO.println("Usuario: " + usuario);
        IO.println("Instante de inicio: " + instanteInicio);
        IO.println("-------------------------");
    }
}
