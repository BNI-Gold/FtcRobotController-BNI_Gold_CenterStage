package org.firstinspires.ftc.teamcode.Compitition.CompititionUltimateGoal.DriveTrains;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class SixWD {
    public DcMotor frontLeftMotor;
    public DcMotor frontRightMotor;
    public DcMotor rearRightMotor;
    public DcMotor rearLeftMotor;
    public static final double TICKS_PER_ROTATION = 383.6;   // GoBilda 13.7 Motor PPR
    public LinearOpMode linearOp = null;

    public void setLinearOp(LinearOpMode linearOp) {

        this.linearOp = linearOp;
    }

    public SixWD() {
    }

    public void stopMotors(double leftPower, double rightPower) {
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

    public void setMotorSpeeds (double leftPower, double rightPower) {
        frontLeftMotor.setPower(leftPower);
        frontRightMotor.setPower(rightPower);
        rearRightMotor.setPower(rightPower);
        rearLeftMotor.setPower(leftPower);
    }

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
}

