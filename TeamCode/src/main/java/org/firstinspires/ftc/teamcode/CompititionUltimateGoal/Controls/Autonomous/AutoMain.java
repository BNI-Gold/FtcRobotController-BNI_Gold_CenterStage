package org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Controls.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Modules.EasyOpenCVWebcam;
import org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Robots.LabBot;

public abstract class AutoMain extends LinearOpMode {

    public TargetZone zone = null;

//    This will later detect our 0, 1, or 4 ring stacks!
    public TargetZone detectStarterStack (LabBot Bot) {
        zone = TargetZone.C;

        return zone;
    }

//    Lower servo to score it, and then raise it to not damage anything.
    public void ScoreWobble (LabBot Bot) {
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
    }

}
