package org.firstinspires.ftc.teamcode.Outreach.OutreachRanger.Robots;

//import android.graphics.Color;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.internal.system.Deadline;
import org.firstinspires.ftc.teamcode.Compitition.ZCompititionUltimateGoal.DriveTrains.MecanumDrive;
import org.firstinspires.ftc.teamcode.Compitition.ZCompititionUltimateGoal.Modules.EasyOpenCVWebcam;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import org.openftc.easyopencv.OpenCvPipeline;


public class OutreachRangerBot extends MecanumDrive {

    //hardware constructors
    public HardwareMap hwBot  =  null;


    public VoltageSensor voltageSensor = null;

    public double launchCoefficient;

//GYRO INITIALIZATION

    public BNO055IMU imu;
    public Orientation angles;
    public Acceleration gravity;
    public final double SPEED = .3;
    public final double TOLERANCE = .4;

    // Color and Distance Hardware & Variables
    public float hsvValues[] = {0F, 0F, 0F};
    public final double SCALE_FACTOR = 1;


    public Servo ServoRingPusher = null;
    public Servo RingMag = null;
    public CRServo IntakeCorrector = null;

    //Launcher Motor:
    public DcMotor IntakeMotor = null;
    public double RingPushPos = 0.26;
    //.196 before (Mar 3, 2021 3:40pm)
    public double RingPullPos = 0.40;
    //.460 before (Mar 3, 2021 3:40pm)
    public double RingMagUpAuto = 0.217;

    public DcMotorEx launcherMotor1 = null;
    public DcMotorEx launcherMotor2 = null;

    public int rapidFireRing = 0;
    public int rapidPushTimer = 150; //200 before
    public int rapidPullTimer = 175; //150 before
    public boolean launchModePush = false;
    public boolean launchModePull = false;

    public RevBlinkinLedDriver blinkinLedDriver;
    public RevBlinkinLedDriver.BlinkinPattern pattern;

    public ElapsedTime wobbleArmTimer;
    public ElapsedTime ringPusherTimer;


    public void initRobot(HardwareMap hardwareMap, String startPosition, String mode){

        hwBot = hardwareMap;


        voltageSensor = hardwareMap.voltageSensor.iterator().next();
        launchCoefficient = 12 / voltageSensor.getVoltage();

        RingMag = hwBot.get(Servo.class, "ring_mag");
        ServoRingPusher = hwBot.get(Servo.class, "servo_ring_pusher");

        if (mode.equals("auto")){
            RingMagUp();
            RingPush();
        }

        switch (startPosition) {
            case "BlueLeft":
                break;
            case "BlueRight":
                break;
            case "RedLeft":
                break;
            case "RedRight":
                break;



        }

        blinkinLedDriver = hwBot.get(RevBlinkinLedDriver.class, "Blinkin");
        pattern = RevBlinkinLedDriver.BlinkinPattern.BREATH_GRAY;
        blinkinLedDriver.setPattern(pattern);

        IntakeCorrector = hardwareMap.crservo.get ("intake_corrector");
        IntakeCorrector.setDirection(DcMotorSimple.Direction.REVERSE);
        IntakeCorrector.setPower(0);



        // define motors for robot
        frontLeftMotor=hwBot.dcMotor.get("front_left_motor");
        frontRightMotor=hwBot.dcMotor.get("front_right_motor");
        rearLeftMotor = hwBot.dcMotor.get("rear_left_motor");
        rearRightMotor = hwBot.dcMotor.get("rear_right_motor");


        IntakeMotor = hwBot.dcMotor.get("intake_motor");


        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        rearLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotor.Direction.FORWARD);
        rearRightMotor.setDirection(DcMotor.Direction.FORWARD);

        launcherMotor1 = hwBot.get(DcMotorEx.class, "launcher_motor_1");
        launcherMotor1.setDirection(DcMotorSimple.Direction.REVERSE);
        launcherMotor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        launcherMotor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        launcherMotor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        launcherMotor2 = hwBot.get(DcMotorEx.class, "launcher_motor_2");
        launcherMotor2.setDirection(DcMotorSimple.Direction.REVERSE);
        launcherMotor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        launcherMotor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        launcherMotor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        IntakeMotor.setDirection(DcMotor.Direction.REVERSE);
        IntakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //Initialize Motor Run Mode for Robot
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

//      Timers
        wobbleArmTimer = new ElapsedTime();
        wobbleArmTimer.reset();

        ringPusherTimer = new ElapsedTime();
        ringPusherTimer.reset();



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


//

    public void LauncherOn(double power) {
        launcherMotor1.setVelocity(power);
        launcherMotor2.setVelocity(power);
     }
    public void LauncherOff(double power) {
        launcherMotor1.setPower(power);
        launcherMotor2.setPower(power);
    }

    public void RingFullAuto(){
//        sleeeeeeeeeeaaaaaspoprts its in the game3 = autonomousmonkey
//        what are these random words?
    }


    public void FirstLaunch(){
        RingPull();
        ringPusherTimer.reset();
        launchModePush = true;
        launchModePull = false;
        rapidFireRing++;
    }

    public void RingPush() {
        ServoRingPusher.setPosition(RingPushPos);
    }
    //        Pushes ring to launch!  Zoom Zoom
    public void RingPull() {
        ServoRingPusher.setPosition(RingPullPos);
    }
    public void RingMagUp(){
        RingMag.setPosition(RingMagUpAuto);
    }

    public void gyroCorrection (double speed, double angle) {

        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        linearOp.telemetry.addData("current angle to start: ", angles.firstAngle);
        linearOp.telemetry.update();

        if (angles.firstAngle >= angle + TOLERANCE && linearOp.opModeIsActive()) {
            while (angles.firstAngle >=  angle + TOLERANCE && linearOp.opModeIsActive()) {
                rotateRight(speed);
                angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
                linearOp.telemetry.addData("current angle > : ", angles.firstAngle);
                linearOp.telemetry.update();
            }
        }
        else if (angles.firstAngle <= angle - TOLERANCE && linearOp.opModeIsActive()) {
            while (angles.firstAngle <= angle - TOLERANCE && linearOp.opModeIsActive()) {
                rotateLeft(speed);
                angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
                linearOp.telemetry.addData("current angle < : ", angles.firstAngle);
                linearOp.telemetry.update();
            }
        }
        stopMotors();

        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
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
        linearOp.sleep(100);

        while (currentPos < ticks + startPosition && linearOp.opModeIsActive()) {

            angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

            currentPos = frontLeftMotor.getCurrentPosition();
            leftSideSpeed = power + (angles.firstAngle - target) / 100;            // they need to be different
            rightSideSpeed = power - (angles.firstAngle - target) / 100;

            leftSideSpeed = Range.clip(leftSideSpeed, -1, 1);        // helps prevent out of bounds error
            rightSideSpeed = Range.clip(rightSideSpeed, -1, 1);

            frontLeftMotor.setPower(leftSideSpeed);
            rearLeftMotor.setPower(leftSideSpeed);

            frontRightMotor.setPower(rightSideSpeed);
            rearRightMotor.setPower(rightSideSpeed);

            linearOp.idle();
        }

        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        rearLeftMotor.setPower(0);
        rearRightMotor.setPower(0);

        linearOp.idle();

    }


    public void driveGyroStraight (int encoders, double power) throws InterruptedException {

        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        double currentPos = 0;
        double leftSideSpeed;
        double rightSideSpeed;


        double target = angles.firstAngle;
        double startPosition = frontLeftMotor.getCurrentPosition();
        linearOp.sleep(100);
        while (currentPos < encoders + startPosition && linearOp.opModeIsActive()) {

            angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);


            currentPos = Math.abs(frontLeftMotor.getCurrentPosition());

                          currentPos = frontLeftMotor.getCurrentPosition();
                    leftSideSpeed = power + (angles.firstAngle - target) / 100;            // they need to be different
                    rightSideSpeed = power - (angles.firstAngle - target) / 100;

                    leftSideSpeed = Range.clip(leftSideSpeed, -1, 1);        // helps prevent out of bounds error
                    rightSideSpeed = Range.clip(rightSideSpeed, -1, 1);

                    frontLeftMotor.setPower(leftSideSpeed);
                    rearLeftMotor.setPower(leftSideSpeed);

                    frontRightMotor.setPower(rightSideSpeed);
                    rearRightMotor.setPower(rightSideSpeed);



            linearOp.idle();
        }

        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        rearLeftMotor.setPower(0);
        rearRightMotor.setPower(0);

        linearOp.idle();
//
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
//        linearOp.sleep(2000);
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



            linearOp.idle();
        }

        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        rearLeftMotor.setPower(0);
        rearRightMotor.setPower(0);

        linearOp.idle();



    }




}
