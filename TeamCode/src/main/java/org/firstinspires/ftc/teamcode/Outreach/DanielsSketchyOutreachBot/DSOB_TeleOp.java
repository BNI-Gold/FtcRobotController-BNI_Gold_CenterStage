package org.firstinspires.ftc.teamcode.Outreach.DanielsSketchyOutreachBot;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;


@TeleOp (name = "Daniel's Sketchy Outreach Bot")
public class DSOB_TeleOp extends OpMode {

    public double speedMultiply = 0.50;

    public double leftSidePower;
    public double rightSidePower;


    //GamePad Variables
    double leftStickYVal;
    double leftStickXVal;
    double rightStickYVal;
    double rightStickXVal;
    public DanielsSketchyOutreachBot dsob = new DanielsSketchyOutreachBot();

    @Override
    public void init (){
        dsob.initRobot(hardwareMap);
    }

    public void speedControl () {
        if (gamepad1.dpad_right == true) {
            speedMultiply = 0.50;}
        else if (gamepad1.dpad_down == true) {
            speedMultiply = 0.60;}
        else if (gamepad1.dpad_left == true) {
            speedMultiply = 0.75;}
        else if (gamepad1.dpad_up == true){
            speedMultiply = 0.25;}
        else if (gamepad1.a == true){
            speedMultiply = 1.00;}
    }

   public void loop (){

   }


   public void drive(){
//       leftStickYVal = gamepad1.left_stick_y;
//       leftStickYVal = Range.clip(leftStickYVal, -1, 1);
//
//       rightStickYVal = gamepad1.right_stick_y;
//       rightStickYVal = Range.clip(rightStickYVal, -1, 1);
//
//       leftSidePower = speedMultiply * leftStickYVal * (-1);
//       rightSidePower = speedMultiply * rightStickYVal * (-1);
//       dsob.tankDrive(leftSidePower, rightSidePower);


//       leftStickYVal = gamepad1.left_stick_y;
//       leftStickYVal = Range.clip(leftStickYVal, -1, 1);
//
//       leftStickXVal = gamepad1.left_stick_x;
//       leftStickXVal = Range.clip(leftStickXVal, -1, 1);
//
//       if (leftStickYVal < -0.1) {
//           dsob.driveForward(speedMultiply * leftStickYVal);
//       } else if (leftStickYVal > 0.1) {
//           dsob.driveBackwards(speedMultiply * leftStickYVal);
//       } else if (leftStickXVal > 0.1) {
//           dsob.rotateRight(speedMultiply * leftStickXVal);
//       } else if (leftStickXVal < -0.1) {
//           dsob.rotateLeft(speedMultiply * leftStickXVal);
//       } else {
//           dsob.stopMotors();
//       }


       leftStickYVal = gamepad1.left_stick_y;
       leftStickYVal = Range.clip(leftStickYVal, -1, 1);
       leftStickXVal = gamepad1.left_stick_x;
       leftStickXVal = Range.clip(leftStickXVal, -1, 1);
       rightStickYVal = gamepad1.right_stick_y;
       rightStickYVal = Range.clip(rightStickYVal, -1, 1);
       rightStickXVal = gamepad1.right_stick_x;
       rightStickXVal = Range.clip(rightStickXVal, -1, 1);

       if (leftStickYVal < -0.1) {
           dsob.driveForward(speedMultiply*leftStickYVal);
       }
       else if (leftStickYVal > 0.1){
           dsob.driveBackwards(speedMultiply*leftStickYVal);
       }

       else if (rightStickXVal > 0.1) {
           dsob.rotateRight(speedMultiply*rightStickXVal);
       }

       else if (rightStickXVal < -0.1) {
           dsob.rotateLeft(speedMultiply*rightStickXVal);
       }
       else {
           dsob.stopMotors();
       }
   }


}
