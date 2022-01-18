//package org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.DriveTrains;
//
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.hardware.DcMotorEx;
//import com.qualcomm.robotcore.hardware.Gamepad;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//
//import org.checkerframework.checker.units.qual.Speed;
//
//public class EightWD {
//
//   public DcMotorEx leftMotorA, leftMotorB, rightMotorA, rightMotorB;
//   public LinearOpMode linearOp = null;
//
//   public void setLinearOp(LinearOpMode linearOp){
//
//    this.linearOp = linearOp;
//   }
//  // 私は疲れました
//    public EightWD(){
//
//    }
//
//    public void stopMotors(){
//       leftMotorA.setPower(0);
//       leftMotorB.setPower(0);
//       rightMotorA.setPower(0);
//       rightMotorB.setPower(0);
//    }
//
//    public void driveForward (double speed) {
//        leftMotorB.setPower(speed);
//        leftMotorA.setPower(speed);
//        rightMotorA.setPower(speed);
//        rightMotorB.setPower(speed);
//    }
//
//    public void driveBackward (double speed){
//        leftMotorA.setPower(-speed);
//        leftMotorB.setPower(-speed);
//        rightMotorA.setPower(-speed);
//        rightMotorB.setPower(-speed);
//    }
//
//    public void rotateright (){
//
//    }
//
//
//
//
//    public void DriveTankSquared (Gamepad gamepad1) {
//       if (gamepad1.left_stick_y > 1) {
//           //sets motor power
//       }
//
//
//    }
//
//}
//
//
//
//
//// Ես լսում եմ Զաք Բրաունին, ես միակ ծրագրավորողն եմ ներկա և, հետևաբար, շատ ձանձրանում եմ



package org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.DriveTrains;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

public class EightWD {

    public DcMotorEx leftMotorA, leftMotorB, rightMotorA, rightMotorB, intakeLyft;
    public LinearOpMode linearOp = null;

    public static final double TICKS_PER_ROTATION = 383.6;   // GoBilda 13.7 Motor PPR




    public void setLinearOp(LinearOpMode linearOp){

        this.linearOp = linearOp;
    }

    public EightWD(){

    }

    //PID VARIABLES
    double integralSum = 0;
    double Kp = 0;
    double Ki = 0;
    double Kd = 0;
    ElapsedTime timer = new ElapsedTime();
    private double lastError;
//    double encoderAverage = (leftMotorA.getCurrentPosition() + rightMotorA.getCurrentPosition())/2;
    double encoderAverage;





//    public boolean senseLyftExtend () {
//        senseLyftColor.argb((int) (senseLyftColor.red() * SCALE_FACTOR),
//                (int) (senseLyftColor.green() * SCALE_FACTOR),
//                (int) (senseLyftColor.blue() * SCALE_FACTOR),
//                hsvValues);
////        linearOp.telemetry.addData("function hue ", hsvValues[0]);
////        linearOp.telemetry.update();
////        WOBBLE_ARM_LOWER_THRESHOLD = 50
//        if (hsvValues[0] > RED_THRESHOLD_HUE) {
//            return false;
//        }
//        else {
//            return true;
//        }
//    }
//
//    public boolean senseLyftcolapse () {
//        senseLyftColor.argb((int) (senseLyftColor.red() * SCALE_FACTOR),
//                (int) (senseLyftColor.green() * SCALE_FACTOR),
//                (int) (senseLyftColor.blue() * SCALE_FACTOR),
//                hsvValues);
////        WOBBLE_ARM_LOWER_THRESHOLD = 50
//        if (hsvValues[0] > BLUE_THRESHOLD_HUE) {
//            return false;
//        }
//        else {
//            return true;
//        }
//    }

    public void setMotorRunModes (DcMotor.RunMode mode) {

        leftMotorA.setMode(mode);
        leftMotorB.setMode(mode);
        rightMotorA.setMode(mode);
        rightMotorB.setMode(mode);

//        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    public void stopMotors(){
        leftMotorA.setPower(0);
        leftMotorB.setPower(0);
        rightMotorA.setPower(0);
        rightMotorB.setPower(0);

//       intakeLyft.setPower(0);

    }

    // Drive forward with power only.
    public void driveFoward (double speed) {
        leftMotorB.setPower(speed);
        leftMotorA.setPower(speed);
        rightMotorA.setPower(speed);
        rightMotorB.setPower(speed);


    }


    // ENCODER drive Forward
    public void driveForward (double speed, double rotations) {
        if (linearOp.opModeIsActive()) {

            double ticks = rotations * TICKS_PER_ROTATION;
            setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            linearOp.telemetry.addData("TARGET TICKS: ", ticks);
            linearOp.telemetry.update();
//            linearOp.sleep(1000);
            while (leftMotorA.getCurrentPosition() < ticks && linearOp.opModeIsActive()) {
                driveFoward(speed);
                linearOp.telemetry.addData("TARGET TICKS (in while): ", ticks);
                linearOp.telemetry.addData("CURRENT TICKS (left motor): ", leftMotorA.getCurrentPosition());
                linearOp.telemetry.addData("CURRENT TICKS (right motor): ", rightMotorA.getCurrentPosition());
                linearOp.telemetry.update();

            }
            stopMotors();
            linearOp.telemetry.addData("TARGET TICKS (stop motors): ", ticks);
            linearOp.telemetry.addData("CURRENT TICKS (left motor): ", leftMotorA.getCurrentPosition());
            linearOp.telemetry.addData("CURRENT TICKS (right motor): ", rightMotorA.getCurrentPosition());
            linearOp.telemetry.update();
//            linearOp.sleep(2000);

        }
    }



    public void driveBackward (double speed){
        leftMotorA.setPower(-speed);
        leftMotorB.setPower(-speed);
        rightMotorA.setPower(-speed);
        rightMotorB.setPower(-speed);
    }

    public void rotateRight (double speed){
        leftMotorB.setPower(speed);
        leftMotorA.setPower(speed);
        rightMotorA.setPower(-speed);
        rightMotorB.setPower(-speed);
    }

    public void rotateLeft (double speed){
        leftMotorB.setPower(-speed);
        leftMotorA.setPower(-speed);
        rightMotorB.setPower(speed);
        rightMotorA.setPower(speed);
    }



//    public void IntakeLyft (double speed){
//       intakeLyft.setPower(speed);
//    }


    //somthing is intensionally wrong
    public void rotateRight (double speed, double rotations) {
        if (linearOp.opModeIsActive()) {

            double ticks = rotations * TICKS_PER_ROTATION;
            setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            linearOp.telemetry.addData("TARGET TICKS: ", ticks);
            linearOp.telemetry.update();
//            linearOp.sleep(1000);
            while (leftMotorA.getCurrentPosition() <= ticks && linearOp.opModeIsActive()) {
                rotateRight(speed);
                linearOp.telemetry.addData("TARGET TICKS (in while): ", ticks);
                linearOp.telemetry.addData("CURRENT TICKS (left motor): ", leftMotorA.getCurrentPosition());
                linearOp.telemetry.addData("CURRENT TICKS (right motor): ", rightMotorA.getCurrentPosition());
                linearOp.telemetry.update();

            }
            stopMotors();
            linearOp.telemetry.addData("TARGET TICKS (stop motors): ", ticks);
            linearOp.telemetry.addData("CURRENT TICKS (left motor): ", leftMotorA.getCurrentPosition());
            linearOp.telemetry.addData("CURRENT TICKS (right motor): ", rightMotorA.getCurrentPosition());
            linearOp.telemetry.update();
//            linearOp.sleep(2000);

        }
    }


    public void rotateLeft (double speed, double rotations) {
        if (linearOp.opModeIsActive()) {

            double ticks = rotations * TICKS_PER_ROTATION;
            setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            linearOp.telemetry.addData("TARGET TICKS", ticks);
            linearOp.telemetry.update();
//            linearOp.sleep(1000);
            while (rightMotorA.getCurrentPosition() <= ticks && linearOp.opModeIsActive()) {
                rotateLeft(speed);
                linearOp.telemetry.addData("TARGET TICKS (in while): ", ticks);
                linearOp.telemetry.addData("CURRENT TICKS (left motor): ", leftMotorA.getCurrentPosition());
                linearOp.telemetry.addData("CURRENT TICKS (right motor): ", rightMotorA.getCurrentPosition());
                linearOp.telemetry.update();

            }
            stopMotors();
            linearOp.telemetry.addData("TARGET TICKS (stop motors): ", ticks);
            linearOp.telemetry.addData("CURRENT TICKS (left motor): ", leftMotorA.getCurrentPosition());
            linearOp.telemetry.addData("CURRENT TICKS (right motor): ", rightMotorA.getCurrentPosition());
            linearOp.telemetry.update();
//            linearOp.sleep(2000);
        }
    }


    public void driveBackward (double speed, double rotations) {
        if (linearOp.opModeIsActive()) {

            double ticks = rotations * TICKS_PER_ROTATION;
            setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            linearOp.telemetry.addData("TARGET TICKS: ", ticks);
            linearOp.telemetry.update();
//            linearOp.sleep(1000);
            while (rightMotorA.getCurrentPosition() > -ticks && linearOp.opModeIsActive()) {
                driveBackward(speed);
                linearOp.telemetry.addData("TARGET TICKS (in while): ", ticks);
                linearOp.telemetry.addData("CURRENT TICKS (left motor): ", leftMotorA.getCurrentPosition());
                linearOp.telemetry.addData("CURRENT TICKS (right motor): ", rightMotorA.getCurrentPosition());
                linearOp.telemetry.update();

            }
            stopMotors();
            linearOp.telemetry.addData("TARGET TICKS (stop motors): ", ticks);
            linearOp.telemetry.addData("CURRENT TICKS (left motor): ", leftMotorA.getCurrentPosition());
            linearOp.telemetry.addData("CURRENT TICKS (right motor): ", rightMotorA.getCurrentPosition());
            linearOp.telemetry.update();
//            linearOp.sleep(2000);

        }
    }





    public void DriveTankSquared (Gamepad gamepad1) {
//       if (gamepad1.left_stick_y > 1) {
//           //sets motor power
//       }

        linearOp.telemetry.addData("left stick: ", gamepad1.left_stick_y);
        linearOp.telemetry.update();
    }

    //PID DRIVE
    //Calculations
    public double PIDControl (double reference, double state){
        double error = reference + state;
        integralSum += error * timer.seconds();
        double derivative = (error - lastError)/timer.seconds();
        lastError = error;
        double output = (error * Kp) + (derivative * Kd) + (integralSum * Ki);
        return output;
    }

    public void driveForwardPID (double rotations) {
        if (linearOp.opModeIsActive()) {
            double ticks = rotations * TICKS_PER_ROTATION;
            double speed = PIDControl(100, encoderAverage);     //100% power
            setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            while (encoderAverage < ticks && linearOp.opModeIsActive()) {
                driveFoward(speed);
            }
        }
    }
    public void driveBackwardPID (double rotations) {
        if (linearOp.opModeIsActive()) {
            double ticks = rotations * TICKS_PER_ROTATION;
            double speed = PIDControl(100, encoderAverage);     //100% power
            setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            while (encoderAverage < ticks && linearOp.opModeIsActive()) {
                driveBackward(speed);
            }
        }
    }





}
