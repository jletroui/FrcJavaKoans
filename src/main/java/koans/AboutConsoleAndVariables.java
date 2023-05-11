package koans;

import static engine.Helpers.readLine;

public class AboutConsoleAndVariables {
    /**
     * Display 'Hello!' in the console.
     * 
     * ---------   TIPS --------------
     * 
     * You can use the method System.out.println([some value]) to do so.
     * 
     * You can tell Java that something is text by enclosing it between double quotes. Ex:
     * 
     * "This is text"
     * 
     * -------------------------------
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
     * ---------   TIPS --------------
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
     * -------------------------------
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
     * ---------   TIPS --------------
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
     * -------------------------------
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
     * ---------   TIPS --------------
     * 
     * The java type for a piece of text is called 'String'.
     * 
     * -------------------------------
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
     * ---------   TIPS --------------
     * 
     * You can read a text from the console with readLine(). This method is _returning_ the value typed in the console by the user.
     * 'Returning a result' means Java will replace the call to the method by its returned result in the code.
     * 
     * Let's say that when asked, the user will type 'Hello' then the [ENTER] key in the console. Then:
     * 
     * String someText = readLine();
     * 
     * Will become the equivalent to:
     * 
     * String someText = "Hello";
     * 
     * -------------------------------
     * 
     * Expected result in the console:
     * 
     * What is your name?
     * [Enter some name]
     * Your name is:
     * [The name entered above]
     */
    public static void askAndDisplayNameInConsole() {
        System.out.println("What is your name?");
        String name = readLine();
        System.out.println("Your name is:");
        System.out.println(name);
    }    
}
