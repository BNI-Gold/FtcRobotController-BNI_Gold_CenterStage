package org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Controls.Autonomous.Tank.BlueShippingHubPark;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Robots.TankBot;
import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.mechanisms.TSELocation;

@Autonomous (name = "(DO NOT RUN) Blue: Shipping Hub: Warehouse Park", group = "BLUE")
public class AutoBlueShippingHubPark extends BlueShippingHubPark{
    public TankBot Bot =  new TankBot();

    public long sleepTime = 250;

    @Override
    public void runOpMode() throws InterruptedException {
        Bot.initRobot(hardwareMap);
//        Bot.initWebcam();
        Bot.setLinearOp(this);

        telemetry.addLine("WAITING FOR START >");
        telemetry.update();

//        Bot.detectBarcode();

        TSELocation location = null;

        waitForStart();

        while (opModeIsActive()) {

//            location = locator(Bot);




//            switch (location){
//                case TSELocation.barcode1:
//
//            break;
//            }

            DriveToShippingHub(Bot, location);

            requestOpModeStop();
        }
        idle();
    }
}
