package org.firstinspires.ftc.teamcode.Compitition.CenterStage.Controls.Auto.AutoWithRoadrunner;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Compitition.CenterStage.Controls.Auto.AutoRedAliianceRoadrun;
import org.firstinspires.ftc.teamcode.Compitition.CenterStage.RoadRunner.drive.RoadrunMecanumDrive;
import org.firstinspires.ftc.teamcode.Compitition.CenterStage.RoadRunner.trajectorysequence.TrajectorySequence;

@Config
@Autonomous(name = "RR: Acker: Traj Sequence")
public class RoadrunneConnorRedesign extends AutoRedAliianceRoadrun {

    public boolean trajectoryCompleted = false;

    @Override
    public void runOpMode() throws InterruptedException {

        RoadrunMecanumDrive drive = new RoadrunMecanumDrive(hardwareMap);
        Pose2d startPose = new Pose2d(13, -60, -4.7);
        drive.setPoseEstimate(startPose);

        TrajectorySequence trajSeq = drive.trajectorySequenceBuilder(startPose)
                .forward(27)
                .turn(Math.toRadians(90))
                .back(25)
                .turn(Math.toRadians(-180))
                .strafeLeft(30)
                .back(90)
                .build();

        telemetry.addLine("Robot Awaiting Start");
        telemetry.update();

        waitForStart();

        if (!trajectoryCompleted) { // Check if the trajectory has not been completed
            telemetry.addLine("Executing Trajectory...");
            telemetry.update();
            drive.followTrajectorySequence(trajSeq); // Execute the trajectory sequence
            telemetry.addLine("Trajectory Completed");
            telemetry.update();
            trajectoryCompleted = true;
        }
        telemetry.addLine("Ready for Next Sequence");
        telemetry.update();
    }

}
