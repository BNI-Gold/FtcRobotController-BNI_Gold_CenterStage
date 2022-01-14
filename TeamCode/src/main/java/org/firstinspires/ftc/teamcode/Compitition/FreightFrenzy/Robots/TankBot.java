package org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Robots;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.DriveTrains.TankTreadDrive;

public class TankBot extends TankTreadDrive {

    public HardwareMap hwBot = null;

    public DcMotor DuckSpinner;
    public DcMotor Lyft;
    public DcMotor intake;

    public double duckSpinnerPower = 0.32;

    public double LyftExtendPower = 1.0;
    public double LyftRetractPower = -1.0;

    public Servo boxHolder = null;

    // Higher value = gate higher
    // Lower value == gate goes down more.
    public double boxHolder_Up = .37;
    public double boxHolder_Down = 0.46;
    public double boxHolder_Release = 0.51;
//    public CRServo LyftServo = null;

    public void initRobot(HardwareMap hardwareMap) {
        hwBot = hardwareMap;

        leftMotorA = hwBot.get(DcMotorEx.class, "left_motor_a");
        leftMotorB = hwBot.get(DcMotorEx.class, "left_motor_b");
        rightMotorA = hwBot.get(DcMotorEx.class, "right_motor_a");
        rightMotorB = hwBot.get(DcMotorEx.class, "right_motor_b");

        leftMotorA.setDirection(DcMotorEx.Direction.FORWARD);
        leftMotorB.setDirection(DcMotorEx.Direction.FORWARD);
        rightMotorA.setDirection(DcMotorEx.Direction.REVERSE);
        rightMotorB.setDirection(DcMotorEx.Direction.REVERSE);

        leftMotorA.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightMotorA.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        leftMotorB.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightMotorB.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        Lyft = hwBot.get(DcMotorEx.class, "lyft_extender");
        Lyft.setDirection(DcMotorEx.Direction.REVERSE);
        DuckSpinner = hwBot.get(DcMotorEx.class, "duck_spinner");
        DuckSpinner.setDirection(DcMotorEx.Direction.FORWARD);
        intake = hardwareMap.dcMotor.get("intake");
        intake.setDirection(DcMotor.Direction.FORWARD);

        boxHolder = hwBot.get(Servo.class, "box_holder");
        setBoxHolder_Up();

    }

    public void duckspinclockwise() {
        DuckSpinner.setPower(duckSpinnerPower);
    }

    public void duckspincounterclockwise() {
        DuckSpinner.setPower(-duckSpinnerPower);
    }

    public void duckspinstop() {
        DuckSpinner.setPower(0);
    }

    public void Intake(double speed) {
        intake.setPower(speed);
    }

    public void LyftExtend()
    {
        Lyft.setPower(LyftExtendPower);
    }

    public void LyftRetract () {
        Lyft.setPower(LyftRetractPower);
    }

    public void LyftStopMotors () {
        Lyft.setPower(0);
    }

    public void setBoxHolder_Up() {
        boxHolder.setPosition(boxHolder_Up);
    }

    public void setBoxHolder_Down() {
        boxHolder.setPosition(boxHolder_Down);
    }

    public void setBoxHolder_Release () {
        boxHolder.setPosition(boxHolder_Release);
    }

}
