package org.firstinspires.ftc.teamcode.Compitition.ZFreightFrenzy.Controls.AutonomousFFCompititionBot.BlueDuck;

import org.firstinspires.ftc.teamcode.Compitition.ZFreightFrenzy.Robots.EightWheelBot;
import org.firstinspires.ftc.teamcode.Compitition.ZCompititionUltimateGoal.Controls.Autonomous.StartPosition;
import org.firstinspires.ftc.teamcode.Compitition.ZCompititionUltimateGoal.Controls.Autonomous.TargetZone;

public class AutoBlueRight extends BlueRight {


    public EightWheelBot Bot = new EightWheelBot();
    public StartPosition startPosition = null;
    public TargetZone targetZone = null;
    public int sleepTime = 250;


    @Override
    public void runOpMode() throws InterruptedException {


        requestOpModeStop();
    }


}