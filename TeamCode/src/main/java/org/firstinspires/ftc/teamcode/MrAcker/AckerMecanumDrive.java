package org.firstinspires.ftc.teamcode.MrAcker;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class AckerMecanumDrive {

    // Instance Variables & Constants
    public DcMotor frontLeftMotor;
    public DcMotor frontRightMotor;
    public DcMotor rearRightMotor;
    public DcMotor rearLeftMotor;
    public static final double TICKS_PER_ROTATION = 383.6;   // Set for GoBilda 13.7 Motor
    public LinearOpMode linearOp = null;

    // Required for Class Inheritance
    public void setLinearOp(LinearOpMode linearOp) {
        this.linearOp = linearOp;
    }

    // Default constructor
    public AckerMecanumDrive() {}

    // Drivetrain Control Methods

    public void setMotorRunModes (DcMotor.RunMode mode) {
        frontLeftMotor.setMode(mode);
        frontRightMotor.setMode(mode);
        rearLeftMotor.setMode(mode);
        rearRightMotor.setMode(mode);

    }

    public void setMotorSpeeds (double speed) {
        frontLeftMotor.setPower(speed);
        frontRightMotor.setPower(speed);
        rearRightMotor.setPower(speed);
        rearLeftMotor.setPower(speed);
    }


    public void stopMotors() {
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        rearLeftMotor.setPower(0);
        rearRightMotor.setPower(0);
    }

    //Movement Methods: Powers Motors with Power/Speed

    public void driveForward (double speed){
        frontLeftMotor.setPower(speed);
        frontRightMotor.setPower(speed);
        rearLeftMotor.setPower(speed);
        rearRightMotor.setPower(speed);
    }

    public void driveBackward (double speed){
        frontLeftMotor.setPower(-speed);
        frontRightMotor.setPower(-speed);
        rearLeftMotor.setPower(-speed);
        rearRightMotor.setPower(-speed);
    }

    public void rotateRight (double speed) {
        frontLeftMotor.setPower(speed);
        frontRightMotor.setPower(-speed);
        rearLeftMotor.setPower(speed);
        rearRightMotor.setPower(-speed);
    }

    public void rotateLeft (double speed) {
        frontLeftMotor.setPower(-speed);
        frontRightMotor.setPower(speed);
        rearLeftMotor.setPower(-speed);
        rearRightMotor.setPower(speed);
    }

    public void strafeLeft (double speed) {
        frontLeftMotor.setPower(-speed);
        frontRightMotor.setPower(speed);
        rearLeftMotor.setPower(speed);
        rearRightMotor.setPower(-speed);
    }

    public void strafeRight (double speed) {
        frontLeftMotor.setPower(speed);
        frontRightMotor.setPower(-speed);
        rearLeftMotor.setPower(-speed);
        rearRightMotor.setPower(speed);
    }

    // Movement Methods: Powers Motors with Power/Speed and Encoder Counts
    public void driveForward( double speed, double rotations) {

        if (linearOp.opModeIsActive()) {

            double ticks = rotations * TICKS_PER_ROTATION;
            setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            while (frontLeftMotor.getCurrentPosition() < ticks && linearOp.opModeIsActive()) {
                driveForward(speed);
            }
            stopMotors();
        }

    }


    public void driveBackward ( double speed, double rotations){

        if (linearOp.opModeIsActive()) {
            double ticks = rotations * (-1) * TICKS_PER_ROTATION;
            setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            while (frontLeftMotor.getCurrentPosition() > ticks && linearOp.opModeIsActive()) {
                driveBackward(speed);
            }
            stopMotors();
        }
    }


    public void rotateLeft (double speed, double rotations) {

        if (linearOp.opModeIsActive()) {
            double ticks = Math.abs(rotations) * (-1) * TICKS_PER_ROTATION; //strafing left moves encoder towards positive infinity
            setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            while (frontLeftMotor.getCurrentPosition() > ticks && linearOp.opModeIsActive()) {
                rotateLeft(speed);
            }
            stopMotors();
        }
    }

    public void rotateRight (double speed, double rotations) {

        if (linearOp.opModeIsActive()) {

            double ticks = Math.abs(rotations) * TICKS_PER_ROTATION; //strafing right moves encoder towards -infinity
            setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            while (frontLeftMotor.getCurrentPosition() < ticks && linearOp.opModeIsActive() ) {
                rotateRight(speed);
            }
            stopMotors();
        }
    }


    public void strafeRight (double speed, double rotations) {

        if (linearOp.opModeIsActive()) {

            double ticks = Math.abs(rotations) * TICKS_PER_ROTATION;
            setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            while (frontLeftMotor.getCurrentPosition() < ticks && linearOp.opModeIsActive()) {
                strafeRight(speed);
            }
            stopMotors();
        }
    }

    public void strafeLeft (double speed, double rotations) {

        if (linearOp.opModeIsActive()) {

            double ticks = Math.abs(rotations) * (-1) * TICKS_PER_ROTATION;
            setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            while (frontLeftMotor.getCurrentPosition() > ticks && linearOp.opModeIsActive()) {
                strafeLeft(speed);
            }
            stopMotors();
        }
    }


}
