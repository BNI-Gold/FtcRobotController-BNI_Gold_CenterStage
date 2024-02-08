package org.firstinspires.ftc.teamcode.Compitition.CenterStage.Controls.Auto;

import org.firstinspires.ftc.teamcode.Compitition.CenterStage.RoadRunner.drive.RoadrunMecanumDrive;
import org.firstinspires.ftc.teamcode.Compitition.CenterStage.Vision.TeamPropPositionPipeline_Gold;

public abstract class AutoRedAliianceRoadrun extends AutoMain{

    public RoadrunMecanumDrive drive = new RoadrunMecanumDrive(hardwareMap);
    public TeamPropPositionPipeline_Gold pipeline = new TeamPropPositionPipeline_Gold("RED", 140);

    public void CameraDetection() {
        teamPropPosition = pipeline.getAnalysis();
        telemetry.addData("Position Detected: ", teamPropPosition);
        telemetry.update();
        sleep(150);


        stopCamera();
        telemetry.addLine("Stopping Camera");
        telemetry.update();
        //sleep(150);

    }
}



