package org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Controls.Autonomous;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.LED;

import org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Modules.EasyOpenCVWebcam;
import org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Robots.CompetitionBot;
import org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Robots.LabBot;

public abstract class AutoMain extends LinearOpMode {

    public int sleepTimeDrive = 250;

    public TargetZone zone = null;

//    This will later detect our 0, 1, or 4 ring stacks!
    public TargetZone detectStarterStack (CompetitionBot Bot) {

//         Following 2 lines are for Hard Coding the Target Zone.  Uncomment to not use EOCV.  AND comment out the lines below them.
//                zone = TargetZone.A;
//                return zone;
//    Line below are to use with EOCV
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
    }

//    Lower servo to score it, and then raise it to not damage anything.
    public void ScoreWobble (CompetitionBot Bot){
        Bot.WobbleLower();
        sleep(1000);
        Bot.WobbleOpen();
        sleep(1000);
        Bot.WobbleRaised();
        sleep(1000);
        Bot.WobbleClosed();
        sleep(1000);
    }



    public void CollectWobble (CompetitionBot Bot){
        Bot.WobbleLower();
        sleep(1000);
        Bot.WobbleOpen();
        sleep(1000);
        Bot.strafeLeft(.2,.5);
        sleep(1000);
        Bot.gyroCorrection(.2,0);
        sleep(1000);
        Bot.WobbleClosed();
        sleep(1000);
        Bot.WobbleRaised();
        sleep(1000);
    }



        public void ScoreLaunch (CompetitionBot Bot){

        Bot.LauncherOn(1);
        sleep(300);
        Bot.IntakeOn(1);

    }

        public void StopLaunch (CompetitionBot Bot){
        Bot.LauncherOff(0);
        Bot.IntakeOff(0);
    }

        public void LEDs (CompetitionBot Bot, TargetZone target) {
        if (TargetZone.A == target){
            Bot.blinkinLedDriver.setPattern(RevBlinkinLedDriver.BlinkinPattern.BLUE);
        }
        else if (TargetZone.B == target){
            Bot.blinkinLedDriver.setPattern(RevBlinkinLedDriver.BlinkinPattern.WHITE);
        }
        else {
            Bot.blinkinLedDriver.setPattern(RevBlinkinLedDriver.BlinkinPattern.CONFETTI);
        }
    }
}
