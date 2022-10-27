package org.firstinspires.ftc.teamcode.Compitition.PowerPlay.controls.Autonomus.ComptitionAutoPaths.BluePark;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.Compitition.PowerPlay.Robots.CompetionBot;
import org.firstinspires.ftc.teamcode.Compitition.PowerPlay.controls.Autonomus.ComptitionAutoPaths.AutoTargetZone;

@Autonomous (name = "Park")
public class AutoBlue extends BluePark {

    public CompetionBot Bot = new CompetionBot();

    public AutoTargetZone targetZone = null;

    @Override
    public void runOpMode() throws InterruptedException {
        Bot.initRobot(hardwareMap);

        Bot.setLinearOp(this);

        // Initialize WebCam and Create Image Processing Pipeline
        initializePipeline();

        //targetZone = AutoTargetZone.A;

        telemetry.addLine("WAITING FOR START >");
        telemetry.addData("TARGET ZONE: ", TargetZone);
        telemetry.update();

        // Find Tags During the Init Loop
        while (!isStarted() && !isStopRequested()) {
            findTag();
            sleep(20);
        }
        waitForStart();

        while (opModeIsActive()) {

            //targetZone = DetectSleaveImage(Bot);

            telemetry.addData("TARGET ZONE: ", TargetZone);
            telemetry.update();

            sleep(1000);

            parkplace(Bot, TargetZone);
            sleep(1000);





            idle();
            requestOpModeStop();
        }
    }
}
