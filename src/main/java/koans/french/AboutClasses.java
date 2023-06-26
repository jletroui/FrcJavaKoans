package koans.french;

public class AboutClasses {
    /**
     * Crée une classe 'utils.MathUtils'. Dans cette classe, écris une méthode 'cube' qui prend un entier en paramètre et retourne son cube.
     * 
     * ---------   INDICES   --------------
     * 
     * En Java, toutes tes méthodes doivent aller dans des classes. Les classes organisent les méthodes ensemble.
     * 
     * Les classes sont elles-même organisées dans des répertoires appelés 'packages'.
     * 
     * Les packages commencent à la racine Java du projet, généralement 'src/main/java'.
     * Par exemple, le package 'koans' est situé dans le répertoire 'src/main/java/koans'.
     * 
     * Les packages peuvent être imbriqués les uns dans les autres. Par exemple: le package 'french' est situé dans le package 'koans'.
     * Les classes du package 'koans.french' sont situées dans 'src/main/java/koans/french'.
     * 
     * Note: tu l'as peut être remarqué, en Java, pour localiser quelque chose situé dans quelque chose d'autre, on utilise la notation '.'.
     * Par exemple, la classe 'AboutClasses' dans le package 'english' dans le package 'koans' est notée:
     * 
     *     koans.english.AboutClasses
     * 
     * Pour créer une classe, crée un fichier nomé '[nom classe].java' dans le répertoire de son package.
     * Tu pourrais avoir besoin de créer le répertoire en premier, si c'est la première classe du package.
     * 
     * Par exemple, une classe 'frc.Robot' irait dans un fichier 'Robot.java' dans le répertoire 'src/main/java/frc'.
     * 
     * Dans le fichier de la classe, on déclare la classe ainsi:
     * 
     *     // La toute première ligne doit déclarer dans quel package cette classe se situe
     *     package frc;
     * 
     *     public class Robot {
     *         // Les méthodes de la class Robot vont ici
     *     }
     * 
     * Rappel: le cube d'un nombre est ce nombre multiplié 3 fois par lui-même.
     * 
     * -------------------------------
     * 
     * Résultat attendu:
     * 
     * utils.MathUtils.cube(2) devrait retourner 8
     * 
     */


    /**
     * En utilisant utils.MathUtils.cube, écris une méthode 'displayCube' dans koans.french.AboutClasses qui affiche le cube d'un nombre dans la console.
     * 
     * ---------   INDICES   --------------
     * 
     * Pour appeler une méthode dans une classe qui n'est pas dans le même package, tu dois d'abord "l'importer".
     * Les imports vont juste après la déclaration du package, mais avant la déclaration de la classe. Ex:
     * 
     *     // Le package de la classe que l'on est en train de déclarer
     *     package mypackage;
     *
     *     // Exemples d'imports de classes que l'on pourrait utiliser dans ce fichier Java
     *     import frc.Robot;
     *     import utils.MathUtils;
     * 
     * Une fois importées, tu peux utiliser des méthodes de ces autres classes:
     * 
     *     public class MyClass {
     *         public static void illuminateRobot() {
     *             Robot.tunOnDELs(); // Utilisation d'une classe importée en appelant une de ses méthodes
     *         }
     *     }
     * 
     * Note: pour utiliser une classe une fois qu'elle est importée, pas besoin de répéter le nom de son package. Le nom de la classe suffit.
     * 
     * -------------------------------
     * 
     * Résultat attendu:
     * 
     * displayCube(3) devrait afficher '27' dans la console.
     */


    /**
     * Crée une classe 'utils.math.OtherMathUtils'. Dans cette classe, crée une méthode 'max' qui prends 2 entiers, et retourne le plus grand des 2.
     * 
     * -------------------------------
     * 
     * Résultat attendu:
     * 
     * utils.math.OtherMathUtils.max(2, 3) devrait retourner 3
     * 
     */


    /**
     * En utilisant utils.math.OtherMathUtils.max, écris une méthode 'displayMax' dans koans.french.AboutClasses qui affiche le plus grand de 2 entiers.
     * 
     * -------------------------------
     * 
     * Résultat attendu:
     * 
     * displayMax(5, -1) devrait afficher '5' dans la console.
     * 
     */


    /**
     * En utilisant la méthode 'min' créée dans un précédent Koan, écris une méthode 'displayMin' dans koans.french.AboutClasses qui affiche le plus petit de 2 entiers.
     * 
     * ---------   INDICES   --------------
     * 
     * Rappel: tu n'as pas besoin d'importer les classes qui font partie du même package que ta classe courante. Tu peux utiliser ces classes directement.
     * 
     * -------------------------------
     * 
     * Résultat attendu:
     * 
     * displayMin(5, -1) devrait afficher '-1' dans la console.
     * 
     */


}
