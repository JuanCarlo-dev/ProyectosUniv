package Battleship;


public class Utilidades {


    //Convierte una letra (A-J) a su índice numérico (0-9).

    public static int letraAColumna(char letra) {
        letra = Character.toUpperCase(letra);
        if (letra >= 'A' && letra <= 'J') {
            return letra - 'A';
        }
        return -1;
    }


     // Convierte un índice de columna (0-9) a su letra correspondiente (A-J).

    public static char columnaALetra(int columna) {
        return (char) ('A' + columna);
    }


     //Valida si una coordenada está dentro de los límites del tablero.
    public static boolean coordenadaValida(int fila, int columna, int tamañoTablero) {
        return fila >= 0 && fila < tamañoTablero && columna >= 0 && columna < tamañoTablero;
    }


     //Limpia la consola simulando con saltos de línea (no borra realmente la pantalla).
    public static void limpiarConsola() {
        for (int i = 0; i < 30; i++) {
            System.out.println();
        }
    }

    //Valida si una orientación ingresada es válida (H o V).
    public static boolean orientacionValida(String orientacion) {
        return orientacion.equalsIgnoreCase("H") || orientacion.equalsIgnoreCase("V");
    }
}

