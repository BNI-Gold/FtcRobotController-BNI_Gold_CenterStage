package org.firstinspires.ftc.teamcode.Compitition.ZFreightFrenzy.Controls.Autonomous.EightWD.RedShippingHub;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Compitition.ZFreightFrenzy.Robots.EightWheelBot;

@Autonomous(name = "Red Carousel Shipping Hub 8WD", group = "Programming Bot")
@Disabled

public class AutoRedShippingHubTop extends RedShippingHub {


    public EightWheelBot Bot = new EightWheelBot();

    public long sleepTime = 250;

    @Override
    public void runOpMode() throws InterruptedException {
        Bot.initRobot(hardwareMap);
//        Bot.initCamera();
        Bot.setLinearOp(this);



        waitForStart();

        while (opModeIsActive()) {
            Bot.boxHolder2.setPosition(Bot.boxHolder2up);

            red_carousel_spinDuck(Bot);
            sleep(sleepTime);

            CarouselToSHubRed(Bot);
            sleep(sleepTime);

            Shipping_Hub_Score(Bot);
            sleep(sleepTime);

            SHubToWarehouseRed(Bot);

//            Bot.driveForward(0.3, 3);
//            sleep(sleepTime);
//
            requestOpModeStop();

            idle();

            requestOpModeStop();

        }

    }
}
