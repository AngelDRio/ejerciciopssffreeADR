# EjercicioPsDfFree

Este programa en Java muestra cómo ejecutar tres procesos externos y guardar la información que devuelven en un archivo `.md` es decir, Markdown.

Los procesos externos a ejecutar son los comando en Linux `Ps`, `Df`, `Free`.

El proceso `Ps` muestra información sobre los procesos que se están ejecutando en el sistema.
El proceso `Df` muestra información sobre el uso del espacio en disco de los sistemas de archivos montados.
El proceso `Free` Muestra información sobre memoria RAM y swap del sistema.

Este programa funciona a través de dos interfaces:
- `formatoInforme`
  
  ```java
  package es.etg.dam.psp;
  
  public interface FormatoInforme {
    String generarSeccion(String titulo, String contenido);
    String getNombreArchivo();
  }
  ```
- `generadorInforme`
  
  ```java
  package es.etg.dam.psp;

  public interface GeneradorInforme{
    void agregarSeccion(String titulo, String contenido);
    void guardarInforme(String nombreArchivo) throws Exception;
  }
  ```
A través de estas interfaces se crean, mediante su implementación, las clases necesarias para generar el formato deseado. Una de ellas nos genera las reglas utilizadas en el tipo de formato que vamos a crear, en este caso Markdown.
- `FormatoMarkdown`

   ```java
  package es.etg.dam.psp;

  import lombok.AllArgsConstructor;
  import lombok.Data;
  import lombok.NoArgsConstructor;
  
  @AllArgsConstructor
  @NoArgsConstructor
  @Data
  public class FormatoMarkdown implements FormatoInforme {
  
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
    ```
Mientras que la otra toma el formato ya creado en la anterior e implementa la interfaz `generarInforme` para crear el archivo en sí.
- `generadorInformeEstrategia`

  ```java
  package es.etg.dam.psp;

  import java.io.FileWriter;
  import java.io.IOException;  
  
  import lombok.Data;
  import lombok.RequiredArgsConstructor;
  
  @RequiredArgsConstructor
  @Data
  
  public class GeneradorInformeEstrategia implements GeneradorInforme {
  
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
  ```
Para la llamada a los procesos se usa el comando `runTime` en una clase a parte que es llamada desde el `main`, donde se centraliza todo el programa.
Lo bueno de este proyecto es que no solo sirve para documentar en markdown, sino que debido a su estructura puede ser capaz de crear documentos en cualquier tipo de archivo. Lo único necesario para ello es una clase con las reglas propias del formato y llamarlo en el main, todo el resto del código es reutilizable.

Cabe destacar que el código no es perfecto y podría optimizarse mucho más si se pensase, lamentablememte debido a la falta de tiempo no se ha podido llegar a su máxima optimización.
  
Se puede acceder al proyecto en gitHub mediante el siguiente enlace:
[Enlace](https://github.com/AngelDRio/ejerciciopssffreeADR).
