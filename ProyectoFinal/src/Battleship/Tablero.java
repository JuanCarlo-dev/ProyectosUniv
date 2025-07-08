package Battleship;

import java.util.ArrayList;
import java.util.List;


public class Tablero {
    private final int tamaño;
    private final char[][] matriz;
    private final List<Barco> barcos;


    public Tablero() {
        this.tamaño = 10;
        this.matriz = new char[tamaño][tamaño];
        this.barcos = new ArrayList<>();
        inicializarTablero();
    }


     //Inicializa el tablero con celdas vacías ('~').

    private void inicializarTablero() {
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                matriz[i][j] = '~';
            }
        }
    }


    //Intenta colocar un barco en el tablero
    public boolean colocarBarco(Barco barco, Coordenada inicio) {
        barco.setPosiciones(inicio);
        // Validar que todas las posiciones estén dentro del tablero y no se sobrepongan
        for (Coordenada c : barco.getPosiciones()) {
            if (!Utilidades.coordenadaValida(c.getFila(), c.getColumna(), tamaño) || hayBarcoEn(c)) {
                return false;
            }
        }
        barcos.add(barco);
        // Marcar el barco en la matriz
        for (Coordenada c : barco.getPosiciones()) {
            matriz[c.getFila()][c.getColumna()] = 'B';
        }
        return true;
    }


     //Verifica si ya hay un barco en la coordenada dada.

    public boolean hayBarcoEn(Coordenada c) {
        for (Barco b : barcos) {
            if (b.ocupa(c)) return true;
        }
        return false;
    }


     //Procesa un ataque en la coordenada dada.

    public char atacar(Coordenada c) {
        char celda = matriz[c.getFila()][c.getColumna()];
        if (celda == 'X' || celda == 'O') {
            return '-';
        }
        for (Barco b : barcos) {
            if (b.recibirImpacto(c)) {
                matriz[c.getFila()][c.getColumna()] = 'X';
                return 'X';
            }
        }
        matriz[c.getFila()][c.getColumna()] = 'O';
        return 'O';
    }


     //Muestra el tablero propio (incluye barcos y ataques).

    public void mostrarPropio() {
        System.out.print("  ");
        for (int col = 0; col < tamaño; col++) {
            System.out.print(Utilidades.columnaALetra(col) + " ");
        }
        System.out.println();
        for (int fila = 0; fila < tamaño; fila++) {
            System.out.print((fila + 1) + " ");
            for (int col = 0; col < tamaño; col++) {
                System.out.print(matriz[fila][col] + " ");
            }
            System.out.println();
        }
    }


     // Muestra el tablero enemigo (solo ataques: impactos y fallos, los barcos ocultos).

    public void mostrarEnemigo() {
        System.out.print("  ");
        for (int col = 0; col < tamaño; col++) {
            System.out.print(Utilidades.columnaALetra(col) + " ");
        }
        System.out.println();
        for (int fila = 0; fila < tamaño; fila++) {
            System.out.print((fila + 1) + " ");
            for (int col = 0; col < tamaño; col++) {
                char celda = matriz[fila][col];
                if (celda == 'X' || celda == 'O') {
                    System.out.print(celda + " ");
                } else {
                    System.out.print("~ ");
                }
            }
            System.out.println();
        }
    }


     //Verifica si todos los barcos han sido hundidos.

    public boolean todosHundidos() {
        for (Barco b : barcos) {
            if (!b.estaHundido()) {
                return false;
            }
        }
        return true;
    }


     //Devuelve el tamaño del tablero.
    public int getTamaño() {
        return tamaño;
    }


    public Barco barcoHundidoEn(Coordenada c) {
        for (Barco b : barcos) {
            if (b.ocupa(c) && b.estaHundido()) {
                return b;
            }
        }
        return null;
    }

}
