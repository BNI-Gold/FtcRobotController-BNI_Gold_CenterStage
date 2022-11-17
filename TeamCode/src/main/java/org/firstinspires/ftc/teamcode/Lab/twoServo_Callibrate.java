package org.firstinspires.ftc.teamcode.Lab;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "Grabber Arm")
//@Disabled

public class twoServo_Callibrate extends OpMode {

    private Servo grabberArmLeft = null;
    private double grabberLeftArmPos = 0.6;
    private double incVal = 0.001;
    private Servo grabberArmRight = null;
    private double grabberRightArmPos = 0.6;


    @Override
    public void init () {
        grabberArmLeft = hardwareMap.servo.get("grabber_arm_left");
        grabberArmLeft.setPosition(grabberLeftArmPos);
        grabberArmRight = hardwareMap.servo.get("grabber_arm_right");
        grabberArmRight.setPosition((grabberRightArmPos));
    }

    @Override
    public void loop () {
        if (gamepad1.right_bumper) {
            grabberLeftArmPos += incVal;
            grabberLeftArmPos = Range.clip(grabberLeftArmPos,0,1);
            telemetry.addLine("Increase Servo Pos!");
        }

        if (gamepad1.left_bumper){
            grabberLeftArmPos -= incVal;
            grabberLeftArmPos = Range.clip(grabberLeftArmPos, 0,  1);
            telemetry.addLine( "Decrease Servo Pos!");
        }
        if (gamepad1.x) {
            grabberArmLeft.setPosition(.90);
        }

        if (gamepad1.y) {
            grabberArmLeft.setPosition(.10);
        }


        grabberArmLeft.setPosition(grabberLeftArmPos);

        if (gamepad2.right_bumper) {
            grabberRightArmPos += incVal;
            grabberRightArmPos = Range.clip(grabberRightArmPos,0,1);
            telemetry.addLine("Increase Servo Pos!");
        }

        if (gamepad2.left_bumper){
            grabberRightArmPos -= incVal;
            grabberRightArmPos = Range.clip(grabberRightArmPos, 0,  1);
            telemetry.addLine( "Decrease Servo Pos!");
        }

        if (gamepad2.x) {
            grabberArmRight.setPosition(.90);
        }

        if (gamepad2.y) {
            grabberArmRight.setPosition(.10);
        }


        grabberArmRight.setPosition(grabberRightArmPos);
        updateTelemetry();
    }


    public void updateTelemetry () {
        telemetry.addLine("RB: increase, LB: Decrease");
        telemetry.addLine("x = set to .90, y = set to 0.10");
        telemetry.addData("Grabber Left Arm Position:", grabberArmLeft.getPosition());
        telemetry.addData("Grabber Left Arm Position:", grabberLeftArmPos);
        telemetry.addData("Grabber Right Arm Position", grabberArmRight.getPosition());
        telemetry.addData("Grabber Right Arm Position", grabberRightArmPos);
        telemetry.update();
    }
}
