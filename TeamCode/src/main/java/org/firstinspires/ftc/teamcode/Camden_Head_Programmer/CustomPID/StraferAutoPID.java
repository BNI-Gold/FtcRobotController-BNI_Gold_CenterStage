package org.firstinspires.ftc.teamcode.Camden_Head_Programmer.CustomPID;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Compitition.ZCompititionUltimateGoal.Robots.StraferKit;

@Autonomous (name = "Custom PID Test - Strafer Kit")
public class StraferAutoPID extends LinearOpMode {

    int sleepTime = 250;

    StraferKit Bot = new StraferKit();

    @Override
    public void runOpMode() throws InterruptedException {

        Bot.initRobot(hardwareMap);
        Bot.setLinearOp(this);

        waitForStart();

        while (opModeIsActive()) {

            Bot.driveForwardPID(2);
            sleep(sleepTime);

            Bot.driveBackwardPID(2);
            sleep(sleepTime);

            requestOpModeStop();

        }

        idle();

    }

}
