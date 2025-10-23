package es.etg.dam.psp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class formatoMarkdown implements formatoInforme {

    private String prefijoTitulo = "## ";
    private String bloqueCodigoInicio = "```\n";
    private String bloqueCodigoFin = "\n```\n";
    private String saltoLinea = "\n";
    
    private String nombreArchivo = "informe_sistema.md";

    @Override
    public String generarSeccion(String titulo, String contenido) {
        return prefijoTitulo + titulo + saltoLinea + saltoLinea + bloqueCodigoInicio + contenido + bloqueCodigoFin + saltoLinea + saltoLinea;
    }
}