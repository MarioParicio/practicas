package practicaHangman;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


/**
 * Programa para jugar al ahorcado
 */
public class Hangman {
    // constante con el número máximo de intentos
    private static final int MAX_TRIES = 8;
    // lista de palabras para el juego
    private static final String[] WORDS = { "mouse", "monitor", "desktop", "laptop", "keyboard", "software", "hardware",
            "firmware", "network", "digit", "language", "development", "program", "computer", "connector", "database",
            "query", "constraint", "disk", "protocol", "debug", "breakpoint", "source", "branch", "device", "printer" };
    // cadena con las letras del alfabeto inglés
    private static final String LETTERS = "abcdefghijklmnopqrstuvwxyz";
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // abrir scanner
        Scanner sc = new Scanner(System.in);

        System.out.println("Juego del ahorcado");

        boolean exit = true;
        do {
            // elegir una palabra al azar
            String secret = chooseWord();

            // jugar
            playHangman(secret, sc);

            boolean answered = false;
            while (!answered) {
                // preguntar si seguir jugando
                System.out.println("Deseas volver a jugar([s]/n)");
                String s = sc.nextLine().toLowerCase();
                // verificar la respuesta
                if (s.length() == 0 || s.equals("s")) {
                    answered = true;
                    exit = false;
                } else if (s.equals("n")) {
                    answered = true;
                    exit = true;
                } else {
                    answered = false;
                }
            }

        } while (!exit); // jugar de nuevo mientras lo indique el usuario
        // cerrar scanner
        sc.close();
    }

    /**
     * Se elegirá una palabra al azar entre las presente en la lista
     * 
     * @return Cadena con la palabra elegida
     */
    private static String chooseWord() {
        // elegir una palabra al azar
        Random rnd = new Random();
        int index = rnd.nextInt(WORDS.length);
        return WORDS[index];
    }

    /**
     * Devuelve si la palabra secreta ha sido o no adivinada
     * 
     * @param secret  String con la palabra secreta
     * @param letters Lista de letras que ha intentado el usuario
     * @return true, si el usuario a encontrado todas las letras de la palabra
     *         secreta, false si no es así
     */
    private static boolean isWordDiscovered(String secret, ArrayList<Character> letters, int tries) {
        // TODO: completa el código de este método
        ArrayList<Character> aux = new ArrayList<Character>();
        for (int i = 0; i < secret.length(); i++) {
            aux.add(secret.charAt(i));
        }
        if (letters.containsAll(aux)) {
            return true;
        }
            return false;
    }

    /**
     * Obtiene una cadena con las letras descubiertas por el jugador y guiones bajos
     * (underscore) en las ocultas
     * 
     * @param secret  Palabra secreta a adivinar
     * @param letters Lista de letras que ha intentado el usuario
     * @return String con la palabra secreta en la que las letras descubiertas se
     *         muestran en su sitio y las letras no descubiertas se muestran con
     *         guines bajos seguidos de espacios para mejorar la lectura
     */
    private static String getSecretWord(String secret, ArrayList<Character> letters) {
        // TODO: completa el código de este método
        String aux = "";
        for (int i = 0; i < secret.length(); i++) {
            if (letters.contains(secret.charAt(i))) {
                aux +=  secret.charAt(i);
            } else {
                aux += "_";
            }
        }
        aux = aux.toUpperCase().charAt(0) + aux.substring(1, aux.length());
        return aux;
    }

    /**
     * Devuelve las letras que no han sido usadas por el jugador
     * 
     * @param letters Lista de las letras que han sido usadas por el jugador
     * @return String con las letras que no han sido utilizadas en las jugadas
     *         anteriores
     */
    private static String getNotGuessedLetters(ArrayList<Character> letters) {
        // TODO: completa el código de este método
        String aux = "";

            for(int j = 0; j < LETTERS.length(); j++) {
                if (!letters.contains(LETTERS.charAt(j))) {
                    aux += LETTERS.charAt(j);
                }
                }
        
        return aux;
    }

    /**
     * Inicia el juego interactivo
     * 
     * Al inicio el jugador debe saber cuántas letras tiene la palabra secreta: el
     * programa mostrará tantos guiones bajos (undercore) como letras tenga la
     * palabra
     * 
     * El jugador dispone de 8 intentos para encontrar la palabra secreta
     * 
     * El programa pedirá al usuario que indique una letra por jugada
     * 
     * El usuario recibirá información de si la letra está o no contenida en la
     * palabra secreta se mostrará la palabra con las letras descubiertas por el
     * usuario y con guiones las que no. Si la palabra no contiene la letra
     * introducida se restará uno al número de intentos disponibles. Las letras
     * presentes en la palabra no restan intentos
     * 
     * Tras cada ronda el programa debe mostrar las letras disponibles (no
     * utilizadas por el usuario todavía y las rondas disponibles)
     * 
     * El programa termina si el jugador descubre todas las letras o si consume los
     * 8 intentos
     * 
     * @param secret Palabra secreta que debe adivinar el jugador
     * @param sc Scanner abierto sobre la entrada del programa, no cerrar
     */
    private static void playHangman(String secret, Scanner sc) {
        // inicializar número de intentos
        int tries = MAX_TRIES;
        // lista para las letras introducidas por el usuario
        ArrayList<Character> letters = new ArrayList<>();
        // mostrar número de letras de la palabra
        System.out.println("La palabra secreta tiene " + secret.length() + " letras");

        // TODO: tu código aquí
        // mientras te queden intentos
        while(tries>0){
            // mostrar info: palabra con guiones, letras disponibles, intentos restantes
            System.out.println("La palabra secreta es: " + getSecretWord(secret, letters));
            System.out.println("Letras disponibles: " + getNotGuessedLetters(letters));
            System.out.println("Intentos restantes " + tries);
            // pedir letra
           System.out.println("Introduce una letra");
           String letter = sc.nextLine();
    
            // obtener la entrada del usuario convertida a minúsculas
            letter = letter.toLowerCase();
            // si no introduce nada volver a mostrar info y pedir letra
            if (letter.length() != 1) {
                continue;
            }
            // obtener letra introducida
            // comprobar si las letra ya ha sido introducida antes
            for (int i = 0; i < letters.size(); i++) {
                if (Character.compare(letter.charAt(0), letters.get(i)) == 0) {
            // si es así avisar al usuario 
                    System.out.println("letra ya introducida");
                    tries ++;
                    break;
                     }
                    }
            // si no: añadir la letra a la lista de letras introducidas, verificar si la letra está en la palabra 
                letters.add(letter.charAt(0));
                for(int y = 0; y < secret.length(); y++) {
                    if (secret.charAt(y) == letter.charAt(0)) {
                        System.out.println("La letra está en la palabra");
                        tries ++;
                        break;
                    } 
                }
            // si está: informar al jugador, verificar si la palabra se ha adivinado por completo, en ese caso salir ya que ha ganado el juego
            // Mostrar información de fin: tanto si ha ganado como si ha perdido
                if (isWordDiscovered(secret, letters, tries) == true) {
                    System.out.println("Has ganado");
                    break;
                    
                } else if (tries == 1) {
                    System.out.println("Has perdido");
                }
            // si no está: informar al jugador y restar un intento
            tries--;
            
        }
    

        


    }

}
