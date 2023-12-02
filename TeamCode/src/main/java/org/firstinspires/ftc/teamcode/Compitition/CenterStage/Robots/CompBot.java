package org.firstinspires.ftc.teamcode.Compitition.CenterStage.Robots;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Compitition.CenterStage.Drivetrains.MecanumDrive;

public class CompBot extends MecanumDrive {



        public HardwareMap hwBot = null;

        public DcMotor viperSlideRight = null;
//        public DcMotor viperSlideLeft = null;
        public DcMotor wormgearRight = null;
//        public DcMotor wormgearLeft = null;
        public DcMotor endgameArm = null;
        public Servo endgameArmRotator = null;
        public Servo pixelRotator = null;

        public DcMotor pixelRotatorButThisTimeItsAMotor = null;

        public Servo pixelClawLeft = null;
        public Servo pixelClawRight = null;

        public DcMotor planeLauncher = null;
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


            pixelRotatorButThisTimeItsAMotor = hwBot.dcMotor.get("pixel_rotator_motor");
            pixelRotatorButThisTimeItsAMotor.setDirection(DcMotorSimple.Direction.FORWARD);
            pixelRotatorButThisTimeItsAMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

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
            endgameArmRotator.setDirection(Servo.Direction.FORWARD);


            pixelRotator = hwBot.servo.get("pixel_rotator");
            pixelRotator.setDirection(Servo.Direction.REVERSE);

            pixelClawLeft = hwBot.servo.get("pixel_claw_left");
            pixelClawLeft.setDirection(Servo.Direction.FORWARD);

            pixelClawRight = hwBot.servo.get("pixel_claw_right");
            pixelClawRight.setDirection(Servo.Direction.FORWARD);
//
//            planeLauncher = hwBot.dcMotor.get("plane_launcher");
//            planeLauncher.setDirection(DcMotor.Direction.FORWARD);
//            planeLauncher.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

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

    public void rightPixelClawOpen () { pixelClawRight.setPosition(0.478);//378
    }
    public void rightPixelClawClose(){
        pixelClawRight.setPosition(0.948);
    }//848

    public void leftPixelClawOpen (){
        pixelClawLeft.setPosition(0.717);
    }

    public void leftPixelClawClose (){
        pixelClawLeft.setPosition(0.268);
    }

    public void planeLauncherOn(){
            planeLauncher.setPower(1);
    }

    public void planeLauncherOff(){
            planeLauncher.setPower(0);
    }

    public void collectorPosition(){
            pixelRotator.setPosition(.5);
    }

    public void drivePosition(){
            pixelRotator.setPosition(.3);
    }

    public void autoPlacePosition() {

            pixelRotator.setPosition(0.7);

    }

    public void automousPosition(){
            pixelRotator.setPosition(.9);
    }

    public void hangPosition(){
            pixelRotator.setPosition(.5);
    }


    //collector position .22
    //drive position .35
    //auto pos .9

}
