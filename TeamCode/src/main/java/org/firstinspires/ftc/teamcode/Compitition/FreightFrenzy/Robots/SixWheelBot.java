package org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Robots;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.DriveTrains.EightWD;
import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.DriveTrains.SixWD;

public class SixWheelBot extends SixWD {

    public HardwareMap hwBot = null;


    public CRServo DuckTurnerleft = null;
    public CRServo DuckTurnerright = null;

    public Servo intakeLyft2 = null;
    public double intakeLyft2up = 1;
    //.13 before (Mar 3, 2021 3:42pm)
    public double intakeLyft2down = 0.785 ;

    public DcMotor lift;
    public DcMotor intake;
    public SixWheelBot() {


    }

    public void initRobot(HardwareMap hardwareMap){
        hwBot = hardwareMap;

        intakeLyft2 = hwBot.get(Servo.class,"Intake_Lyft2");


        leftMotorA = hwBot.get(DcMotorEx.class, "left_motor_a");
        leftMotorB = hwBot.get(DcMotorEx.class, "left_motor_b");
        rightMotorA = hwBot.get(DcMotorEx.class, "right_motor_a");
        rightMotorB = hwBot.get(DcMotorEx.class, "right_motor_b");

//        leftMotorA.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        leftMotorA.setDirection(DcMotorEx.Direction.FORWARD);
        leftMotorB.setDirection(DcMotorEx.Direction.FORWARD);
        rightMotorA.setDirection(DcMotorEx.Direction.REVERSE);
        rightMotorB.setDirection(DcMotorEx.Direction.REVERSE);

//        intakeLyft = hwBot.get(DcMotorEx.class,"Intake_Lyft");
        intake = hardwareMap.dcMotor.get ("Intake");
//        intake
//        motor = hardwareMap.dcMotor.get("motor");


        intake.setDirection(DcMotor.Direction.FORWARD);
//        intakeLyft.setDirection(DcMotorEx.Direction.FORWARD);

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

        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        intakeLyft.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        DuckTurnerleft = hardwareMap.crservo.get ("Duck_Turner_Left");
        DuckTurnerleft.setDirection(DcMotorSimple.Direction.REVERSE);
        DuckTurnerleft.setPower(0);

        DuckTurnerright = hardwareMap.crservo.get ("Duck_Turner_Right");
        DuckTurnerright.setDirection(DcMotorSimple.Direction.REVERSE);
        DuckTurnerright.setPower(0);


    }

    public void setIntakeLyft2up (){
        intakeLyft2.setPosition(intakeLyft2up);
    }
    public void setIntakeLyft2down (){
        intakeLyft2.setPosition(intakeLyft2down);
    }

    public void SpinDuckRightleft(){
        DuckTurnerleft.setPower(1);
    }

    public void SpinDuckleftleft(){
        DuckTurnerleft.setPower(-1);
    }

    public void StopSpinningDuckleft(){
        DuckTurnerleft.setPower(0);
    }

    public void SpinDuckRightRight(){
        DuckTurnerright.setPower(1);
    }

    public void SpinDuckleftRight(){
        DuckTurnerright.setPower(-1);
    }

    public void StopSpinningDuckRight(){
        DuckTurnerright.setPower(0);
    }

    public void Intake (double speed){
        intake.setPower(speed);
    }

    public void SpinIntake (double speed){
        intake.setPower(1);
    }
    public void StopIntake (double speed){
        intake.setPower(0);
    }
    public void ReverseIntake (double speed){
        intake.setPower(-1);
    }

}
