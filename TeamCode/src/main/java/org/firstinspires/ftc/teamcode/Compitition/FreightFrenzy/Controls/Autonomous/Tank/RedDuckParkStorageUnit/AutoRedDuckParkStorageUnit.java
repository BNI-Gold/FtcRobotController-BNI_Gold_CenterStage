package org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Controls.Autonomous.Tank.RedDuckParkStorageUnit;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Robots.TankBot;
import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.mechanisms.TSELocation;

@Autonomous (name = "Tank: Red: Duck: Depot", group = "RED")

public class AutoRedDuckParkStorageUnit extends RedDuckParkStorageUnit {


    public TankBot Bot =  new TankBot();

    public long sleepTime = 250;

    public String Alliance;
    public String AutoPath;


    @Override
    public void runOpMode() throws InterruptedException {
        Bot.initRobot(hardwareMap);
        Bot.setLinearOp(this);

        telemetry.addLine("WAITING FOR START >");
        telemetry.update();

        Alliance = "Blue";
//        AutoPath = "CameraShippingHubWarehouse";
        AutoPath = "DuckToStorage";

        Bot.initRobot(hardwareMap);
//        Bot.initWebcam();
        Bot.setLinearOp(this);

        telemetry.addLine("WAITING FOR START >");
        telemetry.addLine("All my telemetry will be on FTC Dashboard");
        telemetry.addLine("http://192.168.43.1:8080/dash");
        telemetry.update();

//        Bot.detectBarcode();

        TSELocation location = null;


        FtcDashboard dashboard = FtcDashboard.getInstance();
        telemetry = dashboard.getTelemetry();
        FtcDashboard.getInstance().startCameraStream(Bot.webcam, 10);
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
                    spinDuckRed(Bot);
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
//            TestAuto(Bot);r
            sleep(sleepTime);
            spinDuckRed(Bot);
            sleep(sleepTime);
            DuckSpinnerToStorageUnit (Bot);
            sleep(sleepTime);
            idle();
            requestOpModeStop();

            */
           requestOpModeStop();
        }
        idle();
    }
}
