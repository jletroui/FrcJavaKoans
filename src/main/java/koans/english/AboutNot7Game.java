package koans.english;

import static engine.Helpers.random;

public class AboutNot7Game {
    /**
     * These koans are a bit special. You will gradually code everything necessary for a 2 players game called 'Not 7!'.
     * 
     * Each player has only one turn. On its turn, the player will throw 2 dice as many times as they wish.
     * 
     * However, if at any time the result for one throw is 7, the player loose immediately.
     * 
     * Otherwise, when the player decide to pass and not throw anymore, their score is the sum of all the dice results.
     * 
     * Here is a game example, in the console:
     * 
     * Player 1, it's your turn!
     * 
     * You threw 4 and 5. Your result so far: 9
     * Do you want to throw again [y/n]? y
     * You threw 3 and 1. Your result so far: 13
     * Do you want to throw again [y/n]? y
     * You threw 2 and 6. Your result so far: 21
     * Do you want to throw again [y/n]? n
     * 
     * Well done, your score is 21!
     * 
     * Player 2, it's your turn!
     * 
     * You threw 2 and 3. Your result so far: 5
     * Do you want to throw again [y/n]? y
     * You threw 4 and 4. Your result so far: 13
     * Do you want to throw again [y/n]? y
     * You threw 2 and 1. Your result so far: 16
     * Do you want to throw again [y/n]? y
     * You threw 3 and 3. Your result so far: 22
     * Do you want to throw again [y/n]? n
     * 
     * Well done, your score is 22!
     * 
     * Congratulations, player 2 wins!!!
     * 
     * 
     * 
     * Here is an other game example, where one unlucky player is throwing a 7:
     * 
     * Player 1, it's your turn!
     * 
     * You threw 3 and 2. Your result so far: 5
     * Do you want to throw again [y/n]? y
     * You threw 5 and 6. Your result so far: 16
     * Do you want to throw again [y/n]? n
     * 
     * Well done, your score is 16!
     * 
     * Player 2, it's your turn!
     * 
     * You threw 2 and 3. Your result so far: 5
     * Do you want to throw again [y/n]? y
     * You threw 2 and 5. That's a 7, you loose!
     * 
     * Congratulations, player 1 wins!!!
     */


    /**
     * Write a method 'die6()' returning the result of a 6-face die throw as an integer.
     * 
     * ---------   TIPS --------------
     * 
     * To generate a random number between 0 and 1, use the method random(). Ex:
     * 
     *     double someNumber = random(); // someNumber can be anything between 0 and 1
     * 
     * Try to think about what computations would be needed to go from a number between 0 and 1 to a number between 0 and 6.
     * 
     * Now, the result of the computation will be a decimal number, and you will need to round it to the next integer.
     * For example, if the result of your previous computation is 3.2, you will need to round it to 4. You can do that with:
     * 
     *     double rounded = Math.ceil(3.2) // rounded is 4.0
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


    /**
     * Write a method 'die6Sum(int times)' throwing a 6-face die times times and returning the sum of the result.
     * 
     * ---------   TIPS --------------
     * 
     * Use the die6 method in a loop.
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * die6Sum(2) should return an int between 2 and 12 randomly
     * 
     */

     
}
