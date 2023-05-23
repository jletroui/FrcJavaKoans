package koans.english;

import static engine.Helpers.*;

public class AboutNot7Game {
    /**
     * Write a method 'die6()' returning the result of a 6-face die throw as an integer.
     * 
     * ---------   TIPS --------------
     * 
     * To generate a random number between 0 and 1, use the method random(). Ex:
     * 
     *     double someNumber = random(); // someNumber can be anything between 0 and 1
     * 
     * Try to think about what computations would be needed to go from a number between 0 and 1 to a number between 0 and 6.
     * 
     * Now, the result of the computation will be a decimal number, and you will need to round it to the next integer.
     * For example, if the result of your previous computation is 3.2, you will need to round it to 4. You can do that with:
     * 
     *     double rounded = Math.ceil(3.2) // rounded is 4.0
     * 
     * Finally, you will need to convert that decimal number to an int. You can do this conversion by specifying the type you want to convert into in parentheses. Ex:
     * 
     *     int roundedAsInt = (int)4.0; // from double to int
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * die6() should return an int between 1 and 6 randomly
     * 
     */
    public static int die6() {
        return (int)Math.ceil(6*random());
    }   
}
