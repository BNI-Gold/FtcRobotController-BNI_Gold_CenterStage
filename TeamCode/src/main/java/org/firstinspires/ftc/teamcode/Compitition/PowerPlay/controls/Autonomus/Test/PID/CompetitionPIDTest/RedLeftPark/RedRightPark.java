package org.firstinspires.ftc.teamcode.Compitition.PowerPlay.controls.Autonomus.Test.PID.CompetitionPIDTest.RedLeftPark;

import org.firstinspires.ftc.teamcode.Compitition.PowerPlay.Robots.CompetionBot;
import org.firstinspires.ftc.teamcode.Compitition.PowerPlay.Robots.StraferBot;
import org.firstinspires.ftc.teamcode.Compitition.PowerPlay.controls.Autonomus.Test.PID.CompetitionPIDTest.AutoMain;
import org.firstinspires.ftc.teamcode.Compitition.PowerPlay.controls.Autonomus.Test.PID.CompetitionPIDTest.AutoTargetZone;

public abstract class RedRightPark extends AutoMain {
    public void parkplace (CompetionBot Bot, AutoTargetZone target) throws InterruptedException{
        switch (target) {
            case A:
                blueRightStack(Bot);
                sleep(sleepTime);

                Bot.strafeLeft_PID(0.6, 0.1);
                sleep(sleepTime);

                Bot.driveForward_PID(0.4, 2.2);
                sleep(sleepTime);

                Bot.rotateRight(0.4, 2.2);
                sleep(sleepTime);

                break;

            case B:
                blueRightStack(Bot);
                sleep(sleepTime);

                Bot.driveBackward_PID(.5,.1);
                sleep(sleepTime);

                Bot.rotateRight(0.4, 2.2);
                sleep(sleepTime);

                break;

            case C:
                blueRightStack(Bot);
                sleep(sleepTime);

                Bot.strafeLeft_PID(0.6, 0.1);
                sleep(sleepTime);

                Bot.driveBackward_PID(0.4, 2.5);
                sleep(sleepTime);

                break;

            case None:
                blueRightStack(Bot);
                sleep(sleepTime);

                Bot.driveBackward_PID(.5,.1);
                sleep(sleepTime);

                Bot.rotateRight(0.4, 2.2);
                sleep(sleepTime);

                break;
        }
    }

    public void parkplace (StraferBot BotStrafer, AutoTargetZone target) throws InterruptedException{
        switch (target) {
            case A:
                BotStrafer.driveForward_PID(.5,3);
                sleep(sleepTime);

                BotStrafer.gyroCorrection(.2,0);
                sleep(sleepTime);

                BotStrafer.strafeLeft_PID(.5,2.85);
                sleep(sleepTime);

                BotStrafer.gyroCorrection(.2,0);
                sleep(sleepTime);
                break;

            case B:
                BotStrafer.driveForward_PID(.5,3);
                sleep(sleepTime);

                BotStrafer.gyroCorrection(.2,0);
                sleep(sleepTime);
                break;

            case C:
                BotStrafer.driveForward_PID(.5,3);
                sleep(sleepTime);

                BotStrafer.gyroCorrection(.2,0);
                sleep(sleepTime);

                BotStrafer.strafeRight_PID(.5,2.85);
                sleep(sleepTime);

                BotStrafer.gyroCorrection(.2,0);
                sleep(sleepTime);
                break;

        }
    }

    public void park (CompetionBot Bot) {


    }

    public void blueRightStack(CompetionBot Bot) {

        Bot.driveForward_PID(.5,3);
        sleep(sleepTime);

        Bot.gyroCorrection(.3,0);
        sleep(sleepTime);

        Bot.rotateLeft(0.3, 0.5);
        sleep(sleepTime);

        Bot.gyroCorrection(0.3, 45);
        sleep(sleepTime);

        Bot.extendGrabberLift(0.7);
        sleep(2000);

        Bot.stopGrabberLift();
        sleep(sleepTime);

        Bot.extendGrabberLift(.2);
        sleep(700);

        Bot.driveForward_PID(.3,.35);
        sleep(sleepTime);

        Bot.gyroCorrection(.2,45);
        sleep(600);

        Bot.retractGrabberLift(0.2);
        sleep(500);

        Bot.openGrabberArms();
        sleep(sleepTime);

        Bot.stopGrabberLift();
        sleep(sleepTime);


        Bot.driveBackward_PID(.2,.6);
        sleep(sleepTime);

        Bot.retractGrabberLift(0.2);
        sleep(450);

        Bot.stopGrabberLift();
        sleep(sleepTime);

        Bot.rotateLeft(.5,.4);
        sleep(sleepTime);

        Bot.gyroCorrection(.2,90);
        sleep(sleepTime);
    }
}
