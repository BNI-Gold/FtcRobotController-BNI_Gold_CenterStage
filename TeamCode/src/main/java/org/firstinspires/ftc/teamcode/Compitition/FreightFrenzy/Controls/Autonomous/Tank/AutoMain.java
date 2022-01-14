package org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Controls.Autonomous.Tank;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Robots.TankBot;

public abstract class AutoMain extends LinearOpMode {
    public long sleepTime = 250;

    //90 degree LEFT turn =
    //         Bot.rotateLeft(.5, 1.68);

    public void TestAuto (TankBot Bot) {
        Bot.driveForward(1, 10);
        sleep(sleepTime);
        Bot.driveBackward(1, 6);
        sleep(sleepTime);
        Bot.rotateLeft(0.5,10);
        sleep(sleepTime);
        Bot.rotateRight(0.5, 10);
        sleep(sleepTime);
    }

    public void spinDuckBlue (TankBot Bot) {
        Bot.duckspincounterclockwise();
        //Time to spin duck spinner!
        sleep(5000);
        Bot.duckspinstop();
        sleep(sleepTime);
    }

}
