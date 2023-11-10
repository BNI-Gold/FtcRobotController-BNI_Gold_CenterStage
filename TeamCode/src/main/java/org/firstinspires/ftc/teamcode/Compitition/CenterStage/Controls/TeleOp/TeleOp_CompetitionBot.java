package org.firstinspires.ftc.teamcode.Compitition.CenterStage.Controls.TeleOp;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Compitition.CenterStage.Robots.CompBot;

@TeleOp (name = "A - Center Stage - 'RANGER RATTLE'")
public class TeleOp_CompetitionBot extends OpMode {

    FtcDashboard dashboard = FtcDashboard.getInstance();
    Telemetry dashboardTelemetry = dashboard.getTelemetry();

    public double rotationPos = 0.5;
    double incValue = 0.05;

    boolean planeLauncherOn = false;

    double pixelRotationUp = 1.0;

    double pixelRotationMiddle = 0.5;

    double pixelRotationDown = 0.0;

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

    public double wormgearPower = 1;

    public double viperSlidePower = .7;

    public double viperSlideMaxTicks = 250;
    public double viperSlideMinTicks = 1;
    public double wormgearMaxTicks = 100;
    public double wormgearMinTicks = 1;

    public boolean slowMode = false;

    public boolean variableSlowMode = false;


    public CompBot Bot = new CompBot();

    public void init() {
        Bot.initRobot(hardwareMap);
    }

    public void init_loop() {
    }

    public void start() {

    }

    public void loop() {
        speedControl();
        //driverControlChanger();
        endgameArm();
        pixelMechanismControl();
        planeLauncher();
        drive();
        telemetryOutput();
    }

    public void speedControl() {

        if (gamepad1.left_trigger > 0.35) {

            slowMode = true;

        } else {

            slowMode = false;

        }

        if (gamepad1.right_trigger > 0.1) {

            variableSlowMode = true;

        } else {

            variableSlowMode = false;

        }

        if (slowMode) {

            speedMultiply = 0.3;

        } else if (variableSlowMode) {

            //experimental
            speedMultiply = gamepad1.right_trigger / 0.8;

        } else {

            speedMultiply = 1;

        }

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

    public void pixelMechanismControl() {

       if (gamepad2.y){
           Bot.collectorPosition();
       }
       else {
           Bot.drivePosition();
       }

        if (gamepad2.right_trigger > 0.2) {
            Bot.linearSlideExtend(viperSlidePower);
        } else if (gamepad2.left_trigger > 0.2) {
            Bot.linearSlideRetract(viperSlidePower);
        } else {
            Bot.viperSlideRight.setPower(0);
        }

        if (Math.abs(Bot.viperSlideRight.getCurrentPosition()) > viperSlideMaxTicks) {
            Bot.viperSlideRight.setPower(0);
        } else if (Math.abs(Bot.viperSlideRight.getCurrentPosition()) <= viperSlideMinTicks) {
            Bot.viperSlideRight.setPower(0);
        }

        if (gamepad2.left_stick_y < -0.1) {
            Bot.rightWormgearDown(wormgearPower);
        } else if (gamepad2.left_stick_y > 0.1) {
            Bot.rightWormgearUp(wormgearPower);
        } else {
            Bot.wormgearRight.setPower(0);
        }


        if (gamepad2.left_bumper) {

            Bot.leftPixelClawOpen();

        } else {

            Bot.leftPixelClawClose();

        }

        if (gamepad2.right_bumper) {
            Bot.rightPixelClawOpen();
        }
        else {
            Bot.rightPixelClawClose();
        }


    }

    public void endgameArm() {

        if (gamepad2.right_stick_x < -0.1) {
            Bot.endgameArmRetract();
        } else if (gamepad2.right_stick_x > 0.1) {
            Bot.endgameArmExtend();
        } else {
            Bot.endgameArmStop();
        }

        if (gamepad2.dpad_up) {
            Bot.endgameArmRotator.setPosition(0.8);
        } else if (gamepad2.dpad_right) {
            Bot.endgameArmRotator.setPosition(0.4);
        } else if (gamepad2.dpad_left) {
            Bot.endgameArmRotator.setPosition(0.0);
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

    public void planeLauncher() {

        if (gamepad2.dpad_down) {
            Bot.planeLauncherOn();
        } else {

            Bot.planeLauncherOff();

        }
    }
        public void telemetryOutput () {

            telemetry.addData("Front Left: ", Bot.frontLeftMotor.getCurrentPosition());
            telemetry.addData("Front Right: ", Bot.frontRightMotor.getCurrentPosition());
            telemetry.addData("Rear Left: ", Bot.rearLeftMotor.getCurrentPosition());
            telemetry.addData("Rear Right: ", Bot.rearRightMotor.getCurrentPosition());

            dashboardTelemetry.addData("FRONT LEFT: ", Bot.frontLeftMotor.getPower());
            dashboardTelemetry.addData("FRONT RIGHT: ", Bot.frontRightMotor.getPower());

            dashboardTelemetry.addData("REAR LEFT: ", Bot.rearLeftMotor.getPower());
            dashboardTelemetry.addData("REAR RIGHT: ", Bot.rearRightMotor.getPower());
            dashboardTelemetry.update();
            telemetry.update();
        }


    }



