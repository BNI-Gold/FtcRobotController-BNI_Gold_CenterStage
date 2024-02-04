package org.firstinspires.ftc.teamcode.Compitition.CenterStage.RoadRunner.drive.opmode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Compitition.CenterStage.RoadRunner.drive.RoadrunMecanumDrive;

/*
 * This is an example of a more complex path to really test the tuning.
 */
@Autonomous(group = "drive")
public class SplineTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        RoadrunMecanumDrive drive = new RoadrunMecanumDrive(hardwareMap);

        waitForStart();

        if (isStopRequested()) return;




        Trajectory traj = drive.trajectoryBuilder(new Pose2d(13, -58, -4.7))

                .forward(23)
                .lineToLinearHeading(new Pose2d(13,-57,Math.toRadians(1)))
//                                    .forward(35)
//                                    .strafeRight(Math.toRadians(800))
                .splineToSplineHeading(new Pose2d(45,-31,Math.toRadians(0)),Math.toRadians(90))
                .splineToSplineHeading(new Pose2d(13,-12,Math.toRadians(180)),Math.toRadians(190))
//
                .build();

        drive.followTrajectory(traj);

        sleep(2000);

//        drive.followTrajectory(
//                drive.trajectoryBuilder(traj.end(), true)
//                        .splineTo(new Vector2d(0, 0), Math.toRadians(180))
//                        .build()
//        );
    }
}
