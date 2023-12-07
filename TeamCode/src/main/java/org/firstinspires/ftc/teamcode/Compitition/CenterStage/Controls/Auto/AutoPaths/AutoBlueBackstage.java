package org.firstinspires.ftc.teamcode.Compitition.CenterStage.Controls.Auto.AutoPaths;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Compitition.CenterStage.Controls.Auto.AutoBlueAlliance;
import org.firstinspires.ftc.teamcode.Compitition.CenterStage.Controls.Auto.AutoMain;
import org.firstinspires.ftc.teamcode.Compitition.CenterStage.Robots.CompBot;
import org.firstinspires.ftc.teamcode.Compitition.CenterStage.Robots.ProgrammingBot;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;

@Autonomous (name = "A - Blue Backstage FOR COMP")
public class AutoBlueBackstage extends AutoBlueAlliance {

    public static final boolean USE_WEBCAM = true;

    public static int oneSecond = 1000;

    public TfodProcessor tFod;

    //public VisionPortal visionPortal;




    @Override
    public void runOpMode() {
        Bot.initRobot(hardwareMap);
       initCamera();
        Bot.setLinearOp(this);
        startObjectDetectionPipeline(pipeline);

//        startObjectDetectionPipeline(pipeline);
//        telemetry.addLine("Starting Vision Pipeline");




        telemetry.addLine("Robot Awaiting Start Procedure");
        telemetry.update();


        waitForStart();



        while (opModeIsActive()) {

            telemetry.addLine("Robot Autonomous Control Initialized");
//


//            Bot.driveForward(0.5,0.55);
//            sleep(1000);
//            Bot.rotateLeft(0.4,2.2);
//            sleep(1000);
//            Bot.driveForward(0.5,3.0);
//            sleep(1000);
           CameraDetection();

            if (teamPropPosition == teamPropPosition.BLUE_LEFT) {
                telemetryUpdate("Position : LEFT");
                Bot.driveForward(0.5,0.7);
                sleep(1000);
                Bot.rotateLeft(0.3,1.1);
            }
            else if (teamPropPosition == teamPropPosition.BLUE_MIDDLE) {
                telemetryUpdate("Position - CENTER");
                Bot.driveForward(0.5,0.7);
            }
            else if (teamPropPosition == teamPropPosition.BLUE_RIGHT){
                telemetryUpdate("Position : RIGHT");
                Bot.driveForward(0.5,0.7);
                sleep(1000);
                Bot.rotateRight(0.3,1.1);
            }
            else {
                telemetryUpdate("No Position Detected");
                Bot.driveForward(0.5,0.55);
            sleep(1000);
            Bot.rotateLeft(0.4,2.2);
            sleep(1000);
            Bot.driveForward(0.5,3.0);
            sleep(1000);
            }

            stopCamera();


            
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
