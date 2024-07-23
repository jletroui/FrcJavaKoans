package bonuses.english;

import java.util.List;
import java.util.function.IntPredicate;

import bonuses.teachingmaterial.Combining;
import engine.Locale;
import engine.Sensei;
import sensei.AboutInterfacesKoans;

public class AboutInterfaces {
    /**
     * # First interface implementations
     * 
     * Write a class 'numbers.AddNumbers' which implements interface 'bonuses.teachingmaterial.Combining'.
     * The implementation of the combine() method should return the 2 numbers added together.
     * Write a class 'numbers.MultiplyNumbers' which also implements interface 'bonuses.teachingmaterial.Combining'.
     * This implementation of the combine() method should return the 2 numbers multiplied together.
     * 
     * ---------   TIPS   --------------
     * 
     * Consider the following situation:
     * 
     * You are in the middle of a large, empty room, when a zombie suddenly attacks you.
     * You have no weapon.
     * Luckily, a fellow living human is standing in the doorway of the room.
     * "Quick!" you shout at him. "Throw me something I can hit the zombie with!"
     * 
     * Now consider:
     * You didn't specify (nor do you care) exactly what your friend will choose to toss; ...But it doesn't matter, as long as:
     * 
     * 1) It's something that can be tossed (He can't toss you the sofa)
     * 2) It's something that you can grab hold of (Not a wet soap)
     * 3) It's something you can use to bash the zombie's brains out (That rules out pillows and such)
     * 
     * It doesn't matter whether you get a baseball bat or a hammer - as long as it implements your three conditions, you're good.
     * 
     * In Java, you sometimes need an object that implements "conditions": one or more specific methods. But you don't care about its class. The interface is what will describe those methods.
     * We can see the interface as a kind of contract that an object would respect.
     * 
     * For example, in a game where the situation above would happen, you could create the following interface in a game:
     * 
     *     public interface Weapon {
     *         void hit(Monster monster);
     *     }
     * 
     * Now, whether your weapon is a sword inflicting 10 damage points, or a knife inflicting only 4 damage points to the monster, the player will be able to hit a monster with it.
     * 
     * Respecting the contract of a Java interface is called 'implementing the interface'. A class can implement an interface this way:
     * 
     *         you declare that this class will implement the Weapon interface
     *                        vvvvvvvvvvvvvvvvv
     * 
     *     public class Sword implements Weapon {
     * 
     *         // Since Sword implements Weapon, it must implement the hit method.
     *         @Override // This special annotation means the method is defined elsewhere (our interface in this case)
     *         public void hit(Monster monster) {
     *             // Some code computing and applying damage to the monster, applying some tear and wear on the weapon, etc...
     *         }
     * 
     *     }
     * 
     * 
     * Now, the code in hit() maybe complicated but it does not matter: it follows the contract of the interface, and you can call it.
     * For example, you could create an object allowing you to hit a zombie with the following code:
     * 
     *     Monster zombie = ...;               // Code getting the object for the zombie in the middle of the room
     *     Weapon tossedWeapon = new Sword();  // or new Hammer() or new Axe() or new WhateverImplementsWeapon()
     *     tossedWeapon.hit(zombie);           // use the Weapon interface
     * 
     * Notice the type of the variable 'tossedWeapon': it is a Weapon, not a Sword. Interfaces, like classes, are types you can use for variables, fields, and parameters.
     * Because Sword implements Weapon, Java considers that a Sword object _is_ a Weapon. Having variables and parameters using the interface type allows you to work with any object implementing that interface.
     * 
     * Take a look at the bonuses.teachingmaterial.Combining interface. It defines a method which can be implemented in various ways.
     * This exercise is about implementing that interface in 2 ways.
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * The following code:
     * 
     *     Combining combining = new AddNumbers();
     *     System.out.println(combining.combine(3, 4));
     *     combining = new MultiplyNumbers();
     *     System.out.println(combining.combine(3, 4));
     * 
     * Should display:
     * 
     * 7
     * 12
     * 
     */

     
    /**
     * # Anonymous interface implementation
     * 
     * Write a method 'getAnonymousCombining' which returns an anonymous implementation of 'bonuses.teachingmaterial.Combining'.
     * The implementation of the combine() method should return the second number subtracted from the first.
     * 
     * ---------   TIPS   --------------
     * 
     * Sometimes, it is tedious to create a class file to implement an interface just for one occasion.
     * In these situation, you can implement the interface in an anonymous class. It is anonymous, because it does not have a name.
     * That class is instantiated immediately where it is created. For example:
     * 
     *     public Weapon toss() {
     *         return new Weapon() {
     *             @Override
     *             public void hit(Monster monster) {
     *                 // Some code computing and applying damage to the monster, applying some tear and wear on the weapon, etc...
     *             }
     *         }
     *     }
     * 
     * When looking at this code, you could be tempted to believe there is a constructor for the interface Weapon, but there is not.
     * We are really creating a class, for which there exists a single object.
     * The constructor with empty parameters is the one of this nameless class.
     * We can now get and use the tossed weapon this way:
     * 
     *     Weapon tossedWeapon = toss();
     *     tossedWeapon.hit(zombie);
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * getAnonymousCombining().combine(3, 4) should return -1
     * 
     */

    
    /**
     * # Lambda methods
     * 
     * Write a method 'getLambdaCombining' which returns an lambda method implementing 'bonuses.teachingmaterial.Combining'.
     * The implementation of the combine() method should return the first number subtracted from the second.
     * 
     * ---------   TIPS   --------------
     * 
     * When an interface have only one method, there is an even shorter form to implement it. You can use what is called a 'lambda method'.
     * A 'lambda method' is a stripped down version of a method. Since our example interface 'Weapon' has only a single method 'hit()', we can use this shortcut:
     * 
     * For example:
     * 
     *     public Weapon toss() {
     *         return (monster) -> { 
     *             // Some code computing and applying damage to the monster, applying some tear and wear on the weapon, etc.. 
     *         };
     *     }
     * 
     * We can now get and use the tossed weapon this way:
     * 
     *     Weapon tossedWeapon = toss();
     *     tossedWeapon.hit(zombie);
     * 
     * The general syntax for lambda method returning void, or having a body with multiple lines:
     * 
     *     ([param1Name], [param2Name], ...) -> {
     *         // Lambda method body here
     *     }
     * 
     * If your lambda is having a single expression, you can even skip the parentheses and the 'return':
     * 
     *     ([param1Name], [param2Name], ...) -> // expression here
     * 
     * Here are some example of methods and their lambda equivalent (assuming the interface has only one method in it):
     * 
     * This interface implementation:
     * 
     *     public void sayHello() {
     *         System.out.println("hello");
     *     }
     * 
     * Can be replaced by this lambda:
     * 
     *     () -> {
     *         System.out.println("hello");
     *     }
     * 
     * This interface implementation:
     * 
     *     public int square(int x) {
     *         return x * x;
     *     }
     * 
     * Can be replaced by this lambda:
     * 
     *     (x) -> x * x
     * 
     * This interface implementation:
     * 
     *     public int min(int x, int y) {
     *         if (x < y) {
     *             return x;
     *         }
     *         return y;
     *     }
     * 
     * Can be replaced by this lambda:
     * 
     *     (x, y) -> {
     *         if (x < y) {
     *             return x;
     *         }
     *         return y;
     *     }
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * getLambdaCombining().combine(3, 4) should return 1
     * 
     */


    /**
     * # Common lambda interfaces
     * 
     * Write a method 'getIsEven' which returns a lambda method testing if an integer is even.
     * 
     * ---------   TIPS   --------------
     * 
     * Since lambda methods are so useful, a lot of interfaces already exist in the Java standard library, and we don't have to create them ourselves.
     * 
     * For example:
     * 
     * For a lambda taking no parameter, and returning nothing, {@link java.lang.Runnable}:
     * 
     *     Runnable sayHello = () -> { System.out.println("Hello"); };
     * 
     * For a lambda taking a int parameter, and returning nothing, {@link java.util.function.IntConsumer}:
     * 
     *     IntConsumer displayInt = (anInt) -> { System.out.println(anInt); };
     * 
     * The same exist for other type. For example {@link java.util.function.DoubleConsumer}:
     * 
     *     DoubleConsumer displayDouble = (aDouble) -> { System.out.println(aDouble); };
     * 
     * The reverse functions, taking nothing as a parameter, but returning something exist as well: {@link java.util.function.IntSupplier}, {@link java.util.function.DoubleSupplier}. etc...
     * 
     *     DoubleSupplier giveMePiPleeeaaase = () -> 3.14159;
     * 
     * There is also a lot of case where you would need to test a number somehow. This is where interfaces like {@link java.util.function.IntPredicate} shine:
     * 
     *     IntPredicate isPositive = (number) -> number >= 0;
     * 
     * For the exercise, you can use the modulo operator, %, which computes the remainder of an integer division:
     * 
     *     int remainder = 17 % 5; // remainder equals 2
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * getIsEven().test(4) should return true
     * 
     */


     public static void main(String[] args) {
        new Sensei(Locale.en, List.of(AboutInterfacesKoans.koans)).offerKoans();
    }
}