package org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Robots;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.DriveTrains.EightWD;
import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.DriveTrains.SixWD;

public class SixWheelBot extends SixWD {

    public HardwareMap hwBot = null;

    public SixWheelBot() {


    }

    public void initRobot(HardwareMap hardwareMap){
        hwBot = hardwareMap;


        leftMotorA = hwBot.get(DcMotorEx.class, "left_motor_a");
        leftMotorB = hwBot.get(DcMotorEx.class, "left_motor_b");
        rightMotorA = hwBot.get(DcMotorEx.class, "right_motor_a");
        rightMotorB = hwBot.get(DcMotorEx.class, "right_motor_b");

//        leftMotorA.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        leftMotorA.setDirection(DcMotorEx.Direction.REVERSE);
        leftMotorB.setDirection(DcMotorEx.Direction.REVERSE);
        rightMotorA.setDirection(DcMotorEx.Direction.FORWARD);
        rightMotorB.setDirection(DcMotorEx.Direction.FORWARD);

//        rightMotorA.setDirection(DcMotorSimple.Direction.FORWARD);


//Needed to fix this - only A was here, so "a" was having issues.
//        Commented these out and works:
//        leftMotorA.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        leftMotorA.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

//        setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        Causing issues 0 not sure why yet.

        leftMotorA.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightMotorA.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        leftMotorB.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightMotorB.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);


    }
}
