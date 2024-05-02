package koans.french;

public class AboutDecimalNumbers {
    /**
     * # Convertir une mesure de longueur
     * 
     * Écris une méthode 'toCm' avec un paramètre pour un nombre de pouces, et qui retourne la conversion en centimètres.
     * 
     * ---------   INDICES   --------------
     * 
     * Cette fois, le paramètre ne peut pas être un entier, car nous voulons convertir les fractions de pouces également.
     * 
     * En Java, les nombres décimaux sont représentés par le type 'double'. Ex:
     * 
     *     double pi = 3.14;
     * 
     * Pour convertir de pouces vers des centimètres, il faut multiplier par 2.54.
     * 
     * -------------------------------
     * 
     * Résultat attendu:
     * 
     * toCm(2.0) devrait retourner 5.08
     * 
     */


    /**
     * # Convertir dans l'autre sens
     * 
     * Écris une méthode 'toInches' qui fait l'inverse de la méthode précédente.
     * 
     * -------------------------------
     * 
     * Résultat attendu:
     * 
     * toInches(5.08) devrait retourner 2.0
     * 
     */


    /**
     * # Calculer de la géométrie
     * 
     * Écris une méthode 'rectangleArea' qui calcule l'aire d'un rectangle, étant donné la longueur de ses cotés.
     * 
     * -------------------------------
     * 
     * Résultat attendu:
     * 
     * rectangleArea(3.6, 2.0) devrait retourner 7.2
     * 
     */


     /**
     * # Calculer la distance parcourue par un robot, étape 1
     * 
     * Écris une méthode 'wheelCircumference' qui calcule la circonférence d'une roue de robot, étant donné le rayon de la roue.
     * 
     * ---------   INDICES   --------------
     * 
     * Utilise la valeur de 3.14 pour pi.
     * 
     * -------------------------------
     * 
     * Résultat attendu:
     * 
     * wheelCircumference(1) devrait retourner 6.28
     * 
     */


    /**
     * # Calculer la distance parcourue par un robot, étape 2
     * 
     * Écris une méthode 'wheelRotations' qui calcule le nombres de rotations de roue étant donné le nombre de tours de moteur et le ratio de la boîte de transmission:
     * 
     * ---------   INDICES   --------------
     * 
     * Le ratio de la boîte de transmission est combien de tours tourne la roue quand le moteur tourne 1 tour.
     * Exemple: si le moteur doit faire 5 tours pour que la roue fasse exactement 1 tour, le ratio est de 1/5 = 0.2.
     * 
     * -------------------------------
     * 
     * Résultat attendu:
     * 
     * wheelRotations(0.2, 2.0) devrait retourner 0.4
     * 
     */


    /**
     * # Calculer la distance parcourue par un robot, étape finale
     * 
     * Utilise les 2 méthodes précédentes pour écrire une méthode 'toDistance' qui calcule la distance parcourue par les roues d'un robot étant donné:
     * 
     * - Le nombre de tours du moteur
     * - Le ratio de transmission
     * - Le rayon des roues
     * 
     * Attention! Il ne faut pas refaire les calculs des méthodes précédentes. À la place, réutilises-les.
     * 
     * -------------------------------
     * 
     * Résultat attendu:
     * 
     * toDistance(10.0, 0.2, 2.0) devrait retourner 25.12
     * 
     */

     
}
