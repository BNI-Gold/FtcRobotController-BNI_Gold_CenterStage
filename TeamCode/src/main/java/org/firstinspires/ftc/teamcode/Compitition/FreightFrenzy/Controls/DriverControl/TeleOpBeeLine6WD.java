package org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Controls.DriverControl;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.Range;

import org.checkerframework.checker.index.qual.Positive;
import org.checkerframework.checker.units.qual.Speed;
import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.DriveTrains.SixWD;
import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Robots.EightWheelBot;
import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Robots.SixWheelBot;

@TeleOp (name = "TeleOp 6WD_BeeLine")

public class TeleOpBeeLine6WD extends OpMode {


    public SixWheelBot Bot = new SixWheelBot();
//    public Gamepad gamepad1;

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



    boolean squaredDrive = false;
    FtcDashboard dashboard = FtcDashboard.getInstance();


    @Override
    public void init(){
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



        telemetry = dashboard.getTelemetry();


        Bot.setIntakeLyft2up();

    }

    @Override
    public void loop() {

//        Bot.DriveTankSquared(gamepad1);
//        Bot.driveFoward(gamepad1.left_stick_y);
//        arcadesix();
        getController();
        if (squaredDrive == true){
            tankDriveSquared();
        }
        else if (squaredDrive == false){
            tankDrive();
        }

        DuckSpinner();

        Intakecontroller();

        telemtryOutput();

        LyftIntake2();
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

        if (gamepad1.a == true){
            squaredDrive = true;

        }
        if (gamepad1.b == true){
            squaredDrive = false;
        }
    }


    public void tankDrive () {
        Bot.leftMotorA.setPower(leftStickY1);
        Bot.leftMotorB.setPower(leftStickY1);
        Bot.rightMotorA.setPower(rightStickY1);
        Bot.rightMotorB.setPower(rightStickY1);
    }

    public void tankDriveSquared () {
        Bot.leftMotorA.setPower(squared(leftStickY1));
        Bot.leftMotorB.setPower(squared(leftStickY1));
        Bot.rightMotorA.setPower(squared(rightStickY1));
        Bot.rightMotorB.setPower(squared(rightStickY1));

    }

    public double squared (double value) {
        double squaredValue = value * value;
        if (value >= 0){
                return  squaredValue;
        }
        else {
            return -squaredValue;
        }
    }

    public void telemtryOutput () {
        telemetry.addData("gp1 left stick: ", gamepad1.left_stick_y);
        telemetry.addData("gp1 right stick: ", gamepad1.right_stick_y);

        telemetry.addData("left stick value Y 1: ", leftStickY1);
        telemetry.addData("right stick value Y 1: ", rightStickY1);

        telemetry.addData("power output yeah:",Bot.leftMotorA.getPower());
    }



    public void DuckSpinner () {
        if (gamepad2.dpad_left == true){
            Bot.SpinDuckleftleft();
            Bot.SpinDuckleftRight();
        }



        if (gamepad2.dpad_right == true){
            Bot.SpinDuckRightleft();
            Bot.SpinDuckRightRight();
        }

        if (gamepad2.dpad_down == true){
            Bot.StopSpinningDuckleft();
            Bot.StopSpinningDuckRight();
        }
    }

    public void Intakecontroller () {
        if (leftStickY2 > 0.1){
            Bot.Intake(leftStickY2);
        }
        else if (leftStickY2 < -0.1){
            Bot.Intake(-leftStickY2);
        }
        else {
            Bot.Intake (0);
        }

    }

    public void LyftIntake2 () {
        if (gamepad2.a == true){
            Bot.setIntakeLyft2down();
        }
        if (gamepad2.y == true){
            Bot.setIntakeLyft2up();
        }

    }

//    public void LyftIntake () {
//        if (rightStickY2 > 0.1){
//            Bot.IntakeLyft(rightStickY2);
//        }
//        else if (rightStickY2 < -0.1){
//            Bot.IntakeLyft(-rightStickY2);
//        }
//        else {
//            Bot.IntakeLyft(0);
//        }
//    }



    public void arcadesix () {

//        leftStickY = --gamepad1.left_stick_y;
//        leftStickY = Range.clip(leftStickY, -1, 1);
//        leftStickX= --gamepad1.left_stick_x;
//        leftStickX = Range.clip(leftStickX, -1, 1);
//
//        Bot.leftMotorA.setDirection(DcMotorSimple.Direction.FORWARD);
//        Bot.leftMotorB.setDirection(DcMotorSimple.Direction.FORWARD);
//        Bot.rightMotorA.setDirection(DcMotorSimple.Direction.REVERSE);
//        Bot.rightMotorB.setDirection(DcMotorSimple.Direction.REVERSE);
//
//        frontLeftSpeed = leftStickY + leftStickX;
//        frontLeftSpeed = Range.clip(frontLeftSpeed, -1, 1);
//
//        frontRightSpeed = leftStickY - leftStickX;
//        frontRightSpeed = Range.clip(frontRightSpeed, -1, 1);
//
//        rearLeftSpeed = leftStickY - leftStickX;
//        rearLeftSpeed = Range.clip(rearLeftSpeed, -1, 1);
//
//        rearRightSpeed = leftStickY + leftStickX;
//        rearRightSpeed = Range.clip(rearRightSpeed, -1, 1);
//
//        if (frontLeftSpeed <= powerThreshold && frontLeftSpeed >= -powerThreshold) {
//            frontLeftSpeed = 0;
//            Bot.leftMotorA.setPower(frontLeftSpeed);
//        } else {
//            Bot.leftMotorA.setPower(frontLeftSpeed * speedMultiply);
//        }
//
//        if (frontRightSpeed <= powerThreshold && frontRightSpeed >= -powerThreshold) {
//            frontRightSpeed = 0;
//            Bot.rightMotorA.setPower(frontRightSpeed);
//        } else {
//            Bot.rightMotorA.setPower(frontRightSpeed * speedMultiply);
//        }
//
//        if (rearLeftSpeed <= powerThreshold && rearLeftSpeed >= -powerThreshold) {
//            rearLeftSpeed = 0;
//            Bot.leftMotorB.setPower(rearLeftSpeed);
//        } else {
//            Bot.leftMotorB.setPower(rearLeftSpeed * speedMultiply);
//        }
//
//        if (rearRightSpeed <= powerThreshold && rearRightSpeed >= -powerThreshold) {
//            rearRightSpeed = 0;
//            Bot.rightMotorB.setPower(rearRightSpeed);
//        } else {
//            Bot.rightMotorB.setPower(rearRightSpeed * speedMultiply);
//        }
//
//
    }


}
