package org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Controls.Autonomous.Tank.RedShippingHubPark;

import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Controls.Autonomous.Tank.AutoMain;
import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Robots.TankBot;
import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.mechanisms.TSELocation;

public abstract class RedShippingHubPark extends AutoMain {

    public void DriveToShippingHub (TankBot Bot, TSELocation barcode) {


//        switch (barcode) {
//            case barcode1:
//
//        }

        Bot.driveForward(1, 2);
        sleep(sleepTime);
        Bot.rotateRight(1, 1);
        sleep(sleepTime);
        Bot.driveBackward(1, 3);
        sleep(sleepTime);
        Bot.duckspincounterclockwise();
        sleep(sleepTime);
        Bot.driveForward(1, 2);
        sleep(sleepTime);
        Bot.rotateRight(1, 0.5);
        sleep(sleepTime);
        Bot.driveForward(1, 3);
        sleep(sleepTime);
        Bot.rotateLeft(1, 1);
        sleep(sleepTime);
        Bot.driveForward(1, 0.5);
        sleep(sleepTime);
        Bot.senseLyftExtend();
        sleep(500);
        Bot.setBoxHolder_Release();
        sleep(1000);
        Bot.setBoxHolder_Up();
        sleep(sleepTime);
        Bot.senseLyftColapse();
        sleep(500);
        Bot.driveBackward(1, 2);
        sleep(sleepTime);
        Bot.rotateRight(1, 1);
        sleep(sleepTime);
        Bot.driveForward(1, 5);
        sleep(sleepTime);


    }


}
