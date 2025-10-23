package es.etg.dam.psp;

public interface generadorInforme{
    void agregarSeccion(String titulo, String contenido);
    void guardarInforme(String nombreArchivo) throws Exception;
}
