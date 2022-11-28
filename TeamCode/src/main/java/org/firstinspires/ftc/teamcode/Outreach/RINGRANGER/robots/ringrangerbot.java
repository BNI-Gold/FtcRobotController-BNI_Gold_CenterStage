package org.firstinspires.ftc.teamcode.outreach.RINGRANGER.robots;

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
import org.firstinspires.ftc.teamcode.Compitition.ZCompititionUltimateGoal.Robots.CompetitionBot;
import org.firstinspires.ftc.teamcode.outreach.RINGRANGER.drivetrains.MecanumDriveButMatthewMadeIt;



    public class ringrangerbot extends MecanumDriveButMatthewMadeIt {

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
//    public ColorSensor sensorColorWobbleArm;
//    public DistanceSensor sensorDistanceWobbleArm;
        public float hsvValues[] = {0F, 0F, 0F};
        public final double SCALE_FACTOR = 1;
        //    Under 100 = red tape
        public static final int WOBBLE_ARM_RAISE_THRESHOLD = 170;
        //    Looks for > 200 with blue tape
        public static final int WOBBLE_ARM_LOWER_THRESHOLD = 100;
        //  Camera Initialization




//    public static final double TICKS_PER_ROTATION = 383.6;   // GoBilda 13.7 Motor PPR




        //    Servo WobbleArm = null;
        Servo WobbleGrab = null;
        Servo Camera = null;
        public Servo ServoRingPusher = null;
        public Servo RingMag = null;
        public CRServo IntakeCorrector = null;

//    Servo WobbleArmStop = null;
//    Servos servos = new Servos();


        public double servoOpenPos = 0.36;
        public double servoClosePos = 0.93;
        //    was 0.446
//    public double WobbleArmRaisedPos = 0.23;
//    public double WobbleArmLowerPos = 0.613;
        public double WobbleGrabOpenPos = .59;
        public double WobbleGrabClosePos = 0.116;
        //Blue Left:
        //was at .2
        public double CameraServoPosBlueLeft = 0.2;
        //Blue Right:
        public double CameraServoPosBlueRight = 0.602;
        //Launcher Motor:
        public DcMotor IntakeMotor = null;
        public double RingPushPos = 0.26;
        //.196 before (Mar 3, 2021 3:40pm)
        public double RingPullPos = 0.40;
        //.460 before (Mar 3, 2021 3:40pm)
        public double RingMagUpPos = 0.2;
        //.13 before (Mar 3, 2021 3:42pm)
        public double RingMagDownPos = 0.051;
        //.058 before (Mar 3, 2021 3:42pm)
        public double RingMagUpAuto = 0.217;

        public double DeltaRing = Math.abs(RingPullPos - RingPushPos);
        int numLoops = 7;
        public double ringIncrement = DeltaRing/numLoops;

        public double DeltaRingMag = Math.abs(RingMagUpPos - RingMagDownPos);
        int numLoopsMag = 9;
        //if numloops is high = servo is slower
        //if numloops is low = servo is faster
        public double ringIncrementMag = DeltaRingMag/numLoopsMag;

//    public double WobbleArmStopOpen = 0.02;
//    public double WobbleArmStopClose = 0.48;
//    Wobble Arm Motor Data

        public DcMotor WobbleArmMotor = null;
        //    public DcMotor motor_left = null;
//    public DcMotor motor_right = null;
        public DcMotorEx launcherMotor1 = null;
        public DcMotorEx launcherMotor2 = null;
        public double velocity = 1600;
        public boolean wobbleArmRaiseEngage;
        public boolean wobbleArmLowerengage;


//.78 before

        public int rapidFireRing = 0;
        public int rapidPushTimer = 150; //200 before
        public int rapidPullTimer = 175; //150 before
        public boolean launchModePush = false;
        public boolean launchModePull = false;

        private final static int LED_PERIOD = 10;
        private final static int GAMEPAD_LOCKOUT = 500;

        public RevBlinkinLedDriver blinkinLedDriver;
        public RevBlinkinLedDriver.BlinkinPattern pattern;

        public double maxWobbleArmRaiseTime = 0.3;
        public double maxWobbleArmLowerTime = 2; //2.0 before
        public double maxRingPusherTime = 0.3;
        public double maxRingPullerTime = 0.3;

        public ElapsedTime wobbleArmTimer;
        public ElapsedTime ringPusherTimer;



        Telemetry.Item patternName;
        Telemetry.Item display;
        Deadline ledCycleDeadline;
        Deadline gamepadRateLimit;

        protected enum DisplayKind {
            MANUAL,
            AUTO
        }
        //LabBot constructor
        public ringrangerbot() {

        }

        public void initRobot(HardwareMap hardwareMap, String startPosition, String mode){
//        HardwareMap hwMap, String startPosition, String mode

//        hwBot = hwMap
            hwBot = hardwareMap;
//        WobbleArm = hwBot.get(Servo.class, "wobble_arm");
//        WobbleArm.setDirection(Servo.Direction.FORWARD);
//        if (mode.equals("auto")) {
//            WobbleArm.setPosition(WobbleArmRaisedPos);
//
//        }


            voltageSensor = hardwareMap.voltageSensor.iterator().next();
            launchCoefficient = 12 / voltageSensor.getVoltage();

            WobbleGrab = hwBot.get(Servo.class, "wobble_grab");
            WobbleGrab.setDirection(Servo.Direction.FORWARD);
            RingMag = hwBot.get(Servo.class, "ring_mag");
            ServoRingPusher = hwBot.get(Servo.class, "servo_ring_pusher");
//        WobbleArmStop = hwBot.get(Servo.class, "wobble_arm_stopper");
            Camera = hwBot.get(Servo.class, "camera_servo");
//        Camera.setDirection(Servo.Direction.FORWARD);
            if (mode.equals("auto")){
                WobbleGrab.setPosition(WobbleGrabClosePos);
//            WobbleArmStopOpen();
                RingMagUp();
                RingPush();

                Camera.setPosition(CameraServoPosBlueLeft);
            }
            Camera = hwBot.get(Servo.class, "camera_servo");
            Camera.setDirection(Servo.Direction.FORWARD);

            switch (startPosition) {
                case "BlueLeft":
                    Camera.setPosition(CameraServoPosBlueLeft);
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

//        IntakeCorrector = hwBot.get(CRServo.class, "intake_corrector");
            IntakeCorrector = hardwareMap.crservo.get ("intake_corrector");
            IntakeCorrector.setDirection(DcMotorSimple.Direction.REVERSE);
            IntakeCorrector.setPower(0);



            // define motors for robot
            frontLeftMotor=hwBot.dcMotor.get("front_left_motor");
            frontRightMotor=hwBot.dcMotor.get("front_right_motor");
            rearLeftMotor = hwBot.dcMotor.get("rear_left_motor");
            rearRightMotor = hwBot.dcMotor.get("rear_right_motor");


            IntakeMotor = hwBot.dcMotor.get("intake_motor");

            WobbleArmMotor = hwBot.dcMotor.get("wobble_arm_motor");


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


            WobbleArmMotor.setDirection(DcMotor.Direction.FORWARD);
            WobbleArmMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


            wobbleArmRaiseEngage = false;
            wobbleArmLowerengage = false;


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

        public void WobbleOpen(){
            WobbleGrab.setPosition(WobbleGrabOpenPos);
        }
        public void WobbleClosed(){
            WobbleGrab.setPosition(WobbleGrabClosePos);
        }
        public void detectRings () { }

        public void LauncherOn(double power) {
            launcherMotor1.setVelocity(power);
            launcherMotor2.setVelocity(power);
        }
        public void LauncherOff(double power) {
            launcherMotor1.setPower(power);
            launcherMotor2.setPower(power);
        }

        public void IntakeOn(double power){
            IntakeMotor.setPower(power);
        }
        public void IntakeOff(double power){
            IntakeMotor.setPower(power);
        }
        public void WobbleArmRaised(double power){WobbleArmMotor.setPower(power);}
        public void WobbleArmLower(double power){WobbleArmMotor.setPower(-power);}



        //    sensorWobbleArmLower() == false &&
        public void WobbleArmLowerColorSensor () {
            wobbleArmTimer.reset();
            while (linearOp.opModeIsActive() && wobbleArmTimer.time() < maxWobbleArmLowerTime) {
                WobbleArmLower(1.0);
            }
            WobbleArmStopMotors();
        }
        public void RingPusherTimer(){
            ringPusherTimer.reset();
            while (linearOp.opModeIsActive() && ringPusherTimer.time() < maxRingPusherTime){
                RingPush();
            }
        }
        public void RingPullerTimer(){
            while (linearOp.opModeIsActive() && ringPusherTimer.time() < maxRingPullerTime){
                RingPull();
            }
        }

        public void RingFullAuto(){
//        sleeeeeeeeeeaaaaaspoprts its in the game3 = autonomousmonkey
//        what are these random words?
        }

        public void SpinInIntakeCorrector(){
            IntakeCorrector.setPower(1);
        }

//    public void SpinOutIntakeCorrector(){
//        IntakeCorrector.setPower(-1);
//    }

        public void StopIntakeCorrector(){
            IntakeCorrector.setPower(0);
        }

        public void FirstLaunch(){
            RingPull();
            ringPusherTimer.reset();
            launchModePush = true;
            launchModePull = false;
            rapidFireRing++;
//        linearOp.telemetry.addLine("FIRST");
//        linearOp.telemetry.update();
        }
        public void rapidFire(){
            if (rapidFireRing == 0){
                ringPusherTimer.reset();
                rapidFireRing++;
                launchModePush = true;
                launchModePull = false;
            }
            else if (rapidFireRing == 1){
                FirstLaunch();
            }
            else if (rapidFireRing == 2){
                rapidFireOp();
            }
            else if (rapidFireRing == 3){
                rapidFireOp();
            }
            else if (rapidFireRing == 4){
                rapidFireOp();

            }
            else if (rapidFireRing == 5){
                rapidFireOp();
            }
            else if (rapidFireRing == 6){
                rapidFireOp();
            }
            else{
                RingPull();
            }

        }

        public void rapidFireOp(){
            if (ringPusherTimer.milliseconds() > rapidPushTimer && launchModePush == true){
                RingPush();
                ringPusherTimer.reset();
                launchModePush = false;
                launchModePull = true;
            }
            else if (ringPusherTimer.milliseconds() > rapidPullTimer && launchModePull == true){
                RingPull();
                ringPusherTimer.reset();
                rapidFireRing++;
                launchModePush = true;
                launchModePull = false;
            }
        }
        public void RingPusherPullBack(){
        }

        public void WobbleArmRaiseColorSensor () {
            while (linearOp.opModeIsActive()  && wobbleArmTimer.time() < maxWobbleArmRaiseTime) {
                WobbleArmRaised(0.8);
            }
            WobbleArmStopMotors();
        }

        public void WobbleArmStopMotors () {
            WobbleArmMotor.setPower(0);
        }
        //        Pulls back pusher

        public void RingPush() {
            ServoRingPusher.setPosition(RingPushPos);
        }
        //        Pushes ring to launch!  Zoom Zoom
        public void RingPull() {
            ServoRingPusher.setPosition(RingPullPos);
        }

        public void RingPullIncrement(){
            if (ServoRingPusher.getPosition() <= RingPullPos) {
                ServoRingPusher.setPosition(ServoRingPusher.getPosition() + ringIncrement);
            }
        }
        public void RingMagIncrement(){
            if (RingMag.getPosition() <= RingMagUpPos){
                RingMag.setPosition(RingMag.getPosition() + ringIncrementMag);
            }
        }
        public void RingMagUp(){
            RingMag.setPosition(RingMagUpAuto);
        }
        public void RingMagDown(){
            RingMag.setPosition(RingMagDownPos);
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
//        while (currentPos < ticks + startPosition && linearOp.opModeIsActive()) {
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


                // missing waiting

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


        }


        public void driveGyroStraight (int encoders, double power) throws InterruptedException {

            angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
            double currentPos = 0;
            double leftSideSpeed;
            double rightSideSpeed;


            double target = angles.firstAngle;
            double startPosition = frontLeftMotor.getCurrentPosition();
            //  linearOp.telemetry.addData("Angle to start: ", angles.firstAngle);
            //  linearOp.telemetry.update();
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

