package org.firstinspires.ftc.teamcode.Compitition.PowerPlay.controls.Autonomus.Test.GyroDriveTest.BlueLeftPark;

import org.firstinspires.ftc.teamcode.Compitition.PowerPlay.Robots.CompetionBot;
import org.firstinspires.ftc.teamcode.Compitition.PowerPlay.Robots.StraferBot;
import org.firstinspires.ftc.teamcode.Compitition.PowerPlay.controls.Autonomus.Test.GyroDriveTest.AutoMain;
import org.firstinspires.ftc.teamcode.Compitition.PowerPlay.controls.Autonomus.Test.GyroDriveTest.AutoTargetZone;

public abstract class BlueLeftPark extends AutoMain {

    boolean gyroTest = false;

    public void parkplace(CompetionBot Bot, AutoTargetZone target) throws InterruptedException {
        switch (target) {
            case A:

                break;

            case B:

                break;

            case C:

                break;

            case None:

                if (gyroTest == true) {

                    competitionGyroDriveTest(Bot);

                } else if (gyroTest == false) {

                    competitionEncoderDriveTest(Bot);

                } else {

                    telemetry.addLine("Error, test mode not defined.");

                }

                break;
        }
    }


    public void competitionGyroDriveTest (CompetionBot Bot) throws InterruptedException {

        Bot.driveForwardGyro(0.3, 3);
        sleep(sleepTime);

        Bot.driveBackwardGyro(0.3, 3);
        sleep(sleepTime);

    }

    public void competitionEncoderDriveTest (CompetionBot Bot) {

        Bot.driveForward_PID(0.3, 3);
        sleep(sleepTime);

        Bot.driveBackward_PID(0.3, 3);
        sleep(sleepTime);

    }

}
