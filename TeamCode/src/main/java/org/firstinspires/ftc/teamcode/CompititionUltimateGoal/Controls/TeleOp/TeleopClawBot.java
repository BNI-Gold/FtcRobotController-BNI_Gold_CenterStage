package org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Controls.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Robots.ClawBot;
import org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Robots.OutreachBot;

@TeleOp (name = "Teleop_ClawBot")
public class TeleopClawBot extends OpMode {
    public ClawBot Bot = new ClawBot();

    @Override
    public void init() {
        Bot.initRobot(hardwareMap, "TeleOp", "TeleOp");

    }

    @Override
    public void init_loop() {
    }
    @Override
    public void loop() {
        claw();
    }

    public void claw(){
        if (gamepad1.left_stick_y > 0.1){
            Bot.ClawForward(0.2);
        }
        else if (gamepad1.left_stick_y < -0.1){
            Bot.ClawBackward(0.2);
        }
        else{
            Bot.ClawBackward(0);
            Bot.ClawForward(0);
        }
        if (gamepad1.left_stick_x < -0.1){
            Bot.ClawLeftSide(0.2);
        }
        else if (gamepad1.left_stick_x > 0.1){
            Bot.ClawRightSide(0.2);
        }
        else{
            Bot.ClawLeftSide(0);
            Bot.ClawRightSide(0);
        }
        if (gamepad1.right_stick_y > 0.1){
            Bot.ClawUp(0.2);
        }
        else if (gamepad1.right_stick_y < -0.1){
            Bot.ClawDown(0.2);
        }
        else{
            Bot.ClawUp(0);
            Bot.ClawDown(0);
        }
    }
}
