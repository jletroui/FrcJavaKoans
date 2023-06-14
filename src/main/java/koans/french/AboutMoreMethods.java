package koans.french;

public class AboutMoreMethods {
    /**
     * Écris une méthode 'abs' qui a un paramètre entier, et retourne la valeur absolue de cet entier.
     * 
     * -------------------------------
     * 
     * Résultat attendu:
     * 
     * abs(-2) devrait retourner 2
     * 
     */


    /**
     * Écris une méthode 'min' qui a 2 paramètres entiers, et retourne le plus petit des 2 nombres.
     * 
     * -------------------------------
     * 
     * Résultat attendu:
     * 
     * min(4, 3) devrait retourner 3
     * 
     */


    /**
     * Écris une méthode 'remainder' qui prend 2 entiers en paramètres:
     * 
     * - le premier est un dividende
     * - le 2e est un diviseur
     * 
     * La méthode retourne le reste de la division entière du dividende par le diviseur.
     * 
     * ---------   INDICES   --------------
     * 
     * Tu peux calculer le résultat d'une division entière avec l'opérateur '/':
     * 
     *     int result = 13 / 3; // 'result' vaut 4
     * 
     * Le reste est ce qu'il reste non divisé du dividende.
     * Par exemple, quand on divise 13 par 3, 12 est la plus grande part de 13 divisé au complet (en 4 parts de 3), et le reste est donc 13 - 12 = 1.
     * 
     * -------------------------------
     * 
     * Résultat attendu:
     * 
     * remainder(17, 5) devrait retourner 2
     * 
     */


    /**
     * Écris une méthode 'isEven' qui prend un entier en paramètre et retourne 'true' si le nombre est pair, 'false' sinon.
     * Utilise la méthode précédente 'remainder' pour calculer le résultat.
     * 
     * ---------   INDICES   --------------
     * 
     * Le type de valeur qui peut être soit 'true' soir 'false' est appelé un booléen ('boolean' en Java).
     * Tu as déjà rencontré les booléens: les conditions utilisent des booléens. Mais tu peux utiliser les booléens en dehors des conditions. Ex:
     * 
     *     boolean result = 3 > 4; // 'result' vaut 'false'
     * 
     * Tu peux faire retourner un booléen à une méthode en le précisant dans son type de retour:
     * 
     *     public static boolean isCool() {
     *         // Du code qui retourne un booléen
     *     }
     * 
     * Pour tester si un nombre est pair, réfléchis à ce qu'il se passe lorsque l'on calcule le reste d'une division par 2.
     * 
     * -------------------------------
     * 
     * Résultat attendu:
     * 
     * isEven(5) devrait retourner false
     * 
     */

     
    /**
     * Écris une méthode 'isMultiple' avec 2 paramètres entiers qui retourne 'true' si le second nombre est un multiple du premier.
     * Utilise la méthode 'remainder' pour calculer le résultat.
     * 
     * ---------   INDICES   --------------
     * 
     * Pense à ce qu'il se passe sur le reste lorsque l'on divise un nombre par un multiple de ce nombre.
     * 
     * -------------------------------
     * 
     * Résultat attendu:
     * 
     * isMultiple(15, 5) devrait retourner true
     * 
     */

     
    /**
     * Utilise la précédente méthode 'isMultiple' pour écrire une méthode 'fizzBuzz' prenant un entier en paramètre et qui affiche dans la console:
     * 
     * - "Fizz" si l'entier est un multiple de 3
     * - "Buzz" si l'entier est un multiple de 5
     * - "FizzBuzz" si l'entier est un multiple de 3 et de 5
     * - l'entier lui même sinon
     * 
     * -------------------------------
     * 
     * Résultat attendu:
     * 
     * fizzBuzz(20) devrait afficher:
     * 
     * Buzz
     * 
     */ 
      
}
