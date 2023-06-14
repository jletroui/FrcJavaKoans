package koans.french;

import static engine.Helpers.readLine;

public class AboutMethods {
    /**
     * Demande un âge. Affiche l'âge dans 5 ans.
     * Demande un âge une deuxième fois. Affiche cet âge dans 10 ans.
     * Demande un âge une troisième fois. Affiche cet âge dans 20 ans.
     * 
     * -------------------------------
     * 
     * Résultat attendu dans la console:
     * 
     * Quel âge as tu?
     * [Tape un âge]
     * Dans 5 ans, tu auras [L'âge tapé ci dessus + 5] ans.
     * Quel âge as tu?
     * [Tape un âge]
     * Dans 10 ans, tu auras [Le nouvel âge tapé ci dessus + 10] ans.
     * Quel âge as tu?
     * [Tape un âge]
     * Dans 20 ans, tu auras [Le 3e âge tapé ci dessus + 20] ans.
     * 
     */
    public static void computeAgeIn5And10And20YearsConsole() {

    }

    /**
     * Faisons une pause. Est-ce que ce n'était pas un peu pénible d'écrire 3 fois presque le même code?
     * Fais la même chose cette fois ci, mais en appelant 3 fois une nouvelle méthode que tu vas créer.
     * 
     * ---------   INDICES   --------------
     * 
     * Une méthode est un mini programme. Tu sais déjà ce qu'une méthode est, car chaque exercice que tu as terminé t'as fait écrire du code dans le _corps_ d'une méthode.
     * Il est temps de créer une méthode complète toi même, et de l'appeler.
     * Pour créer une méthode, il faut d'abord se placer au bon endroit dans la classe, entre la '{' et la '}' de la classe, mais en dehors d'une méthode existente. Ex:
     * 
     *                                                                        <-- mauvais endroit, en dehors de la classe
     *     public class AboutMethods {
     *                                                                        <-- bon endroit 
     *         public static void computeAgeIn5And10And20YearsConsole() {
     *                                                                        <-- mauvais endroit, dans une autre méthode
     *         }
     *                                                                        <-- bon endroit 
     *     }
     *                                                                        <-- mauvais endroit, en dehors de la classe
     * 
     * Une fois l'endroit choisi, tu vas devoir écrire la _signature_ de la méthode, qui va donner à Java des informations cruciales sur ta méthode.
     * Pour l'instant, nous n'allons nous préoccuper que de 2 informations: le _nom_ de la méthode et ses _paramètres_.
     * Tu as besoin de donner un nom à ta méthode, pour la même raison que tu dois donner un nom à tes variable: Java va savoir à quoi tu réfère lorsque tu utilise ce nom.
     * Les paramètres sont un moyen pour le programme qui appelle la méthode de configurer ce qu'il va se passer dans la méthode. Voici comment on déclare une signature de méthode:
     * 
     *     public static void [nom de la méthode]([type paramètre 1] [nom paramètre 1], [type paramètre 2] [nom paramètre 2], ...) {
     *
     *     }
     * 
     * La signature commence par 'public static void', dont nous allons ignorer la signification pour le moment. Ensuite vient le nom de la méthode.
     * C'est très important de bien choisir un nom qui renseigne le lecteur de ton code sur la raison qui t'as poussé à écrire cette méthode (comparé à, par exemple, ce que la méthode éxécute à l'intérieur).
     * Par exemple, disons que nous voulons créer une méthode qui prend un texte en paramètre, et l'affiche dans la console, suivi de 3 points d'exclamation, comme si la méthode criait le texte.
     * Évite des noms du style 'displayInConsoleAndAddExclamations'. Le lecteur voit déjà que c'est ce que la méthode fait en lisant son code.
     * Préfère des noms qui expriment l'intention en arrière de la méthode, dans un anglais le moins abstrait possible. Par exemple: 'yell'.
     * 
     * Après le nom viennent les paramètres. La liste des paramètres est donnée entre parenthèses. Si ta méthode n'a pas de paramètres, écrit simplement '()' après son nom.
     * Si tu as plus d'un paramètre, sépare les avec des ','.
     * Un paramètre est un peu comme une variable pour la méthode, avec la différence que ce n'est pas la méthode qui va décider de sa valeur.
     * La méthode va plutôt _recevoir_ la valeur de la part de la partie du programme qui l'appelle.
     * Donc le/la programm·euse·eur ne sait pas d'avance, lorsqu'iel écrit sa méthode, quelle sera la valeur. Et c'est ok.
     * La méthode peut par contre utiliser la valeur, comme avec une variable normale. Dans notre exemple, la méthode qui 'crie' va avoir besoin de savoir quoi crier.
     * Le type du paramètre va donc être du texte, soit 'String' en Java.
     * Le nom du paramètre devra rendre facile de se souvenir à quoi il sert. Par exemple: 'textToYell'.
     * 
     * En appliquant tout ça, c'est ainsi que nous écririons une telle méthode:
     * 
     *                 nom méthode  type param   nom param
     *                        v       v           v
     *     public static void yell(String textToYell) {
     *          System.out.println(textToYell + " !!!");  
     *     }
     *                                ^
     *       À l'intérieur de la méthode, le paramètre est utilisé comme une variable normale
     * 
     * Maintenant, comment éxécuter la méthode 'yell'? Dans une autre méthod, tu peux l'appeler. Cela veut dire que tu peux demander à Java d'éxécuter le code du corps (l'intérieur) de la méthode.
     * Pour cela, tu dois donner à Java som nom et les valeurs des paramètres. Les valeurs sont entre parenthèses. Ex:
     * 
     *     public static void otherMethod() {
     *         yell("Java est trop cool");
     *     }
     *          ^            ^
     *    nom méthode   valeur paramètre 1
     * 
     * Pour cet exercice, tu va créer une méthode qui demande et calcule un âge dans un certain nombre d'années.
     * Ce nombre d'années sera différent à chaque appel, et devra donc être un paramètre de la méthode.
     * Dans la méthode 'computeAgeIn5And10And20YearsConsoleWithMethod()', tu devras simplement appeler la méthode 3 fois:
     *   - une fois avec la valeur 5
     *   - une fois avec la valeur 10
     *   - et finalement une fois avec la valeur 20
     * 
     * -------------------------------
     * 
     * Résultat attendu dans la console:
     * 
     * Quel âge as tu?
     * [Tape un âge]
     * Dans 5 ans, tu auras [L'âge tapé ci dessus + 5] ans.
     * Quel âge as tu?
     * [Tape un âge]
     * Dans 10 ans, tu auras [Le nouvel âge tapé ci dessus + 10] ans.
     * Quel âge as tu?
     * [Tape un âge]
     * Dans 20 ans, tu auras [Le 3e âge tapé ci dessus + 20] ans.
     * 
     */
    public static void computeAgeIn5And10And20YearsConsoleWithMethod() {

    }

    /**
     * Écrit une méthode qui a un entier pour paramètre, et retourne le carré de ce nomre.
     * 
     * ---------   INDICES   --------------
     * 
     * Jusqu'à maintenant, les méthode que tu as écrite 'faisait des choses', comme afficher quelque chose dans la console.
     * Mais elles ne communiquaient aucun résultat au reste du programme.
     * En programmation, il est très utile, non seulement de recevoir des informations du reste du programme (via les paramètres), mais aussi de renvoyer de l'informaiton au reste du programme.
     * Pour renvoyer de l'information au reste du programme, une méthode peut retourner une, et une seule valeur appelée 'la valeur de retour'.
     * Jusqu'à maintenant, les méthodes commencaient de cette façon:
     * 
     *     public static void [name of the method]()
     *                     ^
     *                 le type de retour
     * 
     * 'void' veut dire que la méthode ne retourne aucune information.
     * Pour que la méthode puisse retourner quelque chose, il faut dire à Java quel type de valeur nous voulons faire retourner à la méthode.
     * Par exemple, disons que nous voulons faire une méthode qui divise un entier par 2, et retourne le résultat:
     * 
     *     public static int half(int number) { ... }
     *                    ^
     *           Maintenant retourne un entier
     * 
     * Cependant, cela indique juste à Java quel type de valeur la méthode va retourner.
     * Comment demande-t-on à la méthode de retourner une valeur? En utilisant le mot clef 'return':
     * 
     *     public static int half(int number) {
     *         return number / 2;
     *     }
     *                    ^
     *            une expression de type 'entier'
     * 
     * Finalement, comment utiliser le résultat retourné par la méthode? Nous pouvons appeler la méthode comme n'importe quelle autre:
     * 
     *     System.out.println(half(10));
     * 
     * Et comme vu précédemment, Java, après avoir appelé la méthode, va remplacer l'appel par le résultat de la méthode.
     * Dans cet exemple, ce sera équivalent à écrire:
     * 
     *     System.out.println(5);
     * 
     * Dans les prochains exercices cependant, tu ne vas pas appeler test méthode, juste les écrire pour le maître.
     * 
     * Pour cet exercice, la signature de la méthode a déjà été écrite pour toi, tu n'as qu'à modifier le corps de la méthode pour respecter l'objectif de l'exercice.
     * 
     * -------------------------------
     * 
     * Résultat attendu:
     * 
     * square(3) devrait retourner 9
     * 
     */
    public static int square(int number) {
        return 0;
    }    

    /**
     * Écrit une méthode appelée 'opposite' qui prend un paramètre entier, et retourne l'opposé de cet entier.
     * 
     * ---------   INDICES   --------------
     * 
     * Pour cet exercice, tu dois écrire la signature de la méthode toi même.
     * 
     * -------------------------------
     * 
     * Résultat attendu:
     * 
     * opposite(2) devrait retourner -2
     * 
     */


    /**
     * Écrit une méthode appelée 'legs', qui aide une fermière à compter le nombre de pattes de ses animaux, étant donné le nombre de chaque type d'animaux.
     * Il y a 3 paramètres: le nombre de poulets, le nombre de cochons, et le nombre de vaches.
     * 
     * -------------------------------
     * 
     * Résultat attendu:
     * 
     * legs(2, 3, 4) devrait retourner 32
     * 
     */
}
