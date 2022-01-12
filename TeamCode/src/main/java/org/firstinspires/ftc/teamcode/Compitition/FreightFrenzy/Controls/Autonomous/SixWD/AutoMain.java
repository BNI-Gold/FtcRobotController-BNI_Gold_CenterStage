package org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Controls.Autonomous.SixWD;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Robots.SixWheelBot;

public abstract class AutoMain extends LinearOpMode {

    int sleepTime = 100;

    public void blue_carousel_spinDuck(SixWheelBot Bot) {

        Bot.driveForward(0.5, 1.5);
        sleep(sleepTime);

        Bot.rotateLeft(0.5, 1.1);
        sleep(sleepTime);

        Bot.driveBackward(0.5, 2.9);
        sleep(sleepTime);

        //second right/left is the pole the duck spinner is on


    }

    public void red_carousel_spinDuck(SixWheelBot Bot) {

        Bot.driveForward(0.5, 1.5);
        sleep(sleepTime);

        Bot.rotateRight(0.5, 1.1);
        sleep(sleepTime);

        Bot.driveBackward(0.5, 2.9);
        sleep(sleepTime);

        //second right/left is the pole the duck spinner is on
        Bot.SpinDuckBLeft();
        sleep(5000);

        Bot.StopSpinningDuckRight();
        Bot.StopSpinningDuckLeft();
        sleep(sleepTime);

    }

    public void Shipping_Hub_Score (SixWheelBot Bot) {

        Bot.senseLyftExtend();
        sleep(1000);

        Bot.setboxHolder2down();
        sleep(2000);
        Bot.setboxHolder2up();
        sleep(500);

        Bot.senseLyftcolapse();
        sleep(sleepTime);
    }
}
