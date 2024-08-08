package koans.english;

public class AboutDecimalNumbers {
    /**
     * # Converting a measure of length
     * 
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
     * To convert from inches to centimeters, simply multiply by 2.54.
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * toCm(2.0) should return 5.08
     * 
     */
    public static double toCm(double num){

        return num * 2.54;

    }


    /**
     * # Converting the other way
     * 
     * Write a method named 'toInches' which does the opposite of the previous one.
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * toInches(5.08) should return 2.0
     * 
     */

    public static double toInches(double num){

        return num / 2.54;

    }


    /**
     * # Computing some geometry
     * 
     * Write a method named 'rectangleArea' which computes the area of a rectangle, given the length of its sides.
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * rectangleArea(3.6, 2.0) should return 7.2
     * 
     */
    public static double rectangleArea(double num1, double num2){

        return num1 * num2;

    }


     /**
     * # Computing how long a robot is traveling step 1
     * 
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
    public static double wheelCircumference(double radius){

        return 2*(3.14)*radius;

    }


    /**
     * # Computing how long a robot is traveling step 2
     * 
     * Write a method named 'wheelRotations' which computes the number of rotations of the wheel given the number of rotations of the motor and the gear ratio.
     * 
     * ---------   TIPS  --------------
     * 
     * The gear ratio is how many rotations the wheel is turning when the motor rotates once.
     * For example, if the motor has to turn 5 rotations to make the wheel rotate once, the ratio is 1/5 = 0.2.
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * wheelRotations(2.0, 0.2) should return 0.4
     * 
     */
    public static double wheelRotations(double num1, double num2){

        return num1 * num2;

    }


    /**
     * # Computing how long a robot is traveling final step
     * 
     * Use the previous 2 methods in a new one named 'toDistance' which computes the distance the wheel traveled given:
     * 
     * - The number of rotations of the motor.
     * - The gear ratio of the gearbox.
     * - The wheel radius.
     * 
     * Warning! Do not recode the computations already coded in the previous 2 methods. Instead, reuse the previous 2 methods in this one.
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * toDistance(10.0, 0.2, 2.0) should return 25.12
     * 
     */
    public static double toDistance(double num1, double num2, double num3){

        return wheelRotations(num1, num2) * wheelCircumference(num3);

    }

     
}
