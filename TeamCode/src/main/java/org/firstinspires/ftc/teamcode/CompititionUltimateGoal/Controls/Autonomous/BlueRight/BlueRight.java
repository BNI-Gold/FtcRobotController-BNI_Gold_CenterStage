package org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Controls.Autonomous.BlueRight;

import org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Controls.Autonomous.AutoMain;
import org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Controls.Autonomous.TargetZone;
import org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Robots.CompetitionBot;
import org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Robots.LabBot;

public abstract class BlueRight extends AutoMain {
    public void driveToTargetZone(CompetitionBot Bot, TargetZone target) {
        switch (target) {
            case A:
                Bot.strafeLeft(0.5, 1);
                sleep(100);
                Bot.driveBackward(0.5, 8);
                sleep(100);
                Bot.strafeRight(0.5, 5);
                sleep(100);
                break;
            case B:
                Bot.strafeLeft(0.5, 1);
                sleep(100);
                Bot.driveBackward(0.5, 10);
                sleep(100);
                Bot.strafeRight(0.5, 2);
                sleep(100);
                break;

            case C:
                Bot.strafeLeft(0.5, 1);
                sleep(100);
                Bot.driveBackward(0.5, 14);
                sleep(100);
                Bot.strafeRight(0.5, 5);
                sleep(100);
                break;
        }
    }

    public void ParkLaunchLine(CompetitionBot Bot, TargetZone target) {
        switch (target) {
            case A:
                Bot.gyroCorrection(45, .5);
                break;

            case B:
                Bot.driveForward(0.5, 2.5);
                sleep(250);
                Bot.gyroCorrection(0.2, 0);
                sleep(100);
                break;

            case C:
                Bot.driveForward(0.4, 4);
                sleep(100);
                break;
        }
    }
}