package Battleship;

import java.util.Scanner;

public class Juego {
    private Jugador jugador1;
    private Jugador jugador2;
    private Scanner scanner;

    private final int[] tamañosBarcos = {2, 3, 3, 4, 5};


    public Juego() {
        scanner = new Scanner(System.in);
        System.out.println("========================================= ");
        System.out.println("        BATTLESHIP - 2 JUGADORES          ");
        System.out.println("========================================\n");
        System.out.print("Jugador 1, ingresa tu nombre: ");
        String nombre1 = scanner.nextLine().trim();
        System.out.print("Jugador 2, ingresa tu nombre: ");
        String nombre2 = scanner.nextLine().trim();
        jugador1 = new Jugador(nombre1.isEmpty() ? "Jugador 1" : nombre1);
        jugador2 = new Jugador(nombre2.isEmpty() ? "Jugador 2" : nombre2);
    }


     //Inicia el juego: colocación de barcos, turnos alternados y fin de partida.
    public void iniciarJuego() {
        System.out.println("\n" + jugador1.getNombre() + ": Presiona ENTER para comenzar.");
        scanner.nextLine();

        // Colocación de barcos
        jugador1.colocarBarcos(scanner, tamañosBarcos);
        Utilidades.limpiarConsola();
        jugador2.colocarBarcos(scanner, tamañosBarcos);
        Utilidades.limpiarConsola();

        boolean juegoTerminado = false;
        Jugador actual = jugador1;
        Jugador oponente = jugador2;

        // Bucle principal de turnos
        while (!juegoTerminado) {
            System.out.println("Turno de " + actual.getNombre());
            actual.atacar(scanner, oponente);

            // Verificar si el juego termina
            if (oponente.todosBarcosHundidos()) {
                System.out.println("¡" + actual.getNombre() + " ha ganado la partida!");
                Registro.registrar("FIN DEL JUEGO: Ganador -> " + actual.getNombre());
                juegoTerminado = true;
            } else {
                // Alternar turnos
                Jugador temp = actual;
                actual = oponente;
                oponente = temp;
                System.out.println("Presiona ENTER para pasar el turno...");
                scanner.nextLine();
                Utilidades.limpiarConsola();
            }
        }
        System.out.println("Gracias por jugar Batalla Naval.");
    }
}

