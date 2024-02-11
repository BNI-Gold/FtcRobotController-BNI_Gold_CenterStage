package org.firstinspires.ftc.teamcode.Compitition.CenterStage.Controls.Auto.AutoWithRoadrunner;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Compitition.CenterStage.Controls.Auto.AutoRedAlliance;
import org.firstinspires.ftc.teamcode.Compitition.CenterStage.RoadRunner.drive.RoadrunMecanumDrive;
import org.firstinspires.ftc.teamcode.Compitition.CenterStage.RoadRunner.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.Compitition.CenterStage.Vision.TeamPropPosition;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;

//@Config
//@Disabled
@Autonomous(name = "RR: RED BACKSTAGE")
public class RoadrunnerRedBackstage extends AutoRedAlliance {

    public boolean trajectoryCompleted = false;

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

        Bot.tuckPosition();


        RoadrunMecanumDrive drive = new RoadrunMecanumDrive(hardwareMap);
        Pose2d startPose = new Pose2d(13, -60, -4.7);
        drive.setPoseEstimate(startPose);



        TrajectorySequence trajSeq1 = drive.trajectorySequenceBuilder(startPose)
                .forward(25)
                .addDisplacementMarker(() -> {
                        Bot.collectorPosition();
                       // if (Bot.pixelDistanceSensor1.getDistance(DistanceUnit.INCH) > 1.05 && Bot.pixelDistanceSensor1.getDistance(DistanceUnit.INCH) < 1.5 ))
                    Bot.rightWormgearDown(.6, 800);
                })
                .turn(Math.toRadians(90))
                .forward(8)
                .back(0.5)

                .addDisplacementMarker(() -> {

                    Bot.leftPixelClawOpen();

                })

                .waitSeconds(1)
                .back(24)

                .addDisplacementMarker(() -> {
                    Bot.leftPixelClawClose();
                    Bot.drivePosition();
                })
                .turn(Math.toRadians(-180))
                .addDisplacementMarker(()->{
                    Bot.rightWormgearUp(1,751);
                    Bot.autoPlacePosition();

                })

                .strafeLeft(23)
                .forward(15)









                .build();

        TrajectorySequence trajseq2 = drive.trajectorySequenceBuilder(startPose)
                .forward(15)

                .addDisplacementMarker(() -> {
                    Bot.collectorPosition();
                    // if (Bot.pixelDistanceSensor1.getDistance(DistanceUnit.INCH) > 1.05 && Bot.pixelDistanceSensor1.getDistance(DistanceUnit.INCH) < 1.5 ))
                    Bot.rightWormgearDown(.6, 800);

                })

                .waitSeconds(1)

                .forward(19)
                .back(2.5)
                .addDisplacementMarker(() -> {
                    Bot.leftPixelClawOpen();
                })



                .back(7)
                .waitSeconds(1)
                .turn(Math.toRadians(-90))


                .addDisplacementMarker(()->{
                    Bot.rightWormgearUp(1,751);
                    Bot.autoPlacePosition();

                })
                .forward(25)
                .strafeLeft(20)
                .forward(25)
                        .build();

        TrajectorySequence trajseq3 = drive.trajectorySequenceBuilder(startPose)
                .forward(7)

                .addDisplacementMarker(() -> {
                    Bot.collectorPosition();
                    // if (Bot.pixelDistanceSensor1.getDistance(DistanceUnit.INCH) > 1.05 && Bot.pixelDistanceSensor1.getDistance(DistanceUnit.INCH) < 1.5 ))
                    Bot.rightWormgearDown(.6, 800);

                })

                .waitSeconds(1)
                .strafeRight(18)
                .forward(11)
                .back(1)
                .addDisplacementMarker(() -> {
                    Bot.leftPixelClawOpen();
                })



                .back(8)
                .waitSeconds(1)
                .turn(Math.toRadians(-90))


                .addDisplacementMarker(()->{
                    Bot.rightWormgearUp(1,751);
                    Bot.autoPlacePosition();

                })
                .forward(25)
                .strafeLeft(9)
                .forward(15)
                .build();



//        Trajectory traj2 = drive.trajectoryBuilder(trajSeq1.end())
//                        .back(25)
//                                .build();
//
//        Trajectory traj3 = drive.trajectoryBuilder(traj2.end())
//                        .strafeLeft(27)
//                                .build();
//        Trajectory traj4 = drive.trajectoryBuilder(traj3.end())
//                        .forward(10)
//                                .build();

        telemetry.addLine("Robot Awaiting Start");
        telemetry.update();

        waitForStart();

        if(isStopRequested()) return;

      //  if (!trajectoryCompleted) { // Check if the trajectory has not been completed

            CameraDetection();

            if (teamPropPosition == TeamPropPosition.RED_LEFT) {
                telemetry.addLine("Executing Trajectory...");
                telemetry.update();
                drive.followTrajectorySequence(trajSeq1);
                telemetry.addLine("Finished Trajectory...");
                telemetry.update();

                Bot.linearSlideExtend(.8,390);
                sleep(500);
                Bot.rightPixelClawClose();
                sleep(1500);
                Bot.linearSlideRetract(.8,200);
                sleep(200);
                Bot.driveForward(0.8,2);
                Bot.strafeLeft(0.6,3.5);
                Bot.driveBack(0.6,1.5);

                requestOpModeStop();

            }
            else if (teamPropPosition == TeamPropPosition.RED_MIDDLE) {
                telemetry.addLine("Executing Trajectory...");
                telemetry.update();
                drive.followTrajectorySequence(trajseq2);
                telemetry.addLine("Finished Trajectory...");
                telemetry.update();
                Bot.linearSlideExtend(.8,390);
                sleep(500);
                Bot.rightPixelClawClose();
                sleep(1500);
                Bot.linearSlideRetract(.8,200);
                sleep(200);
                Bot.driveForward(0.8,2.2);
                Bot.strafeLeft(0.6,2.4);
                Bot.driveBack(0.6,1);

//

                requestOpModeStop();

            }

            else if (teamPropPosition == TeamPropPosition.RED_RIGHT) {
                telemetry.addLine("Executing Trajectory...");
                telemetry.update();
                drive.followTrajectorySequence(trajseq3);
                telemetry.addLine("Finished Trajectory...");
                telemetry.update();

                Bot.linearSlideExtend(.8,390);
                sleep(500);
                Bot.rightPixelClawClose();
                sleep(1500);
                Bot.linearSlideRetract(.8,200);
                sleep(200);
                Bot.strafeLeft(0.5,0.5);
                Bot.driveForward(0.8,1);
                Bot.strafeLeft(0.6,1.2);
                Bot.driveBack(0.6,0.5);



                requestOpModeStop();

            }


//            telemetry.addLine("Trajectory Completed");
//            telemetry.update();
       //     trajectoryCompleted = true;
        }
//        sleep(1000);
//        telemetry.addLine("Ready for Next Sequence");
//        telemetry.update();
//        sleep(1000);
//

    }


