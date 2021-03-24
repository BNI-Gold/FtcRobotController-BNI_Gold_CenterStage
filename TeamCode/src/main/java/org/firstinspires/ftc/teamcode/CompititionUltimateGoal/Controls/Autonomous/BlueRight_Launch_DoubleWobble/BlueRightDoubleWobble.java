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
//                first wobble was too far to inside - needs to go more towards wall for room for second wobble
//                Changed from -130 to -135
                Bot.gyroCorrection(0.2, -135);
                sleep(sleepTimeDrive);
                Bot.driveBackward(.3);
                sleep(700);
                Bot.stopMotors();
                sleep(sleepTimeDrive);
                break;
            case B:
                Bot.gyroCorrection(.2,0);
                sleep(sleepTimeDrive);
                Bot.driveForward(1,3.4);
                sleep(sleepTimeDrive);
                Bot.gyroCorrection(.2,0);
                sleep(sleepTimeDrive);
                Bot.strafeRight(.78,0.85);
                sleep(sleepTimeDrive);
                Bot.gyroCorrection(.2,0);
                sleep(sleepTimeDrive);
                break;
            case C:
                Bot.rotateRight(1,2.8);
                sleep(sleepTimeDrive);
                Bot.gyroCorrection(.2,-166);
                sleep(sleepTimeDrive);
                Bot.driveBackward(.7,3.8);
                sleep(sleepTimeDrive);
//                Bot.driveForward(0.5, 3.6);
//                sleep(sleepTimeDrive);
//                Bot.gyroCorrection(0.5, -120);
//                sleep(sleepTimeDrive);
//                Bot.gyroCorrection(0.2, -120);
//                sleep(sleepTimeDrive);
//                Bot.driveBackward(.4);
//                sleep(230);
//                Bot.stopMotors();
                break;
        }
    }

    public void ScoreRings (CompetitionBot Bot, TargetZone target) throws InterruptedException {
//        launch 1
        int launchSleep = 750;
//        double launchPower = 0.65;
        double launchVelocity = 1725;
//        was 1700
        Bot.LauncherOn(1750);
        sleep(1250);
        Bot.RingPull();
        sleep(launchSleep);
        Bot.RingPush();
//        Launch 2
        Bot.LauncherOn(launchVelocity);
        sleep(launchSleep);
        Bot.RingPull();
        sleep(launchSleep);
        Bot.RingPush();
//        Launch 3
        Bot.LauncherOn(launchVelocity);
        sleep(launchSleep);
        Bot.RingPull();
        sleep(launchSleep);
        Bot.RingPush();
        sleep(50);  //here so the ring mag doesn't get caught on the pusher.
//        ADD THIS SLEEP BACK IN IF NEEDED
//        sleep(launchSleep);
        Bot.LauncherOff(0);
        Bot.RingMagDown();
        sleep(launchSleep);
        Bot.gyroCorrection(0.2,0);
        sleep(sleepTimeDrive);
        Bot.CameraInit();
        sleep(sleepTimeDrive);
    }

    public void driveToLeftWobble (CompetitionBot Bot, TargetZone target) throws InterruptedException {
        switch (target) {
            case A:
//                was 1.8
//                IF SPINNING, MAKE LOWER ROTATIONS

                Bot.driveForward(0.8,1.7);
                sleep(sleepTimeDrive);
                Bot.rotateLeft(1, 1.4);
                sleep(sleepTimeDrive);
                // was at -18 - too far to the left changed to -24 to go to the right
                Bot.gyroCorrection(0.3, -22);
                sleep(sleepTimeDrive);
                Bot.gyroCorrection(0.2, -22);
                sleep(sleepTimeDrive);
//                Bot.WobbleArmLower(1);
//                sleep(sleepTimeDrive);
                Bot.driveBackward(1,.65);
                sleep(sleepTimeDrive);
                Bot.driveBackward(.2,.53);
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
                Bot.driveBackward(.8,2.7);
                sleep(sleepTimeDrive);
                Bot.WobbleOpen();
                sleep(sleepTimeDrive);

                break;
            case B:
                Bot.strafeLeft(.85,2.3);
                sleep(sleepTimeDrive);
                Bot.gyroCorrection(.2,0);
                sleep(sleepTimeDrive);
                Bot.driveBackward(1,5.78);
                sleep(sleepTimeDrive);
//                7.0 - was slightly to left
//                7.5 went more to left - updating to 6.5.
                Bot.gyroCorrection(.3,10);
                sleep(sleepTimeDrive);
                Bot.gyroCorrection(.2,10);
                sleep(sleepTimeDrive);
                Bot.driveBackward(.3,.5);
                sleep(sleepTimeDrive);
                Bot.WobbleClosed();
                sleep(600);
                Bot.WobbleArmRaised(1);
                sleep(200);
                Bot.WobbleArmStopMotors();
                Bot.driveForward(1,3);
                sleep(sleepTimeDrive);
                Bot.rotateLeft(1,2.5);
                sleep(sleepTimeDrive);
                Bot.driveBackward(0.6,1);
                sleep(sleepTimeDrive);
                Bot.WobbleOpen();
                sleep(sleepTimeDrive);

                break;
            case C:
                Bot.gyroCorrection(.2,-166);
                sleep(sleepTimeDrive);
                //was -168 before and hit wall on flip changed to -164 go left
                Bot.driveForward(1,4.2);
                sleep(sleepTimeDrive);
                Bot.rotateRight(.7,3.5);
                sleep(sleepTimeDrive);
                //4 before
//                to far to right - was angle 4
                //7 before
                Bot.gyroCorrection(0.2,18);
                sleep(sleepTimeDrive);
                Bot.driveBackward(.4,1.4);
                sleep(sleepTimeDrive);
                Bot.WobbleClosed();
                sleep(600);
                Bot.WobbleArmRaised(1);
                sleep(200);
                Bot.WobbleArmStopMotors();
                sleep(200);
                Bot.driveForward(1,1.4);
                sleep(sleepTimeDrive);
                Bot.rotateRight(1,3.35);
                sleep(sleepTimeDrive);
                //was 3.5
                Bot.gyroCorrection(.3,-170);
                sleep(sleepTimeDrive);
                //was -175 and went right changed to -170 and went left
//                Bot.rotateRight(.3,0.25);
//                sleep(sleepTimeDrive);
                //-165 before (more right)
                //-185 and 178 spirals robot outta control
                Bot.driveBackward(1,4.1);
                sleep(sleepTimeDrive);
                Bot.WobbleOpen();
                sleep(sleepTimeDrive);
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
                Bot.strafeLeft(1,5);
                sleep(sleepTimeDrive);
                break;
            case B:
                Bot.driveForward(1,.8);
                sleep(sleepTimeDrive);

                break;
            case C:
                Bot.driveForward(0.6,1);
                sleep(sleepTimeDrive);
                Bot.rotateLeft(0.3,1);
                sleep(sleepTimeDrive);
                Bot.driveForward(1,1.8);
                break;
        }
    }
}