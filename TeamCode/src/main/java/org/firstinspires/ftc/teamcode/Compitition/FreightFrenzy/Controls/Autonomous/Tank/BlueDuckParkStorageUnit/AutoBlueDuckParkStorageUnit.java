package org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Controls.Autonomous.Tank.BlueDuckParkStorageUnit;

import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Robots.TankBot;

public class AutoBlueDuckParkStorageUnit extends BlueDuckParkStorageUnit {

    public TankBot Bot =  new TankBot();

    public long sleepTime = 250;

    @Override
    public void runOpMode() throws InterruptedException {
        Bot.initRobot(hardwareMap);
        Bot.setLinearOp(this);

        telemetry.addLine("WAITING FOR START >");
        telemetry.update();

        while (opModeIsActive()) {
            StartToDuckSpinner(Bot);
            sleep(sleepTime);


            idle();
            requestOpModeStop();
        }
        idle();
    }
}
