package org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Controls.Autonomous.SixWD.BlueCarousel;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.DriveTrains.SixWD;
import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Robots.SixWheelBot;

@Autonomous (name = "Blue Carousel", group = "Programming Bot")

public class AutoBlueCarousel extends BlueCarousel {


    public SixWheelBot Bot = new SixWheelBot();

    public long sleepTime = 250;

    @Override
    public void runOpMode() throws InterruptedException {
        Bot.initRobot(hardwareMap);
//        Bot.initCamera();
        Bot.setLinearOp(this);



        waitForStart();

        while (opModeIsActive()) {

            blue_carousel_spinDuck(Bot);

            Bot.driveForward(.5,4);
            sleep(sleepTime);

            Bot.rotateLeft(.5, 0.22);
            sleep(sleepTime);

            Bot.driveForward(1,7.5);
            sleep(sleepTime);

            idle();
            requestOpModeStop();

        }
        idle();
    }
}
