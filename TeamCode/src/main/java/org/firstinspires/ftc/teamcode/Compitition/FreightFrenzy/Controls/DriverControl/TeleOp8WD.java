package org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Controls.DriverControl;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
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
        DuckSpinner();
    }



    public void DuckSpinner () {
       if (gamepad2.dpad_left == true);
            Bot.SpinleftDuckTurner();

       if (gamepad2.dpad_right == true);
            Bot.SpinrightDuckTurner();

       if (gamepad2.dpad_down == true);
            Bot.StopDuckTurner();
    }





}
