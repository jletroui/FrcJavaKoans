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
     * Écris une méthode 'getAnonymousCombining' qui retourne une implémentation anonyme de 'bonuses.teachingmaterial.Combining'.
     * L'implémentation de la méthode combine() devrait retourner le 2ème nombre soustrait du 1er.
     * 
     * ---------   TIPS   --------------
     * 
     * Parfois, créer un fichier et une classe publique représente beaucoup de travail lorsque l'on implémente une interface simple, et qu'on l'utilise à un seul endroit dans le code.
     * Dans cette situation, tu peux implémenter une interface dans une classe anonyme. La classe est anonyme car elle n'a pas de nom.
     * Cette classe est instantiée en un object immédiatement où elle est définie. Par exemple:
     * 
     *     public Weapon toss() {
     *         return new Weapon() {
     *             @Override
     *             public void hit(Monster monster) {
     *                 // Du code qui calcule et applique le dommage, applique de l'usure à l'arme, etc...
     *             }
     *         }
     *     }
     * 
     * Quand on considère ce code, on pourrait être tenté de croire qu'il y a un constructeur pour l'interface 'Weapon', mais évidemment, une interface n'a pas de constructeur, puisque c'est un simple "contrat".
     * Ici, nous créons vraiment une classe, pour laquelle le seul endroit où des objets vont être créés est la méthode 'toss()'.
     * Le constructeur sans paramètre est celui de cette classe sans nom.
     * Nous pouvons maintenant utiliser cette arme envoyée de cette manière:
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
     * # Méthodes lambda
     * 
     * Écris une méthode 'getLambdaCombining' qui retourne une méthode lambda implémentant 'bonuses.teachingmaterial.Combining'.
     * L'implémentation de la méthode combine() devrait retourner le 1er nombre soustrait du 2ème.
     * 
     * ---------   TIPS   --------------
     * 
     * Quand une interface a une seule méthode, il exist une forme encore plus courte pour l'implémenter. Tu peux créer ce que l'on appelle une "méthode lambda".
     * Une "méthode lambda" est une version raccourcie d'une méthode. Comme notre interface exemple 'Weapon' ne contient qu'une seule méthode 'hit()', nous pouvons utiliser ce raccourci.
     * 
     * Par example:
     * 
     *     public Weapon toss() {
     *         return (monster) -> { 
     *             // Du code qui calcule et applique le dommage, applique de l'usure à l'arme, etc...
     *         };
     *     }
     * 
     * Nous pouvons maintenant utiliser cette arme envoyée de cette manière:
     * 
     *     Weapon tossedWeapon = toss();
     *     tossedWeapon.hit(zombie);
     * 
     * La syntaxe générale pour une méthode lambda dont l'implémentation est constituée de plusieurs lignes est la suivante:
     * 
     *     ([nomParam1], [nomParam2], ...) -> {
     *         // Corps de la méthode lambda ici
     *     }
     * 
     * Si ta lambda a une seule expression, tu peux même enlever les accolades et l'instruction 'return':
     * 
     *     ([nomParam1], [nomParam2], ...) -> // expression ici
     * 
     * Voici quelques exemples de méthodes et leur équivalent lambda (en supposant que l'interface n'a qu'une seule de ces méthodes dans son contrat):
     * 
     * L'implémentation de cette méthode:
     * 
     *     public void sayHello() {
     *         System.out.println("hello");
     *     }
     * 
     * Peut être remplacée par cette lambda:
     * 
     *     () -> System.out.println("hello")
     * 
     * L'implémentation de cette méthode:
     * 
     *     public int square(int x) {
     *         return x * x;
     *     }
     * 
     * Peut être remplacée par cette lambda:
     * 
     *     (x) -> x * x
     * 
     * L'implémentation de cette méthode:
     * 
     *     public int min(int x, int y) {
     *         if (x < y) {
     *             return x;
     *         }
     *         return y;
     *     }
     * 
     * Peut être remplacée par cette lambda:
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
     * # Interfaces lambda communes
     * 
     * Écris une méthode 'getIsEven' qui retourne une méthode lambda testant si un entier est pair.
     * 
     * ---------   TIPS   --------------
     * 
     * Note: la notation {@link java.lang.String} permet de montrer un lien vers une classe dans un commentaire. Pour voir la classe, tu peux faire un [CTRL] + clic sur le nom de la classe.
     * 
     * Comme les méthodes lambdas sont si pratiques, beaucoup d'interfaces simples existent déjà dans la librairie standard de Java. Nous n'avons pas besoin de les créer nous même.
     * Par exemple, une interface avec une méthode ayant la même signature que 'combine()' existe déjà. C'est {@link java.util.function.IntBinaryOperator}.
     * 
     * D'autres exemples:
     * 
     * Pour une lambda sans paramètre, et ne retournant rien, {@link java.lang.Runnable}:
     * 
     *     Runnable sayHello = () -> System.out.println("Hello");
     * 
     * Pour une lambda prenant un paramètre 'int', et ne retournant rien, {@link java.util.function.IntConsumer}:
     * 
     *     IntConsumer displayInt = (anInt) -> System.out.println(anInt);
     * 
     * La même chose existe pour d'autres types de paramètres. Par exemple {@link java.util.function.DoubleConsumer}:
     * 
     *     DoubleConsumer displayDouble = (aDouble) -> System.out.println(aDouble);
     * 
     * Les methodes inverses, ne prenant aucun paramètre, mais retournant un résultat, existent aussi: {@link java.util.function.IntSupplier}, {@link java.util.function.DoubleSupplier}. etc...
     * 
     *     DoubleSupplier giveMePiPleeeaaase = () -> 3.14159;
     * 
     * Un cas courant est lorsque nous avons besoin de tester si un nombre respecte une condition. C'est là que les interfaces "prédicat" brillent, comme {@link java.util.function.IntPredicate}:
     * 
     *     IntPredicate isPositive = (number) -> number >= 0;
     * 
     * Pour cet exercice, tu peux utiliser l'opérateur modulo, %, qui calcule le reste d'une division entière:
     * 
     *     int remainder = 17 % 5; // remainder vaut 2
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
