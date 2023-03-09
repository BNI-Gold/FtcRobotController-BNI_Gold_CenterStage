package org.firstinspires.ftc.teamcode.Outreach.OutreachTank.Controls;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Compitition.ZFreightFrenzy.Robots.TankBot;
import org.firstinspires.ftc.teamcode.Outreach.OutreachTank.Robots.OutreachTank;

@TeleOp( name = "L.A.U.N.C.H.E.R", group = "2")

// Ludicrous Application for the Universal Nullification for the Comprehension of Hardware of Extreme Range

public class TeleOpTank extends OpMode {

    public OutreachTank Bot = new OutreachTank();

    private float leftStickY1;
    private float rightStickY1;
    private float leftStickX1;
    private float rightStickX1;

    private float leftStickY2;
    private float leftStickX2;
    private float rightStickY2;
    private float rightStickX2;

    public float dpad_left;
    public float dpad_right;
    public float dpad_down;

    double frontLeftSpeed;
    double frontRightSpeed;
    double rearLeftSpeed;
    double rearRightSpeed;

    double powerThreshold = 0;
    double speedMultiply = 1;

    public double leftMotorValue;
    public double rightMotorValue;

    boolean tankDrive = true;
//    FtcDashboard dashboard = FtcDashboard.getInstance();


    @Override
    public void init() {
        Bot.initRobot(hardwareMap);
        Bot.stopMotors();

        leftStickY1 = 0;
        leftStickX1 = 0;
        rightStickY1 = 0;
        rightStickX1 = 0;

        leftStickY2 = 0;
        leftStickX2 = 0;
        rightStickY2 = 0;
        rightStickX2 = 0;
    }

    @Override
    public void loop() {
        getController();
        if (tankDrive == true) {
            tankDrive();
        }
        else {
//            tankDrive();
            arcadeDrive();
        }

        driveSpeed();

        telemtryOutput();

        turntableControl();
        flywheelControl();
        launcherAngleControl();

    }

    public void driveSpeed () {
        if (gamepad1.dpad_up) {
            speedMultiply = 1;
        }

        if (gamepad1.dpad_down) {
            speedMultiply = 0.5;
        }
    }
    public void getController () {
        leftStickY1 = -gamepad1.left_stick_y;
        leftStickX1 = -gamepad1.left_stick_x;
        rightStickY1 = -gamepad1.right_stick_y;
        rightStickX1 = -gamepad1.right_stick_x;

        leftStickY2 = -gamepad2.left_stick_y;
        leftStickX2 = -gamepad2.left_stick_x;
        rightStickY2 = -gamepad2.right_stick_y;
        rightStickX2 = -gamepad2.right_stick_x;

//        if (gamepad1.b) {
//            tankDrive = false;
//        }
//        else if (gamepad1.x) {
//            tankDrive = true;
//        }
    }



    public void tankDrive () {
        Bot.leftMotorA.setPower(leftStickY1 * speedMultiply);
        Bot.leftMotorB.setPower(leftStickY1 * speedMultiply);
        Bot.rightMotorA.setPower(rightStickY1 * speedMultiply);
        Bot.rightMotorB.setPower(rightStickY1 * speedMultiply);
    }



    public void arcadeDrive () {
        leftMotorValue = leftStickY1 - leftStickX1;
        rightMotorValue = leftStickY1 + leftStickX1;
        leftMotorValue = Range.clip(leftMotorValue,-1, 1);
        rightMotorValue = Range.clip(rightMotorValue, -1, 1);
        Bot.leftMotorA.setPower(leftMotorValue * speedMultiply);
        Bot.leftMotorB.setPower(leftMotorValue * speedMultiply);
        Bot.rightMotorA.setPower(rightMotorValue * speedMultiply);
        Bot.rightMotorB.setPower(rightMotorValue * speedMultiply);
    }

    public void turntableControl () {

        if (gamepad1.right_bumper) {

            Bot.tableRight(0.6);

        } else if (gamepad1.left_bumper) {

            Bot.tableLeft(0.6);

        } else {

            Bot.stopTable();

        }

    }

    public void launcherAngleControl () {

        if (gamepad1.y) {

            Bot.launcherAngleIncrease();

        } else if (gamepad1.a) {

            Bot.launcherAngleDecrease();

        } else {

            Bot.launcherAngleStop();

        }

    }

    public void flywheelControl () {

        if (gamepad1.right_trigger >= 0.2) {

            Bot.flywheelSpeed(1);

        } else {

            Bot.flywheelStop();

        }

    }

    public void telemtryOutput () {
        telemetry.addData("Tank Drive? (x/b to swap) ", tankDrive);

//        telemetry.addData("gp1 left stick: ", gamepad1.left_stick_y);
//        telemetry.addData("gp1 right stick: ", gamepad1.right_stick_y);

//        telemetry.addData("left stick value Y 1: ", leftStickY1);
//        telemetry.addData("right stick value Y 1: ", rightStickY1);
        telemetry.addData("left motor ticks ", Bot.leftMotorA.getCurrentPosition());
        telemetry.addData("right motor ticks", Bot.rightMotorA.getCurrentPosition());

        telemetry.addData("power output Left A:",Bot.leftMotorA.getPower());
        telemetry.addData("power output Left B:",Bot.leftMotorB.getPower());
        telemetry.addData("power output Right A:",Bot.rightMotorA.getPower());
        telemetry.addData("power output Left B:",Bot.rightMotorB.getPower());
    }
}
