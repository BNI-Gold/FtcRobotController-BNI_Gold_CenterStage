package org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Controls.Autonomous.BlueLeftDoubleWobble;

import org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Controls.Autonomous.AutoMain;
import org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Controls.Autonomous.TargetZone;
import org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Robots.CompetitionBot;

public abstract class BlueLeftDoubleWobble extends AutoMain {
    public void driveToTargetZone (CompetitionBot Bot, TargetZone target) throws InterruptedException {
        switch (target) {
            case A:
                Bot.driveGyroBackward(0.5,5.5);
                sleep(sleepTimeDrive);
                Bot.gyroCorrection(0.2, 0);
                sleep(sleepTimeDrive);
                Bot.strafeLeft(.5,1);
                sleep(sleepTimeDrive);
                Bot.gyroCorrection(0.2, 0);
                sleep(sleepTimeDrive);
                Bot.driveGyroBackward(.5,1);
                sleep(sleepTimeDrive);
                Bot.gyroCorrection(0.2, 0);
                sleep(sleepTimeDrive);
                break;
            case B:
                Bot.driveGyroBackward(.5,7.5);
                sleep(sleepTimeDrive);
                Bot.gyroCorrection(0.2, 0);
                sleep(sleepTimeDrive);
                Bot.strafeLeft(0.5,3.5);
                sleep(sleepTimeDrive);
                Bot.gyroCorrection(0.2, 0);
                sleep(sleepTimeDrive);
                break;
            case C:
//                Bot.driveBackward(.5,5);
                Bot.driveGyroBackward(0.5, 9.3);
                sleep(sleepTimeDrive);
                Bot.gyroCorrection(0.2, 0);
                sleep(sleepTimeDrive);
                Bot.strafeLeft(0.5,1);
                sleep(sleepTimeDrive);
                Bot.gyroCorrection(0.2, 0);
                sleep(sleepTimeDrive);
                break;
        }
    }

    public void ParkLaunchLine (CompetitionBot Bot, TargetZone target) throws InterruptedException {
        switch (target) {
            case A:
                Bot.strafeLeft(.5,1);
                sleep(sleepTimeDrive);
                Bot.gyroCorrection(0.2, 0);
                sleep(sleepTimeDrive);
                Bot.driveGyroForward(.5,.5);
                sleep(sleepTimeDrive);
                Bot.gyroCorrection(0.2, 0);
                sleep(sleepTimeDrive);
//                No need to move - already parked on line ot score Wobble.
                break;
            case B:
                Bot.strafeLeft(.5,.5);
                sleep(sleepTimeDrive);
                Bot.gyroCorrection(0.2,0);
                sleep(sleepTimeDrive);
                Bot.driveGyroForward(0.5, 1.5);
                sleep(sleepTimeDrive);
                Bot.gyroCorrection(0.2, 0);
                sleep(sleepTimeDrive);
                break;
            case C:
//                Bot.driveForward(0.5, 4.5);
                Bot.strafeLeft(.5,1);
                sleep(sleepTimeDrive);
                Bot.gyroCorrection(0.2, 0);
                sleep(sleepTimeDrive);
                Bot.driveGyroForward(0.4, 3.5);
                sleep(sleepTimeDrive);
                Bot.gyroCorrection(0.2, 0);
                sleep(sleepTimeDrive);
                break;
        }
    }
}
