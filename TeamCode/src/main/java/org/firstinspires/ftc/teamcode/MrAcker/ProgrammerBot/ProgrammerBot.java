// Robot Class Template for Competition 2022-2023
// Revision: 09-Oct-22
// Author: Jamie Acker

package org.firstinspires.ftc.teamcode.MrAcker.ProgrammerBot;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.Compitition.ZFreightFrenzy.mechanisms.CompContourPipeline;
import org.firstinspires.ftc.teamcode.Compitition.ZFreightFrenzy.mechanisms.TSELocation;
import org.opencv.core.Scalar;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

public class ProgrammerBot extends MecDrive_Programmer {

    //Declare Hardware Mapping Variable
    public HardwareMap hwBot = null;

    //Declare WebCam and OpenCV Variables
    public CompContourPipeline myPipeline;      //OpenCV Task that generates a sequence of images from video frames
    public OpenCvCamera webcam;
    private static final int CAMERA_WIDTH = 320;
    private static final int CAMERA_HEIGHT = 240;

    // WEBCAM - COLOR RANGE
    public static Scalar scalarLowerYCrCb = new Scalar(0.0, 0.0, 0.0);
    public static Scalar scalarUpperYCrCb = new Scalar(255.0, 150.0, 120.0);

    //Gyro Control Variables
    public BNO055IMU imu;
    public Orientation angles;
    public Acceleration gravity;
    public final double SPEED = 0.3;
    public final double TOLERANCE = 0.4;

    // Robot Initialization Method
    public void initRobot(HardwareMap hardwareMap) {

        hwBot = hardwareMap;

        // Drivetrain Motor Hardware Mapping & Configuration
        frontLeftMotor = hwBot.dcMotor.get("front_left_motor");
        frontRightMotor = hwBot.dcMotor.get("front_right_motor");
        rearLeftMotor = hwBot.dcMotor.get("rear_left_motor");
        rearRightMotor = hwBot.dcMotor.get("rear_right_motor");

        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        rearLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotor.Direction.FORWARD);
        rearRightMotor.setDirection(DcMotor.Direction.FORWARD);

        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Gyro Hardware Mapping & Configuration
        BNO055IMU.Parameters parametersimu = new BNO055IMU.Parameters();
        parametersimu.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parametersimu.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parametersimu.calibrationDataFile = "BN0055IMUCalibration.json";
        parametersimu.loggingEnabled = true;
        parametersimu.loggingTag = "IMU";
        parametersimu.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
        imu = hwBot.get(BNO055IMU.class,"imu");
        imu.initialize(parametersimu);

    }

    public void initWebcam() {

        //OPENCV WEBCAM
        int cameraMonitorViewId = hwBot.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hwBot.appContext.getPackageName());

        webcam = OpenCvCameraFactory.getInstance().createWebcam(hwBot.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);

        //OPENCV PIPELINE
        webcam.setPipeline(myPipeline = new CompContourPipeline());

        // CONFIGURATION OF PIPELINE
        myPipeline.ConfigurePipeline(30, 30, 30, 30, CAMERA_WIDTH, CAMERA_HEIGHT);
        myPipeline.ConfigureScalarLower(scalarLowerYCrCb.val[0], scalarLowerYCrCb.val[1], scalarLowerYCrCb.val[2]);
        myPipeline.ConfigureScalarUpper(scalarUpperYCrCb.val[0], scalarUpperYCrCb.val[1], scalarUpperYCrCb.val[2]);

            webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
                @Override
                public void onOpened() {webcam.startStreaming(CAMERA_WIDTH, CAMERA_HEIGHT, OpenCvCameraRotation.UPRIGHT);}

                @Override
                public void onError(int errorCode) {}
            }
            );
    }


}


