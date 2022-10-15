package org.firstinspires.ftc.teamcode.Compitition.PowerPlay.controls.Autonomus.ComptitionAutoPaths.BlueLeft;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Compitition.PowerPlay.Robots.CompetionBot;
import org.firstinspires.ftc.teamcode.Compitition.PowerPlay.controls.Autonomus.ComptitionAutoPaths.AutoTargetZone;

@Autonomous (name = "AutoBlueLeft")
public class AutoBlueLeft extends BlueLeftPark{

    public CompetionBot Bot = new CompetionBot();

    public AutoTargetZone targetZone = null;

    @Override
    public void runOpMode() throws InterruptedException {
        Bot.initRobot(hardwareMap);

        Bot.setLinearOp(this);

        targetZone = DetectSleaveImage(Bot);

        telemetry.addLine("WAITING FOR START >");
        telemetry.addData("TARGET ZONE: ", targetZone);
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            targetZone = DetectSleaveImage(Bot);

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
