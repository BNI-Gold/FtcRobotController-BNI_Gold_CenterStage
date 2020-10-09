package org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Controls.Autonomous.Remote.Paths;

import org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Controls.Autonomous.AutoMain;
import org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Controls.Autonomous.TargetZone;
import org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Robots.LabBot;

public class AutoBlueLeft extends DriveToTargetZone {

    public LabBot Bot = new LabBot();

    @Override
    public void runOpMode() throws InterruptedException {
        Bot.initRobot(hardwareMap);
        Bot.setLinearOp(this);
        TargetZone target = null;
//        setLinearOp (this);
        waitForStart();

        while (opModeIsActive()) {

            target = detectStarterStack();

//            driveToSquare(targetarget);

        }
    }
}
