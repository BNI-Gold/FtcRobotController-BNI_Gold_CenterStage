package org.firstinspires.ftc.teamcode.Compitition.ZCompititionUltimateGoal.dancew;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "If_We_Have_Time")
@Disabled
public class if_we_have_time extends OpMode {
    double leftStickYVal;
    double leftStickXVal;
    double rightStickXVal;

    double frontLeftSpeed;
    double frontRightSpeed;
    double rearLeftSpeed;
    double rearRightSpeed;
    double LauncherSpeed;

    double launcherPower = 0.6;
    double launcherVelocity = 1350;
//    Before adding coeffiicent - was 1540
    //was at 1650, and 2000 before that, OG = 1575

    double PowerShotVelocity = 1235; //1255 reduced by 20
    //1500 before

    boolean PushToggle = false;
    boolean PushToggleMag = false;


    double powerThreshold = 0;
    double encoders;
    double speedMultiply = 1;
    boolean forwardMode = true;

    boolean reverseModeToggle = false;
    boolean prankToggle = false;
    boolean rapidFireEngage = false;
    boolean rapidFireEngage2 = false;

    public IfWeHaveTimeBot Bot = new IfWeHaveTimeBot();

    @Override
    public void init() {
        Bot.initRobot(hardwareMap, "BlueLeft", "auto");
    }

    @Override
    public void init_loop() {
    }

    @Override
    public void start() {
        Bot.WobbleClosed();
        Bot.wobbleArmRaiseEngage = false;
        Bot.wobbleArmLowerengage = false;
//        Bot.WobbleArmStopClose();
        Bot.RingMagDown();
        Bot.RingPush();

    }

    @Override
    public void loop() {
        drive();
        IntakeAndDrive();
        LaunchAndWobbleGrab();


    }


    @Override
    public void stop() {

    }

    public void drive() {
/*
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

        telemetryOutput();*/


        if (reverseModeToggle) {

            leftStickYVal = --gamepad1.left_stick_y;
            leftStickYVal = Range.clip(leftStickYVal, -1, 1);
            leftStickXVal = gamepad1.left_stick_x;
            leftStickXVal = Range.clip(leftStickXVal, -1, 1);
            rightStickXVal = -gamepad1.right_stick_x;
            rightStickXVal = Range.clip(rightStickXVal, -1, 1);

            Bot.frontLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
            Bot.rearLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
            Bot.frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
            Bot.rearRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

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
                Bot.frontLeftMotor.setPower(frontLeftSpeed);
            } else {
                Bot.frontLeftMotor.setPower(frontLeftSpeed * speedMultiply);
            }

            if (frontRightSpeed <= powerThreshold && frontRightSpeed >= -powerThreshold) {
                frontRightSpeed = 0;
                Bot.frontRightMotor.setPower(frontRightSpeed);
            } else {
                Bot.frontRightMotor.setPower(frontRightSpeed * speedMultiply);
            }

            if (rearLeftSpeed <= powerThreshold && rearLeftSpeed >= -powerThreshold) {
                rearLeftSpeed = 0;
                Bot.rearLeftMotor.setPower(rearLeftSpeed);
            } else {
                Bot.rearLeftMotor.setPower(rearLeftSpeed * speedMultiply);
            }

            if (rearRightSpeed <= powerThreshold && rearRightSpeed >= -powerThreshold) {
                rearRightSpeed = 0;
                Bot.rearRightMotor.setPower(rearRightSpeed);
            } else {
                Bot.rearRightMotor.setPower(rearRightSpeed * speedMultiply);
            }
        }
        else if (prankToggle){
            leftStickYVal = -gamepad1.left_stick_y;
            leftStickYVal = Range.clip(leftStickYVal, -1, 1);
            leftStickXVal = gamepad1.left_stick_x;
            leftStickXVal = Range.clip(leftStickXVal, -1, 1);
            rightStickXVal = gamepad1.right_stick_x;
            rightStickXVal = Range.clip(rightStickXVal, -1, 1);
            Bot.frontLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
            Bot.rearLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
            Bot.frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
            Bot.rearRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);

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
                Bot.frontLeftMotor.setPower(frontLeftSpeed);
            } else {
                Bot.frontLeftMotor.setPower(frontLeftSpeed * speedMultiply);
            }

            if (frontRightSpeed <= powerThreshold && frontRightSpeed >= -powerThreshold) {
                frontRightSpeed = 0;
                Bot.frontRightMotor.setPower(frontRightSpeed);
            } else {
                Bot.frontRightMotor.setPower(frontRightSpeed * speedMultiply);
            }

            if (rearLeftSpeed <= powerThreshold && rearLeftSpeed >= -powerThreshold) {
                rearLeftSpeed = 0;
                Bot.rearLeftMotor.setPower(rearLeftSpeed);
            } else {
                Bot.rearLeftMotor.setPower(rearLeftSpeed * speedMultiply);
            }

            if (rearRightSpeed <= powerThreshold && rearRightSpeed >= -powerThreshold) {
                rearRightSpeed = 0;
                Bot.rearRightMotor.setPower(rearRightSpeed);
            } else {
                Bot.rearRightMotor.setPower(rearRightSpeed * speedMultiply);
            }
        }
        else {

            leftStickYVal = -gamepad1.left_stick_y;
            leftStickYVal = Range.clip(leftStickYVal, -1, 1);
            leftStickXVal = gamepad1.left_stick_x;
            leftStickXVal = Range.clip(leftStickXVal, -1, 1);
            rightStickXVal = gamepad1.right_stick_x;
            rightStickXVal = Range.clip(rightStickXVal, -1, 1);
            Bot.frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
            Bot.rearLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
            Bot.frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
            Bot.rearRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);

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
                Bot.frontLeftMotor.setPower(frontLeftSpeed);
            } else {
                Bot.frontLeftMotor.setPower(frontLeftSpeed * speedMultiply);
            }

            if (frontRightSpeed <= powerThreshold && frontRightSpeed >= -powerThreshold) {
                frontRightSpeed = 0;
                Bot.frontRightMotor.setPower(frontRightSpeed);
            } else {
                Bot.frontRightMotor.setPower(frontRightSpeed * speedMultiply);
            }

            if (rearLeftSpeed <= powerThreshold && rearLeftSpeed >= -powerThreshold) {
                rearLeftSpeed = 0;
                Bot.rearLeftMotor.setPower(rearLeftSpeed);
            } else {
                Bot.rearLeftMotor.setPower(rearLeftSpeed * speedMultiply);
            }

            if (rearRightSpeed <= powerThreshold && rearRightSpeed >= -powerThreshold) {
                rearRightSpeed = 0;
                Bot.rearRightMotor.setPower(rearRightSpeed);
            } else {
                Bot.rearRightMotor.setPower(rearRightSpeed * speedMultiply);
            }
        }
    }

    public void IntakeAndDrive() {
        if (gamepad1.left_stick_y > 0.1 || gamepad1.left_stick_y < -0.1){
            Bot.IntakeOn(gamepad2.left_stick_y);
            Bot.SpinInIntakeCorrector();
        }
        else {
            Bot.IntakeOff(0);
            Bot.StopIntakeCorrector();
        }
    }

    public void LaunchAndWobbleGrab(){
        if (gamepad2.y == true){
            Bot.LauncherOn(launcherVelocity);
            Bot.RingMagUp();
            Bot.WobbleOpen();
        }
        if (gamepad2.a == true){
            Bot.LauncherOff(0);
            Bot.RingMagDown();
            Bot.driveForward(1);
        }
        if (gamepad2.left_trigger > 0.1){
            Bot.driveBackward(1);
            Bot.WobbleClosed();
        }
        if (gamepad2.right_bumper == true){
            Bot.gyroCorrection(0.9,360);
            Bot.WobbleOpen();
        }
        if (gamepad2.left_bumper == true){
            Bot.gyroCorrection(0.2,0);
            Bot.WobbleClosed();
        }
    }
    public void telemetryOutput() {
//        telemetry.addData("front left motor ticks: ", Bot.frontLeftMotor.getCurrentPosition());
////        telemetry.addData()
//        telemetry.addData("intake motor: ", Bot.IntakeMotor.getPower());
////        telemetry.addData("gamepad 2 left stick y", gamepad2.left_stick_y);
//        telemetry.addData("launch motor: ", Bot.LauncherMotor.getPower());
//        telemetry.addData("Hue! ", Bot.hsvValues[0]);
        telemetry.addLine(String.format("Voltage: %.1f", Bot.voltageSensor.getVoltage()));
        telemetry.addData("Launcher Coefficient", Bot.launchCoefficient);
        telemetry.addData("Target Velocity", launcherPower * Bot.launchCoefficient);
        telemetry.addData("1 motor power: ", Bot.launcherMotor1.getPower());

        telemetry.addData("2 motor power: ", Bot.launcherMotor2.getPower());
        telemetry.addData("1 motor velocity: ", Bot.launcherMotor1.getVelocity());
        telemetry.addData("2 motor velocity: ", Bot.launcherMotor2.getVelocity());
//        telemetry.addData("1 motor encoders: ", Bot.launcherMotor1.getCurrentPosition());
//        telemetry.addData("2 motor encoders: ", Bot.launcherMotor2.getCurrentPosition());
        telemetry.addData("Velocity: ", launcherVelocity);
        telemetry.addData("Rapid Fire Ring: ", Bot.rapidFireRing);
        telemetry.addData("rapidFireEngage", rapidFireEngage);
        telemetry.addData("rapidFireEngage2", rapidFireEngage2);
        telemetry.addData("Servo continuous", Bot.IntakeCorrector.getPower());
        telemetry.update();
    }

}
