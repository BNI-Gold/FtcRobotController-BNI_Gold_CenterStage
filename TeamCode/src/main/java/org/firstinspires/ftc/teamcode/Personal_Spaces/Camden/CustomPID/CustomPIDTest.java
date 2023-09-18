package org.firstinspires.ftc.teamcode.Personal_Spaces.Camden.CustomPID;

//import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Compitition.ZCompititionUltimateGoal.Robots.StraferKit;

@TeleOp (name = "C PID Test",group = "2")
public class CustomPIDTest extends OpMode {

    double integral = 0;
    double repetitions = 0;

    public static final double TICKS_PER_ROTATION = 383.6;

    public static PIDCoefficients testPID = new PIDCoefficients(0.4,0.6,0.2);

    public static double TARGET_POS = 100; // 100 is default value

    ElapsedTime PIDTimer = new ElapsedTime();

    public StraferKit Bot = new StraferKit();

    private DcMotorEx frontLeftMotor;
    private DcMotorEx frontRightMotor;
    private DcMotorEx grabberLiftOne;
    private DcMotorEx grabberLiftTwo;

    private DcMotorEx turretMotor;

    int testVariable;

    int desiredAngle = 0;

    double leftStickYVal;

    int liftRest = 0;
    int liftLow = 600;
    int liftMid = 1300;
    int liftHigh = 2000;

    int turret90CW = 800;
    int turret90CCW = -800;
    int turretCenter = 0;

    int turret180CW = 2000;
    int turret180CCW = -2000;

    double turretEncoderPower = 0.5;
    double referenceVariable;

    boolean rtpTest = true;

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

    boolean turretMoveAllow = false;

    boolean turretStopAllow = true;

    boolean liftStopAllow = true;

//    turret values:
//        0 = left 90
//        1 = centered
//        2 = right 90
//        3 = 180

    int turretPosition = 0;

    public static double speed = 1200; //arbitrary number; static to allow for analyzing how PID performs through multiple speeds in dashboard

    public static PIDCoefficients pidCoeffs = new PIDCoefficients(0.5, 0.4, 0.3); //PID coefficients that need to be tuned probably through FTC dashboard
    public PIDCoefficients pidGains = new PIDCoefficients(0.5, 0.4, 0.3); //PID gains which we will define later in the process

//    FtcDashboard dashboard;

    public void systemControl() {

        frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        if (gamepad1.right_bumper) {

            frontRightMotor.setTargetPosition((int) (5 * TICKS_PER_ROTATION));

            if (rtpTest == true) {

                Bot.driveForward_PIDRTP(0.5, 5);
                referenceVariable = 0.8;

            }

        }

        frontRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

                if (gamepad1.x) {

//                    PID(speed); //running the PID algorithm at defined speed

                } else {

                    referenceVariable = 0;

                }

            }

    void moveTestMotor(double targetPosition) {
        double error = frontRightMotor.getCurrentPosition();
        double lastError = 0;

        while (Math.abs(error) <= 9 * 500 /*Modify with above comments*/ && repetitions < 100 /*Modify*/) {
            error = frontRightMotor.getCurrentPosition() - targetPosition;
            double changeInError = lastError - error;
            integral += changeInError * PIDTimer.time();
            double derivative = changeInError / PIDTimer.time();
            double P = testPID.p * error;
            double I = testPID.i * integral;
            double D = testPID.d * derivative;
            frontRightMotor.setPower(P + I + D);
            error = lastError;
            PIDTimer.reset();
            repetitions ++;
        }

    }

//    double lastError = 0;
//    double integral = 0;
    //initializing our variables

//    public void PID(double targetVelocity){
//        PIDTimer.reset(); //resets the timer
//
//        double currentVelocity = frontRightMotor.getVelocity();
//        double error = targetVelocity - currentVelocity; //pretty self explanatory--just finds the error
//
//        double deltaError = error - lastError; //finds how the error changes from the previous cycle
//        double derivative = deltaError / PIDTimer.time(); //deltaError/time gives the rate of change (sensitivity of the system)
//
//        integral += error * PIDTimer.time();
//        //continuously sums error accumulation to prevent steady-state error (friction, not enough p-gain to cause change)
//
//        pidGains.p = error * pidCoeffs.p;
//        //acts directly on the error; p-coefficient identifies how much to act upon it
//        // p-coefficient (very low = not much effect; very high = lots of overshoot/oscillations)
//        pidGains.i = integral * pidCoeffs.i;
//        //multiplies integrated error by i-coefficient constant
//        // i-coefficient (very high = fast reaction to steady-state error but lots of overshoot; very low = slow reaction to steady-state error)
//        // for velocity, because friction isn't a big issue, only reason why you would need i would be for insufficient correction from p-gain
//        pidGains.d = derivative * pidCoeffs.d;
//        //multiplies derivative by d-coefficient
//        // d-coefficient (very high = increased volatility; very low = too little effect on dampening system)
//
//        frontRightMotor.setVelocity(pidGains.p + pidGains.i + pidGains.d + targetVelocity);
//        //adds up the P I D gains with the targetVelocity bias
//
//        lastError = error;
//        //makes our current error as our new last error for the next cycle
//    }

    public void updateTelemetry() {

//        dashboard = FtcDashboard.getInstance();
//
//        telemetry = dashboard.getTelemetry();

        leftStickYVal = gamepad2.left_stick_y;

//        telemetry.addData("Front Left: ", frontLeftMotor.getVelocity());
//        telemetry.addData("Front Right: ", frontRightMotor.getVelocity());
//        telemetry.addData("Rear Left: ", rearLeftMotor.getVelocity());
//        telemetry.addData("Rear Right: ", rearRightMotor.getVelocity());


//        telemetry.addData("Test Var: ", testVariable);
//        telemetry.addData("Left Stick Y Val: ", leftStickYVal);
//
//        telemetry.addData("Desired Angle: ", desiredAngle);

//        telemetry.addData("Lift Motor One: ", grabberLiftOne.getCurrentPosition());
//        telemetry.addData("Lift Motor Two: ", grabberLiftTwo.getCurrentPosition());
//
//        telemetry.addData("Lift Level Selected: ", liftLevel);
//        telemetry.addData("Lift Moving? ", liftLevelAllow);

//        telemetry.addData("Turret Motor Position: ", turretMotor.getCurrentPosition());
//        telemetry.addData("Turret Motor Velo: ", turretMotor.getVelocity());
//
//        telemetry.addData("Turret Position Input: ", turretPosition);
//
//        telemetry.addData("Right Stick X: ", gamepad2.right_stick_x);

//        telemetry.addData("Robot Velocity: ", (frontRightMotor.getVelocity()) * -1);
//        telemetry.addData("Target Velocity: ", 250);

        telemetry.addData("Robot Position: ", frontRightMotor.getCurrentPosition() / TICKS_PER_ROTATION);
        telemetry.addData("Target Position: ", 5);

        telemetry.addData("Robot Velocity: ", frontRightMotor.getVelocity());
        telemetry.addData("Theoretical Linear Velocity: ", 0.5);

    }

    @Override
    public void init() {

        Bot.initRobot(hardwareMap);

//        frontLeftMotor = hardwareMap.get(DcMotorEx.class, "null");
//
//        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
//
//        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//
        frontRightMotor = hardwareMap.get(DcMotorEx.class, "front_right_motor");

        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        frontRightMotor.setPositionPIDFCoefficients(1);
        frontRightMotor.setVelocityPIDFCoefficients(0.8,0.4,1,1);

//        grabberLiftOne = hardwareMap.get(DcMotorEx.class, "front_right_motor");
//
//        grabberLiftOne.setDirection(DcMotorSimple.Direction.FORWARD);
//
//        grabberLiftOne.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        grabberLiftOne.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//
//        grabberLiftTwo = hardwareMap.get(DcMotorEx.class, "rear_right_motor");
//
//        grabberLiftTwo.setDirection(DcMotorSimple.Direction.FORWARD);
//
//        grabberLiftTwo.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        grabberLiftTwo.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

//        turretMotor = hardwareMap.get(DcMotorEx.class, "rear_right_motor");
//
//        turretMotor.setDirection(DcMotorSimple.Direction.FORWARD);
//
//        turretMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        turretMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void liftMechanismEncoderNew() {
//        Only go to target position when press 'y'.
//        Allows P2 to get lift "target" position ready.
        if (gamepad2.left_stick_button) {
            switch (liftLevel) {
                case 0:
                    if (grabberLiftOne.getCurrentPosition() > liftRest) {
                        grabberLiftOne.setPower(liftPowerDown);
                        grabberLiftTwo.setPower(liftPowerDown);
                    } else {
                        grabberLiftOne.setPower(liftPowerUp);
                        grabberLiftTwo.setPower(liftPowerUp);
                    }
                    grabberLiftOne.setTargetPosition(liftRest);
                    grabberLiftOne.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                    grabberLiftTwo.setTargetPosition(liftRest);
                    grabberLiftTwo.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                    break;

                case 1:
                    if (grabberLiftOne.getCurrentPosition() > liftLow) {
                        grabberLiftOne.setPower(liftPowerDown);
                        grabberLiftTwo.setPower(liftPowerDown);
                    } else {
                        grabberLiftOne.setPower(liftPowerUp);
                        grabberLiftTwo.setPower(liftPowerUp);
                    }
                    grabberLiftOne.setTargetPosition(liftLow);
                    grabberLiftOne.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                    grabberLiftTwo.setTargetPosition(liftLow);
                    grabberLiftTwo.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                    break;

                case 2:
                    if (grabberLiftOne.getCurrentPosition() > liftMid) {
                        grabberLiftOne.setPower(liftPowerDown);
                        grabberLiftTwo.setPower(liftPowerDown);
                    } else {
                        grabberLiftOne.setPower(liftPowerUp);
                        grabberLiftTwo.setPower(liftPowerUp);
                    }
                    grabberLiftOne.setTargetPosition(liftMid);
                    grabberLiftOne.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                    grabberLiftTwo.setTargetPosition(liftMid);
                    grabberLiftTwo.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                    break;

                case 3:
                    grabberLiftOne.setPower(liftPowerUp);
                    grabberLiftOne.setTargetPosition(liftHigh);
                    grabberLiftOne.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                    grabberLiftTwo.setPower(liftPowerUp);
                    grabberLiftTwo.setTargetPosition(liftHigh);
                    grabberLiftTwo.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                default:
                    break;
            }
        }

        if (liftLevel == 0 && (grabberLiftOne.getCurrentPosition() <= 100 || grabberLiftTwo.getCurrentPosition() <= 100)) {

            grabberLiftOne.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            grabberLiftTwo.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        }

    }

    public void liftControlEncoder() {
        if (gamepad2.b == true && liftLevelAllow == true) {
            if (liftLevel < 3) {
                liftLevel += 1;
            }
            liftLevelAllow = false;
            liftToggle = false;
        } else if (gamepad2.x == true && liftLevelAllow == true) {
            if (liftLevel > 0) {
                liftLevel -= 1;
            }
            liftToggle = false;
            liftLevelAllow = false;
        } else {  // The IF here makes it so lift* goes back to default 'false' ONLY when not pressing Trigger.
            if (gamepad2.b == false && gamepad2.x == false) {
                liftLevelAllow = true;
                liftToggle = true;
            }
        }
    }

    public void liftControlManual() {

        if (gamepad2.left_stick_y >= 0.2) {

            grabberLiftOne.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
            grabberLiftOne.setPower(0.8);

            grabberLiftTwo.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
            grabberLiftTwo.setPower(0.8);

            liftStopAllow = true;

        } else if (gamepad2.left_stick_y <= -0.2) {

            grabberLiftOne.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            grabberLiftOne.setPower(-0.3);

            grabberLiftTwo.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            grabberLiftTwo.setPower(-0.3);

            liftStopAllow = true;

        } else {

            if (liftStopAllow == true) {

                grabberLiftOne.setPower(0);
                grabberLiftTwo.setPower(0);

            }

            liftStopAllow = false;

        }

    }

    public void turretControlManual() {

        if (gamepad2.right_stick_x >= 0.2) {

            turretMotor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
            turretMotor.setPower(0.5);

            turretStopAllow = true;
            telemetry.addLine("1");

        } else if (gamepad2.right_stick_x <= -0.2) {

            turretMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            turretMotor.setPower(-0.5);

            turretStopAllow = true;
            telemetry.addLine("2");


        } else {

            if (turretStopAllow == true) {

                turretMotor.setPower(0);

            }

            turretStopAllow = false;

        }

    }

    public void turretEncoderControlNew() {

        if (gamepad2.dpad_up) {

            turretPosition = 1;

            turretMoveAllow = true;

        } else if (gamepad2.dpad_left) {

            turretMoveAllow = true;

            if (turretMotor.getCurrentPosition() < 0) {

                turretPosition = 3;

            } else if (turretMotor.getCurrentPosition() >= 0) {

                turretPosition = 4;

            }

        } else {

            turretMoveAllow = false;

        }
    }

    public void turretEncoderNew() {

        if (turretMoveAllow) {
            switch (turretPosition) {
                case 0:
                    if (turretMotor.getCurrentPosition() > turret90CCW) {
                        turretMotor.setPower(-turretEncoderPower);
                    } else {
                        turretMotor.setPower(turretPowerEncoder);
                    }
                    turretMotor.setTargetPosition(turret90CCW);
                    turretMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                    break;

                case 1:
                    if (turretMotor.getCurrentPosition() > turretCenter) {
                        turretMotor.setPower(-turretPowerEncoder);
                    } else {
                        turretMotor.setPower(turretPowerEncoder);
                    }
                    turretMotor.setTargetPosition(turretCenter);
                    turretMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                    break;

                case 2:
                    if (turretMotor.getCurrentPosition() > turret90CW) {
                        turretMotor.setPower(-turretPowerEncoder);
                    } else {
                        turretMotor.setPower(turretPowerEncoder);
                    }
                    turretMotor.setTargetPosition(turret90CW);
                    turretMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                    break;

                case 3:

                    if (turretMotor.getCurrentPosition() > turret180CCW) {
                        turretMotor.setPower(-turretPowerEncoder);
                    } else {
                        turretMotor.setPower(turretPowerEncoder);
                    }
                    turretMotor.setTargetPosition(turret180CCW);
                    turretMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                    break;

                case 4:

                    if (turretMotor.getCurrentPosition() > turret180CW) {
                        turretMotor.setPower(-turretPowerEncoder);
                    } else {
                        turretMotor.setPower(turretPowerEncoder);
                    }
                    turretMotor.setTargetPosition(turret180CW);
                    turretMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                default:
                    break;

            }
        }

    }

    @Override
    public void loop() {

        updateTelemetry();

        systemControl();

        //liftControlEncoder();
        //liftMechanismEncoderNew();

//        turretEncoderNew();
//        turretEncoderControlNew();
//
//        turretControlManual();

        //liftControlManual();

    }

}