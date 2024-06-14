package engine;

import static engine.Localizable.local;

public class Texts {

    // Sensei

    public static Localizable<String> MOUNTAINS_ARE_AGAIN_MERELY_MOUNTAINS =
        local("Mountains are again merely mountains.")
        .fr("Les montagnes sont de nouveau de simples montagnes.");
    public static Localizable<String> THINKING =
        local("Thinking %s ...")
        .fr("En pensant %s ...");
    public static Localizable<String> HAS_DAMAGED_YOUR_KARMA =
        local("%s has damaged your karma.")
        .fr("%s a alourdi ton karma.");
    public static Localizable<String> THE_MASTER_SAYS =
        local("The master says:")
        .fr("Le maître dit:");
    public static Localizable<String> THE_ANSWERS_YOU_SEEK =
        local("The answers you seek...")
        .fr("Les réponses que tu cherches...");
    public static Localizable<String> YOU_HAVE_NOT_REACHED_ENLIGHTMENT =
        local("  You have not yet reached enlightment ...")
        .fr("  Tu n'as pas encore atteint l'illumination ...");
    public static Localizable<String> EXPECTED_METHOD_TO_BE_PUBLIC =
        local("Expected  method %s to be public, but found it not public.")
        .fr("Attendu à ce que la méthode %s soit publique, mais ce n'est pas le cas.");
    public static Localizable<String> THE_METHOD_APPEARS_TO_PRODUCE_AN_ERROR =
        local("The method %s() appears to produce an error: %s.")
        .fr("La méthode %s() a produit une erreur: %s.");
    public static Localizable<String> THE_CODE_TRIED_BY_THE_SENSEI_SEEMS_TO_NOT_FINISH =
        local("The following code, tried by the Sensi, appears to not finish. Did you code an infinite loop?\n\n%s")
        .fr("Le code suivant, essayé par le Sensei, semble ne jamais terminer. As tu codé une boucle infinie?\n\n%s");
    public static Localizable<String> THE_METHOD_SEEMS_TO_RECURSE_INFINITELY =
        local("The method %s() appears to not finish. Did you call the method in itself, forming an infinite loop?")
        .fr("La méthode %s() semble ne jamais terminer. As tu appelé la méthode dans elle-même, formant une boucle infinie?");
    public static Localizable<String> THE_CONSTRUCTOR_APPEARS_TO_PRODUCE_AN_ERROR =
        local("The constructor of %s appears to produce an error: %s.")
        .fr("Le constructeur de %s a produit une erreur: %s.");
    public static Localizable<String> EXPECTED_TO_FIND_MEHOD_NO_PARAMS =
        local("Expected to find a method called '%s' in src/main/java/%s.java but did not find any.")
        .fr("Attendu à une méthode nommée '%s' dans src/main/java/%s.java, mais ne la trouve pas.");
    public static Localizable<String> EXPECTED_METHOD_TO_BE_STATIC =
        local("Expected method '%s' in src/main/java/%s.java to have the 'static' modifier, but it had not.")
        .fr("Attendu à ce que la méthode '%s' dans src/main/java/%s.java ai le modifieur 'static', mais elle ne l'a pas.");
    public static Localizable<String> EXPECTED_METHOD_TO_NOT_BE_STATIC =
        local("Expected method '%s' %s to not have the 'static' modifier, but it had.")
        .fr("Attendu à ce que la méthode '%s' dans %s n'ai pas le modifieur 'static', mais elle l'a.");
    public static Localizable<String> EXPECTED_TO_FIND_MEHOD_RETURN_TYPE =
        local("Expected to find a method called '%s' in src/main/java/%s.java with a '%s' return type but did not find any.")
        .fr("Attendu à une méthode nommée '%s' dans src/main/java/%s.java, avec un type de retour '%s' mais ne la trouve pas.");
    public static Localizable<String> EXPECTED_TO_FIND_MEHOD_ONE_PARAM =
        local("Expected to find a method called '%s' in src/main/java/%s.java with a '%s' parameter but did not find any.")
        .fr("Attendu à une méthode nommée '%s' dans src/main/java/%s.java, avec un paramètre '%s' mais ne la trouve pas.");
    public static Localizable<String> EXPECTED_TO_FIND_MEHOD_MANY_PARAMS =
        local("Expected to find a method called '%s' in src/main/java/%s.java with parameters of type %s but did not find any.")
        .fr("Attendu à une méthode nommée '%s' dans src/main/java/%s.java, avec des paramètres de type %s mais ne la trouve pas.");
    public static Localizable<String> EXPECTED_CONSTRUCTOR_TO_BE_PUBLIC =
        local("Expected constructor in %s to be public but it is not.")
        .fr("Attendu à ce que le constructeur dans %s soit publique, mais il ne l'est pas.");
    public static Localizable<String> EXPECTED_TO_FIND_CONSTRUCTOR_NO_PARAMS =
        local("Expected to find a constructor in %s but did not find any.")
        .fr("Attendu à un constructeur dans %s, mais ne le trouve pas.");
    public static Localizable<String> EXPECTED_TO_FIND_CONSTRUCTOR_ONE_PARAM =
        local("Expected to find a constructor in %s with a '%s' parameter but did not find any.")
        .fr("Attendu à constructeur dans %s, avec un paramètre '%s' mais ne le trouve pas.");
    public static Localizable<String> EXPECTED_TO_FIND_CONSTRUCTOR_MANY_PARAMS =
        local("Expected to find a constructor in %s with parameters of type %s but did not find any.")
        .fr("Attendu à un constructeur dans %s, avec des paramètres de type %s mais ne le trouve pas.");
    public static Localizable<String> EXPECTED_TO_FIND_A_CLASS_IN_THE_PACKAGE =
        local("Expected to find a class %s in the %s package, but did not find any.")
        .fr("Attendu à trouver une classe %s dans le package %s, mais ne la trouve pas.");
    public static Localizable<String> EXPECTED_CLASS_TO_BE_INSTANTIABLE =
        local("Expected class %s to be instantiable, but it is not.")
        .fr("Attendu ce que la classe %s soit instantiable, mais ne l'est pas.");
    public static Localizable<String> PLEASE_MEDITATE_ON =
        local("Please meditate on %s in src/main/java/koans/english/%s.java")
        .fr("Tu peux méditer sur %s dans src/main/java/koans/french/%s.java");
    public static Localizable<String> THE_MASTER_SENSED_AN_HARMONY_BREACH_WHEN_ANSWERING =
        local("The master sensed the harmony dissolving when planning to answer %s.")
        .fr("Le maître a senti l'harmonie se briser en voulant répondre %s.");
    public static Localizable<String> THE_MASTER_SENSED_AN_HARMONY_BREACH_WHEN_LOOKING_AT =
        local("The master sensed the harmony dissolving when looking at the result of %s")
        .fr("Le maître a senti l'harmonie se briser en regardant le résultat de %s");
    public static Localizable<String> THE_MASTER_SENSED_AN_HARMONY_BREACH_WHEN =
        local("The master sensed the harmony dissolving when:")
        .fr("Le maître a senti l'harmonie se briser en voulant:");
    public static Localizable<String> WHEN_ANSWERING =
        local("  - planning to answer %s")
        .fr("  - répondre %s");
    public static Localizable<String> WHEN_LOOKING_THE_RESULT_OF =
        local("  - and looking at the result of %s")
        .fr("  - et regarder le résultat de %s");
    public static Localizable<String> WHEN_EXECUTING =
        local("  - executing:")
        .fr("  - exécuter:");
    public static Localizable<String> AND_FINALLY_LOOKING_THE_RESULT_OF =
        local("  - and finally looking at the result of %s")
        .fr("  - et finalement regarder le résultat de %s");
    public static Localizable<String> CONSOLE =
        local("Console:")
        .fr("Console:");
    public static Localizable<String> YOUR_PROGRESS_THUS_FAR =
        local(ConsoleFmt.green("Your progress thus far: [") + "%s" + ConsoleFmt.green("]") + " %d/%d")
        .fr(ConsoleFmt.green("Ton progrès jusqu'à maintenant: [") + "%s" + ConsoleFmt.green("]") + " %d/%d");
    public static Localizable<String> AND =
        local(", and %s")
        .fr(" et %s");
    public static Localizable<String> EXPECTED_TO_FIND_FIELD_IN_CLASS =
        local("Expected to find a '%s' field in class %s, but did not find any.")
        .fr("Attendu à trouver un champ '%s' dans la classe %s, mais ne le trouve pas.");
    public static Localizable<String> BUG_FOUND =
        local("Oops, it seems there is a design issue in this Koan. Please transmit the following message to maintainers: '%s'.")
        .fr("Oups, il semble y avoir un problème de conception avec ce Koan. SVP transmettre ce message aux créateurs: '%s'.");

    // Assertions
    
    public static Localizable<String> EXPECTED_TO_SEE_EMPTY_LINE_IN_CONSOLE_BUT_SAW_NOTHING =
        local("Expected to see an empty line in the console, but saw nothing!")
        .fr("Attendu à voir une ligne vide dans la console, mais n'a rien vu!");
    public static Localizable<String> EXPECTED_TO_SEE_EMPTY_LINE_IN_CONSOLE_BUT_SAW_INSTEAD =
        local("Expected to see an empty line in the console, but saw '%s' instead!")
        .fr("Attendu à voir une ligne vide dans la console, mais vu '%s' à la place!");
    public static Localizable<String> OK_DISPLAYED_EMPTY_LINE_IN_CONSOLE =
        local("Ok: displayed an empty line in the console.")
        .fr("Ok: affiché une ligne vide dans la console.");
    public static Localizable<String> EXPECTED_TO_SEE_IN_CONSOLE_BUT_SAW_NOTHING =
        local("Expected to see '%s' in the console when calling %s, but did not see it!")
        .fr("Attendu à voir '%s' dans la console en appelant %s, mais ne l'a pas vu!");
    public static Localizable<String> EXPECTED_TO_SEE_IN_CONSOLE_BUT_SAW_INSTEAD =
        local("Expected to see '%s' in the console when calling %s, but saw '%s' instead!")
        .fr("Attendu à voir '%s' dans la console en appelant %s, mais vu '%s' à la place!");
    public static Localizable<String> OK_DISPLAYED_IN_CONSOLE =
        local("Ok: displayed '%s' in the console when calling %s.")
        .fr("Ok: affiché '%s' dans la console en appelant %s.");
    public static Localizable<String> EXPECTED_TO_SEE_NOTHING_IN_CONSOLE_BUT_SAW_INSTEAD =
        local("Expected to not see anything more in the console when calling %s, but saw '%s' instead!")
        .fr("Attendu à ne plus rien voir dans la console en appelant %s, mais vu '%s' à la place!");
    public static Localizable<String> OK_ASKED_FOR_LINE_IN_CONSOLE =
        local("Ok: asked for a line in the console.")
        .fr("Ok: demandé du texte dans la console.");
    public static Localizable<String> EXPECTED_FOR_USER_TO_ANSWER_IN_CONSOLE =
        local("Expected the user to be able to answer in the console, but they were not!")
        .fr("Attendu à ce que l'utilisateur puisse répondre dans la console, mais iel ne la pas pu!");
    public static Localizable<String> EXPECTED_TO_RETURN_INT_BUT_RETURNED_NULL =
        local("Expected %s to return %s but returned null instead!")
        .fr("Attendu à ce que %s retourne %s mais a retourné null à la place!");
    public static Localizable<String> EXPECTED_TO_RETURN_INT_BUT_RETURNED_OTHER_TYPE =
        local("Expected %s to return an integer but returned a '%s' instead!")
        .fr("Attendu à ce que %s retourne un entier mais a retourné un '%s' à la place!");
    public static Localizable<String> EXPECTED_TO_RETURN_INT_BUT_RETURNED =
        local("Expected %s to return %s but returned %s instead!")
        .fr("Attendu à ce que %s retourne %s mais a retourné %s à la place!");
    public static Localizable<String> OK_RETURNED_INT =
        local("Ok: %s returned %s.")
        .fr("Ok: %s a retourné %s.");
    public static Localizable<String> EXPECTED_TO_RETURN_DOUBLE_BUT_RETURNED_NULL =
        local("Expected %s to return %s but returned null instead!")
        .fr("Attendu à ce que %s retourne %s mais a retourné null à la place!");
    public static Localizable<String> EXPECTED_TO_RETURN_DOUBLE_BUT_RETURNED_OTHER_TYPE =
        local("Expected %s to return a double but returned a '%s' instead!")
        .fr("Attendu à ce que %s retourne un décimal mais a retourné un '%s' à la place!");
    public static Localizable<String> EXPECTED_TO_RETURN_DOUBLE_BUT_RETURNED =
        local("Expected %s to return %s but returned %s instead!")
        .fr("Attendu à ce que %s retourne %s mais a retourné %s à la place!");
    public static Localizable<String> OK_RETURNED_DOUBLE =
        local("Ok: %s returned %s.")
        .fr("Ok: %s a retourné %s.");
    public static Localizable<String> EXPECTED_TO_RETURN_BOOLEAN_BUT_RETURNED_NULL =
        local("Expected %s to return %s but returned null instead!")
        .fr("Attendu à ce que %s retourne %s mais a retourné null à la place!");
    public static Localizable<String> EXPECTED_TO_RETURN_BOOLEAN_BUT_RETURNED_OTHER_TYPE =
        local("Expected %s to return a boolean but returned a '%s' instead!")
        .fr("Attendu à ce que %s retourne un booléen mais a retourné un '%s' à la place!");
    public static Localizable<String> EXPECTED_TO_RETURN_BOOLEAN_BUT_RETURNED =
        local("Expected %s to return %s but returned %s instead!")
        .fr("Attendu à ce que %s retourne %s mais a retourné %s à la place!");
    public static Localizable<String> OK_RETURNED_BOOLEAN =
        local("Ok: %s returned %s.")
        .fr("Ok: %s a retourné %s.");
    public static Localizable<String> EXPECTED_TO_RETURN_STRING_BUT_RETURNED_NULL =
        local("Expected %s to return \"%s\" but returned null instead!")
        .fr("Attendu à ce que %s retourne \"%s\" mais a retourné null à la place!");
    public static Localizable<String> EXPECTED_TO_RETURN_STRING_BUT_RETURNED_OTHER_TYPE =
        local("Expected %s to return a String but returned a %s instead!")
        .fr("Attendu à ce que %s retourne une String mais a retourné un '%s' à la place!");
    public static Localizable<String> EXPECTED_TO_RETURN_STRING_BUT_RETURNED =
        local("Expected %s to return \"%s\" but returned \"%s\" instead!")
        .fr("Attendu à ce que %s retourne \"%s\" mais a retourné \"%s\" à la place!");
    public static Localizable<String> OK_RETURNED_STRING =
        local("Ok: %s returned \"%s\".")
        .fr("Ok: %s a retourné \"%s\".");
    public static Localizable<String> EXPECTED_TO_RETURN_INT_ARRAY_BUT_RETURNED_NULL =
        local("Expected %s to return %s but returned null instead!")
        .fr("Attendu à ce que %s retourne %s mais a retourné null à la place!");
    public static Localizable<String> EXPECTED_TO_RETURN_INT_ARRAY_BUT_RETURNED_OTHER_TYPE =
        local("Expected %s to return a int[] but returned a %s instead!")
        .fr("Attendu à ce que %s retourne une int[] mais a retourné un '%s' à la place!");
    public static Localizable<String> EXPECTED_TO_RETURN_INT_ARRAY_BUT_RETURNED =
        local("Expected %s to return %s but returned %s instead!")
        .fr("Attendu à ce que %s retourne %s mais a retourné %s à la place!");
    public static Localizable<String> OK_RETURNED_INT_ARRAY =
        local("Ok: %s returned %s.")
        .fr("Ok: %s a retourné %s.");
    public static Localizable<String> EXPECTED_TO_RETURN_BUT_RETURNED_NULL =
        local("Expected %s to return %s but returned null instead!")
        .fr("Attendu à ce que %s retourne un %s mais a retourné null à la place!");
    public static Localizable<String> EXPECTED_TO_RETURN_BUT_RETURNED_OTHER_TYPE =
        local("Expected %s to return a %s but returned a %s instead!")
        .fr("Attendu à ce que %s retourne un %s mais a retourné un %s à la place!");
    public static Localizable<String> EXPECTED_TO_RETURN_BUT_RETURNED =
        local("Expected %s to return %s but returned %s instead!")
        .fr("Attendu à ce que %s retourne %s mais a retourné %s à la place!");
    public static Localizable<String> OK_RETURNED =
        local("Ok: %s returned %s.")
        .fr("Ok: %s a retourné %s.");
    public static Localizable<String> EXPECTED_TO_LOOK_LIKE_BUT_LOOKED_LIKE =
        local("Expected object to look like \"%s\" but looked like \"%s\" instead!")
        .fr("Attendu à ce que l'objet ressemble à \"%s\" mais ressemble à \"%s\" à la place!");
    public static Localizable<String> OK_LOOKED_LIKE =
        local("Ok: object looked like \"%s\".")
        .fr("Ok: l'object ressemble à \"%s\".");
    public static Localizable<String> EXPECTED_TO_RETURN_INT_FROM_RANDOM_BUT_RETURNED =
        local("Expected %s to return %d from random number %f but returned %d instead!")
        .fr("Attendu à ce que %s retourne %d à partir du nombre aléatoire %f mais a retourné %d à la place!");
    public static Localizable<String> OK_RETURNED_INT_FROM_RANDOM =
        local("Ok: %s returned %d from random number %f.")
        .fr("Ok: %s a retourné %d à partir du nombre aléatoire %f.");
    public static Localizable<String> EXPECTED_TO_RETURN_INT_FROM_RANDOMS_BUT_RETURNED =
        local("Expected %s to return %d from random numbers %s but returned %d instead!")
        .fr("Attendu à ce que %s retourne %d à partir des nombres aléatoires %s mais a retourné %d à la place!");
    public static Localizable<String> OK_RETURNED_INT_FROM_RANDOMS =
        local("Ok: %s returned %d from random numbers %s.")
        .fr("Ok: %s a retourné %d à partir du nombre aléatoire %s.");        
    public static Localizable<String> EXPECTED_FIELD_TO_BE_PRIVATE =
        local("Expected '%s' field in class %s to be private, but it is not.")
        .fr("Attendu à ce que le champ '%s' dans la classe %s soit privé, mais il ne l'est pas.");
    public static Localizable<String> EXPECTED_FIELD_TO_BE_FINAL =
        local("Expected '%s' field in class %s to be final, but it is not.")
        .fr("Attendu à ce que le champ '%s' dans la classe %s soit final, mais il ne l'est pas.");
    public static Localizable<String> EXPECTED_FIELD_TO_NOT_BE_FINAL =
        local("Expected '%s' field in class %s to not be final, but it is.")
        .fr("Attendu à ce que le champ '%s' dans la classe %s ne soit pas final, mais il l'est.");
    public static Localizable<String> EXPECTED_FIELD_TO_BE_OF_TYPE =
        local("Expected '%s' field in class %s to be a '%s', but it is a '%s'.")
        .fr("Attendu à ce que le champ '%s' dans la classe %s soit un '%s', mais il est un '%s'.");

}
