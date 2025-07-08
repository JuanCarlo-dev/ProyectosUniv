package Battleship;

public class MainPrincipal {
    public static void main(String[] args) throws Exception {
        Registro.iniciarRegistro();
        Juego juego = new Juego();
        juego.iniciarJuego();
        Registro.cerrarRegistro();
    }
}

