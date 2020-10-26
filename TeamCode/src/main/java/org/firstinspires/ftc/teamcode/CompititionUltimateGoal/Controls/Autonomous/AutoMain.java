package org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Controls.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Modules.EasyOpenCVWebcam;
import org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Robots.CompetitionBot;
import org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Robots.LabBot;

public abstract class AutoMain extends LinearOpMode {

    public TargetZone zone = null;

//    This will later detect our 0, 1, or 4 ring stacks!
    public TargetZone detectStarterStack (CompetitionBot Bot) {

//         Following 2 lines are for Hard Coding the Target Zone.  Uncomment to not use EOCV.  AND comment out the lines below them.
//                zone = TargetZone.C;
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
            sleep(500);
            Bot.WobbleClosed();
            sleep(500);
            Bot.WobbleRaised();
            sleep(500);
            Bot.WobbleLower();
            sleep(500);
            Bot.WobbleOpen();
            sleep(500);
            Bot.WobbleRaised();
            sleep(500);
        }

    }
