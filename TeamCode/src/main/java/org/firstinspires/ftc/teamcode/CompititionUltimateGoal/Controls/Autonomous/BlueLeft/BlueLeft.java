package org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Controls.Autonomous.BlueLeft;

import org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Controls.Autonomous.AutoMain;
import org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Controls.Autonomous.StartPosition;
import org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Controls.Autonomous.TargetZone;
import org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Robots.CompetitionBot;
import org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Robots.LabBot;

public abstract class BlueLeft extends AutoMain {
    public void driveToTargetZone (CompetitionBot Bot, TargetZone target) {
        switch (target) {
            case A:
                Bot.driveBackward(0.5,7.75);
                sleep(500);
                Bot.gyroCorrection(0.2, 0);
                sleep(100);
                Bot.strafeLeft(0.5,1);
                sleep(500);
                break;
            case B:
                Bot.driveBackward(.5,10.5);
                sleep(500);
                Bot.gyroCorrection(0.2, 0);
                sleep(100);
                Bot.strafeLeft(0.5,3.5);
                sleep(500);
                Bot.gyroCorrection(0.2, 0);
                break;
            case C:
                Bot.driveBackward(.5,5);
                sleep(500);
                Bot.strafeLeft(0.5,.5);
                sleep(500);
                break;
        }
    }

    public void ParkLaunchLine (CompetitionBot Bot, TargetZone target) {
        switch (target) {
            case A:
//                Bot.gyroCorrection(45, .5);
//                No need to move - already parked on line ot score Wobble.
                break;
            case B:
                Bot.driveForward(0.5, 2.5);
                sleep(250);
                Bot.gyroCorrection(0.2, 0);
                sleep(100);
                Bot.driveForward(0.5, 0.5);
                sleep(100);
                Bot.gyroCorrection(0.2, 0);
                break;
            case C:
                Bot.driveForward(0.5, 4.5);
                sleep(250);
                break;
        }
    }
}
