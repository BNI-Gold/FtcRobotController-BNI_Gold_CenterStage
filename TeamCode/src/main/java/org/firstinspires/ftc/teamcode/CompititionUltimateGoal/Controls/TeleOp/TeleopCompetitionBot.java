package org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Controls.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Robots.CompetitionBot;

import java.security.KeyStore;
//import org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Robots.LabBot;


@TeleOp(name = "Teleop_CompetitionBot")
public class TeleopCompetitionBot extends OpMode {

    // Variables & Constants specific to TeleLabBot
    double leftStickYVal;
    double leftStickXVal;
    double rightStickXVal;

    double frontLeftSpeed;
    double frontRightSpeed;
    double rearLeftSpeed;
    double rearRightSpeed;
    double LauncherSpeed;


    double powerThreshold = 0;
    double encoders;
    double speedMultiply = 1;
    boolean forwardMode = true;

    boolean reverseModeToggle = true;

    public CompetitionBot Bot=new CompetitionBot();


    @Override
    public void init() {
        Bot.initRobot(hardwareMap,  "TeleOp");
    }
    @Override
    public void init_loop() {
    }
    @Override
    public void start() {
    }
    @Override
    public void loop() {
        drive();
        wobble();
        launcher();
        intake();
        driveMode();

    }


    @Override
    public void stop() {

    }

    public void driveMode () {
        if (gamepad1.left_bumper) {
            speedMultiply = 0.3;
        }
        else if (gamepad1.right_bumper) {
            speedMultiply = 1;
        }


        if (gamepad1.dpad_right) {
            reverseModeToggle = true;
        }
        if (gamepad1.dpad_left) {
            reverseModeToggle = false;
        }

    }

    public void drive () {
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

            leftStickYVal = -gamepad1.left_stick_y;
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

            if (frontRightSpeed <= powerThreshold && frontRightSpeed >= -powerThreshold){
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

            if (rearRightSpeed <= powerThreshold && rearRightSpeed >= -powerThreshold){
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

            if (frontRightSpeed <= powerThreshold && frontRightSpeed >= -powerThreshold){
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

            if (rearRightSpeed <= powerThreshold && rearRightSpeed >= -powerThreshold){
                rearRightSpeed = 0;
                Bot.rearRightMotor.setPower(rearRightSpeed);
            } else {
                Bot.rearRightMotor.setPower(rearRightSpeed * speedMultiply);
            }
        }
    }




    public void wobble () {
        if (gamepad2.right_trigger > 0.1) {
            Bot.WobbleLower();
        }
        if (gamepad2.right_bumper == true) {
            Bot.WobbleOpen();
        }
        if (gamepad2.left_trigger > 0.1) {
            Bot.WobbleRaised();
        }
        if (gamepad2.left_bumper == true) {
            Bot.WobbleClosed();
        }
    }

    public void launcher () {
        if (gamepad2.a == true) {
//            .75 always launched high.
            Bot.LauncherOn(0.73);
        }
        if (gamepad2.y == true) {
            Bot.LauncherOff(0);
        }

    }
    public void intake() {
        if (gamepad2.left_stick_y > 0.1 || gamepad2.left_stick_y < -.1) {
            Bot.IntakeOn(gamepad2.left_stick_y);
        }
        else {
            Bot.IntakeOff(0);
        }

    }
    public void telemetryOutput () {
        telemetry.addData("front left motor ticks: ", Bot.frontLeftMotor.getCurrentPosition());
//        telemetry.addData()
        telemetry.addData("intake motor: ", Bot.IntakeMotor.getPower());
//        telemetry.addData("gamepad 2 left stick y", gamepad2.left_stick_y);
        telemetry.addData("launch motor: ", Bot.LauncherMotor.getPower());
    }
}
