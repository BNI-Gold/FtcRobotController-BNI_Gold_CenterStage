package org.firstinspires.ftc.teamcode.Compitition.CenterStage.Controls.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Compitition.CenterStage.BNIVision_Adapted;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

@Autonomous(name = "Object Detection")
public class ObjectDetection extends BNIVision_Adapted{


    OpenCvCamera webcam;
    TeamPropPositionPipeline pipeline;



    @Override
    public void runOpMode() {

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);

        // Used for SamplePipe from WebcamExample.  Going to change it to use "SkystoneDeterminationPipeline" based on Wizards.exe example.
        pipeline = new TeamPropPositionPipeline();
        webcam.setPipeline(pipeline);
        webcam.setViewportRenderingPolicy(OpenCvCamera.ViewportRenderingPolicy.MAXIMIZE_EFFICIENCY);
        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                // Supported Resolution
                // https://ftc-docs.firstinspires.org/en/latest/apriltag/vision_portal/visionportal_webcams/visionportal-webcams.html
                webcam.startStreaming(960, 720, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {}
        });


        waitForStart();

        while (opModeIsActive()) {

            telemetry.addData("Analysis", pipeline.getAnalysis());

            telemetry.update();

            sleep(10000);

            webcam.stopStreaming();
            webcam.stopRecordingPipeline();



            requestOpModeStop();
        }
    }
}
