package org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Controls.Autonomous;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.LED;

import org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Modules.EasyOpenCVWebcam;
import org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Robots.CompetitionBot;
import org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Robots.LabBot;

public abstract class AutoMain extends LinearOpMode {

    public int sleepTimeDrive = 300;

    public TargetZone zone = null;

//    This will later detect our 0, 1, or 4 ring stacks!
    public TargetZone detectStarterStack (CompetitionBot Bot) {

//         Following 2 lines are for Hard Coding the Target Zone.  Uncomment to not use EOCV.  AND comment out the lines below them.
                zone = TargetZone.A;
                return zone;
//    Line below are to use with EOCV
        /*
        Bot.pipeline.getAnalysis();
        if (Bot.pipeline.position == EasyOpenCVWebcam.SkystoneDeterminationPipeline.RingPosition.NONE) {
            return TargetZone.A;
        }
        else if (Bot.pipeline.position == EasyOpenCVWebcam.SkystoneDeterminationPipeline.RingPosition.ONE) {
            return TargetZone.B;
        }
        else {
            return TargetZone.C;
        }

         */
    }

    public TargetZone detectStarterStack (LabBot Bot) {

//         Following 2 lines are for Hard Coding the Target Zone.  Uncomment to not use EOCV.  AND comment out the lines below them.
        zone = TargetZone.A;
        return zone;
//    Line below are to use with EOCV
        /*
        Bot.pipeline.getAnalysis();
        if (Bot.pipeline.position == EasyOpenCVWebcam.SkystoneDeterminationPipeline.RingPosition.NONE) {
            return TargetZone.A;
        }
        else if (Bot.pipeline.position == EasyOpenCVWebcam.SkystoneDeterminationPipeline.RingPosition.ONE) {
            return TargetZone.B;
        }
        else {
            return TargetZone.C;
        }

         */
    }


//    Lower servo to score it, and then raise it to not damage anything.
    public void ScoreWobble (CompetitionBot Bot){
        Bot.WobbleLower();
        sleep(500);
        Bot.WobbleOpen();
        sleep(1000);
        Bot.WobbleRaised();
        sleep(sleepTimeDrive);
        Bot.WobbleClosed();
        sleep(sleepTimeDrive);
    }



    public void CollectWobble (CompetitionBot Bot){
        Bot.WobbleLower();
        sleep(sleepTimeDrive);
        Bot.WobbleOpen();
        sleep(sleepTimeDrive);
        Bot.strafeRight(.3,.7);
        sleep(sleepTimeDrive);
        Bot.gyroCorrection(.2,0);
        sleep(sleepTimeDrive);
        Bot.strafeLeft(.3,.04);
        sleep(sleepTimeDrive);
        Bot.WobbleClosed();
        sleep(sleepTimeDrive);
        Bot.WobbleRaised();
        sleep(sleepTimeDrive);
    }



        public void ScoreLaunch (CompetitionBot Bot){

        Bot.LauncherOn(1);
        sleep(sleepTimeDrive);
        Bot.IntakeOn(1);

    }

        public void StopLaunch (CompetitionBot Bot){
        Bot.LauncherOff(0);
        Bot.IntakeOff(0);
    }

        public void LEDs (LabBot Bot, TargetZone target) {
        if (TargetZone.A == target){
            Bot.blinkinLedDriver.setPattern(RevBlinkinLedDriver.BlinkinPattern.BLUE);
        }
        else if (TargetZone.B == target){
            Bot.blinkinLedDriver.setPattern(RevBlinkinLedDriver.BlinkinPattern.WHITE);
        }
        else {
            Bot.blinkinLedDriver.setPattern(RevBlinkinLedDriver.BlinkinPattern.RED);
        }
    }

    public void LEDs (CompetitionBot Bot, TargetZone target) {
        if (TargetZone.A == target){
            Bot.blinkinLedDriver.setPattern(RevBlinkinLedDriver.BlinkinPattern.BLUE);
        }
        else if (TargetZone.B == target){
            Bot.blinkinLedDriver.setPattern(RevBlinkinLedDriver.BlinkinPattern.WHITE);
        }
        else {
            Bot.blinkinLedDriver.setPattern(RevBlinkinLedDriver.BlinkinPattern.RED);
        }
    }
}
