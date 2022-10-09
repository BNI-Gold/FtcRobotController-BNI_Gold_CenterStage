package org.firstinspires.ftc.teamcode.MrAcker;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class AckerBot extends MecanumDrive_Acker {

    //Hardware Mapping
    public HardwareMap hwBot = null;

    //Grabber Variables
    public DcMotor grabberLift = null;
    public Servo grabberArmLeft = null;
    public Servo grabberArmRight = null;
    TouchSensor magSwitch;

    //Gyro Controls
    public BNO055IMU imu;
    public Orientation angles;
    public Acceleration gravity;
    public final double SPEED = 0.3;
    public final double TOLERANCE = 0.4;

    // Robot Initialization
    public void initRobot(HardwareMap hardwareMap) {

        hwBot = hardwareMap;

        // Drivetrain Motor Hardware
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

        // Grabber Lift Motors Hardware
        grabberLift = hwBot.dcMotor.get("grabber_lift");
        grabberLift.setDirection(DcMotor.Direction.FORWARD);
        grabberLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        grabberLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        grabberLift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        magSwitch = hardwareMap.get(TouchSensor.class, "magnetic_switch");

        // Grabber Arm Hardware
        grabberArmLeft = hwBot.get(Servo.class, "grabber_arm_left");
        grabberArmRight = hwBot.get(Servo.class, "grabber_arm_right");
        grabberArmLeft.setDirection(Servo.Direction.FORWARD);
        grabberArmRight.setDirection(Servo.Direction.FORWARD);

        // Gyro Hardware
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

    // Grabber Arm Methods

    void openGrabberArms() {
        grabberArmLeft.setPosition(.55);
        grabberArmRight.setPosition(.45);

    }

    void closeGrabberArms() {
        grabberArmLeft.setPosition(.45);
        grabberArmRight.setPosition(.55);
    }


    void closeGrabberArms(double armLeftPos, double armRightPos) {
        grabberArmLeft.setPosition(armLeftPos);
        grabberArmRight.setPosition(armRightPos);
    }

    void openGrabberArms(double armLeftPos, double armRightPos) {
        grabberArmLeft.setPosition(armLeftPos);
        grabberArmRight.setPosition(armRightPos);
    }

    // Grabber Lift Methods

    void extendGrabberLift(double power) {

        grabberLift.setPower(Math.abs(power));
    }

    void retractGrabberLift (double power) {

        grabberLift.setPower(-Math.abs(power));
    }

    void stopGrabberLift () {
        grabberLift.setPower(0);
    }

}


