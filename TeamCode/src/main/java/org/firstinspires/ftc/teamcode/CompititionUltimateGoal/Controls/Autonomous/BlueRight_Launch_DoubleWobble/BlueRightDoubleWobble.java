package org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Controls.Autonomous.BlueRight_Launch_DoubleWobble;

import org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Controls.Autonomous.AutoMain;
import org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Controls.Autonomous.TargetZone;
import org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Robots.CompetitionBot;

public abstract class BlueRightDoubleWobble extends AutoMain {
    public void driveToZoneOne(CompetitionBot Bot, TargetZone target) throws InterruptedException {
        switch (target) {
            case A:

//                Line 13 is an example of an "encoder turn"  Use that, and then a gyroCorrection.
                Bot.rotateRight(1, 2.5);
                sleep(sleepTimeDrive);
                Bot.gyroCorrection(0.2, -125);
                sleep(sleepTimeDrive);
                Bot.driveBackward(.4);
                sleep(550);
                Bot.stopMotors();
                sleep(sleepTimeDrive);
                break;
            case B:
                Bot.gyroCorrection(.2,0);
                sleep(sleepTimeDrive);
                Bot.driveForward(1,3.9);
                sleep(sleepTimeDrive);
                Bot.gyroCorrection(.2,0);
                sleep(sleepTimeDrive);
                Bot.strafeRight(.4,1.3);
                sleep(sleepTimeDrive);
                Bot.gyroCorrection(.2,0);
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
        double launchPower = 0.65;
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
        Bot.LauncherOff(0);
        sleep(launchSleep);
        Bot.RingPush();
        sleep(launchSleep);
        Bot.RingMagDown();
        sleep(launchSleep);

    }

    public void driveToLeftWobble (CompetitionBot Bot, TargetZone target) throws InterruptedException {
        switch (target) {
            case A:
//                was 1.8
//                IF SPINNING, MAKE LOWER ROTATIONS
                Bot.driveForward(0.8,1);
                sleep(sleepTimeDrive);
                Bot.rotateLeft(1, 1.4);
                sleep(sleepTimeDrive);
                Bot.gyroCorrection(0.2, -25);
                sleep(sleepTimeDrive);
//                Bot.WobbleArmLower(1);
//                sleep(sleepTimeDrive);
                Bot.driveBackward(.8,1.6);
                Bot.driveBackward(.3,.3);
                sleep(sleepTimeDrive);
                Bot.WobbleClosed();
                sleep(400);
                Bot.WobbleArmRaised(1);
                sleep(200);
                Bot.WobbleArmStopMotors();
                Bot.rotateRight(.5,2.7);
                sleep(sleepTimeDrive);
                Bot.gyroCorrection(.2,-160);
                sleep(sleepTimeDrive);
                Bot.driveBackward(.8,2.5);
                sleep(sleepTimeDrive);
                Bot.WobbleOpen();
                sleep(sleepTimeDrive);

                break;
            case B:
                Bot.strafeLeft(.6,2.1);
                sleep(sleepTimeDrive);
                Bot.gyroCorrection(.2,0);
                sleep(sleepTimeDrive);
                Bot.driveBackward(1,6);
                sleep(sleepTimeDrive);
                Bot.gyroCorrection(.8,7);
                sleep(sleepTimeDrive);
                Bot.gyroCorrection(.2,7);
                sleep(sleepTimeDrive);
                Bot.driveBackward(.3,.5);
                sleep(sleepTimeDrive);
                Bot.WobbleClosed();
                sleep(400);
                Bot.WobbleArmRaised(1);
                sleep(200);
                Bot.WobbleArmStopMotors();

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
                Bot.driveForward(1,2);
                sleep(sleepTimeDrive);
                Bot.rotateLeft(1,.5);
                sleep(sleepTimeDrive);
                Bot.strafeLeft(1,4.5);
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