package org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Controls.Autonomous.BlueRight_Launch_DoubleWobble;

import org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Controls.Autonomous.AutoMain;
import org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Controls.Autonomous.TargetZone;
import org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Robots.CompetitionBot;

public abstract class BlueRightDoubleWobble extends AutoMain {
    public void driveToZoneOne(CompetitionBot Bot, TargetZone target) throws InterruptedException {
//        int intakePower = 1;
//        int intakeSleep = 3000;
        switch (target) {
            case A:
//                Line 13 is an example of an "encoder turn"  Use that, and then a gyroCorrection.
                Bot.rotateRight(1, 2.2);
                sleep(sleepTimeDrive);
                //was 2.4
//                first wobble was too far to inside - needs to go more towards wall for room for second wobble
//                Changed from -130 to -135
                //increase angle for dropping wobble in targetzone completely
                Bot.gyroCorrection(0.2, -120);
                sleep(sleepTimeDrive);
//                Bot.driveBackward(.3);
                Bot.driveBackward(.3, 1.3);
//                sleep(950); //850 before
//                Bot.stopMotors();
                sleep(sleepTimeDrive);
                break;
            case B:
                Bot.gyroCorrection(.2,0);
                sleep(sleepTimeDrive);
                Bot.driveForward(1,3.6);
                sleep(sleepTimeDrive);
                // was 3.4
                Bot.gyroCorrection(.2,0);
                sleep(sleepTimeDrive);
//                Bot.strafeRight(.78,0.1); //.2 before
//                sleep(sleepTimeDrive);
                Bot.gyroCorrection(.2,0);
                sleep(sleepTimeDrive);
                break;

//                below is the code to intake the ring during auto and then double wobble
//                Bot.rotateRight(.5,4);
//                sleep(sleepTimeDrive);
//                Bot.IntakeOn(intakePower);
//                sleep(intakeSleep);
//                Bot.driveForward(.3,.8);
//                sleep(sleepTimeDrive);
//                Bot.rotateLeft(.5,4);
//                sleep(sleepTimeDrive);

            case C:
                Bot.rotateRight(1,2.8);
                sleep(sleepTimeDrive);
                Bot.gyroCorrection(.2,-155);
                sleep(sleepTimeDrive);
                //-150 before
                // more positive = leads robot toward wall
                Bot.driveBackward(.7,3.4); //3.5 before
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
        int launchSleep = 300;
//        double launchPower = 0.65;
        double launchVelocity = 1320;
//        was 1725
//        Bot.LauncherOn(1350);
        //1750 before
        sleep(sleepTimeDrive);
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
        Bot.LauncherOn(1315);
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
        Bot.CameraInit();
        sleep(sleepTimeDrive);
    }

    public void driveToLeftWobble (CompetitionBot Bot, TargetZone target) throws InterruptedException {
        switch (target) {
            case A:
//                was 1.8
//                IF SPINNING, MAKE LOWER ROTATIONS

                Bot.driveForward(0.8,1.85);
                sleep(sleepTimeDrive);
                Bot.rotateLeft(1, 1.4);
                sleep(sleepTimeDrive);
                // was at -18 - too far to the left changed to -24 to go to the right
                Bot.gyroCorrection(0.3, -31);
                sleep(sleepTimeDrive);
                Bot.gyroCorrection(0.2, -31);
                sleep(sleepTimeDrive);
                //-18 before too far left
//                Bot.WobbleArmLower(1);
//                sleep(sleepTimeDrive);
                Bot.driveBackward(1,1);
                sleep(sleepTimeDrive);
                //.9 before
                Bot.driveBackward(.2,.4);
                sleep(sleepTimeDrive);
                //.55 before
                Bot.WobbleClosed();
                sleep(400);
                Bot.WobbleArmRaised(1);
                sleep(200);
                Bot.WobbleArmStopMotors();
                Bot.rotateRight(.5,2);
                sleep(sleepTimeDrive);
                //1.6 before
                Bot.gyroCorrection(.2,-148);
                sleep(sleepTimeDrive);
                //-150 before
                //less negative = more toward targetzone A
                Bot.driveBackward(.8,2.4);
                sleep(sleepTimeDrive);
                Bot.WobbleOpen();
                sleep(sleepTimeDrive);
                break;
            case B:
                Bot.driveForward(.8,.3);
                sleep(sleepTimeDrive);
                Bot.strafeLeft(.85,2.4);
                sleep(sleepTimeDrive);
                //2.25 before
                Bot.gyroCorrection(.2,0);
                sleep(sleepTimeDrive);
                Bot.driveBackward(.8,5.6);
                sleep(sleepTimeDrive);
//                7.0 - was slightly to left
//                7.5 went more to left - updating to 6.5.
                Bot.gyroCorrection(.3,5); //16 before
                sleep(sleepTimeDrive);
                //angle = 8 before the angles.
                //angles will be 0-25
//                Bot.gyroCorrection(.2,24);
//                sleep(sleepTimeDrive);
                Bot.driveBackward(.3,.6);
                sleep(sleepTimeDrive);
                //.5 before
                Bot.WobbleClosed();
                sleep(600);
                Bot.WobbleArmRaised(1);
                sleep(200);
                Bot.WobbleArmStopMotors();
                Bot.gyroCorrection(.3,12);
                sleep(sleepTimeDrive);
                Bot.driveForward(.8,3);
                sleep(sleepTimeDrive);
                Bot.rotateLeft(.8,2.3);
                sleep(sleepTimeDrive);
                //was 1.8 before and right
                Bot.driveBackward(0.6,0.7);
                sleep(sleepTimeDrive);
                //1.6 before
                Bot.WobbleOpen();
                sleep(sleepTimeDrive);

                break;
            case C:
                Bot.gyroCorrection(.2,-170);
                sleep(sleepTimeDrive);
                //was -168 before and hit wall on flip changed to -164 go left
                Bot.driveForward(1,4.2);
                sleep(sleepTimeDrive);
                Bot.rotateRight(.7,3.5);
                sleep(sleepTimeDrive);
                //4 before
//                to far to right - was angle 4
                //7 before
                Bot.gyroCorrection(0.2,24);
                sleep(sleepTimeDrive);
                Bot.driveBackward(.4,.8); //1.4 before
                sleep(sleepTimeDrive);
                Bot.WobbleClosed();
                sleep(600);
                Bot.WobbleArmRaised(1);
                sleep(200);
                Bot.WobbleArmStopMotors();
                sleep(200);
                Bot.driveForward(1,1.4);
                sleep(sleepTimeDrive);
                Bot.rotateLeft(1,3.35);
                sleep(sleepTimeDrive);
                //was 3.5
                Bot.gyroCorrection(.3,-165);
                sleep(sleepTimeDrive);
                //was -175 and went right changed to -170 and went left
//                Bot.rotateRight(.3,0.25);
//                sleep(sleepTimeDrive);
                //-165 before (more right)
                //-185 and 178 spirals robot outta control
                Bot.driveBackward(1,3.5);
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
                Bot.strafeLeft(1,4.5);
                sleep(sleepTimeDrive);
                break;
            case B:
                Bot.driveForward(1,1);
                sleep(sleepTimeDrive);

                break;
            case C:
                Bot.driveForward(1,2.2);
                break;
        }
    }
}