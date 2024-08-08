package koans.english;

import static engine.Helpers.readLine;

public class AboutMethods {
    /**
     * # Repetitive tasks
     * 
     * Ask for an age. Display the age in 5 years.
     * Ask for an age a second time. Display the new age in 10 years.
     * Ask for an age a third time. Display the new age in 20 years.
     * 
     * -------------------------------
     * 
     * Expected result in the console:
     * 
     * What is your age?
     * [Enter some age]
     * In 5 years from now, you will be [The age entered above + 5 years].
     * What is your age?
     * [Enter some age]
     * In 10 years from now, you will be [The new age entered above + 10 years].
     * What is your age?
     * [Enter some age]
     * In 20 years from now, you will be [The new age entered above + 20 years].
     * 
     */
    public static void computeAgeIn5And10And20YearsConsole() {

        int yearGap = 5;

        for(int i = 0; i < 3; i++){

            if(i == 2){

                computeAge(yearGap * (i+2));

            } else {

                computeAge(yearGap * (i+1));

            }      

        }

    }

    public static void computeAge(int y){

        System.out.println("What is your age?");
        int age = Integer.parseInt(readLine());
        System.out.println("In " + y + " years from now, you will be " + (age+y) + ".");
        
        
    }

    /**
     * # Repetitive tasks: methods to the rescue
     * 
     * Now, that was a bit tedious to repeat 3 times almost the same thing, was it not?
     * Do it again, but this time, with a new method in this class.
     * 
     * ---------   TIPS --------------
     * 
     * A method is a new mini-program. You already know what a method is, because each exercise you completed made you code the _body_ of a method.
     * It is time to create your own method and make it execute yourself.
     * To create a method, place yourself somewhere within the '{' and '}' of the class, but outside the existing methods. Ex:
     *                                                                        <-- wrong place, outside the class
     *     public class AboutMethods {
     *                                                                        <-- good place 
     *         public static void computeAgeIn5And10And20YearsConsole() {
     *                                                                        <-- wrong place, inside an other method
     *         }
     *                                                                        <-- good place 
     *     }
     *                                                                        <-- wrong place, outside the class
     * 
     * Then, you will have to write the _signature_ of a method, which is telling Java about several things.
     * We will care for now only about 2 things: its _name_ and its _parameters_.
     * You need to give a name to the method, in the same way you give a name to a variable: so when you want to refer to it, Java knows what you are referring to.
     * The parameters are a way for the part of the program wanting to execute the method to give it some 'configuration'. Let's see how to write a signature:
     * 
     *     public static void [name of the method]([parameter 1 type] [parameter 1 name], [parameter 2 type] [parameter 2 name], ...) {
     *
     *     }
     * 
     * The signature starts with 'public static void', which we will ignore for now. Next comes the name of the method.
     * It is very important to choose a name that tells very well to the reader why you created the method (as opposed to, say, what the method is doing inside).
     * For example, let's say we want to create a method that takes a text and outputs it in the console with exclamation marks as if it was yelling.
     * Avoid names like "displayInConsoleAndAddExclamations". The reader will be able to already see that in the code within the method.
     * Prefer names that express what the intent is, in as plain English as possible. For example "yell".
     * 
     * Next come the parameters. The parameter list is given between parentheses. If your method has no parameters, just write '()' after its name.
     * If you have more than one parameter, separate each of them with ','.
     * A parameter is a bit like a variable for the method, except it's not the method that is deciding what the value is.
     * The method will instead _receive_ the value from the rest of the program. So it does not know what the value is, and this is ok.
     * It can still use that value like a normal variable. In our example, the yelling method will need to know which text to yell.
     * So the type will be text, or 'String' in Java.
     * And the name could be something like 'textToYell' so it is easy to understand what's the purpose of this parameter.
     * 
     * Everything together, this is how you would define this method:
     * 
     *                       name  param type  param name
     *                          v     v           v
     *     public static void yell(String textToYell) {
     *          System.out.println(textToYell + "!!!");  
     *     }
     *                                ^
     *       Inside the method, the parameter is used like a regular variable
     * 
     * Now, how do you execute this method 'yell'? In another method, you can 'call' it. Meaning you can ask Java to execute the code within the method.
     * For that, you will need to tell Java the name and the values of the parameters of the method. You give the values inside parentheses.
     * For example:
     * 
     *     public static void otherMethod() {
     *         yell("Java is so cool");
     *     }
     *          ^           ^
     *    method name    parameter 1 value
     * 
     * For this exercise, you need to create a method that is asking and computing the age in a certain number of years.
     * This certain number of years would be the method's parameter.
     * In the existing method 'computeAgeIn5And10And20YearsConsoleWithMethod()', you will then only have to call this method 3 times:
     *   - once with a value of 5
     *   - once with a value of 10
     *   - and finally once with a value of 20
     * 
     * -------------------------------
     * 
     * Expected result in the console:
     * 
     * What is your age?
     * [Enter some age]
     * In 5 years from now, you will be [The age entered above + 5 years].
     * What is your age?
     * [Enter some age]
     * In 10 years from now, you will be [The new age entered above + 10 years].
     * What is your age?
     * [Enter some age]
     * In 20 years from now, you will be [The new age entered above + 20 years].
     * 
     */
    public static void computeAgeIn5And10And20YearsConsoleWithMethod() {

        int yearGap = 5;

        for(int i = 0; i < 3; i++){

            if(i == 2){

                computeAge(yearGap * (i+2));

            } else {

                computeAge(yearGap * (i+1));

            }      

        }

    }

    /**
     * # Methods can also return results
     * 
     * Write a method that has a parameter for an integer number, and returns the square of that number.
     * 
     * ---------   TIPS --------------
     * 
     * Up to now, methods were only "doing stuff", like displaying text to the console. But they were not communicating anything to the rest of the program.
     * A very useful thing in programmation is when methods can not only receive information _from_ the rest of the program (via its parameters) but also give back information _to_ the rest of the program.
     * When a method gives back information, it is called 'returning a value'. It can return one, and only one value, called 'the return value'. Up to now, the methods we saw started with:
     * 
     *     public static void [name of the method]()
     *                     ^
     *                 The type of return
     * 
     * 'void' meant that our methods did not return anything.
     * To make a method return something, we must tell Java what type of value we want the method to return.
     * For example, let's say we want to make a method divide an integer number by 2:
     * 
     *     public static int half(int number) { ... }
     *                    ^
     *           Now returning an integer number
     * 
     * However, this is just telling Java that, at the end of the method, we will return a number.
     * How do we do the actual returning thing? Simply by using the special word 'return':
     * 
     *     public static int half(int number) {
     *         return number / 2;
     *     }
     *                     ^
     *            An integer expression
     * 
     * Finally, how do we benefit from this method? We can simply call it somewhere else, as any other method. For example:
     * 
     *     System.out.println(half(10));
     * 
     * In this example, Java will call the method, and replace the call with the return value.
     * In this example, this would be equivalent to writing:
     * 
     *     System.out.println(5);
     * 
     * In the next series of exercises, though, we will not call our methods, just create them.
     * 
     * For this exercise, the signature of the method has been written for you, you simply need to modify the body of it.
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * square(3) should return 9
     * 
     */
    public static int square(int number) {
        return number * number;
    }    

    
    /**
     * # Returning the opposite of a number
     * 
     * Write a method named 'opposite' which has a parameter for an integer number, and returns the opposite of that number.
     * 
     * ---------   TIPS --------------
     * 
     * For this exercise, you have to write the signature of the method yourself.
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * opposite(2) should return -2
     * 
     */

     public static int opposite(int number){

        return -1 * number;

     }


    /**
     * # Helping a farmer
     * 
     * Write a method named 'legs' which helps a farmer counts the number of legs of her animals, given the count of each type of animal.
     * There are 3 int parameters: chickenCount, pigCount, cowCount.
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * legs(2, 3, 4) should return 32
     * 
     */
    public static int legs(int chick, int pig, int cow){

        return chick*2 + pig * 4 + cow * 4;

    }
}
