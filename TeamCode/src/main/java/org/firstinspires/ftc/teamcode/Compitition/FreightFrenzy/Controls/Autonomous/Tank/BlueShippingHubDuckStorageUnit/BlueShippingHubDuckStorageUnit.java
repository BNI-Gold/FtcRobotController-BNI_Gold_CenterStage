package org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Controls.Autonomous.Tank.BlueShippingHubDuckStorageUnit;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;

import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Controls.Autonomous.Tank.AutoMain;
import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Robots.TankBot;
import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.mechanisms.TSELocation;

public abstract class BlueShippingHubDuckStorageUnit extends AutoMain {

    //BlueShippingHubPark
    private double straightSpd = 0.6;
    private double turnEncoderSpd = 0.5;
    //        Speed .2 == too low for gyro turn
    private double turnGyro1 = 0.25;
    private double turnGyro2 = 0.3;


    public void DriveShippingHubScore (TankBot Bot, String Alliance, TSELocation location) {

        telemetry.addData("RectArea: ", Bot.myPipeline.getRectArea());
        telemetry.addData("Rect Midpoint X", Bot.myPipeline.getRectMidpointX());
        telemetry.addData("Rect Midpoint Y", Bot.myPipeline.getRectMidpointY());
        telemetry.addData("Barcode Location: ", location);
//        telemetry.addData("Barcode: ", Bot.barcode);
        telemetry.update();
        sleep(1000);


        switch (location) {
            case barcode1:
                Bot.pattern = RevBlinkinLedDriver.BlinkinPattern.YELLOW;
                Bot.blinkinLedDriver.setPattern(Bot.pattern);
                telemetry.addData("LED: ", Bot.pattern);

                Bot.driveForward(straightSpd, 2.0);

                // just commented out below so not raising lift every time.

                sleep(1000);
                Bot.LyftExtend();
                sleep(1600);
                Bot.setBoxHolder_Release();
                sleep(100);  //this allows the servo to lower while the motor is still engaged.
                Bot.LyftStopMotors();
                // Motor retracts easily, so may slide back from sleep.
//                sleep(100);
                sleep(500);
                Bot.setBoxHolder_Up();
                sleep(100);
                Bot.LyftRetract();
                sleep(1100);
                Bot.LyftStopMotors();
                Bot.setBoxHolder_Down();


                sleep(sleepTime);

                break;
            case barcode2:
                Bot.pattern = RevBlinkinLedDriver.BlinkinPattern.BLUE;
                Bot.blinkinLedDriver.setPattern(Bot.pattern);
                telemetry.addData("LED: ", Bot.pattern);
                Bot.driveForward(straightSpd, 2.3);
                sleep(1000);
                Bot.LyftExtend();
                sleep(700);
                Bot.setBoxHolder_Release();
                sleep(100);  //this allows the servo to lower while the motor is still engaged.
                Bot.LyftStopMotors();
                // Motor retracts easily, so may slide back from sleep.
//                sleep(100);
                sleep(500);
                Bot.setBoxHolder_Up();
                sleep(100);
                Bot.LyftRetract();
                sleep(700);
                Bot.LyftStopMotors();
                Bot.setBoxHolder_Down();
                sleep(sleepTime);
                Bot.driveBackward(straightSpd, 0.2);
                sleep(sleepTime);

                break;
            case barcode3:
                Bot.pattern = RevBlinkinLedDriver.BlinkinPattern.GREEN;
                Bot.blinkinLedDriver.setPattern(Bot.pattern);
                telemetry.addData("LED: ", Bot.pattern);
                Bot.driveForward(straightSpd, 2.5);
                sleep(1000);
                Bot.LyftExtend();
                sleep(500);
                Bot.setBoxHolder_Release();
                sleep(100);  //this allows the servo to lower while the motor is still engaged.
                Bot.LyftStopMotors();
                // Motor retracts easily, so may slide back from sleep.
//                sleep(100);
                sleep(500);
                Bot.setBoxHolder_Up();
                sleep(100);
                Bot.LyftRetract();
                sleep(400);
                Bot.LyftStopMotors();
                Bot.setBoxHolder_Down();
                sleep(sleepTime);
                Bot.driveBackward(straightSpd, 0.1);
                sleep(sleepTime);

                break;
            default:
                Bot.pattern = RevBlinkinLedDriver.BlinkinPattern.RED;
                Bot.blinkinLedDriver.setPattern(Bot.pattern);
                telemetry.addData("LED: ", Bot.pattern);
                break;
        }
        telemetry.update();
    }

    public void ShippingHubToDuck (TankBot Bot, String Alliance, TSELocation location) {

        Bot.rotateLeft(turnEncoderSpd, 1.7);
        sleep(sleepTime);
        Bot.gyroCorrection(turnGyro1, +90);
        sleep(sleepTime);
        Bot.driveBackward(straightSpd, 5.7);
        sleep(sleepTime);
        Bot.rotateRight(turnEncoderSpd, 0.6);
        sleep(sleepTime);
        Bot.gyroCorrection(turnGyro1, +45);
        sleep(sleepTime);

//            Drive to duck spinner.  Should be at it after this.
        Bot.driveBackward(0.4, 0.4, 1500);
        sleep(sleepTime);
        Bot.driveBackward(0.2, 0.3, 800);
        sleep(sleepTime);
        Bot.driveBackward(0.15, 0.2, 500);
        sleep(sleepTime);
        switch (location) {
            case barcode1:

                break;
            case barcode2:

                break;
            case barcode3:
                break;
        }


    }

    public void DuckSpinnerToStorageUnit (TankBot Bot) {
            Bot.driveForward(straightSpd, 0.4);
            sleep(sleepTime);
            Bot.rotateRight(turnEncoderSpd, 0.9);
            sleep(sleepTime);
            Bot.driveForward(straightSpd, 2.7);
            sleep(sleepTime);
            Bot.rotateLeft(turnEncoderSpd, .13);
            sleep(sleepTime);
            Bot.driveForward(straightSpd, 1.8);
            sleep(sleepTime);
    }

}
