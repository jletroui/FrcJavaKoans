package sensei;

import static engine.Localizable.local;

import engine.Localizable;

public class Texts {
    // AboutConsoleAndVariables
    static final Localizable<String> IN_5_YEARS_YOU_WILL_BE = 
        local("In 5 years from now, you will be %s.")
        .fr("Dans 5 ans, tu auras %s ans.");
    static final Localizable<String> IN_10_YEARS_YOU_WILL_BE = 
        local("In 10 years from now, you will be %s.")
        .fr("Dans 10 ans, tu auras %s ans.");
    static final Localizable<String> IN_20_YEARS_YOU_WILL_BE = 
        local("In 20 years from now, you will be %s.")
        .fr("Dans 20 ans, tu auras %s ans.");
    static final Localizable<String> YOUR_NAME_IS_AND_YOUR_AGE_IS = 
        local("Your name is %s and your age is %s.")
        .fr("Ton nom est %s et ton âge est %s ans.");
    static final Localizable<String> WHAT_IS_YOUR_AGE = 
        local("What is your age?")
        .fr("Quel âge as tu?");
    static final Localizable<String> YOUR_NAME_IS = 
        local("Your name is:")
        .fr("Ton nom est:");
    static final Localizable<String> YOUR_NAME_IS_SINGLE_LINE = 
        local("Your name is: %s")
        .fr("Ton nom est: %s");
    static final Localizable<String> WHAT_IS_YOUR_NAME = 
        local("What is your name?")
        .fr("Quel est ton nom?");

    // AboutConditions
    static final Localizable<String> POSITIVE = 
        local("positive")
        .fr("positif");
    static final Localizable<String> NEGATIVE = 
        local("negative")
        .fr("négatif");
    static final Localizable<String> ZERO = 
        local("zero")
        .fr("zéro");
    static final Localizable<String> YOU_CHEAT = 
        local("You cheat!")
        .fr("Tu triches!");
    static final Localizable<String> GOT_EVERYTHING_RIGHT = 
        local("Congratulations! You got everything right!")
        .fr("Bravo, tu as tout bon!");        
    static final Localizable<String> OOPS = 
        local("Oops!")
        .fr("Ouch!");
    static final Localizable<String> YOU_FAILED = 
        local("You failed!")
        .fr("Échec!");        
    static final Localizable<String> YOU_PASS = 
        local("Congratulations! You pass!")
        .fr("Bravo, tu passes!");

    // AboutNot7Game
    static final Localizable<String> DO_YOU_WANT_TO_CONTINUE = 
        local("Do you want to continue [y/n]?")
        .fr("Voulez-vous continuer [o/n]?");
    static final Localizable<String> Y = 
        local("y")
        .fr("o");
    static final Localizable<String> N = 
        local("n")
        .fr("n");
    static final Localizable<String> YOU_THREW = 
        local("You threw %s and %s.")
        .fr("Vous avez lancé un %s et un %s.");
    static final Localizable<String> DO_YOU_WANT_TO_THROW_AGAIN = 
        local("Do you want to throw again [y/n]?")
        .fr("Voulez-vous continuer à lancer [o/n]?");
    static final Localizable<String> YOUR_RESULT_SO_FAR = 
        local("Your result so far: %s.")
        .fr("Votre résultat jusqu'à maintenant: %s.");            
    static final Localizable<String> OH_NO_NOT7_YOU_LOOSE = 
        local("Oh no, Not 7! You lose!")
        .fr("Oh non, Pas 7! Vous avez perdu!");
    static final Localizable<String> WELL_DONE_YOUR_SCORE_IS = 
        local("Well done, your score is %s!")
        .fr("Bravo, votre score est %s!");            
    static final Localizable<String> PLAYER_1_ITS_YOUR_TURN = 
        local("Player 1, it's your turn!")
        .fr("Joueur 1, c'est votre tour!");   
    static final Localizable<String> PLAYER_2_ITS_YOUR_TURN = 
        local("Player 2, it's your turn!")
        .fr("Joueur 2, c'est votre tour!"); 
    static final Localizable<String> PLAYER_1_WINS = 
        local("Congratulations, player 1 wins!!!")
        .fr("Bravo, le joueur 1 gagne!!!"); 
    static final Localizable<String> PLAYER_2_WINS = 
        local("Congratulations, player 2 wins!!!")
        .fr("Bravo, le joueur 2 gagne!!!"); 
    static final Localizable<String> TIE = 
        local("Tie!")
        .fr("Égalité!"); 
}

