package koans.french;

import static engine.Helpers.readLine;

public class AboutConsoleAndVariables {
    /**
     * Afficher 'Hello!' dans la console.
     * 
     * ---------   INDICES   --------------
     * 
     * En Java, toutes les instructions de code sur une ligne doivent se terminer avec un ';'. Ex:
     * 
     *      System.out.println("Pomme");
     * 
     * Tu peux utiliser la méthode System.out.println([une valeur]) pour afficher une valeur dans la console.
     * 
     * Tu peux dire à Java qu'une valeur est un texte en l'entourant par des guillements. Ex:
     * 
     *      "Ceci est du texte"
     * 
     * -------------------------------
     * 
     * Résultat attendu dans la console:
     * 
     * Hello!
     * 
     */    
    public static void sayHelloInConsole() {

    }

    /**
     * Fait calculer 2 + 2 à Java, et affiche le résultat dans la console.
     * 
     * ---------   INDICES   --------------
     * 
     * En Java, tu peux calculer des expressions arithmétiques. Ex:
     * 
     *      3 + 4
     *      3 * 4
     *      3 / 4
     *      3 - 4
     *      -3
     *      -3 * (4 - 2)
     * 
     * Tu peux utiliser la méthode System.out.println([une expression]) pour afficher le résultat dans la console.
     * 
     * -------------------------------
     * 
     * Résultat attendu dans la console:
     * 
     * 4
     * 
     */
    public static void computeTwoAndTwo() {

    }

    /**
     * Calcule 2 + 2 et stocke la valeur dans une variable. Affiche la valeur de la variable.
     * 
     * ---------   INDICES   --------------
     * 
     * Une variable est un morceau d'information avec un nom. On peut récupérer cette information en utilisant son nom.
     * En conséquence, pour déclarer une variable, tu as besoin de lui donner un nom et une valeur.
     * Mais tu as aussi besoin de dire à Java, qui est un peu tatillon, quel genre d'information la variable va contenir (son 'type').
     * Par exemple, quand l'information est un nombre entier, son type est appelé 'int' en Java.
     * En appliquant tout cela, nous pouvons créer, par exemple, une variable pour le nombre de pattes d'un cochon:
     * 
     *      int pigNbOfLegs = 4;
     *       ^       ^        ^
     *      type    nom     valeur
     * 
     * The value can be an arithmetic expression. For example:
     * La valeur peut aussi être une expression arithmétique:
     * 
     *      int pigNbOfLegs = 1 + 1 + 1 + 1;
     * 
     * À chaque fois que vous utilisez le nom d'une variable, Java va le remplacer par la valeur de la variable quand le programme s'exécute.
     * Par exemple, pour afficher la valeur contenue dans la variable ci dessus:
     * 
     *      System.out.println(pigNbOfLegs);
     *                              ^
     *          le nom 'pigNbOfLegs' va être remplacé par la valeur de la variable.
     *
     * Dans notre exemple, c'est donc équivalent à (car la valeur de pigNbOfLegs est 4): 
     * 
     *      System.out.println(4);
     * 
     * -------------------------------
     * 
     * Résultat attendu dans la console:
     * 
     * 4
     */
    public static void createAndDisplayAVariable() {

    }

    /**
     * Crée une variable de type texte appelée 'greeting' avec la valeur 'Hello!', et affiche la.
     * 
     * ---------   INDICES   --------------
     * 
     * En Java, le type pour des valeurs textuelles est appelé 'String'.
     * 
     * -------------------------------
     * 
     * Résultat attendu dans la console:
     * 
     * Hello!
     * 
     */
    public static void createAndDisplayAStringVariable() {

    }


    /**
     * Demande un nom dans la console. Laisse l'utilisateur répondre. Stocke la réponse dans une variable de type 'String'. Affiche 'Ton nom est:', puis la réponse.
     * 
     * ---------   INDICES   --------------
     * 
     * Tu peux lire du texte tapé dans la console avec la méthode readLine(). Cette méthode _retourne_ la valeur tapée par l'utilisateur dans la console.
     * 'Une méthode retourne un résultat' veut dire que Java va remplacer l'appel à la méthode par le résultat retourné, exactement comme pour une variable.
     * 
     * Par exemple, disons que l'utilisateur va taper 'orange', puis la touche [ENTRÉE] dans la console. Alors:
     * 
     *     String someText = readLine();
     *                           ^
     *      cet appel de méthode va être remplacé par son résultat
     * 
     * C'est équivalent à:
     * 
     *     String someText = "orange";
     * 
     * -------------------------------
     * 
     * Résultat attendu dans la console:
     * 
     * Quel est ton nom?
     * [Tape un nom]
     * Ton nom est:
     * [Le nom tapé ci dessus]
     * 
     */
    public static void askAndDisplayNameInConsole() {

    }

    /**
     * La même chose que 'askAndDisplayNameInConsole', mais en affichant le résultat sur une seule ligne.
     * 
     * ---------   INDICES   --------------
     * 
     * Tu vas avoir à créer une valeur textuelle en collant 2 valeurs textuelles. Coller 2 textes ensemble s'appelle 'concaténer'.
     * En Java, tu peux faire ça avec l'opérateur '+':
     * 
     *     String glued = "abc" + "def";
     *              ^
     *    la valeur de glued est "abcdef"
     * 
     * Attention! Ne pas confondre l'opérateur concaténation avec l'opérateur addition. Par exemple, cela ne peut fonctionner:
     * 
     *     4 + "abc"
     *       ^
     *     Erreur: parceque la valeur à gauche de l'opérateur est un nombre, le '+' ici est l'addition, et pas la concaténation.
     *     Et donc, Java s'attend à un nombre à droite de l'opérateur également, mais "abc" n'est pas un nombre, c'est un texte.
     * 
     * Note: par contre l'inverse fonctionne:
     * 
     *     String glued = "abc" + 4;
     *               ^
     *     la valeur de glued est "abc4"
     * 
     * Pourquoi? Parceque Java peut convertir automatiquement n'importe quelle valeur en texte, incluant les nombre. Donc dans ce cas, c'est équivalent à:
     * 
     *     String glued = "abc" + "4";
     *                              ^
     *               Le nombre est converti automatiquement
     * 
     * -------------------------------
     * 
     * Résultat attendu dans la console:
     * 
     * Quel est ton nom?
     * [Tape un nom]
     * Ton nom est: [Le nom tapé ci dessus]
     * 
     */
    public static void askAndDisplayNameOnASingleLineInConsole() {

    }

    /**
     * Demande un nom, puis un âge. Affiche les 2 sur une même ligne.
     * 
     * ---------   INDICES   --------------
     * 
     * Tu peux concaténer plus que 2 textes ensemble. Ex:
     * 
     *      String glued = "abc" + "def" + "ghi";
     *               ^
     *       la valeur de glued est "abcdefghi"
     * 
     * -------------------------------
     * 
     * Résultat attendu dans la console:
     * 
     * Quel est ton nom?
     * [Tape un nom]
     * Quel âge as tu?
     * [Tape un âge]
     * Ton nom est: [Le nom tapé ci dessus] et ton âge est [L'âge tapé ci dessus] ans.
     * 
     */
    public static void askNameAndAgeInConsole() {

    }

    
    /**
     * Demande un âge. Affiche l'âge dans 5 ans.
     * 
     * ---------   INDICES   --------------
     * 
     * Comme l'utilisateur peut taper n'importe quoi dans la console, la valeur retournée par readLine() est toujours un texte.
     * Donc pour calculer l'âge dans 5 ans, il va te falloir d'abord convertir la réponse de l'utilisateur en un nombre.
     * Tu peux convertir un nombre sous forme de texte en un entier comme ceci:
     * 
     *      String nbOfOrangesAsText = "3";
     *      int nbOfOrangesAsNumber = Integer.parseInt(nbOfOrangesAsText);
     *                   ^
     *         la valeur est 3, en nombre
     * 
     * Pour le calcul, tu peux soit créer une nouvelle variable à afficher plus tard:
     * 
     *     int nbOfOragesAfterIAteOne = nbOfOrangesAsNumber - 1;
     * 
     * Ou faire le calcul directement à l'endroit où tu va l'afficher, en utilisant des parenthèses:
     * 
     *     System.out.println("Oranges left: " + (nbOfOrangesAsNumber - 1));
     * 
     * -------------------------------
     * 
     * Résultat attendu dans la console:
     * 
     * Quel âge as tu?
     * [Tape un âge]
     * Dans 5 ans, tu auras [L'âge tapé ci dessus + 5] ans.
     * 
     */
    public static void computeAgeIn5YearsConsole() {

    }
}
