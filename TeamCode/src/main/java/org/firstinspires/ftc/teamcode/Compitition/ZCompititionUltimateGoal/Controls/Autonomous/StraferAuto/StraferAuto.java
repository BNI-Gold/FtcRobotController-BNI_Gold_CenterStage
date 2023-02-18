package org.firstinspires.ftc.teamcode.Compitition.ZCompititionUltimateGoal.Controls.Autonomous.StraferAuto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Compitition.ZCompititionUltimateGoal.Robots.StraferKit;
@Autonomous (name = "Strafer Auto")
public class StraferAuto extends LinearOpMode {

    int sleepTime = 250;

    StraferKit Bot = new StraferKit();

    @Override
    public void runOpMode() throws InterruptedException {

        Bot.initRobot(hardwareMap);
        Bot.setLinearOp(this);

        waitForStart();

        while (opModeIsActive()) {

            Bot.driveForwardPID(1);
            sleep(sleepTime);

            requestOpModeStop();

        }

        idle();

    }

}
