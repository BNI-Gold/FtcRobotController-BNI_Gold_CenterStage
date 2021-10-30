package org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Controls.Autonomous.SixWD.RedCarousel;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Robots.SixWheelBot;

@Autonomous(name = "Red Carousel 2", group = "Programming Bot")

public class AutoRedCarousel2 extends RedCarousel {


    public SixWheelBot Bot = new SixWheelBot();

    public long sleepTime = 250;

    @Override
    public void runOpMode() throws InterruptedException {
        Bot.initRobot(hardwareMap);
//        Bot.initCamera();
        Bot.setLinearOp(this);



        waitForStart();

        while (opModeIsActive()) {

            Bot.driveForward(.5,1.5);
            sleep(sleepTime);

//            Bot.rotateRight(.5,1.15);
//            sleep(sleepTime);

            Bot.rotateRight(.5,1.1);
            sleep(sleepTime);

            Bot.driveBackward(.5,2.55);
            sleep(sleepTime);

            Bot.SpinDuckleftRight();
            sleep(5000);

            Bot.StopSpinningDuckRight();
            sleep(sleepTime);

            Bot.driveForward(.5,4);
            sleep(sleepTime);

            Bot.rotateRight(.5, 0.22);
            sleep(sleepTime);

            idle();
            requestOpModeStop();

        }
        idle();
    }
}
