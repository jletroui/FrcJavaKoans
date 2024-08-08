package koans.english;

import static engine.Helpers.random;
import static engine.Helpers.readLine;

import java.security.PublicKey;

public class AboutNot7Game {
    /**
     * # Introduction to the 'Not 7!' game
     * 
     * These koans are a bit special because they put everything you learned so far into practice.
     * 
     * You will gradually code everything necessary for a 2 players game called 'Not 7!'.
     * 
     * Each player has only one turn. On its turn, the player will throw 2 dice as many times as they wish.
     * 
     * However, if at any time the result for one throw is 7, the player loses immediately.
     * 
     * Otherwise, when the player decides to pass and stop throwing, their score is the sum of all the dice results so far.
     * 
     * Here is a game example, in the console:
     * 
     * Player 1, it's your turn!
     * 
     * You threw 4 and 5.
     * Your result so far: 9.
     * Do you want to throw again [y/n]?
     * y
     * You threw 3 and 1.
     * Your result so far: 13.
     * Do you want to throw again [y/n]?
     * y
     * You threw 2 and 6.
     * Your result so far: 21.
     * Do you want to throw again [y/n]?
     * n
     * 
     * Well done, your score is 21!
     * 
     * Player 2, it's your turn!
     * 
     * You threw 2 and 3.
     * Your result so far: 5.
     * Do you want to throw again [y/n]?
     * y
     * You threw 4 and 4.
     * Your result so far: 13.
     * Do you want to throw again [y/n]?
     * y
     * You threw 2 and 1.
     * Your result so far: 16.
     * Do you want to throw again [y/n]?
     * y
     * You threw 3 and 3.
     * Your result so far: 22.
     * Do you want to throw again [y/n]?
     * n
     * 
     * Well done, your score is 22!
     * 
     * Congratulations, player 2 wins!!!
     * 
     * 
     * 
     * Here is another game example, where one unlucky player is throwing a 7:
     * 
     * Player 1, it's your turn!
     * 
     * You threw 3 and 2.
     * Your result so far: 5.
     * Do you want to throw again [y/n]?
     * y
     * You threw 5 and 6.
     * Your result so far: 16.
     * Do you want to throw again [y/n]?
     * n
     * 
     * Well done, your score is 16!
     * 
     * Player 2, it's your turn!
     * 
     * You threw 2 and 3.
     * Your result so far: 5.
     * Do you want to throw again [y/n]?
     * y
     * You threw 2 and 5.
     * Oh no, Not 7! You lose!
     * 
     * Congratulations, player 1 wins!!!
     */


    /**
     * # Rolling a 6 sided die
     * 
     * Write a method 'die6()' returning the result of a 6-face die throw as an integer.
     * 
     * ---------   TIPS --------------
     * 
     * To generate a random number between 0 and 1, use the method random(). Ex:
     * 
     *     double someNumber = random(); // 'someNumber' can be anything between 0 and 1
     * 
     * Try to think about what computations would be needed to go from a number between 0 and 1 to a number between 0 and 6.
     * 
     * Now, the result of the computation will be a decimal number, and you will need to round it to the next integer.
     * For example, if the result of your previous computation is 3.2, you will need to round it to 4. You can do that with:
     * 
     *     double rounded = Math.ceil(3.2) // 'rounded' is 4.0
     * 
     * Finally, you will need to convert that decimal number to an int. You can do this conversion by specifying the type you want to convert into in parentheses. Ex:
     * 
     *     int roundedAsInt = (int)4.0; // from double to int
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * die6() should return an int between 1 and 6 randomly
     * 
     */
    public static int die6(){

        return (int)(Math.random()*6)+1;

    }


    /**
     * # Asking a question to the player
     * 
     * Write a method 'askQuestion(String questionText)' asking a question to the user, and returns a boolean stating if the user answered 'y'.
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * askQuestion("Do you want to continue [y/n]? ") should:
     *   - display "Do you want to continue [y/n]? " in the console
     *   - let the user answer by entering text in the console
     *   - return true if the user entered 'y'
     * 
     * askQuestion("Do you like oranges [y/n]? ") should:
     *   - display "Do you like oranges [y/n]? " in the console
     *   - let the user answer by entering text in the console
     *   - return false if the user entered 'n'
     * 
     */
    public static boolean askQuestion(String questionText){

        System.out.println(questionText);
        String userInput = readLine();
        return userInput.equals("y");

    }


    /**
     * # Rolling 2 dice
     * 
     * Use die6() to write a method 'throwDice' throwing 2 dice, displaying the dice results in the console, and returning the sum of the 2 results.
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * When the thrown dice results are 2 and 3, throwDice() should display 'You threw 2 and 3.' and return 5.
     * 
     */
    public static int throwDice(){

        int roll1 = die6();
        int roll2 = die6();
        System.out.println("you threw " + roll1 + " and " + roll2 + ".");

        return roll1 + roll2;

    }


    /**
     * # Programming a round of the game: 1st step
     * 
     * Use 'throwDice' and 'askQuestion' to write a method called 'gameRoundv1' which repeatedly, while the user says 'y':
     * 
     * - throws 2 dice
     * - asks if the user wants to throw the dice again
     * 
     * ---------   TIPS --------------
     * 
     * Use a loop with a condition on the return value of the askQuestion() method.
     * You will have to create a boolean variable initialized with 'true' so that during the loop, you record whether the user wants to continue.
     *
     * You may use a while loop for this specific instance. 
     * Implementation will be easy if a while loop is used, but be sure to define your condition and updated the values using in the conditions logic correctly.
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * When the thrown dice results are 2 and 3, gameRoundv1() should display:
     * 
     * You threw 2 and 3.
     * Do you want to throw again [y/n]?
     * 
     * If the user answers 'y', then it should go for another throw.
     * If the user answers 'n', then it should stop.
     */
    public static void gameRoundv1(){

        boolean isPlaying = true;

        while(isPlaying){

            throwDice();
            if(!askQuestion("Do you want to throw again [y/n]?")){

                isPlaying = !isPlaying;
                
            }

        }

    }


    /**
     * # Programming a round of the game: 2nd step
     * 
     * Write a method called 'gameRoundv2' that does the same thing as gameRoundv1, but it also displays the sum of the results so far.
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * When the thrown dice results are 2 and 3, gameRoundv2() should display:
     * 
     * You threw 2 and 3.
     * Your result so far: 5.
     * Do you want to throw again [y/n]?
     * 
     * If the user answers 'y', then it should go for another round.
     * If the user answers 'n', then it should stop.
     */

    public static void gameRoundv2(){

        boolean isPlaying = true;

        while(isPlaying){

            System.out.println("Your result so far:" + throwDice() + ".");
            if(!askQuestion("Do you want to throw again [y/n]?")){

                isPlaying = !isPlaying;
                
            }

        }

    }

    /**
     * # Programming a round of the game: 3rd step
     * 
     * Write a method called 'gameRoundv3' that does the same thing as gameRoundv2, but it also returns the result once the player chooses to stop.
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * When the thrown dice results are 2 and 3, gameRoundv3() should display:
     * 
     * You threw 2 and 3.
     * Your result so far: 5.
     * Do you want to throw again [y/n]?
     * 
     * If the user answers 'n', then it should stop and return 5.
     */       

     public static int gameRoundv3(){

        boolean isPlaying = true;

        int throwSum = 0;

        while(isPlaying){

            throwSum = throwDice();

            System.out.println("Your result so far:" + throwSum + ".");
            if(!askQuestion("Do you want to throw again [y/n]?")){

                isPlaying = !isPlaying;
                
            }

        }

        return throwSum;

    }


    /**
     * # Programming a round of the game: 4th step
     * 
     * Write a method called 'gameRoundv4' that does the same thing as gameRoundv3, but it stops if the dice result is exactly 7.
     * If it does stop that way, 'gameRoundv4' should display "Oh no, Not 7! You Lose!", with an empty line after it and return 0.
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * When the thrown dice results are 4 and 3, gameRoundv4() should return 0 and display:
     * 
     * You threw 4 and 3.
     * Oh no, Not 7! You lose!
     * 
     */

     public static int gameRoundv4(){

        boolean isPlaying = true;

        int throwSum = 0;

        while(isPlaying){

            throwSum = throwDice();

            System.out.println("Your result so far:" + throwSum + ".");
            if(throwSum == 7){

                System.out.println("Oh no, Not 7! You lose!");
                return 0;

            }
            if(!askQuestion("Do you want to throw again [y/n]?")){

                isPlaying = !isPlaying;
                
            }

        }

        return throwSum;

    }


    /**
     * # Programming a round of the game: final step
     * 
     * Write a method called 'gameRoundv5' that does the same thing as gameRoundv4, but in the case of the player stopping before throwing a 7,
     *   it should display their score between empty lines.
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * When the thrown dice results are 4 and 3, gameRoundv5() should return 0 and display:
     * 
     * You threw 4 and 3.
     * Oh no, Not 7! You lose!
     * 
     * 
     * When the thrown dice results are 4 and 5, and the player answers 'n', gameRoundv5() should return 9 and display:
     * 
     * You threw 4 and 5.
     * Your result so far: 9.
     * Do you want to throw again [y/n]?
     * n
     * 
     * Well done, your score is 9!
     * 
     */
    public static int gameRoundv5(){

        boolean isPlaying = true;

        int throwSum = 0;

        while(isPlaying){

            throwSum = throwDice();

            System.out.println("Your result so far:" + throwSum + ".");
            if(throwSum == 7){

                System.out.println("Oh no, Not 7! You lose!");
                return 0;

            }
            if(!askQuestion("Do you want to throw again [y/n]?")){

                isPlaying = !isPlaying;
                
            }

        }

        System.out.println("Well done, your score is " + throwSum + "!");

        return throwSum;

    }


    /**
     * # Programming the game: 1st step
     * 
     * Using 'gameRoundv5', write a method called 'not7Gamev1' that displays which player's round it is, and makes that player play their round.
     * 
     * -------------------------------
     * 
     * Example of game:
     * 
     * Player 1, it's your turn!
     * 
     * You threw 4 and 5.
     * Your result so far: 9.
     * Do you want to throw again [y/n]?
     * y
     * You threw 3 and 1.
     * Your result so far: 13.
     * Do you want to throw again [y/n]?
     * y
     * You threw 2 and 6.
     * Your result so far: 21.
     * Do you want to throw again [y/n]?
     * n
     * 
     * Well done, your score is 21!
     * 
     * Player 2, it's your turn!
     * 
     * You threw 2 and 3.
     * Your result so far: 5.
     * Do you want to throw again [y/n]?
     * y
     * You threw 4 and 4.
     * Your result so far: 13.
     * Do you want to throw again [y/n]?
     * y
     * You threw 2 and 1.
     * Your result so far: 16.
     * Do you want to throw again [y/n]?
     * y
     * You threw 3 and 3.
     * Your result so far: 22.
     * Do you want to throw again [y/n]?
     * n
     * 
     * Well done, your score is 22!
     * 
     */


    /**
     * # Programming the game: final step
     * 
     * Write a method called 'not7Gamev2' that does the same thing as not7Gamev1, but is also displays who the winner is.
     * In case of a tie, simply display: 'Tie!'.
     * 
     * -------------------------------
     * 
     * Example of game:
     * 
     * Player 1, it's your turn!
     * 
     * You threw 4 and 5.
     * Your result so far: 9.
     * Do you want to throw again [y/n]?
     * y
     * You threw 3 and 1.
     * Your result so far: 13.
     * Do you want to throw again [y/n]?
     * y
     * You threw 2 and 6.
     * Your result so far: 21.
     * Do you want to throw again [y/n]?
     * n
     * 
     * Well done, your score is 21!
     * 
     * Player 2, it's your turn!
     * 
     * You threw 2 and 3.
     * Your result so far: 5.
     * Do you want to throw again [y/n]?
     * y
     * You threw 4 and 4.
     * Your result so far: 13.
     * Do you want to throw again [y/n]?
     * y
     * You threw 2 and 1.
     * Your result so far: 16.
     * Do you want to throw again [y/n]?
     * y
     * You threw 3 and 3.
     * Your result so far: 22.
     * Do you want to throw again [y/n]?
     * n
     * 
     * Well done, your score is 22!
     * 
     * Congratulations, player 2 wins!!!
     * 
     */
 

    /**
     * # BONUS
     * 
     * If you made it this far, congratulations! You now know a lot about programming!!!
     * Before the next Koan though, what about the ability to launch your game so you can play with a friend?
     * 
     * In Java, in order for a program to know what to execute, you have to create a method called 'main' somewhere.
     * The method must look exactly like this:
     * 
     *     public static void main(String[] args) {
     *       // The code to execute when the program starts
     *     }
     * 
     * For example, the Koans are running because the class 'EnglishPathToEnlightment' on which you right-click to start the Koans, contains such a method.
     * 
     * So go ahead, and create such a method in this AboutNot7Game class and call your 'not7Gamev2' method in it.
     * 
     * Then, right-click on AboutNot7Game.java in the explorer and choose 'Run Java'. Your game is running for real!
     * 
     */

}
