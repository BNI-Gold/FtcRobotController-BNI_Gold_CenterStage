package org.firstinspires.ftc.teamcode.Compitition.CenterStage.RoadRunner.drive;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.acmerobotics.roadrunner.drive.DriveSignal;
import com.acmerobotics.roadrunner.drive.MecanumDrive;
import com.acmerobotics.roadrunner.followers.HolonomicPIDVAFollower;
import com.acmerobotics.roadrunner.followers.TrajectoryFollower;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder;
import com.acmerobotics.roadrunner.trajectory.constraints.AngularVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.MecanumVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.MinVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.ProfileAccelerationConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryAccelerationConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryVelocityConstraint;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Compitition.CenterStage.RoadRunner.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.Compitition.CenterStage.RoadRunner.trajectorysequence.TrajectorySequenceBuilder;
import org.firstinspires.ftc.teamcode.Compitition.CenterStage.RoadRunner.trajectorysequence.TrajectorySequenceRunner;
import org.firstinspires.ftc.teamcode.Compitition.CenterStage.RoadRunner.util.LynxModuleUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Simple mecanum drive hardware implementation for REV hardware.
 */
@Config
public class RoadrunMecanumDrive extends MecanumDrive {

    public static final double TICKS_PER_REV = 1;
    public static final double MAX_RPM = 1;   //actually 1

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

    public LinearOpMode LinearOp = null;

    public ElapsedTime upTimer = new ElapsedTime();
    public ElapsedTime downTimer = new ElapsedTime();

    public static final double TICKS_PER_ROTATION = 386.3;

    RevBlinkinLedDriver blinkinLedDriver;
    RevBlinkinLedDriver.BlinkinPattern pattern;

    RevBlinkinLedDriver blinkinLedDriver2;
    RevBlinkinLedDriver.BlinkinPattern pattern2;



    public double headingError  = 0;

    /*
     * Set RUN_USING_ENCODER to true to enable built-in hub velocity control using drive encoders.
     * Set this flag to false if drive encoders are not present and an alternative localization
     * method is in use (e.g., tracking wheels).
     *
     * If using the built-in motor velocity PID, update MOTOR_VELO_PID with the tuned coefficients
     * from DriveVelocityPIDTuner.
     */
    public static final boolean RUN_USING_ENCODER = false;
    public static PIDFCoefficients MOTOR_VELO_PID = new PIDFCoefficients(0, 0, 0,
            getMotorVelocityF(MAX_RPM / 60 * TICKS_PER_REV));
    /*
     * These are physical constants that can be determined from your robot (including the track
     * width; it will be tune empirically later although a rough estimate is important). Users are
     * free to chose whichever linear distance unit they would like so long as it is consistently
     * used. The default values were selected with inches in mind. Road runner uses radians for
     * angular distances although most angular parameters are wrapped in Math.toRadians() for
     * convenience. Make sure to exclude any gear ratio included in MOTOR_CONFIG from GEAR_RATIO.
     */
    public static double WHEEL_RADIUS = 1.889765; // in
    public static double GEAR_RATIO = 0.875; // output (wheel) speed / input (motor) speed
    public static double TRACK_WIDTH = 15.86; // in

    /*
     * These are the feedforward parameters used to model the drive motor behavior. If you are using
     * the built-in velocity PID, *these values are fine as is*. However, if you do not have drive
     * motor encoders or have elected not to use them for velocity control, these values should be
     * empirically tuned.
     */
    public static double kV = 0.0035 / rpmToVelocity(MAX_RPM); //0.01
    public static double kA = 0.001; //0
    public static double kStatic = 0.02; //0

    /*
     * These values are used to generate the trajectories for you robot. To ensure proper operation,
     * the constraints should never exceed ~80% of the robot's actual capabilities. While Road
     * Runner is designed to enable faster autonomous motion, it is a good idea for testing to start
     * small and gradually increase them later after everything is working. All distance units are
     * inches.
     */
    public static double MAX_VEL = 20; //45.92255419789212
    public static double MAX_ACCEL = 20; //45.92255419789212
    public static double MAX_ANG_VEL = Math.toRadians(200.65656843804032); //166.5296544303
    // 7974
    public static double MAX_ANG_ACCEL = Math.toRadians(200.52965443037974); //166.52965443037974

    /*
     * Adjust the orientations here to match your robot. See the FTC SDK documentation for details.
     */
    public static RevHubOrientationOnRobot.LogoFacingDirection LOGO_FACING_DIR =
            RevHubOrientationOnRobot.LogoFacingDirection.RIGHT;
    public static RevHubOrientationOnRobot.UsbFacingDirection USB_FACING_DIR =
            RevHubOrientationOnRobot.UsbFacingDirection.UP;




    public static double encoderTicksToInches(double ticks) {
        return WHEEL_RADIUS * 2 * Math.PI * GEAR_RATIO * ticks / TICKS_PER_REV;
    }

    public static double rpmToVelocity(double rpm) {
        return rpm * GEAR_RATIO * 2 * Math.PI * WHEEL_RADIUS / 60.0;
    }

    public static double getMotorVelocityF(double ticksPerSecond) {
        // see https://docs.google.com/document/d/1tyWrXDfMidwYyP_5H4mZyVgaEswhOC35gvdmP-V-5hA/edit#heading=h.61g9ixenznbx
        return 32767 / ticksPerSecond;
    }
    public static PIDCoefficients TRANSLATIONAL_PID = new PIDCoefficients(0, 0, 1);
    public static PIDCoefficients HEADING_PID = new PIDCoefficients(8, 0, 1);

    public static double LATERAL_MULTIPLIER = 1.5;

    public static double VX_WEIGHT = 1;
    public static double VY_WEIGHT = 1;
    public static double OMEGA_WEIGHT = 1;

    private TrajectorySequenceRunner trajectorySequenceRunner;


    private static final TrajectoryVelocityConstraint VEL_CONSTRAINT = getVelocityConstraint(MAX_VEL, MAX_ANG_VEL, TRACK_WIDTH);
    private static final TrajectoryAccelerationConstraint ACCEL_CONSTRAINT = getAccelerationConstraint(MAX_ACCEL);
    private TrajectoryFollower follower;

    private DcMotorEx leftFront, leftRear, rightRear, rightFront;
    private List<DcMotorEx> motors;

    private IMU imu;
    private VoltageSensor batteryVoltageSensor;

    private List<Integer> lastEncPositions = new ArrayList<>();
    private List<Integer> lastEncVels = new ArrayList<>();





//

    public RoadrunMecanumDrive(HardwareMap hardwareMap) {


        super(kV, kA, kStatic, TRACK_WIDTH, TRACK_WIDTH, LATERAL_MULTIPLIER);

        follower = new HolonomicPIDVAFollower(TRANSLATIONAL_PID, TRANSLATIONAL_PID, HEADING_PID,
                new Pose2d(0.5, 0.5, Math.toRadians(5.0)), 0.5);

        LynxModuleUtil.ensureMinimumFirmwareVersion(hardwareMap);

        batteryVoltageSensor = hardwareMap.voltageSensor.iterator().next();

        for (LynxModule module : hardwareMap.getAll(LynxModule.class)) {
            module.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
        }

        // TODO: adjust the names of the following hardware devices to match your configuration


        leftFront = hardwareMap.get(DcMotorEx.class, "front_left_motor");
        leftRear = hardwareMap.get(DcMotorEx.class, "rear_left_motor");
        rightRear = hardwareMap.get(DcMotorEx.class, "rear_right_motor");
        rightFront = hardwareMap.get(DcMotorEx.class, "front_right_motor");

        motors = Arrays.asList(leftFront, leftRear, rightRear, rightFront);

        for (DcMotorEx motor : motors) {
            MotorConfigurationType motorConfigurationType = motor.getMotorType().clone();
            motorConfigurationType.setAchieveableMaxRPMFraction(1.0);
            motor.setMotorType(motorConfigurationType);
        }

        if (RUN_USING_ENCODER) {
            setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }

        setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        if (RUN_USING_ENCODER && MOTOR_VELO_PID != null) {
            setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, MOTOR_VELO_PID);
        }

        // TODO: reverse any motors using DcMotor.setDirection()

        rightFront.setDirection(DcMotorSimple.Direction.FORWARD);
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightRear.setDirection(DcMotorSimple.Direction.FORWARD);
        leftRear.setDirection(DcMotorSimple.Direction.REVERSE);

        List<Integer> lastTrackingEncPositions = new ArrayList<>();
        List<Integer> lastTrackingEncVels = new ArrayList<>();

        // TODO: if desired, use setLocalizer() to change the localization method
         setLocalizer(new StandardTrackingWheelLocalizer(hardwareMap, lastTrackingEncPositions, lastTrackingEncVels));

        trajectorySequenceRunner = new TrajectorySequenceRunner(
                follower, HEADING_PID, batteryVoltageSensor,
                lastEncPositions, lastEncVels, lastTrackingEncPositions, lastTrackingEncVels
        );

//        magSensor1 = hwBot.get (TouchSensor.class, "MagSensor1");







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


//



        pixelDistanceSensor1 = hwBot.get(DistanceSensor.class, "pixel_distance_1");
        pixelDistanceSensor2 = hwBot.get(DistanceSensor.class, "pixel_distance_2");


        blinkinLedDriver = hwBot.get(RevBlinkinLedDriver.class, "left_light");
        blinkinLedDriver2 = hwBot.get(RevBlinkinLedDriver.class, "right_light");

//

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

    public void linearSlideExtend(double power, double rotations)  {
        double ticks = rotations * (1) * TICKS_PER_ROTATION;
        viperSlideRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        viperSlideRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        while (Math.abs(viperSlideRight.getCurrentPosition()) < ticks && LinearOp.opModeIsActive()) {
            linearSlideExtend(power);
        }
        stopLinearSlide();
    }

    public void linearSlideRetract(double power, double rotations) {
        double ticks = rotations * TICKS_PER_ROTATION;
        viperSlideRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        viperSlideRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        while (Math.abs(viperSlideRight.getCurrentPosition())< ticks && LinearOp.opModeIsActive()) {
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
        pixelRotatorRight.setPosition(0.555);
        pixelRotatorLeft.setPosition((0.555));
    }

    public void automousPosition(){
        pixelRotatorRight.setPosition(.9);
    }

    //Need to determine new hang position based on how hang arm is mounted
    public void hangPosition(){
        pixelRotatorRight.setPosition(.5);
    }

    public void tuckPosition(){
        pixelRotatorRight.setPosition(0.283);
        pixelRotatorLeft.setPosition(0.283);
    }


    public TrajectoryBuilder trajectoryBuilder(Pose2d startPose) {
        return new TrajectoryBuilder(startPose, VEL_CONSTRAINT, ACCEL_CONSTRAINT);
    }

    public TrajectoryBuilder trajectoryBuilder(Pose2d startPose, boolean reversed) {
        return new TrajectoryBuilder(startPose, reversed, VEL_CONSTRAINT, ACCEL_CONSTRAINT);
    }

    public TrajectoryBuilder trajectoryBuilder(Pose2d startPose, double startHeading) {
        return new TrajectoryBuilder(startPose, startHeading, VEL_CONSTRAINT, ACCEL_CONSTRAINT);
    }

    public TrajectorySequenceBuilder trajectorySequenceBuilder(Pose2d startPose) {
        return new TrajectorySequenceBuilder(
                startPose,
                VEL_CONSTRAINT, ACCEL_CONSTRAINT,
                MAX_ANG_VEL, MAX_ANG_ACCEL
        );
    }

    public void turnAsync(double angle) {
        trajectorySequenceRunner.followTrajectorySequenceAsync(
                trajectorySequenceBuilder(getPoseEstimate())
                        .turn(angle)
                        .build()
        );
    }

    public void turn(double angle) {
        turnAsync(angle);
        waitForIdle();
    }

    public void followTrajectoryAsync(Trajectory trajectory) {
        trajectorySequenceRunner.followTrajectorySequenceAsync(
                trajectorySequenceBuilder(trajectory.start())
                        .addTrajectory(trajectory)
                        .build()
        );
    }

    public void followTrajectory(Trajectory trajectory) {
        followTrajectoryAsync(trajectory);
        waitForIdle();
    }

    public void followTrajectorySequenceAsync(TrajectorySequence trajectorySequence) {
        trajectorySequenceRunner.followTrajectorySequenceAsync(trajectorySequence);
    }

    public void followTrajectorySequence(TrajectorySequence trajectorySequence) {
        followTrajectorySequenceAsync(trajectorySequence);
        waitForIdle();
    }

    public Pose2d getLastError() {
        return trajectorySequenceRunner.getLastPoseError();
    }

    public void update() {
        updatePoseEstimate();
        DriveSignal signal = trajectorySequenceRunner.update(getPoseEstimate(), getPoseVelocity());
        if (signal != null) setDriveSignal(signal);
    }

    public void waitForIdle() {
        while (!Thread.currentThread().isInterrupted() && isBusy())
            update();
    }

    public boolean isBusy() {
        return trajectorySequenceRunner.isBusy();
    }

    public void setMode(DcMotor.RunMode runMode) {
        for (DcMotorEx motor : motors) {
            motor.setMode(runMode);
        }
    }

    public void setZeroPowerBehavior(DcMotor.ZeroPowerBehavior zeroPowerBehavior) {
        for (DcMotorEx motor : motors) {
            motor.setZeroPowerBehavior(zeroPowerBehavior);
        }
    }

    public void setPIDFCoefficients(DcMotor.RunMode runMode, PIDFCoefficients coefficients) {
        PIDFCoefficients compensatedCoefficients = new PIDFCoefficients(
                coefficients.p, coefficients.i, coefficients.d,
                coefficients.f * 12 / batteryVoltageSensor.getVoltage()
        );

        for (DcMotorEx motor : motors) {
            motor.setPIDFCoefficients(runMode, compensatedCoefficients);
        }
    }

    public void setWeightedDrivePower(Pose2d drivePower) {
        Pose2d vel = drivePower;

        if (Math.abs(drivePower.getX()) + Math.abs(drivePower.getY())
                + Math.abs(drivePower.getHeading()) > 1) {
            // re-normalize the powers according to the weights
            double denom = VX_WEIGHT * Math.abs(drivePower.getX())
                    + VY_WEIGHT * Math.abs(drivePower.getY())
                    + OMEGA_WEIGHT * Math.abs(drivePower.getHeading());

            vel = new Pose2d(
                    VX_WEIGHT * drivePower.getX(),
                    VY_WEIGHT * drivePower.getY(),
                    OMEGA_WEIGHT * drivePower.getHeading()
            ).div(denom);
        }

        setDrivePower(vel);
    }
    public void setLinearOp(LinearOpMode LinearOp) {this.LinearOp = LinearOp;}
    @NonNull
    @Override
    public List<Double> getWheelPositions() {
        lastEncPositions.clear();

        List<Double> wheelPositions = new ArrayList<>();
        for (DcMotorEx motor : motors) {
            int position = motor.getCurrentPosition();
            lastEncPositions.add(position);
            wheelPositions.add(encoderTicksToInches(position));
        }
        return wheelPositions;
    }

    @Override
    public List<Double> getWheelVelocities() {
        lastEncVels.clear();

        List<Double> wheelVelocities = new ArrayList<>();
        for (DcMotorEx motor : motors) {
            int vel = (int) motor.getVelocity();
            lastEncVels.add(vel);
            wheelVelocities.add(encoderTicksToInches(vel));
        }
        return wheelVelocities;
    }

    @Override
    public void setMotorPowers(double v, double v1, double v2, double v3) {
        leftFront.setPower(v);
        leftRear.setPower(v1);
        rightRear.setPower(v2);
        rightFront.setPower(v3);
    }

    @Override
    public double getRawExternalHeading() {
        return 0;
    }

    @Override
    public Double getExternalHeadingVelocity() {
        return 0.0;
    }

    public static TrajectoryVelocityConstraint getVelocityConstraint(double maxVel, double maxAngularVel, double trackWidth) {
        return new MinVelocityConstraint(Arrays.asList(
                new AngularVelocityConstraint(maxAngularVel),
                new MecanumVelocityConstraint(maxVel, trackWidth)
        ));
    }

    public static TrajectoryAccelerationConstraint getAccelerationConstraint(double maxAccel) {
        return new ProfileAccelerationConstraint(maxAccel);
    }
}
