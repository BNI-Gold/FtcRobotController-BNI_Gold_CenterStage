//package org.firstinspires.ftc.teamcode.Compitition.ZCompetitionSkyStone.Controls.MetalBotControls.AutoPaths.AutoBuildingPaths;
//
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.Disabled;
//
//import org.firstinspires.ftc.teamcode.Compitition.ZCompetitionSkyStone.Controls.MetalBotControls.AutoBuilding;
//import org.firstinspires.ftc.teamcode.Compitition.ZCompetitionSkyStone.robots.MetalBot;
//
//@Autonomous(name = "Blue:Building Plate:Outer")
//@Disabled
//public class AutoBlueBuildingPlateOuter extends AutoBuilding {
//
//    public MetalBot Bot = new MetalBot();
//
//    @Override
//    public void runOpMode() throws InterruptedException {
//
//        Bot.initRobot(hardwareMap, "Build");
//        Bot.setLinearOp(this);
//        Bot.HookRelease();
//
//        setLinearOp(this);
//
//
//
//        waitForStart();
//
//        while (opModeIsActive()) {
//
//            goToBuildPlate(Bot, "Blue");
//
//            orientBuildPlateBuild(Bot, "Blue");
//
//            pushBuildPlate(Bot, "Blue");
//
//            parkBuildingPlateOuter(Bot, "Blue");
//
//            idle();
//            requestOpModeStop();
//        }
//        idle();
//
//    }
//}
