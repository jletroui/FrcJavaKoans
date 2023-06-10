package koans.french;

public class AboutMoreMethods {
    /**
     * Create a method named 'abs' which has a parameter for an integer number, and returns the absolute value of that number
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * abs(-2) should return 2
     * 
     */


     /**
     * Create a method named 'min' which has 2 integer parameters, and returns the smallest of those 2 numbers.
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * min(4, 3) should return 3
     * 
     */


     /**
     * Create a method named 'remainder' which has 2 integer parameters:
     * 
     * - first one is a dividend
     * - second one is a divisor
     * 
     * It returns the remainder of the integer division of the first number by the second.
     * 
     * ---------   TIPS --------------
     * 
     * You can compute an integer division with the '/' operator:
     * 
     *     int result = 13 / 3; // result is 4
     * 
     * The remainder is what's left of the dividend which is not divided.
     * For example, when we divide 13 by 3, 12 is fully divided (in 4), and the reminder is 13 - 12 = 1. 
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * remainder(17, 5) should return 2
     * 
     */


    /**
     * Create a method named 'isEven' which has an integer parameter, and return true if the number is even, false otherwise.
     * Use the previous method 'remainder' to compute the result.
     * 
     * ---------   TIPS --------------
     * 
     * The type of a value which can be either true or false is called a 'boolean' in Java.
     * You already encountered booleans: conditions are using booleans. But you can use booleans outside of conditions. Ex:
     * 
     *     boolean result = 3 > 4; // result is false
     * 
     * You can make a method returning a boolean by specifying that the return type of the method is boolean:
     * 
     *     public static boolean isCool() {
     *         // Some code returning a boolean
     *     }
     * 
     * To compute wether or not a number is even, think about what happens to the remainder when you divide that number by 2.
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * isEven(5) should return false
     * 
     */

     
    /**
     * Create a method named 'isMultiple' which has 2 integer parameters and return true if the second one is a multiple of the first one.
     * Use the method 'remainder' to compute the result.
     * 
     * ---------   TIPS --------------
     * 
     * Think about what happens to the remainder when you divide a number by a multiple.
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * isMultiple(15, 5) should return true
     * 
     */

     
    /**
     * Use the previous 'isMultiple' method to code a method named 'fizzBuzz' which displays in the console:
     * 
     * - "Fizz" if the number is a multiple of 3
     * - "Buzz" if the number is a multiple of 5
     * - "FizzBuzz" if the number is a multiple of 3 and 5
     * - the number itself otherwise
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * fizzBuzz(15) should display:
     * 
     * Buzz
     * 
     */ 
      
}
