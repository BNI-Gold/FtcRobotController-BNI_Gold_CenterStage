package org.firstinspires.ftc.teamcode.Compitition.PowerPlay.Robots;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
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


        public DcMotor grabberLift = null;
        public Servo grabberArmLeft = null;
        public Servo grabberArmRight = null;
        TouchSensor magSwitch;

        //public void straferbot2test(){ }

        public void initRobot (HardwareMap hardwareMap) {
            hwBot = hardwareMap;

            frontLeftMotor=hwBot.dcMotor.get("front_left_motor");
            frontRightMotor=hwBot.dcMotor.get("front_right_motor");
            rearLeftMotor=hwBot.dcMotor.get("rear_left_motor");
            rearRightMotor=hwBot.dcMotor.get("rear_right_motor");


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


            BNO055IMU.Parameters parametersimu = new BNO055IMU.Parameters();
            parametersimu.angleUnit = BNO055IMU.AngleUnit.DEGREES;
            parametersimu.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
            parametersimu.calibrationDataFile = "BN0055IMUCalibration.json";

            parametersimu.loggingEnabled = true;
            parametersimu.loggingTag = "IMU";
            parametersimu.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

            imu = hwBot.get(BNO055IMU.class,"imu");
            imu.initialize(parametersimu);

            grabberLift = hwBot.dcMotor.get("grabber_lift");
            grabberLift.setDirection(DcMotor.Direction.FORWARD);
            grabberLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            grabberLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            grabberLift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            // Grabber Lift Sensor Hardware Mapping & Configuration
            magSwitch = hardwareMap.get(TouchSensor.class, "magnetic_switch");

            // Grabber Arms Servo Hardware Mapping/Configuration
            grabberArmLeft = hwBot.get(Servo.class, "grabber_arm_left");
            grabberArmRight = hwBot.get(Servo.class, "grabber_arm_right");
            grabberArmLeft.setDirection(Servo.Direction.FORWARD);
            grabberArmRight.setDirection(Servo.Direction.FORWARD);


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
