package org.firstinspires.ftc.teamcode.Compitition.CenterStage.OffseasonProjects;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous (name = "APrilTagAllign_Test")
public class AprilTagAlign_AutoTest extends AprilTagALign_FORAUTOUSAGE {



    public double GYRO_PATH_SPD = .5;
    public    double GYRO_CORRECT_SPD = .21;
    public double MAX_SPD = 1.0;
    public  double FAST_SPD = .7;
    public double MED_SPD = .5;
    public double STRAFE_SPD = .8;
    public double LONG_STRAFE_SPD = 1;
    public  int SLEEP_GYRO = 150;
    public   int SLEEP_TIME = 100;



    @Override
    public void runOpMode() throws InterruptedException {
        Bot.initRobot(hardwareMap);
        Bot.setLinearOp(this);



        boolean targetFound     = false;    // Set to true when an AprilTag target is detected
        double  drive           = 0;        // Desired forward power/speed (-1 to +1)
        double  strafe          = 0;        // Desired strafe power/speed (-1 to +1)
        double  turn            = 0;        // Desired turning power/speed (-1 to +1)
       //for april tag stuff

        telemetry.addLine("Robot Awaiting Start Procedure");
        telemetry.update();





        waitForStart();


        while (opModeIsActive()) {
            Bot.resetHeading();

            telemetry.addLine("Robot Autonomous Control Initialized");

            Bot.speedAcceleration(2.5,FAST_SPD, RipOffRoadrunner_Adapted_MecanumDrive.driveDirections.DRIVE_FORWARD);
            sleep(500);

            telemetry.addLine("DRIVE FORWARD");
            Bot.gyroTurn(GYRO_CORRECT_SPD,0);
            sleep(500);
            telemetry.addLine("GYRO CORRECT");
            Bot.gyroTurn(GYRO_PATH_SPD,90);
            sleep(500);
            telemetry.addLine("TURN TARGET ANGLE 90");
            Bot.gyroTurn(GYRO_CORRECT_SPD,90);
            sleep(500);
            telemetry.addLine("GYRO CORRECT");
            AprilTagAutoAdjust();
            sleep(100);
            telemetry.addLine("APRIL TAG ALIGNMENT");
            Bot.gyroTurn(GYRO_CORRECT_SPD,90);
            sleep(500);

//            if (isRobotAlligned == true) {
//                telemetry.addLine("Robot is Alligned");
//            }
//            else if (isRobotAlligned == false) {
//                telemetry.addLine("RObot is NOT alligned");
//            }

            telemetry.update();


        
            requestOpModeStop();

        }

        idle();

    }


}
