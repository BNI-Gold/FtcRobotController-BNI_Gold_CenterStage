package org.firstinspires.ftc.teamcode.Compitition.ZFreightFrenzy.Controls.Autonomous.EightWD.BlueCarousel;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Compitition.ZFreightFrenzy.Robots.EightWheelBot;

@Autonomous (name = "Blue Carousel 8WD", group = "Programming Bot")
@Disabled

public class AutoBlueCarousel extends BlueCarousel {


    public EightWheelBot Bot = new EightWheelBot();

    public long sleepTime = 250;

    @Override
    public void runOpMode() throws InterruptedException {
        Bot.initRobot(hardwareMap);
//        Bot.initCamera();
        Bot.setLinearOp(this);



        waitForStart();

        while (opModeIsActive()) {

            blue_carousel_spinDuck(Bot);

            CarouselToWarehouseBlue(Bot);

            idle();
            requestOpModeStop();

        }
        idle();
    }
}
