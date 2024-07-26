package bonuses.french;

import java.util.List;

import engine.Locale;
import engine.Sensei;
import sensei.AboutInterfacesKoans;

public class AboutInterfaces {
    /**
     * # Première implémentations d'interface
     * 
     * Écrit une classe 'numbers.AddNumbers' qui implémente l'interface 'bonuses.teachingmaterial.Combining'.
     * L'implémentation de la méthode 'combine()' devrait retourner les 2 entiers ajoutés l'un à l'autre.
     * Écrit une classe 'numbers.MultiplyNumbers' qui implémente l'interface 'bonuses.teachingmaterial.Combining'.
     * L'implémentation de la méthode 'combine()' devrait retourner les 2 entiers multipliés l'un par l'autre.
     * 
     * ---------   TIPS   --------------
     * 
     * Considère la situation suivante:
     * 
     * Tu es au milieu d'une grande pièce vide, quand un zombie t'attaque soudainement.
     * Tu n'as pas d'arme.
     * Par chance, un frêre humain vivant est debout dans le cadre de porte de la pièce.
     * "Vite!" lui crie tu. "Envoie moi quelque chose avec lequel je puisse frapper le zombie!"
     * 
     * Maintenant regardons:
     * Tu n'a pas spécifié ce que ton ami va choisir de t'envoyer (et ça ne t'intéresse pas). Ça n'a pas d'importance, tant que:
     * 
     * 1) C'est quelque chose qui est lançable (il ne peut pas te lancer le sofa)
     * 2) C'est quelque chose que tu peux attraper (pas un savon mouillé, donc...)
     * 3) C'est quelque chose avec laquelle tu peux endommager le cerveau du zombie (cela exclut donc les oreillers, etc...)
     * 
     * Cela n'a pas d'importance si tu reçoit une batte de baseball ou un marteau. Tant que les 3 conditions sont respectées, tu es sauvé.
     * 
     * En Java, tu as souvent besoin d'un objet qui posssède certaines méthodes. Et ce, peu importe sa classe.
     * Une interface est un contrat qu'un objet peut choisir de respecter, en implémentant les méthodes listées dans le contrat.
     * 
     * Par exemple, dans le jeu où la situation ci-dessus pourrait arriver, tu pourrait choisir de créer l'interface suivante:
     * 
     *     public interface Weapon {
     *         void hit(Monster monster);
     *     }
     * 
     * Maintenant, que l'arme en question soit une épée infligeant 10 points de dommage, ou un couteau n'infligeant que 4 points de dommage au monstre, le joueur pourra frapper le monstre avec.
     * 
     * Respecter le contrat d'une interface Java s'appelle 'implémenter l'interface'. Une classe peut implémenter une interface de cette manière:
     * 
     *         tu déclare que cette class va implémenter l'interface Weapon
     *                        vvvvvvvvvvvvvvvvv
     * 
     *     public class Sword implements Weapon {
     * 
     *         // Comme Sword implémente Weapon, elle doit implémenter la méthode 'hit()'.
     *         @Override // Cette étrange annotation dit à Java que cette méthode est définie ailleurs (dans l'interface)
     *         public void hit(Monster monster) {
     *             // Du code qui, par exemple, calcule et applique le dommage, applique de l'usure à l'arme, etc...
     *         }
     * 
     *     }
     * 
     * Le code dans 'hit()' peut être compliqué ou simple. Cela n'a pas d'importance: il respecte le contrat de l'interface, et donc tu peux l'appeler.
     * Par exemple, tu peux créer un objet qui permet de frapper un zombie avec le code suivant:
     * 
     *     Monster zombie = ...;               // Code qui récupère d'une façon ou d'une autre un objet représentant le zombie
     *     Weapon tossedWeapon = new Sword();  // ou 'new Hammer()' ou 'new Axe()' ou 'new WhateverImplementsWeapon()'
     *     tossedWeapon.hit(zombie);           // utilise l'interface Weapon
     * 
     * Note que le type de la variable 'tossedWeapon' est 'Weapon' et pas 'Sword'. Les interfaces, comme les classes, sont des types que tu peux utiliser pour tes variables, champs, et paramètres.
     * Puisque 'Sword' implémente 'Weapon', Java considère qu'un objet 'Sword' _est_ une 'Weapon'. Avoir une variable de type interface permet que cette variable puisse avoir pour valeur des objets de classes différentes, pourvu qu'elles implémentent toutes l'interface.
     * 
     * Regarde l'interface 'bonuses.teachingmaterial.Combining'. Elle définie une méthode qui peut être implémentée de plusieurs façons.
     * Cet exercice a pour but d'implémenter l'interface de 2 façons.
     * 
     * -------------------------------
     * 
     * Résultat attendu:
     * 
     * Le code suivant:
     * 
     *     Combining combining = new AddNumbers();
     *     System.out.println(combining.combine(3, 4));
     *     combining = new MultiplyNumbers();
     *     System.out.println(combining.combine(3, 4));
     * 
     * Devrait afficher:
     * 
     * 7
     * 12
     * 
     */

     
    /**
     * # Anonymous interface implementation
     * 
     * Write a method 'getAnonymousCombining' which returns an anonymous implementation of 'bonuses.teachingmaterial.Combining'.
     * The implementation of the combine() method devrait retourner the second number subtracted from the first.
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
     * Résultat attendu:
     * 
     * getAnonymousCombining().combine(3, 4) devrait retourner -1
     * 
     */

    
    /**
     * # Lambda methods
     * 
     * Write a method 'getLambdaCombining' which returns an lambda method implementing 'bonuses.teachingmaterial.Combining'.
     * The implementation of the combine() method devrait retourner the first number subtracted from the second.
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
     * Résultat attendu:
     * 
     * getLambdaCombining().combine(3, 4) devrait retourner 1
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
     * Résultat attendu:
     * 
     * getIsEven().test(4) devrait retourner true
     * 
     */


     public static void main(String[] args) {
        new Sensei(Locale.fr, List.of(AboutInterfacesKoans.koans)).offerKoans();
    }
}
