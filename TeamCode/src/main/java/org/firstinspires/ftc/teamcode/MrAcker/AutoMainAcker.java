package org.firstinspires.ftc.teamcode.MrAcker;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Controls.Autonomous.TargetZone;
import org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Modules.EasyOpenCVWebcam;
import org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Robots.LabBot;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraRotation;

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
