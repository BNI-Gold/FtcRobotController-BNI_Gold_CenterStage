package org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Controls.Autonomous.Tank.RedShippingHubParkWarehouse;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Robots.TankBot;
import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.mechanisms.TSELocation;

@Autonomous (name = "Tank: Red: Shipping Hub: Warehouse Park", group = "RED")
public class AutoRedShippingHubPark extends RedShippingHubPark {
    public TankBot Bot =  new TankBot();

    public long sleepTime = 250;

    public String Alliance;
    public String AutoPath;

    @Override
    public void runOpMode() throws InterruptedException {
        Bot.initRobot(hardwareMap);

        Bot.setLinearOp(this);

//        telemetry.addLine("WAITING FOR START >");
//        telemetry.update();

        Alliance = "Blue";
        AutoPath = "CameraShippingHubWarehouse";
//        AutoPath = "DuckToStorage";

//        Bot.initRobot(hardwareMap);
//        Bot.initWebcam();
//        Bot.setLinearOp(this);

//        telemetry.addLine("WAITING FOR START >");
//        telemetry.addLine("All my telemetry will be on FTC Dashboard");
//        telemetry.addLine("http://192.168.43.1:8080/dash");
//        telemetry.update();

        //Bot.detectBarcode();

        TSELocation location = null;





//        Bot.detectBarcode();
//
//        TSELocation location = null;

        //        FtcDashboard dashboard = FtcDashboard.getInstance();
//        telemetry = dashboard.getTelemetry();
//        FtcDashboard.getInstance().startCameraStream(Bot.webcam, 10);
        telemetry.addLine("WAITING FOR START >");
        telemetry.update();


        waitForStart();

        while (opModeIsActive()) {
            Bot.initWebcam();
            sleep(2000);

            switch (AutoPath) {
                case "CameraShippingHubWarehouse":
                    location = Bot.detectBarcode();  // uses webcam -- only midpoint telemetry shows
//            location = locator(Bot);            // does not use webcam - new telemetry shows
//            Bot.detectBarcode();

                    DriveShippingHubScore(Bot, Alliance, location);  // use to test if robot functioning!
                    sleep(sleepTime);
                    ShippingHubToWarehosue (Bot, Alliance, location);
                    sleep(sleepTime);
                    break;
                case "DuckToStorage":

            }

            if (AutoPath.equals("CameraShippingHubWarehouse")) {

            }


            if (AutoPath.equals("DuckToStorage")) {

            }

            sleep(1000);
            //            location = locator(Bot);




//            switch (location){
//                case TSELocation.barcode1:
//
//            break;
//            }

//            DriveToShippingHub(Bot, location);

            requestOpModeStop();
        }
        idle();
    }
}
