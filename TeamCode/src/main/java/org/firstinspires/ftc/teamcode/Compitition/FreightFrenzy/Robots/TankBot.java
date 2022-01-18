package org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Robots;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.DriveTrains.TankTreadDrive;
import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.mechanisms.CompContourPipeline;
import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.mechanisms.TSELocation;
import org.opencv.core.Scalar;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.xml.sax.helpers.NamespaceSupport;

public class TankBot extends TankTreadDrive {

    public HardwareMap hwBot = null;

    public DcMotor DuckSpinner;
    public DcMotor Lyft;
    public DcMotor intake;

    public double duckSpinnerPower = 0.35;

    public double LyftExtendPower = 1.0;
    public double LyftRetractPower = -1.0;

    public Servo boxHolder = null;

    // Higher value = gate higher
    // Lower value == gate goes down more.
    public double boxHolder_Up = .37;
    public double boxHolder_Down = 0.46;
    public double boxHolder_Release = 0.51;
//    public CRServo LyftServo = null;

    // WEBCAM VARIABLES - EMMA
    //declaring pipeline
    CompContourPipeline myPipeline;
    private OpenCvCamera webcam;
    private static final int CAMERA_WIDTH = 320; // width  of wanted camera resolution
    private static final int CAMERA_HEIGHT = 240; // height of wanted camera resolution
    // WEBCAM - COLOR RANGE
    public static Scalar scalarLowerYCrCb = new Scalar(0.0, 0.0, 0.0);
    public static Scalar scalarUpperYCrCb = new Scalar(255.0, 150.0, 120.0);
    //enum / detecting barcode
    TSELocation barcode;


    public void initRobot(HardwareMap hardwareMap) {
        hwBot = hardwareMap;

        timer = new ElapsedTime();

        leftMotorA = hwBot.get(DcMotorEx.class, "left_motor_a");
        leftMotorB = hwBot.get(DcMotorEx.class, "left_motor_b");
        rightMotorA = hwBot.get(DcMotorEx.class, "right_motor_a");
        rightMotorB = hwBot.get(DcMotorEx.class, "right_motor_b");

        leftMotorA.setDirection(DcMotorEx.Direction.FORWARD);
        leftMotorB.setDirection(DcMotorEx.Direction.FORWARD);
        rightMotorA.setDirection(DcMotorEx.Direction.REVERSE);
        rightMotorB.setDirection(DcMotorEx.Direction.REVERSE);

        leftMotorA.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightMotorA.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        leftMotorB.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightMotorB.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        Lyft = hwBot.get(DcMotorEx.class, "lyft_extender");
        Lyft.setDirection(DcMotorEx.Direction.REVERSE);
        DuckSpinner = hwBot.get(DcMotorEx.class, "duck_spinner");
        DuckSpinner.setDirection(DcMotorEx.Direction.FORWARD);
        intake = hardwareMap.dcMotor.get("intake");
        intake.setDirection(DcMotor.Direction.FORWARD);

        boxHolder = hwBot.get(Servo.class, "box_holder");
        setBoxHolder_Up();

    }

    public void duckspinclockwise() {
        DuckSpinner.setPower(duckSpinnerPower);
    }

    public void duckspincounterclockwise() {
        DuckSpinner.setPower(-duckSpinnerPower);
    }

    public void duckspinstop() {
        DuckSpinner.setPower(0);
    }

    public void Intake(double speed) {
        intake.setPower(speed);
    }

    public void LyftExtend()
    {
        Lyft.setPower(LyftExtendPower);
    }

    public void LyftRetract () {
        Lyft.setPower(LyftRetractPower);
    }

    public void LyftStopMotors () {
        Lyft.setPower(0);
    }

    public void setBoxHolder_Up() {
        boxHolder.setPosition(boxHolder_Up);
    }

    public void setBoxHolder_Down() {
        boxHolder.setPosition(boxHolder_Down);
    }

    public void setBoxHolder_Release () {
        boxHolder.setPosition(boxHolder_Release);
    }


    public static final int CLOSE_TIME_THRESHOLD = 500;
    public static final int OPEN_TIME_THRESHOLD = 1000;

    double power;
    double powerControl = 0.3 ;

    ElapsedTime timer;



    //emma
//    public void initWebcam() {
//        //OPENCV WEBCAM
//        int cameraMonitorViewId = hwBot.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hwBot.appContext.getPackageName());
//
//        webcam = OpenCvCameraFactory.getInstance().createWebcam(hwBot.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
//        //OPENCV PIPELINE
//        webcam.setPipeline(myPipeline = new CompContourPipeline());
//        // CONFIGURATION OF PIPELINE
//        myPipeline.ConfigurePipeline(30, 30, 30, 30, CAMERA_WIDTH, CAMERA_HEIGHT);
//        myPipeline.ConfigureScalarLower(scalarLowerYCrCb.val[0], scalarLowerYCrCb.val[1], scalarLowerYCrCb.val[2]);
//        myPipeline.ConfigureScalarUpper(scalarUpperYCrCb.val[0], scalarUpperYCrCb.val[1], scalarUpperYCrCb.val[2]);
//
//        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
//            @Override           //why is this override / does it need to disappear for auto
//            public void onOpened() {
//                webcam.startStreaming(CAMERA_WIDTH, CAMERA_HEIGHT, OpenCvCameraRotation.UPRIGHT);
//            }
//
//            @Override       // why is this ovrride / does it need to disappear for auto
//            public void onError(int errorCode) {
//
//            }
//        });
//    }

    public void detectBarcode() {
        //for testing
        linearOp.telemetry.addData("RectArea: ", myPipeline.getRectArea());
        linearOp.telemetry.addData("Rect Midpoint X", myPipeline.getRectMidpointX());
        linearOp.telemetry.addData("Rect Midpoint Y", myPipeline.getRectMidpointY());
        linearOp.telemetry.update();
        //detection of TSE/duck
        if (myPipeline.getRectArea() > 2000) {      //values will probably need to be changed
            if (myPipeline.getRectMidpointX() > 400) {
                barcode = TSELocation.barcode1;
            } else if (myPipeline.getRectMidpointX() > 200) {
                barcode = TSELocation.barcode2;
            } else {
                barcode = TSELocation.barcode3;
            }
        }
    }


    public void senseLyftExtend () {
        timer.reset();
        //was in the compound conditional
//        hsvValues[0] < BLUE_THRESHOLD_HUE &&
        while (timer.milliseconds() < CLOSE_TIME_THRESHOLD && linearOp.opModeIsActive()) {
//            Color.RGBToHSV((int) (senseLyftColor.red() * SCALE_FACTOR),
//                    (int) (senseLyftColor.green() * SCALE_FACTOR),
//                    (int) (senseLyftColor.blue() * SCALE_FACTOR),
//                    hsvValues);
            LyftExtend();
//            linearOp.telemetry.addLine("CLOSING EXTENDER");
//            linearOp.telemetry.addData("Hue", hsvValues[0]);
//            linearOp.telemetry.addData("TIME (ms)", timer.milliseconds());
//            linearOp.telemetry.update();
        }
        Lyft.setPower(0);


    }

    public void senseLyftColapse () {
        timer.reset();
        //was in the compound conditional
//        hsvValues[0] < BLUE_THRESHOLD_HUE &&
        while (timer.milliseconds() < CLOSE_TIME_THRESHOLD && linearOp.opModeIsActive()) {
//            Color.RGBToHSV((int) (senseLyftColor.red() * SCALE_FACTOR),
//                    (int) (senseLyftColor.green() * SCALE_FACTOR),
//                    (int) (senseLyftColor.blue() * SCALE_FACTOR),
//                    hsvValues);
            LyftRetract();
//            linearOp.telemetry.addLine("CLOSING EXTENDER");
//            linearOp.telemetry.addData("Hue", hsvValues[0]);
//            linearOp.telemetry.addData("TIME (ms)", timer.milliseconds());
//            linearOp.telemetry.update();
        }
        Lyft.setPower(0);
    }

}
