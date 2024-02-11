package org.firstinspires.ftc.teamcode.Compitition.CenterStage.Controls.Auto.AutoWithRoadrunner;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.Compitition.CenterStage.RoadRunner.drive.RoadrunMecanumDrive;

//@Config
//@Disabled
@Autonomous(name = "RR: Acker: Traj Builder")
public class RoadRunnerTrajBuilder extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        RoadrunMecanumDrive drive = new RoadrunMecanumDrive(hardwareMap);

        Pose2d startPose = new Pose2d(13, -60, -4.7);
        drive.setPoseEstimate(startPose);

        Trajectory traj1 = drive.trajectoryBuilder(startPose)
                .forward(27)
                .build();

        Trajectory traj2 = drive.trajectoryBuilder(traj1.end())
                .strafeLeft(20)
                .build();

        Trajectory traj3 = drive.trajectoryBuilder(traj2.end())
                .strafeRight(20)
                .build();

        waitForStart();

        if (isStopRequested()) return;

        telemetry.addLine("Executing Trajectory 1...");
        telemetry.update();
//        drive.followTrajectory(traj1);
        telemetry.addLine("Executing Turn...");
        telemetry.update();
        drive.turn(Math.toRadians(90));
        telemetry.addLine("Executing Trajectory 2...");
        telemetry.update();
        drive.followTrajectory(traj2);
        telemetry.addLine("Executing Trajectory 3...");
        telemetry.update();
        drive.followTrajectory(traj3);
        telemetry.addLine("Finished All Trajectories...");
        telemetry.update();
        sleep(1000);
        telemetry.addLine("Ready for Next Sequence...");
        telemetry.update();
        sleep(1000);
        requestOpModeStop();
    }

}




















