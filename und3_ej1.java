//Import  the utility scanner
package und3;

import java.util.Scanner;

public class und3_ej1 {

    public static void main(String[] args) {

        //Opens a new Scanner
        Scanner sc = new Scanner(System.in);

        // Create the variables num in order to save the number inserted
         int num = 0;
        // Create the variable sumdigits to calculate the sum of the digits
        int sumdigits = 0;
        //Create the variable confirmation to store the number while we do de validation
        String confirmation = "";
        //A boolean to check if the user introduced a number
        boolean intergerOrNot2 = false;
 



        //Create the first while in order to restart the values, ask for a number and a execute a condition
        while (true) {
            num = 0;
            sumdigits = 0;


            // Ask for the number and check if it is a number
            System.out.println("Introduce un Numero Entero");
            confirmation = sc.nextLine();
            intergerOrNot2 = confirmation.matches("-?\\d+");


            // If the number isn't a number the program stops with a message
            if (intergerOrNot2 == false){
                System.out.println("Dato invalido, porfavor introduce un número");
                break;
            }
            // If the number is smaller than 0 the program stops with a message
            else if (Integer.parseInt(confirmation) < 0) {
                System.out.println("El dato introducido no es valido, programa finalizado");
                break;
            // If the condition above is false (the number is bigger than 0) execute the while and then do a println 
            } else {
                // Conver the string to a number and calulates the sum of the digits of the number
                num = Integer.parseInt(confirmation);
                while (num > 0) {
                    sumdigits = sumdigits + num % 10;
                    num = num / 10;
                }
                // Print the result (Sum of digits)
                System.out.println("La suma de las cifras es : " + sumdigits);
            }
        
        }
        //Close scanner
        sc.close();
    }

}
