package org.firstinspires.ftc.teamcode.Compitition.PowerPlay.controls.Autonomus.ComptitionAutoPaths.BluePark;

import org.firstinspires.ftc.teamcode.Compitition.PowerPlay.Robots.CompetionBot;
import org.firstinspires.ftc.teamcode.Compitition.PowerPlay.Robots.StraferBot;
import org.firstinspires.ftc.teamcode.Compitition.PowerPlay.controls.Autonomus.ComptitionAutoPaths.AutoMain;
import org.firstinspires.ftc.teamcode.Compitition.PowerPlay.controls.Autonomus.ComptitionAutoPaths.AutoTargetZone;

public abstract class BluePark extends AutoMain {
    public void parkplace (CompetionBot Bot, AutoTargetZone target) throws InterruptedException{
        switch (target) {
            case A:
                Bot.driveForward(.5,1.42);
                sleep(sleepTime);

                Bot.gyroCorrection(.3,0);
                sleep(sleepTime);

                Bot.rotateRight(0.3, 0.4);
                sleep(sleepTime);

                Bot.gyroCorrection(0.3, -45);
                sleep(sleepTime);

                blueStackA(Bot);
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

                break;

//            case None:
//                Bot.driveForward(0.5, 2.75);
//                sleep(sleepTime);
            //(not tested yet)
        }
    }

    public void parkplace (StraferBot BotStrafer, AutoTargetZone target) throws InterruptedException{
        switch (target) {
            case A:
                BotStrafer.driveForward(.5,3);
                sleep(sleepTime);

                BotStrafer.gyroCorrection(.2,0);
                sleep(sleepTime);

                BotStrafer.strafeLeft(.5,2.85);
                sleep(sleepTime);

                BotStrafer.gyroCorrection(.2,0);
                sleep(sleepTime);
                break;

            case B:
                BotStrafer.driveForward(.5,3);
                sleep(sleepTime);

                BotStrafer.gyroCorrection(.2,0);
                sleep(sleepTime);
                break;

            case C:
                BotStrafer.driveForward(.5,3);
                sleep(sleepTime);

                BotStrafer.gyroCorrection(.2,0);
                sleep(sleepTime);

                BotStrafer.strafeRight(.5,2.85);
                sleep(sleepTime);

                BotStrafer.gyroCorrection(.2,0);
                sleep(sleepTime);
                break;

        }
    }

    public void park (CompetionBot Bot) {


    }

    public void blueStackA (CompetionBot Bot) {

        Bot.extendGrabberLift(0.7);
        sleep(2000);

        Bot.stopGrabberLift();
        sleep(sleepTime);

        sleep(700);

        Bot.driveForward(.3,.25);
        sleep(sleepTime);

        Bot.gyroCorrection(.2,-45);
        sleep(600);

        Bot.openGrabberArms();
        sleep(sleepTime);

        Bot.driveBackward(.2,.3);
        sleep(sleepTime);

        Bot.rotateRight(.5,.4);
        sleep(sleepTime);

        Bot.gyroCorrection(.2,-90);
        sleep(sleepTime);

        Bot.driveBackward(.5,1);
        sleep(sleepTime);



    }

    public void blueStackB (CompetionBot Bot) {

        Bot.strafeRight(0.5, 0.3);
        sleep(sleepTime);

        Bot.gyroCorrection(0.5, 0);
        sleep(sleepTime);

        Bot.extendGrabberLift(0.5);
        sleep(3000);

        Bot.autoTurretClockwise(0.3, 0.5);
        sleep(sleepTime);

        Bot.openGrabberArms();
        sleep(sleepTime);

        Bot.strafeLeft(0.2, 0.35);
        sleep(sleepTime);

        Bot.gyroCorrection(0.5, 0);
        sleep(sleepTime);

        Bot.autoTurretCounterClockwise(0.3, 0.5);
        sleep(sleepTime);

        Bot.retractGrabberLift(0.25);
        sleep(2200);

    }

    public void blueStackC (CompetionBot Bot) {

        Bot.strafeRight(0.5, 0.3);
        sleep(sleepTime);

        Bot.gyroCorrection(0.5, 0);
        sleep(sleepTime);

        Bot.extendGrabberLift(0.5);
        sleep(3500);

        Bot.autoTurretClockwise(0.3, 0.5);
        sleep(sleepTime);

        Bot.openGrabberArms();
        sleep(sleepTime);

        Bot.strafeLeft(0.2, 0.35);
        sleep(sleepTime);

        Bot.gyroCorrection(0.5, 0);
        sleep(sleepTime);

        Bot.autoTurretCounterClockwise(0.3, 0.5);
        sleep(sleepTime);

        Bot.retractGrabberLift(0.25);
        sleep(2200);

    }

}
