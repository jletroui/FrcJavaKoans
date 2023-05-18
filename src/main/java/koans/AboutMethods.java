package koans;

import static engine.Helpers.readLine;

public class AboutMethods {
    /**
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
     */
    public static void computeAgeIn5And10And20YearsConsole() {

    }

    /**
     * Now, that was a bit tedious to repeat 3 times almost the same thing, was it not?
     * Do it again, but this time, with a new method in this class.
     * 
     * ---------   TIPS --------------
     * 
     * A method is a new mini program. You already know what a method is, because each exercise you completed made you code the _body_ of a method.
     * It is time to create your own method, and make it execute yourself.
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
     * You need to give a name to the method, in the same way you give a name to a variable: so when you want to refer to it, Java knows what you are refering to.
     * The parameters are a way for the part of the program wanting to execute the method to give it some 'configuration'. Let's see how to write a signature:
     * 
     *     public static void [name of the method]([parameter 1 type] [parameter 1 value], [parameter 2 type] [parameter 2 name], ...) {
     *
     *     }
     * 
     * We will ignore the 'public static void' for now. Next come the name of the method.
     * It is very important to choose a name which tells very well to the reader why you created the method (as opposed to, say, what the method is doing inside).
     * For example, let's say we want to create a method that takes a text, and output it in the console with exclamation marks, like if it was yelling.
     * Avoid names like "displayInConsoleAndAddExclamations". The reader will be able to already see that in the code within the method.
     * Prefer things that express what the intent is, in as plain english as possible. For example "yell".
     * 
     * Next come the parameters. Parameter list is given between parentheses. If your method have no parameters, just write '()' after its name.
     * If you have more than one parameter, separate each of them with a ','.
     * A parameter is a bit like a variable, except the method is not deciding what the value is.
     * It will instead _receive_ the value from the rest of the program. So it does not know what the value is, and this is ok.
     * It can still use that value like a normal variable. In our example, the yelling method will need to know which text to yell.
     * So the type will be text, or 'String' in Java.
     * And the name could be something like 'textToYell' so it is easy to understand what's the purpose of this parameter.
     * 
     * Everything together, this is how you would define this method:
     * 
     *                       name  param type  param name
     *                          v     v           v
     *     public static void yell(String textToYell) {
     *          System.out.println(textToYell + " !!!");  
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
     */
    public static void computeAgeIn5And10And20YearsConsoleWithMethod() {

    }
}
