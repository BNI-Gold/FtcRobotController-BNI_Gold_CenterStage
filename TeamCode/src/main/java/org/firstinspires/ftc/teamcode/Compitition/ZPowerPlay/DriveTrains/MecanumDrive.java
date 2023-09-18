package org.firstinspires.ftc.teamcode.Compitition.ZPowerPlay.DriveTrains;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MecanumDrive {

    // Instance Variables & Constants

    public DcMotorEx frontLeftMotor;
    public DcMotorEx frontRightMotor;
    public DcMotorEx rearRightMotor;
    public DcMotorEx rearLeftMotor;
    public static final double TICKS_PER_ROTATION = 383.6;   // GoBilda 13.7 Motor PPR
    public LinearOpMode linearOp = null;

    public void setLinearOp(LinearOpMode linearOp) {

        this.linearOp = linearOp;
    }


    public MecanumDrive() {

    }

    public void stopMotors() {
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        rearLeftMotor.setPower(0);
        rearRightMotor.setPower(0);

    }


    public void setMotorRunModes (DcMotor.RunMode mode) {

        frontLeftMotor.setMode(mode);
        frontRightMotor.setMode(mode);
        rearLeftMotor.setMode(mode);
        rearRightMotor.setMode(mode);

    }

    // Sets speed for all motors with one method
    public void setMotorSpeeds (double speed) {
        frontLeftMotor.setPower(speed);
        frontRightMotor.setPower(speed);
        rearRightMotor.setPower(speed);
        rearLeftMotor.setPower(speed);
    }


    // Powers Motors with no encoder counts

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

    public void driveForward (double speed){
        frontLeftMotor.setPower(speed);
        frontRightMotor.setPower(speed);
        rearLeftMotor.setPower(speed);
        rearRightMotor.setPower(speed);
    }

    public void driveForward_PID (double power, double rotations) {
        int ticks = (int) (rotations * TICKS_PER_ROTATION);

        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);



        frontLeftMotor.setPower(power);
        frontLeftMotor.setTargetPosition(ticks);

        frontRightMotor.setPower(power);
        frontRightMotor.setTargetPosition(ticks);

        rearLeftMotor.setPower(power);
        rearLeftMotor.setTargetPosition(ticks);

        rearRightMotor.setPower(power);
        rearRightMotor.setTargetPosition(ticks);

        setMotorRunModes(DcMotor.RunMode.RUN_TO_POSITION);
        while (frontLeftMotor.isBusy() && linearOp.opModeIsActive()) {
            linearOp.idle();
        }
        stopMotors();

//        linearOp.sleep(500);
    }

    public void driveBackward_PID (double power, double rotations) {
        int ticks = (int) (rotations * TICKS_PER_ROTATION);

        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        frontLeftMotor.setPower(-power);
        frontLeftMotor.setTargetPosition(-ticks);

        frontRightMotor.setPower(-power);
        frontRightMotor.setTargetPosition(-ticks);

        rearLeftMotor.setPower(-power);
        rearLeftMotor.setTargetPosition(-ticks);

        rearRightMotor.setPower(-power);
        rearRightMotor.setTargetPosition(-ticks);

        setMotorRunModes(DcMotor.RunMode.RUN_TO_POSITION);
        while (frontLeftMotor.isBusy() && linearOp.opModeIsActive()) {
            linearOp.idle();
        }
        stopMotors();
    }

    public void strafeLeft_PID (double power, double rotations) {
        int ticks = (int) (rotations * TICKS_PER_ROTATION);

        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        frontLeftMotor.setPower(-power);
        frontLeftMotor.setTargetPosition(-ticks);

        frontRightMotor.setPower(power);
        frontRightMotor.setTargetPosition(ticks);

        rearLeftMotor.setPower(power);
        rearLeftMotor.setTargetPosition(ticks);

        rearRightMotor.setPower(-power);
        rearRightMotor.setTargetPosition(-ticks);

        setMotorRunModes(DcMotor.RunMode.RUN_TO_POSITION);
        while (frontLeftMotor.isBusy() && linearOp.opModeIsActive()) {
            linearOp.idle();
        }
        stopMotors();
    }

    public void strafeRight_PID (double power, double rotations) {
        int ticks = (int) (rotations * TICKS_PER_ROTATION);

        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        frontLeftMotor.setPower(power);
        frontLeftMotor.setTargetPosition(ticks);

        frontRightMotor.setPower(-power);
        frontRightMotor.setTargetPosition(-ticks);

        rearLeftMotor.setPower(-power);
        rearLeftMotor.setTargetPosition(-ticks);

        rearRightMotor.setPower(power);
        rearRightMotor.setTargetPosition(ticks);

        setMotorRunModes(DcMotor.RunMode.RUN_TO_POSITION);
        while (frontLeftMotor.isBusy() && linearOp.opModeIsActive()) {
            linearOp.idle();
        }
        stopMotors();
    }

    public void driveBackward (double speed){
        frontLeftMotor.setPower(-speed);
        frontRightMotor.setPower(-speed);
        rearLeftMotor.setPower(-speed);
        rearRightMotor.setPower(-speed);
    }



    // Powers Motors with Encoder Counts

    public void driveForward( double speed, double rotations) {

        if (linearOp.opModeIsActive()) {

            double ticks = rotations * TICKS_PER_ROTATION;
            setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            linearOp.telemetry.addData("Current ticks: ", Math.abs(frontRightMotor.getCurrentPosition()));
            linearOp.telemetry.addData("Target ticks: ", ticks);
            linearOp.telemetry.addLine("Going into drive backwards loop");
            linearOp.telemetry.update();

            while (Math.abs(frontRightMotor.getCurrentPosition()) < ticks && linearOp.opModeIsActive()) {
                driveForward(speed);
            }
            stopMotors();
        }

    }


    public void driveBackward ( double speed, double rotations){

        if (linearOp.opModeIsActive()) {
//rotations * (-1) and a > before ticks line 136
            double ticks = Math.abs(rotations) * TICKS_PER_ROTATION;
            setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//            linearOp.telemetry.addData("Current ticks: ", frontLeftMotor.getCurrentPosition());
//            linearOp.telemetry.addData("Target ticks: ", ticks);
//            linearOp.telemetry.addLine("Going into drive backwards loop");
//            linearOp.telemetry.update();
//            linearOp.sleep(1000);
//            Switched from NR 20s to Go Bilda 13.7
            while (Math.abs(frontRightMotor.getCurrentPosition()) < ticks && linearOp.opModeIsActive()) {
                driveBackward(speed);
//                linearOp.telemetry.addData("Current ticks: ", Math.abs(frontRightMotor.getCurrentPosition()));
//                linearOp.telemetry.addData("Target ticks: ", ticks);
//                linearOp.telemetry.addLine("INSIDE drive backwards loop");
//                linearOp.telemetry.update();
            }
            stopMotors();
//            linearOp.telemetry.addData("Current ticks: ", frontLeftMotor.getCurrentPosition());
//            linearOp.telemetry.addData("Target ticks: ", ticks);
//            linearOp.telemetry.addLine("Finished with drive backwards loop");
//            linearOp.telemetry.update();
//            linearOp.sleep(1000);
        }
    }


    public void rotateLeft (double speed, double rotations) {

        if (linearOp.opModeIsActive()) {

            double ticks = Math.abs(rotations) * TICKS_PER_ROTATION; //strafing left moves encoder towards positive infinity
            setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            while (Math.abs(frontRightMotor.getCurrentPosition()) < ticks && linearOp.opModeIsActive()) {
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

            while (Math.abs(frontRightMotor.getCurrentPosition()) < ticks && linearOp.opModeIsActive() ) {
                rotateRight(speed);

                linearOp.telemetry.addData("Current ticks: ", Math.abs(frontRightMotor.getCurrentPosition()));
                linearOp.telemetry.addData("Target ticks: ", ticks);
                linearOp.telemetry.addLine("Going into drive backwards loop");
                linearOp.telemetry.update();
            }
            stopMotors();
        }
    }


    public void strafeRight (double speed, double rotations) {

        if (linearOp.opModeIsActive()) {

            double ticks = Math.abs(rotations) * TICKS_PER_ROTATION;
            setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            while (Math.abs(frontRightMotor.getCurrentPosition()) < ticks && linearOp.opModeIsActive()) {
                strafeRight(speed);
            }
            stopMotors();
        }
    }

    public void strafeLeft (double speed, double rotations) {

        if (linearOp.opModeIsActive()) {

            double ticks = Math.abs(rotations) * TICKS_PER_ROTATION;
            setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            while (Math.abs(frontRightMotor.getCurrentPosition()) < ticks && linearOp.opModeIsActive()) {
                strafeLeft(speed);

                linearOp.telemetry.addData("Current ticks: ", Math.abs(frontLeftMotor.getCurrentPosition()));
            linearOp.telemetry.addData("Target ticks: ", ticks);
//            linearOp.telemetry.addLine("Finished with drive backwards loop");
            linearOp.telemetry.update();
//            linearOp.sleep(1000);
            }
            stopMotors();
        }
    }


    //****
    //
    // Overloaded Methods for Powering Motors with Encoder Counts for TeleOp OpMode... TeleOp
    //
    // ***


    public void driveForward( double speed, double rotations, String Mode) {


        double ticks = rotations * TICKS_PER_ROTATION;
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while (Math.abs(frontRightMotor.getCurrentPosition()) < ticks) {
            driveForward(speed);
        }
        stopMotors();

    }


    public void driveBackward ( double speed, double rotations, String Mode){

        double ticks = rotations * (-1) * TICKS_PER_ROTATION;
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while (frontLeftMotor.getCurrentPosition() > ticks) {
            driveBackward(speed);
        }
        stopMotors();

    }


    public void rotateLeft (double speed, double rotations, String Mode) {

        double ticks = Math.abs(rotations) * (-1) * TICKS_PER_ROTATION; //strafing left moves encoder towards positive infinity
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while (frontLeftMotor.getCurrentPosition() > ticks) {
            rotateLeft(speed);
        }
        stopMotors();

    }

    public void rotateRight (double speed, double rotations, String Mode) {


        double ticks = Math.abs(rotations) * TICKS_PER_ROTATION; //strafing right moves encoder towards -infinity
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while (frontLeftMotor.getCurrentPosition() < ticks ) {
            rotateRight(speed);
        }
        stopMotors();
    }



    public void strafeRight (double speed, double rotations, String Mode) {

        double ticks = Math.abs(rotations) * TICKS_PER_ROTATION;
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while (frontLeftMotor.getCurrentPosition() < ticks ) {
            strafeRight(speed);
        }
        stopMotors();

    }

    public void strafeLeft (double speed, double rotations, String Mode) {

        double ticks = Math.abs(rotations) * (-1) * TICKS_PER_ROTATION;
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while (frontLeftMotor.getCurrentPosition() > ticks ) {
            strafeLeft(speed);
        }
        stopMotors();
    }


    public void initRobot(HardwareMap hardwareMap) {
    }
}
