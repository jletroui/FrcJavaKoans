package koans.french;

public class AboutObjects {
    /**
     * # Le premier objet
     * 
     * Crée une classe 'geom.Point' avec 2 champs décimaux (toujours privés et finaux) 'x' et 'y'. Le constructeur devrait prendre 'x' et 'y' en paramètres.
     * 
     * ---------   INDICES   --------------
     * 
     * Jusqu'à maintenant, nous avons seulement attaché des méthodes aux classes elles-même. Cela permet d'organiser les méthodes dans ton programme.
     * Mais les classes peuvent être bien plus puissantes que de simples 'répertoires à méthodes'.
     * 
     * Une classe peut servir de gabarit pour des éléments de code appelés 'objets'. Des objets groupent ensemble des valeurs et des méthodes qui peuvent agir sur ces valeurs.
     * La classe devient alors un gabarit qui permet de créer plusieurs objets différents, qui ont tous la même structure de méthode et de valeurs.
     * 
     * Par exemple, disons que nous voulons, dans le code, représenter des personnes qui peuvent se présenter. Un objet 'personne' pourrait donc avoir: un nom, un âge, et une méthode 'introduce'.
     * 
     * Le nom et l'âge sont des sortes de variables attachées à un objet de type 'personne'. Les valeurs de ces variables peuvent être différentes d'un objet 'personne' à l'autre.
     * Une telle 'variable' est appelée un 'champ de l'objet'.
     * 
     * De la même façon, la méthode 'introduce' est attachée à l'objet. L'appeler sur 2 objets 'personne' différents va produire un résultat différent, puis que la valeur des champs pourront être différentes d'un objet à l'autre.
     * 
     * Tu peux créer un objet à partir d'une classe en appelant une méthode très spéciale appelée 'constructeur'. Le constructeur... construit un objet à partir de la classe.
     * 
     * Le résultat d'un constructeur est une valeur, dont le type est la classe elle-même.
     * Tu peux appeler un constructeur en utilisant le mot clef 'new', suivit du nom de la classe.
     * Par exemple, pour la classe Person:
     * 
     *     // Dans cet exemple, le constructeur de Person prend un nom et et âge
     *     Person julien = new Person("Julien", 44);
     *     Person stephane = new Person("Stéphane", 26);
     * 
     * Une fois que nous avons des objets, nous pouvons appeler leurs méthodes. Par exemple, si les objets Person ont une méthode 'introduce':
     * 
     *     julien.introduce();
     * 
     * Résultat dans la console:
     * 
     * Salut, mon nom est Julien et j'ai 44 ans
     * 
     * Que se passe-t-il quand nous appelons la même méthode sur l'objet 'stephane'?
     * 
     *     stephane.introduce();
     * 
     * Résultat dans la console:
     * 
     * Salut, mon nom est Stéphane et j'ai 26 ans
     * 
     * Même méthode, mais un résultat différent! C'est parce que la méthode est appelée sur un object qui accède aux champs de l'objet.
     * Comme les valeurs des champs sont différents d'un object à l'autre, la méthode produit un résultat différent.
     * 
     * Qu'arrive-t-il si nous essayons d'appeler la méthode sur la classe elle-même?
     * 
     *     Person.introduce(); // Erreur, this ce n'est pas du code Java valide
     * 
     * Cela produit une erreur, car la méthode est attachée aux _objets_ de la classe Person, pas à la classe Person elle-même.
     * Par défaut, une méthode est attachée aux objets de la classe. Si tu veux attacher une méthode à la classe elle-même, tu dois dire qu'elle est 'static'.
     * 'static' veut dire 'attaché à la classe'.
     * 
     * Maintenant, comment on déclare des champs dans une classe? Comme ceci:
     * 
     *     public class Person {
     *         // Les champs vont au début de la classe.
     *         private final String name;
     *         private final int age;
     *         
     *         public introduce() {
     *             // Dans une méthode attachée à l'objet, nous pouvons utiliser les valeurs des champs, comme si c'était de simples variables
     *             System.out.println("Salut, mon nom est " + name + " et j'ai " + age + " ans");
     *         }
     *     }
     * 
     * Comme les variables, les champs ont un type et un nom. Par contre, nous devons également spécifier s'ils sont visibles en dehors de la classe.
     * Bien que Java permette d'avoir des champs visibles en dehors de la classe, c'est une mauvaise pratique qui facilite la création de bugs non intentionnels.
     * Donc, ils devraient tous être privés ('private' en Java), c'est à dire utilisable seulement à l'intérieur de la classe.
     * Les champs devraient aussi être 'final', ce qui signifie que leur valeurs sont assignées une fois pour toute dans le constructeur, et ne changera jamais au cours de la vie d'un objet.
     * 
     * Et comment déclarer un constructeur? Le constructeur est une méthode un peu spéciale.
     * D'abord, nous n'avons pas besoin de déclarer un type de retour, puisqu'il retourne toujours un objet du type de la classe.
     * Et le nom de cette méthode spéciale doit être le nom de la classe. Ex:
     * 
     *     public class Person {
     *         private final String name;
     *         private final int age;
     *         
     *         // Le constructeur de la class Person
     *         // Type de retour de la méthode: aucun.
     *         // Nom de la méthode: même nom que la classe.
     *         public Person(String name, int age) {
     *             // Nous pouvons assigner les valeurs des champs de l'objet dans le constructeur.
     *             this.name = name;
     *             this.age = age; 
     *         }
     * 
     *         public introduce() {
     *             System.out.println("Hello, my name is " + name + " and I am " + age);
     *         }
     *     }
     * 
     * Tu peux voir que nous utilisons le mot clef 'this' pour faire la différence entre les champs de l'objets et les paramètres du constructeur, qui ont les même noms.
     * 'this' est comme un champ spécial qui contient l'objet courant. Donc 'this.[nom d'un champ ou d'une méthode]' réfère à un champ ou une méthode de l'objet courant.
     * Par défaut, dans une méthode, un nom sans 'this' réfère à un paramètre ou une variable de la méthode, et non un champ de la classe.
     * 
     * -------------------------------
     * 
     * Résultat attendu:
     * 
     * nous pouvons créer un nouvel objet geom.Point
     * 
     */


    /**
     * # Une méthode d'objet
     * 
     * Écris une méthode 'toString' in 'geom.Point' qui retourne une représentation d'un objet de type Point selon ce gabarit:
     * 
     * Point([valeur de x], [valeur de y])
     * 
     * -------------------------------
     * 
     * Résultat attendu:
     * 
     * new Point(2.5, 4.3).toString() devrait retourner le texte "Point(2.5, 4.3)"
     * 
     */


    /**
     * # Une autre méthode d'objet
     * 
     * Écris une méthode 'translate' dans 'geom.Point' qui prend 2 coordonnées de translation 'tx' et 'ty', et retourne un nouveau objet Point, qui est le point translaté par les coordonnées tx et ty.
     * 
     * ---------   INDICES   --------------
     * 
     * Quand on translate un point A de coordonnées x et y, le point résultant a les coordonnées x + tx et y + ty.
     * 
     * Tu vas avoir besoin de créer un nouvel objet de type Point pour le retourner. Tu auras donc peut-être besoin de te raffraichir la mémoire en regardant les indices du premier exercice de cette série.
     * 
     * -------------------------------
     * 
     * Résultat attendu:
     * 
     * new Point(2.5, 4.3).translate(2, -1) devrait retourner un nouveau point avec les coordonnées 4.5 et 3.3
     * 
     */


    /**
     * # Un objet... utilisant d'autres objets
     * 
     * Crée une classe 'geom.Triangle' avec 3 champs privés 'a', 'b', et 'c' de type Point. Le constructeur devrait prendre 'a', 'b', et 'c' en paramètres.
     * 
     * -------------------------------
     * 
     * Résultat attendu:
     * 
     * nous pouvons créer un nouvel objet geom.Triangle
     * 
     */


    /**
     * # Utiliser la méthode d'un autre objet
     * 
     * En utilisant 'Point.toString', écris une méthode 'toString' dans 'geom.Triangle' qui retourne une représentation textuelle d'un objet Triangle suivant ce gabarit:
     * 
     * Triangle(Point([valeur x de 'a'], [valeur y de 'a']), Point([valeur x de 'b'], [valeur y de 'b']), Point([valeur x de 'c'], [valeur y de 'c']))
     * 
     * -------------------------------
     * 
     * Résultat attendu:
     * 
     * new Triangle(new Point(1.0, 1.0), new Point(2.0, 2.0), new Point(3.0, 3.0)).toString() devrait retourner le texte "Triangle(Point(1.0, 1.0), Point(2.0, 2.0), Point(3.0, 3.0))"
     * 
     */


    /**
     * # Utiliser une autre méthode d'un autre objet
     * 
     * En utilisant 'Point.translate', écris une méthode 'translate' dans 'geom.Triangle' qui prend 2 coordonnées de translation 'tx' et 'ty', et retourne un nouvel objet Triangle, qui est le triangle initial, translaté par tx et ty.
     * 
     * -------------------------------
     * 
     * Résultat attendu:
     * 
     * Le code suivant:
     * 
     *     Triangle myTriangle = new Triangle(new Point(1.0, 1.0), new Point(2.0, 2.0), new Point(3.0, 3.0)).translate(2.0, -1.0);
     * 
     * Devrait résulter en 'myTriangle' étant un objet Triangle avec:
     * 
     *   - myTriangle.a qui vaut Point(3.0, 0.0)
     *   - myTriangle.b qui vaut Point(4.0, 1.0)
     *   - myTriangle.c qui vaut Point(5.0, 2.0)
     * 
     */


    /**
     * # Appliquer ses connaissances: la classe Circle
     * 
     * Crée une classe 'geom.Circle' avec les champs 'center' (Point), et 'radius' (double). Le constructeur prend 'center' et 'radius' en paramètres.
     * En utilisant 'Point.toString', écris une méthode 'toString' dans 'geom.Circle' qui retourne une représentation textuelle d'un objet Circle suivant ce gabarit:
     * 
     * Circle(Point([valeur x de 'center'], [valeur y de 'center']), [valeur de 'radius'])
     * 
     * -------------------------------
     * 
     * Résultat attendu:
     * 
     * new Circle(new Point(2.0, 1.0), 3.6).toString() devrait retourner le texte "Circle(Point(2.0, 1.0), 3.6)"
     * 
     */


    /**
     * # Appliquer ses connaissances: translation d'un cercle
     * 
     * En utilisant 'Point.translate', écris une méthode 'translate' dans 'geom.Circle' qui prend 2 coordonnées de translation 'tx' et 'ty', et retourne un nouvel objet Circle, qui est le cercle initial, translaté par tx et ty.
     * 
     * -------------------------------
     * 
     * Résultat attendu:
     * 
     * Le code suivant:
     * 
     *     Circle myCircle = new Circle(new Point(1.0, 1.0), 3.6).translate(2.0, -1.0);
     * 
     * Devrait résulter en 'myCircle' étant un objet Circle avec:
     * 
     *   - myCircle.center qui vaut Point(3.0, 0.0)
     *   - myCircle.radius qui vaut 3.6
     * 
     */


    /**
     * # Objets avec champs muables
     * 
     * Maintenant, programmons une application de scoutisme pour le jeu de 2024 'Crescendo', dans lequel les robot ont besoin de lancer des cercles en mousse dans un 'haut parleur' et un 'amplificateur' ('amp').
     * En mode auto, envoyer une note dans le haut parleur rapporte 5 points, et dans l'amp, 2 points. Créons des classes pour nous aider à calculer le score de robots durant la période autonome.
     * Créé une classe 'frc.RobotAutoScore' avec des champs non finaux 'notesInSpeaker' (int), 'notesInAmp' (int), initialisé à 0.
     * Écris une méthode 'toString' dans 'frc.RobotAutoScore' qui retourne la représentation suivante d'un objet RobotAutoScore:
     * 
     * ScoreRobot: notes dans le haut parleur = [valeur de notesInSpeaker]; notes dans le amp = [valeur de notesInAmp]
     * 
     * -------------------------------
     * 
     * Résultat attendu:
     * 
     * new RobotAutoScore().toString() devrait retourner la String "ScoreRobot: notes dans le haut parleur = 0; notes dans le amp = 0"
     * 
     */


    /**
     * # Changer la valeur des champs d'un objet
     * 
     * Écris une méthode 'noteScoredInSpeaker' dans 'frc.RobotAutoScore' qui ne prend aucun paramètre, et augmente la valeur du champ 'notesInSpeaker' de 1.
     * 
     * -------------------------------
     * 
     * Résultat attendu:
     * 
     * Le code suivant:
     * 
     *     RobotAutoScore robotScore = new RobotAutoScore();
     *     robotScore.noteScoredInSpeaker();
     *     System.out.println(robotScore);
     * 
     * Devrait afficher:
     * 
     *   ScoreRobot: notes dans le haut parleur = 1; notes dans le amp = 0
     * 
     */


    /**
     * # Plus de changement de valeur
     * 
     * Écris une méthode 'noteScoredInAmp' dans 'frc.RobotAutoScore' qui ne prend aucun paramètre, et augmente la valeur du champ 'notesInAmp' de 1.
     * 
     * -------------------------------
     * 
     * Résultat attendu:
     * 
     * Le code suivant:
     * 
     *     RobotAutoScore robotScore = new RobotAutoScore();
     *     robotScore.noteScoredInAmp();
     *     System.out.println(robotScore);
     * 
     * Devrait afficher:
     * 
     *   ScoreRobot: notes dans le haut parleur = 0; notes dans le amp = 1
     * 
     */


    /**
     * # Calculer le score total d'un robot
     * 
     * Écris une méthode 'totalScore' dans 'frc.RobotAutoScore' qui ne prend aucun paramètre, et retourne le score total (2 points pour les notes dans le amp, et 5 point pour les notes dans le haut parleur).
     * 
     * -------------------------------
     * 
     * Résultat attendu:
     * 
     * Le code suivant:
     * 
     *     RobotAutoScore robotScore = new RobotAutoScore();
     *     robotScore.noteScoredInSpeaker();
     *     robotScore.noteScoredInAmp();
     *     System.out.println(robotScore.totalScore());
     * 
     * Devrait afficher:
     * 
     *   7
     * 
     */


    /**
     * # Calculer le score total d'une alliance au complet
     * 
     * Écris une classe 'frc.AllianceAutoScore' avec les champs finaux 'robotAScore', 'robotBScore' et 'robotCScore' (tous de type RobotAutoScore). Le constructeur prend une valeur pour chacun de ces champs en paramètres.
     * Écris une méthode 'totalScore' dans 'frc.AllianceAutoScore' qui calcule le score total pour toute l'alliance.
     * 
     * -------------------------------
     * 
     * Résultat attendu:
     * 
     *     RobotAutoScore robotAScore = new RobotAutoScore();
     *     robotAScore.noteScoredInSpeaker();
     *     robotAScore.noteScoredInAmp();
     *     RobotAutoScore robotBScore = new RobotAutoScore();
     *     RobotAutoScore robotCScore = new RobotAutoScore();
     *     robotAScore.noteScoredInSpeaker();
     *     AllianceAutoScore allianceScore = new AllianceAutoScore(robotAScore, robotBScore, robotCScore);
     *     System.out.println(allianceScore.totalScore());
     * 
     * Devrait afficher:
     * 
     *   12
     * 
     */
        
}
