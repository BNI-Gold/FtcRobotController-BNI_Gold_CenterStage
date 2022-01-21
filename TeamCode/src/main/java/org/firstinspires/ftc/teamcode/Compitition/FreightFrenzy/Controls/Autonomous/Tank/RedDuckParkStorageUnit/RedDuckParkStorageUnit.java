package org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Controls.Autonomous.Tank.RedDuckParkStorageUnit;

import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Controls.Autonomous.Tank.AutoMain;
import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Robots.TankBot;

public abstract class RedDuckParkStorageUnit extends AutoMain {


    private double straightSpd = 0.6;
    private double turnEncoderSpd = 0.5;
    //        Speed .2 == too low for gyro turn
    private double turnGyro1 = 0.25;
    private double turnGyro2 = 0.3;


    public void StartToDuckSpinner (TankBot Bot) {
        Bot.driveForward(0.6, 2.6);
        sleep(500);
        Bot.rotateRight(.5, 1.60);
        sleep(sleepTime);
        Bot.driveBackward(0.6, 2.3);
        sleep(sleepTime);
        Bot.rotateLeft(.5, 0.6);
        sleep(sleepTime);
        Bot.driveBackward(0.4, 0.7, 2000);
        sleep(sleepTime);
        Bot.driveBackward(0.2, 0.4, 1000);
        sleep(sleepTime);
        Bot.driveBackward(0.15, 0.1, 500);
        sleep(sleepTime);
    }

    public void DuckSpinnerToStorageUnit (TankBot Bot) {
        Bot.driveForward(0.6, 0.2);
        sleep(sleepTime);
        Bot.rotateLeft(0.5, 0.7);
        sleep(sleepTime);
        Bot.driveForward(0.6, 2.5);
        sleep(sleepTime);
//        Bot.rotateRight(0.5, 0.1);
//        sleep(sleepTime);
        Bot.driveForward(0.6, 2.4);
        sleep(sleepTime);
    }
}
