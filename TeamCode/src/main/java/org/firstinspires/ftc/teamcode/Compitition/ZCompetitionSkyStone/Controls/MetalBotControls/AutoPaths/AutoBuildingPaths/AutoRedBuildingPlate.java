//package org.firstinspires.ftc.teamcode.Compitition.ZCompetitionSkyStone.Controls.MetalBotControls.AutoPaths.AutoBuildingPaths;
//
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.Disabled;
//
//import org.firstinspires.ftc.teamcode.Compitition.ZCompetitionSkyStone.Controls.MetalBotControls.AutoBuilding;
//import org.firstinspires.ftc.teamcode.Compitition.ZCompetitionSkyStone.robots.MetalBot;
//
//@Autonomous(name = "Red:Building Plate")
//@Disabled
//public class AutoRedBuildingPlate extends AutoBuilding {
//
//    public MetalBot Bot = new MetalBot();
//
//    @Override
//    public void runOpMode() throws InterruptedException {
//
//        Bot.initRobot(hardwareMap, "Build");
//        Bot.setLinearOp(this);
//        Bot.HookRelease();
//        //Bot.autoRaiseStone();
//        //Bot.grabStone();
//        //Bot.setServos();
//
//        setLinearOp(this);
//
//
//
//        waitForStart();
//
//        while (opModeIsActive()) {
//
//            goToBuildPlate(Bot, "Red");
//
//            orientBuildPlateBuild(Bot, "Red");
//
//            pushBuildPlate(Bot, "Red");
//
//            parkBuildingPlateInner(Bot, "Red");
//
//
//            idle();
//            requestOpModeStop();
//        }
//        idle();
//
//    }
//}
