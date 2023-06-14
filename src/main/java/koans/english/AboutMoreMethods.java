package koans.english;

public class AboutMoreMethods {
    /**
     * Write a 'abs' method which has a parameter for an integer number, and returns the absolute value of that number
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * abs(-2) should return 2
     * 
     */


     /**
     * Write a 'min' method which has 2 integer parameters, and returns the smallest of those 2 numbers.
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * min(4, 3) should return 3
     * 
     */


     /**
     * Write a 'remainder' method which has 2 integer parameters:
     * 
     * - first one is a dividend
     * - second one is a divisor
     * 
     * The method returns the remainder of the integer division of the dividend by the divisor.
     * 
     * ---------   TIPS --------------
     * 
     * You can compute an integer division with the '/' operator:
     * 
     *     int result = 13 / 3; // 'result' value is 4
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
     * Write a 'isEven' method which has an integer parameter, and return true if the number is even, false otherwise.
     * Use the previous method 'remainder' to compute the result.
     * 
     * ---------   TIPS --------------
     * 
     * The type of a value which can be either true or false is called a 'boolean' in Java.
     * You already encountered booleans: conditions are using booleans. But you can use booleans outside of conditions. Ex:
     * 
     *     boolean result = 3 > 4; // 'result' value is false
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
     * Write a 'isMultiple' method which has 2 integer parameters and return true if the second one is a multiple of the first one.
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
     * Use the previous 'isMultiple' method to code a 'fizzBuzz' method with a integer parameter which displays in the console:
     * 
     * - "Fizz" if the integer is a multiple of 3
     * - "Buzz" if the integer is a multiple of 5
     * - "FizzBuzz" if the integer is a multiple of 3 and 5
     * - the integer itself otherwise
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * fizzBuzz(20) should display:
     * 
     * Buzz
     * 
     */ 
      
}
