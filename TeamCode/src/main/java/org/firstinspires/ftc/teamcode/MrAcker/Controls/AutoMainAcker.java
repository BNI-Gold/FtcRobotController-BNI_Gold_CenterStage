package org.firstinspires.ftc.teamcode.MrAcker.Controls;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Compitition.ZCompititionUltimateGoal.Controls.Autonomous.TargetZone;
import org.firstinspires.ftc.teamcode.MrAcker.LabBotAcker;

public abstract class AutoMainAcker extends LinearOpMode {


    public TargetZone zone = null;


//    This will later detect our 0, 1, or 4 ring stacks!
    public TargetZone detectStarterStack (LabBotAcker Bot) {
        zone = TargetZone.C;


        return zone;
    }

//    Lower servo to score it, and then raise it to not damage anything.
    public void ScoreWobble (LabBotAcker Bot) {
        Bot.servoOpened();
        sleep(500);
        Bot.servoClosed();
        sleep(500);
    }

}
