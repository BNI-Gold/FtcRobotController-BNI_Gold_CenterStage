package org.firstinspires.ftc.teamcode.MrAcker;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Compitition.ZFreightFrenzy.Robots.TankBot;

public abstract class AutoMain_Acker extends LinearOpMode {

    public long sleepTime = 250;

    public void TestAuto(AckerBot Bot) {
        Bot.driveForward(1, 3);
        sleep(sleepTime);
        Bot.driveBackward(1, 3);
        sleep(sleepTime);
        Bot.rotateLeft(0.5, 2);
        sleep(sleepTime);
        Bot.rotateRight(0.5, 3);
        sleep(sleepTime);
    }

    public void grabCone(AckerBot Bot) {
        double grabberPower = .50;
        long grabberSleep = 1000;
        Bot.openGrabberArms();
        sleep(grabberSleep);
        Bot.closeGrabberArms();
        sleep(sleepTime);
        Bot.extendGrabberLift(grabberPower);

    }

    public void dropCone(AckerBot Bot) {
        double grabberPower = .50;
        long grabberSleep = 1000;
        Bot.retractGrabberLift(grabberPower);
        Bot.openGrabberArms();
        sleep(grabberSleep);
    }

}
