package org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Controls.Autonomous.BlueRight;

import org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Controls.Autonomous.AutoMain;
import org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Controls.Autonomous.TargetZone;
import org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Robots.CompetitionBot;
import org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Robots.LabBot;

public abstract class BlueRight extends AutoMain {
    public void driveToZoneOne(CompetitionBot Bot, TargetZone target) throws InterruptedException {
        switch (target) {
            case A:
                Bot.driveForward(0.5, 1);
                sleep(sleepTimeDrive);
//                Bot.gyroCorrection(0.2, 180);  NO NEED BECAUSE JUST DO AN ENCODER TURN AND THEN GYRO CHECK
                sleep(sleepTimeDrive);
                Bot.rotateRight(0.6, 1.8);
                sleep(sleepTimeDrive);
                Bot.gyroCorrection(0.2, 90);
                sleep(sleepTimeDrive);
                Bot.driveBackward(.3);
                sleep (500);
                Bot.stopMotors();
//                Bot.driveGyroBackward(.4,0.1);
                sleep(sleepTimeDrive);
                break;
            case B:
//                  Bot.strafeLeft(0.5, 1);
//                  sleep(100);
//                  Bot.gyroCorrection(0.2, 0);
//                  sleep(100);
                Bot.rotateRight(0.6,3.4);
                sleep(sleepTimeDrive);
                Bot.driveBackward(.5);
                sleep(700);
                Bot.stopMotors();
                sleep(sleepTimeDrive);

//                Bot.strafeRight(0.3, 3);
//                sleep(100);
//                Bot.gyroCorrection(0.2, 0);
//                sleep(100);
                break;
            case C:
                Bot.driveForward(0.5, 4.5);
                sleep(sleepTimeDrive);
//                Bot.gyroCorrection(0.2, 180);  NO NEED BECAUSE JUST DO AN ENCODER TURN AND THEN GYRO CHECK
                sleep(sleepTimeDrive);
                Bot.rotateRight(0.6, 1.8);
                sleep(sleepTimeDrive);
                Bot.gyroCorrection(0.2, 90);
                sleep(sleepTimeDrive);
                Bot.driveBackward(.3);
                sleep (500);
                Bot.stopMotors();
                sleep(sleepTimeDrive);
////                Bot.driveBackward(.3,0.1);
////                sleep(sleepTimeDrive);
                break;
        }
    }

    public void ScoreRings (CompetitionBot Bot, TargetZone target) throws InterruptedException {
//        launch 1
        Bot.LauncherOn(.9);
        sleep(1000);
        Bot.RingPush();
        sleep(750);
        Bot.RingPull();
//        Launch 2
        Bot.LauncherOn(.9);
        sleep(1000);
        Bot.RingPush();
        sleep(750);
        Bot.RingPull();
//        Launch 3
        Bot.LauncherOn(.9);
        sleep(1000);
        Bot.RingPush();
        sleep(750);
        Bot.RingPull();
        sleep(750);
        Bot.LauncherOff(0);
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
                sleep(100);
                Bot.driveGyroBackward(0.5,1.5);
                sleep(100);
                Bot.gyroCorrection(0.2,0);
                sleep(100);
                Bot.driveGyroForward(.5,1);
                sleep(100);
                Bot.gyroCorrection(0.2,360);
                sleep(100);
                Bot.driveGyroBackward(0.5,2);
                sleep(100);
                break;
            case C:
                Bot.gyroCorrection(0.2,0);
                sleep(100);
                Bot.driveGyroForward(.5,9);
                sleep(100);
                Bot.gyroCorrection(0.2,0);
                break;
        }
    }

    public void driveToZoneTwo (CompetitionBot Bot, TargetZone target) throws InterruptedException {
        switch (target) {
            case A:
                Bot.gyroCorrection(0.2,360);
                sleep(100);
                Bot.driveGyroBackward(.5,5);
                sleep(100);
                Bot.gyroCorrection(0.2,0);
                sleep(100);
                Bot.strafeRight(0.3,0.5);
                sleep(100);
                Bot.gyroCorrection(0.2,0);
                sleep(100);
                break;
            case B:
                Bot.driveGyroBackward(0.5,2);
                sleep(100);
                Bot.gyroCorrection(0.2,360);
                sleep(100);
                Bot.driveGyroForward(0.5,3);
                sleep(100);
                Bot.gyroCorrection(0.2,0);
                sleep(100);
                Bot.strafeLeft(0.3,1);
                sleep(100);
                Bot.gyroCorrection(0.2,0);
                sleep(100);
                break;
            case C:
                Bot.gyroCorrection(0.2,360);
                sleep(100);
                Bot.driveGyroBackward(0.5,9.5);
                sleep(100);
                Bot.gyroCorrection(0.2,0);
                sleep(100);
                break;
        }
    }

    public void ParkLaunchLine(CompetitionBot Bot, TargetZone target) throws InterruptedException {
        switch (target) {
            case A:
                break;
            case B:
                Bot.stopMotors();
                sleep(sleepTimeDrive);
                break;
            case C:
                Bot.strafeRight(.5,4);
                sleep(100);
                break;
        }
    }
}