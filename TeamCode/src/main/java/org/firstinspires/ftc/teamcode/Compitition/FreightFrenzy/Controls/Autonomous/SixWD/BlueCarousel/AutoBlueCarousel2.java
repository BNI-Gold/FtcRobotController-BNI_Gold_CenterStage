package org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Controls.Autonomous.SixWD.BlueCarousel;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Robots.SixWheelBot;

@Autonomous(name = "Blue Carousel 2", group = "Programming Bot")

public class AutoBlueCarousel2 extends BlueCarousel {


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

            Bot.rotateLeft(.5,1.1);
            sleep(sleepTime);

            Bot.driveBackward(.5,2.55);
            sleep(sleepTime);

            Bot.SpinDuckleftleft();
            sleep(5000);

            Bot.StopSpinningDuckleft();
            sleep(sleepTime);

            Bot.driveForward(.5,4);
            sleep(sleepTime);

            Bot.rotateLeft(.5, 0.22);
            sleep(sleepTime);

            idle();
            requestOpModeStop();

        }
        idle();
    }
}
