package org.firstinspires.ftc.teamcode.Compitition.CenterStage.Controls.Auto.AutoPaths;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Compitition.CenterStage.Controls.Auto.AutoBlueAlliance;
import org.firstinspires.ftc.teamcode.Compitition.CenterStage.Controls.Auto.AutoMain;
import org.firstinspires.ftc.teamcode.Compitition.CenterStage.Robots.CompBot;
import org.firstinspires.ftc.teamcode.Compitition.CenterStage.Vision.TeamPropPosition;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;

@Autonomous (name = "A - Blue Audience-FOR COMP")
public class AutoBlueAudience extends AutoBlueAlliance {

    public static final boolean USE_WEBCAM = true;

    public static int oneSecond = 1000;

    public TfodProcessor tFod;









    @Override
    public void runOpMode() {
        Bot.initRobot(hardwareMap);
        initCamera();
        Bot.setLinearOp(this);

        startObjectDetectionPipeline(pipeline);
        telemetry.addLine("Starting Vision Pipeline");
//
//        Bot.automousPosition();
//        Bot.endgameArmRotator.setPosition(0.8);


        telemetry.addLine("Robot Awaiting Start Procedure");
        telemetry.update();



        waitForStart();



        while (opModeIsActive()) {

            telemetry.addLine("Robot Autonomous Control Initialized");

//            Bot.wormgearRight.setPower(0.5);
//            sleep(1500);
//            Bot.wormgearRight.setPower(0);
//            sleep(300);
//            Bot.driveForward(0.5,0.8);
//            sleep (1000);
//            Bot.rotateRight(0.4, 2.2);
//            sleep(1000);
//            Bot.driveBack(0.5,1.35);
//            sleep(1000);
//            Bot.rotateLeft(0.4,4.4);
//            sleep(1000);

//
//            teamPropPosition = pipeline.getAnalysis();
//            telemetry.addData("Position Detected: ", teamPropPosition);
//            telemetry.update();
//            sleep(1000);
//
//            // Backup detection after first detection
//            teamPropPosition = pipeline.getAnalysis();
//            telemetry.addData("Position Detected: ", teamPropPosition);
//            telemetry.update();
//            sleep(1000);
//
//            // Stop Camera Detection
//            stopCamera();
//            telemetry.addLine("Stopping Camera");
//            telemetry.update();
//            sleep(1000);
//
//            if (teamPropPosition == TeamPropPosition.BLUE_LEFT ) {
//                Bot.driveForward(.5,.3);
//            }

//            Bot.endgameArmRotator.setPosition(.1);
//            sleep(oneSecond);
//            Bot.driveForward(0.5,0.3);
//            sleep(oneSecond);
//            Bot.rotateLeft(0.5,2.25);
//            sleep(oneSecond);
//            Bot.driveForward(0.5,8);

            telemetry.addLine("Robot Autonomous Control Complete");

            requestOpModeStop();
        }

        idle();


    }





    public void telemetryUpdate(String comment) {

        telemetry.addLine(comment);
        telemetry.addData("Front Lef Motor:", Bot.frontLeftMotor.getPower());
        telemetry.addData("Front Rig Motor:", Bot.frontRightMotor.getPower());
        telemetry.addData("Rear Lef Motor:", Bot.rearLeftMotor.getPower());
        telemetry.addData("Rear Rig Motor:", Bot.rearRightMotor.getPower());
        telemetry.addData("Encoder Count: ", Bot.frontLeftMotor.getCurrentPosition());
        telemetry.update();
    }
}
