package org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Controls.Autonomous.Tank.TestLab;

import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Controls.Autonomous.Tank.AutoMain;
import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Robots.TankBot;

public abstract class TestLab extends AutoMain {

    public void driveOffWall (TankBot Bot) {
        Bot.driveForward(1, 2);
        sleep(sleepTime);
        Bot.rotateRight(.6, 1);
        sleep(sleepTime);
        Bot.gyroCorrection(.2, 90);
        sleep(sleepTime);
    }



}
