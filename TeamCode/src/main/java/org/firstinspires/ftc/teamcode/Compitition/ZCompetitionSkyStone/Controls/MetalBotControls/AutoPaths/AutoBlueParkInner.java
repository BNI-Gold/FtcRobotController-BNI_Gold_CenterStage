//package org.firstinspires.ftc.teamcode.Compitition.ZCompetitionSkyStone.Controls.MetalBotControls.AutoPaths;
//
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.Disabled;
//
//import org.firstinspires.ftc.teamcode.Compitition.ZCompetitionSkyStone.Controls.MetalBotControls.AutoBuilding;
//import org.firstinspires.ftc.teamcode.Compitition.ZCompetitionSkyStone.robots.MetalBot;
//
//@Autonomous(name = "Blue: Park: Inner")
//@Disabled
//public class AutoBlueParkInner extends AutoBuilding {
//
//    public MetalBot Bot = new MetalBot();
//
//    @Override
//    public void runOpMode() throws InterruptedException {
//
//        Bot.initRobot(hardwareMap, "Build");
//        Bot.setLinearOp(this);
//        //Bot.HookRelease();
//
//        setLinearOp(this);
//
//
//
//        waitForStart();
//
//        while (opModeIsActive()) {
//
//
//            Bot.strafeLeft(midSpeed, 2);
//            Bot.driveForward(midSpeed, 1);
//
//            idle();
//            requestOpModeStop();
//        }
//        idle();
//
//    }
//}
