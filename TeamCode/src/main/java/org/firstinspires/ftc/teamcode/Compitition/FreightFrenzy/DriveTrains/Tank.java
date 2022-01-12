package org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.DriveTrains;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;

public class Tank {


    public LinearOpMode linearOp = null;

    public DcMotorEx leftMotorA, leftMotorB, rightMotorA, rightMotorB;

    public void setLinearOp(LinearOpMode linearOp){

        this.linearOp = linearOp;
    }


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
    public void DriveTankSquared (Gamepad gamepad1) {
//       if (gamepad1.left_stick_y > 1) {
//           //sets motor power
//       }

        linearOp.telemetry.addData("left stick: ", gamepad1.left_stick_y);
        linearOp.telemetry.update();
    }


}
