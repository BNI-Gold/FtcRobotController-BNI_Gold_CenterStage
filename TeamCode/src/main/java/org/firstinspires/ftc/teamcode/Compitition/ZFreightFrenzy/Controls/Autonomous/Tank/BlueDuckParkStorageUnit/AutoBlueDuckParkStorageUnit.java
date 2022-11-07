package org.firstinspires.ftc.teamcode.Compitition.ZFreightFrenzy.Controls.Autonomous.Tank.BlueDuckParkStorageUnit;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Compitition.ZFreightFrenzy.Robots.TankBot;
import org.firstinspires.ftc.teamcode.Compitition.ZFreightFrenzy.mechanisms.TSELocation;
@Disabled
@Autonomous (name = "Tank: Blue: Duck: Depot", group = "BLUE")
public class AutoBlueDuckParkStorageUnit extends BlueDuckParkStorageUnit {

//AutoBlueDuckParkStorageUnit
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
//        AutoPath = "CameraShippingHubWarehouse";
        AutoPath = "DuckToStorage";

//        Bot.initRobot(hardwareMap);
//        Bot.initWebcam();
//        Bot.setLinearOp(this);

//        telemetry.addLine("WAITING FOR START >");
//        telemetry.addLine("All my telemetry will be on FTC Dashboard");
//        telemetry.addLine("http://192.168.43.1:8080/dash");
//        telemetry.update();

//        Bot.detectBarcode();

        TSELocation location = null;


//        FtcDashboard dashboard = FtcDashboard.getInstance();
//        telemetry = dashboard.getTelemetry();
//        FtcDashboard.getInstance().startCameraStream(Bot.webcam, 10);
        telemetry.addLine("WAITING FOR START >");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {


            sleep(1000);

            switch (AutoPath) {
                case "CameraShippingHubWarehouse":

                    break;
                case "DuckToStorage":
                    DriveToDuckSpinner(Bot, Alliance);
                    sleep(sleepTime);
                    spinDuckBlue(Bot);
                    sleep(sleepTime);
                    DuckSpinnerToStorageUnit (Bot, Alliance);
                    break;
            }

            if (AutoPath.equals("CameraShippingHubWarehouse")) {

            }


            if (AutoPath.equals("DuckToStorage")) {

            }

            sleep(1000);


            /*
            StartToDuckSpinner(Bot);
//            TestAuto(Bot);
            sleep(sleepTime);
            spinDuckBlue(Bot);
           sleep(sleepTime);
            DuckSpinnerToStorageUnit (Bot);
            sleep(sleepTime);

             */
            idle();
            requestOpModeStop();
        }
        idle();
    }
}
