package koans.english;

public class AboutDecimalNumbers {
    /**
     * Write a method named 'toCm' which has a parameter for a number of inches, and returns the conversion in centimeters.
     * 
     * ---------   TIPS  --------------
     * 
     * This time, the parameter cannot be an integer, because we want to be able to convert fractions of inches as well.
     * 
     * In Java, decimal numbers are represented with a type named 'double'. Ex:
     * 
     *     double pi = 3.14;
     * 
     * To convert from inches to centimaters, simply multiply by 2.54.
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * toCm(2.0) should return 5.08
     * 
     */


    /**
     * Write a method named 'toInches' which does the opposite of the previous one.
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * toInches(5.08) should return 2.0
     * 
     */


    /**
     * Write a method named 'rectangleArea' which computes the area of a rectangle, given the length of one of its sides.
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * rectangleArea(3.6, 2.0) should return 7.2
     * 
     */


     /**
     * Write a method named 'wheelCircumference' which computes the circumference of a robot wheel given the wheel's radius.
     * 
     * ---------   TIPS  --------------
     * 
     * Use the value 3.14 for pi.
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * wheelCircumference(1) should return 6.28
     * 
     */


    /**
     * Write a method named 'wheelRotations' which computes the number of rotations of the weel given the number of rotations of the motor and the gear ratio.
     * 
     * ---------   TIPS  --------------
     * 
     * The gear ratio is how many rotations the wheel is turning when the motor rotate 1 turn.
     * For example, if the motor has to turn 5 rotations to make the wheel turn 1 rotation, the ratio is 1/5 = 0.2.
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * wheelRotations(2.0, 0.2) should return 0.4
     * 
     */


    /**
     * Use the previous 2 methods in a new one named 'toDistance' which computes the distance the wheel traveled given:
     * 
     * - The number of rotations of the motor.
     * - The gear ratio of the gearbox.
     * - The wheel radius.
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * toDistance(10.0, 0.2, 2.0) should return 25.12
     * 
     */

     
}
