package Battleship;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Registro {
    private static PrintWriter writer;
    private static final String NOMBRE_ARCHIVO = "registro_batalla_naval.txt";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    
     //Inicializa el sistema de registro, abriendo el archivo para escritura.
    
    public static void iniciarRegistro() throws IOException {
        writer = new PrintWriter(new FileWriter(NOMBRE_ARCHIVO, false));
        registrar("--- INICIO DEL JUEGO DE BATALLA NAVAL ---");
    }


    //Registra un mensaje en el archivo de texto, añadiendo una marca de tiempo Y EL MENSAJE.

    public static void registrar(String mensaje) {
        if (writer != null) {
            String timestamp = LocalDateTime.now().format(FORMATTER);
            writer.println("[" + timestamp + "] " + mensaje);
            writer.flush();
        }
    }

    
      //Cierra el archivo de registro.
    public static void cerrarRegistro() {
        if (writer != null) {
            registrar("--- FIN DEL JUEGO DE BATALLA NAVAL ---");
            writer.close();
            writer = null;
        }
    }
}
