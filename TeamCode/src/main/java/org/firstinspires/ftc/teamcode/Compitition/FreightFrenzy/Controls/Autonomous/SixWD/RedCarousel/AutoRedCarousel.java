package org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Controls.Autonomous.SixWD.RedCarousel;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Controls.Autonomous.SixWD.BlueCarousel.BlueCarousel;
import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Robots.SixWheelBot;

@Autonomous(name = "Red Carousel", group = "Programming Bot")

public class AutoRedCarousel extends RedCarousel {


    public SixWheelBot Bot = new SixWheelBot();

    public long sleepTime = 250;

    @Override
    public void runOpMode() throws InterruptedException {
        Bot.initRobot(hardwareMap);
//        Bot.initCamera();
        Bot.setLinearOp(this);



        waitForStart();

        while (opModeIsActive()) {

           red_carousel_spinDuck(Bot);

            Bot.driveForward(.5,5   );
            sleep(sleepTime);

            Bot.rotateRight(.5, 0.22);
            sleep(sleepTime);

            Bot.driveForward(1,7.5);
            sleep(sleepTime);

            idle();
            requestOpModeStop();

        }
        idle();
    }
}
