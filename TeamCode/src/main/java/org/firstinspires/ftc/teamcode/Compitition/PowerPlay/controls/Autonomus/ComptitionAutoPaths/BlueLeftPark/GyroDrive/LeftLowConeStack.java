package org.firstinspires.ftc.teamcode.Compitition.PowerPlay.controls.Autonomus.ComptitionAutoPaths.BlueLeftPark.GyroDrive;

import org.firstinspires.ftc.teamcode.Compitition.PowerPlay.Robots.CompetionBot;
import org.firstinspires.ftc.teamcode.Compitition.PowerPlay.controls.Autonomus.ComptitionAutoPaths.AutoMain;
import org.firstinspires.ftc.teamcode.Compitition.PowerPlay.controls.Autonomus.ComptitionAutoPaths.AutoTargetZone;

public abstract class LeftLowConeStack extends AutoMain {
    public void parkplace (CompetionBot Bot, AutoTargetZone target) throws InterruptedException{
        switch (target) {
            case A:

                break;

            case B:

                break;

            case C:

                break;

            case None:

                //fiveConeAuto(Bot);

                Bot.driveForwardGyro(0.5, 1);
                sleep(sleepTime);

                sleep(500);

                Bot.turretAuto90CW();

                sleep(1000);

                Bot.turretAutoCollect();
                sleep(sleepTime);

                break;
        }
    }

    public void fiveConeAuto(CompetionBot Bot) throws InterruptedException {

        sleep(sleepTime);
        //puts everything together

        //scores preloaded cone
        scoreHigh(Bot);

        sleep(sleepTime);

        //stacks first cone

        sleep(sleepTime);

        scoreConeOne(Bot);

    }

    public void scoreHigh(CompetionBot Bot) throws InterruptedException {

        //start of auto - goes to low goal, scores preloaded cone, gets in position for stacking on low

        Bot.driveForwardGyro(0.6,5.1);
        sleep(sleepTime);

        Bot.rotateRight(0.2, 1);
        sleep(sleepTime);

        Bot.gyroCorrection(0.2, -39);
        sleep(sleepTime);

        Bot.extendGrabberLift(1);
        sleep(3700);
        Bot.extendGrabberLift(0.4);
        sleep(sleepTime);

        Bot.driveForwardGyro(0.2, 0.5);
        sleep(sleepTime);

        Bot.stopGrabberLift();
        sleep(1200);

        Bot.openGrabberArms();
        sleep(sleepTime * 2);

        Bot.extendGrabberLift(0.4);
        sleep(sleepTime);

        Bot.driveBackwardGyro(0.2, 0.6);
        sleep(sleepTime);

        Bot.stopGrabberLift();
        sleep(sleepTime);

        Bot.retractGrabberLift(0.4);
        sleep(1500);

        Bot.stopGrabberLift();
        sleep(sleepTime);

        Bot.driveBackwardGyro(0.3, 0.1);
        sleep(sleepTime);

        Bot.rotateLeft(0.4, 1);
        sleep(sleepTime);

        Bot.gyroCorrection(0.3, 0);
        sleep(sleepTime);

        Bot.driveForwardGyro(0.2, 0.15);
        sleep(sleepTime);

        Bot.rotateLeft(0.4, 2);
        sleep(sleepTime);

        Bot.gyroCorrection(0.3, 90);
        sleep(sleepTime);

        Bot.driveForwardGyro(0.3, 1);
        sleep(sleepTime);

        Bot.gyroCorrection(0.3, 90);
        sleep(sleepTime);

    }

    public void parkFromLow(CompetionBot Bot) {

        //moves from position of stacking final cone --> parking position 1 (can go from there into zone 2 or 3)

    }

    public void scoreConeOne(CompetionBot Bot) throws InterruptedException {

        //grabs first cone (highest cone)

        Bot.driveForwardGyro(0.35, 1.6);
        sleep(sleepTime);

        Bot.closeGrabberArms();
        sleep(sleepTime);

        Bot.extendGrabberLift(0.8);
        sleep(600);

        Bot.extendGrabberLift(0.4);
        sleep(sleepTime);

        Bot.driveBackwardGyro(0.3, 1.5);
        sleep(sleepTime);

    }

    public void grabConeTwo(CompetionBot Bot) {

        //grabs second cone

    }

    public void grabConeThree(CompetionBot Bot) {

        //grabs third cone

    }

    public void grabConeFour(CompetionBot Bot) {

        //grabs fourth cone

    }

    public void grabConeFive(CompetionBot Bot) {

        //grabs fifth cone

    }

}
