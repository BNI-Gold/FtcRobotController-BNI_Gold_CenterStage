//package org.firstinspires.ftc.teamcode.Compitition.CenterStage.Controls.Auto.VisionAuto;
//
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//
//import org.firstinspires.ftc.teamcode.Compitition.CenterStage.Robots.CompBot;
//import org.firstinspires.ftc.teamcode.Compitition.CenterStage.Robots.ProgrammingBot;
//import org.firstinspires.ftc.vision.VisionPortal;
//import org.firstinspires.ftc.vision.tfod.TfodProcessor;
//import org.openftc.easyopencv.OpenCvCamera;
//
//@Autonomous (name = "Backstage Red Vision Test")
//public class BackstageVisionRed extends BackstagePlace {
//
//
//    OpenCvCamera webcam;
//    BNIVision_Adapted.TeamPropPositionPipeline pipeline;
//
//    public static final boolean USE_WEBCAM = true;
//
//    public static int oneSecond = 1000;
//
//    public TfodProcessor tFod;
//
//    public VisionPortal visionPortal;
//
//  public ProgrammingBot Bot = new ProgrammingBot();
//
//
//    @Override
//    public void runOpMode() {
//
//
//
//        //change to comp bot
//        Bot.initRobot(hardwareMap);
//        Bot.setLinearOp(this);
//
//        telemetry.addLine("Robot Awaiting Start Procedure");
//        telemetry.update();
//
//       // Bot.endgameArmRotator.setPosition(0.8);
//
//        //Bot.drivePosition();
//
//        waitForStart();
//
//
//
//        while (opModeIsActive()) {
//
//            telemetry.addLine("Robot Autonomous Control Initialized");
//
//            // get in front of the backdrop here
//
//            //visionPlace(Bot, pipeline.getAnalysis());
//
//            telemetry.addData("Detected Position: ", pipeline.getAnalysis());
//
//            telemetry.addLine("Robot Autonomous Control Complete");
//
//            stopDetectionSystem();
//
//            requestOpModeStop();
//
//        }
//
//        idle();
//
//    }
//
//
//
//
//
//    public void telemetryUpdate(String comment) {
//
//        telemetry.addLine(comment);
//        telemetry.addData("Front Lef Motor:", Bot.frontLeftMotor.getPower());
//        telemetry.addData("Front Rig Motor:", Bot.frontRightMotor.getPower());
//        telemetry.addData("Rear Lef Motor:", Bot.rearLeftMotor.getPower());
//        telemetry.addData("Rear Rig Motor:", Bot.rearRightMotor.getPower());
//        telemetry.addData("Encoder Count: ", Bot.frontLeftMotor.getCurrentPosition());
//        telemetry.update();
//    }
//}
