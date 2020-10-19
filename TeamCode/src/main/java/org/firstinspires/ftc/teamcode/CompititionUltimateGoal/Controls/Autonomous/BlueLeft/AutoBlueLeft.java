package org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Controls.Autonomous.BlueLeft;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Controls.Autonomous.StartPosition;
import org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Controls.Autonomous.TargetZone;
import org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Modules.EasyOpenCVWebcam;
import org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Robots.LabBot;

@Autonomous (name = "Remote:Blue:Left!", group = "BLUE")

public class AutoBlueLeft extends BlueLeft {

    // Initiailize our variables.
    public LabBot Bot = new LabBot();
    public StartPosition startPosition = null;
    public TargetZone targetZone = null;
    public long sleepTime = 100;



    @Override
    public void runOpMode() throws InterruptedException {
//        Constructor to set up our hardware mapping.


        Bot.initRobot(hardwareMap);
        Bot.initCamera();
        Bot.setLinearOp(this);

//        This is hard-coded for this auto.  May or may not use, but here just in case.
        startPosition = StartPosition.BlueLeft;

//        setLinearOp (this);
        waitForStart();

        while (opModeIsActive()) {
//            Change value in detectStarterStack to test different Auto paths.
//            select the function call below and use "Cmd + B" to go direcrtly to that function.
            targetZone = detectStarterStack(Bot);
            telemetry.addData("SAMPLING VALUE #: ", Bot.pipeline.avg1);
            telemetry.addData("NUMBER OF RINGS: ", Bot.pipeline.position);
            telemetry.addData("TARGET ZONE: ", targetZone);

            telemetry.update();

            //STOP THE CAMERA!
            Bot.webcam.closeCameraDevice();
//OR
//            Bot.webcam.pauseViewport();

            sleep(1000);
//            Bot.pipeline.getAnalysis();
//            if (Bot.pipeline.position == EasyOpenCVWebcam.SkystoneDeterminationPipeline.RingPosition.NONE) {
//                targetZone = TargetZone.A;
//            }
//            else if (Bot.pipeline.position == EasyOpenCVWebcam.SkystoneDeterminationPipeline.RingPosition.ONE) {
//                targetZone = targetZone.B;
//            }
//            else {
//                targetZone = TargetZone.C;
//            }
//            Drive to the Target Zone; depends on the targetZone!

            driveToTargetZone (Bot, targetZone);

            sleep(sleepTime);
//            Lower and raise the Servo to score the Wobble.
            ScoreWobble(Bot);
            sleep(sleepTime);
//            Park robot on the launch line.
            ParkLaunchLine(Bot, targetZone);
            sleep(sleepTime);

//            Required to stop Autonomous!
            requestOpModeStop();
        }
        idle();
    }
}
