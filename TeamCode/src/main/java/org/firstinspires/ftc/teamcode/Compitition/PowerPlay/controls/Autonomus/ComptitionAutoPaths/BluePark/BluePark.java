package org.firstinspires.ftc.teamcode.Compitition.PowerPlay.controls.Autonomus.ComptitionAutoPaths.BluePark;

import org.firstinspires.ftc.teamcode.Compitition.PowerPlay.Robots.CompetionBot;
import org.firstinspires.ftc.teamcode.Compitition.PowerPlay.Robots.StraferBot;
import org.firstinspires.ftc.teamcode.Compitition.PowerPlay.controls.Autonomus.ComptitionAutoPaths.AutoMain;
import org.firstinspires.ftc.teamcode.Compitition.PowerPlay.controls.Autonomus.ComptitionAutoPaths.AutoTargetZone;

public abstract class BluePark extends AutoMain {
    public void parkplace (CompetionBot Bot, AutoTargetZone target) throws InterruptedException{
        switch (target) {
            case A:
                Bot.driveForward(.5,2.5);
                sleep(sleepTime);

                Bot.gyroCorrection(.2,0);
                sleep(sleepTime);

                Bot.strafeLeft(.5,3);
                sleep(sleepTime);

                Bot.gyroCorrection(.2,0);
                sleep(sleepTime);

                Bot.driveForward(0.5, 0.5);
                sleep(sleepTime);

                break;

            case B:
                Bot.driveForward(.5,2.5);
                sleep(sleepTime);

                Bot.gyroCorrection(.2,0);
                sleep(sleepTime);

                Bot.driveForward(0.5, 0.5);
                sleep(sleepTime);

                break;

            case C:
                Bot.driveForward(.5,2.5);
                sleep(sleepTime);

                Bot.gyroCorrection(.2,0);
                sleep(sleepTime);

                Bot.strafeRight(.5,3);
                sleep(sleepTime);

                Bot.gyroCorrection(.2,0);
                sleep(sleepTime);

                Bot.driveForward(0.5, 0.5);
                sleep(sleepTime);

                break;

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
}
