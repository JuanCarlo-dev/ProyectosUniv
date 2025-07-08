package Battleship;

import java.util.ArrayList;
import java.util.List;


public class Barco {
    private int tamaño; // Número de casillas que ocupa el barco
    private Orientacion orientacion; // Orientación del barco (HORIZONTAL o VERTICAL)
    private List<Coordenada> posiciones; // Coordenadas ocupadas por el barco
    private List<Coordenada> impactos;   // Coordenadas donde el barco ha sido impactado


    public Barco(int tamaño, Orientacion orientacion) {
        this.tamaño = tamaño;
        this.orientacion = orientacion;
        this.posiciones = new ArrayList<>();
        this.impactos = new ArrayList<>();
    }


     //Asigna las posiciones que ocupa el barco en el tablero, a partir de una coordenada inicial.
    public void setPosiciones(Coordenada inicio) {
        posiciones.clear();
        for (int i = 0; i < tamaño; i++) {
            int fila = inicio.getFila() + (orientacion == Orientacion.HORIZONTAL ? 0 : i);
            int columna = inicio.getColumna() + (orientacion == Orientacion.HORIZONTAL ? i : 0);
            posiciones.add(new Coordenada(fila, columna));
        }
    }


    //Verifica si el barco ocupa una coordenada dada.

    public boolean ocupa(Coordenada c) {
        return posiciones.contains(c);
    }

    //Registra un impacto en el barco si la coordenada corresponde a una parte del barco.
    public boolean recibirImpacto(Coordenada c) {
        if (ocupa(c) && !impactos.contains(c)) {
            impactos.add(c);
            return true;
        }
        return false;
    }

    //Verifica si el barco está completamente hundido.
    public boolean estaHundido() {
        return impactos.size() == tamaño;
    }

    //Devuelve las posiciones que ocupa el barco
    public List<Coordenada> getPosiciones() {
        return posiciones;
    }

    //Devuelve el tamaño del barco.
    public int getTamaño() {
        return tamaño;
    }

    //Devuelve la orientación del barco.
    public Orientacion getOrientacion() {
        return orientacion;
    }
}
