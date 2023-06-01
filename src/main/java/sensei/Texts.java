package sensei;

import static engine.Localizable.local;

import engine.Local;

public class Texts {
    // AboutConsoleAndVariables
    static final Local<String> IN_5_YEARS_YOU_WILL_BE = 
        local("In 5 years from now, you will be %s.")
        .fr("Dans 5 ans, tu auras %s.");
    static final Local<String> IN_10_YEARS_YOU_WILL_BE = 
        local("In 10 years from now, you will be %s.")
        .fr("Dans 10 ans, tu auras %s.");
    static final Local<String> IN_20_YEARS_YOU_WILL_BE = 
        local("In 20 years from now, you will be %s.")
        .fr("Dans 20 ans, tu auras %s.");
    static final Local<String> YOUR_NAME_IS_AND_YOUR_AGE_IS = 
        local("Your name is %s and your age is %s.")
        .fr("Ton nom est %s et ton âge est %s.");
    static final Local<String> WHAT_IS_YOUR_AGE = 
        local("What is your age?")
        .fr("Quel est ton âge?");
    static final Local<String> YOUR_NAME_IS = 
        local("Your name is:")
        .fr("Ton nom est:");
    static final Local<String> WHAT_IS_YOUR_NAME = 
        local("What is your name?")
        .fr("Quel est ton nom?");

    // AboutConditions
    static final Local<String> POSITIVE = 
        local("positive")
        .fr("positif");
    static final Local<String> NEGATIVE = 
        local("negative")
        .fr("négatif");
    static final Local<String> ZERO = 
        local("zero")
        .fr("zéro");
    static final Local<String> YOU_CHEAT = 
        local("You cheat!")
        .fr("Tu triches!");
    static final Local<String> GOT_EVERYTHING_RIGHT = 
        local("Congratulations! You got everything right!")
        .fr("Bravo, tu as tout bon!");        
    static final Local<String> OOPS = 
        local("Oops!")
        .fr("Ouch!");
    static final Local<String> YOU_FAILED = 
        local("You failed!")
        .fr("Échec!");        
    static final Local<String> YOU_PASS = 
        local("Congratulations! You pass!")
        .fr("Bravo, tu passes!");

    // AboutNot7Game
    static final Local<String> DO_YOU_WANT_TO_CONTINUE = 
        local("Do you want to continue [y/n]?")
        .fr("Voulez-vous continuer [y/n]?");
    static final Local<String> Y = 
        local("y")
        .fr("o");
    static final Local<String> N = 
        local("n")
        .fr("n");
    static final Local<String> YOU_THREW = 
        local("You threw %s and %s.")
        .fr("Vous avez lancé un %s et un %s.");
    static final Local<String> DO_YOU_WANT_TO_THROW_AGAIN = 
        local("Do you want to throw again [y/n]?")
        .fr("Voulez-vous continuer à lancer [y/n]? ");
    static final Local<String> YOUR_RESULT_SO_FAR = 
        local("Your result so far: %s.")
        .fr("Votre résultat jusqu'à maintenant: %s.");            
    static final Local<String> OH_NO_NOT7_YOU_LOOSE = 
        local("Oh no, Not 7! You loose!")
        .fr("Oh non, Pas 7! Vous avez perdu!");
    static final Local<String> WELL_DONE_YOUR_SCORE_IS = 
        local("Well done, your score is %s!")
        .fr("Bravo, votre score est %s!");            
    static final Local<String> PLAYER_1_ITS_YOUR_TURN = 
        local("Player 1, it's your turn!")
        .fr("Joueur 1, c'est votre tour!");   
    static final Local<String> PLAYER_2_ITS_YOUR_TURN = 
        local("Player 2, it's your turn!")
        .fr("Joueur 2, c'est votre tour!"); 
    static final Local<String> PLAYER_1_WINS = 
        local("Congratulations, player 1 wins!!!")
        .fr("Bravo, le joueur 1 gagne!!!"); 
    static final Local<String> PLAYER_2_WINS = 
        local("Congratulations, player 2 wins!!!")
        .fr("Bravo, le joueur 2 gagne!!!"); 
    static final Local<String> TIE = 
        local("Tie!")
        .fr("Égalité!"); 
}

