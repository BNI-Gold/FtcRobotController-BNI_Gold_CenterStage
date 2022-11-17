package org.firstinspires.ftc.teamcode.Compitition.PowerPlay.controls.OpMode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Compitition.PowerPlay.Robots.CompetionBot;
import org.firstinspires.ftc.teamcode.Compitition.ZCompititionUltimateGoal.Robots.StraferKit;

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
        drive();
        grabberArmControl();
        grabberLiftControl();
        turretControl();
        updateTelemetry();

    }

    public void updateTelemetry () {

        telemetry.addData("Turret Encoder Position: ", Bot.turretPlatform.getCurrentPosition());
        telemetry.addData("Lift Encoder Position: ", Bot.grabberLift.getCurrentPosition());
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

    }

    public void grabberLiftControl() {

        //just to find if lift works
        if (gamepad2.left_bumper){
            Bot.extendGrabberLift(1);
        }
        else if (gamepad2.right_bumper){
            Bot.retractGrabberLift(.2);
        }



        if (gamepad2.left_stick_y >= 0.1) {

            leftStickYVal = gamepad2.left_stick_y;
            Bot.retractGrabberLift(leftStickYVal * .25);

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

    public void turretControl() {
        if (gamepad2.right_stick_x >= 0.1) {
            rightStickXVal = gamepad2.right_stick_x;
            Bot.turretClockwise(rightStickXVal * 0.3);

        }

        else if (gamepad2.right_stick_x <= -0.1) {
            rightStickXVal = gamepad2.right_stick_x;
            Bot.turretCounterClockwise(rightStickXVal * 0.3);

        } else {
            Bot.turretStop();

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
}
