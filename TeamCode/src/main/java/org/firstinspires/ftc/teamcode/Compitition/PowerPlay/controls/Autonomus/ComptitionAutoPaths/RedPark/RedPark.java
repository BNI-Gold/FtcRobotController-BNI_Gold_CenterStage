package org.firstinspires.ftc.teamcode.Compitition.PowerPlay.controls.Autonomus.ComptitionAutoPaths.RedPark;

import org.firstinspires.ftc.teamcode.Compitition.PowerPlay.Robots.CompetionBot;
import org.firstinspires.ftc.teamcode.Compitition.PowerPlay.controls.Autonomus.ComptitionAutoPaths.AutoMain;
import org.firstinspires.ftc.teamcode.Compitition.PowerPlay.controls.Autonomus.ComptitionAutoPaths.AutoTargetZone;

public abstract class RedPark extends AutoMain {
    public void parkplace (CompetionBot Bot, AutoTargetZone target) throws InterruptedException{
        switch (target) {
            case A:
                Bot.driveForward(.5,3);
                sleep(sleepTime);

                Bot.gyroCorrection(.2,0);
                sleep(sleepTime);

                Bot.strafeRight(.5,2.85);
                sleep(sleepTime);

                Bot.gyroCorrection(.2,0);
                sleep(sleepTime);
                break;

            case B:
                Bot.driveForward(.5,3);
                sleep(sleepTime);

                Bot.gyroCorrection(.2,0);
                sleep(sleepTime);
                break;

            case C:
                Bot.driveForward(.5,3);
                sleep(sleepTime);

                Bot.gyroCorrection(.2,0);
                sleep(sleepTime);

                Bot.strafeLeft(.5,2.85);
                sleep(sleepTime);

                Bot.gyroCorrection(.2,0);
                sleep(sleepTime);
                break;

        }
    }


    public void park (CompetionBot Bot) {


    }
}
