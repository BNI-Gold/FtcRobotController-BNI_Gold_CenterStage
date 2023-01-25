package org.firstinspires.ftc.teamcode.Compitition.PowerPlay.Robots;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.Compitition.PowerPlay.DriveTrains.MecanumDrive;

public class CompetionBot extends MecanumDrive {

        public HardwareMap hwBot = null;


        public BNO055IMU imu;
        public Orientation angles;
        public Acceleration gravity;
        public final double SPEED = 0.3;
        public final double TOLERANCE = 0.4;


        public DcMotor grabberLiftOne = null;
        public DcMotor grabberLiftTwo = null;
        public DcMotor turretPlatform = null;
        public Servo grabberArmServo = null;
        public CRServo bigConeSucker = null;
        TouchSensor magSwitch;



    public void initRobot (HardwareMap hardwareMap) {
            hwBot = hardwareMap;

            grabberArmServo = hwBot.get(Servo.class, "grabber_arm");
            grabberArmServo.setDirection(Servo.Direction.FORWARD);

            bigConeSucker = hwBot.get(CRServo.class, "cone_sucker");
            bigConeSucker.setDirection(CRServo.Direction.FORWARD);

            frontLeftMotor = hardwareMap.get(DcMotorEx.class, "front_left_motor");
        frontRightMotor = hardwareMap.get(DcMotorEx.class, "front_right_motor");
        rearLeftMotor = hardwareMap.get(DcMotorEx.class, "rear_left_motor");
        rearRightMotor = hardwareMap.get(DcMotorEx.class, "rear_right_motor");
//            frontLeftMotor=hwBot.dcMotor.get("front_left_motor");
//            frontRightMotor=hwBot.dcMotor.get("front_right_motor");
//            rearLeftMotor=hwBot.dcMotor.get("rear_left_motor");
//            rearRightMotor=hwBot.dcMotor.get("rear_right_motor");

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


//            frontLeftMotor.setPositionPIDFCoefficients(1);
//            frontRightMotor.setPositionPIDFCoefficients(1);
//            rearRightMotor.setPositionPIDFCoefficients(1);
//            rearLeftMotor.setPositionPIDFCoefficients(1);

//            frontLeftMotor.setVelocityPIDFCoefficients(1,1,1,1);
//            frontLeftMotor.setVelocityPIDFCoefficients(1,1,1,1);
//            frontLeftMotor.setVelocityPIDFCoefficients(1,1,1,1);
//            frontLeftMotor.setVelocityPIDFCoefficients(1,1,1,1);


        BNO055IMU.Parameters parametersimu = new BNO055IMU.Parameters();
            parametersimu.angleUnit = BNO055IMU.AngleUnit.DEGREES;
            parametersimu.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
            parametersimu.calibrationDataFile = "BN0055IMUCalibration.json";

            parametersimu.loggingEnabled = true;
            parametersimu.loggingTag = "IMU";
            parametersimu.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

            imu = hwBot.get(BNO055IMU.class,"imu");
            imu.initialize(parametersimu);

//            grabberLift = hwBot.dcMotor.get("grabber_lift");
//            grabberLift.setDirection(DcMotor.Direction.FORWARD);
//            grabberLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//            grabberLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//            grabberLift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        grabberLiftOne = hardwareMap.get(DcMotorEx.class, "grabber_lift_one");
        grabberLiftOne.setDirection(DcMotorSimple.Direction.REVERSE);
        grabberLiftOne.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        grabberLiftOne.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        grabberLiftOne.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //Bot.grabberLiftOne.setPositionPIDFCoefficients(5);

        grabberLiftTwo = hardwareMap.get(DcMotorEx.class, "grabber_lift_two");
        grabberLiftTwo.setDirection(DcMotorSimple.Direction.FORWARD);
        grabberLiftTwo.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        grabberLiftTwo.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        grabberLiftTwo.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //Bot.grabberLiftTwo.setPositionPIDFCoefficients(5);

        turretPlatform = hardwareMap.get(DcMotorEx.class,"turret_motor");
        turretPlatform.setDirection(DcMotorSimple.Direction.REVERSE);
        turretPlatform.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        turretPlatform.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        turretPlatform.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            // Grabber Lift Sensor Hardware Mapping & Configuration
            magSwitch = hardwareMap.get(TouchSensor.class, "magnetic_switch");

            // Grabber Arms Servo Hardware Mapping/Configuration

        }

        public void coneOuttake() {

            bigConeSucker.setDirection(CRServo.Direction.REVERSE);
            bigConeSucker.setPower(1);

        }

        public void coneIntake() {

            bigConeSucker.setDirection(CRServo.Direction.FORWARD);
            bigConeSucker.setPower(1);

        }

    public void intakeStop() {

        bigConeSucker.setPower(0);

    }

        public void openGrabberArms() {

            grabberArmServo.setPosition(0.76);

        }

        public void closeGrabberArms() {

            grabberArmServo.setPosition(.68);

        }

        public void intakeGrabberArms() {

            grabberArmServo.setPosition(0.723);

        }

    public void extendGrabberLift(double power) {

        grabberLiftOne.setPower(Math.abs(power));
        grabberLiftTwo.setPower(Math.abs(power));
    }

    public void retractGrabberLift (double power) {

        grabberLiftOne.setPower(-Math.abs(power));
        grabberLiftTwo.setPower(-Math.abs(power));
    }

    public void stopGrabberLift () {
        grabberLiftOne.setPower(0);
        grabberLiftTwo.setPower(0);
    }

    public void turretClockwise (double power) {

        turretPlatform.setPower(Math.abs(power));

    }

    public void autoTurretClockwise (double power, double rotations) {
        turretPlatform.setPower(Math.abs(power));
    }

    public void turretCounterClockwise (double power) {
        turretPlatform.setPower(-Math.abs(power));

    }

    public void autoTurretCounterClockwise (double power, double rotations) {
        turretPlatform.setPower(-Math.abs(power));

    }

    public void turretStop () {
        turretPlatform.setPower(0);
    }

        public void gyroCorrection (double speed, double angle) {

            angles = imu.getAngularOrientation(
                    AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

            if (angles.firstAngle >= angle + TOLERANCE && linearOp.opModeIsActive()) {
                while (angles.firstAngle >= angle + TOLERANCE && linearOp.opModeIsActive()) {
                    rotateRight(speed);
                    angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

             linearOp.telemetry.addData("Current Angle: ", angles.firstAngle);
                }
            }

            else if (angles.firstAngle <= angle - TOLERANCE && linearOp.opModeIsActive()); {
                while (angles.firstAngle <= angle - TOLERANCE && linearOp.opModeIsActive()) {
                    rotateLeft(speed);

                    angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
                        linearOp.telemetry.addData("Current Angle ", angles.firstAngle);
                }
            }

            stopMotors();

                angles  = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        }


}
