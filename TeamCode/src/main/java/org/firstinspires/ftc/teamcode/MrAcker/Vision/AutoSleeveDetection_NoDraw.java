package org.firstinspires.ftc.teamcode.MrAcker.Vision;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

// April Tag Detection. No drawing of bounding boxes or cubes
//
@Autonomous(name="Vision: Sleeve Detect No Draw")
public class AutoSleeveDetection_NoDraw extends AutoMain_Acker_NoDraw {

    @Override
    public void runOpMode() throws InterruptedException {

        // Initialize WebCam and Create Image Processing Pipeline
        initializePipeline();

        // Find Tags During the Init Loop
        while (!isStarted() && !isStopRequested()) {
            findTag();
            sleep(20);
        }

        // Start Button Processed
        while (opModeIsActive()) {

            // Select Parking Position
            parkingTelemetry();
            sleep(6000);

            requestOpModeStop();
        }
        idle();
    }

}
