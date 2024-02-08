package org.firstinspires.ftc.teamcode.Compitition.CenterStage.Controls.Auto.AutoWithRoadrunner;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Compitition.CenterStage.Controls.Auto.AutoRedAliianceRoadrun;
import org.firstinspires.ftc.teamcode.Compitition.CenterStage.Controls.Auto.AutoRedAlliance;
import org.firstinspires.ftc.teamcode.Compitition.CenterStage.RoadRunner.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;

@Autonomous(name = "RR- RedBackstage")
public class RedBackStageROADRUN extends AutoRedAliianceRoadrun {

    public static final boolean USE_WEBCAM = true;

    public static int oneSecond = 1000;

    public TfodProcessor tFod;

    @Override
    public void runOpMode() throws InterruptedException {
//        Bot.initRobot(hardwareMap);

        initCamera();
//        Bot.setLinearOp(this);
//        Bot.planeLauncherServo.setPosition(1);
        drive.setLinearOp(this);
        startObjectDetectionPipeline(pipeline);
        telemetry.addLine("Starting Vision Pipeline");
//
//
        drive.tuckPosition();

        Pose2d startPose = new Pose2d(13, -58, -4.7);
        drive.setPoseEstimate(startPose);

        TrajectorySequence trajSeq = drive.trajectorySequenceBuilder(startPose)

                .lineToLinearHeading(new Pose2d(13,-31,Math.toRadians(182)))
                .lineToLinearHeading(new Pose2d(45,-31,Math.toRadians(1)))
                .lineToLinearHeading(new Pose2d(45,-10,Math.toRadians(-45)))
                .forward(5)


//        drive.trajectorySequenceBuilder(new Pose2d(13, -58, -4.7))//-4.7
//                                    .lineToLinearHeading(new Pose2d(13,-31,Math.toRadians(182)))
//                                             .lineToLinearHeading(new Pose2d(45,-31,Math.toRadians(1)))
//                                             .lineToLinearHeading(new Pose2d(13,-59,Math.toRadians(182)))
//                                    .lineTo(new Vector2d(-52,-59))
//                                    .lineTo(new Vector2d(-52,-36))
//                                    .lineTo(new Vector2d(-52,-59))
//                                    .lineTo(new Vector2d(13,-59))
//                                    .lineToLinearHeading(new Pose2d(45,-31,Math.toRadians(1)))
//                                    .lineTo(new Vector2d(45,-59))
//                                   .forward(5)
////

        //red audience new
//                                    drive.trajectorySequenceBuilder(new Pose2d(-36, -58, -4.7))
//                                            .lineToLinearHeading(new Pose2d(-36,-31,Math.toRadians(182)))
//                                            .lineTo(new Vector2d(-36,-20))
//                                            .splineToConstantHeading(new Vector2d(-52,-11),Math.toRadians(-180))
//                                            .lineTo(new Vector2d(37,-11))
//                                            .lineToLinearHeading(new Pose2d(45,-31,Math.toRadians(1)))
//                                            .lineToLinearHeading(new Pose2d(37,-11,Math.toRadians(180 )))
//                                            .lineTo(new Vector2d(-52,-11))
//                                            .lineTo(new Vector2d(37,-11))
//                                            .lineToLinearHeading(new Pose2d(45,-31,Math.toRadians(1)))
//                                            .lineToLinearHeading(new Pose2d(45,-10,Math.toRadians(-45)))
//                                            .forward(5)



        //blue backstage new
//                            drive.trajectorySequenceBuilder(new Pose2d(13, 58, 4.7))
//                                    .lineToLinearHeading(new Pose2d(13,31,Math.toRadians(182)))
//                                    .lineToLinearHeading(new Pose2d(45,31,Math.toRadians(1)))
//                                    .lineToLinearHeading(new Pose2d(13,59,Math.toRadians(182)))
//                                    .lineTo(new Vector2d(-52,59))
//                                    .lineTo(new Vector2d(-52,36))
//                                    .lineTo(new Vector2d(-52,59))
//                                    .lineTo(new Vector2d(13,59))
//                                    .lineToLinearHeading(new Pose2d(45,31,Math.toRadians(1)))
//                                    .lineTo(new Vector2d(45,59))
//                                   .forward(5)


        //blue audience new
//        drive.trajectorySequenceBuilder(new Pose2d(-35, 58, 4.7))
//                .lineToLinearHeading(new Pose2d(-36,31,Math.toRadians(182)))
//                .lineTo(new Vector2d(-36,20))
//                .splineToConstantHeading(new Vector2d(-52,11),Math.toRadians(-180))
//                .lineTo(new Vector2d(37,11))
//                .lineToLinearHeading(new Pose2d(45,31,Math.toRadians(1)))
//                .lineToLinearHeading(new Pose2d(37,11,Math.toRadians(180 )))
//                .lineTo(new Vector2d(-52,11))
//                .lineTo(new Vector2d(37,11))
//                .lineToLinearHeading(new Pose2d(45,31,Math.toRadians(1)))
//                .lineToLinearHeading(new Pose2d(45,5,Math.toRadians(45)))
//                .forward(5)
//




//                            //RED BACKSTAGE SIMPLE (For Acker)
//                                    drive.trajectorySequenceBuilder(new Pose2d(13, -58, -4.7))
//                                            .lineToLinearHeading(new Pose2d(13,-31,Math.toRadians(182)))
//                                             .lineToLinearHeading(new Pose2d(45,-31,Math.toRadians(1)))
//                                            .lineToLinearHeading(new Pose2d(45,-10,Math.toRadians(-45)))
//                                            .forward(5)
                        .build();

        telemetry.addLine("Robot Awaiting Start Procedure");
        telemetry.update();



        waitForStart();



        while (opModeIsActive()) {

            telemetry.addLine("Robot Autonomous Control Initialized");



            CameraDetection();
            drive.followTrajectorySequence(trajSeq);







            telemetry.addLine("Robot Autonomous Control Complete");

            requestOpModeStop();
        }

        idle();


    }





    public void telemetryUpdate(String comment) {

        telemetry.addLine(comment);
//        telemetry.addData("Front Lef Motor:", drive.leftF.getPower());
//        telemetry.addData("Front Rig Motor:", Bot.frontRightMotor.getPower());
//        telemetry.addData("Rear Lef Motor:", Bot.rearLeftMotor.getPower());
//        telemetry.addData("Rear Rig Motor:", Bot.rearRightMotor.getPower());
//        telemetry.addData("Encoder Count: ", Bot.frontLeftMotor.getCurrentPosition());
        telemetry.update();
    }
}
