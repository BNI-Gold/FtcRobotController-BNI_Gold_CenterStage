package org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Controls.Autonomous.Lab;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.internal.tfod.BorderedText;
import org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Robots.LabBot;

@Autonomous(name = "AutoBotEncoder")

public class AutoBotEncoder extends LinearOpMode {

    public LabBot Bot = new LabBot();

    @Override
    public void runOpMode() throws InterruptedException {
        Bot.initRobot(hardwareMap);
        Bot.setLinearOp(this);

        waitForStart();

        while (opModeIsActive()){

            Bot.driveForward(0.35, 11);
            sleep(1000);
            Bot.driveBackward(1,9);
            sleep(1000);




            requestOpModeStop();




        }
    }
}
