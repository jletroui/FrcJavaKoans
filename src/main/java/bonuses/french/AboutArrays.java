package bonuses.french;

import java.util.List;

import engine.Locale;
import engine.Sensei;
import sensei.AboutArraysKoans;

public class AboutArrays {
    /**
     * # Boucles for
     * 
     * Écris une méthod 'displayNumbers' avec un paramètre 'n' qui affiche les nombres entre 1 et n.
     * Utilise une boucle 'for' à la place d'une boucle 'while'. Utilise la forme d'incrémentation / décrémentation la plus courte.
     * 
     * ---------   TIPS   --------------
     * 
     * Pour faire quelque chose plusieurs fois, tu connais déjà la boucle 'while'.
     * La plupart du temps, les boucles 'while' ont une structure très similaire:
     * 
     *     [Initialise un compteur];
     *     while ([Condition sur la variable compteur]) {
     *         // Fait quelque chose de répétitif
     *         [Modifie le compteur à la fin de la boucle];
     *     }
     * 
     * Ex:
     * 
     *     int times = 3; // Initialise un compteur
     *     while (times > 0) { // Condition sur la variable compteur
     *         System.out.println("Toujours en train d'éxécuter"); // Fait quelque chose de répétitif
     *         times = times -1; // Modifie le compteur
     *     }
     * 
     * Comme la plulpart des boucles suivent ces étapes, il y a un raccourci en Java. Ce sont les boucles 'for', qui condensent les 3 parties ci dessus en une seule ligne.
     * 
     *     for([Initialise un compteur]; [Condition sur la variable compteur]; [Modifie le compteur]) {
     *         // Fait quelque chose de répétitif
     *     }
     * 
     * Ex:
     *     
     *     for(int times = 3; times > 0; times = times -1) {
     *         System.out.println("Toujours en train d'éxécuter"); // Fait quelque chose de répétitif
     *     }
     * 
     * Il y a également un autre raccourci: comme incrémenter et décrémenter un nombre arrive souvent, il y a une forme courte:
     * 
     *     a = a - 2; // Forme longue
     *     a -= 2;    // Forme courte
     *     b = b + 2; // Forme longue
     *     b += 2;    // Forme courte
     * 
     * Et si on incrément / décrémente de 1, il y a une forme encore plus courte:
     * 
     *     a -= 1; // Forme courte
     *     a--;    // Forme la plus courte
     *     b += 1; // Forme courte
     *     b++;    // Forme la plus courte
     * 
     * En utilisant ceci, la boucle 'for' devient encore plus condensée:
     * 
     *     for(int times = 3; times > 0; times--) {
     *         System.out.println("Toujours en train d'éxécuter"); // Fait quelque chose de répétitif
     *     }
     * 
     * -------------------------------
     * 
     * Résultat attendu:
     * 
     * displayNumbers(3) devrait afficher:
     * 
     * 1
     * 2
     * 3
     * 
     */


     /**
     * # Premier élément d'un tableau
     * 
     * Écris une méthode 'first' qui prend un tableau d'entiers 'array' en paramètre, et qui retourne le premier élément de ce tableau.
     * 
     * ---------   TIPS   --------------
     * 
     * Parfois, nous avons besoin de stocker plusieurs valeurs d'un même type. Par exemple: pour enregistrer le niveau de batterie d'un robot à chaque seconde lors des 2m30s d'un match (150 valeurs de type double).
     * Avoir 150 variables 'double' ne serait pas très pratique dans notre programme. Nous pouvons à la place créer une seule variable qui stocke les 150 valeurs d'un coup.
     * 
     * Un tel type s'appelle un 'tableau' ('array' en anglais). Pour créer un tableau avec des valeurs d'un type 'SomeType', contenant n valeurs, tu peux écrire:
     * 
     *     SomeType[] myArray = new SomeType[n];
     * 
     * Ex:
     * 
     *     double[] batteryLevels = new double[150];
     *  
     * Un tableau stocke les valeurs séquentiellement. Tu peux accéder à une valeur (appelée un 'élément du tableau') en utilisant sa position appelée un 'index'. Les index dans les tableaux commencent à 0.
     * Tu peux spécifier un index à l'intérieur de '[' et ']'.
     * Par exemple, pour afficher le 3e élément de 'batteryLevels':
     * 
     *     System.out.println(batteryLevels[2]);
     * 
     * De la même façon, si tu veux stocker une valeur dans le 2e élément:
     * 
     *     bateryLevels[1] = 12.8;
     * 
     * -------------------------------
     * 
     * Résultat attendu:
     * 
     * Le code suivant
     * 
     *     var a = new int[2];
     *     a[0] = 10;
     *     a[1] = 20;
     *     System.out.println(first(a));
     * 
     * Devrait afficher:
     * 
     * 10
     * 
     */


     /**
     * # Dernier élément d'un tableau
     * 
     * Écris une méthode 'last' qui prend un tableau d'entiers 'array' en paramètre, et qui retourne le dernier élément de ce tableau.
     * 
     * ---------   TIPS   --------------
     * 
     * Pour connaître la taille d'un tableau que tu n'a pas créé toi même, tu peux utilisant son champ 'length':
     * 
     *     double[] batteryLevels = new double[150];
     *     System.out.println(batteryLevels.length); // Display 150
     * 
     * -------------------------------
     * 
     * Résultat attendu:
     * 
     * Le code suivant
     * 
     *     var a = new int[2];
     *     a[0] = 10;
     *     a[1] = 20;
     *     System.out.println(last(a));
     * 
     * Devrait afficher:
     * 
     * 20
     * 
     */


     /**
     * # Trouver un élément
     * 
     * Écris une méthode 'findFirst' qui prend en paramètres un tableau d'entier 'array' et un entier 'n'.
     * Elle retourne l'index du premier élément qui a la valeur n.
     * Si l'élément n'est pas trouvé, elle retourne -1.
     * 
     * -------------------------------
     * 
     * Résultat attendu:
     * 
     * Le code suivant
     * 
     *     var a = new int[2];
     *     a[0] = 10;
     *     a[1] = 20;
     *     a[2] = 20;
     *     System.out.println(findFirst(a, 20));
     * 
     * Devrait afficher:
     * 
     * 1
     * 
     */


     /**
     * # Trouver un élément à la fin
     * 
     * Écris une méthode 'findLast' qui prend en paramètres un tableau d'entier 'array' et un entier 'n'.
     * Elle retourne l'index du dernier élément qui a la valeur n.
     * Si l'élément n'est pas trouvé, elle retourne -1.
     * 
     * -------------------------------
     * 
     * Résultat attendu:
     * 
     * Le code suivant
     * 
     *     var a = new int[2];
     *     a[0] = 10;
     *     a[1] = 20;
     *     a[2] = 20;
     *     System.out.println(findLast(a, 20));
     * 
     * Devrait afficher:
     * 
     * 2
     * 
     */


     /**
     * # Trouver l'élément le plus petit
     * 
     * Écris une méthode 'min' qui prend un tableau d'entiers 'array' en paramètre.
     * Elle retourne l'entier le plus petit dans le tableau.
     * Si le tableau est vide, elle retourne la valeur Integer.MAX_VALUE, qui est le plus grand entier possible.
     * 
     * -------------------------------
     * 
     * Résultat attendu:
     * 
     * Le code suivant
     * 
     *     var a = new int[2];
     *     a[0] = 10;
     *     a[1] = 2;
     *     a[2] = 20;
     *     System.out.println(min(a));
     * 
     * Devrait afficher:
     * 
     * 2
     * 
     */


     /**
     * # Trouver l'élément le plus petit, revisité.
     * 
     * En utilisant une boucle 'for :', écris une méthode 'min2' qui prend un tableau d'entier 'array' en paramètre.
     * Elle retourne l'entier le plus petit dans le tableau.
     * Si le tableau est vide, elle retourne la valeur Integer.MAX_VALUE, qui est le plus grand entier possible.
     * 
     * ---------   TIPS   --------------
     * 
     * Difficile à croire, mais quand il s'agit de faire une boucle à travers tous les élément d'un tableau, il y a encore un autre raccourcis.
     * 
     * Comme faire une boucle à travers tous les élément d'un tableau est très courant, il existe cette syntaxe:
     * 
     *     for(type variableName: someArray) {
     *         // do something with variableName
     *     }
     * 
     *  Par exemple, pour afficher tous les élément d'un tableau:
     * 
     *     for(double level: batteryLevels) {
     *         System.out.println(level);
     *     }
     * 
     * Note: cependant, cela ne fonctionne que si tu n'as pas besoin de l'index de l'élément. Dans le cas contraire, tu es obligé d'utiliser une boucle 'for' normale pour avoir l'index.
     * 
     * -------------------------------
     * 
     * Résultat attendu:
     * 
     * Pareil que pour la méthode min2() , mais en utilisant une boucle 'for :' à la place d'une boucle 'for' normale.
     * 
     */


     /**
     * # Calculer la somme
     * 
     * Écris une méthode 'sum' qui prend un tableau d'entier 'array' en paramètre.
     * Elle retourne la somme de tous les entiers du tableau.
     * Si le tableau est vide, elle retourne 0.
     * 
     * -------------------------------
     * 
     * Résultat attendu:
     * 
     * Le code suivant
     * 
     *     var a = new int[2];
     *     a[0] = 10;
     *     a[1] = 2;
     *     a[2] = 20;
     *     System.out.println(sum(a));
     * 
     * Devrait afficher:
     * 
     * 32
     * 
     */


     /**
     * # Calculer la moyenne
     * 
     * Écris une méthode 'avg' qui prend un tableau d'entier 'array' en paramètre.
     * Elle retourne la moyenne de tous les entiers du tableau (sous forme de double).
     * Si le tableau est vide, elle retourne 0.0.
     * 
     * -------------------------------
     * 
     * Résultat attendu:
     * 
     * Le code suivant
     * 
     *     var a = new int[2];
     *     a[0] = 10;
     *     a[1] = 3;
     *     a[2] = 20;
     *     System.out.println(avg(a));
     * 
     * Devrait afficher:
     * 
     * 11
     * 
     */


     /**
     * # Remplir un tableau
     * 
     * Écris une méthode 'fill' qui prend un entier 'size' et un entier 'value' en paramètres.
     * Elle retourne un tableau d'entier de taille 'size', dont tous les éléments valent 'value'.
     * 
     * -------------------------------
     * 
     * Résultat attendu:
     * 
     * Le code suivant
     * 
     *     fill(3, 20);
     * 
     * Devrait retourner un tableau avec 3 éléments, tous les éléments valant 20.
     * 
     */

     
     /**
     * # Créer une série
     * 
     * Écris une méthode 'serie' qui prend un entier 'size' en paramètre.
     * Elle retourne un tableau d'entier avec ce nombre d'éléments.
     * Le premier élément vaut 1. Le 2e élément vaut 2. Le 3e vaut 3, etc...
     * 
     * -------------------------------
     * 
     * Résultat attendu:
     * 
     * Le code suivant
     * 
     *     serie(4);
     * 
     * Devrait retourner le tableau [1, 2, 3, 4]. 
     * 
     */


     /**
     * # Intervertir 2 éléments
     * 
     * Écris une méthode 'switchFirst2' qui prend un tableau 'array' en paramètre.
     * Si le tableau a 2, et seulement 2 éléments, la méthode les intervertit.
     * Sinon, la méthode ne fait rien.
     * 
     * -------------------------------
     * 
     * Résultat attendu:
     * 
     * Après le code suivant:
     * 
     *     var a = new int[2];
     *     a[0] = 10;
     *     a[1] = 3;
     *     switchFirst2(a);
     * 
     * a devrait valoir [3, 10]. 
     * 
     */


     /**
     * # Inverser un tableau
     * 
     * Écris une méthode 'reverse' qui prend un tableau 'array' en paramètre.
     * Elle renvoit un nouveau tableau avec les mêmes éléments que 'array', mais dans l'ordre inverse.
     * 
     * -------------------------------
     * 
     * Résultat attendu:
     * 
     * Après le code suivant:
     * 
     *     var a = new int[2];
     *     a[0] = 10;
     *     a[1] = 3;
     *     a[2] = 20;
     *     reverse(a);
     * 
     * reverse(a) devrait retourner le tableau [20, 3, 10]. 
     * 
     */


     public static void main(String[] args) {
        new Sensei(Locale.fr, List.of(AboutArraysKoans.koans)).offerKoans();
    }
}
