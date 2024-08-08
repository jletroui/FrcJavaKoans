package frc;

public class RobotAutoScore {
    
    private int notesInSpeaker = 0;

    private int notesInAmp = 0;

    public String toString(){

        return "RobotScore: notes in speaker = " + notesInSpeaker + "; notes in amp = " + notesInAmp;

    }

    public void noteScoredInSpeaker(){

        notesInSpeaker++;

    }

    public void noteScoredInAmp(){

        notesInAmp++;

    }

    public int totalScore(){

        return notesInSpeaker * 5 + notesInAmp * 2;

    }
    
}
