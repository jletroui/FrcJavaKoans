package koans;

import java.util.Scanner;

public class AboutConsoleAndVariables {
    /**
     * Display 'Hello!' in the console.
     * 
     * You can use the method System.out.println([some text]) to do so.
     * 
     * Text in Java is represented by enclosing it between double quotes. Ex:
     * 
     * "This is text"
     * 
     * Expected result in the console:
     * 
     * Hello!
     */
    public static void sayHelloInConsole() {
        System.out.println("Hello!");
    }

    /**
     * Make java compute 2 + 2 for you, and display the result in the console.
     * 
     * In Java, you can compute arithmetic expressions in Java. For example:
     * 
     * 3 + 4
     * 3 * 4
     * 3 / 4
     * 3 - 4
     * 
     * You can use the method System.out.println([some arithmetic expression]) to do so.
     * 
     * Expected result in the console:
     * 
     * 4
     */
    public static void computeTwoAndTwo() {
        System.out.println(2 + 2);
    }

    /**
     * Compute 2 + 2 and store the value in a variable. Display the value of the variable.
     * 
     * A variable is a piece of information which can be referred to by a name.
     * So, to declare a variable, you need to give it a name and a value.
     * But in Java, you also need to give the kind of information (its type).
     * For example, when the information is an integer number, the type is called 'int'.
     * Putting everything together, if we want to create a variable for the number of legs of a pig:
     * 
     * int pigNbOfLegs = 4;
     *  ^       ^       ^
     * type   name    value
     * 
     * The value can be an arithmetic expression. For example:
     * 
     * int pigNbOfLegs = 1 + 1 + 1 + 1;
     * 
     * Whenever you use the name of the variable, Java will replace it with its value when running.
     * For example, if you want to display the number of legs of a pig variable in the console:
     * 
     * System.out.println(pigNbOfLegs);
     *                         ^
     *     'pigNbOfLegs' will be replaced by its value.
     * 
     * This is equivalent to:
     * 
     * System.out.println(4);
     * 
     * Expected result in the console:
     * 
     * 4
     */
    public static void createAndDisplayAVariable() {
        System.out.println(2 + 2);
    }

    /**
     * Create a text variable called 'greeting' with the value 'Hello!' and display it in the console.
     * 
     * The java type for a piece of text is called 'String'.
     * 
     * Expected result in the console:
     * 
     * Hello!
     */
    public static void createAndDisplayAStringVariable() {
        System.out.println("Hello!");
    }


    /**
     * Display a ask for a name in the console. Let the user answer. Store the answer in a String variable. Display 'Your name is:' and then the answer.
     * 
     * You can read a text from the console with the System.console().readLine(). This method is _returning_ the value read.
     * It means Java will replace the call to the method by its return result.
     * 
     * Let's say that when asked, the user will type 'Hello' then the enter key in the console. Then:
     * 
     * String someText = System.console.readLine();
     * 
     * Will be equivalent to:
     * 
     * String someText = "Hello";
     * 
     * Expected result in the console:
     * 
     * What is your name?
     * [Enter some name]
     * Your name is:
     * [The name entered above]
     */
    public static void askAndDisplayNameInConsole() {
        var scanner = new Scanner(System.in);
        System.out.println("What is your name?");
        String name = scanner.nextLine();
        System.out.println(name);
        scanner.close();
    }    
}
