//package org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Robots;
//
//import com.qualcomm.robotcore.hardware.CRServo;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DcMotorEx;
//import com.qualcomm.robotcore.hardware.DcMotorSimple;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//
//import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.DriveTrains.EightWD;
//
//public class EightWheelBot extends EightWD {
//
//    public HardwareMap hwBot = null;
//
//    public CRServo DuckTurner = null;
//
//
//    public EightWheelBot () {
//
//
//    }
//
//    public void initRobot(HardwareMap hardwareMap){
//        hwBot = hardwareMap;
//
//
//        leftMotorA = hwBot.get(DcMotorEx.class, "left_motor_a");
//        leftMotorB = hwBot.get(DcMotorEx.class, "left_motor_b");
//        rightMotorA = hwBot.get(DcMotorEx.class, "right_motor_a");
//        rightMotorB = hwBot.get(DcMotorEx.class, "right_motor_b");
//
//
//        leftMotorA.setDirection(DcMotor.Direction.REVERSE);
//
//
//        leftMotorA.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        leftMotorA.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//
//        //calling duck carasell spinner named "Duck_Turner"
//        DuckTurner = hardwareMap.crservo.get ("Duck_Turner");
//        DuckTurner.setDirection(DcMotorSimple.Direction.REVERSE);
//        DuckTurner.setPower(0);
//    }
//    public void SpinrightDuckTurner(){
//        DuckTurner.setPower(1);
//    }
//
//    public void SpinleftDuckTurner(){
//        DuckTurner.setPower(-1);
//    }
//
//    public void StopDuckTurner(){
//        DuckTurner.setPower(0);
//    }
//
//
//
//}


package org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Robots;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Controls.mechanisms.CompContourPipeline;
import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Controls.mechanisms.TSELocation;
import org.opencv.core.Scalar;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.DriveTrains.EightWD;

public class EightWheelBot extends EightWD {

    public HardwareMap hwBot = null;


    public CRServo DuckTurnerright = null;
    public CRServo DuckTurnerleft = null;

    public DcMotor LyftExtender;

    public Servo boxHolder2 = null;

    // Higher value = gate higher
    // Lower value == gate goes down more.
    public double boxHolder2up = 0.1;

    public double boxHolder2down = 0.4;
    public CRServo LyftServo = null;

// ^ !careful while changing! ^

    public DcMotor intake;

    public DcMotor lyft;

    double power;
    double powerControl = 0.8;

    public ColorSensor senseLyftColor;

    float hsvValues[] = {0F, 0F, 0F};

    // values is a reference to the hsvValues array.
    final float values[] = hsvValues;

    // sometimes it helps to multiply the raw RGB values with a scale factor
    // to amplify/attentuate the measured values.
    final double SCALE_FACTOR = 255;

    /*
     HUE values for the linear extension slide to know when to stop.
     On testing in BNI lab:

    HUE:
    Blue Gaffers Tape: 220 [closing!]
    Red Gaffers Tape: 20   [opening!]
    No Gaffers Tape: 120

     */
    final double RED_THRESHOLD_HUE = 60;
    final double BLUE_THRESHOLD_HUE = 180;

    //time to close before STOP
    // 1000 == 1 second
    public static final int CLOSE_TIME_THRESHOLD = 500;
    public static final int OPEN_TIME_THRESHOLD = 1000;

    ElapsedTime timer;

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


    public EightWheelBot() {


    }

    public void initRobot(HardwareMap hardwareMap) {
        hwBot = hardwareMap;


        LyftExtender = hwBot.get(DcMotorEx.class, "lyft_extender");

        leftMotorA = hwBot.get(DcMotorEx.class, "left_motor_a");
        leftMotorB = hwBot.get(DcMotorEx.class, "left_motor_b");
        rightMotorA = hwBot.get(DcMotorEx.class, "right_motor_a");
        rightMotorB = hwBot.get(DcMotorEx.class, "right_motor_b");

//        leftMotorA.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        LyftExtender.setDirection(DcMotorEx.Direction.FORWARD);

        leftMotorA.setDirection(DcMotorEx.Direction.FORWARD);
        leftMotorB.setDirection(DcMotorEx.Direction.FORWARD);
        rightMotorA.setDirection(DcMotorEx.Direction.REVERSE);
        rightMotorB.setDirection(DcMotorEx.Direction.REVERSE);

//        intakeLyft = hwBot.get(DcMotorEx.class,"Intake_Lyft");
        intake = hardwareMap.dcMotor.get("intake");
//        intake
//        motor = hardwareMap.dcMotor.get("motor");


        intake.setDirection(DcMotor.Direction.FORWARD);
//        intakeLyft.setDirection(DcMotorEx.Direction.FORWARD);

//        rightMotorA.setDirection(DcMotorSimple.Direction.FORWARD);


//Needed to fix this - only A was here, so "a" was having issues.
//        Commented these out and works:
//        leftMotorA.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        leftMotorA.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

//        setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        Causing issues 0 not sure why yet.

        LyftExtender.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        leftMotorA.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightMotorA.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        leftMotorB.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightMotorB.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        intakeLyft.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        DuckTurnerleft = hardwareMap.crservo.get("duck_turner_left");
        DuckTurnerleft.setDirection(DcMotorSimple.Direction.REVERSE);
        DuckTurnerleft.setPower(0);

        boxHolder2 = hwBot.get(Servo.class, "box_holder");
        boxHolder2.setPosition(boxHolder2up);

        DuckTurnerright = hardwareMap.crservo.get("duck_turner_right");
        DuckTurnerright.setDirection(DcMotorSimple.Direction.REVERSE);
        DuckTurnerright.setPower(0);


        senseLyftColor = hardwareMap.get(ColorSensor.class, "sensor_color_distance_1");

        timer = new ElapsedTime();


    }

    public void setboxHolder2up() {
        boxHolder2.setPosition(boxHolder2up);
    }

    public void setboxHolder2down() {
        boxHolder2.setPosition(boxHolder2down);
    }


    public void SpinDuckBRight() {
        DuckTurnerleft.setPower(-1);
    }

    public void SpinDuckBLeft() {
        DuckTurnerleft.setPower(1);
    }

    public void SpinDuckALeft() {
        DuckTurnerright.setPower(1);
    }

    public void SpinDuckARight() {
        DuckTurnerright.setPower(-1);
    }

    public void StopSpinningDuckRight() {
        DuckTurnerright.setPower(0);
    }

    public void StopSpinningDuckLeft() {
        DuckTurnerleft.setPower(0);
    }

    public void Intake(double speed) {
        intake.setPower(speed);
    }

    public void LyftUp() {
        LyftServo.setPower(1);
    }

    public void LyftDown() {
        LyftServo.setPower(1);
    }

    public void Lyft(double speed) {
        intake.setPower(speed);
    }

    public void LyftExtend(double speed) {
        LyftExtender.setPower(speed);
    }

    public void LyftRetract(double speed) {
        LyftExtender.setPower(-speed);
    }

    public void SpinIntake(double speed) {
        intake.setPower(1);
    }

    public void StopIntake(double speed) {
        intake.setPower(0);
    }

    public void ReverseIntake(double speed) {
        intake.setPower(-1);
    }

    public void senseLyftExtend() {
        timer.reset();
        while (timer.milliseconds() < OPEN_TIME_THRESHOLD && linearOp.opModeIsActive()) {
//            Color.RGBToHSV((int) (senseLyftColor.red() * SCALE_FACTOR),
//                    (int) (senseLyftColor.green() * SCALE_FACTOR),
//                    (int) (senseLyftColor.blue() * SCALE_FACTOR),
//                    hsvValues);
            power = powerControl;
            LyftExtender.setPower(power);
            linearOp.telemetry.addLine("OPENING EXTENDER");
            linearOp.telemetry.addData("Hue", hsvValues[0]);
            linearOp.telemetry.addData("TIME (ms)", timer.milliseconds());
            linearOp.telemetry.update();
        }
        LyftExtender.setPower(0);
    }

    public void senseLyftcolapse() {
        timer.reset();
        //was in the compound conditional
//        hsvValues[0] < BLUE_THRESHOLD_HUE &&
        while (timer.milliseconds() < CLOSE_TIME_THRESHOLD && linearOp.opModeIsActive()) {
//            Color.RGBToHSV((int) (senseLyftColor.red() * SCALE_FACTOR),
//                    (int) (senseLyftColor.green() * SCALE_FACTOR),
//                    (int) (senseLyftColor.blue() * SCALE_FACTOR),
//                    hsvValues);
            power = -powerControl;
            LyftExtender.setPower(power);
            linearOp.telemetry.addLine("CLOSING EXTENDER");
            linearOp.telemetry.addData("Hue", hsvValues[0]);
            linearOp.telemetry.addData("TIME (ms)", timer.milliseconds());
            linearOp.telemetry.update();
        }
        LyftExtender.setPower(0);
    }

    //emma
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
            @Override           //why is this override / does it need to disappear for auto
            public void onOpened() {
                webcam.startStreaming(CAMERA_WIDTH, CAMERA_HEIGHT, OpenCvCameraRotation.UPRIGHT);
            }

            @Override       // why is this ovrride / does it need to disappear for auto
            public void onError(int errorCode) {

            }
        });
    }

    public void detectBarcode() {
        //for testing
        linearOp.telemetry.addData("RectArea: ", myPipeline.getRectArea());
        linearOp.telemetry.addData("Rect Midpoint X", myPipeline.getRectMidpointX());
        linearOp.telemetry.addData("Rect Midpoint Y", myPipeline.getRectMidpointY());
        linearOp.telemetry.update();
        //detection
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
}

