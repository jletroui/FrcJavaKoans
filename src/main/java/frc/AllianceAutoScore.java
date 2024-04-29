package frc;

public class AllianceAutoScore {
    private final RobotAutoScore robotAScore;
    private final RobotAutoScore robotBScore;
    private final RobotAutoScore robotCScore;

    public AllianceAutoScore(RobotAutoScore robotAScore, RobotAutoScore robotBScore, RobotAutoScore robotCScore) {
        this.robotAScore = robotAScore;
        this.robotBScore = robotBScore;
        this.robotCScore = robotCScore;
    }

    public int totalScore() {
        return robotAScore.totalScore() + robotBScore.totalScore() + robotCScore.totalScore();
    }
}
