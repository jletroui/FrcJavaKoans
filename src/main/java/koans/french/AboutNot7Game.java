package koans.french;

import static engine.Helpers.random;
import static engine.Helpers.readLine;

public class AboutNot7Game {
    /**
     * # Introduction au jeu 'Pas 7!'
     * 
     * Les Koans suivants sont un peu spéciaux, car vous allez mettre en pratique tout ce que vous avez appris.
     * 
     * Vous allez graduellement coder un jeu pour 2 appelé 'Pas 7!'.
     * 
     * Dans ce jeu, chaque joueur a seulement 1 tour. À son tour, le joueur va lancer 2 dés, le nombre de fois qu'iel le désire.
     * 
     * Cependant, si à un quelconque moment, le résultat du lancer est 7, le joueur perd immédiatement.
     * 
     * Sinon, quand le joueur décide de passer et d'arrêter de lancer les dés, son score est la somme de tous ses lancers.
     * 
     * Voici un exemple du jeu, dans la console:
     * 
     * Joueur 1, c'est votre tour!
     * 
     * Vous avez lancé un 5 et un 4.
     * Votre résultat jusqu'à maintenant: 9.
     * Voulez-vous continuer à lancer [o/n]?
     * o
     * Vous avez lancé un 1 et un 3.
     * Votre résultat jusqu'à maintenant: 13.
     * Voulez-vous continuer à lancer [o/n]?
     * o
     * Vous avez lancé un 6 et un 2.
     * Votre résultat jusqu'à maintenant: 21.
     * Voulez-vous continuer à lancer [o/n]?
     * n
     * 
     * Bravo, votre score est 21!
     * 
     * Joueur 2, c'est votre tour!
     * 
     * Vous avez lancé un 3 et un 2.
     * Votre résultat jusqu'à maintenant: 5.
     * Voulez-vous continuer à lancer [o/n]?
     * o
     * Vous avez lancé un 4 et un 4.
     * Votre résultat jusqu'à maintenant: 13.
     * Voulez-vous continuer à lancer [o/n]?
     * o
     * Vous avez lancé un 1 et un 2.
     * Votre résultat jusqu'à maintenant: 16.
     * Voulez-vous continuer à lancer [o/n]?
     * o
     * Vous avez lancé un 3 et un 3.
     * Votre résultat jusqu'à maintenant: 22.
     * Voulez-vous continuer à lancer [o/n]?
     * n
     * 
     * Bravo, votre score est 22!
     * 
     * Bravo, le joueur 2 gagne!!!
     * 
     * 
     * 
     * Voici un autre exemple avec un joueur malchanceux qui tire un 7:
     * 
     * Joueur 1, c'est votre tour!
     * 
     * Vous avez lancé un 2 et un 3.
     * Votre résultat jusqu'à maintenant: 5.
     * Voulez-vous continuer à lancer [o/n]?
     * o
     * Vous avez lancé un 6 et un 5.
     * Votre résultat jusqu'à maintenant: 16.
     * Voulez-vous continuer à lancer [o/n]?
     * n
     * 
     * Bravo, votre score est 16!
     * 
     * Joueur 2, c'est votre tour!
     * 
     * Vous avez lancé un 3 et un 2.
     * Votre résultat jusqu'à maintenant: 5.
     * Voulez-vous continuer à lancer [o/n]?
     * o
     * Vous avez lancé un 5 et un 2.
     * Oh non, Pas 7! Vous avez perdu!
     * 
     * Bravo, le joueur 1 gagne!!!
     */


    /**
     * # Lancer un dé
     * 
     * Écris une méthode 'die6()' retournant le résultat d'un dé à 6 faces.
     * 
     * ---------   INDICES   --------------
     * 
     * Pour générer un nombre aléatoire entre 0 et 1, utilise la méthode random(). Ex:
     * 
     *     double someNumber = random(); // 'someNumber' peut être n'importe quel double entre 0 et 1
     * 
     * Essaie de penser à quelle opération arithmétique tu pourrais appliquer pour passer d'un mombre entre 0 et 1 à un nombre entre 0 et 6.
     * 
     * Une fois trouvé, tu devras arrondire le nombre à l'entier suivant.
     * Par exemple, si le résultat de l'opération précédente est 3.2, tu vas devoir l'arrondir à 4. Tu peux faire cela avec:
     * 
     *     double rounded = Math.ceil(3.2) // 'rounded' vaut 4.0
     * 
     * Finalement, ces 2 opérations te donnent un nombre décimal. Il va te falloir le convertir en entier.
     * Tu peux faire cette conversion en mettant le type qui t'intéresse entre parenthèses devant une expression. Ex:
     * 
     *     int roundedAsInt = (int)4.0; // converti un 'double' représentant un entier en un 'int'
     * 
     * -------------------------------
     * 
     * Résultat attendu:
     * 
     * die6() devrait retourner un entier entre 1 et 6 au hasard
     * 
     */


    /**
     * # Poser une question au joueur
     * 
     * Écris une méthode 'askQuestion(String questionText)' qui pose une question à l'utilisateur, et retourne un booléen indiquant si l'utilisateur a répondu 'o'.
     * 
     * ---------   INDICES   --------------
     * 
     * Java a une petite particuliarité. Pour tester si 2 String sont égales, nous ne pouvons pas utiliser '=='. Ex:
     * 
     *     "abc" == "abc" // Retourne toujours faux!!!
     * 
     * Pour comparer 2 String, il faut donc utiliser la méthode 'equals()' de String:
     * 
     *     "abc".equals("abc") // Retourne vrai, comme cela le devrait.
     * 
     * -------------------------------
     * 
     * Résultat attendu:
     * 
     * askQuestion("Voulez-vous continuer [o/n]? ") devrait:
     *   - afficher "Voulez-vous continuer [o/n]? " dans la console
     *   - laisser l'utilisateur répondre avec du texte
     *   - retourner true si l'utilisateur tape 'o'

     * askQuestion("Aimez vous les oranges [o/n]? ") devrait:
     *   - afficher "Aimez vous les oranges [o/n]? " dans la console
     *   - laisser l'utilisateur répondre avec du texte
     *   - retourner false si l'utilisateur tape 'n'
     * 
     */


    /**
     * # Lancer 2 dés
     * 
     * Utilise die6() pour écrire une méthode 'throwDice' qui lance 2 dés, affiche le résultat dans la console, et retourne la somme des 2 dés.
     * 
     * -------------------------------
     * 
     * Résultat attendu:
     * 
     * Quand le résultat des dés est 2 et 3, throwDice() devrait afficher 'Vous avez lancé un 3 et un 2.' et retourner 5.
     * 
     */


    /**
     * # Programmer une manche du jeu: 1ère étape
     * 
     * Utilise 'throwDice' et 'askQuestion' pour écrire une méthode 'gameRoundv1' qui va de façon répétée, tant que l'utilisateur répond 'o':
     * 
     * - lancer 2 dés
     * - demander si l'utilisateur veut continuer à lancer les dés
     * 
     * ---------   INDICES   --------------
     * 
     * Utilise une boucle avec une condition sur la valeur retournée par la méthode askQuestion().
     * Tu vas devoir avoir une variable booléenne initialisée avec 'true', de façon à ce que tu puisse y enregistrer ce que l'utilisateur répond à la fin de la boucle.
     * 
     * -------------------------------
     * 
     * Résultat attendu:
     * 
     * Quand le résultat des dés est 2 et 3, gameRoundv1() devrait afficher:
     * 
     * Vous avez lancé un 3 et un 2.
     * Voulez-vous continuer à lancer [o/n]?
     * 
     * Si l'utilisateur répond 'o', alors la méthode doit recommencer un nouveau lancé.
     * Si l'utilisateur répond 'n', alors la méthode devrait arrêter.
     */


    /**
     * # Programmer une manche du jeu: 2ème étape
     * 
     * Écris une méthode 'gameRoundv2' qui fait la même chose que gameRoundv1, mais qui affiche également la somme des résultats jusqu'à maintenant.
     * 
     * -------------------------------
     * 
     * Résultat attendu:
     * 
     * Quand le résultat des dés est 2 et 3, gameRoundv2() devrait afficher:
     * 
     * Vous avez lancé un 3 et un 2.
     * Votre résultat jusqu'à maintenant: 5.
     * Voulez-vous continuer à lancer [o/n]?
     * 
     * Si l'utilisateur répond 'o', alors la méthode doit recommencer un nouveau lancé.
     * Si l'utilisateur répond 'n', alors la méthode devrait arrêter.
     */


    /**
     * # Programmer une manche du jeu: 3ème étape
     * 
     * Écris une méthode 'gameRoundv3' qui fait la même chose que gameRoundv2, mais qui retourne aussi le score total une fois que l'utilisateur choisi de s'arrêter.
     * 
     * -------------------------------
     * 
     * Résultat attendu:
     * 
     * Quand le résultat des dés est 2 et 3, gameRoundv3() devrait afficher:
     * 
     * Vous avez lancé un 3 et un 2.
     * Votre résultat jusqu'à maintenant: 5.
     * Voulez-vous continuer à lancer [o/n]?
     * 
     * Si l'utilisateur répond 'n', alors la méthode devrait arrêter et retourner 5.
     */       


    /**
     * # Programmer une manche du jeu: 4ème étape
     * 
     * Écris une méthode 'gameRoundv4' qui fait la même chose que gameRoundv3, mais qui s'arrête également si le résultat du lancer est 7.
     * Si la méthode s'arrête de cette manière, une ligne vide devrait être affichée à la fin et le résultat retourné devrait être 0.
     * 
     * -------------------------------
     * 
     * Résultat attendu:
     * 
     * Quand le résultat des dés est 4 et 3, gameRoundv4() devrait retourner 0 et afficher:
     * 
     * Vous avez lancé un 3 et un 4.
     * Oh non, Pas 7! Vous avez perdu!
     * 
     */


    /**
     * # Programmer une manche du jeu: dernière étape
     * 
     * Écris une méthode 'gameRoundv5' qui fait la même chose que gameRoundv4, mais si le joueur s'arrête avant d'avoir fait un 7,
     *   elle affiche aussi son score entre des lignes vides.
     * 
     * -------------------------------
     * 
     * Résultat attendu:
     * 
     * Quand le résultat des dés est 4 et 3, gameRoundv5() devrait retourner 0 et afficher:
     * 
     * Vous avez lancé un 3 et un 4.
     * Oh non, Pas 7! Vous avez perdu!
     * 
     * 
     * Quand le résultat des dés est 4 et 5, et que le joueur répond 'n', gameRoundv5() devrait retourner 9 et afficher:
     * 
     * Vous avez lancé un 5 et un 4.
     * Votre résultat jusqu'à maintenant: 9.
     * Voulez-vous continuer à lancer [o/n]?
     * n
     * 
     * Bravo, votre score est 9!
     * 
     */


    /**
     * # Programmer le jeu: 1ère étape
     * 
     * En utilisant 'gameRoundv5', écris une méthode 'not7Gamev1' qui affiche le joueur dont c'est le tour, et fait jouer son tour au joueur.
     * 
     * -------------------------------
     * 
     * Example de partie:
     * 
     * Joueur 1, c'est votre tour!
     * 
     * Vous avez lancé un 5 et un 4.
     * Votre résultat jusqu'à maintenant: 9.
     * Voulez-vous continuer à lancer [o/n]?
     * o
     * Vous avez lancé un 1 et un 3.
     * Votre résultat jusqu'à maintenant: 13.
     * Voulez-vous continuer à lancer [o/n]?
     * o
     * Vous avez lancé un 6 et un 2.
     * Votre résultat jusqu'à maintenant: 21.
     * Voulez-vous continuer à lancer [o/n]?
     * n
     * 
     * Bravo, votre score est 21!
     * 
     * Joueur 2, c'est votre tour!
     * 
     * Vous avez lancé un 3 et un 2.
     * Votre résultat jusqu'à maintenant: 5.
     * Voulez-vous continuer à lancer [o/n]?
     * o
     * Vous avez lancé un 4 et un 4.
     * Votre résultat jusqu'à maintenant: 13.
     * Voulez-vous continuer à lancer [o/n]?
     * o
     * Vous avez lancé un 1 et un 2.
     * Votre résultat jusqu'à maintenant: 16.
     * Voulez-vous continuer à lancer [o/n]?
     * o
     * Vous avez lancé un 3 et un 3.
     * Votre résultat jusqu'à maintenant: 22.
     * Voulez-vous continuer à lancer [o/n]?
     * n
     * 
     * Bravo, votre score est 22!
     * 
     */


    /**
     * # Programmer le jeu: dernière étape
     * 
     * Écris une méthode 'not7Gamev2' qui fait la même chose que not7Gamev1, mais qui affiche également qui est le gagnant.
     * En cas d'égalité, elle affiche simplement: 'Égalité!'.
     * 
     * -------------------------------
     * 
     * Example de partie:
     * 
     * Joueur 1, c'est votre tour!
     * 
     * Vous avez lancé un 5 et un 4.
     * Votre résultat jusqu'à maintenant: 9.
     * Voulez-vous continuer à lancer [o/n]?
     * o
     * Vous avez lancé un 1 et un 3.
     * Votre résultat jusqu'à maintenant: 13.
     * Voulez-vous continuer à lancer [o/n]?
     * o
     * Vous avez lancé un 6 et un 2.
     * Votre résultat jusqu'à maintenant: 21.
     * Voulez-vous continuer à lancer [o/n]?
     * n
     * 
     * Bravo, votre score est 21!
     * 
     * Joueur 2, c'est votre tour!
     * 
     * Vous avez lancé un 3 et un 2.
     * Votre résultat jusqu'à maintenant: 5.
     * Voulez-vous continuer à lancer [o/n]?
     * o
     * Vous avez lancé un 4 et un 4.
     * Votre résultat jusqu'à maintenant: 13.
     * Voulez-vous continuer à lancer [o/n]?
     * o
     * Vous avez lancé un 1 et un 2.
     * Votre résultat jusqu'à maintenant: 16.
     * Voulez-vous continuer à lancer [o/n]?
     * o
     * Vous avez lancé un 3 et un 3.
     * Votre résultat jusqu'à maintenant: 22.
     * Voulez-vous continuer à lancer [o/n]?
     * n
     * 
     * Bravo, votre score est 22!
     * 
     * Bravo, le joueur 2 gagne!!!
     * 
     */
 

    /**
     * # BONUS
     * 
     * Si tu t'es rendu jusque là, félicitations! Tu connais maintenant les bases de la programmation!!!
     * Avant le prochain Koan, cependant, que dirais tu d'être capable de lancer ton jeu, afin de pouvoir jouer avec un ami?
     * 
     * En Java, pour qu'un programme sache quoi exécuter, tu as besoin de créer une méthode spéciale appelée 'main' quelque part.
     * La signature de la méthode doit être exactement comme celle ci:
     * 
     *     public static void main(String[] args) {
     *       // Le code à exécuter lorsque le programme démarre
     *     }
     * 
     * Par exemple, tu as peut-être remarqué que les Koans s'exécutent car la classe 'FrenchPathToEnlightment', sur laquelle tu fais un clic droit, possède une telle méthode.
     * 
     * Alors n'hésites pas, et crée une telle méthode ici dans la classe 'AboutNot7Game', et appelle ta méthode 'not7Gamev2' à l'intérieur.
     * 
     * Puis, pour démarrer ton jeu, fais simplement un clic droit sur le fichier src\main\java\koans\french\AboutNot7Game.java, et choisis 'Run Java'.
     * Ton jeu s'exécute pour vrai!
     * 
     */

}
