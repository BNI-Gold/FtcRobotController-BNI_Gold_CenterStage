package org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Controls.Autonomous.BlueRight;

//import org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Controls.Autonomous.StartPosition;
//import org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Controls.Autonomous.TargetZone;
//import org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Robots.CompetitionBot;
////import org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Robots.LabBot;
//
//public class AutoBlueRight extends BlueRight {
//
//
//    public CompetitionBot Bot = new CompetitionBot();
//    public StartPosition startPosition = null;
//    public TargetZone targetZone = null;
//    public long sleepTime = 100;
//
//
//
//    @Override
//    public void runOpMode() throws InterruptedException {
//
//
//
//        Bot.initRobot(hardwareMap);
//        Bot.setLinearOp(this);
//
//
//        startPosition = StartPosition.BlueRight;
//
//
//        waitForStart();
//
//
//        while (opModeIsActive()){
//
//
//            targetZone = detectStarterStack(Bot);
//            sleep(sleepTime);
//
//            driveToTargetZone (Bot, targetZone);
//            sleep(sleepTime);
//
//            ScoreWobble(Bot);
//            sleep(sleepTime);
//
//            ParkLaunchLine(Bot,targetZone);
//            sleep(sleepTime);
//
//
//            requestOpModeStop();
//
//        }
//        idle();
//    }
//}
