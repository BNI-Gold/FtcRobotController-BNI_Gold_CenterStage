// TeleOp OpMode Class Learning Template for Competition 2022-2023
// Revision: 09-Oct-22
// Author: Jamie Acker

package org.firstinspires.ftc.teamcode.MrAcker.NamelessBot;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;
@Disabled
@TeleOp (name = "NamelessBot",group = "Lab")

public class TeleOp_Nameless extends OpMode {

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
    public NamelessBot Bot = new NamelessBot();


    @Override
    public void init() {

        Bot.initRobot(hardwareMap);
    }

    @Override
    public void loop() {
        driveControl();
        grabberArmControl();
        grabberLiftControl();
    }


    // Grabber Arm Controls
    public void grabberArmControl() {
        if (gamepad1.left_bumper) {
            Bot.openGrabberArms();
        }
        else if (gamepad1.right_bumper) {
            Bot.closeGrabberArms();
        }

    }

    //Grabber Lift Controls
    public void grabberLiftControl() {
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

    // Driver Control
    public void driveControl () {

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
