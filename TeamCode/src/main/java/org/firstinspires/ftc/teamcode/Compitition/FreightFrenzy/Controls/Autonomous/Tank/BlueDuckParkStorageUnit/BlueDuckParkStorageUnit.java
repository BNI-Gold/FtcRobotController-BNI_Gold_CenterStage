package org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Controls.Autonomous.Tank.BlueDuckParkStorageUnit;

import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Controls.Autonomous.Tank.AutoMain;
import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Robots.TankBot;

public abstract class BlueDuckParkStorageUnit extends AutoMain {

    public void StartToDuckSpinner (TankBot Bot) {
        Bot.driveForward(0.6, 2.6);
        sleep(500);
        Bot.rotateLeft(.5, 1.68);
        sleep(sleepTime);
        Bot.driveBackward(0.6, 2.45);
        sleep(sleepTime);
        Bot.rotateRight(.5, 0.75);
        sleep(sleepTime);
        Bot.driveBackward(0.4, 0.5, 2000);
        sleep(sleepTime);
        Bot.driveBackward(0.2, 0.4, 1000);
        sleep(sleepTime);
        Bot.driveBackward(0.15, 0.1, 500);
        sleep(sleepTime);
    }

    public void DuckSpinnerToStorageUnit (TankBot Bot) {
        Bot.driveForward(0.6, 0.2);
        sleep(sleepTime);
        Bot.rotateRight(.5, .8);
        sleep(sleepTime);
        Bot.driveForward(0.6, 2.2);
        sleep(sleepTime);
        Bot.rotateLeft(.5, .1);
        sleep(sleepTime);
        Bot.driveForward(0.6, 2.2);
        sleep(sleepTime);
    }
}
