package org.firstinspires.ftc.teamcode.Compitition.CenterStage.Controls.Auto.AutoPaths;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Compitition.CenterStage.Controls.Auto.AutoRedAlliance;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;
@Disabled
@Autonomous(name = "A - Red Backstage FOR COMP(Also CAM TEST)")
public class AutoRedBackstage extends AutoRedAlliance {


    public static final boolean USE_WEBCAM = true;

    public static int oneSecond = 1000;

    public TfodProcessor tFod;

    @Override
    public void runOpMode() throws InterruptedException {
        Bot.initRobot(hardwareMap);
        initCamera();
        Bot.setLinearOp(this);
        Bot.planeLauncherServo.setPosition(1);
        startObjectDetectionPipeline(pipeline);
        telemetry.addLine("Starting Vision Pipeline");
//
//        Bot.rightWormgearUp(1, 544);
//        sleep(100);
        Bot.tuckPosition();

        telemetry.addLine("Robot Awaiting Start Procedure");
        telemetry.update();



        waitForStart();



        while (opModeIsActive()) {

            telemetry.addLine("Robot Autonomous Control Initialized");



            CameraDetection();
            sleep(100);
            Bot.drivePosition();
            sleep(100);
            Bot.rightWormgearDown(.5, 790);
            sleep(100);
//            Bot.driveForward(0.5,0.2);
//            sleep(100); //800
            Bot.driveForward(0.5,1.5); //1.1
            sleep(100);
            // Bot.driveForward(0.5,0.5);
            spikeMarkPlaceClose();
            sleep(100);






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
