package org.firstinspires.ftc.teamcode.Lab;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

public class MotorSpeedControl extends OpMode {

    //reset elepased time when press A

    DcMotor myMotor = null;


    public ElapsedTime TeleOpTime;

    public double targetHighGoalEncoder = 1000;

    public double encodersPerSecond = 0;


    public boolean initTeleOpToggle = true;
    public LinearOpMode linearOp = null;

    public double motorSpeed = 0.5;

    public boolean powerMode = true;

    public final static int ENCODER_PPR = 28;

    @Override
    public void init() {


        myMotor = hardwareMap.dcMotor.get ("launch_motor");
        myMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        TeleOpTime = new ElapsedTime();

        myMotor.setMode(DcMotor.RunMode.RESET_ENCODERS);

    }

    @Override
    public void loop() {
        encodersPerSecond = (myMotor.getCurrentPosition() / TeleOpTime.seconds());

        if (initTeleOpToggle == true) {
            initTeleOp();
        }

        if (gamepad2.a) {
            resetProgram();
        }

        if (powerMode == true) {
            if (gamepad2.right_bumper) {
                motorSpeed += .01;
            }
            if (gamepad2.left_bumper) {
                motorSpeed -= .01;
            }
        }

        else if (powerMode == false) {
            if (gamepad2.right_bumper) {
                targetHighGoalEncoder += 10;
            }
            if (gamepad2.left_bumper) {
                targetHighGoalEncoder -= 10;
            }


            if (encodersPerSecond < targetHighGoalEncoder) {
                telemetry.addLine("ENCODERS SLOW; INCREASING MOTOR SPEED");
                motorSpeed += 0.01;
            }

            if (encodersPerSecond > targetHighGoalEncoder) {
                telemetry.addLine("ENDCODERS FAST; DECREASING MOTOR SPEED");
                motorSpeed -= 0.01;
            }



        }

        Range.clip(motorSpeed, 0, 1);
        myMotor.setPower(motorSpeed);


        if (gamepad2.dpad_right == true) {
            powerMode = true;
        }

        if (gamepad2.dpad_left = false) {
            powerMode = false;
        }

        displayTelemetry();

    }

    public void setMotorRunModes (DcMotor.RunMode mode) {
        myMotor.setMode(mode);
    }

    public void setLinearOp(LinearOpMode linearOp) {

        this.linearOp = linearOp;
    }

    public void displayTelemetry () {
        telemetry.addLine("Press A to reset timer and encoders");
        telemetry.addLine("Dpad R:control power motor; Dpad L: Control encoders");
        if (powerMode == true) {
            telemetry.addLine("MOTOR POWER MODE");
            telemetry.addLine("RB: Increase Motor Speed");
            telemetry.addLine("LB: Decrease Motor Speed");
        }

        if (powerMode == false) {
            telemetry.addLine("ENCODER CONTROL MODE");
            telemetry.addLine("RB: Increase Encoders per Second");
            telemetry.addLine("LB: Decrease Encoders per Second");
            telemetry.addData("TARGET ENCODERS PER SECOND: ", targetHighGoalEncoder);
        }

        telemetry.addData("Current Motor Power: ", myMotor.getPower());
        telemetry.addData("Current Time (ms): ", TeleOpTime.seconds());
        telemetry.addData("Current Encoders: ", myMotor.getCurrentPosition());
        telemetry.addData("Encoders per second: ", encodersPerSecond);
//        telemetry.addData("Encoders per 1 minute: ", (encodersPerSecond * 60));
        telemetry.addData("RPMs!: ", (encodersPerSecond * ENCODER_PPR) / 60);
    }

    public void resetProgram () {
        TeleOpTime.reset();
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void initTeleOp () {
        TeleOpTime.reset();
        initTeleOpToggle = false;               // false so initializes only once
    }
}
