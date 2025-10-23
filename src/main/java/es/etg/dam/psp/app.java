package es.etg.dam.psp;

public class app {

    public static final String[] TITULOS = {
        "Procesos en ejecución",
        "Uso del disco",
        "Uso de la memoria"
    };

    public static final String[] COMANDOS = {"ps", "df", "free"};
    public static final String MENSAJE_EXITO = "Informe generado: ";

    public static void main(String[] args) throws Exception {
        
        formatoInforme formato = new formatoMarkdown();
        generadorInforme informe = new generadorInformeEstrategia(formato);

        for (int i = 0; i < TITULOS.length; i++) {
            String ejecucion = comandoSistema.ejecutar(COMANDOS[i]);
            informe.agregarSeccion(TITULOS[i], ejecucion);
        }

        // Guardar usando el nombre que define el propio formato
        informe.guardarInforme(formato.getNombreArchivo());

        System.out.println(MENSAJE_EXITO + formato.getNombreArchivo());
    }
}