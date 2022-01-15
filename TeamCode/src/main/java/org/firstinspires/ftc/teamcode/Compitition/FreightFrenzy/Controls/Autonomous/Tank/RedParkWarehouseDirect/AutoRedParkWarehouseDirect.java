package org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Controls.Autonomous.Tank.RedParkWarehouseDirect;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Controls.Autonomous.Tank.AutoMain;
import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Robots.TankBot;

@Autonomous (name = "Red: Warehouse Park", group = "RED")
public class AutoRedParkWarehouseDirect extends AutoMain {
    public TankBot Bot =  new TankBot();

    public long sleepTime = 250;

    @Override
    public void runOpMode() throws InterruptedException {
        Bot.initRobot(hardwareMap);
        Bot.initWebcam();
        Bot.setLinearOp(this);

        telemetry.addLine("WAITING FOR START >");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            Bot.driveForward(1, 1);
            sleep(sleepTime);
            Bot.rotateRight(1, 1.5);
            sleep(sleepTime);
            Bot.driveForward(1, 5);
            sleep(sleepTime);

            requestOpModeStop();
        }
        idle();
    }
}
