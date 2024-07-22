package bonuses.french;

import java.util.List;

import engine.Locale;
import engine.Sensei;
import sensei.AboutInterfacesKoans;

public class AboutInterfaces {
    /**
     * # For loops
     * 
     * Write a method named 'displayNumbers' with an integer parameter 'n', which displays numbers between 1 and n.
     * Use a 'for' loop instead of a 'while' loop. Use the shortest form of incrementation / decrementation.
     * 
     * ---------   TIPS   --------------
     * 
     * To do things multiple times in Java, you already know the 'while' loop.
     * Most of the time, a 'while' loop has very similar code structure:
     * 
     *     [Initialize a counter];
     *     while ([Condition on the counter variable]) {
     *         // Do stuff repetedly
     *         [Modify the counter at the end of the loop];
     *     }
     * 
     * Ex:
     * 
     *     int times = 3; // Initialize a counter
     *     while (times > 0) { // Condition on the counter variable
     *         System.out.println("Still executing"); // Do stuff repetedly
     *         times = times -1; // Modify the counter at the end of the loop
     *     }
     * 
     * Since this pattern is used again and again, there is a shortcut in Java to make it terser. It's called a 'for' loop, where all 3 parts seen above are grouped on a single line:
     * 
     *     for([Initialize a counter]; [Condition on the counter variable]; [Modify the counter at the end of the loop]) {
     *         // do stuff repetedly
     *     }
     * 
     * Ex:
     *     
     *     for(int times = 3; times > 0; times = times -1) {
     *         System.out.println("Still executing"); // Do stuff repetedly
     *     }
     * 
     * There is even another shortcut: since incrementing or decrementing a number is something that happens very often, there is a short form:
     * 
     *     a = a - 2; // Long form
     *     a -= 2;    // Short form
     *     b = b + 2; // Long form
     *     b += 2;    // Short form
     * 
     * When we increment / decrement by one, there is an even shorter form:
     * 
     *     a -= 1; // Short form
     *     a--;    // Shortest form
     *     b += 1; // Short form
     *     b++;    // Shortest form
     * 
     * Using this, our for loop can become even terser:
     * 
     *     for(int times = 3; times > 0; times--) {
     *         System.out.println("Still executing"); // Do stuff repetedly
     *     }
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * displayNumbers(3) should display:
     * 
     * 1
     * 2
     * 3
     * 
     */


     public static void main(String[] args) {
        new Sensei(Locale.fr, List.of(AboutInterfacesKoans.koans)).offerKoans();
    }
}
