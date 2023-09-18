// Autonomous Abstract Class Learning Template for Competition 2022-2023
// Revision: 09-Oct-22
// Author: Jamie Acker

package org.firstinspires.ftc.teamcode.Personal_Spaces.MrAcker.NamelessBot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public abstract class AutoMain_Nameless extends LinearOpMode {

    // Autonomous Abstract
    public long sleepTime = 250;

    // Autonomous Tester Method

    public void TestAuto(NamelessBot Bot) {
        Bot.driveForward(1, 3);
        sleep(sleepTime);
        Bot.driveBackward(1, 3);
        sleep(sleepTime);
        Bot.rotateLeft(0.5, 2);
        sleep(sleepTime);
        Bot.rotateRight(0.5, 3);
        sleep(sleepTime);
    }

    // Resuable Cone Grabbing Method
    public void grabCone(NamelessBot Bot) {
        double grabberPower = .50;
        long grabberSleep = 1000;
        Bot.openGrabberArms();
        sleep(grabberSleep);
        Bot.closeGrabberArms();
        sleep(sleepTime);
        Bot.extendGrabberLift(grabberPower);

    }

    // Resuable Cone Dropping Method
    public void dropCone(NamelessBot Bot) {
        double grabberPower = .50;
        long grabberSleep = 1000;
        Bot.retractGrabberLift(grabberPower);
        Bot.openGrabberArms();
        sleep(grabberSleep);
    }

}
