package org.firstinspires.ftc.teamcode.Compitition.CenterStage.Controls.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Compitition.CenterStage.Robots.CompBot;

@TeleOp (name = "A_TeleOp_CompBot_Centerstage")
public class TeleOp_CompetitionBot extends OpMode {

    double leftStickYVal;
    double leftStickXVal;
    double rightStickXVal;
    double rightStickYVal;

    double frontLeftSpeed;
    double frontRightSpeed;
    double rearLeftSpeed;
    double rearRightSpeed;

    double powerThreshold = 0;
    double speedMultiply = 1;

    public double   wormgearPower = 1;

    public double viperSlidePower = .7;

    public double viperSlideMaxTicks = 250;
    public double viperSlideMinTicks = 1;
    public double wormgearMaxTicks = 100;
    public double wormgearMinTicks = 1;

    public double rotationPos = 0.5;
    double incValue = 0.05;



    double pixelRotationUp = 1.0;

    double pixelRotationMiddle = 0.5;

    double pixelRotationDown = 0.0;


    public CompBot Bot = new CompBot();

    public void init () {
        Bot.initRobot(hardwareMap);
    }
    public void init_loop() {  }


    public void start() {

    }

    public void loop(){

        speedControl();

        endgameArm();

        pixelMechanismControl();

        drive();

        telemetryOutput();

        pixelRotationControl();

        pixelTestTelemetry();

    }

    public void drive() {

        leftStickYVal = gamepad1.left_stick_y;
        leftStickYVal = Range.clip(leftStickYVal, -1, 1);
        leftStickXVal = -gamepad1.left_stick_x;
        leftStickXVal = Range.clip(leftStickXVal, -1, 1);
        rightStickXVal = -gamepad1.right_stick_x;
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

    // CONTROL FOR PIXEL ARM ROTATION SERVO
    public void pixelRotationControl() {

        // DPAD UP SETS SERVO TO UP POSITION | UP POSITION MAY BE 0 OR 1 IDK
        if (gamepad2.dpad_up) {

            Bot.pixelRotator.setPosition(pixelRotationUp);

        }

        // DPAD UP SETS SERVO TO DOWN POSITION | DOWN POSITION MAY BE 0 OR 1 IDK
        if (gamepad2.dpad_down) {

            Bot.pixelRotator.setPosition(pixelRotationDown);

        }

        // DPAD RIGHT - MIDDLE POSITION
        if (gamepad2.dpad_right) {

            Bot.pixelRotator.setPosition(pixelRotationMiddle);

        }


    }

    public void pixelMechanismControl() {



        if (gamepad2.right_stick_x > 0.1){

            Bot.linearSlideUp(viperSlidePower);

        }

        else if (gamepad2.right_stick_x < -0.1) {

            Bot.linearSlideDown(viperSlidePower);

        }

        else {

            Bot.viperSlideRight.setPower(0);

        }

        if (Math.abs(Bot.viperSlideRight.getCurrentPosition()) > viperSlideMaxTicks) {

            Bot.viperSlideRight.setPower(0);

        }

        else if (Math.abs(Bot.viperSlideRight.getCurrentPosition()) <= viperSlideMinTicks) {

            Bot.viperSlideRight.setPower(0);

        }

        if (gamepad2.right_stick_y > 0.1) {

            Bot.rightWormgearUp(wormgearPower);

        }

        else if (gamepad2.right_stick_y < -0.1) {

            Bot.rightWormgearDown(wormgearPower);

        }

        else {

            Bot.wormgearRight.setPower(0);

        }

        if (gamepad2.left_bumper) {

            Bot.pixelClaw.setPosition(.1);

        }

        else if (gamepad2.right_bumper) {

            Bot.pixelClaw.setPosition(.9);

        }

    }

    public void endgameArm() {
        if (gamepad2.left_stick_y < -0.1) {
            Bot.endgameArmRetract();
        }

        else if (gamepad2.left_stick_y > 0.1) {
            Bot.endgameArmExtend();
        }

        else {
            Bot.endgameArmStop();
        }

        if (gamepad2.x ) {
            Bot.endgameArmRotatorMovement(.2);
        }
         if (gamepad2.y) {
            Bot.endgameArmRotatorMovement(.8);
        }


//        if (gamepad2.a) {
//            CompetitionBot.upTimer.reset();
//            if (CompetitionBot.upTimer.seconds() >= 2.5) {
//                CompetitionBot.endgameArmStop();
//            } else if (CompetitionBot.upTimer.seconds() < 2.5) {
//                CompetitionBot.endgameArmExtend();
//
//            }
//
//        } else if (gamepad2.b) {
//            CompetitionBot.downTimer.reset();
//            if (CompetitionBot.downTimer.seconds() >= 2.4) {
//                CompetitionBot.endgameArmStop();
//            } else if (CompetitionBot.downTimer.seconds() < 2.4) {
//                CompetitionBot.endgameArmRetract();
//            }
//        }

    }
    public void telemetryOutput() {

//        telemetry.addData("pwr", "FRONT LEFT: " + frontLeftSpeed);
//        telemetry.addData("pwr", "FRONT RIGHT: " + frontRightSpeed);
//        telemetry.addData("pwr", "REAR LEFT: " + rearLeftSpeed);
//        telemetry.addData("pwr", "REAR RIGHT: " + rearRightSpeed);

        telemetry.update();

    }

    public void speedControl() {

        if (gamepad1.dpad_up) {
            speedMultiply = 0.5;
        }

        else if (gamepad1.dpad_down) {
            speedMultiply = 1;
        }
    }

    public void pixelTestTelemetry() {

        double pixelServoPos = Bot.pixelRotator.getPosition();

        telemetry.addData("Pixel Rotation Servo Pos: ", pixelServoPos);
        telemetry.update();

    }



}


