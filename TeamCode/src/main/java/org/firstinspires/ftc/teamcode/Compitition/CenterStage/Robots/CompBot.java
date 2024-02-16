package org.firstinspires.ftc.teamcode.Compitition.CenterStage.Robots;

import android.graphics.drawable.GradientDrawable;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.Compitition.CenterStage.Drivetrains.MecanumDrive;
import org.firstinspires.ftc.teamcode.Compitition.CenterStage.RoadRunner.util.Encoder;
import org.firstinspires.ftc.teamcode.Lab.Sensor_MagneticSensor;
import org.firstinspires.ftc.teamcode.Lab.SensorTouchTester;

public class CompBot extends MecanumDrive {

        public HardwareMap hwBot = null;

        public DcMotor viperSlideRight = null;
        public DcMotor viperSlideLeft = null;
        public DcMotor wormgearRight = null;
//        public DcMotor wormgearLeft = null;
        public DcMotor endgameArm = null;
        public Servo endgameArmRotator = null;
        public Servo pixelRotatorRight = null;

        public Servo pixelRotatorLeft = null;

//        public DcMotor pixelRotatorButThisTimeItsAMotor = null;

        public Servo pixelClawLeft = null;
        public Servo pixelClawRight = null;

//         public   TouchSensor magSensor1;

        public static final double TICKS_PER_ROTATION_WORMGEAR = 384.5;

        public DcMotor planeLauncher = null;

        public Servo planeLauncherServo = null;

        public DistanceSensor pixelDistanceSensor1;
        public DistanceSensor pixelDistanceSensor2;
        public ElapsedTime currentTime = new ElapsedTime();

        public ElapsedTime upTimer = new ElapsedTime();
        public ElapsedTime downTimer = new ElapsedTime();

        RevBlinkinLedDriver blinkinLedDriver;
        RevBlinkinLedDriver.BlinkinPattern pattern;

        RevBlinkinLedDriver blinkinLedDriver2;
        RevBlinkinLedDriver.BlinkinPattern pattern2;
        RevHubOrientationOnRobot.LogoFacingDirection logoDirection = RevHubOrientationOnRobot.LogoFacingDirection.RIGHT;
        RevHubOrientationOnRobot.UsbFacingDirection  usbDirection  = RevHubOrientationOnRobot.UsbFacingDirection.UP;
        RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot(logoDirection, usbDirection);


        public CompBot (){}

        public void initRobot(HardwareMap hwMap) {
            hwBot = hwMap;

            frontLeftMotor = hwBot.dcMotor.get("front_left_motor");
            frontRightMotor = hwBot.dcMotor.get("front_right_motor");
            rearLeftMotor = hwBot.dcMotor.get("rear_left_motor");
            rearRightMotor = hwBot.dcMotor.get("rear_right_motor");
//            magSensor1 = hwBot.get (TouchSensor.class, "MagSensor1");


            frontLeftMotor.setDirection(DcMotor.Direction.FORWARD);
            rearLeftMotor.setDirection(DcMotor.Direction.FORWARD);
            frontRightMotor.setDirection(DcMotor.Direction.REVERSE);
            rearRightMotor.setDirection(DcMotor.Direction.REVERSE);

            //Initialize Motor Run Mode for Robot
            setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            rearRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            rearLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

            viperSlideRight = hwBot.dcMotor.get("viper_slide_right");
            viperSlideRight.setDirection(DcMotor.Direction.FORWARD);
            viperSlideRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

            viperSlideLeft = hwBot.dcMotor.get("viper_slide_left");
            viperSlideLeft.setDirection(DcMotorSimple.Direction.FORWARD);
            viperSlideLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

            wormgearRight = hwBot.dcMotor.get("wormgear_right");
            wormgearRight.setDirection(DcMotor.Direction.FORWARD); //check direction b/f testing
            wormgearRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


//            pixelRotatorButThisTimeItsAMotor = hwBot.dcMotor.get("pixel_rotator_motor");
//            pixelRotatorButThisTimeItsAMotor.setDirection(DcMotorSimple.Direction.FORWARD);
//            pixelRotatorButThisTimeItsAMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

//            wormgearLeft = hwBot.dcMotor.get("wormgear_left");
//            wormgearLeft.setDirection(DcMotor.Direction.FORWARD);  //check direction b/f testing
//            wormgearLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);



            //Expantion Hub Port 0

            endgameArm = hwBot.dcMotor.get("endgame_arm");
            endgameArm.setDirection(DcMotorSimple.Direction.FORWARD);
            endgameArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            endgameArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            endgameArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            endgameArmRotator = hwBot.servo.get("end_game_arm_rotator");
//            endgameArmRotator.setDirection(Servo.Direction.FORWARD);


            pixelRotatorRight = hwBot.servo.get("pixel_rotator");
            //pixelRotator.setDirection(Servo.Direction.REVERSE);
            pixelRotatorLeft = hwBot.servo.get("pixel_rotator_left");
            pixelRotatorLeft.setDirection(Servo.Direction.REVERSE);

            pixelClawLeft = hwBot.servo.get("pixel_claw_left");
            pixelClawLeft.setDirection(Servo.Direction.FORWARD);

            pixelClawRight = hwBot.servo.get("pixel_claw_right");
            pixelClawRight.setDirection(Servo.Direction.FORWARD);

            planeLauncherServo = hwBot.servo.get("plane_launcher_servo");
            planeLauncherServo.setDirection(Servo.Direction.FORWARD);
//
//            planeLauncher = hwBot.dcMotor.get("plane_launcher");
//            planeLauncher.setDirection(DcMotor.Direction.FORWARD);
//            planeLauncher.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

            currentTime.reset();

            imu = hwBot.get(IMU.class, "imu");
            imu.initialize(new IMU.Parameters(orientationOnRobot));

            pixelDistanceSensor1 = hwBot.get(DistanceSensor.class, "pixel_distance_1");
            pixelDistanceSensor2 = hwBot.get(DistanceSensor.class, "pixel_distance_2");


            blinkinLedDriver = hwBot.get(RevBlinkinLedDriver.class, "left_light");
            blinkinLedDriver2 = hwBot.get(RevBlinkinLedDriver.class, "right_light");

            // Encoders
            leftEncoder = hwBot.dcMotor.get("front_right_motor");  // Port 1
            rightEncoder = hwBot.dcMotor.get("rear_right_motor");  // Port 3
            centerEncoder = hwBot.dcMotor.get("rear_left_motor");  // Port 2

            leftEncoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightEncoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            centerEncoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            leftEncoder.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            rightEncoder.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            centerEncoder.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        }



    public void linearSlideExtend(double power) {
        viperSlideRight.setPower(-Math.abs(power));
        viperSlideLeft.setPower(-Math.abs(power));
    }

    public void linearSlideRetract(double power) {
            viperSlideRight.setPower(Math.abs(power));
            viperSlideLeft.setPower(Math.abs(power));
    }

    public void stopLinearSlide () {
            viperSlideLeft.setPower(0);
            viperSlideRight.setPower(0);

    }

    public void linearSlideExtend(double power, double ticks)  {
//        double ticks = rotations * (1) * TICKS_PER_ROTATION;
        viperSlideRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        viperSlideRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        viperSlideLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        viperSlideLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        while (Math.abs(viperSlideRight.getCurrentPosition()) < ticks && LinearOp.opModeIsActive()) {
            linearSlideExtend(power);

        }


        stopLinearSlide();
    }

    public void linearSlideRetract(double power, double ticks) {
//        double ticks = rotations * TICKS_PER_ROTATION;
        viperSlideRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        viperSlideRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        viperSlideLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        viperSlideLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        while (Math.abs(viperSlideRight.getCurrentPosition()) < ticks && LinearOp.opModeIsActive()) {
            linearSlideRetract(power);

        }
        stopLinearSlide();
    }

    public void rightWormgearUp (double power, double ticks) {
//        double ticks = rotations * TICKS_PER_ROTATION_WORMGEAR;
        wormgearRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        wormgearRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        while (Math.abs(wormgearRight.getCurrentPosition()) < ticks && LinearOp.opModeIsActive()) {
           rightWormgearDown(power);
        }
        rightWormgearStop();
    }

    public void rightWormgearDown(double power, double ticks){
//        double ticks = rotations * TICKS_PER_ROTATION_WORMGEAR;
        wormgearRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        wormgearRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        while (Math.abs(wormgearRight.getCurrentPosition()) < ticks && LinearOp.opModeIsActive()) {
            rightWormgearUp(power);
        }
        rightWormgearStop();
    }

//    public void linearSlideStop() {
//        viperSlideRight.setPower(0);
//        viperSlideLeft.setPower(0);
//    }

    public void rightWormgearUp(double power) {
        wormgearRight.setPower(Math.abs(power));
    }

    public void rightWormgearDown(double power) {
        wormgearRight.setPower(-Math.abs(power));
    }

    public void rightWormgearStop() {wormgearRight.setPower(0);}

    public void endgameArmExtend(){
        endgameArm.setPower(-1);
    }

    public void endgameArmRetract(){
       endgameArm.setPower(1);
    }
    public void endgameArmStop(){
        endgameArm.setPower(0);
    }

    public void endgameArmRotatorMovement (double position) {
            endgameArmRotator.setPosition(position);
    }

    public void rightPixelClawOpen () { pixelClawRight.setPosition(0.948);//378
    }
    public void rightPixelClawClose(){
        pixelClawRight.setPosition(0.478);
    }//848

    public void leftPixelClawOpen (){
        pixelClawLeft.setPosition(0.948);
    }

    public void leftPixelClawClose (){
        pixelClawLeft.setPosition(0.478);
    }

    public void leftPixelLEDNone() {
        blinkinLedDriver.setPattern(RevBlinkinLedDriver.BlinkinPattern.BLACK);
    }

    public void leftPixelLEDIn() {
            blinkinLedDriver.setPattern(RevBlinkinLedDriver.BlinkinPattern.BLUE);
    }

    public void leftPixelLEDCaptured() {
            blinkinLedDriver.setPattern(RevBlinkinLedDriver.BlinkinPattern.GREEN);
    }

    public void rightPixelLEDNone(){
            blinkinLedDriver2.setPattern(RevBlinkinLedDriver.BlinkinPattern.BLACK);
    }
    public void rightPixelLEDIn(){
            blinkinLedDriver2.setPattern(RevBlinkinLedDriver.BlinkinPattern.BLUE);
    }
    public void rightPixelLEDCaptured(){
            blinkinLedDriver2.setPattern(RevBlinkinLedDriver.BlinkinPattern.GREEN);
    }

//    public void planeLauncherOn(){
//            planeLauncherServo.setPower(1);
//    }
//
//    public void planeLauncherOff(){
//            planeLauncherServo.setPower(0);
//    }

    public void collectorPosition(){
            pixelRotatorRight.setPosition(.4179);
            pixelRotatorLeft.setPosition(.4179);
    }

    public void drivePosition(){
            pixelRotatorRight.setPosition(.5);
            pixelRotatorLeft.setPosition(.5);
    }

    public void autoPlacePosition() {
            pixelRotatorRight.setPosition(0.515);
            pixelRotatorLeft.setPosition((0.515));
    }

    public void automousPosition(){
            pixelRotatorRight.setPosition(.9);
    }

    //Need to determine new hang position based on how hang arm is mounted
    public void hangPosition(){
            pixelRotatorRight.setPosition(.5);
    }

    public void tuckPosition(){
            pixelRotatorRight.setPosition(0.183);
            pixelRotatorLeft.setPosition(0.183);
    }


    //collector position .22
    //drive position .35
    //auto pos .9

}
