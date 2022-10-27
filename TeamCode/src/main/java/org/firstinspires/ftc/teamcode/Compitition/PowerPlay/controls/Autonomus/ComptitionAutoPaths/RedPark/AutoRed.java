package org.firstinspires.ftc.teamcode.Compitition.PowerPlay.controls.Autonomus.ComptitionAutoPaths.RedPark;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Compitition.PowerPlay.Robots.CompetionBot;
import org.firstinspires.ftc.teamcode.Compitition.PowerPlay.controls.Autonomus.ComptitionAutoPaths.AutoTargetZone;
@Disabled
@Autonomous (name = "AutoRed")
public class AutoRed extends RedPark {

    public CompetionBot Bot = new CompetionBot();

    public AutoTargetZone targetZone = null;

    @Override
    public void runOpMode() throws InterruptedException {
        Bot.initRobot(hardwareMap);

        Bot.setLinearOp(this);

        targetZone = AutoTargetZone.C;

        telemetry.addLine("WAITING FOR START >");
        telemetry.addData("TARGET ZONE: ", targetZone);
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            //targetZone = AutoTargetZone.A;

            telemetry.addData("TARGET ZONE: ", targetZone);
            telemetry.update();

            sleep(1000);

            parkplace(Bot, targetZone);
            sleep(1000);





            idle();
            requestOpModeStop();
        }
    }
}
