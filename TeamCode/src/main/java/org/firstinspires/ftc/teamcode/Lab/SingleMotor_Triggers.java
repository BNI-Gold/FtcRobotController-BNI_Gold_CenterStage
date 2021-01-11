package org.firstinspires.ftc.teamcode.Lab;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

//Class is used for testing a single motor, using trigger for variable power.
@Disabled
public class SingleMotor_Triggers extends OpMode {
    private DcMotor motor = null;
    double power;

    @Override
    public void init() {
        motor = hardwareMap.dcMotor.get("motor");
        motor.setDirection(DcMotorSimple.Direction.FORWARD);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        power = 0;

        telemetry.addLine("Right Trigger to go 'forward'");
        telemetry.addLine("Left Trigger to go 'reverse'");
        telemetry.update();
    }

    @Override
    public void loop() {
        if (gamepad1.right_trigger > 0.1) {
            power = gamepad1.left_trigger;
        }
        if (gamepad1.left_trigger > 0.1) {
            power = -gamepad1.left_trigger;
        }

        motor.setPower(power);

        update_telemetry();
    }

    public void update_telemetry () {
        telemetry.addData("Right Trigger Value: ", gamepad1.right_trigger);
        telemetry.addData("Left Trigger Value: ", gamepad1.left_trigger);
        telemetry.addData("POWER: ", power);
    }
}
