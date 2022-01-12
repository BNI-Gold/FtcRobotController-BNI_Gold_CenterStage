package org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Robots;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.DriveTrains.Tank;

public class TankBot extends Tank {

    public HardwareMap hwBot = null;

    public DcMotor DuckSpinner;
    public DcMotor Lyft;
    public DcMotor intake;

    public double LyftExtendPower = 0.6;
    public double LyftRetractPower = -0.6;

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
        Lyft.setDirection(DcMotorEx.Direction.FORWARD);
        DuckSpinner = hwBot.get(DcMotorEx.class, "duck_spinner");
        DuckSpinner.setDirection(DcMotorEx.Direction.FORWARD);
        intake = hardwareMap.dcMotor.get("intake");
        intake.setDirection(DcMotor.Direction.FORWARD);

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

}
