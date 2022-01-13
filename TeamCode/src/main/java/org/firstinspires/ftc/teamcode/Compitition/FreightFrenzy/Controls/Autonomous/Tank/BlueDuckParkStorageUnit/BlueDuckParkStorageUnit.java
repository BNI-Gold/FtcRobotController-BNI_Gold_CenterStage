package org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Controls.Autonomous.Tank.BlueDuckParkStorageUnit;

import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Controls.Autonomous.Tank.AutoMain;
import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Robots.TankBot;

public abstract class BlueDuckParkStorageUnit extends AutoMain {
    public long sleepTime = 100;

    public void StartToDuckSpinner (TankBot Bot) {
        Bot.driveForward(1, 2);
    }
}
