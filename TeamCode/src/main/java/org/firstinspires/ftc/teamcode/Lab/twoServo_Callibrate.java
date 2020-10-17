package org.firstinspires.ftc.teamcode.Lab;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "wobble arm calibration")
public class twoServo_Callibrate extends OpMode {

    private Servo wobbleArm = null;

    private double wobbleArmPos = 0.16;

    private double incVal = 0.001;

    private Servo wobbleGrab = null;

    private double wobbleGrabPos = 0.5;




    @Override
    public void init () {
        wobbleArm = hardwareMap.servo.get("wobble_arm");
        wobbleArm.setPosition(wobbleArmPos);
        wobbleGrab = hardwareMap.servo.get("wobble_grab");
        wobbleGrab.setPosition((wobbleGrabPos));
    }

    @Override
    public void loop () {
        if (gamepad1.right_bumper) {
            wobbleArmPos += incVal;
            wobbleArmPos = Range.clip(wobbleArmPos,0,1);
            telemetry.addLine("Increase Servo Pos!");
        }

        if (gamepad1.left_bumper){
            wobbleArmPos-= incVal;
            wobbleArmPos = Range.clip(wobbleArmPos, 0,  1);
            telemetry.addLine( "Decrease Servo Pos!");
        }

        wobbleArm.setPosition(wobbleArmPos);

        if (gamepad2.right_bumper) {
            wobbleGrabPos += incVal;
            wobbleGrabPos = Range.clip(wobbleGrabPos,0,1);
            telemetry.addLine("Increase Servo Pos!");
        }

        if (gamepad2.left_bumper){
            wobbleGrabPos-= incVal;
            wobbleGrabPos = Range.clip(wobbleGrabPos, 0,  1);
            telemetry.addLine( "Decrease Servo Pos!");
        }

        wobbleGrab.setPosition(wobbleGrabPos);
        updateTelemetry();
    }



    public void updateTelemetry () {
        telemetry.addLine("RB: increase, LB: Decrease");
        telemetry.addLine("x = set to .90, y = set to 0.10");
        telemetry.addData("TestS ervo Positiom: ", wobbleArm.getPosition());
        telemetry.addData("Servo Variable Position: ", wobbleArm);
        telemetry.addData("Servo Wobble Position", wobbleGrab.getPosition());
        telemetry.addData("Servo Wobble Grab Position ", wobbleGrabPos);
        telemetry.update();
    }
}
