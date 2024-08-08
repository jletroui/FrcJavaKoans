package koans.english;

import static engine.Helpers.readLine;

public class AboutConsoleAndVariables {
    /**
     * # Displaying some text in the console
     * 
     * Display 'Hello!' in the console.
     * 
     * ---------   TIPS --------------
     * 
     * All lines of code in Java must end with the ';' character. Ex:
     * 
     *      System.out.println("Apple");
     * 
     * You can use the method System.out.println([some value]) to display a value in the console.
     * 
     * You can tell Java that some value is text by enclosing it between double quotes. Ex:
     * 
     *      "This is text"
     * 
     * -------------------------------
     * 
     * Expected result in the console:
     * 
     * Hello!
     * 
     */
    public static void sayHelloInConsole() {

        System.out.println("Hello!");

    }

    /**
     * # Displaying some calculation in the console
     * 
     * Make Java compute 2 + 2 for you, and display the result in the console.
     * 
     * ---------   TIPS --------------
     * 
     * In Java, you can compute arithmetic expressions in Java. For example:
     * 
     *      3 + 4
     *      3 * 4
     *      3 / 4
     *      3 - 4
     * 
     * You can use the method System.out.println([some arithmetic expression]) to do so.
     * 
     * -------------------------------
     * 
     * Expected result in the console:
     * 
     * 4
     * 
     */
    public static void computeTwoAndTwo() {

        System.out.println(2+2);

    }

    /**
     * # Creating a variable to store a value
     * 
     * Compute 2 + 2 and store the value in a variable. Display the value of the variable.
     * 
     * ---------   TIPS --------------
     * 
     * A variable is a piece of information with a name. We can get the value by using its name.
     * So, to declare a variable, you need to give it a name and a value.
     * But in Java, you also need to give the kind of information (its type).
     * For example, when the information is an integer number, the type is called 'int'.
     * Putting everything together, if we want to create a variable for the number of legs of a pig:
     * 
     *      int pigNbOfLegs = 4;
     *       ^       ^        ^
     *      type   name     value
     * 
     * The value can also be an arithmetic expression. For example:
     * 
     *      int pigNbOfLegs = 1 + 1 + 1 + 1;
     * 
     * Whenever you use the name of the variable, Java will replace the name with the variable's value when running.
     * For example, if you want to display the variable defined above:
     * 
     *      System.out.println(pigNbOfLegs);
     *                              ^
     *          'pigNbOfLegs' will be replaced by the variable's value.
     * 
     * So this is equivalent to (because the variable's value is 4):
     * 
     *      System.out.println(4);
     * 
     * -------------------------------
     * 
     * Expected result in the console:
     * 
     * 4
     */
    public static void createAndDisplayAVariable() {

        int pigNbOfLegs = 1 + 1 + 1 + 1;

        System.out.println(pigNbOfLegs);

    }

    /**
     * # Creating a variable to store text
     * 
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
     * 
     */
    public static void createAndDisplayAStringVariable() {

        String greeting = "Hello!";

        System.out.println(greeting);

    }


    /**
     * # Asking for some user answer
     * 
     * Display a ask for a name in the console. Let the user answer. Store the answer in a String variable. Display 'Your name is:' and then the answer.
     * 
     * ---------   TIPS --------------
     * 
     * You can read a text from the console with readLine(). This method is _returning_ the value typed in the console by the user.
     * 'Returning a result' means Java will replace the call to the method by its returned result in the code.
     * 
     * Let's say that when asked, the user will type 'Hello' then the [ENTER] key in the console. Then:
     * 
     *     String someText = readLine();
     *                             ^
     *       cet appel de méthode va être remplacé par son résultat
     * 
     * Will become the equivalent to:
     * 
     *     String someText = "Hello";
     * 
     * -------------------------------
     * 
     * Expected result in the console:
     * 
     * What is your name?
     * [Enter some name]
     * Your name is:
     * [The name entered above]
     * 
     */
    public static void askAndDisplayNameInConsole() {

        System.out.println("What is your name?");
        String name = readLine();
        System.out.println("Your name is:");
        System.out.println(name);

    }

    /**
     * # Play with text content
     * 
     * Same thing as before, but display the name on a single line.
     * 
     * ---------   TIPS --------------
     * 
     * You will need to create a text out of 2 text values. Glueing multiple text values together is called 'concatenating'.
     * In Java, you can do that with the '+' operator:
     * 
     *     String glued = "abc" + "def";
     *               ^
     *       glued's value is "abcdef"
     * 
     * Warning! Don't confuse the String concatenation operator with the number's 'addition' operator. This won't work:
     * 
     *     4 + "abc"
     *       ^
     *     Error: because the left of the operator is a number, '+' is the 'addition' operator for numbers, not the text's 'concatenate' one.
     *     As a result, Java expects an other number to the right of the '+', but "abc" is not a number.
     * 
     * Note: however, this will work:
     * 
     *     String glued = "abc" + 4;
     *               ^
     *       glued's value is "abc4"
     * 
     * This is because Java can convert anything, including numbers, to text automatically. So in this case, it is equivalent to:
     * 
     *     String glued = "abc" + "4";
     *                              ^
     *                 Automatically converted to text
     * 
     * -------------------------------
     * 
     * Expected result in the console:
     * 
     * What is your name?
     * [Enter some name]
     * Your name is: [The name entered above]
     * 
     */
    public static void askAndDisplayNameOnASingleLineInConsole() {

        System.out.println("What is your name?");
        String name = readLine();
        System.out.println("Your name is: " + name);

    }

    /**
     * # Playing more with text content
     * 
     * Ask for a name, then for an age. Display both on the same line.
     * 
     * ---------   TIPS --------------
     * 
     * You can concatenate more than 2 texts together. For example:
     * 
     *      String glued = "abc" + "def" + "ghi";
     *               ^
     *       glued's value is "abcdefghi"
     * 
     * -------------------------------
     * 
     * Expected result in the console:
     * 
     * What is your name?
     * [Enter some name]
     * What is your age?
     * [Enter some age]
     * Your name is [The name entered above] and your age is [The age entered above].
     * 
     */
    public static void askNameAndAgeInConsole() {

        System.out.println("What is your name?");
        String name = readLine();
        System.out.println("What is your age?");
        String age = readLine();
        System.out.println("Your name is " + name + " and your age is " + age + ".");

    }

    
    /**
     * # Playing with text and numbers at the same time
     * 
     * Ask for an age. Display the age in 5 years.
     * 
     * ---------   TIPS --------------
     * 
     * The trick is, the value returned from the console is always text.
     * As a result, in order to compute the 5-years-from-now value, you will need to convert the text to an integer number first.
     * You can convert some text to an integer like so:
     * 
     *      String nbOfOrangesAsText = "3";
     *      int nbOfOrangesAsNumber = Integer.parseInt(nbOfOrangesAsText);
     *                   ^
     *         value is 3, as a number
     * 
     * For the computation, you can either create a new variable you can display later:
     * 
     *     int nbOfOragesAfterIAteOne = nbOfOrangesAsNumber - 1;
     * 
     * Or compute it directly where you need it, by using parentheses:
     * 
     *     System.out.println("Oranges left: " + (nbOfOrangesAsNumber - 1));
     * 
     * -------------------------------
     * 
     * Expected result in the console:
     * 
     * What is your age?
     * [Enter some age]
     * In 5 years from now, you will be [The age entered above + 5 years].
     * 
     */
    public static void computeAgeIn5YearsConsole() {

        System.out.println("What is your age?");
        String ageInput = readLine();
        int age = Integer.parseInt(ageInput);
        System.out.println("In 5 years from now, you will be " + (age + 5) + ".");

    }
}
