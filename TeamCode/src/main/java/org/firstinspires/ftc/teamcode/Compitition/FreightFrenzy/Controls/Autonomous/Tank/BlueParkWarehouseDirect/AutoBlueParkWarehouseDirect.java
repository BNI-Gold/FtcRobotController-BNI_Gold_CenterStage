package org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Controls.Autonomous.Tank.BlueParkWarehouseDirect;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Controls.Autonomous.Tank.AutoMain;
import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Robots.TankBot;
import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.mechanisms.TSELocation;

@Autonomous (name = "Blue: Warehouse Park", group = "BLUE")
public class AutoBlueParkWarehouseDirect extends AutoMain {
    public TankBot Bot =  new TankBot();

    public long sleepTime = 250;

    @Override
    public void runOpMode() throws InterruptedException {
        Bot.initRobot(hardwareMap);
//        Bot.initWebcam();
        Bot.setLinearOp(this);

        telemetry.addLine("WAITING FOR START >");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            Bot.driveForward(1, 4);
            sleep(sleepTime);
            Bot.rotateRight(1, 1.5);
            sleep(sleepTime);
            Bot.driveBackward(1, 8.5);
            sleep(sleepTime);

            requestOpModeStop();
        }
        idle();
    }
}
