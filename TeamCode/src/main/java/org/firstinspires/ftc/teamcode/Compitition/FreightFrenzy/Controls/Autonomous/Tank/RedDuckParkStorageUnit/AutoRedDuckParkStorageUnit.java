package org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Controls.Autonomous.Tank.RedDuckParkStorageUnit;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Robots.TankBot;

@Autonomous (name = "Tank: Red Duck-Depot", group = "Red")
public class AutoRedDuckParkStorageUnit extends RedDuckParkStorageUnit {


    public TankBot Bot =  new TankBot();

    public long sleepTime = 250;

    @Override
    public void runOpMode() throws InterruptedException {
        Bot.initRobot(hardwareMap);
        Bot.setLinearOp(this);

        telemetry.addLine("WAITING FOR START >");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            StartToDuckSpinner(Bot);
//            TestAuto(Bot);r
            sleep(sleepTime);
            spinDuckRed(Bot);
            sleep(sleepTime);
            DuckSpinnerToStorageUnit (Bot);
            sleep(sleepTime);
            idle();
            requestOpModeStop();
        }
        idle();
    }
}
