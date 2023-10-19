package org.firstinspires.ftc.teamcode.Compitition.CenterStage.Robots;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.Compitition.CenterStage.Drivetrains.MecanumDrive;

public class CompBot extends MecanumDrive {



        public HardwareMap hwBot = null;

        public DcMotor viperSlideRight = null;
//        public DcMotor viperSlideLeft = null;
        public DcMotor wormgearRight = null;
//        public DcMotor wormgearLeft = null;
        public DcMotor endgameArm = null;
        public Servo pixelClaw = null;
        public Servo endgameArmRotator = null;
        public Servo pixelRotator = null;
        public ElapsedTime currentTime = new ElapsedTime();

        public ElapsedTime upTimer = new ElapsedTime();
        public ElapsedTime downTimer = new ElapsedTime();


    public IMU imu  = null;
    public double headingError  = 0;
    RevHubOrientationOnRobot.LogoFacingDirection logoDirection = RevHubOrientationOnRobot.LogoFacingDirection.UP;
    RevHubOrientationOnRobot.UsbFacingDirection  usbDirection  = RevHubOrientationOnRobot.UsbFacingDirection.FORWARD;
    RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot(logoDirection, usbDirection);


//        public BNO055IMU imu;
//        public Orientation angles;
//        public Acceleration gravity;
//        public final double SPEED = .3;
//        public final double TOLERANCE = .4;

        public CompBot (){}

        public void initRobot(HardwareMap hwMap) {
            hwBot = hwMap;

            frontLeftMotor = hwBot.dcMotor.get("front_left_motor");
            frontRightMotor = hwBot.dcMotor.get("front_right_motor");
            rearLeftMotor = hwBot.dcMotor.get("rear_left_motor");
            rearRightMotor = hwBot.dcMotor.get("rear_right_motor");

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

//            viperSlideLeft = hwBot.dcMotor.get("viper_slide_left");
//            viperSlideLeft.setDirection(DcMotorSimple.Direction.FORWARD);
//            viperSlideLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

            wormgearRight = hwBot.dcMotor.get("wormgear_right");
            wormgearRight.setDirection(DcMotor.Direction.FORWARD); //check direction b/f testing
            wormgearRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

//            wormgearLeft = hwBot.dcMotor.get("wormgear_left");
//            wormgearLeft.setDirection(DcMotor.Direction.FORWARD);  //check direction b/f testing
//            wormgearLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);



            //Expantion Hub Port 0

            endgameArm = hwBot.dcMotor.get("endgame_arm");
            endgameArm.setDirection(DcMotorSimple.Direction.FORWARD);
            endgameArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            endgameArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            endgameArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


            pixelClaw = hwBot.servo.get("pixel_claw");
            pixelClaw.setDirection(Servo.Direction.FORWARD);

            endgameArmRotator = hwBot.servo.get("end_game_arm_rotator");
            endgameArmRotator.setDirection(Servo.Direction.FORWARD);


            pixelRotator = hwBot.servo.get("pixel_rotator");
            pixelRotator.setDirection(Servo.Direction.FORWARD);

            currentTime.reset();


//            BNO055IMU.Parameters parametersimu = new BNO055IMU.Parameters();
//            parametersimu.angleUnit = BNO055IMU.AngleUnit.DEGREES;
//            parametersimu.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
//            parametersimu.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
//
//            parametersimu.loggingEnabled = true;
//            parametersimu.loggingTag = "IMU";
//            parametersimu.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
//
//            imu = hwBot.get(BNO055IMU.class, "imu");
//            imu.initialize(parametersimu);

            imu = hwBot.get(IMU.class, "imu");
            imu.initialize(new IMU.Parameters(orientationOnRobot));



        }

//    public void gyroCorrection (double speed, double angle) {
//
//        angles = imu.getAngularOrientation(
//                AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
//
//        if (angles.firstAngle >= angle + TOLERANCE && LinearOp.opModeIsActive()) {
//            while (angles.firstAngle >=  angle + TOLERANCE && LinearOp.opModeIsActive()) {
//                rotateRight(speed);
//                angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
//
//                LinearOp.telemetry.addData("Current Angle Est: ", angles.firstAngle);
//            }
//        }
//        else if (angles.firstAngle <= angle - TOLERANCE && LinearOp.opModeIsActive()) {
//            while (angles.firstAngle <= angle - TOLERANCE && LinearOp.opModeIsActive()) {
//                rotateLeft(speed);
//                angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
//
//                LinearOp.telemetry.addData("Current Angle Est:" , angles.firstAngle);
//            }
//        }
//        stopMotors();
//
//        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
//    }
//
//
//    public void gyroReset () {
//        BNO055IMU.Parameters parametersimu = new BNO055IMU.Parameters();
//        imu.initialize(parametersimu);
//    }



    public void linearSlideExtend(double power) {
        viperSlideRight.setPower(-Math.abs(power));
    }

    public void linearSlideRetract(double power) {viperSlideRight.setPower(Math.abs(power));
    }

    public void linearSlideExtend(double power, double rotations)  {
        double ticks = rotations * (1) * TICKS_PER_ROTATION;
        viperSlideRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        viperSlideRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        while (Math.abs(viperSlideRight.getCurrentPosition()) < ticks && LinearOp.opModeIsActive()) {
            linearSlideExtend(power);
        }
        linearSlideStop();
    }

    public void linearSlideRetract(double power, double rotations) {
        double ticks = rotations * TICKS_PER_ROTATION;
        viperSlideRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        viperSlideRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        while (Math.abs(viperSlideRight.getCurrentPosition())< ticks && LinearOp.opModeIsActive()) {
            linearSlideRetract(power);
        }
        linearSlideStop();
    }

    public void linearSlideStop() {
        viperSlideRight.setPower(0);
    }

    public void rightWormgearUp(double power) {
        wormgearRight.setPower(Math.abs(power));
    }

    public void rightWormgearDown(double power) {
        wormgearRight.setPower(-Math.abs(power));
    }

    public void rightWormgearStop (double power) {wormgearRight.setPower(0);}

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

}
