package org.firstinspires.ftc.teamcode.Compitition.CenterStage.Controls.Auto.AutoPaths;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Compitition.CenterStage.Controls.Auto.AutoRedAlliance;
import org.firstinspires.ftc.teamcode.Compitition.CenterStage.Drivetrains.MecanumDrive;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;

@Autonomous(name = "A - RED Audience-FOR COMP")
public class AutoRedAudience_Acceleration extends AutoRedAlliance {

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
            Bot.resetHeading();

            telemetry.addLine("Robot Autonomous Control Initialized");
            CameraDetection();
            sleep(100);
//            CLAW
            Bot.drivePosition();
            sleep(100);
            Bot.rightWormgearDown(.6, 780);
            Bot.rightWormgearStop();
            sleep(100);
            spikeMarkPlaceFar_Accel();
            sleep(100);
//            USE THIS FOR PIXEL DELIVERY
            dropPixelBackdrop();

            telemetry.addLine("Robot Autonomous Control Complete");

            sleep(1000);
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
