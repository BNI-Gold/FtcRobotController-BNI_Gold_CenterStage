package org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Controls;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Robots.EightWheelBot;

public class TeleOp8WD extends OpMode {
    public EightWheelBot Bot = new EightWheelBot();
    public Gamepad gamepad1;

    @Override
    public void init(){
        Bot.initRobot(hardwareMap);
        Bot.stopMotors();
    }

    @Override
    public void loop() {
        Bot.DriveTankSquared(gamepad1);
    }

}
