package org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Controls.DriverControl;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.DriveTrains.SixWD;
import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Robots.EightWheelBot;
import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Robots.SixWheelBot;

@TeleOp (name = "TeleOp 6WD_BeeLine")

public class TeleOpBeeLine6WD extends OpMode {


    public SixWheelBot Bot = new SixWheelBot();
//    public Gamepad gamepad1;

    private float leftStickY;
    private float rightStickY;
    private float leftStickX;
    private float rightStickX;

    double frontLeftSpeed;
    double frontRightSpeed;
    double rearLeftSpeed;
    double rearRightSpeed;

    double powerThreshold = 0;
    double speedMultiply = 1;


    @Override
    public void init(){
        Bot.initRobot(hardwareMap);
        Bot.stopMotors();

        leftStickY = 0;
        leftStickX = 0;
        rightStickY = 0;
        rightStickX = 0;

    }

    @Override
    public void loop() {

//        Bot.DriveTankSquared(gamepad1);
//        Bot.driveFoward(gamepad1.left_stick_y);

        getController();
        tankDrive();

        telemtryOutput();
    }

    public void getController () {
        leftStickY = gamepad1.left_stick_y;
        leftStickX = gamepad1.left_stick_x;
        rightStickY = gamepad1.right_stick_y;
        rightStickX = gamepad1.right_stick_x;
    }


    public void tankDrive () {
        Bot.leftMotorA.setPower(leftStickY);
        Bot.leftMotorB.setPower(leftStickY);
        Bot.rightMotorA.setPower(rightStickY);
        Bot.rightMotorB.setPower(rightStickY);
    }

    public void telemtryOutput () {
        telemetry.addData("gp1 left stick: ", gamepad1.left_stick_y);
        telemetry.addData("gp1 right stick: ", gamepad1.right_stick_y);

        telemetry.addData("left stick value Y: ", leftStickY);
        telemetry.addData("right stick value Y: ", rightStickY);
    }

    public void arcadesix () {
        leftStickY = --gamepad1.left_stick_y;
        leftStickY = Range.clip(leftStickY, -1, 1);
        leftStickX= --gamepad1.left_stick_x;
        leftStickX = Range.clip(leftStickX, -1, 1);

        Bot.leftMotorA.setDirection(DcMotorSimple.Direction.FORWARD);
        Bot.leftMotorB.setDirection(DcMotorSimple.Direction.FORWARD);
        Bot.rightMotorA.setDirection(DcMotorSimple.Direction.REVERSE);
        Bot.rightMotorB.setDirection(DcMotorSimple.Direction.REVERSE);

        frontLeftSpeed = leftStickY + leftStickX;
        frontLeftSpeed = Range.clip(frontLeftSpeed, -1, 1);

        frontRightSpeed = leftStickY - leftStickX;
        frontRightSpeed = Range.clip(frontRightSpeed, -1, 1);

        rearLeftSpeed = leftStickY - leftStickX;
        rearLeftSpeed = Range.clip(rearLeftSpeed, -1, 1);

        rearRightSpeed = leftStickY + leftStickX;
        rearRightSpeed = Range.clip(rearRightSpeed, -1, 1);

        if (frontLeftSpeed <= powerThreshold && frontLeftSpeed >= -powerThreshold) {
            frontLeftSpeed = 0;
            Bot.leftMotorA.setPower(frontLeftSpeed);
        } else {
            Bot.leftMotorA.setPower(frontLeftSpeed * speedMultiply);
        }

        if (frontRightSpeed <= powerThreshold && frontRightSpeed >= -powerThreshold) {
            frontRightSpeed = 0;
            Bot.rightMotorA.setPower(frontRightSpeed);
        } else {
            Bot.rightMotorA.setPower(frontRightSpeed * speedMultiply);
        }

        if (rearLeftSpeed <= powerThreshold && rearLeftSpeed >= -powerThreshold) {
            rearLeftSpeed = 0;
            Bot.leftMotorB.setPower(rearLeftSpeed);
        } else {
            Bot.leftMotorB.setPower(rearLeftSpeed * speedMultiply);
        }


    }


}
