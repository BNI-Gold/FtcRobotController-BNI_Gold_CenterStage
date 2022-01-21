package org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Controls.Autonomous.Tank.TestLab;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Robots.TankBot;
import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.mechanisms.TSELocation;
@Autonomous (name = "Lab Testing", group = "LAB")
public class AutoTestLab extends TestLab {
    public TankBot Bot =  new TankBot();

    public long sleepTime = 250;

    public String Alliance = "Blue";

    @Override
    public void runOpMode() throws InterruptedException {
        Bot.initRobot(hardwareMap);
        Bot.initWebcam();
        Bot.setLinearOp(this);

        telemetry.addLine("WAITING FOR START >");
        telemetry.addLine("All my telemetry will be on FTC Dashboard");
        telemetry.addLine("http://192.168.43.1:8080/dash");
        telemetry.update();

        Bot.detectBarcode();

        TSELocation location = null;


        FtcDashboard dashboard = FtcDashboard.getInstance();
        telemetry = dashboard.getTelemetry();
        FtcDashboard.getInstance().startCameraStream(Bot.webcam, 10);
        telemetry.addLine("WAITING FOR START >");
        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {
            location = Bot.detectBarcode();  // uses webcam -- only midpoint telemetry shows
//            location = locator(Bot);            // does not use webcam - new telemetry shows
//            Bot.detectBarcode();

            driveTest(Bot, Alliance, location);  // use to test if robot functioning!
            sleep(sleepTime);



            sleep(5000);


            /*


            DriveToDuckSpinner(Bot, Alliance);
            sleep(sleepTime);
            spinDuckBlue(Bot);
            sleep(sleepTime);
            DuckSpinnerToStorageUnit (Bot, Alliance);
            requestOpModeStop();

             */
            requestOpModeStop();
        }
        idle();
    }
}
