package org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Controls.Autonomous.Tank.TestLab;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Controls.Autonomous.Tank.AutoMain;
import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Robots.TankBot;
import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.mechanisms.TSELocation;

public abstract class TestLab extends AutoMain {

    private double straightSpd = 0.6;
    private double turnEncoderSpd = 0.5;
    //        Speed .2 == too low for gyro turn
    private double turnGyro1 = 0.25;
    private double turnGyro2 = 0.3;

    public void driveTest (TankBot Bot, String Alliance, TSELocation location) {

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

                Bot.driveForward(1, 2.6);
                sleep(sleepTime);
                Bot.LyftExtend();
                sleep(2600);
                Bot.LyftStopMotors();
                // Motor retracts easily, so may slide back from sleep.
//                sleep(100);
                Bot.setBoxHolder_Release();
                sleep(500);
                Bot.setBoxHolder_Up();
                sleep(100);
                Bot.LyftRetract();
                sleep(1100);
                Bot.LyftStopMotors();
                sleep(sleepTime);




                break;
            case barcode2:
                Bot.pattern = RevBlinkinLedDriver.BlinkinPattern.BLUE;
                Bot.blinkinLedDriver.setPattern(Bot.pattern);
                telemetry.addData("LED: ", Bot.pattern);
                Bot.driveForward(1, 2.6);
                sleep(sleepTime);
                Bot.LyftExtend();
                sleep(2600);
                Bot.LyftStopMotors();
                // Motor retracts easily, so may slide back from sleep.
//                sleep(100);
                Bot.setBoxHolder_Release();
                sleep(500);
                Bot.setBoxHolder_Up();
                sleep(100);
                Bot.LyftRetract();
                sleep(1100);
                Bot.LyftStopMotors();
                sleep(sleepTime);
                break;
            case barcode3:
                Bot.pattern = RevBlinkinLedDriver.BlinkinPattern.GREEN;
                Bot.blinkinLedDriver.setPattern(Bot.pattern);
                telemetry.addData("LED: ", Bot.pattern);
                Bot.driveForward(1, 2.6);
                sleep(sleepTime);
                Bot.LyftExtend();
                sleep(2600);
                Bot.LyftStopMotors();
                // Motor retracts easily, so may slide back from sleep.
//                sleep(100);
                Bot.setBoxHolder_Release();
                sleep(500);
                Bot.setBoxHolder_Up();
                sleep(100);
                Bot.LyftRetract();
                sleep(1100);
                Bot.LyftStopMotors();
                sleep(sleepTime);
                break;
            default:
                Bot.pattern = RevBlinkinLedDriver.BlinkinPattern.RED;
                Bot.blinkinLedDriver.setPattern(Bot.pattern);
                telemetry.addData("LED: ", Bot.pattern);
                break;
        }


        telemetry.update();


//        Bot.driveForward(1, 2.5);
//        sleep(sleepTime);
    }

    public void testGyroClockWise (TankBot Bot) {
        Bot.rotateRight(.6, 2.8);
        sleep(sleepTime);
//        Speed .2 == too low
        Bot.gyroCorrection(.4, -90);
        sleep(sleepTime);
        Bot.gyroCorrection(.3, -90);
        sleep(sleepTime);


        Bot.driveBackward(.6, 2);
    }

    public void DriveToDuckSpinner (TankBot Bot, String Alliance) {
        if (Alliance.equals("Blue")) {
            Bot.driveForward(straightSpd, 2.6);
            sleep(sleepTime);
            Bot.rotateLeft(turnEncoderSpd, 1.6);
            sleep(sleepTime);
            Bot.gyroCorrection(turnGyro1, +90);
            sleep(sleepTime);
//            Bot.gyroCorrection(turnGyro2, +90);
//            sleep(sleepTime);

//          drive towards duck spinner
            Bot.driveBackward(straightSpd, 2.25);
            sleep(sleepTime);

//            spin towards duck spinner
            Bot.rotateRight(turnEncoderSpd, 0.6);
            sleep(sleepTime);

            Bot.gyroCorrection(turnGyro1, +45);
            sleep(sleepTime);
//            Bot.gyroCorrection(turnGyro2, +46);
//            sleep(sleepTime);

//            Drive to duck spinner.  Should be at it after this.
            Bot.driveBackward(0.4, 0.7, 2000);
            sleep(sleepTime);
            Bot.driveBackward(0.2, 0.4, 1000);
            sleep(sleepTime);
            Bot.driveBackward(0.15, 0.1, 500);
            sleep(sleepTime);
        }
        if (Alliance.equals("Red")) {

        }
    }

    public void DuckSpinnerToStorageUnit (TankBot Bot, String Alliance) {
        if (Alliance.equals("Blue")) {
            Bot.driveForward(0.6, 0.4);
            sleep(sleepTime);
            Bot.rotateRight(.5, 0.9);
            sleep(sleepTime);
            Bot.driveForward(0.6, 2.7);
            sleep(sleepTime);
            Bot.rotateLeft(.5, .15);
            sleep(sleepTime);
            Bot.driveForward(0.6, 2.3);
            sleep(sleepTime);
        }
        if (Alliance.equals("Red")) {

        }
    }



}
