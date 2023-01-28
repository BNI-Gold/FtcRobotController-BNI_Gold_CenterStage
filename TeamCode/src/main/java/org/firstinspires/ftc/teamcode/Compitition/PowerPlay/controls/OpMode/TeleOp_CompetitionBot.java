package org.firstinspires.ftc.teamcode.Compitition.PowerPlay.controls.OpMode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
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

    boolean driveNormal = true;

    int turretClockwise = 410;
    int turretCounterclocwise = -405;

    int liftRest = 0;
    int liftLow = 600;
    int liftMid = 1300;
    int liftHigh = 2000;

    int liftLevel = 0;
    boolean liftLevelAllow = true;
    boolean liftToggle = true;
    boolean turretEncoderCW = false;
    boolean turretEncoderCCW = false;
    boolean turretEncoderCollect = false;
    boolean turrentEncoder180 = false;

    double turretPowerEncoder = 0.3;
    double turretPowerManual = 0.1;
    double liftPowerUp = 1.0;
    double liftPowerDown = 0.2;

    public CompetionBot Bot = new CompetionBot();

    @Override
    public void init() {
        Bot.initRobot(hardwareMap);

//            Bot.grabberLiftOne = hardwareMap.get(DcMotorEx.class, "grabber_lift_one");
//            Bot.grabberLiftOne.setDirection(DcMotorSimple.Direction.FORWARD);
//            Bot.grabberLiftOne.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//            Bot.grabberLiftOne.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//            Bot.grabberLiftOne.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//            //Bot.grabberLiftOne.setPositionPIDFCoefficients(5);
//
//            Bot.grabberLiftTwo = hardwareMap.get(DcMotorEx.class, "grabber_lift_two");
//            Bot.grabberLiftTwo.setDirection(DcMotorSimple.Direction.REVERSE);
//            Bot.grabberLiftTwo.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//            Bot.grabberLiftTwo.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//            Bot.grabberLiftTwo.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//            //Bot.grabberLiftTwo.setPositionPIDFCoefficients(5);
//
//            Bot.turretPlatform = hardwareMap.get(DcMotorEx.class,"turret_motor");
//            Bot.turretPlatform.setDirection(DcMotorSimple.Direction.REVERSE);
//            Bot.turretPlatform.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//            Bot.turretPlatform.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//            Bot.turretPlatform.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


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
        } else if (!driveNormal) {
            driveButton();
        }

        grabberArmControl();
        grabberArmControlBumper();
        updateTelemetry();
        //turretMechanism();
        //liftMechanism();
        //liftMechanismEncoder();
        //liftControl();

    }

    public void updateTelemetry() {

        telemetry.addData("Turret Encoder Position: ", Bot.turretPlatform.getCurrentPosition());
        telemetry.addData("Lift One Encoder Position: ", Bot.grabberLiftOne.getCurrentPosition());
        telemetry.addData("Lift Two Encoder Position: ", Bot.grabberLiftTwo.getCurrentPosition());
        telemetry.addData("leftFront", Bot.frontLeftMotor.getCurrentPosition());
        telemetry.addData("leftRear", Bot.rearLeftMotor.getCurrentPosition());
        telemetry.addData("rightFront", Bot.frontRightMotor.getCurrentPosition());
        telemetry.addData("rightRear", Bot.rearRightMotor.getCurrentPosition());


    }

    public void liftMechanism () {
        if (gamepad2.left_stick_y >= 0.15) {
            leftStickYVal = gamepad2.left_stick_y;
            Bot.grabberLiftOne.setPower(-leftStickYVal);
            Bot.grabberLiftTwo.setPower(-leftStickYVal);
        }
        else if (gamepad2.left_stick_y <= -0.15) {
            leftStickYVal = gamepad2.left_stick_y;
            Bot.grabberLiftOne.setPower(leftStickYVal);
            Bot.grabberLiftTwo.setPower(leftStickYVal);
        }
        else {
            if (Bot.grabberLiftOne.getCurrentPosition() > 500) {
                Bot.grabberLiftOne.setPower(0.1);
            }
            else {
                Bot.grabberLiftOne.setPower(0);
            }

        }
    }

    public void liftMechanismEncoder () {
//        Only go to target position when press 'y'.
//        Allows P2 to get lift "target" position ready.
        if (gamepad2.left_stick_button) {
            switch (liftLevel) {
                case 0:
                    if (Bot.grabberLiftOne.getCurrentPosition() > liftRest) {
                        Bot.grabberLiftOne.setPower(liftPowerDown);
                    }
                    else {
                        Bot.grabberLiftOne.setPower(liftPowerUp);
                    }
                    Bot.grabberLiftOne.setTargetPosition(liftRest);
                    Bot.grabberLiftOne.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    break;
                case 1:
                    if (Bot.grabberLiftOne.getCurrentPosition() > liftLow) {
                        Bot.grabberLiftOne.setPower(liftPowerDown);
                    }
                    else {
                        Bot.grabberLiftOne.setPower(liftPowerUp);
                    }
                    Bot.grabberLiftOne.setTargetPosition(liftLow);
                    Bot.grabberLiftOne.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    break;
                case 2:
                    if (Bot.grabberLiftOne.getCurrentPosition() > liftMid) {
                        Bot.grabberLiftOne.setPower(liftPowerDown);
                    }
                    else {
                        Bot.grabberLiftOne.setPower(liftPowerUp);
                    }
                    Bot.grabberLiftOne.setTargetPosition(liftMid);
                    Bot.grabberLiftOne.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    break;
                case 3:
                    Bot.grabberLiftOne.setPower(liftPowerUp);
                    Bot.grabberLiftOne.setTargetPosition(liftHigh);
                    Bot.grabberLiftOne.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                default:
                    break;
            }
        }
    }

    public void liftControl () {
        if (gamepad2.b == true && liftLevelAllow == true) {
            if (liftLevel < 3) {
                liftLevel += 1;
            }
            liftLevelAllow = false;
            liftToggle = false;
        }
        else if (gamepad2.x == true && liftLevelAllow == true) {
            if (liftLevel > 0) {
                liftLevel -= 1;
            }
            liftToggle = false;
            liftLevelAllow = false;
        }
        else {  // The IF here makes it so lift* goes back to default 'false' ONLY when not pressing Trigger.
            if (gamepad2.b == false && gamepad2.x == false) {
                liftLevelAllow = true;
                liftToggle = true;
            }
        }
    }

    public void grabberArmControl() {
//        if (gamepad2.b) {
//
//            Bot.openGrabberArms();
//
//        }
//
//        if (gamepad2.x) {
//
//            Bot.closeGrabberArms();
//
//        }

        if (gamepad2.x) {

            Bot.coneIntake();
            Bot.intakeGrabberArms();

        } else if (gamepad2.a) {

            Bot.coneOuttake();
            Bot.intakeGrabberArms();

        } else if (gamepad2.b) {

            Bot.closeGrabberArms();
            Bot.intakeStop();

        } else if (gamepad2.y) {

            Bot.intakeStop();
            Bot.openGrabberArms();

        }

    }

    public void grabberArmControlBumper() {

        if (gamepad2.left_stick_y >= 0.1) {

            leftStickYVal = gamepad2.left_stick_y;
            Bot.retractGrabberLift(leftStickYVal * .7);

            /*
            "idea for encoder stop"
            if grabberLiftExtnd.encoder <= number{
            grabberLiftExtnd(power = 0 or brake power)

            "gravity should bring it down. if not"

            grabberLiftRetract(power = 0.05 or 0.1)

            use same principles for retract
             */

        } else if (gamepad2.left_stick_y <= -0.1) {

            leftStickYVal = gamepad2.left_stick_y;
            Bot.extendGrabberLift(leftStickYVal * 1);

        } else {

            Bot.stopGrabberLift();

        }

        if (gamepad2.right_stick_x >= 0.15) {

            rightStickXVal = gamepad2.right_stick_x;
            Bot.turretClockwise(rightStickXVal * 0.75);

        } else if (gamepad2.right_stick_x <= -0.15) {

            rightStickXVal = gamepad2.right_stick_x;
            Bot.turretCounterClockwise(-rightStickXVal * 0.75);

        } else {

            Bot.turretStop();

        }

    }

    public void turretMechanism () {
        int currentTurretEncoder = Bot.turretPlatform.getCurrentPosition();

        //
        // ARE WE USING ENCODER TURN?
        //

        if (gamepad2.left_trigger >= 0.2) {
            turretEncoderCCW = true;
        }
        if (gamepad2.right_trigger >= 0.2) {
            turretEncoderCW = true;
        }
        if (gamepad2.left_bumper) {
            turretEncoderCollect = true;
        }
        if (gamepad2.right_bumper) {
            turrentEncoder180 = true;
        }

        //
        //  MANUAL CONTROL OF TURRET
        //

//        if (gamepad2.left_bumper) {
//            Bot.turretPlatform.setPower(-turretPowerManual);
//        }
//        else if (gamepad2.right_bumper) {
//            Bot.turretPlatform.setPower(+turretPowerManual);
//        }


        //
        //  ENCODER CONTROL OF TURRET
        //

        else if (turretEncoderCW) {
            if (Bot.turretPlatform.getCurrentPosition() < turretClockwise) {
                Bot.turretPlatform.setPower(+turretPowerEncoder);
            }
            else {
                turretEncoderCW = false;
            }
        }

        else if (turretEncoderCCW) {
            if (Bot.turretPlatform.getCurrentPosition() > turretCounterclocwise) {
                Bot.turretPlatform.setPower(-turretPowerEncoder);
            }
            else {
                turretEncoderCCW = false;
            }
        }


        else if (turretEncoderCollect) {
//            if (Bot.turretPlatform.getCurrentPosition() > 0) {
//                if (Bot.turretPlatform.getCurrentPosition() > 0) {
//                    Bot.turretPlatform.setPower(-turretPowerEncoder);
//                }
//                else {
//                    turretEncoderCollect = false;
//                }
//            }
//
//            else if (Bot.turretPlatform.getCurrentPosition() < 0) {
//                if (Bot.turretPlatform.getCurrentPosition() < 0) {
//                    Bot.turretPlatform.setPower(+turretPowerEncoder);
//                }
//                else {
//                    turretEncoderCollect = false;
//                }
//            }


            if (Bot.turretPlatform.getCurrentPosition() >= 0) {
                if (Bot.turretPlatform.getCurrentPosition() >= 0) {
                    Bot.turretPlatform.setPower(-turretPowerEncoder);
                }
                else {
                    turretEncoderCollect = false;
                }

            }

            else if (Bot.turretPlatform.getCurrentPosition() < 0) {
                if (Bot.turretPlatform.getCurrentPosition() < 0) {
                    Bot.turretPlatform.setPower(+turretPowerEncoder);
                }
                else {
                    turretEncoderCollect = false;
                }
            }


            if (!turretEncoderCollect) {
                Bot.turretPlatform.setPower(0);
            }

        }
        else if (turrentEncoder180 == true) {
            if (Bot.turretPlatform.getCurrentPosition() >= 0) {

                if (Bot.turretPlatform.getCurrentPosition() < turretClockwise*2) {
                    Bot.turretPlatform.setPower(+turretPowerEncoder);
                }
                else {
                    turrentEncoder180 = false;
                }
            }
            else if (Bot.turretPlatform.getCurrentPosition() < 0) {
                if (Bot.turretPlatform.getCurrentPosition() > turretCounterclocwise*2) {
                    Bot.turretPlatform.setPower(-turretPowerEncoder);
                }
                else {
                    turrentEncoder180 = false;
                }
            }
        }
        else {
            Bot.turretPlatform.setPower(0);
            turrentEncoder180 = false;
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
