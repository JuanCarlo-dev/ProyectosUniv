package Battleship;

public class Coordenada {

    private int fila;
    private int columna;

    public Coordenada(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    //metodos para obtener o usar los atributos
    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    //metodos que modifican los atributos
    public void setFila(int fila) {
        this.fila = fila;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    //metodo que compara si dos coordenadas tienen la misma posicion
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Coordenada)) return false;
        Coordenada otra = (Coordenada) obj;
        return this.fila == otra.fila && this.columna == otra.columna;
    }

    //metodo que usa coordenadas en colecciones
    public int hashCode() {
        return 31 * fila + columna;
    }

    //metodo para mostrar la coordenada al jugaddr
    public String toString() {
        return "(" + fila + ", " + columna + ")";
    }

    //metodo que valida si la coordenada esta dentro del tablero
    public boolean esValida(int maxX, int maxY) {
            boolean filaValida = fila >= 0 && fila < maxX;
            boolean columnaValida = columna >= 0 && columna < maxY;
            return filaValida && columnaValida;
    }
}
