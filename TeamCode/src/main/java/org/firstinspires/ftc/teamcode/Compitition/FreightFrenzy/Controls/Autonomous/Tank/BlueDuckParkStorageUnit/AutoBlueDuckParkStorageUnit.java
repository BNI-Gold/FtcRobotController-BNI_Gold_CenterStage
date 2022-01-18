package org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Controls.Autonomous.Tank.BlueDuckParkStorageUnit;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Robots.TankBot;
@Autonomous (name = "Tank: Blue Duck-Depot", group = "BLUE")
public class AutoBlueDuckParkStorageUnit extends BlueDuckParkStorageUnit {


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
//            TestAuto(Bot);
            sleep(sleepTime);
            spinDuckBlue(Bot);
            sleep(sleepTime);
            DuckSpinnerToStorageUnit (Bot);
            sleep(sleepTime);
            idle();
            requestOpModeStop();
        }
        idle();
    }
}
