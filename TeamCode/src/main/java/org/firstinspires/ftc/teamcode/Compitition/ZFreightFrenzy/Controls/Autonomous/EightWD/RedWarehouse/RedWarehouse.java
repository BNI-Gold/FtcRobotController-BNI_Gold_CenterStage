package org.firstinspires.ftc.teamcode.Compitition.ZFreightFrenzy.Controls.Autonomous.EightWD.RedWarehouse;

import org.firstinspires.ftc.teamcode.Compitition.ZFreightFrenzy.Controls.Autonomous.EightWD.AutoMain;
import org.firstinspires.ftc.teamcode.Compitition.ZFreightFrenzy.Robots.EightWheelBot;

public abstract class RedWarehouse extends AutoMain {
    public long sleepTime = 100;

    public void driveToWare (EightWheelBot Bot) {
        Bot.driveForward(1,1.45);
        sleep(sleepTime);

        Bot.rotateLeft(1,1.3);
        sleep(sleepTime);

        Bot.driveForward(1,6);
        sleep(sleepTime);

    }



}
