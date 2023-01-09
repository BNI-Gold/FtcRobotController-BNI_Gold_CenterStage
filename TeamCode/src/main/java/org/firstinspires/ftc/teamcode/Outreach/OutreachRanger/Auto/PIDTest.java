package org.firstinspires.ftc.teamcode.Outreach.OutreachRanger.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Compitition.ZFreightFrenzy.Robots.SixWheelBot;
import org.firstinspires.ftc.teamcode.Outreach.OutreachRanger.Robots.OutreachRangerBot;
@Disabled
@Autonomous (name = "PID Test", group = "Programming Bot")

public class PIDTest extends BlueCarousel {


    public OutreachRangerBot Bot = new OutreachRangerBot();

    public long sleepTime = 250;

    @Override
    public void runOpMode() throws InterruptedException {
        Bot.initRobot(hardwareMap);
//        Bot.initCamera();
        Bot.setLinearOp(this);



        waitForStart();

        while (opModeIsActive()) {

            //blue_carousel_spinDuck(Bot);
            Bot.frontRightMotor.setTargetPosition(5000);
            Bot.frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            sleep(sleepTime);

            Bot.driveForward(.5,3);
            Bot.stopMotors();
            sleep(sleepTime);

            Bot.rotateRight(0.3, 0.5);
            sleep(sleepTime);

            Bot.rotateLeft(0.3, 0.5);
            sleep(sleepTime);

            Bot.driveBackward(.5, 3);
            sleep(sleepTime);

            idle();
            requestOpModeStop();

        }
        idle();
    }
}
