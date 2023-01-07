package org.firstinspires.ftc.teamcode.Compitition.PowerPlay.controls.OpMode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Compitition.PowerPlay.Robots.CompetionBot;

@TeleOp (name = "Colonel Clap",group = "1")

public class TeleOp_CompetitionBot extends OpMode {

    double leftStickYVal;
    double leftStickXVal;
    double rightStickXVal;

    double frontLeftSpeed;
    double frontRightSpeed;
    double rearLeftSpeed;
    double rearRightSpeed;


    double powerThreshold = 0;
    double speedMultiply = 1;

    int turretClockwise = 417;
    int turretCounterclockwise = -417;
    int turretReverse = -810;

    int turretErrorMargin = 30;

    boolean turretEncoder180 = false;
    boolean turretEncoder90CCW = false;
    boolean turretEncoder90CW = false;
    boolean turretEncoderCollect = false;

    boolean driveNormal = true;

    double turretPowerEncoder = 0.3;
    double turretPowerManual = 0.5;

    public CompetionBot Bot=new CompetionBot();

    @Override
    public void init() {
        Bot.initRobot(hardwareMap);

    }

    @Override
    public void init_loop() {
    }
    @Override
    public void start() {
    }
    @Override
    public void loop() {
        if (gamepad1.right_bumper) {
            driveNormal = true;
        }
        if (gamepad1.left_bumper) {
            driveNormal = false;
        }
        if (driveNormal) {
            drive();
        }
        else if (!driveNormal) {
            driveButton();
        }

        grabberArmControl();
        grabberArmControlBumper();
        updateTelemetry();
        turretMechanism();

    }

    public void updateTelemetry () {

        telemetry.addData("Turret Encoder Position: ", Bot.turretPlatform.getCurrentPosition());
        telemetry.addData("Lift One Encoder Position: ", Bot.grabberLiftOne.getCurrentPosition());
        telemetry.addData("Lift Two Encoder Position: ", Bot.grabberLiftTwo.getCurrentPosition());
        telemetry.addData("leftA", Bot.frontLeftMotor.getCurrentPosition());
        telemetry.addData("leftB", Bot.rearLeftMotor.getCurrentPosition());
        telemetry.addData("rightA", Bot.frontRightMotor.getCurrentPosition());
        telemetry.addData("rightB", Bot.rearRightMotor.getCurrentPosition());


    }


    public void grabberArmControl() {
        if (gamepad2.b) {

            Bot.openGrabberArms();

        }

        if (gamepad2.x) {

            Bot.closeGrabberArms();

        }

//        if (gamepad2.a) {
//
//            Bot.coneIntake();
//
//        } else if (gamepad2.y) {
//
//            Bot.coneOuttake();
//
//        } else {
//
//            Bot.intakeStop();
//
//        }

    }

    public void grabberArmControlBumper() {

        if (gamepad2.left_stick_y >= 0.1) {

            leftStickYVal = gamepad2.left_stick_y;
            Bot.retractGrabberLift(leftStickYVal * .5);

            /*
            "idea for encoder stop"
            if grabberLiftExtnd.encoder <= number{
            grabberLiftExtnd(power = 0 or brake power)

            "gravity should bring it down. if not"

            grabberLiftRetract(power = 0.05 or 0.1)

            use same principles for retract
             */

        }

        else if (gamepad2.left_stick_y <= -0.1) {

            leftStickYVal = gamepad2.left_stick_y;
            Bot.extendGrabberLift(leftStickYVal * 1);

        }

        else {

            Bot.stopGrabberLift();

        }

    }

    public void turretMechanism() {

        //
        // ARE WE USING ENCODER TURN?
        //

//        IF Gamepad.x, turretEncoderCCW set to "True"
//       if (gamepad2.left_bumper) {
//
//            turretEncoder90CCW = true;
//
//        }
//
//       if (gamepad2.right_bumper) {
//
//            turretEncoder90CW = true;
//
//        }
////        Game pad.b - set turretEncoderCW to "true".
//        if (gamepad2.right_trigger > 0.2) {
//
//            turretEncoder180 = true;
////
//        }
//
////        IF gamepad.a - set  turretEncoderCollect to True
//        if (gamepad2.left_trigger > 0.2) {
//
//            turretEncoderCollect = true;
//
//        }
//
//        //
//        //  MANUAL CONTROL OF TURRET
//        //

        // IF gamepad.left bumper ..
        if (gamepad2.right_stick_x < -0.1) {

            Bot.turretPlatform.setPower(gamepad2.right_stick_x * turretPowerManual);

        }
//        else if gamepad2.right bumpter
        else if (gamepad2.right_stick_x > 0.1) {

            Bot.turretPlatform.setPower(gamepad2.right_stick_x * turretPowerManual);

        } else {

            Bot.turretPlatform.setPower(0);

        }


        //
        //  ENCODER CONTROL OF TURRET
        //

//        Check IF turretEncoderCW is "True"

        if (turretEncoder180) {
//            If our "turretPlatform.getCurrentPosition()" is less than the "turretClockwise", turn turretPlatform
            if (Bot.turretPlatform.getCurrentPosition() > turretReverse) {
                Bot.turretPlatform.setPower(-turretPowerEncoder);
            } else {
//                Need to set turretEncoderCW to "false" - this is how we know have reached target position.
                turretEncoder180 = false;
            }
        }

//        Same concept for "turretEncoderCCW"
        else if (turretEncoder90CCW) {
            if (Bot.turretPlatform.getCurrentPosition() < turretCounterclockwise - turretErrorMargin || Bot.turretPlatform.getCurrentPosition() < turretCounterclockwise + turretErrorMargin) {
                if (Bot.turretPlatform.getCurrentPosition() < turretCounterclockwise - turretErrorMargin || Bot.turretPlatform.getCurrentPosition() < turretCounterclockwise + turretErrorMargin) {

                    Bot.turretPlatform.setPower(turretPowerEncoder);

                } else {

                    turretEncoder90CCW = false;

                }

            } else if (turretEncoder90CW) {
                if (Bot.turretPlatform.getCurrentPosition() < turretClockwise - turretErrorMargin || Bot.turretPlatform.getCurrentPosition() < turretClockwise + turretErrorMargin) {
                    if (Bot.turretPlatform.getCurrentPosition() < turretClockwise - turretErrorMargin || Bot.turretPlatform.getCurrentPosition() < turretClockwise + turretErrorMargin) {

                        Bot.turretPlatform.setPower(-turretPowerEncoder);

                    } else {

                        turretEncoder90CW = false;

                    }

                } else if (Bot.turretPlatform.getCurrentPosition() > turretCounterclockwise + turretErrorMargin || Bot.turretPlatform.getCurrentPosition() > turretCounterclockwise - turretErrorMargin) {

                    if (Bot.turretPlatform.getCurrentPosition() > turretCounterclockwise + turretErrorMargin || Bot.turretPlatform.getCurrentPosition() > turretCounterclockwise - turretErrorMargin) {

                        Bot.turretPlatform.setPower(-turretPowerEncoder);

                    } else {

                        turretEncoder90CCW = false;

                    }

                } else {
//                Set turretEncoderCCW to false
                    turretEncoder90CCW = false;

                }
            }
        }


//      Same concept for "turretEncoderCollect"
//      need TWO code blocks for the "turretEncoderCollect" position:
//        One if turret is on the left, second if the turret is on the right.

        else if (turretEncoderCollect) {
//            Checking if the current Turret Platform Position is on the left
            if (Bot.turretPlatform.getCurrentPosition() > 0) {
//                Checking AGAIN if the current Turret Platform Position is > 0
                if (Bot.turretPlatform.getCurrentPosition() > 0) {
//                    Set the turretPlatform power to "turretPowerEncoder"
                    Bot.turretPlatform.setPower(-turretPowerEncoder);
                } else {
//                    set "turretEncoderCollect" to "False"
                    turretEncoderCollect = false;
                }
            }

//            Else if - same concept, but reverse (turret on right).
            else if (turretEncoderCollect) {
                if (Bot.turretPlatform.getCurrentPosition() < 0) {
//                    Set the turretPlatform power to "turretPowerEncoder"
                    Bot.turretPlatform.setPower(turretPowerEncoder);
                } else {
//                    set "turretEncoderCollect" to "False"
                    turretEncoderCollect = false;
                }
            }


//        Set power to 0!
            else {
                Bot.turretPlatform.setPower(0);

            }
        }
    }



    @Override
    public void stop() {
    }

    public void drive () {

        leftStickYVal = -gamepad1.left_stick_y;
        leftStickYVal = Range.clip(leftStickYVal, -1, 1);
        leftStickXVal = gamepad1.left_stick_x;
        leftStickXVal = Range.clip(leftStickXVal, -1, 1);
        rightStickXVal = gamepad1.right_stick_x;
        rightStickXVal = Range.clip(rightStickXVal, -1, 1);

        frontLeftSpeed = leftStickYVal + leftStickXVal + rightStickXVal;
        frontLeftSpeed = Range.clip(frontLeftSpeed, -1, 1);

        frontRightSpeed = leftStickYVal - leftStickXVal - rightStickXVal;
        frontRightSpeed = Range.clip(frontRightSpeed, -1, 1);

        rearLeftSpeed = leftStickYVal - leftStickXVal + rightStickXVal;
        rearLeftSpeed = Range.clip(rearLeftSpeed, -1, 1);

        rearRightSpeed = leftStickYVal + leftStickXVal - rightStickXVal;
        rearRightSpeed = Range.clip(rearRightSpeed, -1, 1);


        if (frontLeftSpeed <= powerThreshold && frontLeftSpeed >= -powerThreshold) {
            frontLeftSpeed = 0;
            Bot.frontLeftMotor.setPower(frontLeftSpeed * speedMultiply);
        } else {
            Bot.frontLeftMotor.setPower(frontLeftSpeed * speedMultiply);
        }

        if (frontRightSpeed <= powerThreshold && frontRightSpeed >= -powerThreshold){
            frontRightSpeed = 0;
            Bot.frontRightMotor.setPower(frontRightSpeed * speedMultiply);
        } else {
            Bot.frontRightMotor.setPower(frontRightSpeed * speedMultiply);
        }

        if (rearLeftSpeed <= powerThreshold && rearLeftSpeed >= -powerThreshold) {
            rearLeftSpeed = 0;
            Bot.rearLeftMotor.setPower(rearLeftSpeed * speedMultiply);
        } else {
            Bot.rearLeftMotor.setPower(rearLeftSpeed * speedMultiply);
        }

        if (rearRightSpeed <= powerThreshold && rearRightSpeed >= -powerThreshold){
            rearRightSpeed = 0;
            Bot.rearRightMotor.setPower(rearRightSpeed * speedMultiply);
        } else {
            Bot.rearRightMotor.setPower(rearRightSpeed * speedMultiply);
        }
    }

    public void driveButton () {
        double buttonPower = 1;
        if (gamepad1.y) {
            Bot.frontLeftMotor.setPower(buttonPower);
            Bot.frontRightMotor.setPower(buttonPower);
            Bot.rearLeftMotor.setPower(buttonPower);
            Bot.rearRightMotor.setPower(buttonPower);
        }
        else if (gamepad1.a) {
            Bot.frontLeftMotor.setPower(-buttonPower);
            Bot.frontRightMotor.setPower(-buttonPower);
            Bot.rearLeftMotor.setPower(-buttonPower);
            Bot.rearRightMotor.setPower(-buttonPower);
        }
        else if (gamepad1.x) {
            Bot.frontLeftMotor.setPower(-buttonPower);
            Bot.frontRightMotor.setPower(buttonPower);
            Bot.rearLeftMotor.setPower(buttonPower);
            Bot.rearRightMotor.setPower(-buttonPower);
        }
        else if (gamepad1.b) {
            Bot.frontLeftMotor.setPower(buttonPower);
            Bot.frontRightMotor.setPower(-buttonPower);
            Bot.rearLeftMotor.setPower(-buttonPower);
            Bot.rearRightMotor.setPower(buttonPower);
        }
        else {
            Bot.frontLeftMotor.setPower(0);
            Bot.frontRightMotor.setPower(0);
            Bot.rearLeftMotor.setPower(0);
            Bot.rearRightMotor.setPower(0);
        }

    }
}
