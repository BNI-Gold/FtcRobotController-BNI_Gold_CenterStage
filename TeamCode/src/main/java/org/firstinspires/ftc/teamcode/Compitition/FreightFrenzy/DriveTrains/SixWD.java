package org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.DriveTrains;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;

public class SixWD {

   public DcMotorEx leftMotorA, leftMotorB, rightMotorA, rightMotorB;
   public LinearOpMode linearOp = null;

   public void setLinearOp(LinearOpMode linearOp){

    this.linearOp = linearOp;
   }

    public SixWD(){

    }

    public void stopMotors(){
       leftMotorA.setPower(0);
       leftMotorB.setPower(0);
       rightMotorA.setPower(0);
       rightMotorB.setPower(0);
    }

    public void driveFoward (double speed) {
        leftMotorB.setPower(speed);
        leftMotorA.setPower(speed);
        rightMotorA.setPower(speed);
        rightMotorB.setPower(speed);
    }

    public void driveBackward (double speed){
        leftMotorA.setPower(-speed);
        leftMotorB.setPower(-speed);
        rightMotorA.setPower(-speed);
        rightMotorB.setPower(-speed);
    }

    public void rotateright (){

    }




    public void DriveTankSquared (Gamepad gamepad1) {
//       if (gamepad1.left_stick_y > 1) {
//           //sets motor power
//       }

        linearOp.telemetry.addData("left stick: ", gamepad1.left_stick_y);
        linearOp.telemetry.update();
    }
}
