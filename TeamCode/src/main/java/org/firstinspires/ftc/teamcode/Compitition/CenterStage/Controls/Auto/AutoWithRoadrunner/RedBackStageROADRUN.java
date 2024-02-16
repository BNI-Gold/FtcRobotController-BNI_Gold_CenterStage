package org.firstinspires.ftc.teamcode.Compitition.CenterStage.Controls.Auto.AutoWithRoadrunner;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Compitition.CenterStage.Controls.Auto.AutoRedAliianceRoadrun;
import org.firstinspires.ftc.teamcode.Compitition.CenterStage.Controls.Auto.AutoRedAlliance;
import org.firstinspires.ftc.teamcode.Compitition.CenterStage.RoadRunner.drive.RoadrunMecanumDrive;
import org.firstinspires.ftc.teamcode.Compitition.CenterStage.RoadRunner.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.Compitition.CenterStage.Robots.CompBot;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;

@Disabled
@Config
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
        startObjectDetectionPipeline(pipeline);
        telemetry.addLine("Starting Vision Pipeline");
//
//


        RoadrunMecanumDrive drive = new RoadrunMecanumDrive(hardwareMap);

        Pose2d startPose = new Pose2d(13, -60, -4.7);
        drive.setPoseEstimate(startPose);

//        TrajectorySequence trajSeq = drive.trajectorySequenceBuilder(startPose)
//        Trajectory traj1 = drive.trajectoryBuilder(startPose)
//                .forward(30)
////                .lineToLinearHeading(new Pose2d(13,-31,Math.toRadians(182)))
//                .build();
//        Trajectory traj2 = drive.trajectoryBuilder(traj1.end())
////                .lineToLinearHeading(new Pose2d(45,-31,Math.toRadians(1)))
//                .back(22)
//                .build();
//        Trajectory traj3 = drive.trajectoryBuilder(traj2.end())
////                .lineToLinearHeading(new Pose2d(45,-10,Math.toRadians(-45)))
//                .strafeLeft(31)
//                .build();
//        Trajectory traj4 = drive.trajectoryBuilder(startPose)
//                .forward(8)
//                .build();

//        drive.trajectorySequenceBuilder(new Pose2d(13, -60, -4.7))
//                                            .forward(27)
//                                            .turn(Math.toRadians(90))
//                                            .back(30)
//                                            .turn(Math.toRadians(-180))
//                                            .strafeLeft(22)
//                                            .back(90)
//                                            .turn(Math.toRadians(180))
//                                            .back(90)
//                                            .turn(Math.toRadians(180))
//                                            .strafeRight(22)
//                                            .strafeRight(27)
//                                            .forward(17)

//                                            .forward(15)

        //blue backstage simple
//                            drive.trajectorySequenceBuilder(new Pose2d(13, 58, 4.7))
//                                    .forward(27)
//                                    .strafeLeft(2)
//                                    .turn(Math.toRadians(-90))
//                                    .forward(1)
//                                    .back(29)
//                                    .turn(Math.toRadians(180))
//                                    .strafeLeft(7)
//                                    .strafeRight(27)
//                                    .back(90)
//                                    .turn(Math.toRadians(-180))
//                                    .back(90)
//                                    .turn(Math.toRadians(-180))
//                                    .strafeLeft(22)
//                                    .strafeLeft(27)
//                                    .forward(17)


        //red audience simple
//        drive.trajectorySequenceBuilder(new Pose2d(-35, -57, -4.7))
//                .forward(24)
//                .turn(Math.toRadians(-90))
//                .turn(Math.toRadians(180))
//                .strafeLeft(3)
//                .forward(15)
//                .strafeLeft(23)
//                .back(90)
//                .strafeRight(15)
//                .turn(Math.toRadians(-180))
//                .strafeRight(15)
//                .back(90)
//                .strafeLeft(10)
//                .turn(Math.toRadians(180))
//                .strafeRight(13)
//                .forward(4)
//                .back(4)
//                .strafeLeft(23)
//                .back(90)
//                .strafeRight(15)
//                .turn(Math.toRadians(-180))
//                .strafeLeft(34)
//                .forward(15)


        telemetry.addLine(
                "Robot Awaiting Start Procedure");
        telemetry.update();




        TrajectorySequence trajSeq = drive.trajectorySequenceBuilder(startPose)
                .forward(27)
                .turn(Math.toRadians(90))
                .back(25)
                .turn(Math.toRadians(-180))
                .strafeLeft(30)
                .back(90)
//                .turn(Math.toRadians(180))
//                .back(90)
//                .turn(Math.toRadians(180))
//                .strafeRight(22)
//                .strafeRight(27)
//                .forward(17)
//                .forward(15)
                .build();


        waitForStart();

        while (!isStopRequested()) {

            telemetry.addLine("Robot Autonomous Control Initialized");
            drive.followTrajectorySequence(trajSeq);


            //MUST have
            requestOpModeStop();
        }
        idle();
    }





//            drive.followTrajectory(traj1);
//            drive.turn(Math.toRadians(90));
//            drive.followTrajectory(traj2);
//             drive.turn(Math.toRadians(180));
//            drive.followTrajectory(traj3);
//            drive.followTrajectory(traj4);























    public void telemetryUpdate(String comment) {

        telemetry.addLine(comment);
        telemetry.addData("Lateral ENcoder",Bot.rearLeftMotor.getCurrentPosition());
//        telemetry.addData("Front Lef Motor:", drive.leftF.getPower());
//        telemetry.addData("Front Rig Motor:", Bot.frontRightMotor.getPower());
//        telemetry.addData("Rear Lef Motor:", Bot.rearLeftMotor.getPower());
//        telemetry.addData("Rear Rig Motor:", Bot.rearRightMotor.getPower());
//        telemetry.addData("Encoder Count: ", Bot.frontLeftMotor.getCurrentPosition());
        telemetry.update();
    }
}
