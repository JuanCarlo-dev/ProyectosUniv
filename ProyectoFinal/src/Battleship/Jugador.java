package Battleship;

import java.util.Scanner;

public class Jugador {
    private String nombre;
    private Tablero tableroPropio;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.tableroPropio = new Tablero(); // Tablero 10x10
    }

    public void colocarBarcos(Scanner scanner, int[] tamañosBarcos) {

        System.out.println("\n" + nombre + ", es tu turno de colocar barcos.");
        for (int tamaño : tamañosBarcos) {
            boolean colocado = false;
            while (!colocado) {
                tableroPropio.mostrarPropio();
                System.out.print("Coloca el barco de " + tamaño + " casillas (ej: A1 H o C2 V): ");
                String entrada = scanner.nextLine().trim().toUpperCase();

                if (entrada.length() < 4) {
                    System.out.println("Entrada inválida. Ejemplo correcto: B3 H");
                    continue;
                }

                char letraCol = entrada.charAt(0);
                int columna = Utilidades.letraAColumna(letraCol);

                int index = 1;
                while (index < entrada.length() && !Character.isDigit(entrada.charAt(index))) {
                    index++;
                }
                if (index >= entrada.length()) {
                    System.out.println("Fila inválida.");
                    continue;
                }

                int fin = entrada.indexOf(' ', index);
                if (fin == -1) fin = entrada.length() - 1;
                String filaStr = entrada.substring(index, fin).replaceAll("[^0-9]", "");
                if (filaStr.isEmpty()) {
                    System.out.println("Fila inválida.");
                    continue;
                }
                int fila = Integer.parseInt(filaStr) - 1;

                String orientacionStr = entrada.substring(entrada.length() - 1);
                if (!Utilidades.coordenadaValida(fila, columna, tableroPropio.getTamaño())) {
                    System.out.println("Coordenada fuera de rango.");
                    continue;
                }
                if (!Utilidades.orientacionValida(orientacionStr)) {
                    System.out.println("Orientación inválida. Usa H o V.");
                    continue;
                }
                Orientacion orientacion = orientacionStr.equalsIgnoreCase("H") ? Orientacion.HORIZONTAL : Orientacion.VERTICAL;
                Barco barco = new Barco(tamaño, orientacion);
                Coordenada inicio = new Coordenada(fila, columna);

                if (tableroPropio.colocarBarco(barco, inicio)) {
                    Registro.registrar(nombre + " colocó barco de " + tamaño + " en " + inicio + " " + orientacion);
                    colocado = true;
                } else {
                    System.out.println("No se puede colocar el barco aquí. Verifica que no se salga del tablero ni se sobreponga.");
                }
            }
        }
        System.out.println("¡Todos los barcos han sido colocados!\n");
        Utilidades.limpiarConsola();
    }


      //Permite al jugador atacar el tablero del oponente.
      //Muestra un mensaje si el ataque hunde un barco enemigo.

    public void atacar(Scanner scanner, Jugador oponente) {
        boolean ataqueValido = false;
        while (!ataqueValido) {
            System.out.println("Tu tablero:");
            this.tableroPropio.mostrarPropio();

            //  mostrar el tablero enemigo
            oponente.getTableroPropio().mostrarEnemigo();
            System.out.print(nombre + ", ingresa la coordenada a atacar (ej: D5): ");
            String entrada = scanner.nextLine().trim().toUpperCase();

            if (entrada.length() < 2) {
                System.out.println("Entrada inválida. Ejemplo: D5");
                continue;
            }
            char letraCol = entrada.charAt(0);
            int columna = Utilidades.letraAColumna(letraCol);

            int index = 1;
            while (index < entrada.length() && !Character.isDigit(entrada.charAt(index))) {
                index++;
            }
            if (index >= entrada.length()) {
                System.out.println("Fila inválida.");
                continue;
            }
            String filaStr = entrada.substring(index).replaceAll("[^0-9]", "");
            if (filaStr.isEmpty()) {
                System.out.println("Fila inválida.");
                continue;
            }
            int fila = Integer.parseInt(filaStr) - 1;

            if (!Utilidades.coordenadaValida(fila, columna, tableroPropio.getTamaño())) {
                System.out.println("Coordenada fuera de rango.");
                continue;
            }
            Coordenada objetivo = new Coordenada(fila, columna);
            char resultado = oponente.getTableroPropio().atacar(objetivo);

            if (resultado == '-') {
                System.out.println("Ya atacaste esa posición. Elige otra.");
                continue;
            } else if (resultado == 'X') {
                System.out.println("¡Impacto!");
                Registro.registrar(nombre + " atacó " + objetivo + ": IMPACTO");

                //  Verifica si se hundió un barco
                Barco barcoHundido = oponente.getTableroPropio().barcoHundidoEn(objetivo);
                if (barcoHundido != null) {
                    System.out.println("¡Has hundido un barco enemigo de " + barcoHundido.getTamaño() + " casillas!");
                    Registro.registrar(nombre + " hundió un barco de tamaño " + barcoHundido.getTamaño() + " en " + objetivo);
                }
            } else if (resultado == 'O') {
                System.out.println("¡Agua!");
                Registro.registrar(nombre + " atacó " + objetivo + ": AGUA");
            }
            ataqueValido = true;
        }
    }


     //Verifica si todos los barcos del jugador han sido hundidos.
    public boolean todosBarcosHundidos() {
        return tableroPropio.todosHundidos();
    }

    //getters
    public String getNombre() {
        return nombre;
    }

    public Tablero getTableroPropio() {
        return tableroPropio;
    }
}
