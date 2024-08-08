package frc;

import frc.RobotAutoScore;

public class AllianceAutoScore {

    private final RobotAutoScore robotAScore;

    private final RobotAutoScore robotBScore;

    private final RobotAutoScore robotCScore;

    public AllianceAutoScore(frc.RobotAutoScore a, RobotAutoScore b, RobotAutoScore c){

        robotAScore = a;

        robotBScore = b;

        robotCScore = c;

    }

    public int totalScore(){

        return robotAScore.totalScore() + robotBScore.totalScore() + robotCScore.totalScore();

    }
    
}
