package org.firstinspires.ftc.teamcode.Compitition.PowerPlay.controls.Autonomus.ComptitionAutoPaths.BlueLeftPark.GyroDrive;

import org.firstinspires.ftc.teamcode.Compitition.PowerPlay.Robots.CompetionBot;
import org.firstinspires.ftc.teamcode.Compitition.PowerPlay.Robots.StraferBot;
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

                fiveConeAuto(Bot);

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
        moveToStack(Bot);

        sleep(sleepTime);

        grabConeOne(Bot);

        sleep(sleepTime);

        coneStackLow(Bot);

        sleep(sleepTime);

        //stacks second cone
        moveToStack(Bot);

        sleep(sleepTime);

        grabConeTwo(Bot);

        sleep(sleepTime);

        coneStackLow(Bot);

        sleep(sleepTime);

        //stacks third cone
        moveToStack(Bot);

        sleep(sleepTime);

        grabConeThree(Bot);

        sleep(sleepTime);

        coneStackLow(Bot);

        sleep(sleepTime);

        //stacks fourth cone
        moveToStack(Bot);

        sleep(sleepTime);

        grabConeFour(Bot);

        sleep(sleepTime);

        coneStackLow(Bot);

        sleep(sleepTime);

        //stacks fifth cone
        moveToStack(Bot);

        sleep(sleepTime);

        grabConeFive(Bot);

        sleep(sleepTime);

        coneStackLow(Bot);

        sleep(sleepTime);

        //prepares for parking
        parkFromLow(Bot);

        sleep(sleepTime);

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

    public void moveToStack(CompetionBot Bot) throws InterruptedException {

        //moves from position of scoring on low to the stack

        Bot.driveForwardGyro(0.4, 2.25);
        sleep(sleepTime);

    }

    public void coneStackLow(CompetionBot Bot) throws InterruptedException {

        //moves to low goal, scores

        Bot.driveBackwardGyro(0.4, 2);
        sleep(sleepTime);

        Bot.extendGrabberLift(0.7);
        sleep(500);
        Bot.stopGrabberLift();
        Bot.extendGrabberLift(0.4);

        Bot.turretCounterClockwise(0.5);
        sleep(700);
        Bot.turretStop();
        sleep(1000);

        Bot.openGrabberArms();
        sleep(sleepTime);

    }

    public void parkFromLow(CompetionBot Bot) {

        //moves from position of stacking final cone --> parking position 1 (can go from there into zone 2 or 3)

    }

    public void grabConeOne(CompetionBot Bot) {

        //grabs first cone (highest cone)

        Bot.closeGrabberArms();
        sleep(sleepTime);

        Bot.extendGrabberLift(0.8);
        sleep(600);

        Bot.extendGrabberLift(0.4);
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
