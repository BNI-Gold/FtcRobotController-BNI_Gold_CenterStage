package org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Robots;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.CompititionUltimateGoal.DriveTrains.MecanumDrive;
import org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Modules.EasyOpenCVWebcam;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;


public class CompetitionBot extends MecanumDrive {

    //hardware constructors
    public HardwareMap hwBot  =  null;

//GYRO INITIALIZATION

    public BNO055IMU imu;
    public Orientation angles;
    public Acceleration gravity;
    public final double SPEED = .3;
    public final double TOLERANCE = .4;

    // Color and Distance Hardware & Variables
    public ColorSensor sensorColor;
    public DistanceSensor sensorDistance;
    public float hsvValues[] = {0F, 0F, 0F};
    public final double SCALE_FACTOR = 1;
    //  Camera Initialization
    public OpenCvCamera webcam;
    public SkystoneDeterminationPipeline pipeline;

//    public static final double TICKS_PER_ROTATION = 383.6;   // GoBilda 13.7 Motor PPR




    Servo WobbleArm = null;
    Servo WobbleGrab = null;
    Servo Camera =null;
//    Servos servos = new Servos();


    public double servoOpenPos = 0.36;
    public double servoClosePos = 0.93;
    public double WobbleArmRaisedPos = 0.40;
    public double WobbleArmLowerPos = 0.14;
    public double WobbleGrabOpenPos = 0.72;
    public double WobbleGrabClosePos = 0.552;
    //Blue Left:
    public double CameraServoPosBlueLeft = 0.382;
    //Blue Right:
    public double CameraServoPosBlueRight = 0.602;

    //LabBot constructor
    public CompetitionBot() {

    }
    public void initRobot(HardwareMap hwMap, String startPosition){
        hwBot = hwMap;
        WobbleArm = hwBot.get(Servo.class, "wobble_arm");
        WobbleArm.setDirection(Servo.Direction.FORWARD);
        WobbleArm.setPosition(WobbleArmRaisedPos);
        WobbleGrab = hwBot.get(Servo.class, "wobble_grab");
        WobbleGrab.setDirection(Servo.Direction.FORWARD);
        WobbleGrab.setPosition(WobbleGrabClosePos);
        Camera = hwBot.get(Servo.class, "camera_blue_left_servo");
        Camera.setDirection(Servo.Direction.FORWARD);

        switch (startPosition) {
            case "BlueLeft":
                Camera.setPosition(CameraServoPosBlueLeft);
                break;
            case "BlueRight":
                break;
            case "RedLeft":
                break;
            case "RedRight":
                break;
        }




        // define motors for robot
        frontLeftMotor=hwBot.dcMotor.get("front_left_motor");
        frontRightMotor=hwBot.dcMotor.get("front_right_motor");
        rearLeftMotor = hwBot.dcMotor.get("rear_left_motor");
        rearRightMotor = hwBot.dcMotor.get("rear_right_motor");

        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        rearLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotor.Direction.FORWARD);
        rearRightMotor.setDirection(DcMotor.Direction.FORWARD);


        //Initialize Motor Run Mode for Robot
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        // Define and Initialize Gyro
        BNO055IMU.Parameters parametersimu = new BNO055IMU.Parameters();
        parametersimu.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parametersimu.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parametersimu.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parametersimu.loggingEnabled = true;
        parametersimu.loggingTag = "IMU";
        parametersimu.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        imu = hwBot.get(BNO055IMU.class, "imu");
        imu.initialize(parametersimu);





    }

    public void initCamera () {
        int cameraMonitorViewId = hwBot.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hwBot.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hwBot.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        pipeline = new SkystoneDeterminationPipeline();
        webcam.setPipeline(pipeline);
        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                /*
                 * Tell the webcam to start streaming images to us! Note that you must make sure
                 * the resolution you specify is supported by the camera. If it is not, an exception
                 * will be thrown.
                 *
                 * Keep in mind that the SDK's UVC driver (what OpenCvWebcam uses under the hood) only
                 * supports streaming from the webcam in the uncompressed YUV image format. This means
                 * that the maximum resolution you can stream at and still get up to 30FPS is 480p (640x480).
                 * Streaming at e.g. 720p will limit you to up to 10FPS and so on and so forth.
                 *
                 * Also, we specify the rotation that the webcam is used in. This is so that the image
                 * from the camera sensor can be rotated such that it is always displayed with the image upright.
                 * For a front facing camera, rotation is defined assuming the user is looking at the screen.
                 * For a rear facing camera or a webcam, rotation is defined assuming the camera is facing
                 * away from the user.
                 */
                webcam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
            }
        });
    }

    public void servoClosed () {
        WobbleArm.setPosition(servoClosePos);
    }

    public void servoOpened(){
        WobbleArm.setPosition(servoOpenPos);
    }

    public void WobbleLower() {
        WobbleArm.setPosition(WobbleArmLowerPos);
    }
    public void WobbleRaised() {
        WobbleArm.setPosition(WobbleArmRaisedPos);
    }
    public void WobbleOpen(){
        WobbleGrab.setPosition(WobbleGrabOpenPos);
    }
    public void WobbleClosed(){
        WobbleGrab.setPosition(WobbleGrabClosePos);
    }
    public void detectRings () {

     }

    public void gyroCorrection (double speed, double angle) {

        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        if (angles.firstAngle >= angle + TOLERANCE) {
            while (angles.firstAngle >=  angle + TOLERANCE && linearOp.opModeIsActive()) {
                rotateRight(speed);
                angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
            }
        }
        else if (angles.firstAngle <= angle - TOLERANCE) {
            while (angles.firstAngle <= angle - TOLERANCE && linearOp.opModeIsActive()) {
                rotateLeft(speed);
                angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
            }
        }
        stopMotors();

        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
    }

    public static class SkystoneDeterminationPipeline extends OpenCvPipeline
    {
        /*
         * An enum to define the skystone position
         */
        public enum RingPosition
        {
            FOUR,
            ONE,
            NONE
        }

        /*
         * Some color constants
         */
        static final Scalar BLUE = new Scalar(0, 0, 255);
        static final Scalar GREEN = new Scalar(0, 255, 0);

        /*
         * The core values which define the location and size of the sample regions
         */

        //        USE THIS TO FIGURE OUT NUMBER OF RINGS.
        static final Point REGION1_TOPLEFT_ANCHOR_POINT = new Point(181,98);

        //        ORIGINAL AREA
        static final int REGION_WIDTH = 35;
        static final int REGION_HEIGHT = 30;

        //        ORIGINAL THRESHOLDS
        final int FOUR_RING_THRESHOLD = 145;
        final int ONE_RING_THRESHOLD = 135;

        Point region1_pointA = new Point(
                REGION1_TOPLEFT_ANCHOR_POINT.x,
                REGION1_TOPLEFT_ANCHOR_POINT.y);
        Point region1_pointB = new Point(
                REGION1_TOPLEFT_ANCHOR_POINT.x + REGION_WIDTH,
                REGION1_TOPLEFT_ANCHOR_POINT.y + REGION_HEIGHT);

        /*
         * Working variables
         */
        Mat region1_Cb;
        Mat YCrCb = new Mat();
        Mat Cb = new Mat();
        public int avg1;

        // Volatile since accessed by OpMode thread w/o synchronization
        public volatile EasyOpenCVWebcam.SkystoneDeterminationPipeline.RingPosition position = EasyOpenCVWebcam.SkystoneDeterminationPipeline.RingPosition.FOUR;

        /*
         * This function takes the RGB frame, converts to YCrCb,
         * and extracts the Cb channel to the 'Cb' variable
         */
        void inputToCb(Mat input)
        {
            Imgproc.cvtColor(input, YCrCb, Imgproc.COLOR_RGB2YCrCb);
            Core.extractChannel(YCrCb, Cb, 1);
        }

        @Override
        public void init(Mat firstFrame)
        {
            inputToCb(firstFrame);

            region1_Cb = Cb.submat(new Rect(region1_pointA, region1_pointB));
        }

        @Override
        public Mat processFrame(Mat input)
        {
            inputToCb(input);

            avg1 = (int) Core.mean(region1_Cb).val[0];

            Imgproc.rectangle(
                    input, // Buffer to draw on
                    region1_pointA, // First point which defines the rectangle
                    region1_pointB, // Second point which defines the rectangle
                    BLUE, // The color the rectangle is drawn in
                    2); // Thickness of the rectangle lines

            position = EasyOpenCVWebcam.SkystoneDeterminationPipeline.RingPosition.FOUR; // Record our analysis
            if(avg1 > FOUR_RING_THRESHOLD){
                position = EasyOpenCVWebcam.SkystoneDeterminationPipeline.RingPosition.FOUR;
            }else if (avg1 > ONE_RING_THRESHOLD){
                position = EasyOpenCVWebcam.SkystoneDeterminationPipeline.RingPosition.ONE;
            }else{
                position = EasyOpenCVWebcam.SkystoneDeterminationPipeline.RingPosition.NONE;
            }

            Imgproc.rectangle(
                    input, // Buffer to draw on
                    region1_pointA, // First point which defines the rectangle
                    region1_pointB, // Second point which defines the rectangle
                    GREEN, // The color the rectangle is drawn in
                    -1); // Negative thickness means solid fill

            return input;
        }

        public int getAnalysis()
        {
            return avg1;
        }
    }

    class SamplePipeline extends OpenCvPipeline {
        boolean viewportPaused;

        /*
         * NOTE: if you wish to use additional Mat objects in your processing pipeline, it is
         * highly recommended to declare them here as instance variables and re-use them for
         * each invocation of processFrame(), rather than declaring them as new local variables
         * each time through processFrame(). This removes the danger of causing a memory leak
         * by forgetting to call mat.release(), and it also reduces memory pressure by not
         * constantly allocating and freeing large chunks of memory.
         */

        @Override
        public Mat processFrame(Mat input) {
            /*
             * IMPORTANT NOTE: the input Mat that is passed in as a parameter to this method
             * will only dereference to the same image for the duration of this particular
             * invocation of this method. That is, if for some reason you'd like to save a copy
             * of this particular frame for later use, you will need to either clone it or copy
             * it to another Mat.
             */

            /*
             * Draw a simple box around the middle 1/2 of the entire frame
             */
            Imgproc.rectangle(
                    input,
                    new Point(
                            input.cols() / 4,
                            input.rows() / 4),
                    new Point(
                            input.cols() * (3f / 4f),
                            input.rows() * (3f / 4f)),
                    new Scalar(0, 255, 0), 4);

            /**
             * NOTE: to see how to get data from your pipeline to your OpMode as well as how
             * to change which stage of the pipeline is rendered to the viewport when it is
             * tapped, please see {@link PipelineStageSwitchingExample}
             */

            return input;
        }

        @Override
        public void onViewportTapped() {
            /*
             * The viewport (if one was specified in the constructor) can also be dynamically "paused"
             * and "resumed". The primary use case of this is to reduce CPU, memory, and power load
             * when you need your vision pipeline running, but do not require a live preview on the
             * robot controller screen. For instance, this could be useful if you wish to see the live
             * camera preview as you are initializing your robot, but you no longer require the live
             * preview after you have finished your initialization process; pausing the viewport does
             * not stop running your pipeline.
             *
             * Here we demonstrate dynamically pausing/resuming the viewport when the user taps it
             */

            viewportPaused = !viewportPaused;

            if (viewportPaused) {
                webcam.pauseViewport();
            } else {
                webcam.resumeViewport();
            }
        }
    }

    public void driveGyroBackward (double power, double rotations) throws InterruptedException {
        double ticks = rotations * (+1) * TICKS_PER_ROTATION;

        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        double currentPos = 0;
        double leftSideSpeed;
        double rightSideSpeed;


        double target = angles.firstAngle;
        double startPosition = frontLeftMotor.getCurrentPosition();
        linearOp.telemetry.addData("Angle to start: ", angles.firstAngle);
        linearOp.telemetry.update();
        linearOp.sleep(100);
//        while (currentPos < ticks + startPosition && linearOp.opModeIsActive()) {
        while (currentPos < ticks + startPosition && linearOp.opModeIsActive()) {
            angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);


            currentPos = Math.abs(frontLeftMotor.getCurrentPosition());



            leftSideSpeed = power - (angles.firstAngle - target) / 100;            // they need to be different
            rightSideSpeed = power + (angles.firstAngle - target) / 100;

            leftSideSpeed = Range.clip(leftSideSpeed, -1, 1);        // helps prevent out of bounds error
            rightSideSpeed = Range.clip(rightSideSpeed, -1, 1);

            frontLeftMotor.setPower(-leftSideSpeed);
            rearLeftMotor.setPower(-leftSideSpeed);

            frontRightMotor.setPower(-rightSideSpeed);
            rearRightMotor.setPower(-rightSideSpeed);


//            switch (direction) {
//                case "forward":
//                    currentPos = frontLeftMotor.getCurrentPosition();
//                    leftSideSpeed = power + (angles.firstAngle - target) / 100;            // they need to be different
//                    rightSideSpeed = power - (angles.firstAngle - target) / 100;
//
//                    leftSideSpeed = Range.clip(leftSideSpeed, -1, 1);        // helps prevent out of bounds error
//                    rightSideSpeed = Range.clip(rightSideSpeed, -1, 1);
//
//                    frontLeftMotor.setPower(leftSideSpeed);
//                    rearLeftMotor.setPower(leftSideSpeed);
//
//                    frontRightMotor.setPower(rightSideSpeed);
//                    rearRightMotor.setPower(rightSideSpeed);
//                    break;
//                case "backward":
//                    currentPos = -frontLeftMotor.getCurrentPosition();
//                    leftSideSpeed = power - (angles.firstAngle - target) / 100;            // they need to be different
//                    rightSideSpeed = power + (angles.firstAngle - target) / 100;
//
//                    leftSideSpeed = Range.clip(leftSideSpeed, -1, 1);        // helps prevent out of bounds error
//                    rightSideSpeed = Range.clip(rightSideSpeed, -1, 1);
//
//                    frontLeftMotor.setPower(-leftSideSpeed);
//                    rearLeftMotor.setPower(-leftSideSpeed);
//
//                    frontRightMotor.setPower(-rightSideSpeed);
//                    rearRightMotor.setPower(-rightSideSpeed);
//                    break;
//            }
//
//
//
//
//
//            linearOp.telemetry.addData("Left Speed", frontLeftMotor.getPower());
//            linearOp.telemetry.addData("Right Speed", frontRightMotor.getPower());
//            linearOp.telemetry.addData("Distance till destination ", encoders + startPosition - frontLeftMotor.getCurrentPosition());
//            linearOp.telemetry.addData("Current Position", currentPos);
//            linearOp.telemetry.addData("Target Position", target);
//            linearOp.telemetry.addData("Angle: ", angles.firstAngle);
//            linearOp.telemetry.update();
            // missing waiting

            linearOp.idle();
        }

        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        rearLeftMotor.setPower(0);
        rearRightMotor.setPower(0);

        linearOp.idle();

    }


    public void driveGyroForward (double power, double rotations) throws InterruptedException {
        double ticks = rotations * (1) * TICKS_PER_ROTATION;

        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        double currentPos = 0;
        double leftSideSpeed;
        double rightSideSpeed;


        double target = angles.firstAngle;
        double startPosition = frontLeftMotor.getCurrentPosition();
          linearOp.telemetry.addData("Angle to start: ", angles.firstAngle);
          linearOp.telemetry.update();
        linearOp.sleep(100);
        while (currentPos < ticks + startPosition && linearOp.opModeIsActive()) {

            angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);


            currentPos = Math.abs(frontLeftMotor.getCurrentPosition());



            leftSideSpeed = power - (angles.firstAngle - target) / 100;            // they need to be different
            rightSideSpeed = power + (angles.firstAngle - target) / 100;

            leftSideSpeed = Range.clip(leftSideSpeed, -1, 1);        // helps prevent out of bounds error
            rightSideSpeed = Range.clip(rightSideSpeed, -1, 1);

            frontLeftMotor.setPower(+leftSideSpeed);
            rearLeftMotor.setPower(+leftSideSpeed);

            frontRightMotor.setPower(+rightSideSpeed);
            rearRightMotor.setPower(+rightSideSpeed);


//            switch (direction) {
//                case "forward":
//                    currentPos = frontLeftMotor.getCurrentPosition();
//                    leftSideSpeed = power + (angles.firstAngle - target) / 100;            // they need to be different
//                    rightSideSpeed = power - (angles.firstAngle - target) / 100;
//
//                    leftSideSpeed = Range.clip(leftSideSpeed, -1, 1);        // helps prevent out of bounds error
//                    rightSideSpeed = Range.clip(rightSideSpeed, -1, 1);
//
//                    frontLeftMotor.setPower(leftSideSpeed);
//                    rearLeftMotor.setPower(leftSideSpeed);
//
//                    frontRightMotor.setPower(rightSideSpeed);
//                    rearRightMotor.setPower(rightSideSpeed);
//                    break;
//                case "backward":
//                    currentPos = -frontLeftMotor.getCurrentPosition();
//                    leftSideSpeed = power - (angles.firstAngle - target) / 100;            // they need to be different
//                    rightSideSpeed = power + (angles.firstAngle - target) / 100;
//
//                    leftSideSpeed = Range.clip(leftSideSpeed, -1, 1);        // helps prevent out of bounds error
//                    rightSideSpeed = Range.clip(rightSideSpeed, -1, 1);
//
//                    frontLeftMotor.setPower(-leftSideSpeed);
//                    rearLeftMotor.setPower(-leftSideSpeed);
//
//                    frontRightMotor.setPower(-rightSideSpeed);
//                    rearRightMotor.setPower(-rightSideSpeed);
//                    break;
//            }
//
//
//
//
//
//            linearOp.telemetry.addData("Left Speed", frontLeftMotor.getPower());
//            linearOp.telemetry.addData("Right Speed", frontRightMotor.getPower());
//            linearOp.telemetry.addData("Distance till destination ", ticks + startPosition - frontLeftMotor.getCurrentPosition());
//            linearOp.telemetry.addData("Current Position", currentPos);
//            linearOp.telemetry.addData("Target Position", target);
//            linearOp.telemetry.addData("Angle: ", angles.firstAngle);
//            linearOp.telemetry.update();
            // missing waiting

            linearOp.idle();
        }

        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        rearLeftMotor.setPower(0);
        rearRightMotor.setPower(0);

        linearOp.idle();

    }

    public void driveGyroStrafe (double power, double rotations, String direction) throws InterruptedException {
        double ticks = 0;
        ticks = rotations * TICKS_PER_ROTATION;
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        double currentPos = 0;
        double frontLeftSpeed;
        double frontRightSpeed;
        double rearLeftSpeed;
        double rearRightSpeed;


        double target = angles.firstAngle;
        double startPosition = frontLeftMotor.getCurrentPosition();
        linearOp.telemetry.addData("Angle to start: ", angles.firstAngle);
        linearOp.telemetry.update();
        linearOp.sleep(2000);
        while (currentPos < ticks + startPosition && linearOp.opModeIsActive()) {

            angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);


            currentPos = Math.abs(frontLeftMotor.getCurrentPosition());

            switch (direction) {
                case "left":
                    frontLeftSpeed = power - (angles.firstAngle - target) / 100;            // they need to be different
                    frontRightSpeed = power - (angles.firstAngle - target) / 100;
                    rearLeftSpeed = power + (angles.firstAngle - target) / 100;            // they need to be different
                    rearRightSpeed = power + (angles.firstAngle - target) / 100;

                    frontLeftSpeed = Range.clip(frontLeftSpeed, -1, 1);        // helps prevent out of bounds error
                    frontRightSpeed = Range.clip(frontRightSpeed, -1, 1);
                    rearLeftSpeed = Range.clip(rearLeftSpeed, -1, 1);        // helps prevent out of bounds error
                    rearRightSpeed = Range.clip(rearRightSpeed, -1, 1);

                    frontLeftMotor.setPower(-frontLeftSpeed);
                    frontRightMotor.setPower(frontRightSpeed);

                    rearLeftMotor.setPower(rearLeftSpeed);
                    rearRightMotor.setPower(-rearRightSpeed);
                    break;
                case "right":
                    frontLeftSpeed = power + (angles.firstAngle - target) / 100;            // they need to be different
                    frontRightSpeed = power + (angles.firstAngle - target) / 100;
                    rearLeftSpeed = power - (angles.firstAngle - target) / 100;            // they need to be different
                    rearRightSpeed = power - (angles.firstAngle - target) / 100;

                    frontLeftSpeed = Range.clip(frontLeftSpeed, -1, 1);        // helps prevent out of bounds error
                    frontRightSpeed = Range.clip(frontRightSpeed, -1, 1);
                    rearLeftSpeed = Range.clip(rearLeftSpeed, -1, 1);        // helps prevent out of bounds error
                    rearRightSpeed = Range.clip(rearRightSpeed, -1, 1);

                    frontLeftMotor.setPower(frontLeftSpeed);
                    frontRightMotor.setPower(-frontRightSpeed);

                    rearLeftMotor.setPower(-rearLeftSpeed);
                    rearRightMotor.setPower(rearRightSpeed);
                    break;
            }



//            linearOp.telemetry.addData("Left Speed", frontLeftMotor.getPower());
//            linearOp.telemetry.addData("Right Speed", frontRightMotor.getPower());
//            linearOp.telemetry.addData("Distance till destination ", encoders + startPosition - frontLeftMotor.getCurrentPosition());
//            linearOp.telemetry.addData("Current Position", currentPos);
//            linearOp.telemetry.addData("Target Position", target);
//            linearOp.telemetry.addData("Angle: ", angles.firstAngle);
//
//            linearOp.telemetry.update();

            // missing waiting
            linearOp.idle();
        }

        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        rearLeftMotor.setPower(0);
        rearRightMotor.setPower(0);

        linearOp.idle();

    }



    public void driveGyroStrafeAngle (double power, double rotations, String direction, double angle) throws InterruptedException {
        double ticks = 0;
        ticks = rotations * TICKS_PER_ROTATION;

        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        double currentPos = 0;
        double frontLeftSpeed;
        double frontRightSpeed;
        double rearLeftSpeed;
        double rearRightSpeed;


        double target = angle;
        double startPosition = frontLeftMotor.getCurrentPosition();
        linearOp.telemetry.addData("Angle to start: ", angles.firstAngle);
        linearOp.telemetry.update();
        linearOp.sleep(2000);
        while (currentPos < ticks + startPosition && linearOp.opModeIsActive()) {

            angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);


            currentPos = Math.abs(frontLeftMotor.getCurrentPosition());

            switch (direction) {
                case "left":
                    frontLeftSpeed = power - (angles.firstAngle - target) / 100;            // they need to be different
                    frontRightSpeed = power - (angles.firstAngle - target) / 100;
                    rearLeftSpeed = power + (angles.firstAngle - target) / 100;            // they need to be different
                    rearRightSpeed = power + (angles.firstAngle - target) / 100;

                    frontLeftSpeed = Range.clip(frontLeftSpeed, -1, 1);        // helps prevent out of bounds error
                    frontRightSpeed = Range.clip(frontRightSpeed, -1, 1);
                    rearLeftSpeed = Range.clip(rearLeftSpeed, -1, 1);        // helps prevent out of bounds error
                    rearRightSpeed = Range.clip(rearRightSpeed, -1, 1);

                    frontLeftMotor.setPower(-frontLeftSpeed);
                    frontRightMotor.setPower(frontRightSpeed);

                    rearLeftMotor.setPower(rearLeftSpeed);
                    rearRightMotor.setPower(-rearRightSpeed);
                    break;
                case "right":
                    frontLeftSpeed = power + (angles.firstAngle - target) / 100;            // they need to be different
                    frontRightSpeed = power + (angles.firstAngle - target) / 100;
                    rearLeftSpeed = power - (angles.firstAngle - target) / 100;            // they need to be different
                    rearRightSpeed = power - (angles.firstAngle - target) / 100;

                    frontLeftSpeed = Range.clip(frontLeftSpeed, -1, 1);        // helps prevent out of bounds error
                    frontRightSpeed = Range.clip(frontRightSpeed, -1, 1);
                    rearLeftSpeed = Range.clip(rearLeftSpeed, -1, 1);        // helps prevent out of bounds error
                    rearRightSpeed = Range.clip(rearRightSpeed, -1, 1);

                    frontLeftMotor.setPower(frontLeftSpeed);
                    frontRightMotor.setPower(-frontRightSpeed);

                    rearLeftMotor.setPower(-rearLeftSpeed);
                    rearRightMotor.setPower(rearRightSpeed);
                    break;
            }



//           linearOp.telemetry.addData("Left Speed", frontLeftMotor.getPower());
//           linearOp.telemetry.addData("Right Speed", frontRightMotor.getPower());
//           linearOp.telemetry.addData("Distance till destination ", encoders + startPosition - frontLeftMotor.getCurrentPosition());
//           linearOp.telemetry.addData("Current Position", currentPos);
//           linearOp.telemetry.addData("Target Position", target);
//           linearOp.telemetry.addData("Angle: ", angles.firstAngle);
//
//           linearOp.telemetry.update();

            // missing waiting
            linearOp.idle();
        }

        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        rearLeftMotor.setPower(0);
        rearRightMotor.setPower(0);

        linearOp.idle();

    }

}
