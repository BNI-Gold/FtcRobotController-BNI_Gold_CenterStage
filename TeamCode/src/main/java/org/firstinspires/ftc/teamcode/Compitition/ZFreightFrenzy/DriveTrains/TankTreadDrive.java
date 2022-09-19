package org.firstinspires.ftc.teamcode.Compitition.ZFreightFrenzy.DriveTrains;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

public class TankTreadDrive {

    // Go Bilda motor: https://www.gobilda.com/5203-series-yellow-jacket-planetary-gear-motor-19-2-1-ratio-24mm-length-8mm-rex-shaft-312-rpm-3-3-5v-encoder/
    public static final double TICKS_PER_ROTATION = 537.7;

    public LinearOpMode linearOp = null;

    public DcMotorEx leftMotorA, leftMotorB, rightMotorA, rightMotorB;

    public double encoderAvg;

    ElapsedTime timer;



    public void setLinearOp(LinearOpMode linearOp){

        this.linearOp = linearOp;
    }

    public void setMotorRunModes (DcMotor.RunMode mode) {

        leftMotorA.setMode(mode);
        leftMotorB.setMode(mode);
        rightMotorA.setMode(mode);
        rightMotorB.setMode(mode);
    }

    public void stopMotors(){
        leftMotorA.setPower(0);
        leftMotorB.setPower(0);
        rightMotorA.setPower(0);
        rightMotorB.setPower(0);
    }

    // Drive forward with power only.
    public void driveForward (double speed) {
        leftMotorB.setPower(speed);
        leftMotorA.setPower(speed);
        rightMotorA.setPower(speed);
        rightMotorB.setPower(speed);


    }


    // ENCODER drive Forward
    public void driveForward (double speed, double rotations) {
        encoderAvg = 0;
        if (linearOp.opModeIsActive()) {

            double ticks = rotations * TICKS_PER_ROTATION;
            setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//            linearOp.telemetry.addData("TARGET TICKS: ", ticks);
//            linearOp.telemetry.update();
//            linearOp.sleep(1000);
            while (encoderAvg < ticks && linearOp.opModeIsActive()) {
                driveForward(speed);
                encoderAvg = leftMotorA.getCurrentPosition() + rightMotorA.getCurrentPosition();
//                linearOp.telemetry.addData("TARGET TICKS (in while): ", ticks);
//                linearOp.telemetry.addData("CURRENT TICKS (left motor): ", leftMotorA.getCurrentPosition());
//                linearOp.telemetry.addData("CURRENT TICKS (right motor): ", rightMotorA.getCurrentPosition());
//                linearOp.telemetry.update();

            }
            stopMotors();
//            linearOp.telemetry.addData("TARGET TICKS (stop motors): ", ticks);
//            linearOp.telemetry.addData("CURRENT TICKS (left motor): ", leftMotorA.getCurrentPosition());
//            linearOp.telemetry.addData("CURRENT TICKS (right motor): ", rightMotorA.getCurrentPosition());
//            linearOp.telemetry.update();
//            linearOp.sleep(2000);

        }
    }

    public void driveBackward (double speed){
        leftMotorA.setPower(-speed);
        leftMotorB.setPower(-speed);
        rightMotorA.setPower(-speed);
        rightMotorB.setPower(-speed);
    }

    public void driveBackward (double speed, double rotations) {
        if (linearOp.opModeIsActive()) {

            double ticks = rotations * TICKS_PER_ROTATION;
            setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//            linearOp.telemetry.addData("TARGET TICKS: ", ticks);
//            linearOp.telemetry.update();
//            linearOp.sleep(1000);
            while (rightMotorA.getCurrentPosition() > -ticks && linearOp.opModeIsActive()) {
                driveBackward(speed);
//                linearOp.telemetry.addData("TARGET TICKS (in while): ", ticks);
//                linearOp.telemetry.addData("CURRENT TICKS (left motor): ", leftMotorA.getCurrentPosition());
//                linearOp.telemetry.addData("CURRENT TICKS (right motor): ", rightMotorA.getCurrentPosition());
//                linearOp.telemetry.update();

            }
            stopMotors();
//            linearOp.telemetry.addData("TARGET TICKS (stop motors): ", ticks);
//            linearOp.telemetry.addData("CURRENT TICKS (left motor): ", leftMotorA.getCurrentPosition());
//            linearOp.telemetry.addData("CURRENT TICKS (right motor): ", rightMotorA.getCurrentPosition());
//            linearOp.telemetry.update();
//            linearOp.sleep(2000);

        }
    }

    public void driveBackward (double speed, double rotations, double timeThreshold) {
        if (linearOp.opModeIsActive()) {
            timer = new ElapsedTime();
            timer.reset();
            double ticks = rotations * TICKS_PER_ROTATION;
            setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//            linearOp.telemetry.addData("TARGET TICKS: ", ticks);
//            linearOp.telemetry.update();
//            linearOp.sleep(1000);
            while (rightMotorA.getCurrentPosition() > -ticks && timer.milliseconds() <= timeThreshold && linearOp.opModeIsActive()) {
//                linearOp.telemetry.addData("current timer (ms): ", timer.milliseconds());
//                linearOp.telemetry.addData("threshold time: ", timeThreshold);
//                linearOp.telemetry.update();
                driveBackward(speed);
//                linearOp.telemetry.addData("TARGET TICKS (in while): ", ticks);
//                linearOp.telemetry.addData("CURRENT TICKS (left motor): ", leftMotorA.getCurrentPosition());
//                linearOp.telemetry.addData("CURRENT TICKS (right motor): ", rightMotorA.getCurrentPosition());
//                linearOp.telemetry.update();

            }
            stopMotors();
//            linearOp.telemetry.addData("TARGET TICKS (stop motors): ", ticks);
//            linearOp.telemetry.addData("CURRENT TICKS (left motor): ", leftMotorA.getCurrentPosition());
//            linearOp.telemetry.addData("CURRENT TICKS (right motor): ", rightMotorA.getCurrentPosition());
//            linearOp.telemetry.update();
//            linearOp.sleep(2000);

        }
    }



    public void rotateRight (double speed){
        leftMotorB.setPower(speed);
        leftMotorA.setPower(speed);
        rightMotorA.setPower(-speed);
        rightMotorB.setPower(-speed);
    }

    public void rotateRight (double speed, double rotations) {
        if (linearOp.opModeIsActive()) {

            double ticks = rotations * TICKS_PER_ROTATION;
            setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//            linearOp.telemetry.addData("TARGET TICKS: ", ticks);
//            linearOp.telemetry.update();
//            linearOp.sleep(1000);
            while (leftMotorA.getCurrentPosition() <= ticks && linearOp.opModeIsActive()) {
                rotateRight(speed);
//                linearOp.telemetry.addData("TARGET TICKS (in while): ", ticks);
//                linearOp.telemetry.addData("CURRENT TICKS (left motor): ", leftMotorA.getCurrentPosition());
//                linearOp.telemetry.addData("CURRENT TICKS (right motor): ", rightMotorA.getCurrentPosition());
                linearOp.telemetry.update();

            }
            stopMotors();
//            linearOp.telemetry.addData("TARGET TICKS (stop motors): ", ticks);
//            linearOp.telemetry.addData("CURRENT TICKS (left motor): ", leftMotorA.getCurrentPosition());
//            linearOp.telemetry.addData("CURRENT TICKS (right motor): ", rightMotorA.getCurrentPosition());
//            linearOp.telemetry.update();
//            linearOp.sleep(2000);

        }
    }

    public void rotateLeft (double speed){
        leftMotorB.setPower(-speed);
        leftMotorA.setPower(-speed);
        rightMotorB.setPower(speed);
        rightMotorA.setPower(speed);
    }

    public void rotateLeft (double speed, double rotations) {
        if (linearOp.opModeIsActive()) {

            double ticks = rotations * TICKS_PER_ROTATION;
            setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//            linearOp.telemetry.addData("TARGET TICKS", ticks);
//            linearOp.telemetry.update();
//            linearOp.sleep(1000);
            while (rightMotorA.getCurrentPosition() <= ticks && linearOp.opModeIsActive()) {
                rotateLeft(speed);
//                linearOp.telemetry.addData("TARGET TICKS (in while): ", ticks);
//                linearOp.telemetry.addData("CURRENT TICKS (left motor): ", leftMotorA.getCurrentPosition());
//                linearOp.telemetry.addData("CURRENT TICKS (right motor): ", rightMotorA.getCurrentPosition());
//                linearOp.telemetry.update();

            }
            stopMotors();
//            linearOp.telemetry.addData("TARGET TICKS (stop motors): ", ticks);
//            linearOp.telemetry.addData("CURRENT TICKS (left motor): ", leftMotorA.getCurrentPosition());
//            linearOp.telemetry.addData("CURRENT TICKS (right motor): ", rightMotorA.getCurrentPosition());
//            linearOp.telemetry.update();
//            linearOp.sleep(2000);
        }
    }


}
