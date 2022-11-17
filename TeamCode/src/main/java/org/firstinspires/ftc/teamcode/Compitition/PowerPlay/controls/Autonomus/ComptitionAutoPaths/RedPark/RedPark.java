package org.firstinspires.ftc.teamcode.Compitition.PowerPlay.controls.Autonomus.ComptitionAutoPaths.RedPark;

import org.firstinspires.ftc.teamcode.Compitition.PowerPlay.Robots.CompetionBot;
import org.firstinspires.ftc.teamcode.Compitition.PowerPlay.controls.Autonomus.ComptitionAutoPaths.AutoMain;
import org.firstinspires.ftc.teamcode.Compitition.PowerPlay.controls.Autonomus.ComptitionAutoPaths.AutoTargetZone;

public abstract class RedPark extends AutoMain {
    public void parkplace (CompetionBot Bot, AutoTargetZone target) throws InterruptedException{
        switch (target) {
            case A:
                Bot.driveForward(.5,1.4);
                sleep(sleepTime);

                Bot.gyroCorrection(.2,0);
                sleep(sleepTime);

                Bot.strafeLeft(.5,1.4);
                sleep(sleepTime);

                Bot.gyroCorrection(.2,0);
                sleep(sleepTime);

                Bot.driveForward(0.5, 0.5);
                sleep(sleepTime);

                break;

            case B:
                Bot.driveForward(.5,1.5);
                sleep(sleepTime);

                Bot.gyroCorrection(.2,0);
                sleep(sleepTime);

                Bot.driveForward(0.5, 0.5);
                sleep(sleepTime);

                break;

            case C:
                Bot.driveForward(.5,1.4);
                sleep(sleepTime);

                Bot.gyroCorrection(.2,0);
                sleep(sleepTime);

                Bot.strafeRight(.5,1.4);
                sleep(sleepTime);

                Bot.gyroCorrection(.2,0);
                sleep(sleepTime);

                Bot.driveForward(0.5, 0.5);
                sleep(sleepTime);

//            case None:
//                Bot.driveForward(0.5, 2.75);
//                sleep(sleepTime);
                //(not tested yet)

                break;

        }
    }


    public void park (CompetionBot Bot) {


    }

    public void redStackA (CompetionBot Bot) {

        Bot.strafeLeft(0.5, 0.3);
        sleep(sleepTime);

        Bot.gyroCorrection(0.5, 0);
        sleep(sleepTime);

        Bot.extendGrabberLift(0.5);
        sleep(2500);

        Bot.autoTurretCounterClockwise(0.3, 0.5);
        sleep(sleepTime);

        Bot.openGrabberArms();
        sleep(sleepTime);

        Bot.strafeRight(0.2, 0.35);
        sleep(sleepTime);

        Bot.gyroCorrection(0.5, 0);
        sleep(sleepTime);

        Bot.autoTurretClockwise(0.3, 0.5);
        sleep(sleepTime);

        Bot.retractGrabberLift(0.25);
        sleep(2200);

    }

    public void redStackB (CompetionBot Bot) {

        Bot.strafeLeft(0.5, 0.3);
        sleep(sleepTime);

        Bot.gyroCorrection(0.5, 0);
        sleep(sleepTime);

        Bot.extendGrabberLift(0.5);
        sleep(3000);

        Bot.autoTurretCounterClockwise(0.3, 0.5);
        sleep(sleepTime);

        Bot.openGrabberArms();
        sleep(sleepTime);

        Bot.strafeRight(0.2, 0.35);
        sleep(sleepTime);

        Bot.gyroCorrection(0.5, 0);
        sleep(sleepTime);

        Bot.autoTurretClockwise(0.3, 0.5);
        sleep(sleepTime);

        Bot.retractGrabberLift(0.25);
        sleep(2200);

    }

    public void redStackC (CompetionBot Bot) {

        Bot.strafeLeft(0.5, 0.3);
        sleep(sleepTime);

        Bot.gyroCorrection(0.5, 0);
        sleep(sleepTime);

        Bot.extendGrabberLift(0.5);
        sleep(3500);

        Bot.autoTurretCounterClockwise(0.3, 0.5);
        sleep(sleepTime);

        Bot.openGrabberArms();
        sleep(sleepTime);

        Bot.strafeRight(0.2, 0.35);
        sleep(sleepTime);

        Bot.gyroCorrection(0.5, 0);
        sleep(sleepTime);

        Bot.autoTurretClockwise(0.3, 0.5);
        sleep(sleepTime);

        Bot.retractGrabberLift(0.25);
        sleep(2200);

    }

}
