package es.etg.dam.psp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class comandoSistema {

    public static final String SALTO_LINEA = "\n";

    public static String ejecutar(String comando) throws Exception {
        Process proceso = Runtime.getRuntime().exec(comando);
        BufferedReader lector = new BufferedReader(new InputStreamReader(proceso.getInputStream()));

        StringBuilder salida = new StringBuilder();
        String linea;
        while ((linea = lector.readLine()) != null) {
            salida.append(linea).append(SALTO_LINEA);
        }
        return salida.toString();
    }
}
