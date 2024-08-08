package bonuses.english;

import java.util.List;

import engine.Locale;
import engine.Sensei;
import sensei.AboutArraysKoans;

public class AboutArrays {
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


     /**
     * # First element of an array
     * 
     * Write a method named 'first' with an array of integers 'array' as a parameter, which returns the first element of that array.
     * 
     * ---------   TIPS   --------------
     * 
     * Sometime, we would like to store a lot of values of the same type. Example: recording the battery level of a robot for every second during a 2m30s match (150 double values).
     * Having 150 variable of type 'double' would be very cumbersome to manipulate in our program. Instead, we would like a single variable, of a type storing all 150 values at once.
     * 
     * Such a type is called an 'array'. To create an array of values of type 'SomeType' with n values, you can write:
     * 
     *     SomeType[] myArray = new SomeType[n];
     * 
     * Ex:
     * 
     *     double[] batteryLevels = new double[150];
     * 
     * An array is storing all values sequentially. You can access one value (called an 'element of the array') by using its position called an 'index'. Indices in arrays are starting at 0. You specify the index inside '[' and ']'.
     * Therefore, if you want to display the 3rd element of 'batteryLevels':
     * 
     *     System.out.println(batteryLevels[2]);
     * 
     * Similarly, if you want to store a value in the 2nd element:
     * 
     *     bateryLevels[1] = 12.8;
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * The following code:
     * 
     *     var a = new int[2];
     *     a[0] = 10;
     *     a[1] = 20;
     *     System.out.println(first(a));
     * 
     * Should display:
     * 
     * 10
     * 
     */


     /**
     * # Last element of an array
     * 
     * Write a method named 'last' with an array of integers 'array' as a parameter, which returns the last element of that array.
     * 
     * ---------   TIPS   --------------
     * 
     * To know the size of a given array you did not create yourself, you can use its 'length' field:
     * 
     *     double[] batteryLevels = new double[150];
     *     System.out.println(batteryLevels.length); // Display 150
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * The following code:
     * 
     *     var a = new int[2];
     *     a[0] = 10;
     *     a[1] = 20;
     *     System.out.println(last(a));
     * 
     * Should display:
     * 
     * 20
     * 
     */


     /**
     * # Finding an element
     * 
     * Write a method named 'findFirst' with an array of integers 'array' and a integer 'n' as parameters.
     * It returns the first index of the given number within the array.
     * If the element is not found, it should return -1.
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * The following code:
     * 
     *     var a = new int[2];
     *     a[0] = 10;
     *     a[1] = 20;
     *     a[2] = 20;
     *     System.out.println(findFirst(a, 20));
     * 
     * Should display:
     * 
     * 1
     * 
     */


     /**
     * # Finding an element at the end
     * 
     * Write a method named 'findLast' with an array of integers 'array' and a integer 'n' as parameters.
     * It returns the last index of the given number within the array.
     * If the element is not found, it should return -1.
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * The following code:
     * 
     *     var a = new int[2];
     *     a[0] = 10;
     *     a[1] = 20;
     *     a[2] = 20;
     *     System.out.println(findLast(a, 20));
     * 
     * Should display:
     * 
     * 2
     * 
     */


     /**
     * # Finding the smallest element
     * 
     * Write a method named 'min' with an array of integers 'array' as a parameter.
     * It returns the smallest number within the array.
     * If the array is empty, it should return Integer.MAX_VALUE, which is the largest possible int.
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * The following code:
     * 
     *     var a = new int[2];
     *     a[0] = 10;
     *     a[1] = 2;
     *     a[2] = 20;
     *     System.out.println(min(a));
     * 
     * Should display:
     * 
     * 2
     * 
     */


     /**
     * # Finding the smallest element, revisited
     * 
     * Using a 'for :' loop, write a method named 'min2' with an array of integers 'array' as a parameter.
     * It returns the smallest number within the array.
     * If the array is empty, it should return Integer.MAX_VALUE, which is the largest possible int.
     * 
     * ---------   TIPS   --------------
     * 
     * Believe it or not, but when it comes to looping through all elements of an array, there is yet another shortcut for the 'for' loop.
     * 
     * Since looking at all elements of an array is such a common place, there is a special syntax you can use:
     * 
     *     for(type variableName: someArray) {
     *         // do something with variableName
     *     }
     * 
     *  For example, for displaying all the elements of an array:
     * 
     *     for(double level: batteryLevels) {
     *         System.out.println(level);
     *     }
     * 
     * Note: this is only useful if you do not need the index of the current element. If, in addition to the element, you also need its index within the loop, use a regular 'for' loop.
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * Same as the min2() method, but using a 'for :' loop instead of a regular 'for' loop.
     * 
     */


     /**
     * # Computing the sum
     * 
     * Write a method named 'sum' with an array of integers 'array' as a parameter.
     * It returns the sum of all numbers within the array.
     * If the array is empty, it should return 0.
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * The following code:
     * 
     *     var a = new int[2];
     *     a[0] = 10;
     *     a[1] = 2;
     *     a[2] = 20;
     *     System.out.println(sum(a));
     * 
     * Should display:
     * 
     * 32
     * 
     */


     /**
     * # Computing the average
     * 
     * Write a method named 'avg' with an array of integers 'array' as a parameter.
     * It returns the average of numbers within the array (as a double).
     * If the array is empty, it should return 0.0.
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * The following code:
     * 
     *     var a = new int[2];
     *     a[0] = 10;
     *     a[1] = 3;
     *     a[2] = 20;
     *     System.out.println(avg(a));
     * 
     * Should display:
     * 
     * 11
     * 
     */


     /**
     * # Filling an array
     * 
     * Write a method named 'fill' with an integer 'size' and an integer 'value' as a parameters.
     * It returns an integer array with that number of element, all of them having the value 'value'.
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * The following code:
     * 
     *     fill(3, 20);
     * 
     * Should return an array with 3 elements, all of them having the value 20.
     * 
     */


     /**
     * # Creating a serie
     * 
     * Write a method named 'serie' with an integer 'size' as a parameter.
     * It returns an integer array with that number of element.
     * The first element is 1. The second is 2. The 3rd is 3, and so on.
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * The following code:
     * 
     *     serie(4);
     * 
     * Should return the array [1, 2, 3, 4]. 
     * 
     */


     /**
     * # Switch two elements
     * 
     * Write a method named 'switchFirst2' with takes an array 'array' as a parameter.
     * If the array has 2 and only 2 elements, the method is switching them within the array.
     * Otherwise, it does nothing.
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * After the following code:
     * 
     *     var a = new int[2];
     *     a[0] = 10;
     *     a[1] = 3;
     *     switchFirst2(a);
     * 
     * a should be [3, 10]. 
     * 
     */


     /**
     * # Reverse an array
     * 
     * Write a method named 'reverse' with takes an array 'array' as a parameter.
     * Returns a new array with all elements reverted.
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * After the following code:
     * 
     *     var a = new int[2];
     *     a[0] = 10;
     *     a[1] = 3;
     *     a[2] = 20;
     * 
     * reverse(a) should return the array [20, 3, 10]. 
     * 
     */


     public static void main(String[] args) {
        new Sensei(Locale.en, List.of(AboutArraysKoans.koans)).offerKoans();
    }
}
