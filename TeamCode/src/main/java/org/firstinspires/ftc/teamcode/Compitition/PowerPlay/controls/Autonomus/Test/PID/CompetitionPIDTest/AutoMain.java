package org.firstinspires.ftc.teamcode.Compitition.PowerPlay.controls.Autonomus.Test.PID.CompetitionPIDTest;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

import java.util.ArrayList;

public abstract class AutoMain extends LinearOpMode {

    public AutoTargetZone Zone = null;
    OpenCvCamera camera;
    // Remove "_NoDraw" from below variable type in order to draw on image rendering
    TagDetection_NoDraw tagPipeline;
    public AutoTargetZone TargetZone = AutoTargetZone.None;

    // in pixles
    double fx = 578.272;
    double fy = 578.272;
    double cx = 402.145;
    double cy = 221.506;

    double armInitPos = 0.36;

    static final double FEET_PER_METER = 3.28084;
    int ID_TAG_LIST [] = {0,1,2};
    double tagsize = 0.0445;
    public AprilTagDetection tagOfInterest = null;

    public AutoTargetZone zone = AutoTargetZone.A;

    public long sleepTime = 250;

//    public AutoTargetZone DetectSleaveImage (CompetionBot Bot) {
//
//        zone = AutoTargetZone.A;
//
//        return null;
//    }

    public void initializePipeline () {

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);

        // Remove "_NoDraw" from constructor in order to draw on image rendering
        tagPipeline = new TagDetection_NoDraw(tagsize, fx, fy, cx, cy);

        camera.setPipeline(tagPipeline);

        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {

            @Override
            public void onOpened() {
                camera.startStreaming(544,288, OpenCvCameraRotation.UPRIGHT);   //original 800 x 448
            }

            @Override
            public void onError(int errorCode) { }

        });

    }

    public void stopCamera() {

        camera.stopRecordingPipeline();
        camera.stopStreaming();
        camera.closeCameraDevice();

    }

    public void detectTags() {

        findTag();

        sleep(500);

        findTag();

        sleep(500);

        findTag();

        sleep(500);

        stopCamera();

        sleep(500);

    }

    public void findTag () {
        ArrayList<AprilTagDetection> currentDetections = tagPipeline.getLatestDetections();

        if(currentDetections.size() != 0) {
            boolean tagFound = false;
            for(AprilTagDetection tag : currentDetections) {
                for (int i = 0; i < ID_TAG_LIST.length; i++) {
                    if(tag.id == ID_TAG_LIST[i]) {
                        tagOfInterest = tag;
                        tagFound = true;
                        break;
                    }
                }
            }

            if(tagFound) {
                if (tagOfInterest.id == ID_TAG_LIST[0]) {
                    TargetZone = AutoTargetZone.A;

                }
                else if (tagOfInterest.id == ID_TAG_LIST[1]) {
                    TargetZone = AutoTargetZone.B;
                }
                else if (tagOfInterest.id == ID_TAG_LIST[2]) {
                    TargetZone = AutoTargetZone.C;
                }
                else {
                    TargetZone = AutoTargetZone.None;
                }

                telemetry.addLine("Tag Detected");
                parkingTelemetry();
                tagTelemetry(tagOfInterest);
                telemetry.update();
            }
            else {
                telemetry.addLine("No Tag Detected");

                if(tagOfInterest == null) {
                    telemetry.addLine("Tag Detection Failed");
                }
                else {
                    telemetry.addLine("\nTag Previously Detected: ");
                    tagTelemetry(tagOfInterest);
                }
            }

        }
        else {
            telemetry.addLine("No Tag Detected");

            if(tagOfInterest == null) {
                telemetry.addLine("Tag Detection Failed");
            }
            else {
                telemetry.addLine("\nTag Previously Detected: ");
                tagTelemetry(tagOfInterest);
            }

        }
        telemetry.update();

    }

    public void parkingTelemetry() {
        telemetry.addData("Tag ID: ", tagOfInterest.id);
        telemetry.addData("Parking Location: ", TargetZone);
        telemetry.addData("LETS GET THIS W | GO BNI", null);
    }

    // Method for Full Telemetry including Tag ID, XYZ Translation and Rotation
    void tagTelemetry(AprilTagDetection detection)
    {
        telemetry.addLine(String.format("\nDetected tag ID=%d", detection.id));
        telemetry.addLine(String.format("Translation X: %.2f feet", detection.pose.x*FEET_PER_METER));
        telemetry.addLine(String.format("Translation Y: %.2f feet", detection.pose.y*FEET_PER_METER));
        telemetry.addLine(String.format("Translation Z: %.2f feet", detection.pose.z*FEET_PER_METER));
        telemetry.addLine(String.format("Rotation Yaw: %.2f degrees", Math.toDegrees(detection.pose.yaw)));
        telemetry.addLine(String.format("Rotation Pitch: %.2f degrees", Math.toDegrees(detection.pose.pitch)));
        telemetry.addLine(String.format("Rotation Roll: %.2f degrees", Math.toDegrees(detection.pose.roll)));
    }


}
