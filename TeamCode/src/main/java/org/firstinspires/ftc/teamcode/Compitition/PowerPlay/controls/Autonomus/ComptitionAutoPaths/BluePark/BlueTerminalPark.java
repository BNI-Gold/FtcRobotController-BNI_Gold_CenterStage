package org.firstinspires.ftc.teamcode.Compitition.PowerPlay.controls.Autonomus.ComptitionAutoPaths.BluePark;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Compitition.PowerPlay.Robots.CompetionBot;
import org.firstinspires.ftc.teamcode.Compitition.PowerPlay.controls.Autonomus.ComptitionAutoPaths.AutoMain;
import org.firstinspires.ftc.teamcode.Compitition.PowerPlay.controls.Autonomus.ComptitionAutoPaths.AutoTargetZone;

@Autonomous (name = "BlueTerminalPark")

public class BlueTerminalPark extends AutoMain {

    public CompetionBot Bot = new CompetionBot();

    @Override
    public void runOpMode() throws InterruptedException {
        Bot.initRobot(hardwareMap);

        Bot.setLinearOp(this);

        waitForStart();

        while (opModeIsActive()) {

            Bot.driveForward(0.1, 0.1);

            Bot.strafeLeft(0.4, 2.9);

            Bot.gyroCorrection(0.2, 0);

            idle();
            requestOpModeStop();

        }

    }

}
