package org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Modules;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Servos {

    public Servo servoArm;

    public LinearOpMode linearOp = null;

    public double servoArmOpenPos = 0.72;
    public double servoArmClosePos = 1.0;

    public void setLinearOp(LinearOpMode linearOp) {

        this.linearOp = linearOp;
    }

    public Servos () {

    }

    public void CloseArm () {
        servoArm.setPosition(servoArmClosePos);
    }

    public void OpenArm () {
        servoArm.setPosition(servoArmOpenPos);
    }
}
