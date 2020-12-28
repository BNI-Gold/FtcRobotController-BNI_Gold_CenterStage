package org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Controls.Autonomous.BlueStraferKit;

import org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Controls.Autonomous.StartPosition;
import org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Controls.Autonomous.TargetZone;
import org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Robots.StraferKit;

public class AutoBlueStrafer extends BlueStrafer {
    public StraferKit Bot = new StraferKit ();
    public StartPosition startPosition = null;
    public TargetZone targetZone = null;
    public long sleepTime = 100;

    @Override
    public void runOpMode() throws InterruptedException {
        //        Constructor to set up our hardware mapping.
        Bot.initRobot(hardwareMap, "BlueLeft","auto");
//        Bot.initCamera();
        Bot.setLinearOp(this);
//        This is hard-coded for this auto.  May or may not use, but here just in case.
        sleep(100);
        startPosition = StartPosition.BlueLeft;
        targetZone = detectStarterStack(Bot);
//        telemetry.addData("SAMPLING VALUE #: ", Bot.pipeline.avg1);
//        telemetry.addData("NUMBER OF RINGS: ", Bot.pipeline.position);
        telemetry.addData("TARGET ZONE: ", targetZone);

        telemetry.addLine("WAITING FOR START >");
        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {
            targetZone = detectStarterStack(Bot);
            telemetry.addData("TARGET ZONE: ", targetZone);
            telemetry.update();

            driveToLaunch(Bot);
            sleep(sleepTime);





            requestOpModeStop();
        }
        idle();
    }
}
