package koans.english;

public class AboutLoops {
     /**
     * # First loop
     * 
     * Write a method named 'helloNTimes' which has an integer parameter 'times' and displays 'Hello' in the console times times.
     * 
     * ---------   TIPS   --------------
     * 
     * To do things multiple times in Java, you can use the 'for' loop. While the condition is true the code
     * within the loop will run on repeat.
     * A for loop contains 3 attributes, Initialization, Condition, and Update
     * 
     *  - Initialization: This is a line of code that is run when the for loop starts. This is only run once and
     *                    and typically is used to set the initial state of the itteration variable
     * 
     *  - Condition: This is a condition that is checked at the start of each cycle of the loop. 
     *               If the condition is met it will end the loop before running the code inside again.
     *               The condition typically includes some sort of logic check against the itteration variable.
     * 
     *  - Update: This is a line of code that is after each cycle of the loop. It is typically used to update the
     *            itteration variable.
     * 
     *  Ex.
     * 
     *     for(int times = 0; times < 3; times++) {
     *         // It will take 3 executions of this block of code before the condition becomes true.
     *         // So Java will execute it 3 times, and then move on to the rest of the code.
     *         System.out.println("Still executing");
     *     }
     * 
     * There is another loop type 'while' that can be used to repeat sections of code however it is dangerous 
     * especially in robot code. A while loop will run forever until it's condition is met and this could cause
     * your code to lock up if the condition is not met when expected.
     * 
     * A while loop looks a lot like an if condition, except it will execute its block of code again and again while the condition stays true. 
     * 
     * Ex:
     * 
     *     int times = 3;
     *     while (times > 0) {
     *         // It will take 3 executions of this block of code before the condition becomes false.
     *         // So Java will execute it 3 times, and then move on to the rest of the code.
     *         System.out.println("Still executing");
     *         // We can modify the value of an existing variable. We take advantage of this capability here.
     *         times = times -1;
     *     }
     * 
     * -------------------------------
     * 
     *                              PLEASE ATTEMPT TO DO ALL EXERCIES USING FOR LOOPS
     *                                        REFRAIN FROM USING WHILE LOOPS
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * helloNTimes(2) should display:
     * 
     * Hello
     * Hello
     * 
     */
    public static void helloNTimes(int n){

          for(int i = 0; i < n; i++){

               System.out.println("Hello");

          }

    }


     /**
     * # Printing where we are in a loop
     * 
     * Write a method named 'displayNumbers' with an integer parameter 'n', which displays numbers between 1 and n.
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
     public static void displayNumbers(int n){
          
          for(int i = 0; i < n; i++){
          
               System.out.println(i+1);

          }

     }


     /**
     * # Counting in reverse
     * 
     * Write a method named 'displayReverseNumbers' with an integer parameter 'n', which displays numbers between 1 and n in reverse order.
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * displayReverseNumbers(3) should display:
     * 
     * 3
     * 2
     * 1
     * 
     */
    public static void displayReverseNumbers(int n){
          
     for(int i = n; i > 0; i--){
     
          System.out.println(i);

     }

}


     /**
     * # Multiples of 7
     * 
     * Write a method named 'sevens' with 1 integer parameter 'n', which displays all multiples of 7 between 1 and n.
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * sevens(30) should display:
     * 
     * 7
     * 14
     * 21
     * 28
     * 
     */
     public static void sevens(int num){

          int count = 1;

          while(7*count <= num){

               System.out.println(7*count);
               count++;

          }

    }


     /**
     * # Multiples of 7 or 8
     * 
     * Write a method named 'sevensOrEights' with 1 integer parameter 'n', which displays all multiples of 7 or 8 between 1 and n.
     * 
     * ---------   TIPS   --------------
     * 
     * Reuse the 'isMultiple' method in the AboutMoreMethods class. To reuse a method in an other class, write the class name, then a '.', before calling the method. Ex:
     * 
     *     AboutConsoleAndVariables.sayHelloInConsole();  // Will display 'Hello!' in the console
     *                  ^          ^          ^
     *             class name     dot     method call
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * sevensOrEights(20) should display:
     * 
     * 7
     * 8
     * 14
     * 16
     * 
     */
    public static void sevensOrEights(int num){

          int count = 1;

          boolean isSeven = true;

          boolean isFinished = false;

          while(!isFinished){

               if(isSeven){

                    if(7*count > num){

                         isFinished = true;

                    } else {

                         System.out.println(7*count);
                         isSeven = false;

                    }

               } else {

                    if(8*count > num){

                         isFinished = true;

                    } else {

                         System.out.println(8*count);
                         isSeven = true;
                         count++;

                    }
                    
               }

          }

    }


     /**
     * # Exponents
     * 
     * Write a method 'exponent' which computes the exponent of an integer by an other.
     * 
     * ---------   TIPS   --------------
     * 
     * Exponent will be noted '^' in comments. Example: 10^3.
     * x^n is x multiplied by itself n times. Ex:
     * 
     * 2^4 = 2 * 2 * 2 * 2 = 16
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * exponent(5, 3) should return 125
     * 
     */
    public static int exponent(int num1, int num2){

          int finalInt = 1;

          for(int i = 0; i < num2; i++){
               
               finalInt *= num1;
          
          }

          return finalInt;

    }


     /**
     * # Exponents, including 0
     * 
     * Write a method 'exponent2' which does the same as the previous one, except it also handles the case where n is 0.
     * 
     * ---------   TIPS   --------------
     * 
     * x^0 is always 1. Ex:
     * 
     * 34^0 = 1
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * exponent2(5, 3) should return 125
     * 
     */
    public static int exponent2(int num1, int num2){

          if(num2 == 0){

               return 1;
               
          } else {

               return exponent(num1, num2);

          }
     
    }


     /**
     * # Factorial
     * 
     * Write a method 'factorial' which computes the factorial of an integer.
     * 
     * ---------   TIPS   --------------
     * 
     * Factorial is noted '!'. Example: 3!.
     * The factorial of a number n is the product of all numbers between 1 and n. Ex:
     * 
     * 4! = 1 * 2 * 3 * 4 = 24
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * factorial(5) should return 120
     * 
     */
    public static int factorial(int num){

          int finalInt = 1;

          for(int i = 1; i <= num; i++){

               finalInt *= i;

          }

          return finalInt;

    }


}
