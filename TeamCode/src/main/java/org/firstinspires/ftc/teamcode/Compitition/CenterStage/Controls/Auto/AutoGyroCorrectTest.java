package org.firstinspires.ftc.teamcode.Compitition.CenterStage.Controls.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Compitition.CenterStage.Robots.ProgrammingBot;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;

@Autonomous (name = "GyroTest")
public class AutoGyroCorrectTest extends AutoMain{

    public static final boolean USE_WEBCAM = true;
public ProgrammingBot Bot = new ProgrammingBot();
    public static int oneSecond = 1000;

    public TfodProcessor tFod;
    @Override
    public void runOpMode() throws InterruptedException {
        Bot.initRobot(hardwareMap);

        Bot.setLinearOp(this);


        telemetry.addLine("Starting Vision Pipeline");
//



        telemetry.addLine("Robot Awaiting Start Procedure");
        telemetry.update();



        waitForStart();



        while (opModeIsActive()) {

            telemetry.addLine("Robot Autonomous Control Initialized");

            Bot.driveForward(0.5,2.0);
            sleep(500);
            Bot.gyroCorrection(0.5,90);
            sleep(50);
            Bot.gyroCorrection(0.5,-179);






            telemetry.addLine("Robot Autonomous Control Complete");

            requestOpModeStop();
        }

        idle();


    }





    public void telemetryUpdate(String comment) {

        telemetry.addLine(comment);
        telemetry.addData("Front Lef Motor:", Bot.frontLeftMotor.getPower());
        telemetry.addData("Front Rig Motor:", Bot.frontRightMotor.getPower());
        telemetry.addData("Rear Lef Motor:", Bot.rearLeftMotor.getPower());
        telemetry.addData("Rear Rig Motor:", Bot.rearRightMotor.getPower());
        telemetry.addData("Encoder Count: ", Bot.frontLeftMotor.getCurrentPosition());
        telemetry.update();
    }




}
