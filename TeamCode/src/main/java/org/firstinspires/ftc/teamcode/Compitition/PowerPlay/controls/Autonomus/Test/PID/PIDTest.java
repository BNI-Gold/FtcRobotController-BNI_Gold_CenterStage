package org.firstinspires.ftc.teamcode.Compitition.PowerPlay.controls.Autonomus.Test.PID;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Compitition.PowerPlay.Robots.CompetionBot;
import org.firstinspires.ftc.teamcode.Outreach.OutreachRanger.Robots.OutreachRangerBot;

@Autonomous (name = "lets win this tournament! i love programming!!!", group = "1")

public class PIDTest extends BlueCarousel {


    public CompetionBot Bot = new CompetionBot();

    public long sleepTime = 10000;

    @Override
    public void runOpMode() throws InterruptedException {
        Bot.initRobot(hardwareMap);
        Bot.setLinearOp(this);



        waitForStart();

        while (opModeIsActive()) {

//            Bot.driveForward_PID(.5,9);
//            sleep(sleepTime);
//
////            Bot.strafeRight_PID(0.5,0.5);
////            sleep(sleepTime);
////
////            Bot.strafeLeft_PID(0.5,0.5);
////            sleep(sleepTime);
//
//            Bot.driveBackward_PID(0.5, 9);
//            sleep(sleepTime);
//
//            idle();
//            requestOpModeStop();

        }
        idle();
    }
}
