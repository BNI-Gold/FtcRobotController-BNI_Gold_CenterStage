package org.firstinspires.ftc.teamcode.MrAcker;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Compitition.ZCompititionUltimateGoal.Robots.StraferKit;

@TeleOp (name = "NamelessBot",group = "BNI")

public class TeleOp_AckerBot extends OpMode {

    // Variables & Constants for Mecanumum Driver Control
    double leftStickYVal;
    double leftStickXVal;
    double rightStickXVal;

    double frontLeftSpeed;
    double frontRightSpeed;
    double rearLeftSpeed;
    double rearRightSpeed;

    double powerThreshold = 0;
    double speedMultiply = 1;

    //Construct Robot from AckerBot Class
    public AckerBot Bot = new AckerBot();


    @Override
    public void init() {

        Bot.initRobot(hardwareMap);
    }

    @Override
    public void loop() {
        drive();
        grabberArms();
        grabberLift();
    }

    //TeleOp Methods
    public void grabberArms() {
        if (gamepad1.left_bumper) {
            Bot.openGrabber(.55,.45);
        }
        else if (gamepad1.right_bumper) {
            Bot.closeGrabber(.45, .55);
        }

    }

    public void grabberLift() {
        if (gamepad1.left_trigger > 0) {
            Bot.extendGrabberLift(.50);
        }
        else if (gamepad1.right_trigger > 0 ) {
            Bot.retractGrabberLift(.50);
        }
        else {
            Bot.stopGrabberLift();
        }

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

}
