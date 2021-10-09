package org.firstinspires.ftc.teamcode.Compitition.ZCompititionUltimateGoal.Controls.Autonomous.BlueRight_Launch_OneWobble;

import org.firstinspires.ftc.teamcode.Compitition.ZCompititionUltimateGoal.Controls.Autonomous.AutoMain;
import org.firstinspires.ftc.teamcode.Compitition.ZCompititionUltimateGoal.Controls.Autonomous.TargetZone;
import org.firstinspires.ftc.teamcode.Compitition.ZCompititionUltimateGoal.Robots.CompetitionBot;

public abstract class BlueRight extends AutoMain {
    public void driveToZoneOne(CompetitionBot Bot, TargetZone target) throws InterruptedException {
        switch (target) {
            case A:

                Bot.gyroCorrection(0.5, -120);
                sleep(sleepTimeDrive);
                Bot.gyroCorrection(0.2, -120);
                sleep(sleepTimeDrive);
                Bot.driveBackward(.3);
                sleep(550);
                Bot.stopMotors();
                sleep(sleepTimeDrive);
                break;
            case B:
                Bot.driveForward(0.3,2.3);
                sleep(sleepTimeDrive);
                Bot.gyroCorrection(.5,160);
                sleep(sleepTimeDrive);
                Bot.gyroCorrection(.2,170);
                sleep(sleepTimeDrive);
                break;
            case C:
                Bot.driveForward(0.5, 3.6);
                sleep(sleepTimeDrive);
             Bot.gyroCorrection(0.5, -120);
                sleep(sleepTimeDrive);
                Bot.gyroCorrection(0.2, -120);
                sleep(sleepTimeDrive);
                Bot.driveBackward(.4);
                sleep(230);
                Bot.stopMotors();
                break;
        }
    }

    public void ScoreRings (CompetitionBot Bot, TargetZone target) throws InterruptedException {
//        launch 1
        int launchSleep = 750;
        double launchPower = 0.6;
        Bot.LauncherOn(launchPower);
        sleep(launchSleep);
        Bot.RingPush();
        sleep(launchSleep);
        Bot.RingPull();
//        Launch 2
        Bot.LauncherOn(launchPower);
        sleep(launchSleep);
        Bot.RingPush();
        sleep(launchSleep);
        Bot.RingPull();
//        Launch 3
        Bot.LauncherOn(launchPower);
        sleep(launchSleep);
        Bot.RingPush();
        sleep(launchSleep);
        Bot.RingPull();
        sleep(launchSleep);
        Bot.LauncherOn(launchPower);
        sleep(launchSleep);
        Bot.RingPush();
        sleep(launchSleep);
        Bot.RingPull();
        Bot.LauncherOff(0);
        sleep(launchSleep);
    }

    public void driveToLeftWobble (CompetitionBot Bot, TargetZone target) throws InterruptedException {
        switch (target) {
            case A:
//                was 1.8
//                IF SPINNING, MAKE LOWER ROTATIONS
                Bot.driveForward(0.4,1);
                sleep(sleepTimeDrive);
                Bot.rotateLeft(0.6, 1.4);
                sleep(sleepTimeDrive);
                Bot.gyroCorrection(0.2, 179);
                sleep(sleepTimeDrive);

                Bot.strafeLeft(0.4, 1);
                sleep(sleepTimeDrive);
                Bot.gyroCorrection(0.2, 179);
                sleep(sleepTimeDrive);
                Bot.driveBackward(0.5, .5);
                sleep(sleepTimeDrive);
                Bot.gyroCorrection(0.2, 179);
                sleep(sleepTimeDrive);


//                Bot.gyroCorrection(0.2,-90);
//                sleep(100);
//                Bot.driveGyroBackward(.5,5);
//                sleep(100);
//                Bot.gyroCorrection(0.2,0);
//                sleep(100);
//                Bot.strafeLeft(0.3,1);
//                sleep(100);
//                Bot.gyroCorrection(0.2,0);
//                sleep(100);
                break;
            case B:
                Bot.gyroCorrection(0.2,-40);
                sleep(sleepTimeDrive);
                Bot.driveGyroBackward(0.5,1.5);
                sleep(sleepTimeDrive);
                Bot.gyroCorrection(0.2,0);
                sleep(sleepTimeDrive);
                Bot.driveGyroForward(.5,1);
                sleep(sleepTimeDrive);
                Bot.gyroCorrection(0.2,360);
                sleep(sleepTimeDrive);
                Bot.driveGyroBackward(0.5,2);
                sleep(sleepTimeDrive);
                break;
            case C:
                Bot.gyroCorrection(0.2,0);
                sleep(sleepTimeDrive);
                Bot.driveGyroForward(.5,9);
                sleep(sleepTimeDrive);
                Bot.gyroCorrection(0.2,0);
                break;
        }
    }

    public void driveToZoneTwo (CompetitionBot Bot, TargetZone target) throws InterruptedException {
        switch (target) {
            case A:
                Bot.gyroCorrection(0.2,360);
                sleep(sleepTimeDrive);
                Bot.driveGyroBackward(.5,5);
                sleep(sleepTimeDrive);
                Bot.gyroCorrection(0.2,0);
                sleep(sleepTimeDrive);
                Bot.strafeRight(0.3,0.5);
                sleep(sleepTimeDrive);
                Bot.gyroCorrection(0.2,0);
                sleep(sleepTimeDrive);
                break;
            case B:
                Bot.driveGyroBackward(0.5,2);
                sleep(sleepTimeDrive);
                Bot.gyroCorrection(0.2,360);
                sleep(sleepTimeDrive);
                Bot.driveGyroForward(0.5,3);
                sleep(sleepTimeDrive);
                Bot.gyroCorrection(0.2,0);
                sleep(sleepTimeDrive);
                Bot.strafeLeft(0.3,1);
                sleep(sleepTimeDrive);
                Bot.gyroCorrection(0.2,0);
                sleep(sleepTimeDrive);
                break;
            case C:
                Bot.gyroCorrection(0.2,360);
                sleep(sleepTimeDrive);
                Bot.driveGyroBackward(0.5,9.5);
                sleep(sleepTimeDrive);
                Bot.gyroCorrection(0.2,0);
                sleep(sleepTimeDrive);
                break;
        }
    }

    public void ParkLaunchLine(CompetitionBot Bot, TargetZone target) throws InterruptedException {
        switch (target) {
            case A:
                Bot.strafeLeft(.4,2.25);
                sleep(sleepTimeDrive);
                break;
            case B:

                break;
            case C:
//                Bot.strafeRight(.5,4);
//                sleep(100);
                Bot.gyroCorrection(0.5,20);
                sleep(sleepTimeDrive);
                Bot.driveBackward(0.6);
                sleep(1000);
                Bot.stopMotors();
                break;
        }
    }
}