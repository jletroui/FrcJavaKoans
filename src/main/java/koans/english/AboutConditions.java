package koans.english;

public class AboutConditions {
    /**
     * # If construct, and conditions
     * 
     * Write a method named 'sign' which has a parameter for an integer number, and returns a text:
     * 
     * - if the number is greater than or equal to zero, it returns the text "positive"
     * - if the number is lesser than zero, it returns the text "negative"
     * 
     * ---------   TIPS --------------
     * 
     * You can execute code depending on a condition. Conditions between 2 numbers can be expressed this way:
     * 
     *     4 == 4
     *       ^
     *    equality
     * 
     *     4 != 4
     *        ^
     *    inequality
     * 
     *     4 > 3
     *       ^
     *  greater than
     * 
     *     4 >= 3
     *       ^
     * greater than or equal
     * 
     *     4 < 3
     *       ^
     *   lesser than
     * 
     *     4 <= 3
     *       ^
     *  lesser than or equal
     * 
     * To use a condition in your code, you can use an if / else statement. Ex:
     * 
     *     if (number > 10) {
     *         // Code executed only if number is greater than 10
     *     } else {
     *         // Code executed only if number is not greater than 10
     *     }

     * Note1: double bars // allow to write a comment until the end of the line. Ex:
     * 
     *     String text = "Hello"; // Assign the value "Hello" to the variable 'text'.
     * 
     * Note 2: you can return from multiple places in the method's body. Ex:
     * 
     *     if (number > 10) {
     *         return 1; // Stop the method execution right there and return 1.
     *     } else {
     *         return 0; // Stop the method execution right there and return 0.
     *     }
     * 
     * Note 3: within the curly brackets of a 'if' or 'else', you can write all the code you want, including other 'if' / 'else' !.
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * sign(2) should return "positive"
     * 
     */


    /**
     * # Else if construct
     * 
     * Write a method named 'signOrZero' which has a parameter for an integer number, and returns a text:
     * 
     * - if the number is greater than zero, it returns the text "positive"
     * - if the number is 0, it returns the text "zero"
     * - if the number is lesser than zero, it returns the text "negative"
     * 
     * ---------   TIPS --------------
     * 
     * To use multiple conditions in your code, you can use an if / else if / else statement. Ex:
     * 
     *     if (number > 10) {
     *         // Code executed only if number is greater than 10
     *     } else if (number > 0) {
     *         // Code executed only if number is not greater than 10, but is greater than 0
     *     } else {
     *         // Code executed if number is neither greater than 10 nor greater than 0
     *     }
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * signOrZero(2) should return "positive"
     * signOrZero(0) should return "zero"
     * 
     */


    /**
     * # An exam's grade
     *  
     * Write a method named 'gradeComment' which has a parameter for an integer number representing an exam grade, and returns a text:
     * 
     * - if the grade is less than 0 or greater than 100, it returns the text "You cheat!"
     * - if the grade is 100, it returns the text "Congratulations! You got everything right!"
     * - if the grade is 0, it returns the text "Oops!"
     * - if the grade is greater than 0 but less than 60, it returns the text "You failed!"
     * - else, returns the text "Congratulations! You pass!"
     * 
     * ---------   TIPS --------------
     * 
     * You can combine conditions together using the '&&' (AND) and '||' (OR) operators:
     * 
     *     if ((4 > 3) && (3 > 4)) {
     *         // Code here will not be executed
     *     }
     *     if ((4 > 3) || (3 > 4)) {
     *         // Code here will be executed
     *     }
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * gradeComment(80) should return "Congratulation! You pass!"
     * gradeComment(0) should return "Oops!"
     * 
     */    
}
