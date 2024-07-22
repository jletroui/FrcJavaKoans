package bonuses.english;

import java.util.List;

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
     *             // some code computing and applying damage to the monster, applying some tear and wear on the weapon, etc...
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


     public static void main(String[] args) {
        new Sensei(Locale.en, List.of(AboutInterfacesKoans.koans)).offerKoans();
    }
}
