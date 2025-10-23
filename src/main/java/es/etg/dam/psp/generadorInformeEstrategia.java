package es.etg.dam.psp;

import java.io.FileWriter;
import java.io.IOException;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data

public class generadorInformeEstrategia implements generadorInforme {

    private final StringBuilder informe = new StringBuilder();
    private final FormatoInforme formato;

    @Override
    public void agregarSeccion(String titulo, String contenido) {
        informe.append(formato.generarSeccion(titulo, contenido));
    }

    @Override
    public void guardarInforme(String nombreArchivo) throws IOException {
        try (FileWriter writer = new FileWriter(nombreArchivo)) {
            writer.write(informe.toString());
        }
    }
}