package org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Controls.Autonomous.Tank.RedParkWarehouseDirect;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Controls.Autonomous.Tank.AutoMain;
import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Robots.TankBot;

@Autonomous (name = "Red: Warehouse Park", group = "RED")
public class AutoRedParkWarehouseDirect extends AutoMain {
    public TankBot Bot =  new TankBot();

    public long sleepTime = 250;

    private double straightSpd = 0.6;
    private double turnEncoderSpd = 0.5;
    //        Speed .2 == too low for gyro turn
    private double turnGyro1 = 0.25;
    private double turnGyro2 = 0.3;

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
            Bot.rotateLeft(1, 1.5);
            sleep(sleepTime);
            Bot.driveBackward(1, 8.5);
            sleep(sleepTime);

            requestOpModeStop();
        }
        idle();
    }
}
