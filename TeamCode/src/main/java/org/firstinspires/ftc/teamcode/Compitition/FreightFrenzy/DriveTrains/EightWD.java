package org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.DriveTrains;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class EightWD {

   public DcMotorEx leftMotorA, leftMotorB, rightMotorA, rightMotorB;
   public LinearOpMode linearOp = null;

   public void setLinearOp(LinearOpMode linearOp){

    this.linearOp = linearOp;
   }
  // 私は疲れました
    public EightWD(){

    }

    public void stopMotors(){
       leftMotorA.setPower(0);
       leftMotorB.setPower(0);
       rightMotorA.setPower(0);
       rightMotorB.setPower(0);
    }

    public void driveFoward () {


    }




    public void DriveTankSquared (Gamepad gamepad1) {
       if (gamepad1.left_stick_y > 1) {
           //sets motor power
       }


    }

}
