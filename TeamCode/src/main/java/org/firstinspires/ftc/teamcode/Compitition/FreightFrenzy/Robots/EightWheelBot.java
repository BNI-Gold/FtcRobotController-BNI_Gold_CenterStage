package org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Robots;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.DriveTrains.EightWD;

public class EightWheelBot extends EightWD {

    public HardwareMap hwBot = null;

    public CRServo DuckTurner = null;


    public EightWheelBot () {


    }

    public void initRobot(HardwareMap hardwareMap){
        hwBot = hardwareMap;


        leftMotorA = hwBot.get(DcMotorEx.class, "left_motor_a");
        leftMotorB = hwBot.get(DcMotorEx.class, "left_motor_b");
        rightMotorA = hwBot.get(DcMotorEx.class, "right_motor_a");
        rightMotorB = hwBot.get(DcMotorEx.class, "right_motor_b");


        leftMotorA.setDirection(DcMotor.Direction.REVERSE);


        leftMotorA.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftMotorA.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //calling duck carasell spinner named "Duck_Turner"
        DuckTurner = hardwareMap.crservo.get ("Duck_Turner");
        DuckTurner.setDirection(DcMotorSimple.Direction.REVERSE);
        DuckTurner.setPower(0);
    }
    public void SpinrightDuckTurner(){
        DuckTurner.setPower(1);
    }

    public void SpinleftDuckTurner(){
        DuckTurner.setPower(-1);
    }

    public void StopDuckTurner(){
        DuckTurner.setPower(0);
    }



}
