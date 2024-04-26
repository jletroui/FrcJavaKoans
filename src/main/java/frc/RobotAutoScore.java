package frc;

public class RobotAutoScore {
    private int notesInSpeaker = 0;
    private int notesInAmp = 0;

    public void noteScoredInSpeaker() {
        notesInSpeaker++;
    }

    public void noteScoredInAmp() {
        notesInAmp++;
    }

    public int totalScore() {
        return notesInAmp*2 + notesInSpeaker*5;
    }

    public String toString() {
        return String.format("RobotScore: notes in speaker = %d; notes in amp = %d", notesInSpeaker, notesInAmp);
    }
}
